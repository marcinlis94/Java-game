package mainactivity;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;

import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class GameParent extends JPanel{

	protected int setTime;
	protected int level;
	protected int a;							//zmienna pomocnicza do ustawienia ramki czasu
	protected String name;
	protected  JTextField showTime;
	private Timer timer;
	public JLabel time;
	private ImageIcon timeFrameImage;
	protected JPanel playingField;
	protected JPanel bottomPanel;
	protected JLabel next;
	private ImageIcon nextImage;
	private ImageIcon nextImageDisable;
	private ImageIcon backImage;
	protected JLabel back;
	
	public GameParent(String name,int level){
	this.name=name;
	this.level=level;
	setOpaque(false);
	setLayout(new BorderLayout());
	createButtons();
	}
		


	protected void setGamePanel1(){
		playingField=new PlayingField1(level);
		add(playingField);
		add(bottomPanel,BorderLayout.PAGE_END);

	}
	protected void setGamePanel2(){
		playingField=new PlayingField2(level);
		add(playingField);
		add(bottomPanel,BorderLayout.PAGE_END);
	}
	
	protected void setTimePanel(){
		timeFrameImage = new ImageIcon("images/timeframe.png");
		showTime =new JTextField();
		showTime.setEditable(false);
		showTime.setFont(new Font("Helvetica", Font.BOLD, 48));
		showTime.setOpaque(false);
		time = new JLabel(timeFrameImage);
		time.setLayout(new GridBagLayout());
		add(time,BorderLayout.PAGE_START);
		time.setBorder(new EmptyBorder(0, 100,0, 0));
		time.add(showTime);
		timer = new Timer();
		MyTimer time_task = new MyTimer(showTime,setTime,timer,level);
		timer.schedule (time_task, 0, 1000);
	}

	protected void createButtons(){
		bottomPanel = new JPanel();
		nextImage=new ImageIcon("images/nextButton.png");
		nextImageDisable=new ImageIcon("images/nextButtonDisable.png");
		next=new JLabel(nextImage);
		next.setDisabledIcon(nextImageDisable);
		next.setEnabled(false);
		backImage = new ImageIcon("images/backButton2.png");
		back = new JLabel(backImage);
		bottomPanel.setLayout(new BorderLayout());
		next.setBorder(new EmptyBorder(0, 20, 0, 20));
		bottomPanel.add(next,BorderLayout.EAST);
		bottomPanel.add(back,BorderLayout.WEST);
		bottomPanel.setOpaque(false);
	}
}
