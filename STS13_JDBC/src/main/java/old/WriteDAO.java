package old;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/*
 * DAO : Data Access Object 
 * 		DB 에 접근하여 트렌지션을 수행하는 객체
 *다루는 데이터 소스의 종류에 따라 DAO는 여러개 정의 사용 가능하다/
 */
public class WriteDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; // executeQuery(), SELECT 결과

	// DAO 객체가 생성될때 Connection 객체도 생성된다,
	public WriteDAO() {
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
			System.out.println("WriteDAO 생성, 데이터 베이스 연결");
		} catch (Exception e) {
			e.printStackTrace();
			// throw e;
		} // end try
	}// end 생성자

	// DB 자원 반납 메소드, 만들어놓으면 편함
	public void close() throws SQLException {
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (stmt != null)
			stmt.close();
		if (conn != null)
			conn.close();
	} // end close();

	// 새글작성 <-- DTO
	public int insert(WriteDTO dto) throws SQLException {
		String subject = dto.getSubject();
		String content = dto.getContent();
		String name = dto.getName();

		int cnt = this.insert(subject, content, name);
		return cnt;
	}

	// 새글작성 <-- 제목, 내용, 작성자
	public int insert(String subject, String content, String name) throws SQLException {
		int cnt = 0;

		try {
			pstmt = conn.prepareStatement(D.SQL_INSERT);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setString(3, name);

			cnt = pstmt.executeUpdate();
		} finally {
			close(); // 메소드
		}

		return cnt;
	}

	// list.jsp, ResultSet --> DTO 배열로 리턴
	public WriteDTO[] createArray(ResultSet rs) throws SQLException {
		WriteDTO[] arr = null; // DTO 배열

		ArrayList<WriteDTO> list = new ArrayList<WriteDTO>();

		while (rs.next()) {
			int uid = rs.getInt("wr_uid");
			String subject = rs.getString("wr_subject");
			String content = rs.getString("wr_content");
			String name = rs.getString("wr_name");
			int viewCnt = rs.getInt("wr_viewcnt");
			Date d = rs.getDate("wr_regdate");
			Time t = rs.getTime("wr_regdate");
			String regDate = "";
			if (d != null) {
				regDate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " "
						+ new SimpleDateFormat("hh:mm:ss").format(t);
			}
			WriteDTO dto = new WriteDTO(uid, subject, content, name, viewCnt);
			dto.setRegDate(regDate); // regDate 도 따로 set한다.
			list.add(dto);
		} // end while
		int size = list.size();

		if (size == 0)
			return null;

		arr = new WriteDTO[size];
		list.toArray(arr); // List -> 배열 , 리스트에 저장된 데이터를 배열 객체에 복사

		return arr;
	}// end createArray()

	// 전체 SELECT
	public WriteDTO[] select() throws SQLException {
		WriteDTO arr[] = null;
		try {
			pstmt = conn.prepareStatement(D.SQL_WRITE_SELECT);
			rs = pstmt.executeQuery();
			arr = createArray(rs);
		} finally {
			close();
		} // end try
		return arr;
	} // end select()

	// 특정 uid의 글 내용 읽기, 조회수 증가
	// viewcnt 도 1 증가 해야 하고,글  읽어와야 한다. --> 트랜잭션 처리
	public WriteDTO[] readByUid(int uid) throws SQLException{
		int cnt = 0;
		WriteDTO [] arr = null;
		try{
			// 트랜잭션 처리
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(D.SQL_WRITE_INC_VIEWCNT);
			pstmt.setInt(1, uid);
			cnt = pstmt.executeUpdate();
			
			pstmt.close();
			pstmt = conn.prepareStatement(D.SQL_WRITE_SELECT_BY_UID);
			pstmt.setInt(1, uid);
			rs = pstmt.executeQuery();
			arr = createArray(rs);
			conn.commit();
		}catch(SQLException e) {
			conn.rollback();   // 예외 발생하면 rollback 하고
			throw e;		   // 다시 예외를 throw
		}finally{
			close();
		} // end try	
		return arr;
	}// end readByUid()
	
	// 특정 uid 의 글만 SELECT (조회수 증가 없음)
	public WriteDTO [] selectByUid(int uid) throws SQLException{
		WriteDTO arr[] = null;
		try {
			pstmt = conn.prepareStatement(D.SQL_WRITE_SELECT_BY_UID);
			pstmt.setInt(1, uid);
			rs = pstmt.executeQuery();
			arr = createArray(rs);
		} finally {
			close();
		} // end try
		return arr;
	} // end selectByUid()

	
	
	//특정 uid 의 글 수정 (제목, 내용)
	public int update(int uid, String subject, String content) throws SQLException{
		int cnt = 0;
		try{
			pstmt=conn.prepareStatement(D.SQL_WRITE_UPDATE);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setInt(3, uid);
			cnt = pstmt.executeUpdate();
		}finally{
			close();
		} // end try
		return cnt;
	} // end update()
	
	// 특정 uid 글 삭제
	public int deleteByUid(int uid) throws SQLException{
		int cnt = 0;
		try {
			pstmt = conn.prepareStatement(D.SQL_WRITE_DELETE_BY_UID);
			pstmt.setInt(1, uid);
			cnt = pstmt.executeUpdate();
		} finally {
			close();
		}// end try
			return cnt;
		} // end delete()
		
	
	
	
	
	
}// end WriteDAO
