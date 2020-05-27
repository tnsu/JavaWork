<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>EL 내장객체</title>
</head>
<body>
<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String []hobby = request.getParameterValues("hobby");
%>

아이디 : <%= id %><br>
비번 : <%= pw %><br>
취미 :
<% for(int i = 0; i < hobby.length; i++){ %>
		<%= hobby[i] %>
<%} %>

<hr>
<h3>EL 객체</h3>
아이디 : ${param.id }<br>
비번 : ${param.pw }<br>
취미 :${ paramValues.hobby[0] } 
		${ paramValues.hobby[1] } 
		${ paramValues.hobby[2] } <br>
<hr>
	
	아이디 : ${ param["id"] } <br>
	비밀번호 : ${ param["pw"] } <br> 
	취미: ${ paramValues["hobby"][0] } 
		${ paramValues["hobby"][1] } 
		${ paramValues["hobby"][2] } <br>
	<hr>
applicationScope : ${ applicationScope.application_name }<br>
sessionScope : ${ sessionScope.session_name }<br>
	pageScope : ${ pageScope.page_name }<br>
	requestScope : ${ requestScope.request_name }<br>
	<hr>
context 파라메터 초기화 <br>
${initParam.con_name }<br>
${initParam.con_id }<br>
${initParam.con_pw }<br>
<hr>

ContextPath<br>
<%= request.getContextPath() %><br>
${pageContext.request.contextPath }<br> 
<%-- 굉장히 많으 사용하는  --%>

<hr>
<a href="/JSP21_EL/el_foem.jsp">입력폼</a><br> 

<%-- 보다는 꼭 필요할 때 이렇게 사용한다. 그다지 추천은 안한다. 하지만 사용할때 있다. --%>
<a href="${pageContext.request.contextPath }/el_foem.jsp">입력폼 :ContextPath</a><br> 
</body>
</html>


















