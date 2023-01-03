package FiveRock.Listeners;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import FiveRock.CustomDataStructure.Node;
import FiveRock.CustomDataStructure.Stone;
import FiveRock.CustomDataStructure.Stone.State;
import FiveRock.FileSystem.FileReader;
import FiveRock.UI.BackGroundPanel;
import FiveRock.CustomDataStructure.PlayerInformation;
import FiveRock.UI.UIInformation;

public class StoneButtonListener extends JComponent implements ActionListener
{
    private BackGroundPanel backGroundPanel;
    private Stone.State mode = Stone.State.BLACK;
    public Stone.State getMode() { return mode; }

    private JButton jButtons[][] = new JButton[UIInformation.getInstance().mapSize.x][UIInformation.getInstance().mapSize.y];
    private Stone stones[][] = new Stone[UIInformation.getInstance().mapSize.x][UIInformation.getInstance().mapSize.y];

    public String blackStoneCharacterFilePath = "FiveRock/ImageSource/UI/PlayerIcon/Character_Black.png";
    public String whiteStoneCharacterFilePath = "FiveRock/ImageSource/UI/PlayerIcon/Character_White.png";

    public StoneButtonListener(BackGroundPanel backGroundPanel)
    {
        Init();

        this.backGroundPanel = backGroundPanel;

        if (backGroundPanel == null)
            System.out.println("StonButtonLister.super() Error, has not backgroundpanel");
    }

    public void Init()
    {
        for(int i = 0; i < UIInformation.getInstance().mapSize.x; i++)
        {
            for (int j = 0; j < UIInformation.getInstance().mapSize.y; j++)
            {
                stones[i][j] = new Stone();   
            }
        }

        SetMode(Stone.State.BLACK);
    }

    public void setButtonArray(JButton[][] jButtons)
    {
        this.jButtons = jButtons;
    }

    public void SetMode(Stone.State nextMode)
    {
        mode = nextMode;
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

    public boolean FindFiveStar()
    {
        int [][] d = {{0,1}, {1,0}, {1,1}, {-1,1}};

        for (int i = 0; i < UIInformation.getInstance().mapSize.x; i++)
        {
            for (int j = 0; j < UIInformation.getInstance().mapSize.y; j++)
            {
                if (stones[i][j].StoneState == State.WHITE || stones[i][j].StoneState == State.BLACK)
                {
                    for (int k = 0; k < 4; k++)
                    {
                        int ax = i;
                        int ay = j;
                        int cnt = 1;

                        while(true)
                        {
                            ax += d[k][0];
                            ay += d[k][1];

                            if ( 0 <= ax && ax < UIInformation.getInstance().mapSize.x && 0 <= ay && ay < UIInformation.getInstance().mapSize.y) 
                            {
                                if (stones[i][j].StoneState == stones[ax][ay].StoneState)
                                    cnt++;
                                else
                                    break;
                            }
                            else break;
                        }

                        ax = i;
                        ay = j;

                        while(true)
                        {
                            ax -= d[k][0];
                            ay -= d[k][1];

                            if ( 0 <= ax && ax < UIInformation.getInstance().mapSize.x && 0 <= ay && ay < UIInformation.getInstance().mapSize.y)
                            {
                                if (stones[i][j].StoneState == stones[ax][ay].StoneState)
                                    cnt++;
                            else
                                break;
                            }
                            else break;
                        }

                        if (cnt == 5)
                        {
                            Stone.State state = stones[i][j].StoneState;
                            System.out.println("Winner : " + state);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

	public void actionPerformed(ActionEvent e) 
    {
        Node node = FindToPoint(e);

        if (node == null)
            return;

        if (mode == Stone.State.NONE || mode == Stone.State.NOTSTARTING)
            return;

        if (stones[node.x][node.y].StoneState != Stone.State.NONE)
            return;

        else if (stones[node.x][node.y].StoneState == mode)
            return;

        Rectangle rect = jButtons[node.x][node.y].getBounds();
        String position = UIInformation.getInstance().positionX[node.y] + "" +  UIInformation.getInstance().positionY[node.x];
        
        if (mode == Stone.State.BLACK)
        {
            backGroundPanel.DrawImage(FileReader.LoadBufferedImage(UIInformation.getInstance().blackStoneFilePath),
                                      rect.x + UIInformation.getInstance().mapOffset.x, 
                                      rect.y + UIInformation.getInstance().mapOffset.y);

            UIInformation.getInstance().textPanel.AddJLabel("흑돌이 " + position + " 에 돌을 놨습니다.");
            stones[node.x][node.y].StoneState = mode;
            SetMode(Stone.State.WHITE);

            if (FindFiveStar())
                EndGame(Stone.State.BLACK);
        }
        else
        {
            backGroundPanel.DrawImage(FileReader.LoadBufferedImage(UIInformation.getInstance().whiteStoneFilePath), 
                                      rect.x + UIInformation.getInstance().mapOffset.x, 
                                      rect.y + UIInformation.getInstance().mapOffset.y);

            UIInformation.getInstance().textPanel.AddJLabel("백돌이 " + position + "에 돌을 놨습니다.");
            stones[node.x][node.y].StoneState = mode;
            SetMode(Stone.State.BLACK);

            if (FindFiveStar())
                EndGame(Stone.State.WHITE);
        }
    }

    public void EndGame(Stone.State state)
    {
        PlayerInformation b_player = UIInformation.getInstance().blackCharacterInfoPanel.GetPlayerInformation();
        PlayerInformation w_player = UIInformation.getInstance().whiteCharacterInfoPanel.GetPlayerInformation();

        switch(state)
        {
            case BLACK:
                UIInformation.getInstance().textPanel.AddJLabel("흑돌이 승리하였습니다");

                
                b_player.setWincountText(1);
                w_player.setLossCountText(1);

                ImageIcon icon_black = new ImageIcon(FileReader.LoadImage(blackStoneCharacterFilePath));
                JOptionPane.showMessageDialog(
                        null,
                        "!!흑돌 승리!!",
                        "Black is Winner", JOptionPane.INFORMATION_MESSAGE,
                        icon_black);

                ListenerInformation.getInstance().mainButtonListener.actionPerformed("Exit");
            break;

            case WHITE:
                UIInformation.getInstance().textPanel.AddJLabel("백돌이 승리하였습니다");

                w_player.setWincountText(1);
                b_player.setLossCountText(1);

                ImageIcon icon_white = new ImageIcon(FileReader.LoadImage(whiteStoneCharacterFilePath));
                JOptionPane.showMessageDialog(
                        null,
                        "!!백돌 승리!!",
                        "White is Winner", JOptionPane.INFORMATION_MESSAGE,
                        icon_white);

                ListenerInformation.getInstance().mainButtonListener.actionPerformed("Exit");
            break;

            default:

                break;
        }
    }
}