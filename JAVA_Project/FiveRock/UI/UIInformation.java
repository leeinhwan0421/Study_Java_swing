package FiveRock.UI;

import java.awt.Color;
import java.awt.Dimension;

import FiveRock.CustomDataStructure.Node;

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

    public Dimension windowSize = new Dimension(1200, 898);

    public String backgroundFilePath = "FiveRock/ImageSource/Background.png";
    public String iconFilePath = "FiveRock/ImageSource/Icon.png";

    public Color backGroundColor = new Color(224, 189, 102);

    // 좌표를 채팅에 띄울꺼다.
    public char positionX[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T'};
    public int  positionY[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};

    public Node mapSizePixelRate = new Node(784, 784);
    public Node mapOffset = new Node(43, 43);
    public Node mapSize= new Node(19, 19);
}
