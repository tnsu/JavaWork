package teamProject;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Quiz {
	String nonOkMsg="☆☆정답입니다☆☆\n정답 보상으로 앞으로 3칸 이동합니다.";
	String nonNoMsg="★★오답입니다★★\n오답 패널티로 뒤로 4칸 이동합니다.";
	String brainOkMsg="☆☆정답입니다☆☆\n정답 보상으로 앞으로 5칸 이동합니다.";
	String brainNoMsg="★★오답입니다★★\n오답 패널티로 뒤로 3칸 이동합니다.";
	String nonTitle = "넌센스 게임";
	
	int nonOkNum = 3, nonNoNum = -4, brainOkNum = 5, brainNoNum = -3;
	
	//넌센스 퀴즈
	ImageIcon [] nonsenseQuizImgs = {
			new ImageIcon("src/pic/넌센스퀴즈표지.jpg"),
			new ImageIcon("src/pic/넌센스1.png"),
			new ImageIcon("src/pic/넌센스2.png"),
			new ImageIcon("src/pic/넌센스3.png"),
			new ImageIcon("src/pic/넌센스4.png")
	};
	
	ImageIcon [] brainQuizImgs = {
			new ImageIcon("src/pic/문제적남자표지.png"),
			new ImageIcon("src/pic/문제1.jpg"),
			new ImageIcon("src/pic/문제2.jpg"),
			new ImageIcon("src/pic/문제3.jpg"),
			new ImageIcon("src/pic/문제4.jpg"),
			new ImageIcon("src/pic/문제5.jpg"),
			new ImageIcon("src/pic/문제6.png")
		};
		
	int non1() {
		Object []mini1Game1={"맘스터치","롯데리아","버거킹","피자헛"}; //피자헛
		Object Quiz1=null;
		int finalNum = 0;
		while(true) {
			Quiz1=JOptionPane.showInputDialog(null,nonsenseQuizImgs[1], nonTitle, JOptionPane.PLAIN_MESSAGE, null, mini1Game1, mini1Game1[0]);
			if(Quiz1==null) continue;
			if(Quiz1.equals("피자헛")) {
				JOptionPane.showConfirmDialog(null,nonOkMsg,null,JOptionPane.PLAIN_MESSAGE,JOptionPane.PLAIN_MESSAGE,null);
				finalNum = nonOkNum;
				break;
			}else{
				JOptionPane.showConfirmDialog(null,nonNoMsg,null,JOptionPane.WARNING_MESSAGE,JOptionPane.PLAIN_MESSAGE,null);
				finalNum = nonNoNum;
				break;
			}
		}
		return finalNum;
	}
	
	int non2() {
		Object []mini2Game1={"서울대","고려대","가야대","나사렛대"}; //가야대
		Object Quiz2=null;
		int finalNum = 0;
		while(true) {
			Quiz2=JOptionPane.showInputDialog(null, nonsenseQuizImgs[2], nonTitle, JOptionPane.PLAIN_MESSAGE, null, mini2Game1, mini2Game1[0]);
			if(Quiz2==null)continue;	
			if(Quiz2.equals("가야대")) {
				JOptionPane.showConfirmDialog(null,nonOkMsg,null,JOptionPane.PLAIN_MESSAGE,JOptionPane.PLAIN_MESSAGE,null);
				finalNum = nonOkNum;
				break;	
			}else{
				JOptionPane.showConfirmDialog(null,nonNoMsg,null,JOptionPane.WARNING_MESSAGE,JOptionPane.PLAIN_MESSAGE,null);
				finalNum = nonNoNum;
				break;
			}
		}
		return finalNum;
	}
	
	int non3() {
		Object []mini3Game1={"중고차","신차","침수차","녹차"}; //중고차
		Object Quiz3=null;
		int finalNum = 0;
		while(true) {
			Quiz3=JOptionPane.showInputDialog(null, nonsenseQuizImgs[3], nonTitle, JOptionPane.PLAIN_MESSAGE, null, mini3Game1, mini3Game1[0]);
			if(Quiz3==null)continue;
			if(Quiz3.equals("중고차")) {
				JOptionPane.showConfirmDialog(null,nonOkMsg,null,JOptionPane.PLAIN_MESSAGE,JOptionPane.PLAIN_MESSAGE,null);
				finalNum = nonOkNum;
				break;
			}else{
				JOptionPane.showConfirmDialog(null,nonNoMsg,null,JOptionPane.WARNING_MESSAGE,JOptionPane.PLAIN_MESSAGE,null);
				finalNum = nonNoNum;
				break;
			}
		}
		return finalNum;
	}
	
	int non4() {
		Object []mini4Game1={"플로리다","뉴욕","버지니아","텍사스"}; //뉴욕
		Object Quiz4=null;
		int finalNum = 0;
		while(true) {
			Quiz4=JOptionPane.showInputDialog(null, nonsenseQuizImgs[4], nonTitle, JOptionPane.PLAIN_MESSAGE, null, mini4Game1, mini4Game1[0]);
			if(Quiz4==null)continue;
			if(Quiz4.equals("뉴욕")) {
				JOptionPane.showConfirmDialog(null,nonOkMsg,null,JOptionPane.PLAIN_MESSAGE,JOptionPane.PLAIN_MESSAGE,null);
				finalNum = nonOkNum;
				break;
			}else{
				JOptionPane.showConfirmDialog(null,nonNoMsg,null,JOptionPane.WARNING_MESSAGE,JOptionPane.PLAIN_MESSAGE,null);
				finalNum = nonNoNum;
				break;
			}
		}
		return finalNum;
	}
	
	//두뇌게임
	int brain1() {
		int choice=0,answer=6;
		int finalNum = 0;
		while(true) {
			try {
				choice=Integer.parseInt(JOptionPane.showInputDialog(null,brainQuizImgs[1]));
				while(true) {
					if(choice==answer) {
						JOptionPane.showConfirmDialog(null,brainOkMsg,null,JOptionPane.PLAIN_MESSAGE,JOptionPane.PLAIN_MESSAGE,null);
						finalNum = brainOkNum;
						break;
					}else{
						JOptionPane.showConfirmDialog(null,brainNoMsg,null,JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null);
						finalNum = brainNoNum;
						break;
					}
				}
				break;
			}catch(Exception e) {
				continue;
			}
		}
		return finalNum;
	}

	int brain2() {
		int choice=0,answer=5;
		int finalNum = 0;
		while(true) {
			try {
				choice=Integer.parseInt(JOptionPane.showInputDialog(null,brainQuizImgs[2]));
				while(true) {
					if(choice==answer) {
						JOptionPane.showConfirmDialog(null,brainOkMsg,null,JOptionPane.PLAIN_MESSAGE,JOptionPane.PLAIN_MESSAGE,null);
						finalNum = brainOkNum;
						break;
					}else{
						JOptionPane.showConfirmDialog(null,brainNoMsg,null,JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null);
						finalNum = brainNoNum;
						break;
					}
					}
					break;
			}catch(Exception e) {
				continue;
			}
		}
		return finalNum;
	}
	
	int brain3() {
		int choice=0,answer=188;
		int finalNum = 0;
		while(true) {
			try {
				choice=Integer.parseInt(JOptionPane.showInputDialog(null,brainQuizImgs[3]));
				while(true) {
					if(choice==answer) {
						JOptionPane.showConfirmDialog(null,brainOkMsg,null,JOptionPane.PLAIN_MESSAGE,JOptionPane.PLAIN_MESSAGE,null);
						finalNum = brainOkNum;
						break;
					}else{
						JOptionPane.showConfirmDialog(null,brainNoMsg,null,JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null);
						finalNum = brainNoNum;
						break;
					}
				}
				break;
			}catch(Exception e) {
				continue;
			}
		}
		return finalNum;
	}
	
	int brain4() {
		int choice=0,answer=10;
		int finalNum = 0;
		while(true) {
			try {
				choice=Integer.parseInt(JOptionPane.showInputDialog(null,brainQuizImgs[4]));
				while(true) {
					if(choice==answer) {
						JOptionPane.showConfirmDialog(null,brainOkMsg,null,JOptionPane.PLAIN_MESSAGE,JOptionPane.PLAIN_MESSAGE,null);
						finalNum = brainOkNum;
						break;
					}else{
						JOptionPane.showConfirmDialog(null,brainNoMsg,null,JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null);
						finalNum = brainNoNum;
						break;
					}
				}
				break;
			}catch(Exception e) {
				continue;
			}
		}
		return finalNum;
	}
	
	int brain5() {
		String choice=null, answerL="I", answerS="i";
		int finalNum = 0;
		while(true) {
			try {
				choice=JOptionPane.showInputDialog(null, brainQuizImgs[5]);
				while(true) {
					if(choice.equals(answerL) || choice.equals(answerS)) {
						JOptionPane.showConfirmDialog(null,brainOkMsg,null,JOptionPane.PLAIN_MESSAGE,JOptionPane.PLAIN_MESSAGE,null);
						finalNum = brainOkNum;
						break;
					}else{
						JOptionPane.showConfirmDialog(null,brainNoMsg,null,JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null);
						finalNum = brainNoNum;
						break;
					}
				}
				break;
			}catch(Exception e) {
				continue;
			}
		}
		return finalNum;
	}
	
	int brain6() {
		int choice=0,answer=805;
		int finalNum = 0;
		while(true) {
			try {
				choice=Integer.parseInt(JOptionPane.showInputDialog(null,brainQuizImgs[6]));
				while(true) {
					if(choice==answer) {
						JOptionPane.showConfirmDialog(null,brainOkMsg,null,JOptionPane.PLAIN_MESSAGE,JOptionPane.PLAIN_MESSAGE,null);
						finalNum = brainOkNum;
						break;
					}else{
						JOptionPane.showConfirmDialog(null,brainNoMsg,null,JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null);
						finalNum = brainNoNum;
						break;
					}
				}
				break;
			}catch(Exception e) {
				continue;
			}
		}
		return finalNum;
	}
}