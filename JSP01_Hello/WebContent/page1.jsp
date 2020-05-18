<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>page1</title>
</head>
<body>
<h1>page1</h1>
<a href="Hello.jsp">Hello</a><br> <!-- 리퀘스트가 발생    -->
<button onclick="history.back()">back</button> <!-- history.back() : 이전화면으로 돌아감 리퀘스트가 발생안하고 돌아감 -->
</body>
</html>