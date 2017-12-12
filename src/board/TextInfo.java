package board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class TextInfo {
	
	private String text;
	private int value, width, height;
	private Graphics g;
	private Point origin;
	
	TextInfo(Graphics g, Point origin, int width, int height, String text, int value){
		this.g = g;
		this.text = text;
		this.value = value;
		this.origin = origin;
		this.width = width;
		this.height = height;
	}
	
	public void drawTextInfo(){
		
		// set the color
		g.setColor(Color.BLACK);
		
		// set the font
		int font_size = Math.round((float)(height*0.2));
		Font font = new Font("Arial",Font.BOLD, font_size);
		g.setFont(font);		
		// put the text in the center of rectangle		
		int offset_x = origin.x;
		int offset_y = origin.y;
		
		// draw the text 
		g.drawString(text, offset_x, offset_y);
		g.drawString(""+value, offset_x+width-10, offset_y);
		
	}
	
	
	
}
