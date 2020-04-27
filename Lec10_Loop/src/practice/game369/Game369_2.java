package practice.game369;
/*
	 1~100 까지의 자연수를 나열하되,
	10개 단위로 줄바꿈을 하고
	숫자에 3,6,9 중 하나라도 있으면 * 표시를 하기
	1  2  *  4  5  *  7  8  *  10
	11
	21
	*   *   *   *   *   *   *   *  *  * 
	*   
 */
public class Game369_2 {
	public static void main(String[] args) {
		
		for(int i = 1; i <= 100; i++) {
			if((i / 10) ==3 || (i / 10) ==6 || (i / 10) ==9) {
				System.out.print("*\t");				
			}else if((i % 10) == 3 || (i % 10) == 6 || (i % 10) == 9 ) {
				System.out.print("*\t");
			}else {
				System.out.print(i +"\t");
			}
			if(i % 10 == 0 ) {
				System.out.println();
			}
		}
		
		
	}// end main
	
}//end class
