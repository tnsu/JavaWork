package 선택제어문.형성평가01;


//두 개의 정수를 입력받아 큰 수에서 작은 수를 뺀 차를 출력하는 프로그램을 작성하시오.

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n1 = sc.nextInt();
		int n2 = sc.nextInt();
		 int min = (n1 < n2)? n1 : n2;
		int max = (n1 > n2 )? n1 : n2;
		System.out.println(max-min);
	}

}
