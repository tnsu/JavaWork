<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.text.SimpleDateFormat" %>
    <%@ page import = "java.sql.*" %>   <%-- JDBC 관련 클래스 import --%>
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
	final String SQL_WRITE_SELECT =
		"SELECT * FROM test_write ORDER BY wr_uid DESC";
//DESC  내림 차순 10~1  , ASC 오름차순

%>


<%-- 위에서 필요한 트랜잭션이 마무리 되면 페이지에 보여주기 --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>글 목록</title>
<style>
table { width: 100%;}
table, th, td {
	border: 1px solid tomato;
	border-collapse: collapse;
	text-align: center;
}
a{
	text-decoration: none;
	color: black;
}
a:hover{
	color: pink;
}
</style>

</head>
<body>
<%-- rs로 테이블을 만들기 위해서 --%>
<%
	try{
		Class.forName(driver);
		out.println("드라이버 로딩 성공" + "<br>");
		conn = DriverManager.getConnection(url, userid, userpw);
		out.println("conn 성공" + "<br>");
		
		//트렌잭션 실행
		pstmt = conn.prepareStatement(SQL_WRITE_SELECT);
		
		rs = pstmt.executeQuery();
		out.println("쿼리 성공");
		%>
	<hr>
	<h2>리스트</h2>
	<table>
		<tr>
			<th>UID</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>등록일</th>
		</tr>
<%
		while(rs.next()){
			out.println("<tr>");
			int uid = rs.getInt("wr_uid");
			String subject = rs.getString("wr_subject");
			String name = rs.getString("wr_name");
			int viewcnt = rs.getInt("wr_viewcnt");
			Date d = rs.getDate("wr_regdate"); 
			Time t = rs.getTime("wr_regdate");
			String regdate = "";
			if(d != null) {
				regdate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " " 
						+ new SimpleDateFormat("hh:mm:ss").format(t);
			}
			out.println("<td>" + uid + "</td>");
			// 글을 클릭하면 넘어가도록 하기위해서 설정 (어느것을 보여줘야하는지 알지 위해서 ?uid를 해줘애함)
			out.println("<td><a href='view.jsp?uid=" + uid + " '>" + subject + "</td>");
			out.println("<td>" + name + "</td>");
			out.println("<td>" + viewcnt + "</td>");
			out.println("<td>" + regdate +"</td>");
			
			out.println("</tr>");
		}//end while


%>
	</table>
	<button onclick="location.href='write.jsp'">새글  등록</button>
		<% 
	}catch(Exception e){
		e.printStackTrace();
		//예외 처리
		// ※ 여기서 예외처리를 하든지, 예외 페이지를 설정해주어야 한다. 
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
</body>
</html>





