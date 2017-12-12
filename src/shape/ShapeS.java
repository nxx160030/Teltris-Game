package shape;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class ShapeS extends Shape{
	
	VerticalEdge edge1,edge2,edge3,edge4;
	
	public ShapeS(Graphics g, Point origin, int size) {
		super(g, origin, size);
		this.coordinates[0][0] = 1;
		this.coordinates[0][1] = 0;
		
		this.coordinates[1][0] = 2;
		this.coordinates[1][1] = 0;
		
		this.coordinates[2][0] = 0;
		this.coordinates[2][1] = 1;
		
		this.coordinates[3][0] = 1;
		this.coordinates[3][1] = 1;
	}
	
	public void drawShape(){
		
		this.draw(Color.YELLOW);
		
	}

	@Override
	public void clockWiseRotate() {
		if(isVertical)
			setHorizontal();
		else
			setVertical();

	}

	private void setVertical() {
		
		this.coordinates[0][0] = 0;
		this.coordinates[0][1] = 0;
		
		this.coordinates[1][0] = 0;
		this.coordinates[1][1] = 1;
		
		this.coordinates[2][0] = 1;
		this.coordinates[2][1] = 1;
		
		this.coordinates[3][0] = 1;
		this.coordinates[3][1] = 2;
		
		isVertical = true;
		
		edge1 = new VerticalEdge(origin.x,origin.y,origin.y+size*2);
		edge2 = new VerticalEdge(origin.x+size,origin.y,origin.y+size);
		edge3 = new VerticalEdge(origin.x+size,origin.y+size*2,origin.y+size*3);
		edge4 = new VerticalEdge(origin.x+size*2,origin.y+size,origin.y+size*3);
		
	}

	private void setHorizontal() {
		
		this.coordinates[0][0] = 1;
		this.coordinates[0][1] = 0;
		
		this.coordinates[1][0] = 2;
		this.coordinates[1][1] = 0;
		
		this.coordinates[2][0] = 0;
		this.coordinates[2][1] = 1;
		
		this.coordinates[3][0] = 1;
		this.coordinates[3][1] = 1;

		isVertical = false;
		// update edges
		edge1 = new VerticalEdge(origin.x,origin.y+size,origin.y+size*2);
		edge2 = new VerticalEdge(origin.x+size,origin.y,origin.y+size);
		edge3 = new VerticalEdge(origin.x+size*2,origin.y+size,origin.y+size*2);
		edge4 = new VerticalEdge(origin.x+size*3,origin.y,origin.y+size);
		
	}

	@Override
	public void counterClockWiseRotate() {
		
		if(isVertical)
			setHorizontal();
		else
			setVertical();
		
	}

	@Override
	protected void createEdges() {
		edge1 = new VerticalEdge(origin.x,origin.y+size,origin.y+size*2);
		edge2 = new VerticalEdge(origin.x+size,origin.y,origin.y+size);
		edge3 = new VerticalEdge(origin.x+size*2,origin.y+size,origin.y+size*2);
		edge4 = new VerticalEdge(origin.x+size*3,origin.y,origin.y+size);
		this.edgeList.add(edge1);
		this.edgeList.add(edge2);
		this.edgeList.add(edge3);
		this.edgeList.add(edge4);
	}

}
