package ex2_1;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DIApp {

	public static void main(String[] args) {
		System.out.println("Main 시작");
		
		// 컨테이너 생성
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		System.out.println("컨테이너 생성");
		
		
		OperatorBean ob = null;
			ob = ctx.getBean("plus", OperatorBean.class);
		int value = ob.doOperate();
		System.out.println("결과 : "+ value);
		
		ob = (MinusOp)ctx.getBean("minus");
		 value = ob.doOperate();
		System.out.println("결과 : "+ value);
		
		OperatorBean operator = null;
		operator = ctx.getBean("op", OperatorBean.class);
		int val = operator.doOperate();
		System.out.println("결과:" + val);
		
		
		ctx.close();
		System.out.println("Main 종료");
	}

}
