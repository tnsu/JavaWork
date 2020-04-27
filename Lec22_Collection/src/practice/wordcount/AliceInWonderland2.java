package practice.wordcount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

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

// by 장윤성
public class AliceInWonderland2 {

	public static void main(String[] args) {
		System.out.println("실습: 단어 발생 빈도 (내림차순) ");
		String[] words = C.ALICE30.trim().toLowerCase().split("\\W+|\\_|\\t|\\n|\\d+|\\s+|\\r");

		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		List<word> word = new ArrayList<word>();
		// 발생빈도 작성
		for (int i = 0; i < words.length; i++) {
			Integer v = hmap.get(words[i]); // words[i] 가 key
			if (words[i].length() > 1) {
				// 기존에 해당 key 값이 없었다면 ( 즉 첫 등장이면)
				if (v == null) {
					hmap.put(words[i], 1);
				} else {// 기존 등장횟수에 +1 증가
					hmap.put(words[i], v + 1);
				}

			}
		}
		for (Map.Entry<String, Integer> e : hmap.entrySet()) {
			word.add(new word(e.getKey(), e.getValue()));
		}
		Collections.sort(word);
		for (word words1 : word) {
			System.out.println(words1.word + " : " + words1.count + "개" );
		}
			
		
		System.out.println("\n프로그램 종료");


	} // end main()

	 

} // end class

class word implements Comparable<word> {
	String word;
	int count;

	public word() {
		super();
	}

	public word(String word, int count) {
		super();
		this.word = word;
		this.count = count;
	}

	@Override
	public int compareTo(word o) {

		// 점수 내림차순
		if (o.count < this.count)
			return -1; // 내가 더 작으면 음수
		if (this.count < o.count)
			return 1; // 내가 더 크면 양수
		return 0; // 같으면 0

	}

}
