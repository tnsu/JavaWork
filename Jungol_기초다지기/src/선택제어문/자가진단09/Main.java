package 선택제어문.자가진단09;
/*
 * 3개의 정수를 입력받아 조건연산자를 이용하여 입력받은 수들 중 최소값을 출력하는 프로그램을 작성하시오.
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		 int n1 = sc.nextInt();
		 int n2 = sc.nextInt();
		 int n3 = sc.nextInt();
		int min;
		min =  (n1 < n2)? n1: n2;
		min = (min < n3)? min: n3; 
				System.out.println(min);
		
		
		sc.close();
	}

}
