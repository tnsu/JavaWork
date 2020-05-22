<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lec.beans.*"  %>
<jsp:useBean id="dao" class="com.lec.beans.WriteDAO"/>  <%-- DAO bean 생성 --%>
<%	//dao 사용한 트랜잭션
	WriteDTO arr[] = dao.select();
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
	border: 1px solid olive;
	border-collapse: collapse;
	text-align: center;
}
th{
	background-color: lightyellow;
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
	<h2>JSP16_리스트</h2>
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
			<td><a href="view.jsp?uid=<%=arr[i].getUid()%>"><%= arr[i].getSubject() %></a></td>
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
	<button onclick="location.href='write.jsp'">새글  등록</button>
</body>
</html>





