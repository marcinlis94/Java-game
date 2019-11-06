package panels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Background extends JPanel {
	
	private BufferedImage backgroundImage;
	private File background;
	public Background() {
		background = new File("images/background.png");
		try {
			backgroundImage = ImageIO.read(background);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(backgroundImage, 0, 0, this);
	}
}