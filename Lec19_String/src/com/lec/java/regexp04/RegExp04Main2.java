package com.lec.java.regexp04;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 정규표현식 연습
 * 
 * 이번에 우리 쇼핑몰에서 할인 쿠폰을 발행하려 한다.
 * 발행되는 쿠폰의 일련번호 형식은 다음과 같다.
 * 
 *    알파벳두자리-숫자4자리-숫자3자리-알파벳3자리 
 * 
 * 알파벳은 대소문자 구문 없슴
 * 숫자는 0으로 시작하면 안됨.
 * 사용자는 발급받은 쿠폰번호를 입력해야 하는데, 
 * 위와 같은 형식만 받아들일수 있도록 만들자
 * 
 * 허용예]
 * 	Ab-7890-786-zuy
 * 	ki-2010-893-Zip
 * 
 * 불가]
 * 	xX-1200-089-zuy
 * 	p9-324-089-zopl
 * 
 *  쿠폰번호를 계속해서 입력 받으면서 
 * "유효한 쿠폰입니다"  혹은 "유효한 쿠폰이 아닙니다" 판정결과를 출력
 * 
 * 'quit' 입력하면 프로그램 종료

 */

public class RegExp04Main2 {

	// ver. 정종훈
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String regex = "^([a-zA-Z]{2}-[1-9]\\d{3}-[1-9]\\d{2}-[a-zA-Z]{3})$"; // 정규표현식
						// 이 코드로 시작하고 끝나야 한다.
						// 주어진 코드는 전체가 그대로 시작해 끝나야 한다.
						// 그룹으로 정해서 ^그룹& 시작과 끝을 그룹으로 끝나도록 설정
		
		String input; // 코드번호
		Pattern pat = Pattern.compile(regex); // 패턴생성

		while (true) {
			System.out.println("정규표현식 예제");
			System.out.print("입력 : ");
			input = sc.next();

			if (input.equals("quit")) { // quit 입력시 종료
				System.out.println("프로그램 종료");
				break;
			} else {
				Matcher matcher = pat.matcher(input);	// 입력받은 값으로 matcher 객체생성 			
				if (matcher.find()) {	// matcher 객체에 입력값과 패턴 매칭	집에서 더 짧게 만들 수 있는 해보기
					System.out.println("유효한 쿠폰 입니다.");
				} else {
					System.out.println("유효한 쿠폰이 아닙니다.");
				}
				System.out.println();
			} // end if

		} // end while
	} // end main

} // end class










