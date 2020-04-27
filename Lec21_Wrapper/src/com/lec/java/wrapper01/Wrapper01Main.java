package com.lec.java.wrapper01;

/* 
  Wrapper 클래스: 기본 자료형(primitive type)의 데이터를 감싸는 클래스
 기본 자료형: boolean, char, byte, short, int, long, float, double
 Wrapper: Boolean, Character, Byte, Short, Integer, Long, Float, Double
 
 
 
 Wrapper 클래스는 immutable 하다
 
 */

public class Wrapper01Main {

	public static void main(String[] args) {
		System.out.println("Wrapper 클래스");
		System.out.println("wrapper 클래스에 값을 저장하는 방법");
		System.out.println();

		Integer i1 = 100; // reference <= primitive
		i1 = i1+ 200; // reference에 산술연산?
		System.out.println("i1 =" +i1); //300
		// Wrapper 클래스는 마치 primitive 타입 처럼 동작한다.
		
		
		// 1. 생성자 이용
		Integer num1 = new Integer(123);
		System.out.println("num1 = " + num1);
		
		
		// 2. wrapper 클래스의 static 메소드인 valueOf() 메소드를 사용
		Integer num2 = Integer.valueOf(123);
		System.out.println("num2 = " + num2);
		
		if(num1 == num2) { // 다르다
			System.out.println(" == 같음");
		}else {
			System.out.println(" == 다름");
		}
		
		if(num1.equals(num2)) { // 같다.
			System.out.println("equals() 같음");
		}else {
			System.out.println("equals() 같음");
		}
		
		
		// 3. new Integer() VS Integer.valueOf()
		Integer num3 = new Integer(123); // new 는 새로운 instance 생성
		Integer num4 = Integer.valueOf(123);
			// valueOf()는 생성한 Object를 cache 해둠
			// 동일 literal로 생성하면 기존에 생선된 Object 리턴
			// 왜? 메모리 절약
		
		// new 로 생성한 것들끼리 비교
		if(num1 == num3) {
			System.out.println(" == : 생성자 같은 참조");
		}else {
			System.out.println(" == : 생성자 다른 참조");
		}
		
		// valueOf() 호 생성한 것들끼리 비교
		if(num2 == num4) {
			System.out.println("valueOf() : 같은 참조");
		}else {
			System.out.println("valueOf() : 다른 참조");
		}
		
		// 4. literal 상수로 생성
		System.out.println();
		Integer num5 = 123;
		// Integer.valueOf(123) 과 동일한 코드가 동작됨 (Auto-Boxing)
		if(num4 == num5) {
			System.out.println("literal  같은 참조");
		}else {
			System.out.println("literal 다른 참조");
		}
		
		
		
		// 5. valueOf(문자열) 가능!
		Integer num6 = Integer.valueOf("123"); // Integer 123 으로 변환이 되고나서 valueOf 가됨
		System.out.println(num6);
		
		if(num6 == num5) {
			System.out.println("같은 참조");
		}else {
			System.out.println("다른 참조");
		}
		
		System.out.println();
		// 6. 산술 연산 가능
		num6 *=2;
		System.out.println(num6);
		// Wrapper의 연산결과는 새로운 Wrapper 생성(immutable)
		if(num6 == num5) {
			System.out.println("같은 참조");
		}else {
			System.out.println("다른 참조");// Wrapper 클래스는 immutable 하다
		}
		
		System.out.println("\n프로그램 종료");
	} // end main()

} // end class
















