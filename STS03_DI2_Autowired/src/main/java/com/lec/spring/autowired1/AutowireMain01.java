package com.lec.spring.autowired1;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AutowireMain01 {
	public static void main(String[] args) {
		System.out.println("Autowired 자동주입");
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:autowiredCtx1.xml");
		
		System.out.println("--컨테이너 생성--");
		
		System.out.println(ctx.getBean("regService"));
		System.out.println(ctx.getBean("readService")); 
		System.out.println(ctx.getBean("updateService"));  //1 , dao 가 null 인데 dao.toString() 해서 에러남  2. 번 @Autowired 가 생성되면 자동주입되서에러안남
		System.out.println(ctx.getBean("deleteService")); // 1. nullpointerEx  2. setter에 @Autowired 추가
		
		
		ctx.close();
		System.out.println("\n종료");
	}
}
