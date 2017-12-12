package board;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

public class BtnQuit{

	Point origin;
	int width, height;
	Graphics g;
	final static String STRING_QUIT = "QUIT";
	
	public BtnQuit(Graphics g, Point origin, int width, int height) {		
		this.g = g;
		this.origin = origin;
		this.width = width;
		this.height = height;
	}
	
	public void drawBtnQuit(){		
		// draw the rectangle
		g.setColor(Color.BLACK);
		g.drawRect(origin.x, origin.y, width, height);
		
		// set the font
		int font_size = Math.round((float)(height*0.6));
		Font font = new Font("Arial",Font.BOLD, font_size);
		g.setFont(font);		
		// put the text in the center of rectangle
		FontMetrics fm = g.getFontMetrics();
		
		int offset_x = Math.round((float)(origin.x + (width-fm.stringWidth(STRING_QUIT))*0.5));
		int offset_y = Math.round((float)(origin.y + height*0.5 + fm.getHeight()*0.3));
		
		// draw the text "PAUSE"
		g.drawString(STRING_QUIT, offset_x, offset_y);
	}

	public void update(Graphics g, Point quit_top_left, int quit_width, int quit_height){
		this.g = g;
		this.origin = quit_top_left;
		this.width = quit_width;
		this.height = quit_height;
	}
}

