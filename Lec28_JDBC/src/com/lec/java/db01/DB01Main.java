package com.lec.java.db01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;

/*
JDBC (Java DataBase Connectivity) 사용
 0. 라이브러리(jar) 추가:
  1) 이클립스 프로젝트 폴더에 libs 폴더를 생성
  2) C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib\ojdbc6_g.jar
 파일을 libs 폴더로 복사
  3) 복사한 라이브러리를 빌드패스에 추가   
		BulidPath - Configure Build Path..
		Libraries 탭에서  [Add JARs..]   ->  위 libs 폴더의 ojdbc6_g.jar 파일 추가
		Order and Export 탭에서  우선순위 Up (권장)

 1. Oracle 연동을 위한 정보들(상수들)을 정의(세팅)
 2. JDBC 드라이버 클래스를 메모리에 로드
 3. DB와 connection(연결)을 맺음
 4. Statement 인스턴스를 생성 (SQL을 사용하기 위한 인스턴스)
 5. SQL 문장 작성(SELECT, INSERT, UPDATE, DELETE)
 6. SQL 문장을 DB 서버로 전송
 7. 실행 결과 확인
*/

// er-diagram 만들기
// http://ermaster.sourceforge.net/update-site


public class DB01Main {

	// 1. Oracle 연동을 위한 정보들(상수들)을 정의(세팅)
	//JDBC 드라이버 클래스 정보 
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	// DB 서버 접속 주소 (url) 정보
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE"; // localhost : 내 컴퓨터의 주소
	// @ 호스트의 이름을 써주면 됨
	//SQLRecoverableException: IO 오류: The Network Adapter could not establish the connection	
	//포트번호의 오류거나 오라클 프로그램이 실행되고 있지 않아서 에러가 난다
	// connection은 자원이기 때문에 한번 끄면 소멸됨 그래서 다시 실행할때는 다시 잡이줘야 동작됨
	
	// DB접속 사용자 계정 정보
	public static final String USER = "scott0316";
	public static final String PASSWD = "tiger0316";
	
	public static void main(String[] args) {
		System.out.println("DB 1 : JDBC 프로그래밍");
		
		Connection conn = null; // java.sql.Connection
		Statement stmt = null; // java.sql.*;
		ResultSet rs = null; // java.sql
		
		try {
			
			// 2. JDBC 드라이버 클래스를 메모리에 로드
			Class.forName(DRIVER); // 동적 클래스 로딩 , 프로그램 실행중에  실행이 되게 하는 메소드
			System.out.println("드라이버 클래스 로딩 성공");
			
			// 3. DB와 connection(연결)을 맺음
			conn = DriverManager.getConnection(URL,USER, PASSWD);
			System.out.println("DB Conncet 연결");
			
			// 4. Statement 인스턴스를 생성 (SQL을 사용하기 위한 인스턴스)
			stmt = conn.createStatement();  // conn 으로부터 stmt가 만들어짐
			System.out.println("Statement 생성 성공");
			
			// 5. SQL 문장 작성(SELECT, INSERT, UPDATE, DELETE)
			/*
			System.out.println();
			String sql_insert = "INSERT INTO test_member VALUES(100,'마징가', SYSDATE)";
			
			int no1 = 200;
			String name1 = "뽀로로";
			sql_insert = "INSERT INTO test_member VALUES(" + no1 + ",'" + name1 + "',SYSDATE)";
			
			System.out.println(sql_insert);
			int cnt = stmt.executeUpdate(sql_insert); // 'DML' 명령은 executeUpdate() 로 실행, 리턴값 : 정수(int)
			System.out.println(cnt + "개의 row(행)이 INSERT됨");
			*/
			System.out.println();
			String sql_select = "SELECT mb_no, mb_name, mb_birthdate FROM test_member"; //mb_no mm 하면 getString("mm") 해주기
			System.out.println(sql_select); 
			
			rs = stmt.executeQuery(sql_select); // 'SELECT' 및 기타 쿼리의 경우 executeQuery() 로 실행 , 리턴값 : ResultSer 객체
			
			System.out.println();
			//  7. ResultSet 에 담겨있는  record 확인
			//  7-1. 컬럼이름 받기
			/*
			while(rs.next()) { // record 하나 추출하고 true 리턴, 더이상 뽑아낼 레코드 없으면 false 리턴
				String no = rs.getString("mb_no"); // getXXX()에 컬럼명 or 별명을 명시
				String name = rs.getString("mb_name");
				String birthdate = rs.getString("mb_birthdate"); // 문자열로 변환해서 받은것
				String result = no + "\t | " + name  +"\t | " + birthdate ;
				System.out.println(result);
				// 컬럼/별명이름 틀리면
				// java.sql.SQLException: 부적합한 열 이름 
			}// end while
			*/
			System.out.println();
			// 7-2. 컬럼 인덱스 받기
			/*
			while(rs.next()) {  // null 값도 나옴 getString 자체가 null 을 리턴
				String no = rs.getString(1);  // getXXX()에 인덱스 명시(1부터 시작) , SELECT에 썻던 순서대로 출력됨 
				String name = rs.getString(2); 
				String birthdate = rs.getString(3); 
				String result = no + "\t | " + name  +"\t | " + birthdate ;
				System.out.println(result);
			}// end while
			*/
			
			// 7-3. null 처리하기
			// getXXX() 로 가져온 데이터가 NULL 값이면 null로 리턴한다.
			/*
			while(rs.next()) { 
				String no = rs.getString("mb_no");
				if(no == null) no = "";
				String name = rs.getString("mb_name");
				String birthdate = rs.getString("mb_birthdate"); 
				if(birthdate == null) birthdate = "";
				String result = no + "\t | " + name  +"\t | " + birthdate ;
				System.out.println(result);
			}// end while
			System.out.println();
			*/
			
			// 7-4. 개별적인 타입으로 get 하기
			while(rs.next()) { 
				int no = rs.getInt("mb_no");
				// mb_no 컬럼값이 null이면 0 리턴
				String name = rs.getString("mb_name");
				String birthdate = "";
				
				// java.sql.*
				Date d = rs.getDate("mb_birthdate"); // 날짜만 가져옴
				Time t = rs.getTime("mb_birthdate"); // 시간만 가져옴
				
				if(d != null) {
					birthdate = new SimpleDateFormat("yyyy년 MM월 dd일").format(d) + " " 
						+ new SimpleDateFormat("hh:mm:ss").format(t)	;
				}
				
				
				String result = no + "\t | " + name  +"\t | " + birthdate ;
				System.out.println(result);
			}// end while
			System.out.println();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) { // 거이 최상위 
			e.printStackTrace();
		}finally{
			try {
				// 나중에 만들어진 인스턴스 부터 먼저 close() 해주자
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println("프로그램 종료");
	} // end main()

} // end class DB01Main













