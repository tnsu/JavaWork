<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="com.lec.beans.WriteDAO"/>   <!-- DAO bean 생성 -->
   <%
   	// 파라미터 받기
   	int uid = Integer.parseInt(request.getParameter("uid"));
   %>
<%
	// dao 트랜젝션
	int cnt = dao.deleteByUid(uid);
%>
<%-- 위에서 필요한 트랜잭션이 마무리 되면 페이지에 보여주기 --%>
<% if(cnt == 0){ %>
<script>
	alert("삭제 실패");
	hisotry.back();
</script>
<% } else { %>
<script>
	alert("삭제 성공");
	location.href = "list.jsp"; <%-- 삭제후에는 list 로 가자 --%>
</script>
<% } %>  
