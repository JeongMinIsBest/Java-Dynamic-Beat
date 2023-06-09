package dynamic_beat_4;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {
	
	private Player player;
	private boolean isLoop; // 곡이 무한 반복인지 체크
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) {
		try { 
			
			// try - catch 문은 try 에서 오류가 날 경우에는 catch 에서 처리를 해준다
			
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI()); // 해당 파일의 위치 갖고올 수 있게 된다
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis); // 해당 음악 파일을 버퍼에 담아서 갖고올 수 있도록 한다
			player = new Player(bis);
			
	    } catch (Exception e) {
	    	
	    	System.out.println(e.getMessage());
	    
	    }
	}
	
	public int getTime() { // 노래의 시간 값을 불러오는 함수
		
		if (player == null)
			return 0;
		return player.getPosition();
	
	}
	
	public void lose() { // 사용자가 중간에 화면을 종료할 때 노래도 정상적으로 종료되게 만든다
		
		isLoop = false;
		player.close();
		this.interrupt();
		
	}
	
	@Override
	public void run() { // Thread 를 사용할 경우에 무조건 사용 해야하는 함수
		
		try {
		
			do {
				
				player.play(); // 곡 실행
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
				
			} while (isLoop); // isLoop 일 때 곡이 무한 반복 실행
			
	    } catch (Exception e) {
		
		    System.out.println(e.getMessage());
		
	    }
		
    }
	
}	