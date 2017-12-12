package board;
// the lines is reset to 0 each time level up

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

import util.*;

public class TextBoard  extends Canvas{
		/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		private Graphics g;
		private TextInfo level_info, lines_info, score_info;
		private final static String LEVEL = "Level: ", LINES = "Lines: ", SCORE = "Score: ";
		private Point txtBoard_top_left;
		private int size,txt_width,lines,level,score,totalLines;
		
		public TextBoard(Graphics g, Point txtBoard_top_left, int size){
			init(g,txtBoard_top_left,size);
		}
		
		private void init(Graphics g, Point txtBoard_top_left, int size){
			this.g=g;
			this.txtBoard_top_left=txtBoard_top_left;
			this.size=size;
			this.txt_width=size*6;
		}
		
		@Override
		public void paint(Graphics g){
			this.draw();
		}
		
		private void draw(){
			   // draw 3 text infos
			   // draw 1st text info
			   Point level = new Point(txtBoard_top_left.x, txtBoard_top_left.y);
			   level_info = new TextInfo(g, level, txt_width, size*4, LEVEL, this.level);
			   level_info.drawTextInfo();
			   
			   // draw 2nd text info
			   Point lines = new Point(level.x, level.y+size*2);
			   lines_info = new TextInfo(g, lines, txt_width, size*4, LINES, this.lines);
			   lines_info.drawTextInfo();
			   
			   // draw 3rd text info
			   Point score = new Point(lines.x, lines.y+size*2);
			   score_info = new TextInfo(g, score, txt_width, size*4, SCORE, this.score);
			   score_info.drawTextInfo();		
		}

	public void update(Graphics g, Point txtBoard_top_left, int size) {
		init(g,txtBoard_top_left,size);		
	}

	public void updateLines(int removedLines) {
		lines += removedLines;
	}
	
	private void updateScore(){
		score=score+level*Util.M;
	}

	public void increaseLevel() {
		level++;	
		updateScore();
	}

	public int getLines() {

		return lines;
	}

	public void setLines(int value) {
		lines=value;	
	}

	public int getLevel() {
		return level;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int value) {
		this.score=value;		
	}
	
	public int getTotalLines(){
		return totalLines;
	}

	public void increaseTotalLines(int removedLines) {
		totalLines += removedLines;
	}

}
