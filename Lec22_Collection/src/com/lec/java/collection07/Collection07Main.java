package com.lec.java.collection07;

import java.util.HashSet;
import java.util.Iterator;

public class Collection07Main {

	public static void main(String[] args) {
		System.out.println("HashSet 연습");
		
		// String 타입을 저장할 수 있는 HashSet 를 생성하고
		// 5개 이상의 데이터는 저장, 수정, 삭제 등의 동작을 해보고,
		// iterator, enhanced-for 문을 사용해서 출력해보기
		HashSet<String> sSet = new HashSet<String>();
		sSet.add("저장");
		sSet.add("삭제");
		sSet.add("검색");
		sSet.add("입력");
		sSet.add("변경");
		sSet.add("제공");
		System.out.println("Iterator 사용");
		Iterator<String> Iter = sSet.iterator();
		while(Iter.hasNext()) {
			System.out.println(Iter.next());
		}
		System.out.println();
		
		sSet.remove("삭제");
		sSet.add("delete");
		System.out.println("수정 후 ");
		System.out.println("enhanced-for 사용");
		for (String s : sSet) {
			System.out.println(s);
		}
		System.out.println();
		
		sSet.remove("변경");
		Iter = sSet.iterator();
		System.out.println("삭제 후 ");
		System.out.println("Iterator 사용");
		while(Iter.hasNext()) {
			System.out.println(Iter.next());
		}
		
		
		System.out.println("\n프로그램 종료");
	} // end main()

} // end class
















