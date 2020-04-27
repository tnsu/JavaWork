package com.lec.java.class06;

public class Class06Main {

	public static void main(String[] args) {
		System.out.println("클래스 연습 : 성적처리");
		
		Score s1 = new Score("수빈", 10, 29, 79);
		s1.displayInfo();
		
		int total = s1.calcTotal();
		double avg = s1.calcAvg();
		System.out.println("총점: " + total);
		System.out.println("평균: " + avg);

		System.out.println("프로그램 종료");
	} // end main()

} // end class Clas06Main










