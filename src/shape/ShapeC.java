package shape;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class ShapeC extends Shape{
		
	
	public ShapeC(Graphics g, Point origin, int size) {
		super(g, origin, size);
		this.coordinates[0][0] = 0;
		this.coordinates[0][1] = 0;
		
		this.coordinates[1][0] = 1;
		this.coordinates[1][1] = 0;
		
		this.coordinates[2][0] = 0;
		this.coordinates[2][1] = 1;
		
		this.coordinates[3][0] = 1;
		this.coordinates[3][1] = 1;
	}

	public void drawShape(){		
		this.draw(Color.GREEN);
	}

	@Override
	public void clockWiseRotate() {
		// do nothing for cubic rotate		
	}

	@Override
	public void counterClockWiseRotate() {
		// do nothing for cubic rotate		
	}

	@Override
	protected void createEdges() {
		edgeList.add(new VerticalEdge(origin.x,origin.y,origin.y+size*2));
		edgeList.add(new VerticalEdge(origin.x+size*2,origin.y,origin.y+size*2));
	}



}
