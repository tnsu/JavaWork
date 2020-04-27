package teamProject;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameFrame {
	EventPlace ep = new EventPlace();
	
	int [][] cPlace = {
			{85,25}, {165,22}, {239,22}, {312,22}, {384,22}, {456,22}, {529,22}, {602,22}, {674,22}, {747,22},
			{819,30}, {870,84}, {857,155}, {789,176}, {717,176}, {644,176}, {571,176}, {498,176}, {426,176}, {353,176},
			{280,176}, {208,176}, {133,180}, {76,225}, {76,298}, {142,329}, {214,329}, {286,329}, {358,329}, {432,329},
			{505,329}, {578,329}, {651,329}, {724,329}, {797,333}, {863,365}, {875,438}, {826,492}, {754,497}, //39
			{682,497}, {609,497}, {536,497}, {463,497}, {390,497}, {317,497}, {245,497}, {171,497}, {100,510}, //48
			{70,575}, {92,645}, {163,656}, {236,665}, {308,665}, {380,665}, {453,665}, {526,665}, {599,665},
			{672,665}, {745,665}, {827,665}
	};
	
	ImageIcon ic = new ImageIcon("src/pic/g_board.png");
	static ImageIcon ic2 = new ImageIcon("src/pic/player.png");
	static ImageIcon ic3 = new ImageIcon("src/pic/g_item.png");
	static ImageIcon ic4 = new ImageIcon("src/pic/g_minigame.png");
	
	int place = 0;
	static int cnt = 0;
	static int [] itemArr_g = new int[7];
	static int [] quizArr_g = new int[10];
	
	//게임판
	JPanel P1 = new JPanel() {
		public void paintComponent(Graphics g) {
			g.drawImage(ic.getImage(), 0, 0, null);
		}
	};
	//플레이어
	static JPanel P2 = new JPanel() {
		public void paintComponent(Graphics g) {
			g.drawImage(ic2.getImage(), 0, 0, null);	//그림 자체의 위치와 넓이
		}
	};
	//아이템 동그라미
	static JPanel P3 = new JPanel() {
		public void paintComponent(Graphics g) {
			g.drawImage(ic3.getImage(), 0, 0, null);	//그림 자체의 위치와 넓이
		}
	};
	//함정 동그라미
	static JPanel P4 = new JPanel() {
		public void paintComponent(Graphics g) {
			g.drawImage(ic4.getImage(), 0, 0, null);	//그림 자체의 위치와 넓이
		}
	};
	
	//말을 움직이는 함수
	public void diceMove(int index) {
		P3.setVisible(false);
		P4.setVisible(false);

		for(int i=0;i<itemArr_g.length;i++) {
			if(index == itemArr_g[i]) {
				P3.setBounds(cPlace[index][0], cPlace[index][1], 100, 100);
				P3.setVisible(true);
			}
		}
		for(int i=0;i<quizArr_g.length;i++) {
			if(index == quizArr_g[i]) {
				P4.setBounds(cPlace[index][0], cPlace[index][1], 100, 100);
				P4.setVisible(true);
			}
		}
		P2.setBounds(cPlace[index][0], cPlace[index][1], 100, 100);	//그림을 사용할 범위의 위치, 넓이
	}
	
	public void board(int [] itemArr, int [] quizArr) {
		JFrame frm = new JFrame("GAME TEST");
		frm.setLayout(null);
		P1.setBounds(50, 0, 924, 773);
		P2.setBounds(83, 22, 100, 100);	//그림을 사용할 범위의 위치, 넓이
//	    P3.setBounds(83, 22, 100, 100);
//	    P4.setBounds(83, 22, 100, 100);
		
	    JLabel label = new JLabel();
	    JButton button = new JButton("주사위 굴리기");
	    button.setLocation(1000, 350);
	    button.setSize(150,50);
	    
	    ActionListener listener = new ActionListener() {
	    	int num = 0;
	    	String itemTitle = "황금열쇠";
	    	
			@Override
			public void actionPerformed(ActionEvent e) {
				System.arraycopy(itemArr, 0, itemArr_g, 0, 7);
				System.arraycopy(quizArr, 0, quizArr_g, 0, 10);

	    		num = (int) (Math.random()*10%6+1);
				JOptionPane.showMessageDialog(null, "주사위를 굴려 "+num+"이(가) 나왔습니다!",
						"주사위 굴리기", JOptionPane.PLAIN_MESSAGE);
				
				//나온 주사위의 숫자의 반만큼만 이동
				if(cnt!=0) {
					JOptionPane.showMessageDialog(null, "황금열쇠 작용!\n나온 숫자의 반만큼만 이동합니다.", itemTitle,
							JOptionPane.ERROR_MESSAGE);
					if(num==1||num==2) {
						place += 1;
					}else if(num==3||num==4) {
						place += 2;
					}else {
						place += 3;
					}
					cnt = 0;
				}else {
					place += num;
				}
				
				//황금열쇠 및 퀴즈 처리
				place = ep.apply(place, itemArr_g, quizArr_g);
			}
		};
		button.addActionListener(listener);
			
		frm.add(label);
		frm.add(button);
		frm.add(P2);
		frm.add(P3);
		frm.add(P4);
		frm.add(P1);
	    frm.setVisible(true);
	    frm.setBounds(0, 0, 1200, 800);
	    frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
