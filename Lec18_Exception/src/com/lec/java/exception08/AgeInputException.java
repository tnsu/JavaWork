package com.lec.java.exception08;

// TODO : Exception 상속받은 예외 클래스 만들기
public class AgeInputException extends Exception{

	//생성자
	public AgeInputException() {
		super("나이 입력 오류");
//		super("0살부터 100까지만 입력이 가능합니다,");
	}
	
} // end class AgeInputException
