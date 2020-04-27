package com.lec.java.while03;

public class While03Main {

	public static void main(String[] args) {
		System.out.println("while 연습");
		
		//1~10 홀수출력
		int i = 1;
		while(i <= 10){
			if(i % 2 != 0) {//(i % 2 == 1)
				System.out.println(i);
			}
			i++;
		}
		
		System.out.println("if 사용 안하기");
		i = 1;
		while (i <= 10) {
			System.out.println(i);
			i += 2;
		} // end while
		
		
		

	} // end main()

} // end class While03Main





























