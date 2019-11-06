package frame;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import panels.Start;

 
public class Frame extends JFrame{
	private Image ikona;
	private static final int Width = 900;
	private static final int Height = 600;
	
	public Frame() {
		super("Gra");
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLayout(new BorderLayout());
		setVisible(true);
		setSize(Width,Height);
		setResizable(false);
		ikona = new ImageIcon("images/icon.png").getImage();
		setIconImage(ikona);
		JPanel start = new Start();
		add(start);

	}
}