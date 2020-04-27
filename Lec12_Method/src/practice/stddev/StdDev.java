package practice.stddev;

import java.util.Arrays;

public class StdDev {
//  175 177 179 181 183 
//81 76 81 55 36    //소수점 2자리까지 출력하기
	public static void main(String[] args) {
		int[] ar = new int[5]; // 5개 자연수

		for (int i = 0; i < ar.length; i++) {
			ar[i] = (int) (Math.random() * 100 + 1);
		} // end for
		System.out.println(Arrays.toString(ar));
		System.out.println("평균 : " + calcAvg(ar));
		System.out.println("분산 : " + calcVariance(ar));
		System.out.printf("표준 편차 : " + calcStdDev(ar));
		// 임의정수 5개로 초기화한 정수로
		// 평균, 분산, 표준편차 구하기

	} // end main

	/**
	 * 메소드 이름 : calcAvg 매개변수 : int [] 리턴값 : double
	 * 
	 * 주어진 배열의 '평균값' 리턴
	 */
	public static double calcAvg(int[] ar) {
		int sum = 0;
		for (int i : ar) {
			sum += i;
		}
		return (double) sum / ar.length;
	}

	/**
	 * 메소드 이름 : calcVariance 매개변수 : int [] 리턴값 : double
	 * 
	 * 주어진 배열의 '분산값' 리턴
	 */ // 변경하기
	public static double calcVariance(int[] ar) {
		double[] ar2 = new double[5];
		for (int i = 0; i < ar.length; i++) {
			ar2[i] = (calcAvg(ar) - ar[i]);
		}
		int sum = 0;
		for (int i = 0; i < ar2.length; i++) {
			sum += (ar2[i] * ar2[i]);
		}

		return (double) sum / ar.length;

	}

	/**
	 * 메소드 이름 : calcStdDev 매개변수 : int [] 리턴값 : double
	 * 
	 * 주어진 배열의 '표준편차' 리턴
	 */
	public static double calcStdDev(int[] ar) {
		return Math.sqrt(calcVariance(ar));
//		Math.pow(calcVariance(arr), 1.0/2.0);
	}// end main

} // end class
