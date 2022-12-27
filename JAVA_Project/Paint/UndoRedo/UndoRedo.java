package Paint.UndoRedo;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import java.util.Stack;

public class UndoRedo
{
    private final int maxSize = 510;

    public Stack<BufferedImage> prev = new Stack<BufferedImage>();
    public Stack<BufferedImage> next = new Stack<BufferedImage>();

    public UndoRedo()
    {
        prev.setSize(maxSize / 2);
        next.setSize(maxSize / 2);
    }
    
    public void GetImageLog(BufferedImage img)
    {
        BufferedImage imageForStack = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        Graphics2D g2d = imageForStack.createGraphics();
        g2d.drawImage(img, 0, 0, null);

        prev.push(imageForStack);

        if (!next.empty())
            next.clear();
    }

    public BufferedImage Undo()
    {
        if (prev.size() == 0)
        return null;
        
        BufferedImage prevImg = prev.pop();
        
        if (prevImg == null)
            return null;

        next.push(prevImg);
        return prevImg; 
    }

    public BufferedImage Redo()
    {
        if (next.empty())
            return null;

        BufferedImage nextImg = next.pop();

        if (nextImg == null)
            return null;

        prev.push(nextImg);
        return nextImg; 
    }
}