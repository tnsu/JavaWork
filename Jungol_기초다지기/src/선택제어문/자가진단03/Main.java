package 선택제어문.자가진단03;


// 나이를 입력받아 20살 이상이면 "adult"라고 출력하고 
// 그렇지 않으면 몇 년후에 성인이 되는지를 "○ years later"라는 메시지를 출력하는 프로그램을 작성하시오.
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num1 = sc.nextInt();
		
		if(num1 >= 20) {
			System.out.println("adult");
		}else {
			int num2 = 20 - num1;
			System.out.println( num2 + " years later");
		}
		
		sc.close();
	}

}
