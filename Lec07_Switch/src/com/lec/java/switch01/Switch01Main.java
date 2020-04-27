package com.lec.java.switch01;

/* 
 switch - case 조건문
 
 	switch (조건값){
 	case 값1:
 		...
 	case 값2:
 		...
	default:
		...
	}

 	해당 조건의 case문을 찾아서 거기서부터 break를 만날 때까지 실행을 함.
 break를 만나게 되면 switch 문장을 종료.
 해당하는 case가 없으면 default 문장을 실행함.
  
  	※ 모든 switch 조건문은 if - else if - else로 바꿀 수 있다. (할수 있어야 한다)
 */
public class Switch01Main {

	public static void main(String[] args) {
		System.out.println("switch 문");

		int num = 3;
		switch (num) {
		case 1:
			System.out.println("하나");
			System.out.println("One");
			break; //switch 안에서의 수행 종료.
		case 2:
			System.out.println("둘");
			System.out.println("Two");
			break;
		case 3:
			System.out.println("셋");
			System.out.println("THREE");
			break;
		default:
			System.out.println("이도 저도 아님");
			break;
		}
		
		
		System.out.println();
		// 모든 switch 조건문 if - else if - else로 바꿀 수 있다. but if문을 switch문으로 바꿀 수 없음
		//성능은 미세한 차이로  switch문이 더 빠름. if 문은 조건을 하나씩 다 비교. switch문은 조건만 바로 비교함
		if(num ==1) {
			System.out.println("하나");
			System.out.println("One");
		}else if(num == 2) {
			System.out.println("둘");
			System.out.println("Two");
		}else if(num == 3) {
			System.out.println("셋");
			System.out.println("THREE");
		}else {
			System.out.println("이도 저도 아님");
		}
		
		
		
		
		System.out.println("\n프로그램 종료");
	} // end main()

} // end class










