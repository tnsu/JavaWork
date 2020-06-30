package com.lec.sts13_jdbc.board.beans;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.lec.sts13_jdbc.board.C;

public class BWriteDAO {
	JdbcTemplate template;
	
	public BWriteDAO() {
		this.template = C.template;
	}

	// 전체 SELECT
	public List<BWriteDTO> select(){
	
		return (List<BWriteDTO>)template.query(C.SQL_WRITE_SELECT, new BeanPropertyRowMapper<BWriteDTO>(BWriteDTO.class));
		//  게터세터와 일치한것을 정확히 레코드 하나하나마다 매핑을 해서 
	}
	
	public int insert(final BWriteDTO dto) {
		/*
		return template.update(C.SQL_WRITE_INSERT, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, dto.getSubject());
				ps.setString(2, dto.getContent());
				ps.setString(3, dto.getName());
				
			}
		});
		// 1. PreparedStatementSetter 방법
*/
		// 2. update() + PreparedStatementCreator() 사용
				return
				this.template.update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection con) 
							throws SQLException {
						PreparedStatement ps = con.prepareStatement(C.SQL_WRITE_INSERT);
						ps.setString(1, dto.getSubject());
						ps.setString(2, dto.getContent());
						ps.setString(3, dto.getName());
						return ps;
					}
				});
	}
	
	// 글 읽기
	public BWriteDTO readByUid(final int uid){
		BWriteDTO dto = null;
		
		
		
		return dto;
	
	}
}





