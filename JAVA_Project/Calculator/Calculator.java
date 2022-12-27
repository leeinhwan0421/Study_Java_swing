package Calculator;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class Calculator extends JFrame
{
	private JTextField inputSpace;
	private JTextField operatorSpace;

	private String leftOperand = "";							// 왼쪽 피연산자
	private String operator = ""; 								// 연산자 기호
	private String rightOperand = "";						 	// 오른쪽 피연산자

	DecimalFormat df = new DecimalFormat("#.####"); 			// 소숫점 4자리까지만 연산하게 할것입니다...

	public Calculator() 
    {
		// 참고 Link : https://code-review.tistory.com/84

        setLayout(null);

		inputSpace = new JTextField();
		inputSpace.setEditable(false);
		inputSpace.setBackground(Color.white);
		inputSpace.setHorizontalAlignment(JTextField.RIGHT);
		inputSpace.setFont(new Font("맑은 고딕", Font.BOLD, 50));
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
								  "x²", "0", "²√x", "=" };

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

		setTitle("calculator");
		setVisible(true);
		setSize(400,500);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class PadActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String input = e.getActionCommand();

			if (input.equals("C"))
			{
				// 초기화문
				Init();
			}
			else if (input.equals("="))
			{
				if (operator != "")
				{
					if (rightOperand == "") // 예외처리
					{
						rightOperand = inputSpace.getText();
					}
					// 연산자 공간에 식을 전개해서 넣고 입력 공간에는 값을 출력한다.
					operatorSpace.setText(leftOperand + " " + operator + " " + rightOperand + " = ");
					inputSpace.setText(calculate());
				}
			}
			else if (input.equals("÷") || 
					 input.equals("*") || 
					 input.equals("+") || 
					 input.equals("-"))
			{
				// 기호를 받았으면 우측 피연산자를 제거해야한다
				rightOperand = "";

				// 기호를 받았으면 좌측 피연산자를 변수에 넣는다
				leftOperand = inputSpace.getText();
				operator = input;

				// 연산자 공간에 텍스트를 집어넣고, 입력 공간의 텍스트를 지운다
				inputSpace.setText("");
				operatorSpace.setText(leftOperand + " " + operator);
			}
			else if (input.equals("←"))
			{
				String str = inputSpace.getText();
				
				if (str.length() == 0) return;
				inputSpace.setText(str.substring(0, str.length() - 1));
			}
			else if (input.equals("x²"))
			{
				String text = inputSpace.getText();
				double num1 = Double.parseDouble(text);
				inputSpace.setText(formatD(num1 * num1));
				operatorSpace.setText(text + "² = ");

			}
			else if (input.equals("²√x"))
			{
				String text = inputSpace.getText();
				double num1 = Double.parseDouble(text);
				inputSpace.setText(formatD(Math.sqrt(num1)));
				operatorSpace.setText("√" + text + " = ");
			}
			else if (input.equals("%"))
			{
				String text = inputSpace.getText();
				double num1 = Double.parseDouble(text);
				inputSpace.setText(formatD(num1 / 100));

				operatorSpace.setText(text + " / 100 = ");
			}
			else
			{
				// 숫자 입력시 제한 글자 수를 확인하고 추가를 하던지 리턴을 하던지 결정하는 로직입니다.
				if (inputSpace.getText().length() >= 10) return;

				// 숫자 입력시 숫자가 추가만 되면 된다
				inputSpace.setText(inputSpace.getText() + input);
			}
		}

		public void Init()
		{
			inputSpace.setText("");
			operatorSpace.setText("");
			
			leftOperand = "";
			operator = "";
			rightOperand = "";
		}
	}

	public String calculate() 
	{
		double num1 = Double.parseDouble(leftOperand);
		double num2 = Double.parseDouble(rightOperand);

		double result = 0;

		if(operator.equals("+"))
		{
			result = num1 + num2;
		}
		else if(operator.equals("-"))
		{
			result = num1 - num2;
		}
		else if(operator.equals("*"))
		{
			result = num1 * num2;
		}
		else if (operator.equals("÷"))
		{
			result = num1 / num2;
		}

		// 연속으로 =버튼를 누르게 되면 이전 연산자와 피연산자로 계산이 될 수 있도록 좌측 피연산자에 result 할당
		leftOperand = formatD(result);
		return formatD(result);
	}

	// 소수점이 0.0 같이 나왔을 때, 0을 지워주는 방법
	public String formatD(double number) 
	{
		 return df.format(number);
	}
	
	public static void main(String[] args) 
	{
		new Calculator();
	}
}