package board;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;

import listener.*;
import shape.*;
import util.*;

public class MainBoard extends Canvas{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Graphics g;
	private MainBoardListener mainCtrl;
	private int main_area_width,main_area_height,size,removeLines;
	private Point main_top_left,main_down_right,pause_top_left,shape_origin;
	private BtnPAUSE pause;
	private int[][] locations = new int[Util.HEIGHT][Util.WIDTH];
	private Shape cur_main_shape;
	private boolean ifPause = false, nextShape = false, ifChange, ifExit;
	
	  public MainBoard(Graphics g,Point main_top_left,int size){
		  init(g,main_top_left,size);
		  mainCtrl=new MainBoardListener(this);
		  addMouseListener(mainCtrl);
		  addMouseMotionListener(mainCtrl);			
		  addMouseWheelListener(mainCtrl);
	  }
	  
	  private void init(Graphics g,Point main_top_left,int size){
		  this.g=g;
		  this.size=size;
		  this.main_top_left=main_top_left;
		  this.main_area_height = size*Util.HEIGHT;
		  this.main_area_width = size*Util.WIDTH;
		  this.shape_origin=new Point(main_top_left.x+size*(Util.WIDTH/2-1), main_top_left.y);
		  if(main_down_right==null)
			  this.main_down_right=new Point(main_top_left.x+size*Util.WIDTH, 
					  main_top_left.y+size*Util.HEIGHT);
		  else
			  main_down_right.setLocation(main_top_left.x+size*Util.WIDTH, 
					  main_top_left.y+size*Util.HEIGHT);
		  if(pause_top_left==null)
			  this.pause_top_left = new Point(main_top_left.x+size*(Util.WIDTH/2-2), 
					  main_top_left.y+size*(Util.HEIGHT/2-1));		
		  else
			  pause_top_left.setLocation(main_top_left.x+size*(Util.WIDTH/2-2), 
					  main_top_left.y+size*(Util.HEIGHT/2-1));
		  if(cur_main_shape==null&&main_top_left.x!=0)
			  cur_main_shape = Util.getRandomShape(g,shape_origin,size);
	  }
	  
	  @Override
	  public void paint(Graphics g){
		  this.draw();
	  }
	  
	  private void draw(){
		   // draw the main rectangle
		   drawMainRect();		   
		   // draw the remained units
		   drawRemainUnits();
		   // draw current shapes
		   if(!nextShape)
			   drawCurrentShape();
		   else
			   drawNextShape();
		   // draw text PAUSE
		   if(ifPause)
			   drawPause();
		   else
		   // move downwards every second
			   moveDown();
	  }
	
	   private void drawRemainUnits() {		   
		   for(int i = 0; i< locations.length; i++)
			   for(int j = 0; j < locations[0].length; j++)
				   if(locations[i][j]!=0)
				   {
					   g.setColor(Color.BLACK);
					   g.drawRect(main_top_left.x+size*j, main_top_left.y+size*i, size, size);
					   g.setColor(Util.getColorByNum(locations[i][j]));
					   g.fillRect(main_top_left.x+size*j+1, main_top_left.y+size*i+1, size-1, size-1);
				   }		
	}

	private void moveDown(){   
			if(checkDown()){
				   cur_main_shape = Util.getShape(g, main_top_left,size, cur_main_shape);
				   cur_main_shape.run();
			}else{
				// When a new shape has no space to fall, 
				// i.e. existing shapes in “Main area” pile up 
				// to near the top, the game terminates
				exitIfFull();
				
				// write into array locations to remember color
				writeIntoLocations();
				nextShape = true;
				// check if any rows are full and remove, change the level and score
				removeRows();
			}
	   }
	
	private void exitIfFull() {
		if(cur_main_shape.getArrY()==0){
			ifExit=true;
		}		
	}

	// 	check if any rows are full and remove, 
	//	lines+1
	private void removeRows() {
		   for(int i = 0; i< locations.length; i++)
		   {
			   boolean isFull=false;
			   for(int j = 0; j < locations[0].length; j++){
				   if(locations[i][j]==0)
					   break;
				   if(j==locations[0].length-1)
					   isFull=true;
			   }
			   if(isFull){
				   // move all rows above downward
				   for(int row=i;row>0;row--){
					   locations[row]=locations[row-1];
				   }
				   Arrays.fill(locations[0], 0);
				   removeLines++;
			   }			   
		   }	
	}

	private boolean checkDown(){
		
			int originX = cur_main_shape.getOrigin().x, originY = cur_main_shape.getOrigin().y, 
					column = cur_main_shape.getArrX(), row = cur_main_shape.getArrY();
			int[][] coordinates = cur_main_shape.getCoordinates();
			int width = cur_main_shape.getWidth()*size, height = cur_main_shape.getHeight()*size;
		   
		   if((originX>=main_top_left.getX()&&originX+width<=main_down_right.getX())
			&&
			(originY>=main_top_left.getY()&&originY+height+size<=main_down_right.getY()))
		   {
				for(int i = 0; i < coordinates.length; i++){
					if(row+coordinates[i][1]+1<Util.HEIGHT && 
							this.locations[row+coordinates[i][1]+1][column+coordinates[i][0]]==0)
						continue;
					else
						return false;
				}
		   }else
			   return false;
		return true;
	}
	   
	   private void writeIntoLocations(){
		   
		   int originX = cur_main_shape.getArrX(), originY = cur_main_shape.getArrY();
		   System.out.println("x: "+originX + " y: "+ originY);
		   int[][] coordinates = cur_main_shape.getCoordinates();
		   for(int i = 0; i<coordinates.length; i++)
			   if(originY+coordinates[i][1]>=0&&(originY+coordinates[i][1]<=Util.HEIGHT-1)
			   &&
			   (originX+coordinates[i][0]>=0)&&(originX+coordinates[i][0]<=Util.WIDTH-1))
				   this.locations[originY+coordinates[i][1]][originX+coordinates[i][0]] = 
				   Util.getColorNum(cur_main_shape);			   
	   }
	   
	   private void drawCurrentShape() {		
		   cur_main_shape = Util.getShape(g, main_top_left,size, cur_main_shape);
		   cur_main_shape.drawShape();	
	}
	   
	   // define each shape's origin point to be the top most and left most point, top most first.
	   // randomly create a new shape
	   private void drawNextShape(){		   
			   // draw the shape in main area
			   cur_main_shape = Util.getNextShape(g,shape_origin,size,cur_main_shape.getClass());
			   cur_main_shape.drawShape();
			   
			   nextShape = false;
	   }

	private void drawMainRect(){	
		 	g.setColor(Color.BLACK);
			g.drawRect(main_top_left.x, main_top_left.y, main_area_width, main_area_height);	
	   }
	
	//  pause
	private void drawPause(){ 
		   
		   // calculate the text pause width and height
		   int pause_width = size*4, pause_height = size*2;				   
		   pause = new BtnPAUSE(g, pause_top_left, pause_width, pause_height);
		   pause.drawTextPAUSE();
		   
	   }

	public void update(Graphics g, Point mainBoard_top_left, int size) {
		init(g,mainBoard_top_left,size);
	}

	public Point getTopLeft() {
		return main_top_left;
	}

	public Point getDownRight() {
		return main_down_right;
	}

	public boolean getIfPause() {
		return ifPause;
	}

	public Shape getCurMainShape() {
		return cur_main_shape;
	}

	public int[][] getLocations() {
		return locations;
	}

	public int getRectSize() {
		return size;
	}

	public void setIfPause(boolean ifPause) {
		this.ifPause=ifPause;
	}

	public void setCurMainShape(Shape cur_main_shape) {
		this.cur_main_shape=cur_main_shape;
	}

	public boolean getIfNextShape() {		
		return nextShape;
	}

	public int getRemovedLines() {
		return removeLines;
	}

	public void setRemovedLines(int value) {
		removeLines=value;
	}

	public boolean getIfChange() {
		return ifChange;
	}

	public void setIfChange(boolean value) {
		ifChange=value;		
	}
	
	public boolean getIfExit(){
		return ifExit;
	}

}
