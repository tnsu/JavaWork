package 반복제어문1.형성평가03;

/*
 * 0 부터 100 까지의 점수를 계속 입력받다가 범위를 벗어나는 수가 입력되면 
 * 그 이전까지 입력된 자료의 합계와 평균을 출력하는 프로그램을 작성하시오.

(평균은 반올림하여 소수 첫째자리까지 출력한다.)

입력 예
55 100 48 36 0 101
출력 예
sum : 239
avg : 47.8
 */
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n;
		int sum = 0, cnt = 0;
		for (;;) { //while(true)
			n = sc.nextInt();  // 계속 반복해서 숫자 입력
			if(0 > n || n > 100)    // 범위를 벗어나면 입력 종료
				break;
			cnt++; //입력 개수 증가
			sum += n; // 합계 누적
		}
		System.out.println("sum : " + sum);
		System.out.printf("avg : %.1f", (double) sum / cnt);
		
		sc.close();
	}// end main

}// end class
