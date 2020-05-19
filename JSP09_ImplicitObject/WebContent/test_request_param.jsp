<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Arrays"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>request parameter</title>
</head>
<body>
<%!

	String data1, data2;
	String name, id, pw, gender, local, memo;
	String[] hobbys;
%>
<%
	request.setCharacterEncoding("utf-8");  // post 방식이기 때문에 꼭 해주기
	
	data1 = request.getParameter("data1").trim();
	data2 = request.getParameter("data2").trim();
 	name = request.getParameter("name").trim();
 	id = request.getParameter("id").trim();
 	pw = request.getParameter("pw").trim();
 	gender = request.getParameter("gender").trim();
 	local = request.getParameter("local").trim();
 	
 	hobbys = request.getParameterValues("hobby");
 	memo = request.getParameter("memo").trim();
 	// 없는 name 이 오면 null을 리턴한다.
%>
hidden : <%= data1 %>, <%= data2 %><br>
이름 : <%= name %><br> 
아이디 :<%= id %> <br>
 비밀번호 :<%= pw %><br>
 성별 :<%= gender %><br>
 취미 :<%= Arrays.toString(hobbys) %><br>
 지역 :<%= local %><br>
 메모 :<%= memo %>


</body>
</html>






