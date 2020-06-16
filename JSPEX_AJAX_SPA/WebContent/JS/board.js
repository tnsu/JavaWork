/**
 * 
 */

var page = 1; // 현재 페이지
var pageRows = 10; // 한 페이지에 보여지는 게시글 개수

$(document).ready(function() {
	// 게시판 목록 1페이지 로딩
	loadPage(page); // 현재 페이지를 로드 한다.
	
	// 글 작성 버튼 누르면 팝업
	$('#btnWrite').click(function() {
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
				updateList(data);
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
			result += "<td>" + items[i].subject  + "</td>";
			result += "<td>" + items[i].name  + "</td>";
			result += "<td>" + items[i].viewcnt   + "</td>";
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






