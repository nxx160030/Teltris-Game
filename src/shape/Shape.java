package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import util.*;

public abstract class Shape {

	protected Graphics g;
	//   define each shape's origin point to be the top most and left most point, top most first.
	protected Point origin;
	// rectangle size
	protected int size;
	protected int[][] coordinates = new int[4][2];
	protected int width, height;
	protected int arrX = Util.WIDTH/2-1;
	protected int arrY = 0;
	protected boolean isVertical = false;
	// original direction is 1, vertical is 2, down is 3, up is 4
	protected int direction = 1;
	protected List<VerticalEdge> edgeList = new ArrayList<VerticalEdge>();
	
	public Shape(Graphics g, Point origin, int size){		
		this.g = g;
		this.origin = origin;
		this.size = size;
		createEdges();
	}
	
	protected abstract void createEdges();
	
	public abstract void drawShape();
	
	public void run(){
		increaseY();
	}
	
	public void setOrigin(Point point){
		this.origin = point;
	}
	
	public void setArrX(int x){
		this.arrX = x;
	}
	
	public int getArrX(){
		return this.arrX;
	}
	
	public void setArrY(int y){
		this.arrY = y;
	}
	
	public int getArrY(){
		return this.arrY;
	}
	
	private void increaseX(){		
		arrX += 1;
		//origin.setLocation(origin.getX()+size,origin.getY());
		origin.x = (int)origin.getX()+size;
		
		// update vertical edges
		for(VerticalEdge edge:edgeList){
			edge.x += size;
		}
	}

	private void increaseY(){		
		arrY += 1;
		origin.setLocation(origin.getX(),origin.getY()+size);
		
		// update vertical edges
		for(VerticalEdge edge:edgeList){
			edge.y1 += size;
			edge.y2 += size;
		}
	}
	
	private void decreaseX(){
		arrX -= 1;
		origin.setLocation(origin.getX()-size,origin.getY());
		
		// update vertical edges
		for(VerticalEdge edge:edgeList){
			edge.x -= size;
		}
	}
	
	public Point getOrigin(){
		return origin;
	}
	
	public int getWidth(){
		int min = 4, max = -1;
		for(int i = 0; i < 4; i++)
		{
			min = Math.min(min, coordinates[i][0]);
			max = Math.max(max, coordinates[i][0]);
		}
			width = max - min + 1;
		return width;
	}
	
	public int getHeight(){
		int min = 4, max = -1;
		for(int i = 0; i < 4; i++)
		{
			min = Math.min(min, coordinates[i][1]);
			max = Math.max(max, coordinates[i][1]);
		}
			height = max - min + 1;
		return height;
	}
	
	public int[][] getCoordinates(){
		return this.coordinates;
	}
	
	
	protected void draw(Color color){	
		for(int i = 0; i < 4; i++)
			{
				// set line color to black
				g.setColor(Color.BLACK);
				// draw rectangle
				g.drawRect(origin.x+coordinates[i][0]*size, origin.y+coordinates[i][1]*size, size, size);
				// fill rectangle with color
				g.setColor(color);
				g.fillRect(origin.x+coordinates[i][0]*size+1, origin.y+coordinates[i][1]*size+1, size-1, size-1);
			}	
	}


	public abstract void clockWiseRotate(); 

	public abstract void counterClockWiseRotate();

	public void leftClick() {
		this.decreaseX();		
	}

	public void rightClick() {
		this.increaseX();		
	}

	public void setParameters(Shape shape) {
		this.arrX = shape.getArrX();
		this.arrY = shape.getArrY();
		this.coordinates = shape.getCoordinates();
		this.isVertical = shape.isVertical;
		this.direction = shape.direction;
		this.edgeList = shape.getEdgeList();
	}

	public List<VerticalEdge> getEdgeList() {
		return this.edgeList;
	}

}
