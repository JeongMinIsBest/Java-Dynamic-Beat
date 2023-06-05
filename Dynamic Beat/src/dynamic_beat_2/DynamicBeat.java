package dynamic_beat_2;

import javax.swing.JFrame;

public class DynamicBeat extends JFrame {

		
		public DynamicBeat() {
			
			setTitle("Dynamic Beat");
			setSize(Main.SCREEN_WIDTH, Main.SCREEN_WEIGHT); // 전체 게임 창 크기 사이즈
			setResizable(false); // 사용자가 따로 게임 창 크기 바꿀 수 없음
			setLocationRelativeTo(null); // 게임창이 화면 정 중앙에 나오도록
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임 창 닫을 때 닫히도록 -> 없으면 게임이 꺼지지 않는다
			setVisible(true); // 게임 창이 보여야 한다
			
		}

}
