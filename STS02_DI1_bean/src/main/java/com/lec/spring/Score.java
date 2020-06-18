package com.lec.spring;

import java.util.Arrays;
import java.util.List;

public class Score {
	int kor; // 국어점수
	int math; // 수학
	int eng;  // 영어
	String  comment; // 평가코맨트

	public Score() {
		super();
		System.out.println("score 생성");
	}
	public Score(int kor, int math, int eng, String comment) {
		super();
		System.out.printf("Score(%d,%d,%d,%s) 생성\n",kor, eng,math,comment);
		this.kor = kor;
		this.math = math;
		this.eng = eng;
		this.comment = comment;
	}
	public Score(int kor, int math, String comment, int eng ) {
		super();
		System.out.printf("Score(%d,%d,%s,%d) 생성\n",kor, eng,comment,math);
		this.kor = kor;
		this.math = math;
		this.eng = eng;
		this.comment = comment;
	}
	
	public Score(int[]arr) {
		System.out.printf("Score(%s)생성\n", Arrays.toString(arr));
		this.kor = arr[0];
		this.math = arr[2];
		this.eng = arr[1];
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setScore(List<Integer> scores) {
		this.kor = scores.get(0);
		this.eng = scores.get(1);
		this.math = scores.get(2);
	}
	
	@Override
	public String toString() {
		return String.format("[Score 국어 : %d  영어 : %d  수학 : %d  평가 : %s ]", kor,eng, math,comment);
	}
	
	
	
}// end Score
