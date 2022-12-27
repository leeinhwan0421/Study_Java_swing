package Paint.FileSystem;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import Paint.Constants.Constants;
import Paint.RectPanel;

public class FileChooser extends JFrame implements ActionListener
{
    private JFileChooser jfc = new JFileChooser();
    private Constants.FileMode mode = Constants.FileMode.NONE;

    private RectPanel rectPanel;

    int returnVal = 0;

    public FileChooser(RectPanel rectPanel)
    {
        this.rectPanel = rectPanel;

        jfc.setFileFilter(new FileNameExtensionFilter(Constants.fileDescription, Constants.fileFormat));
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.setMultiSelectionEnabled(false);
        jfc.setCurrentDirectory(new File(Constants.defaultfilePath));
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String buttonActionCommand = e.getActionCommand();
		mode = Constants.SetFileMode(buttonActionCommand);

        switch(mode)
        {
            case WRITE:
                if(jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
                {
                    String filepath = jfc.getSelectedFile().toString() + "." + Constants.fileFormat;
                    WriteImage(filepath);
                }
            break;
            case LOAD:
                if(jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
                {
                    File file = jfc.getSelectedFile();
                    LoadImage(file);
                }
            break;
            default:
                break;
        }
    }

    public void LoadImage(File file)
    {
        try
        {
            BufferedImage image = ImageIO.read(file);
            rectPanel.SetImage(image);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void WriteImage(String url)
    {
        try
        {
            BufferedImage image = rectPanel.DrawField();
            RenderedImage rendImage = image;

            if (ImageIO.write(rendImage, Constants.fileFormat, new File(url)))
            {
                System.out.println("Success Write file");
            }
            else
            {
                System.out.println("failed Write file"); // throw False. fix it!
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}