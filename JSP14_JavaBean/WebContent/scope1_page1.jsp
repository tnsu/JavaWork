<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cnt" class="com.lec.beans.CountBean" scope="page"/>
<jsp:setProperty name="cnt" property="count"/>
<%-- cnt.setCount( Integer.parselnt(request.getParmeter("conut"))) --%>

<h3>page1<br>cnt의 getCount 호출</h3>

<jsp:getProperty name="cnt" property="count"/>
<%-- 아래와 동일
<%= cnt.getCount() %>
 --%>
<a href="scope1_page2.jsp">page2로..</a>