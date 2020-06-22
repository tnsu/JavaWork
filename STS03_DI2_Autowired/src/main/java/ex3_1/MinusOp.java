package ex3_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MinusOp implements OperatorBean {
	@Autowired
	@Qualifier("operand1")
	Operand operand1;
	@Autowired
	@Qualifier("operand2")
	Operand operand2;
	
	
	public MinusOp() {
		super();
		System.out.println("MinusOp () 생성");
	}


	public MinusOp(Operand operand1, Operand operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}


	public Operand getOperand1() {
		return operand1;
	}


	public void setOperand1(Operand operand1) {
		this.operand1 = operand1;
	}


	public Operand getOperand2() {
		return operand2;
	}


	public void setOperand2(Operand operand2) {
		this.operand2 = operand2;
	}
	@Override
	public int doOperate() {
		return operand1.getValue()- operand2.getValue();
	}
	@Override
	public String toString() {
		return String.format("[MinusOp : %s,%s]",operand1, operand2);
	}

}
