package FiveRock.FileSystem;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileReader
{
    public static BufferedImage LoadImage(File file)
    {
        BufferedImage image = null;

        try { image = ImageIO.read(file); }
        catch (IOException e) { e.printStackTrace(); }

        return image;
    }
}