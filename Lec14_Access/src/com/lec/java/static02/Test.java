package com.lec.java.static02;

public class Test {
	int num; // 인스턴스 변수
	int num2;
	static int sNum; // 클래스 변수(static 변수)
	
	// 인스턴스 메소드
	public void show() {
		System.out.println("인스턴스 num = "+ num);
		System.out.println("인스턴스 num2 = "+ num2);
		System.out.println("클래스(static) sNum = " + sNum);
	}// end show()
	
	// 클래스(static) 메소드
	public static void show2() {
		// System.out.println("인스턴스 num = "+ num);
		System.out.println("클래스(static) sNum = " + sNum);
	}// end show2()
	
	
}//end class test
