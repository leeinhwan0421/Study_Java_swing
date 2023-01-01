package FiveRock.UI;

import java.awt.Dimension;

public class UIInformation
{
    /// Singleton pattern

    private static UIInformation instance;

	private UIInformation() {}

	public static UIInformation getInstance()
	{
		if (instance == null) instance = new UIInformation();

		return instance;
	}

    public Dimension windowSize = new Dimension(1100, 898);

    public String backgroundFilePath = "FiveRock/ImageSource/Background.png";
    public String iconFilePath = "FiveRock/ImageSource/Icon.png";

    public int mapWidth = 19;
    public int mapHeight = 19;
}
