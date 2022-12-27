package Paint.Utility;

import java.awt.Color;

public class ColorLerp 
{
	// Lerp
	public static Color Lerp(Color A, Color B, float Alpha)
	{
		float ar = A.getRed();
		float ab = A.getBlue();
		float ag = A.getGreen();

		float br = B.getRed();
		float bb = B.getBlue();
		float bg = B.getGreen();

		float r = Lerp(ar, br, Alpha);
		float g = Lerp(ab, bb, Alpha);
		float b = Lerp(ag, bg, Alpha);

		return new Color((int)r, (int)g, (int)b);
	}

	public static float Lerp(float A, float B, float Alpha)
	{
		return A * (1 - Alpha) + B * Alpha;
	}
}