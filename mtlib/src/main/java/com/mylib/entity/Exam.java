package com.mylib.entity;

public class Exam {
	int kor;
	int eng;
	int math;

	@Override
	public String toString() {
		return String.format(
			"Exam [국어:%d 영어:%d 수학:%d]", kor, eng, math);
	}
	
	public int getTotal() {
		return kor + eng + math;
	}
	
	public float getAvg() {
		return getTotal() / 3.0f;
	}

	// 기본생성자, 매개변수생성자,
	// getter/setter 만들기

	
	public Exam() {
		super();
	}


	public Exam(int kor, int eng, int math) {
		super();
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}


	public int getKor() {
		return kor;
	}


	public void setKor(int kor) {
		this.kor = kor;
	}


	public int getEng() {
		return eng;
	}


	public void setEng(int eng) {
		this.eng = eng;
	}


	public int getMath() {
		return math;
	}


	public void setMath(int math) {
		this.math = math;
	}
	
	
	
}


