<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.net.*"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title> include</title>
</head>
<body>
<!-- Action Tag -->
<h3> 지금 현재 페이지는  include  페이지 입니다...</h3> 
<jsp:include page = "sub.jsp"/>
<p> 위 라인의 내용은 sub 페이지 의 내용입니다 </p>

<!--  include 지시자 VS include page -->
<hr>
<!--  include directive -->
<h3> 지금 현재 페이지는  include  페이지 입니다...</h3> 
<%@ include file="sub.jsp" %>
<p> 위 라인의 내용은 sub 페이지 의 내용입니다 </p>

<hr>
<%!
	//변수선언
	String name = "홍길동";
	int age = 33;
%>
<%@ include file="sub2.jsp" %>
 
<%-- <jsp:include page="sub2.jsp" /> --%>
<!-- 값이 넘어가지 않는다 인클루드 하는 시점이 실행중에 삽입되기 때문에  에러 발생-->
		<!--  값을 넘겨주려면  아래처럼 진행-->


<jsp:include page="sub3.jsp">
	<jsp:param value='<%= URLEncoder.encode(name, "utf-8") %>' name="name"/>
	<!-- 자바 는" " 만 되기 때문에 (' ' : 문자를 나타냄) 앞에 ' ' 써주고 안에는(자바 영역) " " 써준다 -->
	<jsp:param value="<%= age %>" name="age"/>
</jsp:include>




</body>
</html>