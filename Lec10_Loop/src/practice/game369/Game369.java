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
public class Game369 {
	public static void main(String[] args) {

		int num = 1;
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {

				if ((num / 10) == 3 || (num / 10) == 6 || (num / 10) == 9) {
					System.out.print("*\t");
				} else if ((num % 10) == 3 || (num % 10) == 6 || (num % 10) == 9) {
					System.out.print("*\t");
				} else {
					System.out.print(num + "\t");
				}
				num++;
			}//end for(j)
			System.out.println();
		}// end for(i)

	}// end main

}// end class
