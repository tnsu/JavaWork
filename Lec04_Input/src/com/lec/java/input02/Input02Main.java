package com.lec.java.input02;

import java.util.Scanner;

/*
 *  문자열(String) 입력
 * 	char 입력
 * 
 * sc.nextLine(); // 엔터를 입력할때 까지의 모든 문자들을 문자열로 리턴
 * 
 * 
 * */
public class Input02Main {

	public static void main(String[] args) {
		System.out.println("표준 입력 : String, char");
		
		Scanner sc = new Scanner(System.in);
		
		// Stinrg 입력
		System.out.print("이름을 입력하세요 : ");
		String name = sc.nextLine(); 
		System.out.print("별명을 입력하세요 : ");
		String nick = sc.nextLine(); 
	
		
		//char 입력 .nextChar() 없는 명령어
		System.out.print("성별을 입력하세요 M/F : ");
		char gender = sc.next().charAt(0);
		
		System.out.println("이름 :  " + name + "\n별명 :  " + nick + "\n성별 :  " + gender);
		
		System.out.println();
		
		
		System.out.println("나이를 입력하세요 : ");
		int age = sc.nextInt();
		sc.nextLine(); // 엔터 제거를 위해 사용됨
		System.out.println("주소를 입력하세요 : ");
		String address = sc.nextLine();
		
		System.out.println("나이 : " + age + " 주소 : " + address);
		
		
		/*
			숫자 입력받은 뒤 문자열 입력시에는 반드시 '\n' 을 consume(버퍼에서 제거) 해야 함.

			키보드 버퍼 
			nextInt() 를 사용하면 88(엔터) 까지 키보드 버퍼에 들어감,  int 는 정수만 받기떄문에  출력을 88만 함 
			nextLine() 은 엔터를 치면 문장이 끝난다는 뜻이 되어서  println울 바로함
			
			
		*/
		
		sc.close();
		
	}//end main()

}// end class



