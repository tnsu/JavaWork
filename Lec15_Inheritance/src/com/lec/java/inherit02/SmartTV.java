package com.lec.java.inherit02;

// BasicTV로부터 상속받은 
//	 └ SmartTV
public class SmartTV  extends BasicTV{
	String ip;
	
	public void displayInfo() {
		super.displayInfo();
		System.out.println("IP주소 : " + ip);
	}
}
