package com.lec.java.inherit04;

public class Car extends Vehicle{
	
	int oil;
	public Car() {
		// 부모클래스의 생성자 호출 --> Vehicle()
		// 명시적인 super 생성자가 없으면 부모의 기본생성자를 호출하게 됨
		System.out.println("Car() 생성");
	}
	
	public Car(int oil) {
		// super()는 반드시 생성자 코드의 '첫번째 문장'이여야 함
//		super(); // super : 부모를 의미, super() <- 부모의 기본생성자 호출
		this.oil = oil;
		System.out.println("Car(int) 생성 : oil = " + oil);
	}
	
	public Car(int speed, int oil) {
		super(speed); // super(int) <- int 타입을 받는  부모생성자에서  호출 
		this.oil = oil;
		System.out.println("Car(int, int) : speed =" + speed+", oil =" + oil);
	}
	
	public Car(double value) {
		this(555, (int)value);  
		// 자기 생성자를 호출하는 것 생성자 위임(delegation)
		//this 와 super은 양립할수 없음
		System.out.println("Car(double) 생성 : value = " + value);
	}
	
}
