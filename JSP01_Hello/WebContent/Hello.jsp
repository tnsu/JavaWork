<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>JSP 시작</title>
</head>
<body>

첫 JSP 파일 임<br>
<!--  C:\tomcat\apache-tomcat-9.0.35\work\Catalina\localhost\JSP01_Hello\org\apache\jsp tomcat 경로    -->
<a href="page1.jsp">page1</a><br>
<a href="./page2.jsp">page2</a><br> <!--  ./ : 현재경로 밑에 파일이다.  -->
<a href="/page3.jsp">page3</a><br>  <!--   / : 도메인 바로 뒤에서 시작한다. -->
</body>
</html>