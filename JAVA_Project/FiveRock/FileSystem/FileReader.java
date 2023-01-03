package FiveRock.FileSystem;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.awt.Image;

import java.io.File;
import java.io.IOException;

public class FileReader
{
    public static BufferedImage LoadBufferedImage(String filePath)
    {
        BufferedImage image = null;
        File file = new File(filePath);
        try 
        { 
            image = ImageIO.read(file); 
        }
        catch (IOException e) 
        {
            e.printStackTrace(); 
            System.out.println("file is null : " + file.getAbsolutePath());
        }

        return image;
    }

    public static Image LoadImage(String filePath)
    {
        Image image = null;
        File file = new File(filePath);
        try 
        { 
            image = ImageIO.read(file); 
        }
        catch (IOException e) 
        {
            e.printStackTrace(); 
            System.out.println("file is null : " + file.getAbsolutePath());
        }

        return image;
    }
}