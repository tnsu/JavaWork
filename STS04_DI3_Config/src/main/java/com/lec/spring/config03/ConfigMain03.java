package com.lec.spring.config03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.lec.beans.Score;

public class ConfigMain03 {

	public static void main(String[] args) {
		// XML 로부터 설정 (XML 안에 Java 설정이 포함되어 있다,
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:appCtx03.xml");
		System.out.println("ctx 설정 --");
		
		//Java 에서 설정
		System.out.println(ctx.getBean("score1"));
		
		// XML 에서 설정
		System.out.println(ctx.getBean("score2"));
		
		System.out.println("");
	}

}
