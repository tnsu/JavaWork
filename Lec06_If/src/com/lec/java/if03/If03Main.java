package com.lec.java.if03;

import java.util.Scanner;

/* if 조건문 연습 : 간단한 성적 처리 프로그램
 * 사용자로부터 국어,영어,수학 점수 (정수) 를 입력 받은뒤
 * 우선 '총점' 과 '평균' 을 계산해서 출력하고
 * 
 * '학점'을 아래와 같이 출력하세요
 * 	평균이 90점 이상이면 "A학점" 출력 (평균: 90 ~ 100)
 * 	평균이 80점 이상이면 "B학점" 출력 (평균: 80 ~ 89)
 * 	평균이 70점 이상이면 "C학점" 출력 (평균: 70 ~ 79)
 * 	평균이 60점 이상이면 "D학점" 출력 (평균: 60 ~ 69)
 * 	평균이 60점 미만이면 "F학점" 출력
 */

public class If03Main {

	public static void main(String[] args) {
		System.out.println("간단한 성적 처리 프로그램");
		Scanner sc = new Scanner(System.in);
		int kor, eng, math; // 변수 선언
		int total; 
		double avg;
	
		System.out.println("국어 점수 입력 :");
		 kor = sc.nextInt();
		 System.out.println("영어 점수 입력 :");
		 eng = sc.nextInt();
		 System.out.println("수학 점수 입력 :");
		 math = sc.nextInt();
		
		 // 사용이 끝난 Scanner를 닫아줌
			sc.close();
		 
		 total = kor + eng + math;
		 
		 avg = (double) total / 3;  //total / 3.0; // 둘다 상관 없음
		 System.out.println("------- 입력한 점수 -------");
		 System.out.println("국어 : " + kor);
		 System.out.println("영어 : " + eng);
		 System.out.println("수학 : " + math);
		 System.out.println("총점 : " + total);
		 System.out.println("평균: " + avg);
		 System.out.printf("평균 : %.1f \n" ,avg);
		 if(avg >= 90 ) {
				System.out.println("A학점");
			}else if (80 <= avg) {
				System.out.println("B학점");
			}else if (70 <= avg) {
				System.out.println("C학점");
			}else if (60 <= avg) {
				System.out.println("D학점");
			}else{
				System.out.println("F학점");
			}
			
		
		System.out.println("\n프로그램 종료");
	} // end main()

} // end class














