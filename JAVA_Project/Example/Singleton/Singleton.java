package Example.Singleton;

public class Singleton
{
	private static Singleton instance;

	private Singleton() {}

	public void Print()
	{
		System.out.println("called Singleton.Print()");
	}

	public static Singleton getInstance()
	{
		if (instance == null) instance = new Singleton();

		return instance;
	}
}