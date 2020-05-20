<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%
	String sessionName1 = "num1";
	String sessionValue1 = "" + (int)(Math.random() * 10);
	
	// 세션생성
	// setAttribute(String arg0, Object arg1) 두번째 매개변수는 Object 타입이다
	session.setAttribute(sessionName1, sessionValue1);
	
	
	String sessionName2 = "datetime";
	String sessionValue2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	
	session.setAttribute(sessionName2, sessionValue2);
	
	// session 객체는 웹 브라우저와 매핑되므로 
	// 해당 웹 브라우저를 닫지 않는 한 같은 창에서 열려진 페이지는 모두 같은 session 객체를 공유하게 된다. 
	// 따라서 session 객체의 setAttribute() 메소드를 사용해서 세션의 속성을 지정하게 되면 
	// 계속 상태를 유지하는 기능을 사용할 수 있게 된다.
%>

<script>
alert("<%= sessionName1 %> <%= sessionName2 %>세션 생성");
location.href = "sessionList.jsp";
</script>

