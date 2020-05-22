<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="com.lec.beans.WriteDAO"/>   <!-- DAO bean 생성 -->
<!--  DAO 만 사용해서 한번 DATO+ 사영해서 한번 해주심 -->
<%
	request.setCharacterEncoding("utf-8");  // 한글 인코딩 받아올때 꼭!
%>
<jsp:useBean id="dto" class="com.lec.beans.WriteDTO"/>
<jsp:setProperty property="*" name="dto"/> <!--  value 가 없으면 form 에서 넘어온 파라메터 받음(name이랑 같은)  -->

<%
	// DAO 사용한 트랜잭션
	// int cnt = dao.insert(subject, content, name);
	int cnt = dao.insert(dto);
%>
<% if(cnt == 0){ %>
	<script>
		alert("등록 실패");
		history.back(); // 브라우저가 직전에 기억하는 페이지로 (입력 페이지)
	</script>
<% } else { %>
	<script>
		alert("등록 성공.  리스트 출력합니다");
		location.href = "list.jsp";
	</script>			
<% } %>















