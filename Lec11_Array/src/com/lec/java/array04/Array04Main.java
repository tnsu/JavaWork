package com.lec.java.array04;

import java.util.Scanner;

/* 연습
 * 길이 5개 int 형 배열을 선언하고
 * 사용자로부터 5개 정수를 입력받아 초기화 한뒤 
 * 
 * 총점, 평균, 최대값, 최소값  출력해보기
 */
public class Array04Main {

	public static void main(String[] args) {
		System.out.println("배열 연습");
		Scanner sc = new Scanner(System.in);
		int sum = 0, min = 0, max = 0;

		int[] arIn = new int[5];
		
		for (int i = 0; i < arIn.length; i++) {
			arIn[i] = sc.nextInt();
			 sum += arIn[i]; // 총점 누적
		}// end for()
		
		max = arIn[0];
		for (int i = 1; i < arIn.length; i++) {
			if(arIn[i] > max) {
				max = arIn[i];
			}
		}
		min =  arIn[0];
		for (int i = 1; i < arIn.length; i++) {
			//min = (arIn[i] < min) ? arIn[i] : min;
			if(arIn[i] < min) {
				min = arIn[i];
			}
		}
		System.out.println("총점 : " + sum);
		System.out.println("평균 : " + (double)sum / arIn.length);
		
		System.out.println("최대값 : " + max);
		System.out.println("최소값 : " + min);
		
	} // end main()

} // end class Array04Main





















