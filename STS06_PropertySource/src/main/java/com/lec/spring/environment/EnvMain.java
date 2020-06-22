package com.lec.spring.environment;

import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

public class EnvMain {

	public static void main(String[] args) {
		System.out.println("Main 시작");
		// Context -> Environment -> PropertySources
		ConfigurableApplicationContext ctx = new GenericXmlApplicationContext();
		ConfigurableEnvironment env = ctx.getEnvironment(); // <- ConfigurableApplicationContext  소속 메서드
		MutablePropertySources propertySources = env.getPropertySources(); // 프로퍼티 추가 및 추출 추가 : propertySources.addLast()  추출 : env.getProperty()

		
		//  PropertySources 에 PropertySource 추가
		try {
			// PS를 하나를  생성해서 propertySources 에 담는다(추가한다) : addLast <-- 끝에 추가
			propertySources.addLast(new ResourcePropertySource("classpath:admin.auth"));
			
			
			/*
			 	이제, Environment 를 통해  원하는 Property 에 접근 가능
			 	굳이 '어느 PropertySources' 의 '어느 Property' 를 요청할 필요가 없다.
			 	'어느 Property' 에 대한것만 요청하면
			 	PropertySources 에 소속된 모든 PropertySources 들을 다 스캔하면서 찾아낸다
			 */
			
			System.out.println(env.getProperty("admin.id"));
			System.out.println(env.getProperty("admin.pw"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// ctx.load() 안됨 
		GenericXmlApplicationContext gCtx = (GenericXmlApplicationContext)ctx;
		 gCtx.load("classpath:appCtx1.xml"); // 설정로딩
		 gCtx.refresh(); // 빈생성
		 
		 AdminConnection adminConnection = gCtx.getBean("adminConnection", AdminConnection.class);
		 System.out.println("admin ID: " + adminConnection.getAdminId());
		 System.out.println("admin PW: " + adminConnection.getAdminPw());
		 
		 
		 gCtx.close();
	}

}
