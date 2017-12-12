package listener;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import board.*;
import shape.*;
import util.*;

public class MainBoardListener  implements MouseMotionListener, MouseListener, MouseWheelListener{
	
	private MainBoard mainBoard;
	private Point main_top_left,main_down_right;
	private boolean ifPause;
	private Shape cur_main_shape;
	private int[][] locations;
	private int size;

	public MainBoardListener(MainBoard mainBoard) {
		this.mainBoard=mainBoard;
		this.main_top_left=mainBoard.getTopLeft();
		this.main_down_right=mainBoard.getDownRight();
		this.ifPause=mainBoard.getIfPause();
		this.cur_main_shape=mainBoard.getCurMainShape();
		this.locations=mainBoard.getLocations();
		this.size=mainBoard.getRectSize();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		int x = e.getX(), y = e.getY();
		if(main_top_left == null) return;
		if((x>main_top_left.x&&x<main_down_right.x)
				&&
				(y>main_top_left.y&&y<main_down_right.y)
						){
			ifPause = true;
			// if inside current shape
			mainBoard.setIfChange(checkIfInsideCurShape(x,y));
		}else{
			ifPause = false;
		}
		mainBoard.setIfPause(ifPause);
	}

	private boolean checkIfInsideCurShape(int x, int y) {
		this.cur_main_shape=mainBoard.getCurMainShape();
		return Util.isPointInsideShape(new Point(x,y),cur_main_shape);		
	}

	private boolean checkRight() {
		   this.cur_main_shape=mainBoard.getCurMainShape();
		   Point currentShapeOrigin = cur_main_shape.getOrigin();
		   int width = cur_main_shape.getWidth()*size;
		   if(currentShapeOrigin.getX()+width+size<=main_down_right.getX()
				   && checkMatrix(1))
			   return true;
		return false;
	}

	private boolean checkMatrix(int value) {
		this.cur_main_shape=mainBoard.getCurMainShape();
		int x = cur_main_shape.getArrX(), y = cur_main_shape.getArrY();
		int[][] coordinates = cur_main_shape.getCoordinates();
		
		for(int i = 0; i< coordinates.length; i++){
			if(y+coordinates[i][0]+value>Util.HEIGHT-1||
					y+coordinates[i][0]+value<0||
					x+coordinates[i][0]+value>Util.WIDTH-1||
					x+coordinates[i][0]+value<0||
					locations[y][x+coordinates[i][0]+value]!=0
					)
				return false;
		}
		return true;
	}

	private boolean checkLeft() {
		   this.cur_main_shape=mainBoard.getCurMainShape();
		   Point currentShapeOrigin = cur_main_shape.getOrigin();
		   if(currentShapeOrigin.getX()-size>=main_top_left.getX()
				   && checkMatrix(-1))
			   return true;
		return false;
	}


	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		if(ifPause)
			return;
		// negative is forward scroll, positive is backward scroll
		int scroll = e.getWheelRotation();
		
		if(checkRotate())
		{
			this.cur_main_shape=mainBoard.getCurMainShape();
			if(scroll<0)
				cur_main_shape.clockWiseRotate();
			else if(scroll>0)		
				cur_main_shape.counterClockWiseRotate();
		}	
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

			if(ifPause)
				return;
			// 1: left click; 3: right click
			int click = e.getButton();
			this.cur_main_shape=mainBoard.getCurMainShape();
			if(click == 1){
				if(checkLeft())
					cur_main_shape.leftClick();
			}else if(click == 3){
				if(checkRight())
					cur_main_shape.rightClick();
			}
			mainBoard.setCurMainShape(cur_main_shape);
	}

	private boolean checkRotate() {
		this.cur_main_shape=mainBoard.getCurMainShape();
		int y = cur_main_shape.getArrX(), x = cur_main_shape.getArrY(), 
				width = cur_main_shape.getWidth(), height = cur_main_shape.getHeight();
		int max = Math.max(width, height);
		
		if(max+x>Util.HEIGHT-1||max+y>Util.WIDTH-1)
			return false;
		
		for(int i = x; i< max+x; i++)
			for(int j = y; j<max+y; j++)
				if(locations[x][y]!=0)
					return false;		
		return true;
	} 

	@Override
	public void mousePressed(MouseEvent e) {
		e.consume();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		e.consume();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		e.consume();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		e.consume();
	}

	@Override
	public void mouseDragged(MouseEvent e) {	
		e.consume();
	}

}
