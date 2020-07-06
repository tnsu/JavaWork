package com.lec.sts16_interceptor.board;

import org.springframework.jdbc.core.JdbcTemplate;

public class C {
	// 스프링 컨테이너에 생성된 JdbcTemplate 을 받아와서
		// 언제든지, 어느곳에서든지 원할때 가져다 쓸수 있도록 public static 설정
		public static JdbcTemplate template;
		
		// 게시글 관련 쿼리문
		// 내용 수정 업데이트
		public static final String SQL_WRITE_UPDATE = "UPDATE test_write SET wr_subject = ?,  wr_content = ?  WHERE wr_uid = ?";
		// list 내림차순으로 정렬
		public static final String SQL_WRITE_SELECT = 	"SELECT wr_uid \"uid\", wr_subject subject, "
				+ "wr_content content, wr_name name, wr_viewcnt viewcnt, "
				+ "wr_regdate regdate FROM test_write ORDER BY wr_uid DESC";

		// 조회수 증가
		public static final String SQL_WRITE_INC_VIEWCNT = "UPDATE test_write SET wr_viewcnt = wr_viewcnt + 1 WHERE wr_uid = ?";
		// 글 읽어오기
		public static final String SQL_WRITE_SELECT_BY_UID = "SELECT wr_uid  \"uid\", wr_subject subject, "
				+ "wr_content content, wr_name name, wr_viewcnt viewcnt, "
				+ "wr_regdate regdate FROM test_write WHERE wr_uid=?";
		// 정보 입력
		public static final String SQL_WRITE_INSERT = "INSERT INTO test_write " + 
				"(wr_uid, wr_subject, wr_content, wr_name) " +
				"VALUES(test_write_seq.nextval, ?, ?, ?)";
		// 아이디로 정보 삭제
		public static final String SQL_WRITE_DELETE_BY_UID =
				"DELETE FROM test_write WHERE wr_uid = ?";
		
}
