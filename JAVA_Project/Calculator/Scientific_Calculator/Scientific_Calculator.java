package Calculator.Scientific_Calculator;

import javax.swing.*;

import Calculator.Scientific_Calculator.Constants.Operator_Mode;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Scientific_Calculator extends JFrame
{
	private JTextField inputSpace;
	private JTextField operatorSpace;

	DecimalFormat df = new DecimalFormat("#.####"); 			// 소숫점 4자리까지만 연산하게 할것입니다...

	public Scientific_Calculator() 
    {
		// 참고 Link : https://code-review.tistory.com/84

        setLayout(null);

		inputSpace = new JTextField();
		inputSpace.setEditable(false);
		inputSpace.setBackground(Color.white);
		inputSpace.setHorizontalAlignment(JTextField.RIGHT);
		inputSpace.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		inputSpace.setBorder(null);
		inputSpace.setBounds(8, 10, 370, 120);

		operatorSpace = new JTextField();
		operatorSpace.setEditable(false);
		operatorSpace.setBackground(Color.white);
		operatorSpace.setHorizontalAlignment(JTextField.RIGHT);
		operatorSpace.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		operatorSpace.setBorder(null);
		operatorSpace.setBounds(8, 130,  370, 20);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5,4,10,10));
		buttonPanel.setBounds(8, 160, 370, 285);

		String button_names[] = { "C", "÷", "*", "←", 
								  "1", "2", "3", "+", 
								  "4", "5", "6", "%", 
								  "7", "8", "9", "-", 
								  "x²", "0", "x½", "=" };

		JButton[] buttons = new JButton[button_names.length];

		for (int i = 0; i < button_names.length; i++) 
		{
			buttons[i] = new JButton(button_names[i]);
			buttons[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));

			if (button_names[i] == "C") buttons[i].setBackground(Color.RED);
			else if ((i >= 4 && i <= 6) || (i >= 8 && i <= 10) || (i >= 12 && i <= 14) || i == 17) buttons[i].setBackground(Color.BLACK);
			else buttons[i].setBackground(Color.GRAY);
			
			buttons[i].setForeground(Color.WHITE);
			buttons[i].setBorderPainted(false);
			buttons[i].addActionListener(new PadActionListener());
			buttonPanel.add(buttons[i]);
		}

		add(operatorSpace);
		add(inputSpace);
		add(buttonPanel);

		setTitle(Constants.title_name);
		setVisible(true);
		setSize(400,500);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public class PadActionListener implements ActionListener
	{
		String text = "";

		public void actionPerformed(ActionEvent e) 
		{
			String input = e.getActionCommand();
			Constants.Run_Mode run_Mode = Constants.Run_Mode.NONE;
			switch (input)
			{
				case "C":   			// Initalized
					Init();
				break;
				case "←":				// BackSpace
					BackSpace();
				break;
				case "=":
					text = inputSpace.getText();
					run_Mode = Constants.Run_Mode.EQUAL;
					if (!Constants.Requirements(text, run_Mode))
						return;
					
					inputSpace.setText(calculate());	
				break;							
				default:
					text = inputSpace.getText();

					if (Character.isDigit(input.charAt(0)))
					{
						run_Mode = Constants.Run_Mode.INPUT_NUMBER;

						if (!Constants.Requirements(text, run_Mode))						
							return;   
					}
					else
					{
						run_Mode = Constants.Run_Mode.INPUT_OPERATOR;

						if (!Constants.Requirements(text, run_Mode))
							return;
					}

					input = Constants.Modified_Input(input);
					inputSpace.setText(inputSpace.getText() + input);
				break;
			}
		}

		public void BackSpace()
		{
			String str = inputSpace.getText();

			if (str.length() == 0) return;
			inputSpace.setText(str.substring(0, str.length() - 1));
		}

		public void Init()
		{
			inputSpace.setText("");
			operatorSpace.setText("");
		}
	}

	public ArrayList<String> Modified_String(String str)
	{
		ArrayList<String> arr = new ArrayList<String>();
		String num = "";

		for (int i = 0; i < str.length(); i++)
		{
			char ch = str.charAt(i);
			if (ch == '-' || ch == '+' || ch == '*' || ch == '÷' || ch == '²' || ch == '%' || ch == '½') 
			{
				if (num != "")
					arr.add(num);

				arr.add(Character.toString(ch));

				num = "";
			}
			else
			{
				num = num + Character.toString(ch);
			}
		}

		arr.add(num);
		arr.remove("");
		return arr;
	}

	public String calculate() 
	{
		double cur = 0;
		double prev = 0;

		String text = inputSpace.getText();
		operatorSpace.setText(text + " = ");

		ArrayList<String> arr = Modified_String(text);

		Constants.Operator_Mode mode = Constants.Operator_Mode.NONE;

		for (int i  = 0; i < arr.size(); i++)
		{
			String str = arr.get(i);

			if (str.equals("²"))
			{
				mode = Constants.SetOperator_Mode(str);

				double left = Double.parseDouble(arr.get(i - 1));
				double result = left * left;

				arr.add(i + 1, formatD(result));

				arr.remove(i - 1);
				arr.remove(i - 1);
				--i;
			}
			else if (str.equals("%"))
			{
				mode = Constants.SetOperator_Mode(str);

				double left = Double.parseDouble(arr.get(i - 1));
				double result = left / 100;

				arr.add(i + 1, formatD(result));

				arr.remove(i - 1);
				arr.remove(i - 1);
				--i;
			}
			else if (str.equals("½"))
			{
				mode = Constants.SetOperator_Mode(str);

				double left = Double.parseDouble(arr.get(i - 1));
				double result = Math.sqrt(left);

				arr.add(i + 1, formatD(result));

				arr.remove(i - 1);
				arr.remove(i - 1);
				--i;
			}
		}

		for (int i = 0; i < arr.size(); i++)
		{
			String str = arr.get(i);

			if (str.equals("*") || str.equals("÷") || str.equals("-") || str.equals("+"))
			{
				mode = Constants.SetOperator_Mode(str);
			}
			else if ((mode == Operator_Mode.MULTI || mode == Operator_Mode.DIV) && !str.equals("+") && !str.equals("-")) 
			{
				double left = Double.parseDouble(arr.get(i - 2));
				double right = Double.parseDouble(arr.get(i));
				double result = 0;

				if (mode == Operator_Mode.MULTI)
				{
					result = left * right;
				}
				else if (mode == Operator_Mode.DIV)
				{
					result = left / right;
				}
				
				arr.add(i + 1, formatD(result));

				for (int j = 0; j < 3; j++)
					arr.remove(i - 2);

				i -= 2;
			}
		}

		mode = Operator_Mode.NONE;

		for (int i = 0; i < arr.size(); i++)
		{
			String str = arr.get(i);

			if (str.equals("-") || str.equals("+"))  // 나눗셈과 곱셈은 사라진 상태임
			{
				mode = Constants.SetOperator_Mode(str);
			}
			else
			{
				cur = Double.parseDouble(str);

				if (mode == Operator_Mode.PLUS)
				{
					prev += cur;
				}
				else if (mode == Operator_Mode.SUB)
				{
					prev -= cur;
				}
				else
				{
					prev = cur;
				}
			}
		}

		return formatD(prev);
	}

	// 소수점이 12.0 같이 나왔을 때, 0을 지워주는 방법
	public String formatD(double number) 
	{
		 return df.format(number);
	}
}