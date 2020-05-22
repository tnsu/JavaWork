<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.text.SimpleDateFormat" %>
    <%@ page import = "java.sql.*" %>   <%-- JDBC 관련 클래스 import --%>
 <!-- 페이징 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="CSS/common.css"/>
<script src="https://kit.fontawesome.com/bb29575d31.js"></script>
 <%  // 게시글 페이징 처리를 위한 
 		
 	int curPage = 1;  // 현재 페이지 (디폴트는 1 page)
	
 	// 현재 몇 페이지 인지 parameter 받아오기
	String pageParam = request.getParameter("page");
	if(pageParam != null && !pageParam.trim().equals("")){ // 페이지 검증  페이지가 null 
		try{
			// 반드시 정수로 형변환 해야한다.
			int p = Integer.parseInt(pageParam);
			if(p > 0){
				curPage = p;
			}
		} catch (NumberFormatException e){
			// page parameter 오류는 별도의 exception 처리는 안함
		}
	} // end if

    
    
 %>
<%!
	//JDBC 관연 기본 객체 변수들 선언
	Connection conn= null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;  // executeQuery(), SELECT 결과 
	int cnt = 0; // 글 목록 전체의 개수 executeUpdate(), DML 결과

	//Connection 에 필요한 값 세팅
	String driver = "oracle.jdbc.driver.OracleDriver";   // JDBC 드라이버 클래스
	String url = "jdbc:oracle:thin:@localhost:1521:XE"; // DB 접속 URL
	String userid = "scott0316";
	String userpw = "tiger0316";
%>
<%! 
	// 퀴리문 준비
	// [페이징] 구현시는 아래 쿼리는 필요없다.
	// final String SQL_WRITE_SELECT = "SELECT * FROM test_write ORDER BY wr_uid DESC";  //DESC  내림 차순 10~1  , ASC 오름차순
	
	// 페이징
	// 글 목록 전체 개수 가져오기
	final String SQL_WRITE_CONUT_ALL = "SELECT count(*) FROM test_write";
	
	// 쿼리: fromRow 부터 pageRows 만큼 SELECT
	// (몇번째) 부터 (몇개)만큼
	final String SQL_WRITE_SELECT_FROM_ROW = "SELECT * FROM " +
			"(SELECT ROWNUM AS RNUM, T.* FROM (SELECT * FROM test_write ORDER BY wr_uid DESC) T) " + 
			"WHERE RNUM >= ? AND RNUM < ?";
	
	// int cnt = 0;   // 글 목록 전체의 개수, 위에서 선언해줌

	
	// 페이징 관련 세팅 값들
	int writePages = 10;    // 한 [페이징] 에 몇개의 '페이지'를 표현할 것인가?
	int pageRows = 8;   // 한 '페이지'에 몇개의 글을 리스트 할것인가? 
	int totalPage = 0; //총 몇 '페이지' 분량인가? 

%>
<%-- rs로 테이블을 만들기 위해서 --%>
<%
	try{
		Class.forName(driver);
		out.println("드라이버 로딩 성공" + "<br>");
		conn = DriverManager.getConnection(url, userid, userpw);
		out.println("conn 성공" + "<br>");
		
		//트렌잭션 실행
		pstmt = conn.prepareStatement(SQL_WRITE_CONUT_ALL); // 갯수를 읽어야한다
		rs = pstmt.executeQuery();
		
		if(rs.next()) 
			cnt = rs.getInt(1);  // conut(*) 값 , 글 전체 개수 구하기
			
		rs.close();
		pstmt.close();
		// 쿼리 두번을 위해서 close() 해준다
		
		totalPage = (int)Math.ceil(cnt / (double)pageRows); //총 몇 페이지 분량 반올림
				
		int fromRow = (curPage - 1) * pageRows + 1;	// 몇번째 row 부터?
		
		pstmt = conn.prepareStatement(SQL_WRITE_SELECT_FROM_ROW);
		pstmt.setInt(1, fromRow);    // 몇번째 row 부터
		pstmt.setInt(2, fromRow + pageRows);  // 몇번째 row 전까지?
		rs = pstmt.executeQuery();	

%>

<%-- 위에서 필요한 트랜잭션이 마무리 되면 페이지에 보여주기 --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>글 목록 <%= curPage %> page</title>
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


	<hr>
	<h2>리스트  <%= curPage %>page</h2>
	<h4><%= cnt %>개</h4> <!-- 전체 글 개수 -->
	<table>
		<tr>
			<th>row</th> <!-- 행번호 -->
			<th>글 번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>등록일</th>
		</tr>
<%
		while(rs.next()){
			out.println("<tr>");
			//row는 있어도 되고 없어도된다.
			int rnum = rs.getInt("rnum");   // rownum 을 받아온다.
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
			out.println("<td>" + rnum + "</td>");  //  rownum 을 찍어 주자
			out.println("<td>" + uid + "</td>");
			// 글을 클릭하면 넘어가도록 하기위해서 설정 (어느것을 보여줘야하는지 알지 위해서 ?uid를 해줘애함)
			out.println("<td><a href='view.jsp?uid=" + uid + "&page="+curPage + " '>" + subject + "</td>");
			out.println("<td>" + name + "</td>");
			out.println("<td>" + viewcnt + "</td>");
			out.println("<td>" + regdate +"</td>");
			
			out.println("</tr>");
		}//end while


%>
	</table>
	<%--페이징 --%>
<jsp:include page="pagination.jsp">
	<jsp:param value="<%= writePages %>" name="writePages"/>
	<jsp:param value="<%= totalPage %>" name="totalPage"/>
	<jsp:param value="<%= curPage %>" name="curPage"/>
</jsp:include>
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





