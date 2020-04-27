package com.lec.java.class02;



public class Class02Main {

	public static void main(String[] args) {
		System.out.println("클래스 연습");		
		
		Circle c1 = new Circle(); // 기본 생성자
		Circle c2 = new Circle(3);
		
		
		double perimeter = c1.calcPerimeter();
		System.out.println("c1의 둘레 : " + perimeter);
		
		perimeter = c2.calcPerimeter();
		System.out.println("c2의 둘레 : " + perimeter);
		
		System.out.println();
		Rectangle r1 = new Rectangle();
		Rectangle r2 = new Rectangle(2, 3);
		
		double rect = r1.calcPerimeter();
		System.out.println("r1의 둘레 : " + rect);
		double area = r1.calcArea();
		System.out.println("r1의 넓이 : " + area);
		
		rect = r2.calcPerimeter();
		System.out.println("r2의 둘레 : " + rect);
		area = r2.calcArea();
		System.out.println("r2의 넓이 : " + area);
		
		
		System.out.println("프로그램 종료");
	} // end main()

} // end class Class02Main










