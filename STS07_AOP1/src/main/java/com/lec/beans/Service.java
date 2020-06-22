package com.lec.beans;

public abstract class Service {
	public abstract void doAction();// 추상메서드
	
	//테스트용 : 메소드 호출정보 출력
	public void printInfo() {
		String className = this.getClass().getSimpleName(); 
		StackTraceElement[] stackTrace = new Throwable().getStackTrace();
		String methodName = stackTrace[1].getMethodName(); // 현재실행되고 있는 메소드 이름 1 하면 printInfo 를 호출한 쪽의 이름(실행중인 메소드의 이름)이 나오게된다.
		System.out.println(className +" 의 "+ methodName + "() 호출");
	}
}
