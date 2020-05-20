<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cookie 리스트</title>
</head>
<body>
<%
	// 사용자 컴퓨터 안의 쿠키 정보는 request 시에(할때마다) 서버로 전달됨
	// request.getCookies() 로 쿠키 받아온다.
	Cookie[] cookies = request.getCookies();   // Cookie[] 배열을 리턴 없을수도있도 여러개 있을수 도 있다.
	
	if(cookies != null){ // 만약 쿠키가 하나도 없다면  null 이 리턴
		for(int i=0; i < cookies.length; i++){
			String cookieName = cookies[i].getName(); // 쿠키 '이름'
			String cookieValue = cookies[i].getValue(); // 쿠키 '값'
			out.println((i + 1) + "] " + cookieName + " : " + cookieValue + "<br>");
		}// end for
	}else{
		out.println("쿠키가 없습니다.<br>");
	}// end if
%>
<hr>
<form action="cookieCreate.jsp">
	<input type="submit" value="쿠키생성&갱신">
</form>
<br>
<form action="cookieDelete.jsp">
<input type="submit" value="쿠키삭제">
</form>

</body>
</html>