package com.lec.java.collection08;
//Set 에서 같은지 다른지, 식별 가능하려면
//hashCode 와 equals 가 오버라이딩 되어야 한다
//hashCode() 값이 같고 equals() 결과가 true 이어야만 같은 객체로 판정한다.

public class MyClass {
	private int id;
	private String name;
	
	public MyClass() {}
	public MyClass(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void displayInfo() {
		System.out.println("학번: " + id);
		System.out.println("이름: " + name);
	}
	
	//만든 코드의 동등성을 주기위해 생성
	@Override
	public boolean equals(Object obj) { // 비교대상이 되는것
		MyClass other = (MyClass)obj;
		
		boolean result = (this.id == other.id) && (this.name.equalsIgnoreCase(other.name)); //대소문자 안가림
		
		return result;
	}
	
	@Override
	public int hashCode() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return "학번: " + id + "\n" + "이름: " + name;
	}
	
} // end class MyClass
