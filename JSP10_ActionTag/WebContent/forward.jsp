<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>forward</title>
</head>
<body>
<!-- Action Tag -->
<h3> 지금 현재 페이지는 forward 페이지 입니다...</h3>  <!--  과연 표시 될까? -->
<jsp:forward page = "sub.jsp"/>
 <!--  서버쪽에서 처리가되는 태그, html에서 처리, 컨테이너에서 처리가되는 태그 -->
 <!-- url 은 변경되지 않는다. Redirect 틑 Url 변경됨 
 title 도 변경
 
 -->
<p> 위 라인의 내용은 sub 페이지 의 내용입니다 </p> <!--  과연 표시 될까? -->
</body>


</html>