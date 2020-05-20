<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	int menu = 1; // menu parmeter 없으면 1 page 디폴트
    	String menu_param = request.getParameter("menu");
    										// menu 란 이름의 parameter 를 받는다
    	if(menu_param != null){
    		try{
    		menu = Integer.parseInt(menu_param);
    		}catch(NumberFormatException e){
    			
    		}
    	}
    	// 1 <= menu <= 4
    	if(menu > 4) menu = 4;
    	if(menu < 1) menu = 1;   
    %>
    <%-- menu값을 left.jsp에 넘겨줘야한다.  param 을 이용햐서--%>
    <!-- 라이브러리 끌고 들어옴 -->
<jsp:include page="top.jsp"/>
<jsp:include page="header.jsp"/>
<jsp:include page="nav.jsp"/>

<!-- 반응형 본문 시작 -->
<div class="container" style="margin-top: 30px">
	<div class="row">
	<jsp:include page="left.jsp">
	<jsp:param value="<%= menu %>" name="menu"/>
	</jsp:include>
	<% String article_page = "article" + menu +".jsp"; %>
	<!-- menu의 값에따라 페이지 변환되게 하기 -->
	<jsp:include page="<%= article_page %>"/>
	</div>
</div>
<!-- 반응형 본문 끝 -->

<jsp:include page="footer.jsp"/>
<jsp:include page="bottom.jsp"/>
