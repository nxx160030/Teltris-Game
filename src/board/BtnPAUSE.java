package board;


import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

public class BtnPAUSE{

	Point origin;
	int width, height;
	Graphics g;
	final static String STRING_PAUSE = "PAUSE";
	boolean hide = false;
	
	public BtnPAUSE(Graphics g, Point origin, int pause_width, int pause_height) {
		
		this.g = g;
		this.origin = origin;
		width = pause_width;
		height = pause_height;

	}
	
	public void drawTextPAUSE(){
		
		// draw the rectangle
		g.setColor(new Color(51,153,255));
		g.drawRect((int)origin.getX(), (int)origin.getY(), width, height);
		
		// set the font
		int font_size = Math.round((float)(height*0.5));
		Font font = new Font("Arial",Font.BOLD, font_size);
		g.setFont(font);		
		// put the text in the center of rectangle
		FontMetrics fm = g.getFontMetrics();
		
		int offset_x = Math.round((float)(origin.getX() + (width-fm.stringWidth(STRING_PAUSE))*0.5));
		int offset_y = Math.round((float)(origin.getY() + height*0.5 + fm.getHeight()*0.3));
		
		// draw the text "PAUSE"
		g.drawString(STRING_PAUSE, offset_x, offset_y);
		hide = true;
	}


}
