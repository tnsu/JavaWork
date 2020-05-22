<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ page import = "java.sql.*" %>   <%-- JDBC 관련 클래스 import --%>
      <%
      	request.setCharacterEncoding("utf-8");  // 한글 인코딩 받아올때 꼭!
      	
      	//입력한 값 받아오기
      	String name = request.getParameter("name");
      	String subject = request.getParameter("subject");
      	String content = request.getParameter("content");
      	
      	// 유효성 체크 (name ,subject NN)
      	// null 이거나, 빈문자열이면 이전화면으로 돌아가기
      	if(name == null || subject == null || name.trim().equals("") || subject.trim().equals("")){      		
%>
      <script>
      		alert("작성자 이름, 글제목은 필수 입니다. ");
			history.back(); //↔ histtory.go(-1);
      </script>
<% 
		return;    // ★더이상 JSP 프로세싱 하지 않도록 여기서 종료★
      	}// end if
%>
<%!
	//JDBC 관연 기본 객체 변수들 선언
	Connection conn= null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;  // executeQuery(), SELECT 결과 
	int cnt = 0; // executeUpdate(), DML 결과

	//Connection 에 필요한 값 세팅
	String driver = "oracle.jdbc.driver.OracleDriver";   // JDBC 드라이버 클래스
	String url = "jdbc:oracle:thin:@localhost:1521:XE"; // DB 접속 URL
	String userid = "scott0316";
	String userpw = "tiger0316";
%>
<%! 
	// 퀴리문 준비
	String sql_insert = "INSERT INTO test_write " + 
		"(wr_uid, wr_subject, wr_content, wr_name) " +
		"VALUES(test_write_seq.nextval, ?, ?, ?)";

%>
<%
	try{
		Class.forName(driver);
		out.println("드라이버 로딩 성공" + "<br>");
		conn = DriverManager.getConnection(url, userid, userpw);
		out.println("conn 성공" + "<br>");
		
		//트렌잭션 실행
		pstmt = conn.prepareStatement(sql_insert);
		
		pstmt.setString(1, subject);
		pstmt.setString(2, content);
		pstmt.setString(3, name);
		
		cnt = pstmt.executeUpdate();  // 정상적으로 동작하면 1이됨다,
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
<%-- 위 트랜잭션이 마무리 되면 페이지 보여주기 --%>
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















