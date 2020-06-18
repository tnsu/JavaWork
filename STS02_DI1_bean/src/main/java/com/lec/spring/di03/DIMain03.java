package com.lec.spring.di03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.lec.spring.Score;

public class DIMain03 {
	public static void main(String[] args) {
		System.out.println("Main 시작");
		
		// ApplicationContext (컨테이너 생성), 빈(bean) 도 자동 생성
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:scoreCtx.xml");
		
		System.out.println("컨테이너 생성]");
		
		Score score = null;
		
		score = ctx.getBean("myScore1", Score.class);
		System.out.println(score);
		
		// getBean(빈 id) 는 Object 리턴. 형변환해서 사용 가능하다
		score = (Score)ctx.getBean("myScore2"); // Object타입으로 변환되기 떄문에 형변환해서 사용
		System.out.println(score);
		
		score = ctx.getBean("myScore3", Score.class);
		System.out.println(score);
		
		score = ctx.getBean("myScore4", Score.class);
		System.out.println(score);
		
		score = ctx.getBean("myScore5", Score.class);
		System.out.println(score);
		
		score = ctx.getBean("myScore6", Score.class);
		System.out.println(score);
		
		score = ctx.getBean("myScore7", Score.class);
		System.out.println(score);
		
		score = ctx.getBean("myScore8", Score.class);
		System.out.println(score);
		
		System.out.println("생성된 Bean 의 개수 : " + ctx.getBeanDefinitionCount());
		for (int i = 1; i <= ctx.getBeanDefinitionCount(); i++) {
			System.out.println(ctx.getBean("myScore"+i)); // 오브젝트의 ㄴㅅ갸ㅜㅎ 
		}
		ctx.close();
		System.out.println("Main 종료");
	}
}
