package 반복제어문3.형성평가03;
/*
 * 자연수 n을 입력받아 "출력 예"와 같이 출력되는 프로그램을 작성하시오.
주의! '*'과 '*'사이에 공백이 없고 줄사이에도 빈줄이 없다.

입력 예
3
출력 예
*
**
***
**
*
 */
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		for(int i=0; i < n; i++ ){
			for(int j =0; j <= i ; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

		//감소방향
		for(int i = n; i >=1 ; i--){ // i가 1보다 크거나같을때 
			for(int j =0; j < i - 1 ; j++) { // j가 i-1 보다 작을때 
				System.out.print("*");
			}
			System.out.println();
		}
		
		
		
		
		
		
		
	}// end main

}// end class


