<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>cookie 삭제</title>
</head>
<body>
<%
	Cookie[] cookies = request.getCookies();  // Cookie[] 배열을 리턴
	String cookieName = "num1";
	
	if(cookies != null){  // 만약 쿠키가 하나도 없다면 null 이 리턴된다
		for(int i = 0 ; i < cookies.length; i++){
			if(cookieName.equals(cookies[i].getName())){ // 쿠키 이름이 num1 이 있으면 
				cookies[i].setMaxAge(0);  // expiry 를 0 으로 하면 
				response.addCookie(cookies[i]);	// response 직후 곧바로 삭제
			}
		}
	}
%>
<script>
alert("<%= cookieName %> 쿠키 삭제");
location.href = "cookieList.jsp";
</script>

</body>
</html>

