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
		if (instance == null) 
            instance = new UIInformation();

		return instance;
	}

    public Dimension windowSize = new Dimension(1200, 898);

    public String backgroundFilePath = "FiveRock/ImageSource/UI/BackGrounds/Background.png";
    public String iconFilePath = "FiveRock/ImageSource/UI/Icons/Icon.png";
    public String whiteStoneFilePath = "FiveRock/ImageSource/Objects/whiteStone.png";
    public String blackStoneFilePath = "FiveRock/ImageSource/Objects/blackStone.png";

    public Color backGroundColor = new Color(224, 189, 102);

    // 좌표를 채팅에 띄울꺼다.
    public char positionX[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T'};
    public int  positionY[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};

    public Node mapSizePixelRate = new Node(796, 796);
    public Node mapOffset = new Node(38, 38);
    public Node mapSize= new Node(19, 19);

    public TextPanel textPanel = new TextPanel();
    public CharacterPanel characterPanel = new CharacterPanel();
    public CharacterInformationPanel blackCharacterInfoPanel = new CharacterInformationPanel("Black [ 흑돌 ]", 892, 695, 136, 136);
    public CharacterInformationPanel whiteCharacterInfoPanel = new CharacterInformationPanel("White [ 백돌 ]", 1028, 695, 136, 136);
}
