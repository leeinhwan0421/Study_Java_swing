package FiveRock.UI;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import FiveRock.FileSystem.FileReader;

public class BackGroundPanel extends JPanel
{
    private BufferedImage bufferedImage;
    private CreateButton createButton;

    public BufferedImage getBufferedImage() { return bufferedImage; }

    public BackGroundPanel()
    {
        setLayout(null);

        BufferedImage targetImage = FileReader.LoadImage(UIInformation.getInstance().backgroundFilePath);
        bufferedImage = new BufferedImage(targetImage.getWidth(), targetImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        setImage(bufferedImage);
        
        setPreferredSize(new Dimension(bufferedImage.getWidth(null), bufferedImage.getHeight(null)));
        setBackground(UIInformation.getInstance().backGroundColor);
        
        createButton = new CreateButton();

        add(createButton.CreateMenuButtons());
        add(createButton.createStoneButtons(this));

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