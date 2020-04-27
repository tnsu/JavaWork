package com.lec.java.collection08;

import java.util.HashSet;
import java.util.Iterator;

public class Collection08Main {

	public static void main(String[] args) {
		System.out.println("HashSet 연습");
		
		// MyClass 타입을 저장할 수 있는 HashSet 인스턴스 생성
		// 데이터 3개 이상 저장해보고 iterator, enahnce-for 등을 
		// 사용하여 출력해보기
		HashSet<MyClass> mySet = new HashSet<MyClass>();
		// 데이터 3개 저장
		MyClass my1 = new MyClass(1, "tnqls");
		MyClass my2 = new MyClass(2, "tnql");
		MyClass my3 = new MyClass(1, "tnqls");
		
		mySet.add(my1);
		mySet.add(my2);
		mySet.add(my3);
		System.out.println("size = " + mySet.size());  
		// 3 개 equals() Hashcode() 가 오버라이딩 안됬을떄는 같은 값을 써도 되지만 
		// equals() Hashcode() 가 오버라이딩 하면 식별이 가능하다.
								
		// 검색: Iterator, enhanced for
		System.out.println();
		System.out.println("Iterator");
		Iterator<MyClass> Iter = mySet.iterator();
		while(Iter.hasNext()) {
			System.out.println(Iter.next());
		}
		System.out.println();
		System.out.println("enhanced for");
		for (MyClass m : mySet) {
			System.out.println(m);
		}	

		// forEach() 메소드 사용
		System.out.println();
		System.out.println("forEach() 사용");
	

		System.out.println("\n프로그램 종료");
	} // end main()
} // end class

