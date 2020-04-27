package phonebook05.file;

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
import java.util.ArrayList;


// CONROLLER 객체
// 어플리케이션의 동작 데이터 처리(CURD), (Business logic 담당) 출력은 하지않는다. 주어진인터페이스와
public class PhonebookManager implements pb , Closeable{
	public static final String PB_DATA_DIR = "data";
	public static final String PB_DATA_FILE = "phonebook.dat";
	private File pbDir;
	private File pbFile;
	
	
	private ArrayList<PhonebookModel> pbList = new ArrayList<PhonebookModel>();
	// singleton적용
	private PhonebookManager() {
		pbDir = new File(PB_DATA_DIR);
		if(!pbDir.exists()) {
			if(pbDir.mkdir()) {
				System.out.println("폴더 생성 성공");
			}else {
				System.out.println("폴더 생성 실패");
			}
		}else {
			System.out.println("폴더 존재 : " + pbDir.getAbsolutePath());
		}
		
		pbFile = new File(pbDir,PB_DATA_FILE);
		if(pbFile.exists()) {
			System.out.println("파일에서 데이터 읽습니다.");
			try(
					InputStream in = new FileInputStream(pbFile);
					ObjectInputStream oin = new ObjectInputStream(in);					
					) {
			
				pbList = (ArrayList<PhonebookModel>)oin.readObject();
				System.out.println(pbList.size() + " 개의 데이터을 읽었습니다.");
			} catch (FileNotFoundException e) {
				System.out.println("저장된 번호가 없습니다.");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			

		}else {
			System.out.println("읽어올 파일이 없음");
		}
		
		// 파일이 존재하면 파일 읽어 들이기--> pblist;
		// 파일의 존재 여부 
		//FileNotFoundException 여부로 확인
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
		
		PhonebookModel pbm = new PhonebookModel(name, phoneNum, memo);  //인스턴스생성 
		pbm.setUid(getMaxUid()+1);
		pbList.add(pbm);
//		pbList.add(new PhonebookModel(name, phoneNum, memo));
	
		return 1;
	}

	@Override
	public PhonebookModel[] selectAll() {
		PhonebookModel[] pbm = new PhonebookModel[pbList.size()]; // pbList의 사이즈 배열의 길이로 지정해줌
		for (int i = 0; i < pbm.length; i++) { //pbList.size() 
			pbm[i] = pbList.get(i);
		}
		return pbm;
		
		//아래와 같이 한줄로 가능 toArray() :List<> --> 배열로 변환
//		return pbList.toArray(new PhonebookModel[pbList.size()]);
	}
	
	

	// 특정 uid의 데이터 검색 리턴
	// 못찾으면 null 리턴
	@Override
	public PhonebookModel selectByUid(int uid) {
		for (int i = 0; i < pbList.size(); i++) {
			if(pbList.get(i).getUid() == uid) {
				return pbList.get(i); 
			}
		}
		return null; // 못찾으면 null 리턴
	}

	@Override
	public int updateByUid(int uid, String name, String phoneNum, String memo) {
		// 매개변수 검증
		if (uid < 1)
			throw new PhonebookException("update() uid 오류 : " + uid, pb.ERR_UID);
		if (name == null || name.trim().length() == 0) // 이름 필수
			throw new PhonebookException("update() 이름입력 오류  ", pb.ERR_EMPTY_STRING);
		// 특정 uid 값을 가진 데이터의 배열 인덱스 찾기
		int index = findIndexByUid(uid);
		if (index < 0)
			throw new PhonebookException("update() 없는 uid : " + uid, pb.ERR_UID);
		
		pbList.get(index).setName(name);
		pbList.get(index).setPhoneNum(phoneNum);
		pbList.get(index).setMemo(memo);
		
	
		return 1;
	}

	@Override
	public int deldteByUid(int uid) {

		// 매개변수 검증
		if (uid < 1)
			throw new PhonebookException("deldte() uid 오류 : " + uid, pb.ERR_UID);

		int index = findIndexByUid(uid);
		if (index < 0)
			throw new PhonebookException("deldte() 없는 uid  : " + uid, pb.ERR_UID);

		pbList.remove(index);

		return 1;
	}

	// 현재 데이터중 가장 큰 uid 값을 찾아서 리턴
	private int getMaxUid() {
		int maxUid = 0;
		for (int i = 0; i < pbList.size(); i++) {
			if(maxUid < pbList.get(i).getUid()) {
				maxUid =pbList.get(i).getUid();
			}
		}
		return maxUid;
	}

	// 특정 uid 값을 가진 데이터의 배열 인덱스 찾기
	// 못찾으면 -1 리턴
	private int findIndexByUid(int uid) {
		for (int i = 0; i < pbList.size(); i++) {
			if(pbList.get(i).getUid() == uid) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void close() throws IOException {
		OutputStream out = new FileOutputStream(pbFile);
		try (ObjectOutputStream oout = new ObjectOutputStream(out)) {
			// 데이터 저장, pbList -> 파일
			oout.writeObject(pbList);
			System.out.println("파일 저장 완료");
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
