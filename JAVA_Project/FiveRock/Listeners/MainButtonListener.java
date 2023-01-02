package FiveRock.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import FiveRock.CustomDataStructure.Stone.State;
import FiveRock.UI.BackGroundPanel;

public class MainButtonListener extends JComponent implements ActionListener
{
    BackGroundPanel backGroundPanel;

    public MainButtonListener(BackGroundPanel backGroundPanel)
    {
        this.backGroundPanel = backGroundPanel;
    }

    public void actionPerformed(ActionEvent e) 
    {
        String actionCommand = e.getActionCommand();

        switch(actionCommand)
        {
            case "Start":
                backGroundPanel.Init();
                ListenerInformation.getInstance().stoneButtonListener.Init();
            break;
            case "Exit":
                backGroundPanel.Init();
                ListenerInformation.getInstance().stoneButtonListener.SetMode(State.NOTSTARTING);
            break;
            case "Surrender":

            break;
            case "Reset":
                backGroundPanel.Init();
                ListenerInformation.getInstance().stoneButtonListener.Init();
            break;
        }
    }
    
}