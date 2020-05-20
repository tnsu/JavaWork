<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="cnt" class="com.lec.beans.CountBean" scope="page"/> <%-- set 없이 받는 결과 값이 나오는가  안나온다. --%>
<b>cnt 의 getCount 호출</b><br>

<jsp:getProperty name="cnt" property="count"/><br>


<a href="scope1_page1.jsp">page1로..</a>
