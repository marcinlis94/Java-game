package mainactivity;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import panels.Start;

public class Game1 extends GameParent{
	

	private AncestorListener nextStepListener;
	
	public Game1(String name, int level) {
		super(name, level);
		setTime=5*level;
		setTimePanel();
		setGamePanel1();
		addListeners();
	}
	

	
	private void addListeners(){
		nextStepListener = new AncestorListener() {
			
			@Override
			public void ancestorRemoved(AncestorEvent arg0) {
					JPanel game2 = new Game2(name,level);
					remove(playingField);
					remove(time);
					remove(bottomPanel);
					add(game2);
					revalidate();
					repaint();
			}
			
			@Override
			public void ancestorMoved(AncestorEvent arg0) {
			}
			
			@Override
			public void ancestorAdded(AncestorEvent arg0) {
			}
		};
		next.setEnabled(true);
		next.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				JPanel game2 = new Game2(name,level);
				remove(playingField);
				remove(time);
				remove(bottomPanel);
				add(game2);
				revalidate();
				repaint();
			}
		});
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

		showTime.addAncestorListener(nextStepListener);
	}
}