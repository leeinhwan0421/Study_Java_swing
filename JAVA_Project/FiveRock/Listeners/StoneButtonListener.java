package FiveRock.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

import FiveRock.CustomDataStructure.Node;
import FiveRock.UI.BackGroundPanel;
import FiveRock.UI.UIInformation;

public class StoneButtonListener extends JComponent implements ActionListener
{
    private BackGroundPanel backGroundPanel;
    JButton jButtons[][] = new JButton[UIInformation.getInstance().mapSize.x][UIInformation.getInstance().mapSize.y];

    public StoneButtonListener(BackGroundPanel backGroundPanel)
    {
        this.backGroundPanel = backGroundPanel;

        if (backGroundPanel == null)
            System.out.println("StonButtonLister.super() Error, has not backgroundpanel");
    }

    public void setButtonArray(JButton[][] jButtons)
    {
        this.jButtons = jButtons;
    }

    public Node FindToPoint(ActionEvent e)
    {
        for (int i = 0; i < UIInformation.getInstance().mapSize.x; i++)
        {
            for (int j = 0; j < UIInformation.getInstance().mapSize.y; j++)
            {
                if (e.getSource() == jButtons[i][j])
                {
                    return new Node(i, j);
                }
            }
        }

        return null;
    }

	public void actionPerformed(ActionEvent e) 
    {
        Node node = FindToPoint(e);

        if (node == null)
            return;

        
    }
}