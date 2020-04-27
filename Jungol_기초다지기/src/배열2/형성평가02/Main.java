package 배열2.형성평가02;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int [] ar = new int[100];
		//new int[11]; 
		
		
		for (int i = 0; i < ar.length; i++) {
			int score = sc.nextInt();
			int p = score;
			ar[i] = p;
			if(score == 0) {	
				break;
			}	
		}
		int num = 0;
		for (int j = 0; j < ar.length; j++) {
		switch (ar[j]/10) {
		case 6:
			num++;
			continue;

		default:
			break;
		}
		}
		System.out.println(num + "person");
	}
}
