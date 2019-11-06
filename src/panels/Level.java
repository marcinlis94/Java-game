package panels;
import mainactivity.Game1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Level extends JPanel{
	
	private ImageIcon levelEnabledIcon;
	private ImageIcon levelDisabledIcon;
	private JLabel levelEnabled[];
	private JLabel levelDisabled[];
	private final static int MAX_LVL=10;
	private String name;
	private JPanel panel;
	private JLabel backToStartButton;
	private ImageIcon backToStartImage;
	
	public Level(String name, int achivedLevel){
		setOpaque(false);
		setLayout(new BorderLayout());
		panel=new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new GridLayout());
		this.name=name;
		createlevels(achivedLevel);
		backToMenu();
	}
	
	public void createlevels(int achivedLevel){
		levelEnabledIcon=new ImageIcon("images/levelEnabled.png");
		levelDisabledIcon=new ImageIcon("images/levelDisabled.png");
		levelEnabled=new JLabel[achivedLevel];
		levelDisabled=new JLabel[MAX_LVL-achivedLevel];
		for(int i=0;i<achivedLevel;i++){
			final int a=i+1;
			levelEnabled[i]=new JLabel(levelEnabledIcon);
			levelEnabled[i].addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					JPanel game = new Game1(name,a);
					remove(panel);
					remove(backToStartButton);
					add(game);
					revalidate();
					repaint();
				}
			});
			panel.add(levelEnabled[i]);	
		}
		for(int i=0;i<MAX_LVL-achivedLevel;i++){
			levelDisabled[i]=new JLabel(levelDisabledIcon);
			panel.add(levelDisabled[i]);
		}
		add(panel);
	}
	public void backToMenu(){
		backToStartImage = new ImageIcon("images/backButton.png");
		backToStartButton = new JLabel(backToStartImage);
		backToStartButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				JPanel startPanel = new Start();
				remove(panel);
				remove(backToStartButton);
				add(startPanel);
				revalidate();
				repaint();
			}
			});
		add(backToStartButton,BorderLayout.PAGE_END);
	}
}
