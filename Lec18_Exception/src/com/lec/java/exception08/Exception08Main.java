package com.lec.java.exception08;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Exception08Main {

	static Scanner sc = new Scanner(System.in);
	
	// TODO : AgeInputException 을 throws 하는 메소드 정의
	public static int inputAge() throws AgeInputException {
		System.out.println("나이 입력:");
		int age = sc.nextInt();

	
		if(age < 0) {
			// 우리가 만든 예외를 발생
			// 예외 클래스의 인스턴스를 생성
			AgeInputException ex = new AgeInputException();
			// exception 인스턴스를 throw
			throw ex;
		}
		
		
		
		return age;
		
	} // end inputAge()
	
	
	public static void main(String[] args) {
		System.out.println("예외 클래스 만들기 2");
		
		while(true) {
			try {
				int age = inputAge();
				System.out.println("나이: " + age);
				break; // 한번 입력 true가되면 멈추게 
				//inputAge();  만 넣어도 괜찮음 break;는 해주기 
			}catch(AgeInputException e) {
				System.out.println(e.getMessage());
				System.out.println("다시입력하세요");
			}catch (InputMismatchException e) {
				System.out.println("적절하지 않은 입력값입니다.");
				sc.nextLine();
			}
		}
	sc.close();
		
		
		System.out.println("프로그램 종료...");
		
	} // end main()

} // end class Exception08Main












