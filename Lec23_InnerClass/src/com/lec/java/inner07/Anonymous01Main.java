package com.lec.java.inner07;

/*
  Anonymous inner class(익명 내부 클래스):
   이름이 없는 local inner class
   이름이 없기 때문에 생성자를 만들 수가 없습니다.
   클래스의 정의와 동시에 인스턴스를 생성합니다.
*/
public class Anonymous01Main {

	public static void main(String[] args) {
		System.out.println("Anonymous Inner Class(익명 내부 클래스)");
		Person p = new Person("헐크");
		Readable r = p.createInstance(55);
		r.readInfo();
		
		Readable r2 = new Readable() {
			
			@Override
			public void readInfo() {
				System.out.println("r2 의 readInfo 입니다");	
			}
		};
		r2.readInfo();
		Readable r3 = new Readable() {
			
			@Override
			public void readInfo() {
				System.out.println("r3 의 readInfo 입니다");	
			}
		};
		r3.readInfo();

		new Readable() {
			@Override
			public void readInfo() {
				System.out.println("new Readable() 로 바로만듬");
			}
		}.readInfo();
		
		Movable m1 = new Movable() {
			
			@Override
			public void move(int times) {
				System.out.println("move() " + times * speed);
				
			}
		};
		m1.move(2);
		m1.move(10);
		
		Myclass my1 = new Myclass();
		my1.aaa(); my1.bbb();

		Myclass my2 = new Myclass() {
			//추상 메서드는 없으나 인위적으로 오버라이드 가능함
			@Override
			public void aaa() {
				System.out.println("AAA ");
			}
		};
		my2.aaa();
		
		
		Myclass my3 = new Myclass() {

			@Override
			public void aaa() {
				
			}

			@Override
			public void bbb() {
				
			}
			 
		};
		
		
		
		
	} // end main()

} // end class Anonymous01Main

abstract class Movable {
	int speed = 100;
	public abstract void move(int times);
}

class Myclass{
	public void aaa() {
		System.out.println("aaa");
	}
	public void bbb() {
		System.out.println("bbb");
	}
}












