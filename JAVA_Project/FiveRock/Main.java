package FiveRock;

import javax.swing.SwingUtilities;

import FiveRock.UI.UIInformation;
import FiveRock.UI.BackGroundPanel;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class Main 
{
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() 
	{
		System.out.println("Created GUI on EDT?" + SwingUtilities.isEventDispatchThread());

		JFrame frame = new JFrame();
		BackGroundPanel backGroundPanel = new BackGroundPanel();

		frame.add(backGroundPanel, BorderLayout.NORTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("FiveRock");
		frame.setLocation(400, 100);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setSize(UIInformation.getInstance().windowSize.width, UIInformation.getInstance().windowSize.height);

		try 
        {
			Image image = ImageIO.read(new File(UIInformation.getInstance().iconFilePath));
			frame.setIconImage(image);
		} 
        catch (IOException e) 
        {
			e.printStackTrace();
		}
	}
}