package dynamic_beat_5;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

		private Image screenImage;
		private Graphics screenGraphic; // 더블 버퍼링을 위해 두 인스턴스 생성
		
		private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
		private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
		private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
		private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
		private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
		private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
		
		private Image background = new ImageIcon(Main.class.getResource("../images/introBackground(Title).png")).getImage(); // 메인 클래스 위치 기반 -> 사진을 얻어와서 이미지 인스턴스를 background 변수에 초기화 -> 4강에서는 선언만 하는 것이 아닌 바로 다 불러오기로 했음
		
		private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png"))); // menuBar 이라는 객체 안에 해당 이미지가 들어가게 된다
	
		private JButton exitButton = new JButton(exitButtonBasicImage); // exit 버튼 이미지 추가 -> 가져오기
		private JButton startButton = new JButton(startButtonBasicImage); // start 버튼 이미지 추가 -> 가져오기
		private JButton quitButton = new JButton(quitButtonBasicImage); // quit 버튼 이미지 추가 -> 가져오기
 		
		private int mouseX, mouseY; // 마우스 X, Y 좌표값
		
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
			
        menuBar.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mousePressed(MouseEvent e) { // 마우스로 클릭했을 때 이벤트 처리
					
					mouseX = e.getX();
					mouseY = e.getY();
					
				}
		});
			
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
				
				@Override
				public void mouseDragged(MouseEvent e) { // 마우스 드래그가 발생했을 때의 이벤트
					
					int x = e.getXOnScreen();
					int y = e.getYOnScreen();
					setLocation(x - mouseX, y - mouseY); // 마우스의 X 값과 Y 값을 실시간으로 받아와서 게임창(JFrame)의 위치를 바꾸어줌
					
				}  
		});
		
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false); // JButton 은 커스터마이징 되어있지 않기 때문에 따로 지정을 해줘야함
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
				
			@Override
			public void mouseEntered(MouseEvent e) {
					exitButton.setIcon(exitButtonEnteredImage); // 마우스가 올라갔을 때 Entered 이미지로 바꾸어줌
					exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 올라갔을 때 손가락 모양으로 바꿔줌
					Music buttonEnteredMusic = new Music("buttonEnteredMusi.mp3", false);
					buttonEnteredMusic.start();
			}
				
			@Override
			public void mouseExited(MouseEvent e) {
				    exitButton.setIcon(exitButtonBasicImage); // 마우스가 없을 때는 다시 원래 이미지로 돌아옴
				    exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 올라갔을 때 손가락 모양으로 바꿔줌
			}
				
			@Override
			public void mousePressed(MouseEvent e) { // 클릭했을 때 게임 종료
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					
					Thread.sleep(1000); // 1초 정도 있다가 프로그램 종료
				
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				
				System.exit(0);
			}
			
		});
		
		add(exitButton); // exit 버튼 넣어두기
		
		startButton.setBounds(40, 500, 400, 100);
		startButton.setBorderPainted(false); // JButton 은 커스터마이징 되어있지 않기 때문에 따로 지정을 해줘야함
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
				
			@Override
			public void mouseEntered(MouseEvent e) {
					startButton.setIcon(startButtonEnteredImage); // 마우스가 올라갔을 때 Entered 이미지로 바꾸어줌
					startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 올라갔을 때 손가락 모양으로 바꿔줌
					Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
					buttonEnteredMusic.start();
			}
				
			@Override
			public void mouseExited(MouseEvent e) {
				    startButton.setIcon(startButtonBasicImage); // 마우스가 없을 때는 다시 원래 이미지로 돌아옴
				    startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 올라갔을 때 손가락 모양으로 바꿔줌
			}
				
			@Override
			public void mousePressed(MouseEvent e) { // 클릭했을 때 게임 스타트
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				startButton.setVisible(false);
				quitButton.setVisible(false);
				background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
			}
			
		});
		
		add(startButton); // start 버튼 넣어두기
		
		quitButton.setBounds(40, 600, 400, 100);
		quitButton.setBorderPainted(false); // JButton 은 커스터마이징 되어있지 않기 때문에 따로 지정을 해줘야함
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
				
			@Override
			public void mouseEntered(MouseEvent e) {
					quitButton.setIcon(quitButtonEnteredImage); // 마우스가 올라갔을 때 Entered 이미지로 바꾸어줌
					quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 올라갔을 때 손가락 모양으로 바꿔줌
					Music buttonEnteredMusic = new Music("buttonEnteredMusi.mp3", false);
					buttonEnteredMusic.start();
			}
				
			@Override
			public void mouseExited(MouseEvent e) {
				    quitButton.setIcon(quitButtonBasicImage); // 마우스가 없을 때는 다시 원래 이미지로 돌아옴
				    quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 올라갔을 때 손가락 모양으로 바꿔줌
			}
				
			@Override
			public void mousePressed(MouseEvent e) { // 클릭했을 때 게임 종료
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					
					Thread.sleep(1000); // 1초 정도 있다가 프로그램 종료
				
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				
				System.exit(0);
			}
			
		});
		
		add(quitButton); // quit 버튼 넣어두기
		
			
		add(menuBar); // 메뉴바 넣어 두기 -> screenDraw 에서 paintComponents 가 그려준다!
			
			
		menuBar.setBounds(0, 0, 1280, 30);
			
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
			g.drawImage(background, 0, 0, null);
			paintComponents(g); // JLabel 등과 같은 것을 JFrame 에 그려주는 역할
			this.repaint(); // paint 는 JFrame 에서 우리가 원하는 내용을 그려줌 (약속!)
		}
}