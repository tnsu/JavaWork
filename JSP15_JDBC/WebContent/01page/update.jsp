<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.text.SimpleDateFormat" %>
    <%@ page import = "java.sql.*" %>   <%-- JDBC 관련 클래스 import --%>
    <%
	// Parameter 받아오기
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
		Class.forName(DRIVER);
		out.println("드라이버 로딩 성공" + "<br>");
		conn = DriverManager.getConnection(URL, USERID, USERPW);
		out.println("conn 성공" + "<br>");
		
		//트렌잭션 실행
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
<title>수정 <%= subject %></title>
</head>
<script>
function chkSubmit() {
	frm = document.forms['frm'];
	var subject = frm['subject'].value.trim();
	
	if(subject == ""){
		alert("제목은 반드시 작성해야 합니다.");
		frm['subject'].focus();
		return false;
	}
	return true;
}
</script>
<body>
<h2> <%= subject %> 글 수정</h2>
<form name="frm" action="updateOk.jsp" method="post" onsubmit="return chkSubmit()">
<input type="hidden" name="uid" value="<%= uid %>"/>
작성자 : <%= name %><br>
제목 : 
<input type="text" name="subject" value="<%= subject %>"/><br>
내용: <br>
<textarea name="content"><%= content %></textarea>
<br>
<input type="submit" value="수정"/>
</form>
<button onclick="history.back()">이전으로</button>
<button onclick="location.href='list.jsp'">목록보기</button>

</body>
</html>