package Paint.FloodFill;

import java.awt.Graphics2D;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class FloodFill
{
    public static BufferedImage FloodFill(int x, int y, Color targetColor, BufferedImage image)
    {
        int getRGB = image.getRGB(x, y);
        int blue = getRGB & 0xff;
        int green = (getRGB & 0xff00) >> 8;
        int red = (getRGB & 0xff0000) >> 16;

        Color firstColor = new Color(red, green, blue);
        Graphics2D g = image.createGraphics();

        



        g.dispose();
        return image;
    }
}