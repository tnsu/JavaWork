package com.lec.java.file14;

import java.nio.charset.Charset;

public class File14Main {

	public static void main(String[] args) {
		System.out.println("시스템 정보 확인");
		System.out.println();
		
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("os.arch")); // 64bit
		System.out.println(System.getProperty("os.version"));
		
		System.out.println();
		System.out.println(System.getProperty("java.home")); // 자바 가상머신)JRE 경로 : Java Runtime Environment <-- JVM 
		System.out.println(System.getProperty("java.version"));
		
		System.out.println();
		// current working directory
		System.out.println(System.getProperty("user.dir")); // 현재 작업경로
		
		// user home directory("내 문서"가 있는 폴더)
		System.out.println(System.getProperty("user.home"));
		
		System.out.println();
		System.out.println(System.getProperty("file.separator"));
		
		// OS 기본 인코딩 값
		System.out.println("Defualt Charset : " + Charset.defaultCharset());
		System.out.println("file.encoding : " + System.getProperty("file.encoding"));


		System.out.println("\n프로그램 종료");
		
	} // end main()

} // end class














