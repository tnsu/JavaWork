package com.lec.java.final01;

public class Final01Main {
	
	// final 멤버변수는 반드시 선언과 동시에 초기화 해준다
	final int NUM = 1;
	
	
	public static void main(String[] args) {
		System.out.println("final: 변경할 수 없는 상수");
		
		int num1 = 1;
		num1 = 10;
		
		final int num2 = 1;
//	num2 = 20; //x
		
		final int num3;
		num3 = 1; // 초기화 나중에 가능

	} // end main()

} // end class Final01Main










