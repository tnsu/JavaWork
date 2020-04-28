package 배열2.형성평가06;

public class Main {
	public static void main(String[] args) {
		int[][] ar = new int[5][5];
		ar[0][0] = {1,0,1,0,1};
		
		for (int i = 0; i < ar.length; i+=2) {
			ar[1][i] = 1;
			System.out.println(ar[1][i]);
		}
	}
}
