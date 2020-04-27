package phonebook06.db;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream.PutField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import phonebook06.db.PhonebookModel;


// CONROLLER 객체
// 어플리케이션의 동작 데이터 처리(CURD), (Business logic 담당) 출력은 하지않는다. 주어진인터페이스와
public class PhonebookManager implements pb, Closeable{
	
	// : db를 위한 변수선언
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
 
	
	// singleton적용
	private PhonebookManager() {
		//
		//JDBC 프로그래밍 
		try {
			//클래스 로딩
			Class.forName(DRIVER);
			System.out.println("드라이버 로딩 성공");
			
			//연결 connection
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			System.out.println("Connection 생성 성공");
			
			//statement (필요시 )생성
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	
	}
	private static PhonebookManager instance = null;

	public static PhonebookManager getInstance() {
		if (instance == null) {
			instance = new PhonebookManager();
		}
		return instance;
	} // end getInstance()

	// 전화번호부 생성 등록
	@Override
	public int insert(String name, String phoneNum, String memo) {

		// 매개변수 검증 : 이름 필수
		if (name == null || name.trim().length() == 0) {
			throw new PhonebookException("insert() 이름입력오류  ", pb.ERR_EMPTY_STRING);
		}
		
		int cnt = 0;
		//
		//SQL_INSERT  close()여기서 sptmt
		try {
			pstmt = conn.prepareStatement(SQL_INSERT);
			pstmt.setString(1, name);
			pstmt.setString(2, phoneNum);
			pstmt.setString(3, memo);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return cnt;
	
	}

	@Override
	public PhonebookModel[] selectAll() {
		//SQL_SELECT_ALL
		List<PhonebookModel> pbList = new ArrayList<PhonebookModel>();
		
		try {
			pstmt = conn.prepareStatement(SQL_SELECT_ALL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
			// DATA 변환하기 1.
			String date = new SimpleDateFormat("yyyy년 MM월 dd일").format(rs.getDate(COL_LABEL_REGDATE)) + " " 
					+ new SimpleDateFormat("HH:mm:ss").format(rs.getTime(COL_LABEL_REGDATE));
			Date rdate =  new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss").parse(date);
			//2.
			//			rs.getTimestamp(COL_LABEL_REGDATE); // 년월일 시분초를 한번에 받을 수있음
			/* 
			// 3
			String Date = rs.getString(COL_LABEL_REGDATE);
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date to = transFormat.parse(Date);
			*/
				pbList.add(new PhonebookModel(rs.getInt(COL_LABEL_UID),
						rs.getString(COL_LABEL_NAME),
						rs.getString(COL_LABEL_PHONENUM),
						rs.getString(COL_LABEL_MEMO),
						rdate
						));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return pbList.toArray(new PhonebookModel[pbList.size()]);
		
	}
	
	

	// 특정 uid의 데이터 검색 리턴
	// 못찾으면 null 리턴
	@Override
	public PhonebookModel selectByUid(int uid) {
		PhonebookModel pbMOdel = null;
		try {
			pstmt = conn.prepareStatement(SQL_SELECT_BY_UID);
			pstmt.setInt(1, uid);
			rs = pstmt.executeQuery();
		
			if(rs.next()) {
				String date = new SimpleDateFormat("yyyy년 MM월 dd일").format(rs.getDate(COL_LABEL_REGDATE)) + " " 
						+ new SimpleDateFormat("HH:mm:ss").format(rs.getTime(COL_LABEL_REGDATE));
				Date rdate =  new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss").parse(date);
				pbMOdel= new PhonebookModel(rs.getInt(COL_LABEL_UID),
						rs.getString(COL_LABEL_NAME),
						rs.getString(COL_LABEL_PHONENUM),
						rs.getString(COL_LABEL_MEMO),
						rdate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return pbMOdel; // 못찾으면 null 리턴
	}

	@Override
	public int updateByUid(int uid, String name, String phoneNum, String memo) {
		// 매개변수 검증
		if (uid < 1)
			throw new PhonebookException("update() uid 오류 : " + uid, pb.ERR_UID);
		if (name == null || name.trim().length() == 0) // 이름 필수
			throw new PhonebookException("update() 이름입력 오류  ", pb.ERR_EMPTY_STRING);
		
		int cnt =0;
	
		// :UPDATE 
		try {
			pstmt = conn.prepareStatement(SQL_UPDATE_BY_UID);
			pstmt.setString(1, name);
			pstmt.setString(2, phoneNum);
			pstmt.setString(3, memo);
			pstmt.setInt(4, uid);
			cnt = pstmt.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deldteByUid(int uid) {

		// 매개변수 검증
		if (uid < 1)
			throw new PhonebookException("deldte() uid 오류 : " + uid, pb.ERR_UID);

		int cnt =0;
		
		//:DELETE
		try {
			pstmt = conn.prepareStatement(SQL_DELETE_BY_UID);
			pstmt.setInt(1, uid);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	// 현재 데이터중 가장 큰 uid 값을 찾아서 리턴
	private int getMaxUid() {
		int maxUid = 0;
		//TODO : 옵션
		return maxUid;
	}

	@Override
	public void close() throws IOException {
		
		try {
			if(rs != null)	rs.close();
			if(pstmt != null)	pstmt.close();
			if(conn != null)	conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}// end PhonebookManager()

// 예외 클래스 정의
// 예외 발생하면 '에러코드' + '에러메세지'를 부여하여 관리하는게 좋다.
class PhonebookException extends RuntimeException {
	private int errCode = pb.ERR_GENERIC;

	public PhonebookException() {
		super("Phonebook 예외 발생");
	}

	public PhonebookException(String msg) {
		super(msg);
	}

	public PhonebookException(String msg, int errCode) {
		super(msg);
		this.errCode = errCode;
	}

	// throwable의 getMessage 를 오버라이딩 가능
	@Override
	public String getMessage() {
		String msg = "ERR-" + errCode + "]" + pb.ERR_STR[errCode] + " " + super.getMessage();
		return msg;
	}

}
