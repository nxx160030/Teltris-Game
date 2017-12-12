package shape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class ShapeI extends Shape {
	
	private VerticalEdge edge1,edge2;	

	public ShapeI(Graphics g, Point origin, int size) {
		super(g, origin, size);
		this.coordinates[0][0] = 0;
		this.coordinates[0][1] = 0;
		
		this.coordinates[1][0] = 1;
		this.coordinates[1][1] = 0;
		
		this.coordinates[2][0] = 2;
		this.coordinates[2][1] = 0;
		
		this.coordinates[3][0] = 3;
		this.coordinates[3][1] = 0;
	}

	public void drawShape() {
		
		this.draw(new Color(66,241,244));

	}

	@Override
	public void clockWiseRotate() {
		if(isVertical){
			setHorizontal();
		}else
		{
			setVertical();
		}
		
	}

	@Override
	public void counterClockWiseRotate() {
		
		if(isVertical){
			setHorizontal();
		}else
		{
			setVertical();
		}
	}
	
	private void setHorizontal(){
		
		this.coordinates[0][0] = 0;
		this.coordinates[0][1] = 0;
		
		this.coordinates[1][0] = 1;
		this.coordinates[1][1] = 0;
		
		this.coordinates[2][0] = 2;
		this.coordinates[2][1] = 0;
		
		this.coordinates[3][0] = 3;
		this.coordinates[3][1] = 0;
		
		isVertical = false;
		
		// update edges
		edge1=new VerticalEdge(origin.x,origin.y,origin.y+size);
		edge2=new VerticalEdge(origin.x+size*4,origin.y,origin.y+size);
	} 
	
	private void setVertical(){
		
		this.coordinates[0][0] = 0;
		this.coordinates[0][1] = 0;
		
		this.coordinates[1][0] = 0;
		this.coordinates[1][1] = 1;
		
		this.coordinates[2][0] = 0;
		this.coordinates[2][1] = 2;
		
		this.coordinates[3][0] = 0;
		this.coordinates[3][1] = 3;
		
		isVertical = true;
		
		// update edges
		edge1=new VerticalEdge(origin.x,origin.y,origin.y+size*4);
		edge2=new VerticalEdge(origin.x+size,origin.y,origin.y+size*4);
	}

	@Override
	protected void createEdges() {
		edge1=new VerticalEdge(origin.x,origin.y,origin.y+size);
		edge2=new VerticalEdge(origin.x+size*4,origin.y,origin.y+size);
		edgeList.add(edge1);
		edgeList.add(edge2);
	}

}
