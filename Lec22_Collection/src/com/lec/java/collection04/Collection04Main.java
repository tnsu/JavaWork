package com.lec.java.collection04;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Collection04Main {

	public static void main(String[] args) {
		System.out.println("ArrayList 연습");
		// MemberModel 타입을 담는 ArrayList를 만드고
		// 사용자로부터 3개의 Student 데이터 을 입력받아서
		//        (id, passwd)
		// 3가지 방법으로 출력해보세요. 
		// for, Enhanced-for, Iterator
		Scanner sc = new Scanner(System.in);
		ArrayList<MemberModel> listm = new ArrayList<MemberModel>();
		for (int i = 0; i < 3; i++) {
			System.out.print("id : ");
			String id = sc.nextLine();
			System.out.print("pw : ");
			String pw = sc.nextLine();
			
			MemberModel mM = new MemberModel(id, pw);
			listm.add(mM);
		}
		sc.close();
		
		System.out.println("Enhanced-for 를 사용한 출력");
		for (MemberModel m : listm) {
			m.displayInfo();
		}
		
		System.out.println("for 를 사용한 출력");
		for (int i = 0; i < listm.size(); i++) {
			listm.get(i).displayInfo();
		}
		
		System.out.println("Iterator 사용:");
		Iterator<MemberModel> Iter = listm.iterator();
		while(Iter.hasNext()) {
			Iter.next().displayInfo();
		}
		
		
		

		System.out.println("\n프로그램 종료");
	} // end main()

} // end class












