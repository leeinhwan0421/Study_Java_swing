package Baekjoon_Example;

import java.util.*;

public class Main
{
	class human
	{
		int height;
		int weight;

		public human(int height, int weight)
		{
			this.height = height;
			this.weight = weight;
		}

		public boolean compareHuman(human other)
		{
			if (height < other.height && weight < other.weight)
				return true;

			return false;
		}
	}
    public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		
		sc.close();
    }
}