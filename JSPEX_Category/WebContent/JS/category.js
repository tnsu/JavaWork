
var parent = 0;
var depth = 1;
$(document).ready(function() {
	loadList(depth, parent);
	
	$('#mycate select ').change(function() {
		parent = $(this).val();
		parent = parseInt(parent);
		depth = $(this).find(':selected').attr('data-depth');
		depth = parseInt(depth);
		depth += 1;
		if(parent !=0){
			loadList(depth, parent);
		} else {	
//			alert(depth);
			for (var i = 0; i < depth; i++) {
				$('#mycate span:nth-child('+(depth+i)+') select').attr("disabled", true);
				$('#mycate span:nth-child('+(depth+i)+') select ').html("");				
			}
			
		}
	});
});

function loadList(depth, parent) {
	$.ajax({
		url : "cate_list.ajax",
		type : "POST",
		data : {
			"depth" : depth,
			"parent" : parent
		},
		cache : false,
		success : function(data,status) {
			if(status == "success"){
				updateList(data);
			}
		}
	});
}

function updateList(data) {
	result = "";
	if(data.status == "OK"){
		var count =data.count;
		
		var items = data.data; // 배열
		result += "<option value='0' data-depth='"+depth+"'>--선택해주세요--</option>";
		for (var i = 0; i < count; i++) {
			result += "<option value='"+items[i].uid + "' data-depth='"+items[i].depth+"'>"+items[i].name+"</option>";
		}
		$('#mycate span:nth-child('+depth+') select ').attr("disabled", false);
		$('#mycate span:nth-child('+depth+') select ').html(result);
		$('#mycate span:nth-child('+(depth+1)+') select').attr("disabled", true);
		$('#mycate span:nth-child('+(depth+1)+') select ').html("");
		return  false;
	}else{
		alert(data.message);
		return false;
	}
}
