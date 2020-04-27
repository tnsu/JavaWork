package practice.cardcompany;

public class Company2 {
	private int num = 10000;
	
	private Company2() {}
	
	private static Company2 instance = null;
	public static Company2 getInstance() {
		if(instance == null) {
			instance = new Company2();
		}
		return instance;
	}
	 public Card2 createCard() {
		 num++;
		 return new Card2(num);
	 }
	// 필요한 변수, 메소드, 생성자 정의하기
		
} // end class
