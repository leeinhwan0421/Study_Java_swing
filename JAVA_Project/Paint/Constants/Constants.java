package Paint.Constants;

// 잡다한 열거형 방식의 코드나, 보안성이 필요한 코드들... ex) 가로 세로와 같은 상수
public class Constants 
{
	public static final String fileFormat = "png";
	public static final String fileDescription = "*.png (이미지 파일)";
	public static final String defaultfilePath = "Paint/Picture";
	public static final String defaulticonPath = "Paint/Icon/Icon.png";

	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 720;

	public static final int WIDTH = 1245;
	public static final int HEIGHT = 675;

	public static final int OFFSET_X = 10;
	public static final int OFFSET_Y = 120;

	public enum ToolMode
	{
		NONE,
		PENCIL,
		ERASER,
		LINE,
		CIRCLE,
		RECTANGLE,
		TEXT,
		TEXTEDIT,
		FILLRECTANGLE,
		FILL
	}

	public enum StrokeMode
	{
		NONE,
		BOLD,
		PLAIN,
		THIN,
	}

	public enum FileMode
	{
		NONE,
		WRITE,
		LOAD,
	}

	public static ToolMode SetToolMode(String str)
	{
		switch(str)
		{
			case "연필":
				return ToolMode.PENCIL;

			case "지우개":
				return ToolMode.ERASER;

			case "직선":
				return ToolMode.LINE;

			case "텍스트":
				return ToolMode.TEXT;

			case "동그라미":
				return ToolMode.CIRCLE;

			case "네모":
				return ToolMode.RECTANGLE;

			case "꽉찬네모":
				return ToolMode.FILLRECTANGLE;

			case "채우기":
				return ToolMode.FILL;
		}

		return ToolMode.NONE;
	}

	public static StrokeMode SetStrokeMode(String str)
	{
		switch(str)
		{
			case "BOLD":
			return StrokeMode.BOLD;

			case "PLAIN":
			return StrokeMode.PLAIN;
			
			case "THIN":
			return StrokeMode.THIN;
		}
		
		return StrokeMode.NONE;
	}
	
	public static FileMode SetFileMode(String str)
	{
		switch(str)
		{
			case "SAVE":
			return FileMode.WRITE;

			case "OPEN":
			return FileMode.LOAD;
		}

		return FileMode.NONE;
	}


	public static float GetStrokeValue(StrokeMode mode)
	{
		switch(mode)
		{
			case BOLD:
				return 10.0f;

			case PLAIN:
				return 4.0f;

			case THIN:
				return 2.0f;

			default:
				return 0.0f;
		}
	}

	public static String GetFileTitle(FileMode mode)
	{
		switch(mode)
		{
			case NONE:
				return null;

			case WRITE:
				return "Save as..";

			case LOAD:
				return "Open File";

			default:
				return null;
		}
	}
}