import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Solution s = new Solution();
		int[] array = {1, 5, 2, 6, 3, 7, 4};
		int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};	
		
		s.solution(array, commands);
		
	}

}
class Solution {
    public int[] solution(int[] array, int[][] commands) {
    	int start = 0 , end = 0, found = 0, s = 0;
    	int [] aa;
    	int[] answer = {};
    	for(int i = 0; i < commands.length; i++) {

    	aa = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);
    		Arrays.sort(aa);
    		s = aa[found-1];
			System.out.println(s);
			answer[i] = s;
    	}
    	
        return answer;
    }
}


/*
class Solution {
    public int[] solution(int[] array, int[][] commands) {
    	int [] aa;
    	int[] answer = new int[commands.length];
    	for(int i = 0; i < commands.length; i++) {
    	aa = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);
    		Arrays.sort(aa);
    		answer[i] = aa[commands[i][2] -1];	
    	}
    	
        return answer;
    }
}
*/