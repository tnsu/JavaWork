package com.mvn.Javaproj2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

// JUnit 으로 테스트 동작시킬 클래스와 메소드는 모두 public 으로 만들자.

public class AppTest {

	private App app = new App();
	
	@Test
	public void test1() {
//			System.out.println(true);
//		} else {
//			System.out.println(false);
//		}
		// assertXXX() 메소드 사용
		 assertEquals("welcame", app.getWelcome());
		 assertEquals("hello", app.getHello());
		 assertEquals("wbue", app.getBye());
	
	}
	

	
	@Test
	public void test2() {
		assertEquals("Bye", app.getBye());
	}
	
	@Test
	public void test3() {
		assertEquals("hello", app.getHello());
	}
	
	
	
}

