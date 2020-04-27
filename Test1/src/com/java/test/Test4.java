package com.java.test;

import java.util.Scanner;

public class Test4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int min = Integer.MAX_VALUE;
		int max = 0;
		
		while(true) {
			int num1 = sc.nextInt();
			min = (min < num1) ? min : num1;
			max = (max > num1) ? max : num1;
			
			if(num1 == 0) {
			System.out.println(max+ " "+ min);
			break;
			}
			
		}

	}

}
