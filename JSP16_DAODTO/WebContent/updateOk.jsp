<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="com.lec.beans.WriteDAO"/> <%-- DAO bean 생성 --%>
<%
	//parameter 받아오기
	request.setCharacterEncoding("utf-8");  // 한글 인코딩 받아올때 꼭!
	int uid = Integer.parseInt(request.getParameter("uid"));
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");

%>
<%  //dao 사용한 트랜잭션 (메서드 호출하면 close()됨
	int cnt = dao.update(uid, subject, content);
%>

<% if(cnt == 0){ %>
	<script>
		alert("수정 실패");
		history.back(); // 브라우저가 직전에 기억하는 페이지로 (입력 페이지)
	</script>
<% } else { %>
	<script>
		alert("수정 성공. ");
		location.href = "view.jsp?uid=<%= uid %>"; 
		<%-- 수정 성공하면 view 로 이동하여 제대로 수정되었는지 보여주는게 좋다--%>
	</script>			
<% } %>
