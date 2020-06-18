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
	// 특정 depth 의 특정 parent 인 카테고리 들 읽어오기
	public static final String SQL_CATEGORY_BY_DEPTH_N_PARENT = "SELECT ca_uid , ca_name , ca_depth , ca_parent , ca_order FROM TEST_CATEGORY WHERE CA_DEPTH =? AND CA_PARENT =?"
		+	" ORDER BY CA_ORDER ASC";
	
	public static final String SQL_CATE_PARANT_N_PARENT0 = "SELECT  ca_uid, ca_name, ca_depth ,ca_parent ,ca_order  FROM TEST_CATEGORY WHERE CA_DEPTH =1";
	
}