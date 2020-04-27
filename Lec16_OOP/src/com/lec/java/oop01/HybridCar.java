package com.lec.java.oop01;

public class HybridCar extends Car{
	private int electicity;

	protected int getElecticity() {return electicity;}
	protected void setElecticity(int electicity) {this.electicity = electicity;}
	
	@Override
	public void displayInfo() {
		System.out.println("--- HybridCar 정보 ---");
		System.out.println("speed : "+ getSpeed());
		System.out.println("oil : " + getOil() );
		System.out.println("electicity : " + electicity);
	
	}
	
	
	
	
}
