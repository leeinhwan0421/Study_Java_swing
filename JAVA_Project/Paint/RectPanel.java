package Paint;

import java.awt.Font;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.AbstractAction;

import Paint.Constants.Constants;
import Paint.UndoRedo.UndoRedo;
import Paint.FileSystem.FileChooser;

public class RectPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
	// Private Value Field
	Constants.ToolMode toolMode = Constants.ToolMode.PENCIL;
	Constants.StrokeMode strokeMode = Constants.StrokeMode.PLAIN;
	
	Point firstPointer = new Point(0, 0);
	Point secondPointer = new Point(0, 0);
	BufferedImage drawField;
	Color colors = Color.black;

	JTextField jtextField;
	
	UndoRedo undoRedo = new UndoRedo();
	FileChooser fileChooser = new FileChooser(this);

	AbstractAction textAction = new AbstractAction() 
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (toolMode == Constants.ToolMode.TEXTEDIT)
				DrawTextField();
		}
	};

	int width;
	int height;
	int minPointx;
	int minPointy;

	// Property Field
	public BufferedImage DrawField() { return drawField; }
	
	public RectPanel() 
	{
        setLayout(null);

		//JButton eraseAllButton = new JButton("전체지우기");

		Dimension d = getPreferredSize();
		drawField = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		setImageBackground(drawField);

		add(CreateUIManager.getInstance().CreateToolButton(this));
		add(CreateUIManager.getInstance().CreateInitButton(this));
		add(CreateUIManager.getInstance().CreateStrokeButton(this));
		add(CreateUIManager.getInstance().CreatePaletteButton(this));
		add(CreateUIManager.getInstance().CreateRedoUndoButton(this));
		add(CreateUIManager.getInstance().CreateFileMenuButton(fileChooser));

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void mousePressed(MouseEvent e) 
	{
		// 다시 클릭됐을경우 좌표 초기화
		undoRedo.GetImageLog(drawField);

		firstPointer.setLocation(0, 0);
		secondPointer.setLocation(0, 0);

		firstPointer.setLocation(e.getX() - Constants.OFFSET_X, e.getY() - Constants.OFFSET_Y);
	}

	public void mouseReleased(MouseEvent e) 
	{
		if (toolMode != Constants.ToolMode.PENCIL)
		{
			secondPointer.setLocation(e.getX() - Constants.OFFSET_X, e.getY() - Constants.OFFSET_Y);
			updatePaint();
		}
	}

	public void actionPerformed(ActionEvent e) 
	{
		String buttonActionCommand = e.getActionCommand();

		if (toolMode == Constants.ToolMode.TEXTEDIT)
			DrawTextField();

		if (buttonActionCommand == "전체지우기")
		{
			setImageBackground(drawField);
			repaint();
		}
		else if (buttonActionCommand == "BOLD" || buttonActionCommand == "PLAIN" || buttonActionCommand == "THIN")
		{
			strokeMode = Constants.SetStrokeMode(buttonActionCommand);
		}
		else if (buttonActionCommand == "연필" || buttonActionCommand == "지우개" || buttonActionCommand == "네모" ||
				 buttonActionCommand == "텍스트" || buttonActionCommand == "동그라미" || buttonActionCommand == "직선" ||
				 buttonActionCommand == "꽉찬네모" || buttonActionCommand == "채우기" || buttonActionCommand == "구현중")
		{
			toolMode = Constants.SetToolMode(e.getActionCommand());
		}
		else if (buttonActionCommand == "UNDO")
		{
			BufferedImage g1 = undoRedo.Undo();

			if (g1 == null) return;

			Graphics2D g = drawField.createGraphics();
			g.drawImage(g1, 0, 0, null);
			g.dispose();
			repaint();
		}
		else if (buttonActionCommand == "REDO")
		{
			BufferedImage g1 = undoRedo.Redo();

			if (g1 == null) return;

			Graphics2D g = drawField.createGraphics();
			g.drawImage(g1, 0, 0, null);
			g.dispose();
			repaint();
		}
		else
		{
			JButton btn = (JButton)e.getSource();

			colors = btn.getBackground();
		}
	}

	public void SetImage(BufferedImage image)
	{
		Graphics2D g = drawField.createGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		repaint();
	}

	public Dimension getPreferredSize() 
	{
		return new Dimension(Constants.WIDTH, Constants.HEIGHT);
	}

	public void updatePaint() 
	{
		width = Math.abs(secondPointer.x - firstPointer.x);
		height = Math.abs(secondPointer.y - firstPointer.y);

		minPointx = Math.min(firstPointer.x, secondPointer.x);
		minPointy = Math.min(firstPointer.y, secondPointer.y);

		Graphics2D g = drawField.createGraphics();

		float stroke =  Constants.GetStrokeValue(strokeMode);

		// draw on paintImage using Graphics
		switch (toolMode) 
		{
		case LINE:
			g.setColor(colors);
			g.setStroke(new BasicStroke(stroke));
			g.drawLine(firstPointer.x, firstPointer.y, secondPointer.x, secondPointer.y);
			break;

		case RECTANGLE:
			g.setColor(colors);
			g.setStroke(new BasicStroke(stroke));
			g.drawRect(minPointx, minPointy, width, height);
			break;

		case FILLRECTANGLE:
			g.setColor(colors);
			g.fillRect(minPointx, minPointy, width, height);
			break;

		case TEXT:
			if (width <= 100) 
				return;
			if (jtextField != null)
				DrawField();

			jtextField = CreateUIManager.getInstance().CreateTextField(minPointx + Constants.OFFSET_X, minPointy + Constants.OFFSET_Y, width, (int)stroke * 6);
			jtextField.setFont(new Font("맑은 고딕", Font.PLAIN,(int)stroke * 5));
			jtextField.setForeground(colors);
			
			KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
			jtextField.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, "ENTER");
			jtextField.getActionMap().put("ENTER", textAction);
			
			add(jtextField);

			toolMode = Constants.ToolMode.TEXTEDIT;
			break;

		case CIRCLE:
			g.setColor(colors);
			g.setStroke(new BasicStroke(stroke));
			g.drawOval(minPointx, minPointy, width, height);
			break;

		case FILL:

		break;

		case PENCIL:
			g.setColor(colors);
			g.setStroke(new BasicStroke(stroke));
			g.drawLine(firstPointer.x, firstPointer.y, secondPointer.x, secondPointer.y);
			break;

		case ERASER:
			g.setColor(Color.white);
			g.setStroke(new BasicStroke(stroke));
			g.drawLine(firstPointer.x, firstPointer.y, secondPointer.x, secondPointer.y);
			break;
		default:
			break;
		}

		g.dispose();
		repaint();
	}

	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(drawField, 10, 120, null);
	}

	public void setImageBackground(BufferedImage bi) 
	{
		this.drawField = bi;
		Graphics2D g = drawField.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
		g.dispose();

		undoRedo.GetImageLog(drawField);
	}

	public void DrawTextField()
	{
		if (jtextField == null) return;

		Graphics2D g = drawField.createGraphics();
		float stroke =  Constants.GetStrokeValue(strokeMode);

		String text = jtextField.getText();

		g.setColor(colors);
		g.setFont(new Font("맑은 고딕", Font.PLAIN, (int)stroke * 5));
		g.drawString(text, jtextField.getBounds().x - Constants.OFFSET_X + (int)stroke * 5,
						   jtextField.getBounds().y - Constants.OFFSET_Y + (int)stroke * 5);

		remove(jtextField);

		g.dispose();
		repaint();

		toolMode = Constants.ToolMode.TEXT;
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		width = Math.abs(secondPointer.x - firstPointer.x);
		height = Math.abs(secondPointer.y - firstPointer.y);

		minPointx = Math.min(firstPointer.x, secondPointer.x);
		minPointy = Math.min(firstPointer.y, secondPointer.y);
		
		if (toolMode == Constants.ToolMode.PENCIL || toolMode == Constants.ToolMode.ERASER) 
		{
			if (secondPointer.x != 0 && secondPointer.y != 0) 
			{
				firstPointer.x = secondPointer.x;
				firstPointer.y = secondPointer.y;
			}
			secondPointer.setLocation(e.getX() - Constants.OFFSET_X, e.getY() - Constants.OFFSET_Y);
			updatePaint();
		} 
		else if (toolMode == Constants.ToolMode.LINE) 
		{
			Graphics g = getGraphics();
			g.drawLine(firstPointer.x + Constants.OFFSET_X, firstPointer.y + Constants.OFFSET_Y,
					   secondPointer.x + Constants.OFFSET_X, secondPointer.y + Constants.OFFSET_Y);

			secondPointer.setLocation(e.getX() - Constants.OFFSET_X, e.getY() - Constants.OFFSET_Y);

			repaint();
			g.dispose();
		} 
		else if (toolMode == Constants.ToolMode.RECTANGLE || toolMode == Constants.ToolMode.FILLRECTANGLE || toolMode == Constants.ToolMode.TEXT) 
		{
			Graphics g = getGraphics();
			g.setColor(Color.BLACK);
			
			g.drawRect(minPointx + Constants.OFFSET_X, minPointy + Constants.OFFSET_Y,
			           width, height);

			secondPointer.setLocation(e.getX() - Constants.OFFSET_X, e.getY() - Constants.OFFSET_Y);
			repaint();
			g.dispose();
		}
		else if (toolMode == Constants.ToolMode.CIRCLE) 
		{
			Graphics g = getGraphics();
			g.setColor(Color.BLACK);
			
			g.drawOval(minPointx + Constants.OFFSET_X, minPointy + Constants.OFFSET_Y,
			           width, height);

			secondPointer.setLocation(e.getX() - Constants.OFFSET_X, e.getY() - Constants.OFFSET_Y);
			
			g.dispose();
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) { }

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }
}