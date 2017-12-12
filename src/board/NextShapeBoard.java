package board;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import shape.*;
import util.*;

public class NextShapeBoard extends Canvas{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int next_shape_height,next_shape_width,size;
	private Point next_shape_top_left;
	private Graphics g;
	private Shape cur_next_shape;
	private boolean nextShape=true;
	
	
	public NextShapeBoard(Graphics g, Point nextShapeBoard_top_left, int size){
		init(g, nextShapeBoard_top_left, size);
	}
	
	private void init(Graphics g, Point nextShapeBoard_top_left, int size){
		this.g=g;
		this.size=size;
		this.next_shape_top_left=nextShapeBoard_top_left;
		
		this.next_shape_height = size*4;
		this.next_shape_width = size*6;
		
		if(cur_next_shape!=null){
			cur_next_shape.setArrX(1);
			cur_next_shape.setArrY(1);
		}
	
	}

	@Override
	public void paint(Graphics g){
		this.draw();
	}
	
	private void draw(){
		
		   // draw the next shape rectangle
		   drawNextShapeRect();
		   
		   // draw next new shape
		   if(nextShape)
			   drawNextShape();
		   else
			   drawCurrentShape();
	}
	   
	   // define each shape's origin point to be the top most and left most point, top most first.
	   // randomly create a new shape
	   private void drawNextShape(){
			   // draw the shape in next area
			   Point origin_next = new Point(next_shape_top_left.x+size, next_shape_top_left.y+size);
			   cur_next_shape = Util.getRandomShape(g,origin_next,size);
			   cur_next_shape.drawShape();			   
			   nextShape = false;
	   }
	   
		  private void drawNextShapeRect(){			  
			   g.setColor(Color.BLACK);			   
			   g.drawRect(next_shape_top_left.x, next_shape_top_left.y, next_shape_width, next_shape_height);		
		   }
		  
		   private void drawCurrentShape() {		
			   cur_next_shape = Util.getShape(g, next_shape_top_left,size, cur_next_shape);
			   cur_next_shape.drawShape();		
		}

		public void update(Graphics g, Point nextShapeBoard_top_left, int size) {
			init(g,nextShapeBoard_top_left,size);			
		}

		public Shape getCurShape() {
			return cur_next_shape;
		}

		public void setIfNextShape(boolean ifNextShape) {
			nextShape = ifNextShape;
		}
}
