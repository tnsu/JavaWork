<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <jsp:useBean id="dto" class="com.lec.beans.WriteDTO" scope="page"/>
 <jsp:setProperty property="uid" name="dto" value="123"/>   
 <jsp:setProperty property="subject" name="dto" value="제목이야요"/>   
 <jsp:setProperty property="name" name="dto" value="작성자 이에오"/>   
   
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>EL - ActionTag</title>
</head>
<body>
		uid : <%= dto.getUid() %><br>
		제목 : <%= dto.getSubject() %><br>
		작성자 : <%= dto.getName() %><br>
		<hr>
uid : <jsp:getProperty name="dto" property="uid"/><br>
제목 : <jsp:getProperty name="dto" property="subject"/><br>
작성자 : <jsp:getProperty name="dto" property="name"/><br>
<hr>
uid : ${dto.uid }<br>
제목 : ${dto.subject }<br>
작성자 : ${dto.name}<br>
<%--내용 : ${ddd.comment }<br>  프로퍼티는 없으면 에러가 난다? --%>
<hr>
</body>
</html>














