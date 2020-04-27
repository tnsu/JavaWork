package 반복제어문2.자가진단07;

public class Main {

	public static void main(String[] args) {
		
		for(int i = 2; i <= 6; i++) { // 세로줄
			for (int j = 0; j < 5; j++) { // 가로줄 
				System.out.print((i+j) + " ");
			} // for 2
			System.out.println();
		}// for 1
	}// end main

}//end class
