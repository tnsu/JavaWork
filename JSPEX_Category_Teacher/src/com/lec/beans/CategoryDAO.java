package com.lec.beans;

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

import common.D;

public class CategoryDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	/**
	 * 
	 */
	public CategoryDAO() {
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		
		// list.jsp, ResultSet --> DTO 배열로 리턴
		public CategoryDTO[] createArray(ResultSet rs) throws SQLException {
			CategoryDTO[] arr = null; // DTO 배열

			ArrayList<CategoryDTO> list = new ArrayList<CategoryDTO>();

			while (rs.next()) {
				int uid = rs.getInt("ca_uid");
				String name = rs.getString("ca_name");
				int depth = rs.getInt("ca_depth");
				int parent = rs.getInt("ca_parent");
				int order = rs.getInt("ca_order");
				CategoryDTO dto = new CategoryDTO(uid, name, depth, parent, order);
				list.add(dto);
			} // end while
			int size = list.size();

			if (size == 0)
				return null;

			arr = new CategoryDTO[size];
			list.toArray(arr); // List -> 배열 , 리스트에 저장된 데이터를 배열 객체에 복사

			return arr;
		}// end createArray()
		
		// parant 가 없는 것 가져오기
		public CategoryDTO[] selectDepth1() throws SQLException{
			CategoryDTO[] arr = null;
			try{
			pstmt = conn.prepareStatement(D.SQL_CATE_PARANT_N_PARENT0);
			rs = pstmt.executeQuery();
			arr = createArray(rs);
		
		}finally {
			close();
			} // end try
			return arr;
		}
		
		public  CategoryDTO[] selectDepthN(int depth, int parent) throws SQLException{
			CategoryDTO[] arr = null;
			try{
			pstmt = conn.prepareStatement(D.SQL_CATEGORY_BY_DEPTH_N_PARENT);
			pstmt.setInt(1, depth);
			pstmt.setInt(2, parent);
			rs = pstmt.executeQuery();
			arr = createArray(rs);
		
		}finally {
			close();
			} // end try
			return arr;
		}
	
}
