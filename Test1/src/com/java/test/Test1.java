package com.java.test;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	
	double num1 = sc.nextDouble();
	double num2 = sc.nextDouble();
	
	double sum = num1+ num2;
	double mul = num1*num2;
	
	System.out.printf("%.1f\n",sum );
	System.out.printf("%.1f",mul );
		
		
		
	}
}
