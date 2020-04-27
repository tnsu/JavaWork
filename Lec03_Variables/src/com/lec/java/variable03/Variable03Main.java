package com.lec.java.variable03;

public class Variable03Main {

	public static void main(String[] args) {
		System.out.println("변수 - 실수타입 float, double");
		
		double number1 = 3.141592;
		// float number2 = 3.14; //기본적으로 실수 리터럴은 double 로 인식
		// float <- double 대입 불가

		// float 리터럴
		float number3 = 3.14f;
		
		// 실수 타입 최소값, 최대값 
		// 실수의 최소 값은 1보다작고 0보다, 음수는 - 넣으면 됨
		//  float와 double 저장할 수 있는 값의 크기만이 아니라 소숫점 이하 정밀도(precision)의 차이가 있음
		// float 6개정도 double 13개 정도 입력한 값으로 나옴
		System.out.println("float : " +Float.MIN_VALUE + " ~ " + Float.MAX_VALUE); 
		//1.4E-45 :  1.4*10의-45 이라는 뜻
		System.out.println("double : " +Double.MIN_VALUE + " ~ " + Double.MAX_VALUE);
		
		float number4 = 1.23456789f;
		double number5 = 1.23456789;
		
		System.out.println("number4 = " + number4);
		System.out.println("number5 = " + number5);
		
		// 실수 표기법
		double number6 = 123; // 자동 형변환 123.0
		double number7 = 1.23e2; // 지수표기법(exponential notation) e 대소문자 상관없음
		
		System.out.println("number6 = " + number6);
		System.out.println("number7 = " + number7);
		
		double number8 = 0.0001213;
		double number9 = 1.213E-4;
		
		System.out.println("number8 = " + number8);
		System.out.println("number9 = " + number9);
		
		
		
	}// end main()

}// end class

