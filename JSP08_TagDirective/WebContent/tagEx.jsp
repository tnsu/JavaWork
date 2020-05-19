<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- jsp 가 꼭 html로 변환되지 않는다는 것을 보여줌 -->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>

	<%
 				int sum = 0;
	 for(int cnt = 0 ; cnt <= 100; cnt++){
		 sum += cnt;
	 }
  		%>
	1부터 100까지의 합 :
	<%= sum %>
	<hr>
	
	<h3>오늘의 식단</h3>
	- 비빔밥<br>
	- 엽떡<br>
	- 바지락 칼국수<br>
	<%@ include file="test.jsp"%>

</body>
</html>