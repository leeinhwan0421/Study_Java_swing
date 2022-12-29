package Paint;

import Paint.FileSystem.FileChooser;
import Paint.Utility.ColorLerp;

import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import javax.swing.border.EtchedBorder;

// JPanel을 리턴하는 클래스로 사용할꺼임.
// Singleton 사용으로 모든 클래스에서 참조가 가능하게끔 생성할 예정

// 이걸 굳이 사용하는 이유는 버튼이 너무 많아지기 때문에
// RectPanel의 코드가 너무 늘어나게 되어버리면 곤란해지기 때문이다.

public class CreateUIManager
{
	private static CreateUIManager buttonManager;

	public static CreateUIManager getInstance()
	{
		if (buttonManager == null) 
			buttonManager = new CreateUIManager();

		return buttonManager;
	}

	public JPanel CreateToolButton(RectPanel rectPanel) // Tool Button 만들어서 리턴합니다!!!
	{
		JPanel toolButtonPanel = new JPanel();
		toolButtonPanel.setLayout(new GridLayout(3,3,10,10));
		toolButtonPanel.setBounds(10, 10, 300, 100);

		String button_names[] = {"연필", "지우개", "네모", "텍스트", "동그라미", "직선", "꽉찬네모", "채우기", "도형제어"};
		JButton[] buttons = new JButton[button_names.length];

		for (int i = 0; i < buttons.length; i++)
		{
			buttons[i] = new JButton(button_names[i]);
		
			buttons[i].addActionListener(rectPanel);
			buttons[i].setFont(new Font("맑은 고딕", Font.BOLD, 14));
			buttons[i].setBorderPainted(false);
		
			buttons[i].setBackground(Color.white);
			buttons[i].setForeground(Color.BLACK);
		
			toolButtonPanel.add(buttons[i]);
		}

		return toolButtonPanel;
	}

	public JPanel CreateInitButton(RectPanel rectPanel)
	{
		JPanel InitButtonPanel = new JPanel();
		InitButtonPanel.setLayout(new GridLayout(1,1,10,10));
		InitButtonPanel.setBounds(320, 10, 150, 100);

		String button_name = "전체지우기";

		JButton buttons = new JButton(button_name);
		buttons.addActionListener(rectPanel);
		buttons.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		buttons.setBorderPainted(false);
		buttons.setBackground(Color.white);
		buttons.setForeground(Color.BLACK);

		InitButtonPanel.add(buttons);

		return InitButtonPanel;
	}

	public JPanel CreateStrokeButton(RectPanel rectPanel)
	{
		JPanel strokeButtonPanel = new JPanel();
		strokeButtonPanel.setLayout(new GridLayout(3,1,10,10));
		strokeButtonPanel.setBounds(480, 10, 80, 100);

		String button_names[] = {"BOLD", "PLAIN", "THIN"};
		JButton[] buttons = new JButton[button_names.length];

		for (int i = 0; i < buttons.length; i++)
		{
			buttons[i] = new JButton(button_names[i]);
		
			buttons[i].addActionListener(rectPanel);
			buttons[i].setFont(new Font("맑은 고딕", Font.BOLD, 14));
			buttons[i].setBorderPainted(false);
		
			buttons[i].setBackground(Color.white);
			buttons[i].setForeground(Color.BLACK);
		
			strokeButtonPanel.add(buttons[i]);
		}
		return strokeButtonPanel;
	}

	public JPanel CreatePaletteButton(RectPanel rectPanel)
	{
		JPanel paletteButtonPanel = new JPanel();
		paletteButtonPanel.setLayout(new GridLayout(3,15,10,10));
		paletteButtonPanel.setBounds(570, 10, 500, 100);

		Color color_list[] = {new Color(255, 0, 0), new Color(255, 94, 0), new Color(255, 128, 0),
							  new Color(255, 196, 0), new Color(251, 255, 0), new Color(106, 255, 0),
							  new Color(0, 255, 153), new Color(0, 255, 255), new Color(0, 204, 255),
							  new Color(0, 102, 255), new Color(0, 0, 255), new Color(102, 0, 255),
							  new Color(204, 0, 255), new Color(255, 0, 204), new Color(255, 255, 255)};

		float alphaValue[] = {0.0f, 0.2f, 0.3f};

		JButton[] buttons = new JButton[color_list.length * alphaValue.length];

		for (int i = 0; i < alphaValue.length; i++)
		{
			for (int j = 0; j < color_list.length; j++)
			{
				buttons[i] = new JButton();

				buttons[i].addActionListener(rectPanel);
				buttons[i].setBorderPainted(true);

				if ((i + 1) * (j + 1) == color_list.length * alphaValue.length)
					buttons[i].setBackground(new Color(0,0,0));
				else
					buttons[i].setBackground(ColorLerp.Lerp(color_list[j], new Color(0,0,0), alphaValue[i]));

				paletteButtonPanel.add(buttons[i]);
			}
		}

		return paletteButtonPanel;
	}

	public JPanel CreateRedoUndoButton(RectPanel rectPanel)
	{
		JPanel redoundoButtonPanel = new JPanel();

		redoundoButtonPanel.setLayout(new GridLayout(2,1,10,10));
		redoundoButtonPanel.setBounds(1080, 10, 80, 100);

		String button_names[] = {"REDO", "UNDO"};
		JButton[] buttons = new JButton[button_names.length];

		for (int i = 0; i < buttons.length; i++)
		{
			buttons[i] = new JButton(button_names[i]);
		
			buttons[i].addActionListener(rectPanel);
			buttons[i].setFont(new Font("맑은 고딕", Font.BOLD, 14));
			buttons[i].setBorderPainted(false);
			buttons[i].setBackground(Color.white);
			buttons[i].setForeground(Color.BLACK);
		
			redoundoButtonPanel.add(buttons[i]);
		}

		return redoundoButtonPanel;
	}

	public JPanel CreateFileMenuButton(FileChooser fileChooser)
	{
		JPanel fileMenuButtonPanel = new JPanel();

		fileMenuButtonPanel.setLayout(new GridLayout(2,1,10,10));
		fileMenuButtonPanel.setBounds(1170, 10, 80, 100);

		String button_names[] = {"SAVE", "OPEN"};
		JButton[] buttons = new JButton[button_names.length];

		for (int i = 0; i < buttons.length; i++)
		{
			buttons[i] = new JButton(button_names[i]);
		
			buttons[i].addActionListener(fileChooser);
			buttons[i].setFont(new Font("맑은 고딕", Font.BOLD, 14));
			buttons[i].setBorderPainted(false);
			buttons[i].setBackground(Color.white);
			buttons[i].setForeground(Color.BLACK);
		
			fileMenuButtonPanel.add(buttons[i]);
		}

		return fileMenuButtonPanel;
	}

	public JTextField CreateTextField(int x, int y, int width, int height)
	{
		JTextField textField = new JTextField();
		
		textField.setEditable(true);
		textField.setOpaque(false);
		textField.setBackground(new Color(0,0,0));
		textField.setHorizontalAlignment(JTextField.LEFT);
		textField.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		textField.setBounds(x, y, width, height);
		textField.setText("텍스트를 입력하세요");

		return textField;
	}
}