package com.lec.java.printf;
/* 서식화된 문자열 (formatted string)
 *  ● 화면에 출력할때는 -> printf() :줄바꿈을 안함
 *    printf : print with format
 *    printf("서식문자열", 값1, 값2....)
 *    
 *  ● 문자열(String)으로 만들때는 -> String.format()
 *       	
 *    String.format("서식문자열", 값1, 값2....)
 *   
 *    '서식문자열' 안에는 '서식지정자'들을 씀
 *    
 *  ● format specifier (서식 지정자)
 *  	%d  :  10진수 정수로 출력
 *  	%f  :  실수 출력 (소수점6자리)
 *  	%s  :  문자열 출력
 *  	%c  :  '문자하나' 출력
 *  	%x  :  16진수 정수로 출력
 *  	%%  :  % 출력
 *  
*/
public class PrintFormatMain {

	public static void main(String[] args) {
		System.out.println("서식화된 출력 : printf() String.format()");
		
		double pi = Math.PI; // 원주율 값
		System.out.println(pi);
		System.out.printf("원주율 %f\n", pi);
		System.out.printf("원주율 %.2f\n", pi); //소수점 2자리까지만 반올림해서 출력 
		
		int age = 10;
		short grade = 3;
		System.out.printf("제 나이는 %d살 이고, 학년은 %d학년 입니다.\n", age, grade);
		
		//소수점 이하 제한
		double height = 182.3;
		System.out.printf("저는 %d살 이고, 키는 %fcm입니다.\n" , age, height);
		System.out.printf("저는 %d살 이고, 키는 %.1fcm입니다.\n" , age, height);
		
		//출력폭 지정, 좌우 정렬
		System.out.printf("|%d|%d|%d|\n", 100, 200, 300);
		System.out.printf("|%5d|%5d|%5d|\n", 100, 200, 300); // 우측정렬(기본) %5d 5칸에 맞춰서 실행됨 
		System.out.printf("|%-5d|%-5d|%-5d|\n", 100, 200, 300); // 좌측정렬
		
		System.out.printf("제이름은 [%10s]이고, 혈액형은 %c형 입니다.\n", "한수빈", 'B');
		
		// % 출력 
		double rate = 134423.0 / 345678.0;
		System.out.printf("합격률은 %.1f%% 입니다.\n", rate);
		
		
		// 숫자의 패딩
		System.out.printf("|%05d|%05d|%05d|\n", 100, 200, 300);// 비어있는 부분이 0으로 채워짐
		
		// format 문자열을 따로 설정해서 운영
		String fmt = "주소 : %s, 우편번호 [%05d]";
		System.out.printf(fmt + "\n", "서울", 12345);
		System.out.printf(fmt + "\n", "광주", 44);
		System.out.printf(fmt + "\n", "대구", 776);
		
		System.out.println();
		
		//String.format()
		String.format("합격률은 %.1f%%입니다.", rate); // 화면 출력용이 아님, 문자열로만 바꿔줌
		String result =  String.format("합격률은 %.1f%%입니다.", rate); 
		System.out.println(result);
		
	}// end main()

}// end class



