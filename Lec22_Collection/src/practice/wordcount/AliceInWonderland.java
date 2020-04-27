package practice.wordcount;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Map.Entry;
/* 1] 문서(문자열) 안의 단어의 빈도수를 계수해서 출력하기
 * 	- 대소문자 구분하지 않기 :   The 와 the 는 같은 단어
 *  - 2글자 이상만 계수하기
 *  - 구두점/기호 ",.\"\'`!?;:-()" 잘라내기
 *  - 공백 짤라내기
 * ex)
 * 	an : 234
 * 	the : 314
 * ...
 * 
 * hint]
 * 	split("\\s+")  --> String[]   
 * 	--> StringTokenizer  (혹은 정규표현식)
 *  	  --> HashMap<String, Integer>   <단어, 빈도수>  사용
 * ───────────────────────────────────────────────────────────    
 * 2] 빈도수 내림차순으로 정렬하여 출력하기
 * 	ex)
 *	1 the:113개
 *	2 she:95개
 *	3 to:85개
 *	...   
 *
 * hint]
 * 	Comparable<> 이나 Comparator<> 적용
 */

// TODO : 필요한 객체들 작성
// hint> 빈도수 담을 객체, Comparator<> .. 해쉬맵, 
// [a-zA-Z]{2,} 
public class AliceInWonderland {

	public static void main(String[] args) {		
		System.out.println("실습: 단어 발생 빈도");
		HashMap<String, Integer> hmap = new HashMap<String, Integer>(); //
		// 단어별로 나누기
		String [] words = C.ALICE30.trim().toLowerCase().split("\\s+"); // 

		String  words2 = "";
		for (int i = 0; i < words.length; i++) {
			words2 += words[i] + "," ;
		}
		// 발생빈도 작성
//		for (String str : words) {
			StringTokenizer sToken = new StringTokenizer(words2,",.\\\"\\'`!?;:-()_*");
			while(sToken.hasMoreTokens()) {
				String sTken = sToken.nextToken().trim();
				if(sTken.length() >= 2) {
					if(hmap.get(sTken) == null) {
						hmap.put(sTken , 1);
					}else {
						hmap.put(sTken ,hmap.get(sTken)+1 ); 
					}	
				}
			}
		List<String> sList = new ArrayList<String>(hmap.keySet()); // 정렬을 위해 list로 변환
		
		Collections.sort(sList, (o1, o2) -> (hmap.get(o2).compareTo(hmap.get(o1)))); //람다식 표현법 사용

		
		// 결과 출력
		for (int i = 0; i < hmap.size(); i++) {
			System.out.println((i+1) + "번 " + sList.get(i) + " : " +  hmap.get(sList.get(i)) + "개");
		}
		
		System.out.println("\n프로그램 종료");
	} // end main()

} // end class

