package panels;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Start extends Background {

	private JLabel startButton;
	private JLabel rankButton;
	private ImageIcon startImage;
	private ImageIcon rankImage;
	
	private JPanel menuPanel;
	
	public Start(){
		createButtons();
		setPanel();
		addListeners();
	}

	private void createButtons(){
		startImage = new ImageIcon("images/startButton.png");
		rankImage = new ImageIcon("images/rankingButton.png");
		startButton = new JLabel(startImage);
		rankButton = new JLabel(rankImage);
	}
	private void setPanel(){
		setLayout(new BorderLayout());
		setOpaque(false);
		menuPanel= new JPanel();
		menuPanel.setOpaque(false);
		menuPanel.setLayout(new GridLayout(0,1));
		menuPanel.setBorder(new EmptyBorder(200, 0, 100, 0));
		menuPanel.add(startButton);
		menuPanel.add(rankButton);
		add(menuPanel);
	}
	private void addListeners(){		
		startButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				JPanel setNamePanel = new SetName();
				remove(menuPanel);
				add(setNamePanel);
				revalidate();
				repaint();
			}
		});
		
		rankButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e){
				JPanel rankPanel = new Ranking();
				remove(menuPanel);
				add(rankPanel);
				revalidate();
				repaint();
			}
		});
	}
}
