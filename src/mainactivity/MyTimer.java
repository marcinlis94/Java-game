package mainactivity;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;

public class MyTimer extends TimerTask{

	private JTextField text;
	private int time;
	private Timer timer;
	private int level; 

	
	public MyTimer(JTextField text, int time,Timer timer,int level){
		this.text=text;
		this.time=time;
		this.timer=timer;
		this.level=level;

	}
	public void run() {
		if(PlayingFieldParent.points==level){
			time=0;
		}
		text.setText(""+time);
		time--;
		if(time<0){
			timer.cancel();
			text.setVisible(false);


		}	
	}
}
