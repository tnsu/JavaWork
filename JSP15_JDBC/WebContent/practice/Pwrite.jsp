<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>글 작성</title>
</head>
<body>
<h2>새 글 작성</h2>
<form name="frm" action="PwriteOk.jsp" method="post" onsubmit="return chkSubmit()">
작성자 : <input type="text" name="name"/><br>
제목 : <input type="text" name="title"/><br>
내용 : <br>
<textarea rows="20" cols="30" name="content" placeholder="날짜는 수정되지 않아요!"></textarea>
<br><br>
날짜 : <input type="date" name="date" id="date"/>
<input type="submit" value="등록"/>
</form>
<script>
Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
});

var d = new Date();
  document.getElementById('date').value =  new Date().toDateInputValue(); //d.getFullYear() + "-" + d.getMonth() + "-" + d.getDate();
//var localD = d.toLocaleDateString();
  
  //new Date().toISOString().substring(0, 10);
</script>
<br>
<button type="button" onclick="location.href='list.jsp'">목록 보기</button>
</body>
</html>