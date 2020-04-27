package com.lec.java.oop05;

/*
 인터페이스(interface):
 1. 모든 메소드가 public abstract으로 선언되고,
 2. 모든 멤버 변수가 public static final로 선언된
 특별한 종류의 추상 클래스

 인터페이스는 interface라고 선언
 인터페이스를 구현(상속)하는 클래스에서는 implements 키워드를 사용
 인터페이스를 구현(상속)할 때는 개수 제한이 없다.
 메소드 선언에서 public abstract는 생략 가능
 멤버 변수 선언에서 public static final은 생략 가능
*/

public class Interface01Main {

	public static void main(String[] args) {
		System.out.println("인터페이스(interface)");
		
		// TestInterface t1 = new TestInterface(); //추상 메소드를 갖고있는 존재는 new 불가
		
		TestImpl test1 = new TestImpl();
		test1.testAAA();
		test1.testBBB();
		
		TestImpl2 test2 = new TestImpl2();
		
		System.out.println(test1.MIN);
//	System.out.println(test2.MIN); // 모호성 에러 ,상속빋은 인스턴스에 MIN이 둘다존재 하고, 값이달라 값을 꺼내올수없음
		
		System.out.println(TestInterface.MIN); // static 은 static 방법으로 사용하자
		System.out.println(TestInterface2.MIN);

		System.out.println("\n 프로그램 종료");
	} // end main()

} // end class

interface TestInterface{
	// 모든 멤버 변수가 public static final     (private 불가)
		public static final int MIN = 0;
		int max = 100; //public static final 생략가능
		public static final String JAVA_STRING = "Java";
		String KOTLIN_STRING ="kotlin";
		
	// 모든 메소드는 public abstract
		public abstract void testAAA();
		void testBBB(); //public abstract 생략가능
		
}

interface TestInterface2{
	public static final int MIN = 1;		
	public abstract void testAAA();
	public abstract void testCCC();
}


//인스턴스 를 생성할수 없고 다른 클래스에서 구현(implement)해야 함
class TestImpl implements TestInterface{

	@Override
	public void testAAA() {
		System.out.println("AAA");
	}

	@Override
	public void testBBB() {
		System.out.println("BBB");
	}
	
}
//인터페이스는 다중상속이 가능하다
class TestImpl2 implements TestInterface,TestInterface2{
// 이름이 같은 메소드는 하나만 임플리먼트 됨 

	@Override
	public void testCCC() {
		System.out.println("CCC");
		
	}

	@Override
	public void testAAA() {
		System.out.println("AAA");
		
	}

	@Override
	public void testBBB() {
		System.out.println("BBB");
		
	}
	
	
}



































