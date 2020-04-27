package teamProject;

import javax.swing.JOptionPane;

public class EventPlace {
	
	public void exceptCheck(int place) {
		GameFrame gf = new GameFrame();
		if(place >= 59) {
			gf.diceMove(59);
			JOptionPane.showMessageDialog(null, "도착!\n축하합니다!", "Game Clear",
					JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}else {
			gf.diceMove(place);
			for(int i=0;i<gf.itemArr_g.length;i++) {
				if(place==gf.itemArr_g[i]) {
					apply(place, gf.itemArr_g, gf.quizArr_g);
				}
			}
			for(int i=0;i<gf.quizArr_g.length;i++) {
				if(place==gf.quizArr_g[i]) {
					apply(place, gf.itemArr_g, gf.quizArr_g);
				}
			}
		}
	}
	
	public int apply(int place, int [] itemArr, int [] quizArr) {
		GameFrame gf = new GameFrame();
		Quiz q = new Quiz();
		RandomMove s = new RandomMove();
		String itemTitle = "황금열쇠";
		int quizMove = 0;
    	
    	//황금열쇠 처리부분
		if(place == itemArr[0]) {	//랜덤위치 이동
			gf.diceMove(place);
			place += s.randomMove();
			if(place<0) {
				place = 0;
			}
			exceptCheck(place);
		}else if(place == itemArr[1]) {	//앞으로 3칸 이동
			gf.diceMove(place);
			JOptionPane.showMessageDialog(null, "황금열쇠 발견!\n앞으로 3칸 이동합니다.", itemTitle,
					JOptionPane.PLAIN_MESSAGE);
			place += 3;
			exceptCheck(place);
		}else if(place == itemArr[2]) {	//앞으로 5칸 이동
			gf.diceMove(place);
			JOptionPane.showMessageDialog(null, "황금열쇠 발견!\n앞으로 5칸 이동합니다.", itemTitle,
					JOptionPane.PLAIN_MESSAGE);
			place += 5;
			exceptCheck(place);
		}else if(place == itemArr[3]){	//뒤로 2칸 이동
			gf.diceMove(place);
			JOptionPane.showMessageDialog(null, "황금열쇠 발견!\n뒤로 2칸 이동합니다.", itemTitle,
					JOptionPane.ERROR_MESSAGE);
			place -= 2;
			exceptCheck(place);
		}else if(place == itemArr[4]){	//뒤로 6칸 이동
			gf.diceMove(place);
			JOptionPane.showMessageDialog(null, "황금열쇠 발견!\n뒤로 6칸 이동합니다.", itemTitle,
					JOptionPane.ERROR_MESSAGE);
			place -= 6;
			exceptCheck(place);
		}else if(place == itemArr[5] || place == itemArr[6]){	//주사위 반만큼만 이동
			gf.diceMove(place);
			JOptionPane.showMessageDialog(null, "황금열쇠 발견!\n다음 턴에 나온 주사위의 반만큼만 이동합니다.\n예) 주사위 숫자가 1, 2는 1칸, 3, 4는 2칸, 5, 6은 3칸",
					itemTitle, JOptionPane.ERROR_MESSAGE);
			gf.cnt++;
		}
		//넌센스게임 처리부분
		else if(place == quizArr[0]){
			gf.diceMove(place);
			place += q.non1();
			exceptCheck(place);
		}else if(place == quizArr[1]){
			gf.diceMove(place);
			place += q.non2();
			exceptCheck(place);
		}else if(place == quizArr[2]){
			gf.diceMove(place);
			place += q.non3();
			exceptCheck(place);
		}else if(place == quizArr[3]){
			gf.diceMove(place);
			place += q.non4();
			exceptCheck(place);
		}
		//두뇌게임 처리부분
		else if(place == quizArr[4]){
			gf.diceMove(place);
			place += q.brain1();
			exceptCheck(place);
		}else if(place == quizArr[5]){
			gf.diceMove(place);
			place += q.brain2();
			exceptCheck(place);
		}else if(place == quizArr[6]){
			gf.diceMove(place);
			place += q.brain3();
			exceptCheck(place);
		}else if(place == quizArr[7]){
			gf.diceMove(place);
			place += q.brain4();
			exceptCheck(place);
		}else if(place == quizArr[8]){
			gf.diceMove(place);
			place += q.brain5();
			exceptCheck(place);
		}else if(place == quizArr[9]){
			gf.diceMove(place);
			place += q.brain6();
			exceptCheck(place);
		}else {
			exceptCheck(place);
		}
		return place;
	}
}