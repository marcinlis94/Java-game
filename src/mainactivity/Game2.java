package mainactivity;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import panels.Level;
import panels.SetName;
import panels.Start;

public class Game2 extends GameParent{


	private AncestorListener nextLvlListener;
	private FileWriter fileWriter;
	private BufferedWriter saveBuffer;
	private int existedNameInt;

	
	public Game2(String name,int level){
		super(name,level);
		setTime=3*level;						
		setTimePanel();
		setGamePanel2();
		addListener();
	}

	

	private void addListener(){
		nextLvlListener = new AncestorListener() {
			@Override
			public void ancestorRemoved(AncestorEvent arg0) {
				if(PlayingFieldParent.points>(level*0.77)){				//na wy¿szych poziomach dopuszczalne 1-2 bledy 
					if(SetName.levelAchived<10){
						if(level==SetName.levelAchived)
							SetName.levelAchived++;
					}
				}
				
				next.setEnabled(true);
				JPanel levelPanel = new Level(name,SetName.levelAchived);
				next.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e)
					{
						try {
							saveResults();
						} catch (IOException f) {
							f.printStackTrace();
						}
						remove(playingField);
						remove(time);
						remove(bottomPanel);
						add(levelPanel);
						revalidate();
						repaint();
					}
				});

			}
			@Override
			public void ancestorMoved(AncestorEvent arg0) {}
			@Override
			public void ancestorAdded(AncestorEvent arg0) {}
		};

		back.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				JPanel startPanel = new Start();
				remove(playingField);
				remove(time);
				remove(bottomPanel);
				add(startPanel);
				revalidate();
				repaint();
			}
			});
		
		
		showTime.addAncestorListener(nextLvlListener);
	}
	
	
	private void saveResults() throws IOException{
		existedNameInt=nameExist();
		SetName.nameListFile.delete();
		fileWriter= new FileWriter("rank/rank.txt",true);
		saveBuffer = new BufferedWriter(fileWriter);
			for(int i=0;i<SetName.playerName.length;i++){
				if(i==existedNameInt){
				}else{
						saveBuffer.write(SetName.playerName[i]+"-"+SetName.playerLevel[i]);
						saveBuffer.newLine();
				}
			}
				saveBuffer.write(name+"-"+SetName.levelAchived);
				saveBuffer.newLine();
				saveBuffer.close();
	}
	
	
	private int nameExist(){
		for(int i=0;i<SetName.playerName.length;i++){
			if(name.equals(SetName.playerName[i])){
				return i;
			}
		}
		return -1;
	}
}

