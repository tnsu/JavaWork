package 선택제어문.형성평가03;


//년도를 입력받아 윤년(leap year)인지 평년(common year)인지 판단하는 프로그램을 작성하시오.


import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		int num = sc.nextInt();
		
		if(num%4 ==0 && num%100 != 0 ) {
			System.out.println("leap year");
		}else if(num%400 == 0 ) {
			System.out.println("leap year");		
		}else {
			System.out.println("common year");
		}
	}

}
