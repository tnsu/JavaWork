package com.lec.spring.scope;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.lec.beans.Score;

public class ScopeMain {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:appCtx02.xml");
		System.out.println("-- ctx 생성");
		
		// scope="singleton"
		Score score11 = ctx.getBean("score1", Score.class);
		Score score12 = ctx.getBean("score1", Score.class);
		System.out.println(score11);
		System.out.println(score12);
		
		if(score11 == score12) {
			System.out.println("동일 인스턴스 ");
		}else {
			System.out.println("다른 인스턴스");
		}
		
		score12.setComment("눈아푸당");
		System.out.println(score11);
		System.out.println(score12);
		
		// scope="prototype"
		// getBean() 할때마다 bean 인스턴스 새로이 생성
		Score score21 = ctx.getBean("score2", Score.class);
		Score score22 = ctx.getBean("score2", Score.class);
		score21.setComment("21 이에오");
		score22.setComment("22 이에오");
		System.out.println(score21);
		System.out.println(score22);
		
		if(score21 == score22) {
			System.out.println("score21 == score22 ");
		}else {
			System.out.println("score21 != score22");
		}
		
		ctx.close();
		System.out.println("종료");
	}
}
