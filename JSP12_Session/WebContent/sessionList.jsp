<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Enumeration"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Session List</title>
</head>
<body>
<%
	if(request.isRequestedSessionIdValid()){
		out.println("유효한 세션이 있습니다.<hr>");
	}else{
		out.println("유효한 세션이 없습니다.<hr>");
	}

	String sessionName, sessionValue;
	Enumeration<String> enumeration =  session.getAttributeNames();  // Enumeratoin<String> 리턴
   
	int i = 0;
	while(enumeration.hasMoreElements()){ // 세션안에있는 모든 정보를 가져옴
		sessionName = enumeration.nextElement();  
		// session.getAttribute(sessionName)  <-- 특정 name의 세션값 추출, name 없으면 null 리턴 
		sessionValue = session.getAttribute(sessionName).toString(); // 세션네임인 1800 --> 30분 기다림 
		out.println((i + 1) + "] " + sessionName + " : " + sessionValue + "<br>");

		
		i++;
	}// end while

	if(i == 0){
		out.println("세션이 없습니다.<br>");
	}
%>
<hr>
<form action="sessionCreate.jsp">
<input type="submit" value="세션생성">
</form>

<br>
<form action="sessionDelete.jsp">
<input type="submit" value="세션삭제">
</form>
<br>
<%
	String sessionId = session.getId();  // 세션아이디값 뽑아내기 
	int sessionInterval = session.getMaxInactiveInterval(); // 세션 유효 시간
	
	out.println("세션 ID: " + sessionId + "<br>");
	out.println("세션 유효시간: " + sessionInterval + "<br>");
%>
<br><br>
<%
	out.println("---- session.invalidate() 후 -----<br>");
	// 세션 무효화 invalidate
	// 기존 세션테이블 삭제 (session id 무효화)
	//   - 그 안의 모든 attribute(이름)도 삭제됨.
	// 심지어 sessionId 마저 무효화.
	// 새로운 세션테이블 생성
	session.invalidate();  // 세션 무효화
	
	sessionId = session.getId();
	sessionInterval = session.getMaxInactiveInterval();
	
	out.println("세션 ID: " + sessionId + "<br>");
	out.println("세션 유효시간: " + sessionInterval + "<br>");	
	// '새로고침 하면서'  어떻게 바뀌는지 보자
	
	if(request.isRequestedSessionIdValid()){
    	out.println("유효한 세션 있습니다...");    	
    }else{
    	out.println("유효한 세션이 없습니다...");
    }
%>


</body>
</html>