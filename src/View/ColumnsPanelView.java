
/*
 * This class is used for the background image of the column panel
 */


package View;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ColumnsPanelView extends JPanel{
	
	 @Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("desk.png");
		Image image;
		try {
			image = ImageIO.read(input);
			image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
		} catch (IOException e) {
		}
	}
	
}
