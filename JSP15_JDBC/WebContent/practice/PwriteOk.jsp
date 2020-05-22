<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ page import = "java.sql.*" %>
<%@page import="java.text.SimpleDateFormat"%>
<%
	request.setCharacterEncoding("utf-8");
	
 //입력한 값 밭아오기
	String name = request.getParameter("name");
 	String title = request.getParameter("title");
 	String content = request.getParameter("content");
 	String  d =request.getParameter("date");
 	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date =transFormat.parse(d);

%>
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
	final String SQL_INSERT =  "INSERT INTO pract_borad (pr_uid, pr_subject, pr_content, pr_name, pr_regdate) " +
			"VALUES(peact_borad_SEQ.nextval, ?, ?, ?, ?)";

%>

<%
	try{
		Class.forName(DRIVER);
		out.println("드라이버 로딩 성공" + "<br>");
		con = DriverManager.getConnection(URL, USERID, USERPW);
		out.println("conn 성공" + "<br>");
		
		//트렌잭션 실행
		pstmt = con.prepareStatement(SQL_INSERT);

		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, name);
		pstmt.setDate(4, new java.sql.Date(date.getTime()));  // 강사ㄴ;ㅁ친스
		
		cnt = pstmt.executeUpdate();
		
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
<% if(cnt == 0){ %>
	<script>
		alert("등록 실패");
		history.back(); // 브라우저가 직전에 기억하는 페이지로 (입력 페이지)
	</script>
<% } else { %>
	<script>
		alert("등록 성공.  리스트 출력합니다");
		location.href = "Plist.jsp";
	</script>			
<% } %>
