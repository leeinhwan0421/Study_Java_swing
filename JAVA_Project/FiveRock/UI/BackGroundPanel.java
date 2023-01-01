package FiveRock.UI;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import FiveRock.FileSystem.FileReader;

public class BackGroundPanel extends JPanel
{
    private BufferedImage bufferedImage;

    public BufferedImage getBufferedImage() { return bufferedImage; }

    public BackGroundPanel()
    {
        BufferedImage targetImage = FileReader.LoadImage(UIInformation.getInstance().backgroundFilePath);

        bufferedImage = new BufferedImage(targetImage.getWidth(), targetImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        setImage(bufferedImage);

        setPreferredSize(new Dimension(bufferedImage.getWidth(null), bufferedImage.getHeight(null)));
        setSize(UIInformation.getInstance().windowSize.width, UIInformation.getInstance().windowSize.height);
        setBackground(new Color(224, 189, 102));

        repaint();
    }

    private void setImage(BufferedImage bi) 
	{
        this.bufferedImage = bi;
		Graphics2D g = bufferedImage.createGraphics();
        BufferedImage targetImage = FileReader.LoadImage(UIInformation.getInstance().backgroundFilePath);
		g.drawImage(targetImage, 0, 0, null);
		g.dispose();
	}

    protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(bufferedImage, 0, 0, null);
	}
}