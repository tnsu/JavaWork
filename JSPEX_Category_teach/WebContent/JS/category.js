var MAX_DEPTH = 3;   // depth 1 ~ 3

$(document).ready(function(){
	getCategory(1);  // depth1 카테고리 읽기
});

// 특정 depth, 특정 parent 의 category 불러오기
function getCategory(depth, parent){
	if(parent == undefined) parent = 0;  // 상위부모 없는 경우
	
	$.ajax({
		url : "cate_list.ajax",
		type : "POST",
		data : {
			"depth" : depth,
			"parent" : parent
		},
		cache : false,
		success : function(data, status){
			if(status == "success"){
				buildSelect(depth, data);
			}
		}
	});
} // end getCategory()

// depth 값과 jsonObj 값으로 <select> 만들기
function buildSelect(depth, jsonObj){
	if(jsonObj.status != "OK") return;
	
	var elm = 
		$("div#mycate span:nth-child(" + depth + ") select");
	
	// 해당 depth 의 <select> 의 disabled 해제
	elm.attr("disabled", false);
	elm.off("change");  // 일단 onchange 이벤트 핸들러 삭제
	elm.empty();   // 내용 지우기
	
	var list = jsonObj.data;
	
	var result   // 선택안한 상태는 value 값을 0 으로 두자 
		= "<option value='0'>--선택하세요--</option>";
	for(i = 0; i < list.length; i++){
		result += "<option value='" + list[i].uid + "'>";
				// select 될때 uid 값. 다음 depth 의 parent 값!
		result += list[i].name;
		result += "</option>";
	}
	
	// <select> 에 result 결과값 추가
	elm.html(result);
	
	// select 의 onchange 이벤트 핸들러 작성
	
	// elm.on("change", function(){});
	elm.change(function(){
		
		// MAX_DEPTH 범위내에서만 가동.
		// 최하위 depth 일때는 가동할 필요 없슴
		if(depth < MAX_DEPTH){
			for(var d = depth + 1; d <= MAX_DEPTH; d++){
				var e = $("div#mycate span:nth-child(" + d + ") select");
				e.off("change");
				e.empty();
				e.attr("disabled", true);
			}
			
			// value 값이 0보다 큰 경우만
			// 즉 parent 값이 있는 것을 select 한 경우만 다음 depth 읽어오기 
			if(elm.val() > 0){
				getCategory(depth + 1, elm.val());  // 다음 depth, parnet값
			}
		}
		
	});
	
	
	
}  // end buildSelect()















