package FiveRock;

import javax.swing.SwingUtilities;

import FiveRock.UI.MainPanel;
import FiveRock.UI.UIInformation;

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
		MainPanel mainPanel = new MainPanel();

		frame.add(mainPanel, BorderLayout.NORTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("FiveRock");
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