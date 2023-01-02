package FiveRock.Listeners;

public class ListenerInformation
{
    /// Singleton pattern

    private static ListenerInformation instance;

	private ListenerInformation() {}

	public static ListenerInformation getInstance()
	{
		if (instance == null) instance = new ListenerInformation();

		return instance;
	}

    public MainButtonListener mainButtonListener = new MainButtonListener(null);
    public StoneButtonListener stoneButtonListener = new StoneButtonListener(null);
}
