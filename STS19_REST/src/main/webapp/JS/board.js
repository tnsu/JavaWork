/**
 * 
 */

var page = 1; // 현재 페이지
var pageRows = 10; // 한 페이지에 보여지는 게시글 개수
var viewItem = undefined;   // 가장 최근에 view 한 글 데이터
$(document).ready(function() {
	// 게시판 목록 1페이지 로딩
	loadPage(page); // 현재 페이지를 로드 한다.
	
	// 글 작성 버튼 누르면 팝업
	$('#btnWrite').click(function() {
		setPopup('write');
		$('#dlg_write').show();
	});
	
	// 모달 대화상자에서 클로즈 버튼 누르면 닫기
	$('.modal .close').click(function() {
		//$('#dlg_write').hide();
		$(this).parents(".modal").hide();
	});
	
	// 글 작성 submit 처리(db저장, action은 없으나)
	$("#frmWrite").submit(function() {
		$(this).parents(".modal").hide(); // 저장 처리를하면 
		return chkWrite(); // 페이지 리로딩이 발생되면 안된다. but 리쿼스트는 할거기 때문에 fun 이용
	});
	
	// 글 삭제 버튼 누르면 
	$('#btnDel').click(function() {
		return chkDelete();
	});
	
	// 글 읽기(view) 대화 상자에서 삭제버튼 누르면 해당 글(uid) 삭제 진행
	$('#viewDelete').click(function() {
		var uid = viewItem.uid;
		if(deleteUid(uid)){//해당글 삭제
			$(this).parents(".modal").hide(); // 삭제 성동하면 대화상자 닫기
			
		}
	});
	
	// 글 읽기(view) 대화 상자에서 수정버튼 누르면 해당 글  수정
	$('#viewUpdate').click(function() {
		setPopup("update");
	});
	
	
	// 글 수정 완료 버튼 누르면 
	$('#updateOk').click(function() {
		chkUpdate();
	});
});


// page 번쨰 페이지 로딩
function loadPage(page) {
	$.ajax({
		url : "list.ajax?page=" + page + "&pageRows="  + pageRows
		, type : "GET"
		, cache : false
		, success : function(data, status){
			if(status == "success"){
				
				// alert("AJAX 성공: 받아쮸~");
				
				
				if(updateList(data)){
					
					// 업데이트된 list 에 필요한 이벤트 가동(문서가 다 로딩된다음에 글의 목록을 불러와야하기 때문에 
					addViewEvent();
					// 만약 위코드를 $(document)/ready()에 두면 동작안할것이다.
					
				}
			}			
		}
	});
}// end loadPage()

// 블러온 데이터 사용 화면 업데이트
function updateList(jsonObj) {
	result = "";
	
	if(jsonObj.status == "OK"){
		
		var count =jsonObj.count;
		
		//전역변수 업데이트
		window.page = jsonObj.page;
		window.pageRows = jsonObj.pagerows;
		
		var i ;
		var items = jsonObj.data; // 배열
		for (var i = 0; i < count; i++) {
			result += "<tr>";
			result += "<td><input type='checkbox' name='uid' value='"+items[i].uid+ "'></td>";
			result += "<td>" + items[i].uid + "</td>";
			result += "<td><span class='subject' data-uid='"+ items[i].uid+"'>" + items[i].subject  + "</span></td>";
			result += "<td>" + items[i].name  + "</td>";
			result += "<td><span data-viewcnt='"+ items[i].uid + "'>" + items[i].viewcnt   + "</span></td>";
			result += "<td>" + items[i].regdate  + "</td>";			
			result += "</tr>";
		}//end for
		$('#list tbody').html(result); // 테이블 업데이트
		
		
		// 페이지 정보 업데이트
		$("#pageinfo").text(jsonObj.page + "/" + jsonObj.totalpage + "페이지, " + jsonObj.totalcnt + "개의글");
		
		// pageRows
		var txt = "<select id='rows' onchange='changePageRows()'>";
		txt += "<option " + ((window.pageRows == 10)?"selected":"") + " value='10'>10개씩</option>\n";
		txt += "<option " + ((window.pageRows == 20)?"selected":"") + " value='20'>20개씩</option>\n";
		txt += "<option " + ((window.pageRows == 50)?"selected":"") + " value='50'>50개씩</option>\n";
		txt += "<option " + ((window.pageRows == 100)?"selected":"") + " value='100'>100개씩</option>\n";
		txt +="</select>";
		
		$('#pageRows').html(txt);
		
		// 페이징 업데이트
		var pagination = buildPagination(jsonObj.writepages, jsonObj.totalpage, jsonObj.page, jsonObj.pagerows);
		$('#pagination').html(pagination);
		
		return true;
	}else{
		alert(jsonObj.message);
		return false;
	}
	return false;
}

function buildPagination(writePages, totalPage, curPage, pageRows) {

	var str = "";   // 최종적으로 페이징에 나타날 HTML 문자열 <li> 태그로 구성
	
	// 페이징에 보여질 숫자들 (시작숫자 start_page ~ 끝숫자 end_page)
    var start_page = ( (parseInt( (curPage - 1 ) / writePages ) ) * writePages ) + 1;
    var end_page = start_page + writePages - 1;

    if (end_page >= totalPage){
    	end_page = totalPage;
    }
    
  //■ << 표시 여부 (맨처음 페이지
	if(curPage > 1){
		str += "<li><a onclick='loadPage(1)' class='tooltip-top' title='처음'><i class='fas fa-angle-double-left'></i></a></li>\n";
	}
	
  	//■  < 표시 여부
    if (start_page > 1) 
    	str += "<li><a  onclick='loadPage("+(start_page - 1)+")' class='tooltip-top' title='이전'><i class='fas fa-angle-left'></i></a></li>\n";
    
    //■  페이징 안의 '숫자' 표시	
	if (totalPage > 1) {
	    for (var k = start_page; k <= end_page; k++) {
	        if (curPage != k)
	            str += "<li><a onclick='loadPage("+ k +")' >" + k + "</a></li>\n";
	        else
	            str += "<li><a class='active tooltip-top' title='현재페이지'>" + k + "</a></li>\n";
	    }
	}
	
	//■ > 표시
    if (totalPage > end_page){
    	str += "<li><a onclick='loadPage("+(end_page +1)+")'  class='tooltip-top' title='다음'><i class='fas fa-angle-right'></i></a></li>\n";
    }

	//■ >> 표시
    if (curPage < totalPage) {
        str += "<li><a onclick='loadPage("+totalPage+")' class='tooltip-top' title='맨끝'><i class='fas fa-angle-double-right'></i></a></li>\n";
    }

    return str;


}// end builcPagination()


function changePageRows(){
	window.pageRows = $("#rows").val();
	loadPage(window.page);
}// end changePageRows()

// 새글 등록 처리 
function chkWrite() {
	var data = $('#frmWrite').serialize(); // 해당 폼 안의 name 이 있는 것들을 끌고 들어옴(String )
			// subject=ddd&name=eeee&content=qwerwe (get 방식 형태로 보내김 )
	//	alert(data+"--"+ typeof data); 확인용
	
	// ajax request
	$.ajax({
		url : "writeOk.ajax",
		type : "POST",
		cache : false,
		data : data,  // post 로 ajax request 하는 경우 parameter 담기
		success : function(data, status) {
			if(status == "success"){
				if(data.status == "OK"){
					alert("INSERT 성공" + data.count+ "개:" + data.status);
					loadPage(1); // 첫 페이지 리로딩
				}else{
					alert("INSERT 실패"+data.status+" : " + data.message);
				}
			}
		}
	});
	
	//request 후, form 에 입력된 것 reset();  .reset(); 어떤 특정 오브젝트를 리셋해야한다. (javascript 함수) jquery아님
//	$('#frmWrite').reset();
	$('#frmWrite')[0].reset();
	
	
	return false; // 페이지 리로딩은 안할것!
}// end chkWrite()

// check  된 uid 의 게시글들만 삭제
//.each() 그 각각에 대해서 함수를 진행햐라
function chkDelete() {
	var uids=[] // 빈 배열 준비
	$('#list tbody input[name=uid]').each(function() {
		//$(this) 는 checkbox
		if($(this).is(":checked")){ // jQuery 에서 check 여부 확인 방법
			uids.push($(this).val()); // 배열에 uid 값 추가
		}
	});
	
//alert(uids);
	if(uids.length == 0){
		alert("삭제할 글을 체크해 주세여");
	}else{
		if(!confirm(uids.length+"개의 글을 삭제하시겠습니까?")) return false; // 취소를 누르면 체크된 상태로 취소
		
		var data = $('#frmList').serialize();
		//uid=1010&uid=1111
		
		$.ajax({
			url:'deleteOk.ajax',
			type : "POST",
			data : data,
			cache : false,
			success : function(data, status) {
				if(status == "success"){
					if(data.status == "OK"){
						alert("DELETE 성공 " + data.count+ "개");
						// 현재 페이지 리로딩
						loadPage(window.page);// 전역변수로 가지고 있기떄문에 현제페이지 리로딩 가능 하지만 저녕ㄱ변수 많이 사용하지 말기(싱글 페이지라서 적용이 가능)
					}else{
						alert("DELETE 실패 " + data.message);
					}
				}
			}
		});
		
	}
	
}// end chkDelete()


// 현재 글 목록 list에 대해 이벤트 등록
function addViewEvent() {
	
	$('#list .subject').click(function() {
		
		//alert($(this).text() + " : " + $(this).attr('data-uid')); // 확인용

		// 읽어오기
		$.ajax({
			url : "view.ajax?uid="+$(this).attr('data-uid'),
			type : "GET",
			cache : false,
			success : function(data, status) {
				if(status == "success"){
					if(data.status == "OK"){
						// 읽어온 view 데이터를 전역변수에 세팅
						viewItem = data.data[0]; // 0 인이유는 하나밖에 없으니깡?.
						
						// 팝업에 보여주기
						setPopup("view");
						$("#dlg_write").show();
						
						// 리스트 상의 조회수 증가시키기 ( data 안에 값이 담겨있따
						$("#list [data-viewcnt='"+ viewItem.uid+"']").text(viewItem.viewcnt);
						
					}else{
						alert("VIEW 실패" + data.message);
					}
				}
			}
		});
		
	});
	
}// end addViewEvent()


function setPopup(mode) {

	
	//글 작성
	if(mode == 'write'){
		// jquery 객체  집합객체이기 떄문에 반드시 [] 해줘애함 그래야 자바스크립트 함수사용가능
		$('#frmWrite')[0].reset(); // from 안의 기존 내용 reset
		$("#dlg_write .title").text('새글 작성');
		$('#dlg_write .btn_group_header').show();
		$('#dlg_write .btn_group_write').show();
		$('#dlg_write .btn_group_view').hide();
		$('#dlg_write .btn_group_update').hide();
		
		$("#dlg_write input[name='subject']").attr("readonly", false);
		$("#dlg_write input[name='subject']").css("border", "1px solid #ffd");
		
		$("#dlg_write input[name='name']").attr("readonly", false);
		$("#dlg_write input[name='name']").css("border", "1px solid #ffd");
		
		$("#dlg_write textarea[name='content']").attr("readonly", false);
		$("#dlg_write textarea[name='content']").css("border", "1px solid #ffd");
	}
	
	//글 읽기
	if(mode == 'view'){
		$('#frmWrite')[0].reset(); // from 안의 기존 내용 reset
		$("#dlg_write .title").text('글 읽기');
		$('#dlg_write .btn_group_header').show();
		$('#dlg_write .btn_group_write').hide();
		$('#dlg_write .btn_group_view').show();
		$('#dlg_write .btn_group_update').hide();
		
		// 조회수와 날짜
		$("#dlg_write #viewcnt").text("#" + viewItem.uid + " - 조회수: " + viewItem.viewcnt);
		$("#dlg_write #regdate").text(viewItem.regdate);
		$("#dlg_write input[name='uid']").val(viewItem.uid);  // 나중에 삭제/수정을 위해 필요
		
		$("#dlg_write input[name='subject']").val(viewItem.subject);
		$("#dlg_write input[name='subject']").attr("readonly", true);
		$("#dlg_write input[name='subject']").css("border", "none");
		
		$("#dlg_write input[name='name']").val(viewItem.name);
		$("#dlg_write input[name='name']").attr("readonly", true);
		$("#dlg_write input[name='name']").css("border", "none");
		
		$("#dlg_write textarea[name='content']").val(viewItem.content);
		$("#dlg_write textarea[name='content']").attr("readonly", true);
		$("#dlg_write textarea[name='content']").css("border", "none");
	
	
	}
	
	//글 수정
	if(mode == 'update'){
		//$('#frmWrite')[0].reset(); // from 안의 기존 내용 reset 인데 수정은 reset하면 담겨져있던 val이 사라짐
		$("#dlg_write .title").text('수정');
		$('#dlg_write .btn_group_header').show();
		$('#dlg_write .btn_group_write').hide();
		$('#dlg_write .btn_group_view').hide();
		$('#dlg_write .btn_group_update').show();
		
		$("#dlg_write input[name='subject']").attr("readonly", false);
		$("#dlg_write input[name='subject']").css("border", "1px solid #ffd");
		
		$("#dlg_write input[name='name']").attr("readonly", true);
		
		$("#dlg_write textarea[name='content']").attr("readonly", false);
		$("#dlg_write textarea[name='content']").css("border", "1px solid #ffd");
	}
}// end setPopup()


// 특정 uid 의 글 삭제하기
function deleteUid(uid) {
	if(!confirm(uid + "글을 삭제 하시겠습니까?")) return false;
	
	
	// POST 방식
	$.ajax({
		url : "deleteOk.ajax",
		type: "POST",
		data : "uid=" + uid,
		cache : false,
		success : function(data, status) {
			if(status == "success"){
				if(data.status == "OK"){
					alert("DELETE 성공" + data.count +"개");
					loadPage(page); // 현재 페이지 로딩
				}else{
					alert("DELETE 실패" + data.message);
					return false;
				}
			}
		}
	});
	
	return true;
}// end deleteUid


// 글 수정 
function chkUpdate() {
	
	var data= $("#frmWrite").serialize();
	
	$.ajax({
		url : "updateOk.ajax",
		type : "POST",
		cache : false,
		data : data,
		success : function(data, status) {
			if (status == "success") {
				if (data.status == "OK") {
					alert("UPDATE 성공" + data.count + "개: " + data.status);
					loadPage(window.page); // 현재 페이지 리로딩
				} else {
					alert("UPDATE 실패" + data.status + " : " + data.message);
				}
				$("#dlg_write").hide(); 	// 현재 팝업닫기
			}
		}
	});
	
}// end chkUpdate()

