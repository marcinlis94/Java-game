package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Ranking extends JPanel{
	
	private ImageIcon rankFrame;
	private JLabel backToStartButton;
	private ImageIcon backToStartImage;
	private JPanel panel;
	private JLabel rank[];
	private final static int LINES_NUMBER=3;
	private File rankFile;
	private Scanner input;
	private int namesNumber;
	private String loadName;
	private String[] playerName;
	private String[] playerLevel;
	private String[] playerResults;
	
	public Ranking(){
		setOpaque(false);
		setLayout(new BorderLayout());
		panel=new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(3,1));
		loadRank();
		sortRank();
		createRank();
		backToMenu();
	}
	
	public void createRank(){
		rankFrame=new ImageIcon("images/rankFrame.png");
		rank=new JLabel[LINES_NUMBER];
		for(int i=0;i<LINES_NUMBER;i++){
			rank[i]=new JLabel(rankFrame);
			rank[i].setText(i+1+". "+playerName[i]+" Level: "+playerLevel[i]);
			rank[i].setFont(new Font("Brush Script MT", Font.BOLD, 42));
			rank[i].setForeground(Color.BLACK);
			rank[i].setHorizontalTextPosition(JLabel.CENTER);
			panel.add(rank[i]);	
			panel.setBorder(new EmptyBorder(150, 0, 0, 0));
			add(panel);	
		}
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
	public void sortRank(){
		String buffer;
		for(int i=0;i<namesNumber-1;i++){
			if(Integer.parseInt(playerLevel[i])<Integer.parseInt(playerLevel[i+1])){
				buffer=playerLevel[i];
				playerLevel[i]=playerLevel[i+1];
				playerLevel[i+1]=buffer;
				buffer=playerName[i];
				playerName[i]=playerName[i+1];
				playerName[i+1]=buffer;
				i=-1;
			}
		}
	}
private void loadRank(){
		
		new File("rank").mkdirs();
		rankFile = new File("rank/rank.txt");
		if (!rankFile.exists()) {
            try {
				rankFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		try {
			input=new Scanner(rankFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		for(namesNumber=0;input.hasNextLine();namesNumber++){
			loadName=input.nextLine();
		}
		input.close();
		playerName = new String[namesNumber];
		playerLevel = new String[namesNumber];
		try {
			input=new Scanner(rankFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for(int i=0;input.hasNextLine();i++){
				loadName=input.nextLine();
				if(!(loadName.equals(null))){
				playerResults = loadName.split("-");
				playerName[i]=playerResults[0];			
				playerLevel[i]=playerResults[1];
			}
		}
		input.close();
	}
}