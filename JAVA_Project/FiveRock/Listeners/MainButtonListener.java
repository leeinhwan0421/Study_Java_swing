package FiveRock.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import FiveRock.CustomDataStructure.PlayerInformation;
import FiveRock.CustomDataStructure.Stone;
import FiveRock.CustomDataStructure.Stone.State;
import FiveRock.UI.BackGroundPanel;
import FiveRock.UI.UIInformation;

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
                UIInformation.getInstance().textPanel.DeleteAllJLabel();
                UIInformation.getInstance().textPanel.AddJLabel("-=-=--=-=-[게임시작]-=-=-=-=-=-");
                break;

            case "Exit":
                backGroundPanel.Init();
                ListenerInformation.getInstance().stoneButtonListener.SetMode(State.NOTSTARTING);
                UIInformation.getInstance().textPanel.DeleteAllJLabel();
                UIInformation.getInstance().textPanel.AddJLabel("-=-=--=-=-[게임퇴장]-=-=-=-=-=-");
            break;

            case "Surrender":
                StoneButtonListener stoneButtonListener = ListenerInformation.getInstance().stoneButtonListener;

                if (stoneButtonListener.getMode() == Stone.State.BLACK)
                    stoneButtonListener.EndGame(Stone.State.WHITE);
                else if (stoneButtonListener.getMode() == Stone.State.WHITE)
                    stoneButtonListener.EndGame(Stone.State.BLACK);

            break;

            case "Reset":
                backGroundPanel.Init();
                ListenerInformation.getInstance().stoneButtonListener.Init();
                UIInformation.getInstance().textPanel.DeleteAllJLabel();

                PlayerInformation b_player = UIInformation.getInstance().blackCharacterInfoPanel.GetPlayerInformation();
                PlayerInformation w_player = UIInformation.getInstance().whiteCharacterInfoPanel.GetPlayerInformation();

                b_player.Init();
                w_player.Init();

                UIInformation.getInstance().textPanel.AddJLabel("-=-=--=-=-[게임리셋]-=-=-=-=-=-");
            break;
        }
    }

    public void actionPerformed(String event) 
    {
        switch(event)
        {
            case "Start":
                backGroundPanel.Init();
                ListenerInformation.getInstance().stoneButtonListener.Init();
                UIInformation.getInstance().textPanel.DeleteAllJLabel();
                UIInformation.getInstance().textPanel.AddJLabel("-=-=--=-=-[게임시작]-=-=-=-=-=-");
                break;

            case "Exit":
                backGroundPanel.Init();
                ListenerInformation.getInstance().stoneButtonListener.SetMode(State.NOTSTARTING);
                UIInformation.getInstance().textPanel.DeleteAllJLabel();
                UIInformation.getInstance().textPanel.AddJLabel("-=-=--=-=-[게임퇴장]-=-=-=-=-=-");
            break;

            case "Surrender":
                StoneButtonListener stoneButtonListener = ListenerInformation.getInstance().stoneButtonListener;

                if (stoneButtonListener.getMode() == Stone.State.BLACK)
                    stoneButtonListener.EndGame(Stone.State.WHITE);
                else if (stoneButtonListener.getMode() == Stone.State.WHITE)
                    stoneButtonListener.EndGame(Stone.State.BLACK);

            break;
            
            case "Reset":
                backGroundPanel.Init();
                ListenerInformation.getInstance().stoneButtonListener.Init();
                UIInformation.getInstance().textPanel.DeleteAllJLabel();

                PlayerInformation b_player = UIInformation.getInstance().blackCharacterInfoPanel.GetPlayerInformation();
                PlayerInformation w_player = UIInformation.getInstance().whiteCharacterInfoPanel.GetPlayerInformation();

                b_player.Init();
                w_player.Init();

                UIInformation.getInstance().textPanel.AddJLabel("-=-=--=-=-[게임리셋]-=-=-=-=-=-");
            break;
        }
    }
    
}