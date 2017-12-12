package listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import board.*;

public class QuitBoardListener  implements MouseListener{

	private QuitBoard quitBoard;
	
	public QuitBoardListener(QuitBoard quitBoard) {
		this.quitBoard=quitBoard;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {		
		// click "QUIT", quit the program
		int x = e.getX(), y = e.getY();
		if((x>=quitBoard.getTopLeft().x&&x<=quitBoard.getDownRight().x
				&& y>=quitBoard.getTopLeft().y&&y<=quitBoard.getDownRight().y)){
			System.exit(0);
		}		
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
}
