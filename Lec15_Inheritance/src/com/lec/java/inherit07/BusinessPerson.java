package com.lec.java.inherit07;

public class BusinessPerson extends Person {
	
	private String company;

	public String getCompany() {return company;}
	public void setCompany(String company) {this.company = company;}
	
	// 메소드 재정의(Overriding)
	public void showInfo() {
		super.showInfo();
		System.out.println("회사는 : " + company);
	}

		
	//메소드 오버로딩( Overloading)
	public void showInfo(int id) {
		System.out.println("id : " + id);
		showInfo();
	}
	
//	@Override
//	public void whoAreYou() { // final 메소드 오버라이딩 불가
//		super.whoAreYou();
//	}
	@Override
	public String toString() { // 객체를 문자열로 사용할떄 사용
		return "BusinessPerson:" + getName() +  " " + company;
	}
	
	
	
}
