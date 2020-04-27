package phonebook03.ex;

import java.util.Arrays;

// CONROLLER 객체
// 어플리케이션의 동작 데이터 처리(CURD), (Business logic 담당)
public class PhonebookManager implements pb {
	
	public static final int MAX_DATA = 3; //최대 데이터 개수
	
	// 배열로 구현
		private PhonebookModel[] pblist = new PhonebookModel[MAX_DATA]; 
		
	// 몇개의 데이터가 저장되었는지
		private int count = 0;
		
	
	// singleton적용
		private PhonebookManager() {}
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
		if(name == null || name.trim().length() == 0) {
			 throw new PhonebookException("insert() 이름입력오류  ",pb.ERR_EMPTY_STRING);
		}
		
		// 만약 배열이 다 차있으면, MAX_DATA 만큼 추가된 새 배열 작성
		if(count == pblist.length) {
			pblist = Arrays.copyOf(pblist, pblist.length + MAX_DATA);
			// 자리를 옮겨주는,  ( 변경하려는array이름, 그길이와 추가할만큼)
		}
		
		pblist[count] = new PhonebookModel(name, phoneNum, memo);
		pblist[count].setUid(getMaxUid()+1); // 기존의 최대 uid 값보다 1 증가한 값, (unique 한 값 보장)
		// uid 와 배열 index는 다르다!!
 		count++;
		
		return 1;
	}

	@Override
	public PhonebookModel[] selectAll() {
		return Arrays.copyOfRange(pblist, 0, count); // 0 ~ count 직전까지의 배열 
	}

	// 특정 uid의 데이터 검색 리턴
		// 못찾으면 null 리턴
	@Override
	public PhonebookModel selectByUid(int uid) {
		for (int index = 0; index < count; index++) {
			if(pblist[index].getUid() == uid) {
				return pblist[index]; // uid값 발견하면 리턴
			}
		}
		return null; // 못찾으면 null 리턴
	}

	@Override
	public int updateByUid(int uid, String name, String phoneNum, String memo) {		
		// 매개변수 검증 
		if(uid < 1) 
			throw new PhonebookException("update() uid 오류 : " + uid, pb.ERR_UID);
		if(name == null || name.trim().length() == 0) // 이름 필수
			throw new PhonebookException("update() 이름입력 오류  ", pb.ERR_EMPTY_STRING);
		//특정 uid 값을 가진 데이터의 배열 인덱스 찾기
		int index = findIndexByUid(uid);
		if(index < 0) 
			throw new PhonebookException("update() 없는 uid : " + uid, pb.ERR_UID);
		
		pblist[index].setName(name);
		pblist[index].setPhoneNum(phoneNum);
		pblist[index].setMemo(memo);
		
		
		
		return 1;
	}


	@Override
	public int deldteByUid(int uid) {
		
		// 매개변수 검증
		if(uid < 1)
			throw new PhonebookException("deldte() uid 오류 : " + uid, pb.ERR_UID);
		
		int index = findIndexByUid(uid);
		if(index < 0)
		throw new PhonebookException("deldte() 없는 uid  : " + uid, pb.ERR_UID);
			
		// 배열 뒤에 원소들을 앞으로 이동
		for (int i = index, j = index + 1; j < count; i++, j++) { //j에있는게 i로 당겨오는것
			pblist[i] = pblist[j];
		}
		
		//count 감소
		count--;
			
		return 1;
	}
	
	// 현재 데이터중 가장 큰 uid 값을 찾아서 리턴
	private int getMaxUid() {
		int maxUid = 0;
		
		for (int index = 0; index < count; index++) {
			if(maxUid < pblist[index].getUid()) {
				maxUid = pblist[index].getUid();
			}
		}
		return maxUid;
	}
	
	//특정 uid 값을 가진 데이터의 배열 인덱스 찾기
	// 못찾으면 -1 리턴
	private int findIndexByUid(int uid) {
		for (int index = 0; index < count; index++) {
			if(pblist[index].getUid() == uid) {
				return index;
			}
		}
		return -1;
	}
	
	

}//end PhonebookManager()


// 예외 클래스 정의
// 예외 발생하면 '에러코드' + '에러메세지'를 부여하여 관리하는게 좋다.
class PhonebookException extends RuntimeException{
	private int errCode = pb.ERR_GENERIC;
	
	public PhonebookException() {
		super("Phonebook 예외 발생");
	}
	
	public PhonebookException(String msg){
		super(msg);
	}
	
	public PhonebookException(String msg, int errCode){
		super(msg);
		this.errCode = errCode;
	}
	
	//throwable의 getMessage  를 오버라이딩 가능
	@Override
	public String getMessage() {
		String msg = "ERR-" + errCode + "]" + pb.ERR_STR[errCode] + " " + super.getMessage();
		return msg;
	}

	
	
}

