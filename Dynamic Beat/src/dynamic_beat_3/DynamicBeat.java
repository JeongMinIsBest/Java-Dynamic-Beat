package dynamic_beat_3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

		private Image screenImage;
		private Graphics screenGraphic; // 더블 버퍼링을 위해 두 인스턴스 생성
		private Image introBackground = new ImageIcon(Main.class.getResource("../images/introBakground(Title).png")).getImage(); // 메인 클래스 위치 기반 -> 사진을 얻어와서 이미지 인스턴스를 introBackground 변수에 초기화 -> 4강에서는 선언만 하는 것이 아닌 바로 다 불러오기로 했음
		private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png"))); // menuBar 이라는 객체 안에 해당 이미지가 들어가게 된다
	
		public DynamicBeat() {
			
			setUndecorated(true); // 실행 했을 때 바로 메뉴 바가 보이지 않게 해준다
			setTitle("Dynamic Beat");
			setSize(Main.SCREEN_WIDTH, Main.SCREEN_WEIGHT); // 전체 게임 창 크기 사이즈
			setResizable(false); // 사용자가 따로 게임 창 크기 바꿀 수 없음
			setLocationRelativeTo(null); // 게임창이 화면 정 중앙에 나오도록
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임 창 닫을 때 닫히도록 -> 없으면 게임이 꺼지지 않는다
			setVisible(true); // 게임 창이 보여야 한다
			setBackground(new Color(0, 0, 0, 0)); // screenDraw 에서 paintComponents 를 했을 때 회색이 아닌 하얀색으로 보이도록 해줌
			setLayout(null); // 버튼이랑 다른거 넣었을 때 레이아웃 그래도 하게
			
			menuBar.setBounds(0, 0, 1280, 30);
			add(menuBar); // 메뉴바 넣어 두기 -> screenDraw 에서 paintComponents 가 그려준다!
			
			
			Music introMusic = new Music("RaindropFlower.mp3", true); // 게임이 실해되면 메인 화면 음악 실행
			introMusic.start();
			
		}
		
		public void paint(Graphics g) {
			screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_WEIGHT);
			screenGraphic = screenImage.getGraphics();
			screenDraw(screenGraphic);
			g.drawImage(screenImage, 0, 0, null);
		}

		public void screenDraw(Graphics g) {
			g.drawImage(introBackground, 0, 0, null);
			paintComponents(g); // JLabel 등과 같은 것을 JFrame 에 그려주는 역할
			this.repaint(); // paint 는 JFrame 에서 우리가 원하는 내용을 그려줌 (약속!)
		}
}
