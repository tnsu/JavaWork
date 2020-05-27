package common;
/*
 * DB 접속 정보, 퀴리문, 테이블명, 컬럼명 등은
 * 별도로 관리하던지
 * XML, 초기화 파라메터 등에서 관리 하는 것이 좋다.
 * 
 */
public class D {
	//Connection 에 필요한 값 세팅
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";   // JDBC 드라이버 클래스
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE"; // DB 접속 URL
	public static final String USERID = "scott0316";
	public static final String USERPW = "tiger0316";
	
	// 퀴리문 준비
	
	// 내용 수정 업데이트
	public static final String SQL_WRITE_UPDATE = "UPDATE test_write SET wr_subject = ?,  wr_content = ?  WHERE wr_uid = ?";
	// list 내림차순으로 정렬
	public static final String SQL_WRITE_SELECT = "SELECT * FROM test_write ORDER BY wr_uid DESC"; 
	// 조회수 증가
	public static final String SQL_WRITE_INC_VIEWCNT = "UPDATE test_write SET wr_viewcnt = wr_viewcnt + 1 WHERE wr_uid = ?";
	// 글 읽어오기
	public static final String SQL_WRITE_SELECT_BY_UID = "SELECT * FROM test_write WHERE wr_uid = ?";
	// 정보 입력
	public static final String SQL_INSERT = "INSERT INTO test_write " + 
			"(wr_uid, wr_subject, wr_content, wr_name) " +
			"VALUES(test_write_seq.nextval, ?, ?, ?)";
	// 아이디로 정보 삭제
	public static final String SQL_WRITE_DELETE_BY_UID =
			"DELETE FROM test_write WHERE wr_uid = ?";
	

	
}