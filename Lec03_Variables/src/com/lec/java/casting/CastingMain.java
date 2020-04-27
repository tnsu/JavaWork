package com.lec.java.casting;
/*
 * 암묵적 형변환(Implicit casting): 자바 언어가 자동으로 해주는 형변환
 * 
 * 		primitive type 에서 implicit casting 방향
 * 
 * 		byte → short → int → long → float → long  
 *                 		↑
 *                   char
 * 
 * 명시적 형변환(Explicit casting): 프로그래머가 직접 타입을 변환하는 것
 *      
 *      (변환하고자 하는 타입명)변수/값      
       
 */

public class CastingMain {

	public static void main(String[] args) {
		System.out.println("형변환(Type Casting/Type Converting");
		byte num1 = 123;
		int num2 = 123;
		//byte num3 = num2;   // 자동형변환 불가 int 타입 -> byte 타입 안됨
		byte num3 = (byte)num2;  // 명시적 형변환!
		
		System.out.println("num3 : " + num3);
		
		// 명시적 형변환시 주의점 : 데이터 손실이 발생할 수 있음
		byte num5 = (byte)513; // 상위바이트를 없에고 하위1바이트만 가져옴
		System.out.println("num5 : " + num5); // 1
		
		//
		double avg1 = (99 + 88 + 78) / 3; //  int/int 기 때문에 int 소수값이 잘림 
		System.out.println("avg1 : " + avg1);
		
		double avg2 = (double)(99 + 88 + 78) / 3; //둘 중 하나만 바꿔도 됨 
		System.out.println("avg2 : " + avg2);
		
		double avg3 = (99 + 88 + 78) / 3.0;
		System.out.println("avg3 : " + avg3);
		
		
		
	}//end main

}//end class












