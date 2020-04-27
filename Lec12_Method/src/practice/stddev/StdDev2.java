package practice.stddev;

// 김준영.
public class StdDev2 {

	public static void main(String[] args) {
		// 평균, 분산, 표준편차 구하기
		int[] arr = new int[5];
		for (int i = 0; i < arr.length; i++) {
			int num = (int) (Math.random() * 100) + 1;
			arr[i] = num;
		}
		for (int value : arr) {
			System.out.print(value + " ");
		}

		System.out.println();
		System.out.println("평균 : " + calcAvg(arr));
		System.out.println("분산 : " + String.format("%.2f", calcVariance(arr)));
		System.out.println("표준편차 : " + calcStdDev(arr));

	} // end main

	/**
	 * 메소드 이름 : calcAvg 매개변수 : int [] 리턴값 : double
	 * 
	 * 주어진 배열의 '평균값' 리턴
	 */
	public static double calcAvg(int[] arr) {
		double avg = 0;
		int sum = 0;
		for (int value : arr) {
			sum += value;
		}
		return avg = (double) sum / arr.length;
	}

	/**
	 * 메소드 이름 : calcVariance 매개변수 : int [] 리턴값 : double
	 * 
	 * 주어진 배열의 '분산값' 리턴
	 */
	// 분산 -> 편차제곱의 평균
	// 편차 -> 변량 - 평균
	public static double calcVariance(int[] arr) {
		double[] va = new double[5];
		for (int i = 0; i < arr.length; i++) {
			double va1 = arr[i] - calcAvg(arr);
			va[i] = Math.pow(va1, 2);
		}
		double total = 0;
		for (int i = 0; i < va.length; i++) {
			total += va[i];
		}
		return total / arr.length;
	}

	/**
	 * 메소드 이름 : calcStdDev 매개변수 : int [] 리턴값 : double
	 * 
	 * 주어진 배열의 '표준편차' 리턴
	 */
	public static double calcStdDev(int[] arr) {
		return Math.sqrt(calcVariance(arr));
		// Math.pow(calcVariance(arr), 1.0/2.0);
	}
} // end class
