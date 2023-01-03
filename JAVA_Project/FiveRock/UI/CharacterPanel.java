package FiveRock.UI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import FiveRock.FileSystem.FileReader;

public class CharacterPanel extends JPanel
{
    public String blackStoneCharacterFilePath = "FiveRock/ImageSource/UI/PlayerIcon/Character_Black.png";
    public String whiteStoneCharacterFilePath = "FiveRock/ImageSource/UI/PlayerIcon/Character_White.png";

    public CharacterPanel()
    {
		setLayout(new GridLayout(1, 2, 0, 0));
		setBounds(892, 540, 272, 136);
        setOpaque(false);
        setBackground(new Color(0,0,0));

        AddCharatcher();
    }

    public void AddCharatcher()
    {
        JLabel jLabel = new JLabel();
        jLabel.setIcon(new ImageIcon(FileReader.LoadImage(blackStoneCharacterFilePath)));
        add(jLabel);

        JLabel jLabel2 = new JLabel();
        jLabel2.setIcon(new ImageIcon(FileReader.LoadImage(whiteStoneCharacterFilePath)));
        add(jLabel2);
    }
}