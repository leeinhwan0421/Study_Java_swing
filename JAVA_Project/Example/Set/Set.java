package Example.Set;

import java.util.*;

public class Set
{
	public static void main(String[] args) 
	{
		HashSet<String> set = new HashSet<String>();

		set.add("a");
		set.add("b");
		set.add("b");
		set.add("c");

		set.remove("b");

		System.out.println("set 크기 확인 : " + set.size());

		Iterator<String> iter = set.iterator();
		while(iter.hasNext())
		{
			System.out.println("반복자 : " + iter.next());
		}
	}
}