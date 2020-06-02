package com.lec.beans;

//Person bean 객체 정의
public class Person {
	private String name;
	private int age;
	private int id;
	private String gender;
	
	// 기본생성자,매개변수 생성자, getter,setter 생성
	public Person() {} // 기본생성자가 없으면 에러 발생한다.
	
	public Person(String name, int age, int id, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.id = id;
		this.gender = gender;
	}


	public final String getName() {
		return name;
	}
	public final void setName(String name) { //setname 동작, setnAme 에러발생 500에러
		this.name = name;
	}
	public final void setname(String name) {  // setname을 우선적을 찾는다.(property="Name" 일시에는 아니다.
		this.name = name;
	}
	public final int getAge() {
		return age;
	}
	public final void setAge(int age) {
		this.age = age;
	}
	public final int getId() {
		return id;
	}
	public final void setId(int id) {
		this.id = id;
	}
	public final String getGender() {
		return gender;
	}
	public final void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
