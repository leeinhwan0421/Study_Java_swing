package FiveRock.UI;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import FiveRock.Component.RoundedButton;
import FiveRock.Listeners.StoneButtonListener;

public class CreateButton
{
    public JPanel CreateMenuButtons()
    {
        JPanel menuButtonPanel = new JPanel();

		menuButtonPanel.setLayout(new GridLayout(2,2,20,20));
		menuButtonPanel.setBounds(902, 20, 272, 200);
        menuButtonPanel.setBackground(UIInformation.getInstance().backGroundColor);

		String button_names[] = {"START", "EXIT", "SURRENDER", "RESET"};
		RoundedButton[] buttons = new RoundedButton[button_names.length];

		for (int i = 0; i < buttons.length; i++)
		{
			buttons[i] = new RoundedButton(button_names[i]);
		
			//buttons[i].addActionListener();
			buttons[i].setFont(new Font("CookieRun Regular", Font.BOLD, 20));
			buttons[i].setBorderPainted(true);
            buttons[i].setBorder(new LineBorder(Color.black));
            buttons[i].setBackGroundColor(UIInformation.getInstance().backGroundColor);

            buttons[i].setStrokeSize(5.0f);
            buttons[i].setBorderPaintedOption(true);
		
			menuButtonPanel.add(buttons[i]);
		}

		return menuButtonPanel;
    }

    public JPanel createStoneButtons(BackGroundPanel backGroundPanel)
    {
        JPanel stoneButtonPanel = new JPanel();

		stoneButtonPanel.setLayout(new GridLayout(UIInformation.getInstance().mapSize.y, UIInformation.getInstance().mapSize.x, 10, 10));
		stoneButtonPanel.setBounds(UIInformation.getInstance().mapOffset.x, UIInformation.getInstance().mapOffset.y, 
                                   UIInformation.getInstance().mapSizePixelRate.x, UIInformation.getInstance().mapSizePixelRate.y);
                                   
        stoneButtonPanel.setOpaque(false);
        stoneButtonPanel.setBackground(new Color(0,0,0));

		JButton[][] buttons = new JButton[UIInformation.getInstance().mapSize.x][UIInformation.getInstance().mapSize.y];
        StoneButtonListener stoneButtonListener = new StoneButtonListener(backGroundPanel);

		for (int i = 0; i < UIInformation.getInstance().mapSize.x; i++)
		{
            for (int j = 0; j < UIInformation.getInstance().mapSize.y; j++)
            {
                buttons[i][j] = new JButton();
                buttons[i][j].setBorderPainted(false);
                buttons[i][j].setOpaque(false);
                buttons[i][j].setBackground(new Color(0,0,0));
                buttons[i][j].addActionListener(stoneButtonListener);
		
			    stoneButtonPanel.add(buttons[i][j]);
            }
		}
        
        stoneButtonListener.setButtonArray(buttons);
        return stoneButtonPanel;
    }
}