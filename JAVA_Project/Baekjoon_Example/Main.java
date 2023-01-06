package Baekjoon_Example;

import java.io.*;

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
	}
}