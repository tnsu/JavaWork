package com.lec.java.oop01;
/*
 	다형성  ( Polymorphism )
 	하나의 이름의 클래스나 메소드가 '여러 가지 형태의 동작을 하는 능력'
 	
 	클래스의 다형성:
	 	한 타입의 참조변수로 여러타입의 객체를 참조 가능.
	 	조상클래스 타입의 참조변수로 자손클래스의 인스턴스를 참조가능한것
 	
 	메소드의 다형성:
 		메소드 오버로딩, 메소드 오버라이딩
 	
 */
public class Polymorphism01Main {

	public static void main(String[] args) {
		System.out.println("다형성(Polymorphism)");
		
		Vehicle v1 = new Vehicle();
		Car c1 = new Car();
		HybridCar h1 = new HybridCar();
		
		v1.displayInfo();
		c1.displayInfo();
		h1.displayInfo();
		
		System.out.println();
		
		//자손타입이 조상타입으로 형변환이 가능함
		Vehicle car1 = new Vehicle();
		Vehicle car2 = new Car(); // Car IS-A Vehicle
		Vehicle car3 = new HybridCar(); // HybridCar IS-A Car
		
		// 생성자의 실제 인스턴스의 값으로  나옴
		car1.displayInfo();
		car2.displayInfo();
		car3.displayInfo();
		
		
		Car car4 = new HybridCar();
		
//		HybridCar car5 = new Car(); // 불가능 자손 <- 조상(x)
		
		
		System.out.println("\n 프로그램 종료");
	} // end main()
	
	// TODO

} // end class








































