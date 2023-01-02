package FiveRock.UI;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import FiveRock.Component.RoundedButton;
import FiveRock.Listeners.StoneButtonListener;
import FiveRock.Listeners.MainButtonListener;
import FiveRock.Listeners.ListenerInformation;

public class CreateButton
{
    public JPanel CreateMenuButtons(BackGroundPanel backGroundPanel)
    {
        JPanel menuButtonPanel = new JPanel();

		menuButtonPanel.setLayout(new GridLayout(2,2,20,20));
		menuButtonPanel.setBounds(892, 20, 272, 200);
        menuButtonPanel.setBackground(UIInformation.getInstance().backGroundColor);

		String button_names[] = {"Start", "Exit", "Surrender", "Reset"};
		RoundedButton[] buttons = new RoundedButton[button_names.length];

        ListenerInformation.getInstance().mainButtonListener = new MainButtonListener(backGroundPanel);
        
		for (int i = 0; i < buttons.length; i++)
		{
			buttons[i] = new RoundedButton(button_names[i]);
		
			buttons[i].setFont(new Font("CookieRun Regular", Font.BOLD, 20));
			buttons[i].setBorderPainted(true);
            buttons[i].setBorder(new LineBorder(Color.black));
            buttons[i].setBackGroundColor(UIInformation.getInstance().backGroundColor);
            buttons[i].setStrokeSize(5.0f);
            buttons[i].setBorderPaintedOption(true);
			buttons[i].addActionListener(ListenerInformation.getInstance().mainButtonListener);
		
			menuButtonPanel.add(buttons[i]);
		}

		return menuButtonPanel;
    }

    public JPanel createStoneButtons(BackGroundPanel backGroundPanel)
    {
        JPanel stoneButtonPanel = new JPanel();

		stoneButtonPanel.setLayout(new GridLayout(UIInformation.getInstance().mapSize.y, UIInformation.getInstance().mapSize.x, 0, 0));
		stoneButtonPanel.setBounds(UIInformation.getInstance().mapOffset.x, UIInformation.getInstance().mapOffset.y, 
                                   UIInformation.getInstance().mapSizePixelRate.x, UIInformation.getInstance().mapSizePixelRate.y);
                                   
        stoneButtonPanel.setOpaque(false);
        stoneButtonPanel.setBackground(new Color(0,0,0));

		JButton[][] buttons = new JButton[UIInformation.getInstance().mapSize.x][UIInformation.getInstance().mapSize.y];

        ListenerInformation.getInstance().stoneButtonListener = new StoneButtonListener(backGroundPanel);

		for (int i = 0; i < UIInformation.getInstance().mapSize.x; i++)
		{
            for (int j = 0; j < UIInformation.getInstance().mapSize.y; j++)
            {
                buttons[i][j] = new JButton();
                buttons[i][j].setBorderPainted(false);
                buttons[i][j].setOpaque(false);
                buttons[i][j].setBackground(new Color(0,0,0));
                buttons[i][j].addActionListener(ListenerInformation.getInstance().stoneButtonListener);
		
			    stoneButtonPanel.add(buttons[i][j]);
            }
		}

        ListenerInformation.getInstance().stoneButtonListener.setButtonArray(buttons);
        return stoneButtonPanel;
    }
}