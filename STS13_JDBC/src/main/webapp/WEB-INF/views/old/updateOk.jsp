<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%  //dao 사용한 트랜잭션 (메서드 호출하면 close()됨
	int cnt = (Integer)request.getAttribute("updateOk");
	int uid = (Integer)request.getAttribute("upUid");
%>

<% if(cnt == 0){ %>
	<script>
		alert("수정 실패");
		history.back(); // 브라우저가 직전에 기억하는 페이지로 (입력 페이지)
	</script>
<% } else { %>
	<script>
		alert("수정 성공. ");
		location.href = "view.do?uid=<%= uid %>"; 
		<%-- 수정 성공하면 view 로 이동하여 제대로 수정되었는지 보여주는게 좋다--%>
	</script>			
<% } %>
