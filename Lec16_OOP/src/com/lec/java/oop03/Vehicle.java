package com.lec.java.oop03;

public class Vehicle {
	private int speed;

	protected int getSpeed() { return speed;}
	protected void setSpeed(int speed) {this.speed = speed;}
	
	public void displayInfo() {
		System.out.println("--- Vehicle 정보 ---");
		System.out.println("speed : " + speed);
	}
	
	
	
	
}
