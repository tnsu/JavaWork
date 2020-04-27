package phonebook04.list;

import java.util.ArrayList;


// CONROLLER 객체
// 어플리케이션의 동작 데이터 처리(CURD), (Business logic 담당) 출력은 하지않는다. 주어진인터페이스와
public class PhonebookManager implements pb {

	private ArrayList<PhonebookModel> pbList = new ArrayList<PhonebookModel>();
	// singleton적용
	private PhonebookManager() {
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
		 
		//셋중에 가장 좋음
		pbList.get(index).setName(name);
		pbList.get(index).setPhoneNum(phoneNum);
		pbList.get(index).setMemo(memo);
		
//		pbList.set(index,new PhonebookModel(name, phoneNum, memo));
//		pbList.get(index).setUid(uid);
		
//		int i = pbList.get(index).getUid();
//		PhonebookModel pbm = new PhonebookModel(name, phoneNum, memo);
//		pbm.setUid(i);
//		pbList.set(index, pbm); 
		

		
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

		//20.03.27 by. 윤종섭
//		if(pbList.size() > 0) {
//			int uid = pbList.get((pbList.size() - 1)).getUid();
//			if(maxUid < uid) maxUid = uid;
//		} //중간에 삽입하는것이 없으면 끝에있는걸로만 동작을 하게하려면 사용이 가능함
		
//		for (PhonebookModel p : pbList) {
//		if(maxUid < p.getUid()) {
//			maxUid = p.getUid();
//		}
//	}
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
