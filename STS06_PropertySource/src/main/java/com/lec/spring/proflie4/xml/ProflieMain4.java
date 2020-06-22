package com.lec.spring.proflie4.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ProflieMain4 {
	public static void main(String[] args) {
		
		String config = "run"; // "run" / "dev" 설정
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		
		// proflie 값을 설정해주어야 에러없이 돌아감
		// 현재 활성화할 proflie 이 무엇인지 세팅
		ctx.getEnvironment().setActiveProfiles(config);  // config 이름이 들어간 곳을 로드 해준다
		
		// 해당 proflie 의 설정파일만 설정된당
		ctx.load("appCtx4_dev.xml", "appCtx4_run.xml");
		ctx.refresh();
		
		ServiceInfo info = ctx.getBean("serverInfo", ServiceInfo.class);
		System.out.println("ip : " + info.getIpNum());
		System.out.println("port : " + info.getPortNum());
		
		ctx.close();
	}

}
