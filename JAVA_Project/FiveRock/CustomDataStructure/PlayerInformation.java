package FiveRock.CustomDataStructure;

import javax.swing.JLabel;

public class PlayerInformation
{
    int m_wincount = 0;
    int m_losscount = 0;

    public String m_Color;

    public JLabel nameLabel;
    public JLabel lossCount;
    public JLabel wincount;

    public PlayerInformation(String m_Color, JLabel lossCount, JLabel wincount, JLabel  nameLabel)
    {
        this.m_Color = m_Color;

        this.lossCount = lossCount;
        this.wincount = wincount;
        this.nameLabel = nameLabel;
    }

    public void Init()
    {
        m_wincount = 0;
        m_losscount = 0;

        setLossCountText(0);
        setWincountText(0);
    }

    public boolean isSameName(String name) { return m_Color == name; }

    public void setnameLabelText(String text) { nameLabel.setText(text); }
    
    public void setLossCountText(int count) 
    { 
        m_losscount += count;

        lossCount.setText("패배 : " + m_losscount); 
    }
    
    public void setWincountText(int count) 
    { 
        m_wincount += count;

        wincount.setText("승리 : " + m_wincount); 
    }
}