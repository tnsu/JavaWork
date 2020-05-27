<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lec.beans.*"  %>
<%	//Controller 로 부터 결과 데이터 받음
	WriteDTO [] arr = (WriteDTO [])request.getAttribute("list");
%>
  
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>글 목록</title>
<style>
table { width: 100%;}
table, th, td {
	border: 1px solid orange;
	border-collapse: collapse;
	text-align: center;
}
th{
	background-color: lightpink;
}
a{
	text-decoration: none;
	color: black;
}
a:hover{
	color: olive;
}
</style>

</head>
<body>
<%
		if(arr == null || arr.length == 0){
			out.println("멤버가 없습니다<br>");	
		} else {
%>
	<hr>
	<h2>JSP19_리스트</h2>
	<table>
		<tr>
			<th>UID</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>등록일</th>
		</tr>
<%
	for(int i = 0; i < arr.length; i++){
		%>
		<tr>
			<td><%= arr[i].getUid() %></td>
			<td><a href="view.do?uid=<%=arr[i].getUid()%>"><%= arr[i].getSubject() %></a></td>
			<td><%= arr[i].getName()%></td>
			<td><%= arr[i].getViewCnt() %></td>
			<td><%= arr[i].getRegDate() %></td>	
		</tr>
<% 
	} // end for
} // end if
%>
	</table>
	<br>
	<button onclick="location.href='write.do'">새글  등록</button>
</body>
</html>





