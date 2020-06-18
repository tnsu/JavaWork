package com.lec.spring;

public class MessageEng implements MessageBean{
	String msgEng = "Good Morning";

	
	// 생성자 : 언제 생성되는지 예의 주시
	public MessageEng() {
		System.out.println("MessageEng() 생성");
	}


	@Override
	public void sayHello() {
		System.out.println(msgEng);
		
	}
	

}
