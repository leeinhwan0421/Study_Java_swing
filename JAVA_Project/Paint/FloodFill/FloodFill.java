package Paint.FloodFill;

import java.awt.image.BufferedImage;

import java.util.Stack;

public class FloodFill
{
    class Node
    {
        public Node(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        int x;
        int y;
    }

    Stack<Node> nodes = new Stack<Node>();

    public BufferedImage floodFill(BufferedImage image, int x, int y, int newColor) 
    {
        if (image.getRGB(x, y) == newColor) return image;
        
        flood(image, image.getRGB(x, y), newColor, x, y);
        
        return image;
    }

    public void flood(BufferedImage image, int oldColor, int newColor, int x, int y)
    {
        nodes.push(new Node(x, y));

        while(true)
        {
            if (nodes.empty())
                break;

            Node node = nodes.pop();

            if (node.x < 0 || node.x >= image.getWidth())
                continue;

            if (node.y < 0 || node.y >= image.getHeight())
                continue;

            if (image.getRGB(node.x, node.y) != oldColor)
                continue;

            image.setRGB(node.x, node.y, newColor);

            nodes.push(new Node(node.x + 1, node.y));
            nodes.push(new Node(node.x - 1, node.y));
            nodes.push(new Node(node.x, node.y + 1));
            nodes.push(new Node(node.x, node.y - 1));
        }
    }
}