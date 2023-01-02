package FiveRock.Component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Rectangle;
import java.awt.FontMetrics;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class RoundedButton extends JButton 
{
   Color backGroundColor = Color.WHITE;
   Color borderColor = Color.BLACK;
   Color fontColor = Color.BLACK;

   Stroke stroke = new BasicStroke(1.0f);

   boolean isBorderPaint = false;
   boolean isOpaque = false;

   public RoundedButton() 
   { 
      super(); 
      decorate(); 
   } 

   public RoundedButton(String text) 
   { 
      super(text); 
      decorate(); 
   } 

   public RoundedButton(Action action) 
   { 
      super(action); 
      decorate(); 
   } 

   public RoundedButton(Icon icon) 
   { 
      super(icon); 
      decorate(); 
   } 

   public RoundedButton(String text, Icon icon)
   { 
      super(text, icon); 
      decorate();
   }

   public void setBackGroundColor(Color color)
   {
      backGroundColor = color;
      decorate();
   }

   public void setBorderColor(Color color)
   {
      borderColor = color;
      decorate();
   }

   public void setStrokeSize(float stroke)
   {
      if (stroke <= 0.0f)
         System.out.println("Stroke has underflow");
      else
         this.stroke = new BasicStroke(stroke);
   }

   public void setFontColor(Color color)
   {
      fontColor = color;
      decorate();
   }

   public void setBorderPaintedOption(boolean isTrue)
   {
      isBorderPaint = isTrue;
      decorate();
   }

   public void setOpaqueOption(boolean isTrue)
   {
      isOpaque = isTrue;
      decorate();
   }

   protected void decorate() 
   { 
      setBorderPainted(isBorderPaint); 
      setOpaque(isOpaque); 
   }

   @Override 
   protected void paintComponent(Graphics g) 
   {
      int width = getWidth(); 
      int height = getHeight(); 

      Graphics2D graphics = (Graphics2D) g; 
      graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 

      if (getModel().isArmed()) 
      { 
         graphics.setColor(backGroundColor.darker()); 
      } 
      else if (getModel().isRollover()) 
      { 
         graphics.setColor(backGroundColor.brighter()); 
      } 
      else 
      {
         graphics.setColor(backGroundColor); 
      } 

      graphics.fillRoundRect(0, 0, width, height, 30, 30); 

      if (isBorderPaint)
      {
         graphics.setColor(borderColor);
         graphics.setStroke(stroke);
         graphics.drawRoundRect(0, 0, width, height, 30, 30); 
      }

      FontMetrics fontMetrics = graphics.getFontMetrics(); 
      Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 

      int textX = (width - stringBounds.width) / 2; 
      int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 

      graphics.setColor(fontColor); 
      graphics.setFont(getFont()); 
      graphics.drawString(getText(), textX, textY); 
      graphics.dispose(); 

      super.paintComponent(g); 
   }
}