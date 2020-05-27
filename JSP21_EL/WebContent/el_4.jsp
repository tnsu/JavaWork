<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.lec.beans.*" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>EL request</title>
</head>
<body>
<%
// EL 을 사용하면 request 객체 안의 내용을 손쉽게 출력 가능
	request.setAttribute("myage", 30);
	request.setAttribute("mydto", new WriteDTO(100, "제목","내용","작성자",3));
	pageContext.setAttribute("myage", "흥!~");
%>
<%-- 동일한 이름이면 우선 순위가 있다. page, request, session, application 순이다 
구체적으로 하고 싶을때 requestScope 사용--%>
${myage }<br>
${requestScope.myage }<br>

${mydto}<br>     <%-- toString() 결과값 나온다. --%>
${mydto.uid }<br>
<%= ((WriteDTO)request.getAttribute("mydto")).getUid() %><br>
${mydto.subject }<br>
${mydto.content }<br>
</body>
</html>