package gui;
	
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import listener.*;
import board.*;
import util.*;
	
	
	public class CanvasPaint  extends Canvas {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int maxX, maxY, minMaxXY, size;
		private Graphics g;
		private Point mainBoard_top_left, nextShapeBoard_top_left,quitBoard_top_left,txtBoard_top_left;
		private MainBoard mainBoard;
		private NextShapeBoard nextShapeBoard;
		private TextBoard txtBoard;
		private QuitBoard quitBoard;
		private QuitBoardListener quitBoardListener;
		private MainBoardListener mainBoardListener;
		
		public CanvasPaint(){
			
			init();
			quitBoardListener = new QuitBoardListener(quitBoard);
			addMouseListener(quitBoardListener);
			
			mainBoardListener = new MainBoardListener(mainBoard);
			addMouseListener(mainBoardListener);
			addMouseMotionListener(mainBoardListener);
			addMouseWheelListener(mainBoardListener);
		}
		
	   public void paint(Graphics g)
	   { 
		   this.g = g;		   
		   init();	
			   
		   nextShapeBoard.paint(g);
		   quitBoard.paint(g);
		   txtBoard.paint(g);	
		   mainBoard.paint(g);
		   
		   // exit if full
		   if(mainBoard.getIfExit()){
			   this.setEnabled(false);
			   this.setVisible(false);
			   new GameOverGUI(txtBoard.getTotalLines(),txtBoard.getLevel(),
					   txtBoard.getScore());
		   }
		   
		   try {
			Thread.sleep(Util.FS);					
		   } catch (InterruptedException e) {
			e.printStackTrace();
		   }
		   this.repaint();
	   }
	   
	   // initialization
		private void init()
		   {  	
				Dimension dim = getSize();
				maxX = Math.round((float)(dim.width - 1)); 
				maxY = Math.round((float)(dim.height - 1)); 
				minMaxXY = Math.min(maxX, maxY);
				size = minMaxXY/(Util.HEIGHT+3);
			
				mainBoard_top_left=new Point(Math.round((float)minMaxXY/10),Math.round((float)minMaxXY/10));		
				nextShapeBoard_top_left = new Point(mainBoard_top_left.x+size*(Util.WIDTH+1), mainBoard_top_left.y+5);
				quitBoard_top_left = new Point(nextShapeBoard_top_left.x, mainBoard_top_left.y+(Util.HEIGHT-2)*size);
				txtBoard_top_left = new Point(nextShapeBoard_top_left.x, nextShapeBoard_top_left.y+size*5);
				
				if(nextShapeBoard==null)
					nextShapeBoard=new NextShapeBoard(g,nextShapeBoard_top_left,size);
				else{
					// main board tells next shape board if create new next shape;
					if(mainBoard.getIfNextShape()){
						mainBoard.setCurMainShape(nextShapeBoard.getCurShape());
						nextShapeBoard.setIfNextShape(true);
					}
					nextShapeBoard.update(g,nextShapeBoard_top_left,size);
				}
				
				if(mainBoard==null)
					mainBoard=new MainBoard(g,mainBoard_top_left,size);
				else
				{
					// if change flag is true and score is enough to penalize, change current shape and penalize
					int score = txtBoard.getScore();
					score -= txtBoard.getLevel()*Util.M;
					
					if(mainBoard.getIfChange()&&score>=0){
						mainBoard.setCurMainShape(Util.changeShape(g,
								mainBoard.getCurMainShape(),
								nextShapeBoard.getCurShape(),
								mainBoard.getCurMainShape().getOrigin(),size));
						mainBoard.setIfChange(false);
						// penalize
						penalize();
					}
					mainBoard.update(g,mainBoard_top_left,size);
				}
				
				if(txtBoard==null)
					txtBoard=new TextBoard(g,txtBoard_top_left,size);
				else{
					txtBoard.update(g,txtBoard_top_left,size);
					updateCalculation();						
				}
				
				if(quitBoard==null)
					quitBoard=new QuitBoard(g,quitBoard_top_left,size);
				else
					quitBoard.update(g,quitBoard_top_left,size);
						
		   }

		// Score = Score - Level  x M
		private void penalize() {
			int score = txtBoard.getScore();
			score -= txtBoard.getLevel()*Util.M;
			txtBoard.setScore(score);			
		}

		private void updateCalculation() {
			int removedLines = mainBoard.getRemovedLines();
			if(removedLines>0){
				// update score
				int score = txtBoard.getScore();
				for(int i=0;i<removedLines;i++){
					score += txtBoard.getLevel()*Util.M;
				}
				txtBoard.setScore(score);
				
				txtBoard.increaseTotalLines(removedLines);
				
				if(removedLines+txtBoard.getLines()>=Util.N){
					// level up
					txtBoard.increaseLevel();
					// reset lines to 0
					txtBoard.setLines(0);
					// increase the FS
					int speed = Util.FS/(1+txtBoard.getLevel()*Util.S);
					Util.setFS(speed);
				}
				else 
					txtBoard.updateLines(removedLines);
				mainBoard.setRemovedLines(0);
			}
		}

}
