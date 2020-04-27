package com.lec.java.regexp03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 정규표현식 에 사용하는 각종 표현식들
 * 	정규표현식		설명
 * 	^			문자열 시작
 * 	$			문자열 종료
 * 	.			임의의 문자 [단 ‘'는 넣을 수 없습니다.]
 * 	*			앞 문자가 0개 이상의 개수가 존재할 수 있습니다.
 * 	+			앞 문자가 1개 이상의 개수가 존재할 수 있습니다.
 * 	?			앞 문자가 없거나 하나 있을 수 있습니다.
 * []			문자의 집합이나 범위를 표현합니다. -기호를 통해 범위를 나타낼 수 있습니다. ^가 존재하면 not을 나타냅니다.
 * {}			횟수 또는 범위를 나타냅니다.
 * ()			괄호안의 문자를 하나의 문자로 인식합니다. (그룹)
 * |			패턴을 OR 연산을 수행할 때 사용합니다.
 * \s			공백 문자
 * \S			공백 문자가 아닌 나머지 문자
 * \w			알파벳이나 숫자
 * \W			알파벳이나 숫자를 제외한 문자
 * \d			[0-9] 숫자
 * \D			숫자를 제외한 모든 문자
 * (?i)			대소문자를 구분하지 않습니다.
 * 
 * 
 * 자바 정규표현식에 사용되는 escaped character 들
 *    \.[]{}()<>*+-=!?^$|
 */
public class RegExp03Main {

	public static void main(String[] args) {
		System.out.println("정규표현식\n");

		String regex, intput, title;
		String [] arrInput;
		
		//─────────────────────────────────────────
		title = "^ : 바로 문자뒤의 문자열로 시작됨";
		regex = "^The"; //The 로 시작하는 문자열 패턴
		arrInput = new String[] {
				"The Things",
				"On The Things", //X
				" The The The", // 공백이 있어서 X 
				"The The the The" // 하나만 발생.  매치1: The {0~3}
		};
	
		//─────────────────────────────────────────
		title = "$ : 문자열의 마지막이 이 문자열로 마무리 됨";
		regex = "Man$"; // Man으로 끝나는 문자열 패턴 
		arrInput = new String[] {
				"SuperMan",
				"AquaMan",
				"WonderWoman",// 소문자이므로 X
				"WonderWoMan",
				"PostMan " // 공백 X
				
		};
			
		//─────────────────────────────────────────
		title = "^표현식$ : 정확하게 전체패턴매칭되는 문자열";
		regex = "^Su...Man$";
		arrInput = new String[] {
				"SuperMan",
				"SugerMan",
				"Super Man",
				" SuperMan",
				"SuperMan "
		};
		//─────────────────────────────────────────
		title = " . : 어떤 문자든지 임의의 '한문자'를 말한다.꼭 하나의 문자와 매칭";
		regex = "x.y"; 
		arrInput = new String[] {
				"xyz",
				"xxzdfdk",
				"aa10x9zbxbz",
				"xz",
				"90x zxx_zdf",  // 공백도 하나의 문자다! 매칭된다
				"xbz",				
				"xyyz x1y xyyy xxxyyyxyxxyxxxyxyxxyxy"  // o 
		};
		
		//─────────────────────────────────────────
		title = " * : 바로 앞의 문자가 없거나 한개 이상의 경우를 매칭";
		regex = "ab*";
		arrInput = new String[] {
				"a",
				"abc", //ab 매칭
				"ab",
				"abbbaaaabababbab", // 8개
				"bbba",
				"cdef"
		};
		
		//─────────────────────────────────────────
		title = " + : 바로 앞의 문자를 나타내면 꼭 한개 혹은 그 이상을 매칭";
		regex = "ab+";
		arrInput = new String[] {
				"a",  //x
				"abc",  
				"ab",
				"abbbaaaabababbab", // 5새
				"bbba", // x
				"cdef" 
		
		};
		
		//─────────────────────────────────────────
		title = " ? : 바로 앞의 문자가 한개 있거나 없는것을 매칭";
		regex = "ab?"; 
		arrInput = new String[] {
				"a", // 1
				"abc", // 1
				"kkabcc", // 1
				"abbbaaaabababbab", // 8
				"bbba" // 1
		};

		//─────────────────────────────────────────
		title = " [] : 안에 존재하는 문자들중 한 문자만을 매칭";
		regex = "[abc]"; // a 또는 b 또는 c 중의 한 문자에 매칭 
		arrInput = new String[] {
				"able",
				"bible",
				"cable",
				"xenosys"
		};
		
		regex = "[abc]+";  // ab, b b, cab, 없음
		regex = "[a-z]+";  // a~z 까지 한개이상 
		arrInput = new String[] {
				"abc100",
				"abcDefGHIUJ-KLM123opQrstuz"
			};
		regex = "[a-zA-Z]+"; // 숫자나 기호가 있어서 나눠짐 
		regex = "[a-zA-Z0-9]+";
		regex = "[a-zA-Z0-9-]+";  // - 추가해서 출력됨
		/*
		arrInput = new String[] {
				"23",
				"0", // 양의정수
				"-10",
				"012",// 자연수 아님
				"111324"
			};
		regex = "^[1-9][0-9]*$"; //자연수에 대한 정규표현식
		//─────────────────────────────────────────
		title = " {} : 앞에 있는 문자나 문자열의 등장개수를 정함";
		regex = "ab{2}";  // a 로시작 b 가 두개 매칭되있음
		arrInput = new String[] {
				"abb",
				"abbb",
				"abbbabbbbbbbbabaabab",
		};
		regex = "ab{2,}"; // b갯수가 두개 이상
		regex = "ab{3,5}"; // b갯수가 3~5까지
		//─────────────────────────────────────────
		title = " () : ()안에 있는 글자들을 그룹화 ";
		regex = "a(bc)*";  //(bc)가 없거나 하나이상
		arrInput = new String[] {
				"abc",
				"abcbcbbc", //abcbc 
				"abcabcabc",
				"a"
		};
		
		//─────────────────────────────────────────
		title = " | : OR 연산자  역할";
		regex = "a|b"; // a 또는 b 둘중 하나
		arrInput = new String[] {
				"a",
				"b",
				"ab",
				"xyz",
				"abcccbaacbaabacbbcacbbaabcccbaabcabcb"
		};
		regex = "(a|b)+";
		//─────────────────────────────────────────
		title = "(?i)  : 대소문자 구분안하고 매칭 ";  // 타 언어 정규표현식과 다름
		regex = "(?i)abc"; 
		arrInput = new String[] {
				"abc",
				"ABC",
				"Abc"
		};
		
		//─────────────────────────────────────────
		title = "\\s : 공백,  \\S : 공백아닌 문자";
		regex = "\\s+"; // 공백(스페이스바,줄바꿈,탭) 한개이상
		arrInput = new String[] {
				"Hello My World",
				"He \tllo My World", // 3
				"\n\t Hello My World\n\n" // 4
		};
		regex = "\\S+";
		
		//─────────────────────────────────────────
		title = "\\w : 알파벳이나 숫자, \\W 알파벳이나 숫자를 제외한 문자";
		regex = "\\w+"; 
		arrInput = new String[] {
				"This is 2020-03-23 !!",
				
		};
		regex = "\\W+";

		//─────────────────────────────────────────
		title = "\\d : [0-9] 숫자, \\D 숫자를 제외한 모든 문자";
		regex = "\\d+"; 
		arrInput = new String[] {
				"This is 2020-03-23 !!"
		};
		regex = "\\D+";
				
		
		//─────────────────────────────────────────
		title = "escaped character 매칭 시키기";
		regex = "[.]+";
		arrInput = new String[] {
				"My name is ..."
		};
	*/
		//*****************************************
		// 패턴매칭 수행
		System.out.println(title);
		regExpTest(regex, arrInput);

		System.out.println("프로그램 종료");
	} // end main()
	
	// 도우미 함수
	public static void regExpTest(String regex, String [] arrInput) {
		for(String input : arrInput) regExpTest(regex, input);
	}
	
	public static void regExpTest(String regex, String input) {
		System.out.println("[정규표현식 매칭 테스트]-----------------");
		System.out.println("정규표현식: " + regex);
		System.out.println("입력문자열: " + input);
		
		Matcher matcher = Pattern.compile(regex).matcher(input);
		int groupCount = matcher.groupCount();  // 그룹 개수
		
		int matchCount = 0;		
		while(matcher.find()) {
			matchCount++;
			System.out.println("    매치" + matchCount + ": " + matcher.group() + " {" + matcher.start() + "~" + matcher.end() + "}");
			
			// 그룹이 있으면 group별 출력
			if(groupCount > 0) {
				for(int i = 0; i <= groupCount; i++) {	 // i 범위 주목!	
					System.out.printf("\t group(%d): %s {%d~%d}\n",
							i, matcher.group(i), matcher.start(i), matcher.end(i));
				}
			}
			
		} // end while
		if(matchCount == 0) System.out.println("   Ⅹ매치 없슴Ⅹ");
		System.out.println();
	} // end regExpTest()

} // end class
