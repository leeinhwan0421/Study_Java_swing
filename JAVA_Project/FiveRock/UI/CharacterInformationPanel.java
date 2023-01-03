package FiveRock.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import FiveRock.CustomDataStructure.PlayerInformation;;

public class CharacterInformationPanel extends JPanel
{
    PlayerInformation player;

    public PlayerInformation GetPlayerInformation()
    {
        return player;
    }

    String name;

    public CharacterInformationPanel(String name, int x, int y, int width, int height)
    {
		setBounds(x, y, width, height);
		setLayout(new GridLayout(3, 1, 0, 0));
        setOpaque(false);
        setBackground(new Color(0,0,0));

        this.name = name;

        AddJLabel();
    }

    public void AddJLabel()
    {
        JLabel losscount;
        JLabel wincount;
        JLabel nameLabel;

        wincount = new JLabel();
        losscount = new JLabel();
        nameLabel = new JLabel();

        nameLabel.setVerticalAlignment(JLabel.TOP);
        nameLabel.setFont(new Font("CookieRun Regular", Font.PLAIN, 20));
        nameLabel.setOpaque(false);
        nameLabel.setBackground(new Color(0, 0, 0));
        nameLabel.setText(name);

        losscount.setVerticalAlignment(JLabel.TOP);
        losscount.setFont(new Font("CookieRun Regular", Font.PLAIN, 20));
        losscount.setOpaque(false);
        losscount.setBackground(new Color(0, 0, 0));
        losscount.setText("패배 : 0");

        wincount.setVerticalAlignment(JLabel.TOP);
        wincount.setFont(new Font("CookieRun Regular", Font.PLAIN, 20));
        wincount.setOpaque(false);
        wincount.setBackground(new Color(0, 0, 0));
        wincount.setText("승리 : 0");

        player = new PlayerInformation(name, losscount, wincount,  nameLabel);

        add(player.nameLabel);
        add(player.lossCount);
        add(player.wincount);
    }
}