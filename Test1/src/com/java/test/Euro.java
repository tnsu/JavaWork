package com.java.test;
class Money {

       private String country = "Canada";

       public String getC() { return country; }

 }

class Yen extends Money {

        public String getC() { return super.getC(); }
}

 public class Euro extends Money {

     public String getC(int x) { return super.getC(); }

    public static void main(String[] args) {

       System.out.print(new Yen().getC() 

                         + " " + new Euro().getC());

    }

 }
// private 은 자기자신인 같은 클래스 안에서만 사용할수 있고, 상속도 되지 않는다.
// 해서  private  을  protected 로 바꿔주면 사용할 수 있다.
 
 // return super.country 를 return super.getC(); 해주면 됨
