package com.lec.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.D;

public class CategoryDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	// DAO 객체가 생성될때 Connection 도 생성된다
	public CategoryDAO(){
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
			System.out.println("CategoryDAO생성, 데이터베이스 연결!!");
		} catch (Exception e) {			
			e.printStackTrace();
		} // end try
	}// 생성자
	
	// DB 자원 반납 메소드, 만들어놓으면 편함..
	public void close() throws SQLException{
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(stmt != null) stmt.close();
		if(conn != null) conn.close();
	} // end close();
	
	// ResultSet --> DTO배열로 리턴
	public CategoryDTO[] createArray(ResultSet rs) throws SQLException{
		ArrayList<CategoryDTO> list = new ArrayList<CategoryDTO>();
		
		while(rs.next()){
			int uid = rs.getInt("uid");
			String name = rs.getString("name");
			int depth = rs.getInt("depth");
			int parent = rs.getInt("parent"); 
			int order = rs.getInt("order"); 
			
			CategoryDTO dto = new CategoryDTO(uid, name, depth, parent, order);
			list.add(dto);
		}//end while		
		int size = list.size();
		
		if(size == 0) return null;
		
		CategoryDTO arr[] = new CategoryDTO[size];
		list.toArray(arr); //리스트에 저장된 데이터를 배열 객체에 복사
		return arr;
	} // end createArray()

	
	// 전체 SELECT
		public CategoryDTO[] select() throws SQLException{
			CategoryDTO arr[] = null;
			try{
				pstmt = conn.prepareStatement(D.SQL_CATEGORY_ALL);
				rs = pstmt.executeQuery();
				arr = createArray(rs);
			}finally{
				close();
			} // end try
			return arr;
		} // end select()
	
		// 특정 depth
		public CategoryDTO[] selectByDepth(int depth) throws SQLException{
			CategoryDTO arr[] = null;
			try{
				pstmt = conn.prepareStatement(D.SQL_CATEGORY_BY_DEPTH);
				pstmt.setInt(1, depth);
				rs = pstmt.executeQuery();
				arr = createArray(rs);
			}finally{
				close();
			} // end try
			return arr;
		} // end select()
		
		// 특정 depth + 특정 parent
		public CategoryDTO[] selectByDepthParent(int depth, int parent) throws SQLException{
			CategoryDTO arr[] = null;
			try{
				pstmt = conn.prepareStatement(D.SQL_CATEGORY_BY_DEPTH_N_PARENT);
				pstmt.setInt(1, depth);
				pstmt.setInt(2, parent);
				rs = pstmt.executeQuery();
				arr = createArray(rs);
			}finally{
				close();
			} // end try
			return arr;
		} // end select()
		
} // end DAO













