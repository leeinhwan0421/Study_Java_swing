package Example.HashTable;

import java.util.Hashtable;;

public class HashTable
{
	public static void main(String[] args) 
	{
		Hashtable<Integer, String> ht = new Hashtable<Integer, String>();

		System.out.println(ht.size());

		ht.put(0, "new Clone1");
		ht.put(1, "new Clone2");
		ht.put(2, "new Clone3");

		ht.replace(2, "replaced new Clone3");

		ht.remove(2);

		for (int i = 0; i < ht.size(); i++)
		{
			System.out.println(ht.get(i));
		}

		System.out.println("HashTable 크기 : " + ht.size());
		System.out.println("HashTable Key 확인 : " + ht.containsKey(1));
		System.out.println("HashTable Value 확인 : " + ht.containsValue("new Clone2"));
		System.out.println("HashTable 크기 0인지 확인 : " + ht.isEmpty());
		System.out.println("HashTable 키값 확인 : " + ht.keySet());
	}
}