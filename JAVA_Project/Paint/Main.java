package Paint;

import Paint.Constants.Constants;

import javax.swing.SwingUtilities;
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

		JFrame f = new JFrame();
		RectPanel rectPanel = new RectPanel();

		f.add(rectPanel, BorderLayout.NORTH);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Paint App");
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setVisible(true);
		f.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

		try {
			Image image = ImageIO.read(new File(Constants.defaulticonPath));
			f.setIconImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}