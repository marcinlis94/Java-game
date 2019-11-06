package panels;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SetName extends JPanel{
	
	private JLabel nextButton;
	private JLabel backButton;
	private JLabel textFrame;
	private ImageIcon nextImage;
	private ImageIcon backImage;
	private ImageIcon textFrameImage;
	private JTextField setName;
	private String name;
	private String loadName;
	private String[] playerResults;
	public static String[] playerName;
	public static String[] playerLevel;
	private JPanel namePanel;					
	private Scanner input;
	public static File nameListFile;
	public static int levelAchived;
	private int namesNumber;

	
	public SetName(){
		createButtons();
		loadNames();
		setPanel();
		addListeners();
	}
	
	private void createButtons(){
		setName = new JTextField(5);
		setName.setFont(new Font("Helvetica", Font.BOLD, 32));
		nextImage = new ImageIcon("images/startButton.png");
		backImage = new ImageIcon("images/backButton.png");
		textFrameImage = new ImageIcon("images/textFrame.png");
		nextButton = new JLabel(nextImage);
		backButton = new JLabel(backImage);
		textFrame = new JLabel(textFrameImage);
	}
	
	private void setPanel(){
		setLayout(new BorderLayout());
		setOpaque(false);
		namePanel = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		namePanel.setLayout(new GridBagLayout());
		namePanel.setOpaque(false);
		namePanel.setBorder(new EmptyBorder(50, 0, 0, 0));
		namePanel.add(textFrame,c);
		namePanel.add(setName,c);
		namePanel.add(nextButton);
		c.gridx = 0;
		c.gridy = 1;
		namePanel.add(backButton,c);
		add(namePanel,BorderLayout.CENTER);
		
	}
	private void addListeners(){
	nextButton.addMouseListener(new MouseAdapter()
	{
		public void mouseClicked(MouseEvent e)
		{
			name=setName.getText().trim();
			if(!name.equals("")){
				if(playerName.length==0){
					levelAchived=1;
					playerName=new String[1];
					playerName[0]=name;
				}else{
					for(int i=0;i<playerName.length;i++){
						if(name.equals(playerName[i])){
							levelAchived=Integer.parseInt(playerLevel[i]);
						}else
							levelAchived=1;
						if(levelAchived>1) break;
					}
				}
				JPanel levelPanel = new Level(name,levelAchived);
				remove(namePanel);
				add(levelPanel);
				revalidate();
				repaint();
			}
		}
		});
	backButton.addMouseListener(new MouseAdapter(){
		public void mouseClicked(MouseEvent e)
		{
			JPanel startPanel = new Start();
			remove(namePanel);
			add(startPanel);
			revalidate();
			repaint();
		}
		});
	}
	private void loadNames(){
		
		new File("rank").mkdirs();
		nameListFile = new File("rank/rank.txt");
		if (!nameListFile.exists()) {
            try {
				nameListFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		try {
			input=new Scanner(nameListFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		for(namesNumber=0;input.hasNextLine();namesNumber++){
			loadName=input.nextLine();
		}
		input.close();
		playerName=new String[namesNumber];
		playerLevel=new String[namesNumber];
		try {
			input=new Scanner(nameListFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for(int i=0;input.hasNextLine();i++){
				loadName=input.nextLine();
				if(!(loadName.equals(null))){
				playerResults=loadName.split("-");
				playerName[i]=playerResults[0];			
				playerLevel[i]=playerResults[1];
			}
		}
		input.close();
	}

}
