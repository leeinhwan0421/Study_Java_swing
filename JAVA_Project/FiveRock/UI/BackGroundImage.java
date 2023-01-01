package FiveRock.UI;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BackGroundImage extends JPanel
{
    private BufferedImage bufferedImage;

    public BufferedImage getBufferedImage() { return bufferedImage; }

    public BackGroundImage()
    {
        bufferedImage = new BufferedImage(722, 720, BufferedImage.TYPE_INT_ARGB);
        setImage(bufferedImage);
        
        repaint();
    }

    private void setImage(BufferedImage bi) 
	{
        this.bufferedImage = bi;
		Graphics2D g = bufferedImage.createGraphics();

		g.setColor(Color.white);
		g.fillRect(0, 0, 722, 720);
		g.dispose();
	}

    protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(bufferedImage, 0, 0, null);
	}
}