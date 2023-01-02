package FiveRock.UI;

import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Component;

import java.util.Queue;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class TextPanel extends JPanel
{
    Queue<Component> jLabels = new LinkedList<Component>();
    int queueMaxsize = 9;

    public TextPanel()
    {
		setLayout(new GridLayout(queueMaxsize, 1, 0, 0));
		setBounds(892, 240, 272, 400);
        setOpaque(false);
        setBackground(new Color(0,0,0));
        setBorder(new LineBorder(Color.black));

        AddJLabel("샘플 텍스트 YES OR NOT");
    }
    
    public void AddJLabel(String text)
    {
        JLabel mainTextField = new JLabel();
        
        mainTextField.setVerticalAlignment(JLabel.TOP);
        mainTextField.setFont(new Font("CookieRun Regular", Font.PLAIN, 15));
        mainTextField.setText(text);

        if (jLabels.size() >= queueMaxsize)
            remove(jLabels.poll());
        
        jLabels.add(add(mainTextField));
        updateUI();
    }
}