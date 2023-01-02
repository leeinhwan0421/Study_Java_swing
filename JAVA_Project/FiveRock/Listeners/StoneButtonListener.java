package FiveRock.Listeners;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

import FiveRock.CustomDataStructure.Node;
import FiveRock.CustomDataStructure.Stone;
import FiveRock.FileSystem.FileReader;
import FiveRock.UI.BackGroundPanel;
import FiveRock.UI.UIInformation;

public class StoneButtonListener extends JComponent implements ActionListener
{
    private BackGroundPanel backGroundPanel;
    private Stone.State mode = Stone.State.BLACK;

    private JButton jButtons[][] = new JButton[UIInformation.getInstance().mapSize.x][UIInformation.getInstance().mapSize.y];
    private Stone stones[][] = new Stone[UIInformation.getInstance().mapSize.x][UIInformation.getInstance().mapSize.y];

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
            backGroundPanel.DrawImage(FileReader.LoadImage(UIInformation.getInstance().blackStoneFilePath),
                                      rect.x + UIInformation.getInstance().mapOffset.x, 
                                      rect.y + UIInformation.getInstance().mapOffset.y);

            UIInformation.getInstance().textPanel.AddJLabel("흑돌이 " + position + " 에 돌을 놨습니다.");
            stones[node.x][node.y].StoneState = mode;
            SetMode(Stone.State.WHITE);
        }
        else
        {
            backGroundPanel.DrawImage(FileReader.LoadImage(UIInformation.getInstance().whiteStoneFilePath), 
                                      rect.x + UIInformation.getInstance().mapOffset.x, 
                                      rect.y + UIInformation.getInstance().mapOffset.y);

            UIInformation.getInstance().textPanel.AddJLabel("백돌이 " + position + "에 돌을 놨습니다.");
            stones[node.x][node.y].StoneState = mode;
            SetMode(Stone.State.BLACK);
        }
    }
}