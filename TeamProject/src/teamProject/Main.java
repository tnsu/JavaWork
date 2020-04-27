package teamProject;

import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
		String[]MainMenu = {"게임 시작","종료"};
		String helloMsg = "게임 접속을 환영합니다!";
		GameFrame gf = new GameFrame();
		int mainChoice = 0;
		int [] itemArr = new int[7];
    	int [] quizArr = new int[10];
		int num = 0, flag = 0;
		
    	for(int i=0;i<itemArr.length;i++) {
    		num = (int)(Math.random()*1000%58+1);
    		for(int j=0;j<i;j++) {
    			if(num == itemArr[j])	flag++;
    		}
    		if(flag==0) {
    			itemArr[i]=num;
    		}else {
    			i--;
    			flag=0;
    			continue;
    		}
    	}
    	
    	for(int i=0;i<quizArr.length;i++) {
    		num = (int)(Math.random()*1000%58+1);
    		for(int j=0;j<itemArr.length;j++) {
    			if(num == itemArr[j])	flag++;
    		}
    		for(int j=0;j<i;j++) {
    			if(num == quizArr[j])	flag++;
    		}
    		if(flag==0) {
    			quizArr[i]=num;
    		}else {
    			i--;
    			flag=0;
    			continue;
    		}
    	}
    	
    	for(int i=0;i<itemArr.length;i++) {
    		System.out.printf("%d ", itemArr[i]);
    	}
    	System.out.println();
    	for(int i=0;i<quizArr.length;i++) {
    		System.out.printf("%d ", quizArr[i]);
    	}
		
		while(true) {
			mainChoice=JOptionPane.showOptionDialog(null,helloMsg, "주사위 게임",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,MainMenu,MainMenu[0]);
			if(mainChoice==-1) {
				int result=JOptionPane.showConfirmDialog(null, "게임을 종료하시겠습니까?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
				if(result==JOptionPane.CLOSED_OPTION) {
					System.out.println("메인화면으로 돌아갑니다.");
				}else if(result==JOptionPane.OK_OPTION){
					System.out.println("게임을 종료합니다.");
					break;
				}else {
					System.out.println("메인화면으로 돌아갑니다.");
				}
			}else if(mainChoice == 0) {
				gf.board(itemArr, quizArr);
				break;
			}else {
				JOptionPane.showMessageDialog(null, "게임을 종료합니다.");
				break;
			}
		}
	}
}
