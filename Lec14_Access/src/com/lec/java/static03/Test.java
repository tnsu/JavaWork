package com.lec.java.static03;

public class Test {
	private int num;  
	static int count = 0;
	
	// 기본생성자 
	private Test(){
		count++;
		System.out.println(count+ "번째 인스턴스 생성");
	}
	
	
	//인스턴스를 하나만 제공할수 있도록 만든다.
	private static Test instance = null;  // 레퍼런싱타입
	public static Test getInstance() {
		if(instance == null) {
			instance = new Test(); // 인스턴스 생성
		}
		return instance;
	}

	// getter & setter
	public int getNum() {return num;}
	public void setNum(int num) {this.num = num;}

	//private 로 선언하는 이유 클래스의 생성자를 외부에서 호출할수 없도록하기위함
	// getInstance() 메서드를 사용하여  test 인스턴스가 이미 생성되어 있는지를 검사하고 생성되지 않은 상호아이라면 생성자를 호출해
	// 인스턴스를 생성하고, 이미 생성되있다면 정적변수 num 변수를 참조하는 인스턴스를 반환

	//어떤 클래스가 최초 한번만 메모리를 할당하고(Static) 그 메모리에 인스턴스를 만들어 사용하는 디자인패턴.



}













