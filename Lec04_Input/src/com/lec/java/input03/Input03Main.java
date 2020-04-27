package com.lec.java.input03;

import java.util.Scanner;

public class Input03Main {

	public static void main(String[] args) {
		System.out.println("nextLine() VS next()");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("여러 단어의 문장을 입력> ");
		String line = sc.nextLine(); // '\n' 까지 한 라인 전체 입력
		System.out.println("line : " + line);
		
		// next() (문자열)
		// 다음 단어(token)을 받아서 Stirng 으로 결과 리턴
		System.out.print("여러 단어의 문장을 입력> ");
		String token1 = sc.next(); // 공백(스페이스바)을 인식하지 않음 키보드 버퍼에는 담겨있음.
		System.out.println("token1 : " + token1);
		
		// next()를 또 실행시키면  기존의 버퍼에 담겨 있는 내용들이 담김. 앞에있는 공백은 제거됨
		String token2 = sc.next(); 
		System.out.println("token2 : " + token2);
		String token3 = sc.next(); 
		System.out.println("token3 : " + token3);
		
		//nextInt(), nextDouble()... <-- next() 처럼 동작 됨
		//따라서 숫자 타입도 여러개를 '한줄'로 입력 받을 수 있다.
		System.out.println("숫자 3개(int ,double, int)를 입력 받으세요>");
		int i1 = sc.nextInt();
		double d1 = sc.nextDouble();
		int i2 = sc.nextInt();
		
		System.out.println("i1 , d1 , i2 : " + i1 + " , " + d1  + " , " + i2);
		
		
		sc.close();
	}//end main()

}// end class
















