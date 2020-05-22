<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<% 
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int cnt = 0;
	
	//Connection 에 필요한 값 세팅
	final String DRIVER = "oracle.jdbc.driver.OracleDriver";   // JDBC 드라이버 클래스
	final String URL = "jdbc:oracle:thin:@localhost:1521:XE"; // DB 접속 URL
	final String USERID = "scott0316";
	final String USERPW = "tiger0316";
%>
<%! 
	// 퀴리문 준비
	

%>
<%
	try{
		Class.forName(DRIVER);
		out.println("드라이버 로딩 성공" + "<br>");
		con = DriverManager.getConnection(URL, USERID, USERPW);
		out.println("conn 성공" + "<br>");
		
		//트렌잭션 실행
	}catch(Exception e){
		e.printStackTrace();
		//예외 처리
	}finally{
		//리소스 해제
		try{
		if( rs != null) rs.close();
		if( stmt != null) stmt.close();
		if( pstmt != null) pstmt.close();
		if( con != null) con.close();
		}catch(Exception e){
			e.printStackTrace();
			//예외 처리
		}
	}
	
%>
<%-- 위에서 필요한 트랜잭션이 마무리 되면 페이지에 보여주기 --%>