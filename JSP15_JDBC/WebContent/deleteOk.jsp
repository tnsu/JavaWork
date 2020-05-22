<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.sql.*" %>   <%-- JDBC 관련 클래스 import --%>
   <%
   	// 파라미터 받기
   	int uid = Integer.parseInt(request.getParameter("uid"));
   %>
<%!
	//JDBC 관연 기본 객체 변수들 선언
	Connection conn= null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;  // executeQuery(), SELECT 결과 
	int cnt = 0; // executeUpdate(), DML 결과

	//Connection 에 필요한 값 세팅
	final String DRIVER = "oracle.jdbc.driver.OracleDriver";   // JDBC 드라이버 클래스
	final String URL = "jdbc:oracle:thin:@localhost:1521:XE"; // DB 접속 URL
	final String USERID = "scott0316";
	final String USERPW = "tiger0316";
%>
<%! 
	// 퀴리문 준비
	final String SQL_WRITE_DELETE_BY_UID =
						"DELETE FROM test_write WHERE wr_uid = ?";

%>
<%
	try{
		Class.forName(DRIVER);
		out.println("드라이버 로딩 성공" + "<br>");
		conn = DriverManager.getConnection(URL, USERID, USERPW);
		out.println("conn 성공" + "<br>");
		
		//트렌잭션 실행
		pstmt = conn.prepareStatement(SQL_WRITE_DELETE_BY_UID);
		pstmt.setInt(1, uid);
		
		cnt=pstmt.executeUpdate();
		
	}catch(Exception e){
		e.printStackTrace();
		//예외 처리
	}finally{
		//리소스 해제
		try{
		if( rs != null) rs.close();
		if( stmt != null) stmt.close();
		if( pstmt != null) pstmt.close();
		if( conn != null) conn.close();
		}catch(Exception e){
			e.printStackTrace();
			//예외 처리
		}
	}
	
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
