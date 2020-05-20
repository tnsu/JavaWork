<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="person1" class="com.lec.beans.Person" scope="page" />
<%-- 다음과 같은 일 수행
	Person person1 = new com.lec.beans.Person(); // 기본생성자 호출
	request.setAttribute("person1", person1); //<-- 좀더 정확히는 이코드까지 수행
 --%>
 
	<%-- 기본생성자 생성후 useBean 안에서 setPropery 사용 --%>
	<jsp:useBean id="person2" class="com.lec.beans.Person" scope="page">
		<jsp:setProperty name="person2" property="name" value="명기범"/>
		<jsp:setProperty name="person2" property="age" value="26"/>
		<jsp:setProperty name="person2" property="id" value="998877"/>
		<jsp:setProperty name="person2" property="gender" value="남"/>
	</jsp:useBean>
	<%-- form 에서 넘어온 값을 bean 객체가 바로 받을 수 있다
	bean 의 필드명(getter/setter 명) 과 from 의 name 이 일치해야 한다, --%>
<jsp:useBean id="person3" class="com.lec.beans.Person" scope="page">
		<jsp:setProperty name="person3" property="name"/>
		<jsp:setProperty name="person3" property="age"/>
		<jsp:setProperty name="person3" property="id"/>
		<jsp:setProperty name="person3" property="gender"/>
	</jsp:useBean>
	<%-- person3.getGender(request.getParameter("gender"));  개념적으로 같다.--%>
	
	<%-- parameter  를 일괄로 받아올수 있다, --%>
	<jsp:useBean id="person4" class="com.lec.beans.Person" scope="page"/>
		<jsp:setProperty name="person4" property="*"/>
		
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>자바빈</title>
</head>
<body>
	<jsp:setProperty property="name" name="person1" value="고길동" />
	<jsp:setProperty property="age" name="person1" value="24" />
	<jsp:setProperty name="person1" property="id" value="1515" />
	<jsp:setProperty name="person1" property="gender" value="남" />
	<%-- 다음과 같은 일을 수행한다.
		person.setName("고길동");
		person.setAge(24);
		person.setId(1515);
		person.setGender("남");
	 --%>

	<h3>person1</h3>
	이름: <jsp:getProperty name="person1" property="name" /><br>
	<%--name 에 대소문자 틀리면 500 에러,   --%>
	나이: <jsp:getProperty name="person1" property="age" /><br>
	아이디: <jsp:getProperty name="person1" property="id" /><br>
	성별: <jsp:getProperty name="person1" property="gender" /><br>
	<%-- 다음과 같은 일을 수행한다
		<%= person.getName() %>
		<%= person.getAge() %>
		<%= person.getId() %>
		<%= person.getGender() %>
	 --%>

	<h3>person2</h3>
	이름: <jsp:getProperty name="person2" property="name" /><br>
	나이: <jsp:getProperty name="person2" property="age" /><br>
	아이디: <jsp:getProperty name="person2" property="id" /><br>
	성별: <jsp:getProperty name="person2" property="gender" /><br>
	
	<h3>person3</h3>
	이름: <jsp:getProperty name="person3" property="name" /><br>
	나이: <jsp:getProperty name="person3" property="age" /><br>
	아이디: <jsp:getProperty name="person3" property="id" /><br>
	성별: <jsp:getProperty name="person3" property="gender" /><br>
	
	<h3>person4</h3>
	이름: <jsp:getProperty name="person4" property="name" /><br>
	나이: <jsp:getProperty name="person4" property="age" /><br>
	아이디: <jsp:getProperty name="person4" property="id" /><br>
	성별: <jsp:getProperty name="person4" property="gender" /><br>
	
	
	
	
</body>
</html>