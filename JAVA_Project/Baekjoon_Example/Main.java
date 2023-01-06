package Baekjoon_Example;

import java.io.*;
<<<<<<< Updated upstream

// 7682

// X = X, O = O , . = empty
// 3*3 field
// First X, Second O loop

// can, vaild/ can't, invaild

public class Main
{
	public static boolean IsTiktakto(String str)
	{
		int xCount = 0;
		int oCount = 0;
		int emptyCount = 0;

		for (int i = 0; i < str.length(); i++)
		{
			if (str.charAt(i) == 'O')
				oCount++;
			else if (str.charAt(i) == 'X')
				xCount++;
			else
				emptyCount++;
		}

		String f_line = str.substring(0, 2);
		String s_line = str.substring(3, 5);
		String t_line = str.substring(6, 8);

		String arr[] = {f_line, s_line, t_line};

		
		if (xCount == oCount && xCount == oCount + 1)
		{
			for (int i = 0; i < arr.length; i++)
			{
				if (arr[i] == "XXX" || arr[i] == "OOO")
					return true;

				if (f_line.charAt(i) == s_line.charAt(i) && s_line.charAt(i) == t_line.charAt(i))
					return true;
			}

			//대각선처리
			//if (f_line.charAt(0) )
		}


		return false;
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true)
		{
			String str = br.readLine();

			if (str == "end")
				break;

			if (IsTiktakto(str))
				System.out.println("valid");
			else
				System.out.println("invalid");
		}

		br.close();
=======

public class Main
{
	public static boolean isTiktakto(String str)
	{
		int xCount = 0, oCount = 0, emptyCount = 0;

		for (int i = 0; i < str.length(); i++)
		{
			switch(str.charAt(i))
			{
				case 'X':
					xCount++;
				break;
				case 'O':
					oCount++;
				break;
				default:
					emptyCount++;
				break;
			}
		}

		String f_line = str.substring(0, 3);
		String s_line = str.substring(3, 6);
		String t_line = str.substring(6, 9);

		if (xCount == oCount)
		{
			if (f_line == "XXX" || s_line == "XXX" || t_line == "XXX")
				return false;

			else if (f_line == "OOO" || s_line == "000" || t_line == "000")
				return true;

			for (int i = 0; i < 3; i++)
			{ 
				if (f_line.charAt(i) == 'X' && s_line.charAt(i) == 'X' && t_line.charAt(i) == 'X')
					return false;
			}
			for (int i = 0; i < 3; i++)
			{
				if (f_line.charAt(i) == 'O' && s_line.charAt(i) == 'O' && t_line.charAt(i) == 'O')
					return true;
			}

			if ((f_line.charAt(0) == 'O' && s_line.charAt(1) == 'O' && t_line.charAt(2) == 'O') ||
				(f_line.charAt(2) == 'O' && s_line.charAt(1) == 'O' && t_line.charAt(0) == 'O'))
				return true;
		}

		else if (xCount == oCount + 1)
		{
			if (f_line == "OOO" || s_line == "000" || t_line == "000")
				return false;
			else if (f_line == "XXX" || s_line == "XXX" || t_line == "XXX")
				return true;

			for (int i = 0; i < 3; i++)
			{ 
				if (f_line.charAt(i) == 'O' && s_line.charAt(i) == 'O' && t_line.charAt(i) == 'O')
					return false;
			}
			for (int i = 0; i < 3; i++)
			{ 
				if (f_line.charAt(i) == 'X' && s_line.charAt(i) == 'X' && t_line.charAt(i) == 'X')
					return true;
			}

			if ((f_line.charAt(0) == 'X' && s_line.charAt(1) == 'X' && t_line.charAt(2) == 'X') ||
				(f_line.charAt(2) == 'X' && s_line.charAt(1) == 'X' && t_line.charAt(0) == 'X'))
				return true;
		}

		if (emptyCount == 0 && xCount == oCount + 1)
			return true;

		return false;
	}

	public static void main(String[] args) throws IOException
	{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true)
		{
			String str = br.readLine();

			if (str.length() != 9)
				break;
			else if (isTiktakto(str))
				System.out.println("valid");
			else
				System.out.println("invalid");
		}
>>>>>>> Stashed changes
	}
}