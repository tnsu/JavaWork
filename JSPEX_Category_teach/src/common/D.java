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
	
	// 특정 depth 의 특정 parent 인 카테고리들 읽어오기
		public static final String SQL_CATEGORY_ALL = 
				"SELECT ca_uid \"uid\", ca_name name, ca_depth depth, ca_parent parent, ca_order AS \"order\" FROM test_category ";

		// 특정 depth 카테고리 읽기
		public static final String SQL_CATEGORY_BY_DEPTH = SQL_CATEGORY_ALL +
				" WHERE ca_depth = ? ORDER BY ca_order ASC";
		
		public static final String SQL_CATEGORY_BY_DEPTH_N_PARENT = SQL_CATEGORY_ALL +
				" WHERE ca_depth = ? AND ca_parent = ? ORDER BY ca_order ASC";
		
		// TODO : 필요한 쿼리문 만들어 보기

	}
