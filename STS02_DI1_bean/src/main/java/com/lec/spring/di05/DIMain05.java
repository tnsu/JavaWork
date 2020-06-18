package com.lec.spring.di05;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.lec.spring.Score;
import com.lec.spring.Student;

public class DIMain05 {

	public static void main(String[] args) {
		System.out.println("Main 시작");
		// 컨테이너 생성 
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:studentCtx2.xml");
		System.out.println("--컨테이너 생성--");
		
		Score score= null;
		score = (Score)ctx.getBean("score1");
		System.out.println(score);
		
//		국어만 점수가 들어감
		System.out.println(ctx.getBean("score2"));
		
		System.out.println(ctx.getBean("stu1")); // 생성자 매개변수
		System.out.println(ctx.getBean("stu2")); // 기본 생성자
		
		ctx.close();
		System.out.println("Main 종료");
	}

}
