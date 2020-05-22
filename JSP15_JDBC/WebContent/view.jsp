<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.sql.*" %>   <%-- JDBC 관련 클래스 import --%>
<%
	// Parameter 받아오기
	int uid = Integer.parseInt(request.getParameter("uid"));
	// list.jsp 에서 넘어올때 name 값을  uid로 넘겨 주었기 때문에 uid 로 받는다.   href='view.jsp?uid=" + uid

	// ※ 이 단계에서 Parameter 검사 필요
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
	final String SQL_WRITE_INC_VIEWCNT = // 조회수 증가
			"UPDATE test_write SET wr_viewcnt = wr_viewcnt + 1 WHERE wr_uid = ?";
	final String SQL_WRITE_SELECT_BY_UID = // 글 읽어오기
			"SELECT * FROM test_write WHERE wr_uid = ?";

%>
<%
	// db 에서 읽어올 데이터를 담기위한 변수 선언
	String name = "";
  	String subject = "";
  	String content = "";
  	String regdate = "";
  	int viewcnt = 0;

%>
<%
	try{
		Class.forName(driver);
		out.println("드라이버 로딩 성공" + "<br>");
		conn = DriverManager.getConnection(url, userid, userpw); // 시간이 오래걸림
		out.println("conn 성공" + "<br>");
		
		//트렌잭션 실행
		// Auto-commit 을 비활성화 (false) 
		conn.setAutoCommit(false);
		
		// 쿼리들 수행
		pstmt = conn.prepareStatement(SQL_WRITE_INC_VIEWCNT);
		pstmt.setInt(1, uid);
		cnt = pstmt.executeUpdate();
		
		pstmt.close();
		
		pstmt = conn.prepareStatement(SQL_WRITE_SELECT_BY_UID);
		pstmt.setInt(1, uid);
		rs = pstmt.executeQuery(); 
		
		// 한개의 레코드만 select 된다. 따라서 while 필요없음
		if(rs.next()){ // 레코드 존재 시 
			subject = rs.getString("wr_subject");
			content = rs.getString("wr_content");
			if(content == null) content = ""; // null일 경우 처리
			name = rs.getString("wr_name");
			viewcnt = rs.getInt("wr_viewcnt");
			Date d = rs.getDate("wr_regdate"); 
			Time t = rs.getTime("wr_regdate");
			regdate = "";
			if(d != null) {
				regdate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " " 
						+ new SimpleDateFormat("hh:mm:ss").format(t);
			}
		}else{
			// wr_uid 값에 레코드가 없다 
%>
			<script>
			alert("해당 정보가 삭제되거나 없습니다");
			history.back();
			</script>
<% 
			return;  // 더이상 JSP 프로세싱 하지 않고 종료
		}
		
		// 모튼 쿼리 성공하면 commit
		conn.commit();

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

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>글 읽기 <%= subject %></title> <!-- 제목에 내용을 넣을수도 있다 -->
</head>
<script>
function chkDelete(uid){
	// 삭제 여부, 다시 확인 하고 진행하기
	var r = confirm("삭제하시겠습니까?");
	
	if(r){
		location.href = 'deleteOk.jsp?uid=' + uid;
	}
} // end chkDelete()
</script>
<body>
<h2>읽기 <%= subject %></h2>
<br>
uid : <%= uid %><br>
작성자 : <%= name %><br>
제목: <%= subject %><br>
등록일: <%= regdate %><br>
조회수: <%= viewcnt %><br>
내용: <br>
<hr>
<div>
<%= content %>
</div>
<hr>
<br>
<button onclick="location.href='update.jsp?uid=<%= uid %>'">수정하기</button>
<button onclick="location.href='list.jsp'">목록보기</button>
<button onclick="chkDelete(<%= uid %>)">삭제하기</button>
<button onclick="location.href='write.jsp'">새 글 등록</button>

</body>
</html>










