package teamProject;

import java.util.NavigableMap;

import javax.swing.JOptionPane;

public class RandomMove {
	String Movetitle = "랜덤 위치 이동";

	void check(int num) {
		if(num>0) {
			JOptionPane.showMessageDialog(null, "앞으로 "+Math.abs(num)+"칸 이동합니다.", Movetitle,JOptionPane.PLAIN_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "뒤로 "+Math.abs(num)+"칸 이동합니다.", Movetitle,JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public int randomMove() {
		
		String [] arloca = {"1번","2번","3번","4번"};
		String msg = "황금열쇠 발견!\n다음 중 원하는 번호를 선택하면 랜덤으로 위치를 이동합니다.\n※앞으로 이동할 수도, 뒤로 이동할 수도 있습니다.";
		int [] numArr = new int[4];
		int [] negativeNumArr = new int[2];
		int num = 0, flag = 0;
		
    	for(int i=0;i<numArr.length;i++) {
    		num = (int)(Math.random()*10%10+1);
    		for(int j=0;j<i;j++) {
    			if(num == numArr[j])	flag++;
    		}
    		if(flag==0) {
    			numArr[i]=num;
    		}else {
    			i--;
    			flag=0;
    			continue;
    		}
    	}
    	
    	for(int i=0;i<negativeNumArr.length;i++) {
    		num = (int)(Math.random()*10%4);
    		for(int j=0;j<i;j++) {
    			if(num == negativeNumArr[j])	flag++;
    		}
    		if(flag==0) {
    			negativeNumArr[i]=num;
    		}else {
    			i--;
    			flag=0;
    			continue;
    		}
    	}
    	
    	numArr[negativeNumArr[0]] = -numArr[negativeNumArr[0]]; 
    	numArr[negativeNumArr[1]] = -numArr[negativeNumArr[1]];
    	
    	System.out.println();
    	for(int i=0;i<4;i++) {
    		System.out.printf("%d ", numArr[i]);
    	}
    	
		int choice = 0, finalNum = 0;
		while(true) {
			choice = JOptionPane.showOptionDialog(null, msg, Movetitle, JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, arloca, arloca[0]);
			if(choice ==-1) {
				JOptionPane.showMessageDialog(null, "보기 중 하나를 골라주세요.", Movetitle , JOptionPane.ERROR_MESSAGE);
				continue;
			}
			switch(choice) {
			case 0 : 
				check(numArr[0]);
				finalNum = numArr[0];
				break;
			case 1 :
				check(numArr[1]);
				finalNum = numArr[1];
				break;
			case 2 :
				check(numArr[2]);
				finalNum = numArr[2];
				break;
			case 3 :
				check(numArr[3]);
				finalNum = numArr[3];
				break;
			}
		 return finalNum;
		}
	}
}