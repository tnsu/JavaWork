package 배열1.자가진단09;


// 10개의 정수를 입력받아 배열에 저장한 후 내림차순으로 정렬하여 출력하시오.
/*
 * 입력 예
95 100 88 65 76 89 58 93 77 99
출력 예
100 99 95 93 89 88 77 76 65 58
 */
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] ar =new int[10];
		for(int i = 0; i<ar.length; i++) {
			ar[i] = sc.nextInt();
		}
		sc.close();
		
		//cycle
		for (int i = ar.length; i > 0; i--) {
			//비교
			for (int j = 0; j < i-1; j++) {
				if(ar[j] < ar[j+1]) {
					int temp = ar[j];
					ar[j] = ar[j+1];
					ar[j+1] = temp;
				}
			}
		}
		for(int i = 0; i<ar.length; i++) {
			System.out.print(ar[i]+ " ");
		}
		
	}

}
