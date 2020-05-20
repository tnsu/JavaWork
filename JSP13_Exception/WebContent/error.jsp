<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 이페이지는 에러핸들링을 위한 페이지 이다 라는 처리를 위해 씀 --%>
<%@ page isErrorPage="true" %> 
<% response.setStatus(200);%> 
<%-- response 코드를 정상으로 돌린다. 이 상태값으로 현재 웹페이지가 정상인지 에러인지를 알수 있습니다. URL 안바뀐다,
 --%>   
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>에러 안내</title>
</head>
<body>
에러가 발생했습니다.<br>
예외 타입 : <%= exception.getClass().getName() %> <br>
예외 메세지 : <%= exception.getMessage() %>
</body>
</html>