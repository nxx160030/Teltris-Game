package board;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

import listener.*;

public class QuitBoard  extends Canvas{
	  /**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		private BtnQuit quit;
		private Graphics g;
		private Point quit_top_left,quit_down_right;
		private int quit_width,quit_height;
		private QuitBoardListener quitBoardListener;
	  
	  public QuitBoard(Graphics g,Point quit_top_left,int size){
		  init(g,quit_top_left,size);
		  quitBoardListener = new QuitBoardListener(this);
		  addMouseListener(quitBoardListener);
	  }
	   
	  private void init(Graphics g,Point quit_top_left,int size){
		  this.g=g;
		  this.quit_top_left=quit_top_left;
		  this.quit_width = size*4;
		  this.quit_height = size*2;
		  if(quit_down_right==null)
			  this.quit_down_right=new Point(quit_top_left.x+quit_width,quit_top_left.y+quit_height);
		  else
			  this.quit_down_right.setLocation(quit_top_left.x+quit_width,quit_top_left.y+quit_height);
	  }
	  
		@Override
		public void paint(Graphics g){
			this.draw();
		}
	  
	  private void draw(){
		   // draw button quit
		   if(quit==null)
			  quit = new BtnQuit(g, quit_top_left, quit_width, quit_height);
		   else
			   quit.update(g, quit_top_left, quit_width, quit_height);
		   quit.drawBtnQuit();
	  }

	public void update(Graphics g, Point quitBoard_top_left, int size) {
			init(g,quitBoard_top_left,size);		
	}

	public Point getTopLeft() {
		return quit_top_left;
	}
	
	public Point getDownRight() {
		return quit_down_right;
	}
}
