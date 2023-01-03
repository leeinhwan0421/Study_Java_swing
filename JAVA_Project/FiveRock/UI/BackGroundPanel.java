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

        Init();
        
        setPreferredSize(new Dimension(bufferedImage.getWidth(null), bufferedImage.getHeight(null)));
        setBackground(UIInformation.getInstance().backGroundColor);
        
        createButton = new CreateButton();

        add(createButton.CreateMenuButtons(this));
        add(createButton.createStoneButtons(this));
        add(UIInformation.getInstance().textPanel);
        add(UIInformation.getInstance().characterPanel);
        add(UIInformation.getInstance().blackCharacterInfoPanel);
        add(UIInformation.getInstance().whiteCharacterInfoPanel);
        repaint();
    }

    private void setImage(BufferedImage bi) 
	{
        this.bufferedImage = bi;
		Graphics2D g = bufferedImage.createGraphics();
        BufferedImage targetImage = FileReader.LoadBufferedImage(UIInformation.getInstance().backgroundFilePath);
		g.drawImage(targetImage, 0, 0, null);
		g.dispose();
        repaint();
	}

    public void Init()
    {
        BufferedImage targetImage = FileReader.LoadBufferedImage(UIInformation.getInstance().backgroundFilePath);
        bufferedImage = new BufferedImage(targetImage.getWidth(), targetImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        setImage(bufferedImage);
    }

    public void DrawImage(BufferedImage targetImage, int x, int y)
    {
		Graphics2D g = bufferedImage.createGraphics();
		g.drawImage(targetImage, x, y, null);
		g.dispose();
        repaint();
    }

    protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(bufferedImage, 0, 0, null);
	}
}