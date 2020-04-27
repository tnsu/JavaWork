package com.lec.java.while01;

/*
 	조건식이 true 인 동안 while 블럭 반복
 	
 	while(조건식 true / false){
 		.. 
 		..
 	}
 
 */
public class While01Main {

	public static void main(String[] args) {
		System.out.println("while 반복문");
		//초기 ,조건, 증감식 for ↔ while 은 100% 전환 가능 

		int count =1; //초기식
		while(count <= 10) { //조건식
			System.out.println(count);
			count++; //증감식
		}//end while
		//순서에따라 값이 달라짐 
		count++;
		System.out.println(count); //1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12
		
		System.out.println();
		count = 10;
		while(count > 0 ) { // =>1
			System.out.println(count);
			count--;
		}
		
		

	} // end main()
	
} // end class While01Main




















