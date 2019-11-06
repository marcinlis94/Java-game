package mainactivity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PlayingFieldParent extends JPanel{
	
	private String[] engWords;
	private String[] plWords;
	protected String[] chosenWordsEng;
	protected static String[] chosenWordsPl;
	private Scanner inEng;
	private Scanner inPl;
	private JLabel[] wordLabel;
	private ImageIcon wordFrame;
	private final static int LABEL_NO=20;
	private BufferedImage backgroundImage;
	private File background;
	public static int points;
	private int level;
	
	public PlayingFieldParent(int level){
		loadBackground();
		points=0;
		this.level=level;
	}
	
	protected void loadBackground(){
		background = new File("images/field.png");
		try {
			backgroundImage = ImageIO.read(background);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(backgroundImage, 5, 0, this);
	}
	
	protected void readData()
	{
		try {
			inEng = new Scanner(new FileInputStream("words/eng.txt"));
			inPl = new Scanner(new FileInputStream("words/pl.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		engWords = new String[200];
		plWords = new String[200];														
		
		for(int i=0;i<200;i++){
			engWords[i] =inEng.nextLine();
			plWords[i] =inPl.nextLine();
		}
	}
	
	protected void chooseWords(int lvl){
		int r;
		int different[]=new int[2*lvl];
		chosenWordsEng=new String[lvl];
		chosenWordsPl=new String[2*lvl];
		for(int i=0;i<2*lvl;i++){
			r=randomNo(engWords.length);
			different[i]=r;
			for(int j=0;j<i;j++){
				if(different[j]==r){
					r=randomNo(engWords.length);
					different[i]=r;
					j=-1;
				}
			}	
			chosenWordsPl[i]=plWords[r];
			if(i<lvl)
			chosenWordsEng[i]=engWords[r];
		}
	}
	
	private int randomNo(int i){
        Random random = new Random();
        int randomNum = random.nextInt(i);
        return randomNum;
	}
	
	protected void createlabels(int wordsNumber,String chosenWords[],int lvl){
		int a;
		int different[]=new int[wordsNumber];
		wordFrame=new ImageIcon("images/wordFrame.png");
		setLayout(new GridLayout(5, 4, 5, 5));
		setOpaque(false);
		setBorder(new EmptyBorder(10, 0, 40, 0));
		wordLabel= new JLabel[LABEL_NO];
		for(int i=0;i<LABEL_NO;i++){
			wordLabel[i]=new JLabel(wordFrame);
			add(wordLabel[i]);
			wordLabel[i].setVisible(false);
		}
		for(int i=0;i<wordsNumber;i++){
			a=randomNo(LABEL_NO);
			different[i]=a;
			for(int j=0;j<i;j++){
				if(different[j]==a){
					a=randomNo(LABEL_NO);
					different[i]=a;
					j=-1;
				}	
			}
			wordLabel[a].setVisible(true);
			wordLabel[a].setText(chosenWords[i]);
			wordLabel[a].setFont(new Font("Arial Narrow", Font.BOLD, 16));
			wordLabel[a].setForeground(Color.DARK_GRAY);
			wordLabel[a].setHorizontalTextPosition(JLabel.CENTER);
			if(chosenWords[i]==chosenWordsPl[i])
			addListener(wordLabel[a]);
		}
	}
	
	private void addListener(JLabel word){
		word.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				int compare;
				compare=points;
				for(int i=0;i<level;i++){
					if(word.getText()==chosenWordsPl[i]){
						points++;
						word.setForeground(Color.GREEN);
					}
				}	
				if(compare==points){
					points--;
					word.setForeground(Color.RED);
				}
			}
			});
	
	}
}