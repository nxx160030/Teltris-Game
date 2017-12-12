package util;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import java.util.Random;

import shape.*;

public class Util {
	
	// scoring factor (range: 1-10)
	public static int M = 1;
	
	// N – number of rows required for each Level of difficulty (range: 20-50)
	public static int N = 20;
	
	// speed factor (range: 0.1-1.0)
	public static int S = 1;
	
	public static int WIDTH = 10;
	
	public static int HEIGHT = 20;
	
	public static int FS = 500;
		
	public static Shape getRandomShape(Graphics g, Point origin, int size){
		
		   // randomly create a new shape
		   Random random = new Random();
		   int type = random.nextInt(7);
		   
		   switch(type){
		   case 0:
			   return new ShapeC(g,origin,size);
		   case 1:
			   return new ShapeI(g,origin,size);
		   case 2:
			   return new ShapeLeft(g,origin,size);
		   case 3:
			   return new ShapeRight(g,origin,size);
		   case 4:
			   return new ShapeS(g,origin,size);
		   case 5:
			   return new ShapeT(g,origin,size);
		   case 6:
			   return new ShapeZ(g,origin,size);
		   }
		return null;
	}
	
	
	public static Shape getShape(Graphics g, Point origin, int size, Shape shape){
		
		Shape cur_shape = null;
		Point shape_origin = new Point(origin.x+shape.getArrX()*size, origin.y+shape.getArrY()*size);
		
		Class<?extends Shape> shapeClass = shape.getClass();
		if(shapeClass == ShapeC.class){
			cur_shape = new ShapeC(g,shape_origin,size); 
		}else if(shapeClass == ShapeI.class)
		{
			cur_shape = new ShapeI(g,shape_origin,size); 
		}else if(shapeClass == ShapeLeft.class)
		{
			cur_shape = new ShapeLeft(g,shape_origin,size); 
		}else if(shapeClass == ShapeRight.class)
		{
			cur_shape = new ShapeRight(g,shape_origin,size); 
		}else if(shapeClass == ShapeS.class)
		{
			cur_shape = new ShapeS(g,shape_origin,size); 
		}else if(shapeClass == ShapeT.class)
		{
			cur_shape = new ShapeT(g,shape_origin,size); 
		}else if(shapeClass == ShapeZ.class)
		{
			cur_shape = new ShapeZ(g,shape_origin,size); 
		}
		
		cur_shape.setParameters(shape);
		
		return cur_shape;		
	}


	public static int getColorNum(Shape shape) {
		
		int number = 0;
		
		Class<?extends Shape> shapeClass = shape.getClass();
		if(shapeClass == ShapeC.class){
			number = 1; 
		}else if(shapeClass == ShapeI.class)
		{
			number = 2;  
		}else if(shapeClass == ShapeLeft.class)
		{
			number = 3; 
		}else if(shapeClass == ShapeRight.class)
		{
			number = 4; 
		}else if(shapeClass == ShapeS.class)
		{
			number = 5; 
		}else if(shapeClass == ShapeT.class)
		{
			number = 6; 
		}else if(shapeClass == ShapeZ.class)
		{
			number = 7; 
		}
		
		return number;
	}
	
	public static Color getColorByNum(int number){
		Color color = null;
		switch(number){
		case 1:
			return Color.GREEN;
		case 2:
			return new Color(66,241,244);
		case 3:
			return Color.RED;
		case 4:
			return Color.BLUE;
		case 5:
			return Color.YELLOW;
		case 6:
			return new Color(244,188,66);
		case 7:
			return new Color(137,66,244);
		}
		
		return color;
	}


	public static Shape getNextShape(Graphics g, Point origin_main, int size, 
			Class<? extends Shape> shape) {
		
		Shape cur_shape = null;

		if(shape == ShapeC.class){
			cur_shape = new ShapeC(g,origin_main,size); 
		}else if(shape == ShapeI.class)
		{
			cur_shape = new ShapeI(g,origin_main,size); 
		}else if(shape == ShapeLeft.class)
		{
			cur_shape = new ShapeLeft(g,origin_main,size); 
		}else if(shape == ShapeRight.class)
		{
			cur_shape = new ShapeRight(g,origin_main,size); 
		}else if(shape == ShapeS.class)
		{
			cur_shape = new ShapeS(g,origin_main,size); 
		}else if(shape == ShapeT.class)
		{
			cur_shape = new ShapeT(g,origin_main,size); 
		}else if(shape == ShapeZ.class)
		{
			cur_shape = new ShapeZ(g,origin_main,size); 
		}
		
		return cur_shape;
	}

	public static void setFS(int speed){
		FS = speed;
	}
	
	public static void setM(int m){
		M = m;
	}
	
	public static void setN(int n){
		N = n;
	}


	public static void setS(int value) {
		S=value;		
	}


	public static void setWidth(int value) {
		WIDTH=value;
	}


	public static void setHeight(int value) {
		HEIGHT=value;
	}


	public static boolean isPointInsideShape(Point point, Shape cur_main_shape) {
		List<VerticalEdge> edgeList = cur_main_shape.getEdgeList();
		int count=0;
		for(VerticalEdge edge:edgeList){
			if(point.x<=edge.x&&
					(point.y>edge.y1&&point.y<=edge.y2))
				count++;
		}
		return count%2!=0;
	}


	public static Shape changeShape(Graphics g,Shape cur_main_shape,
			Shape cur_next_shape, Point origin,int size) {
		boolean ifChange=true;
		Shape changeShape=null;		
		while(ifChange){
			ifChange=false;
			changeShape = getRandomShape(g,origin,size);
			changeShape.setArrX(cur_main_shape.getArrX()); 
			changeShape.setArrY(cur_main_shape.getArrY()); 
			if(cur_main_shape.getClass()==changeShape.getClass()||
					cur_next_shape.getClass()==changeShape.getClass())
				ifChange=true;
		}
		return changeShape;
	}
}
