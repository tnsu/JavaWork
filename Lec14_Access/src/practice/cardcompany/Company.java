package practice.cardcompany;

public class Company { 
	private static int num = 10000; //어짜피 인스턴스는 1개이기 때문에 static을 써주지않아도 동작은 한다. 
	private Company() {}
	private static Company instance = null;
	public static Company getInstance() {
		if(instance == null) {
			instance = new Company();
		}
		return instance;
	}
	
	public Card createCard() {	//동작할때마다 넘버 증가
		 num++;
		return new Card(num);
	}

	
	
	// 필요한 변수, 메소드, 생성자 정의하기
		
} // end class
