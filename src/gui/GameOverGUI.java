package gui;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GameOverGUI extends Frame implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Panel pnlInfo,pnlOK;
	Label lblScore,lblLines,lblLevel;
	Button btnOK; 
	
	
	public GameOverGUI(int lines, int level, int score){
		
		super("Game Over");
		   
		addWindowListener(new WindowAdapter()
        {
			public void windowClosing(WindowEvent e){				
				System.exit(0);
			}
		}
		);
		
		setSize (400, 200);
		this.setResizable(false);;
		
		Font font = new Font("",Font.BOLD,18);
		Dimension lblDimension = new Dimension(200,20);
		this.setFont(font);
		
		lblLines = new Label("Your  Total Lines:	"+lines);
		lblLevel = new Label("Your  Level:	"+level);
		lblScore = new Label("Your  Score:	"+score);
		
		lblLines.setPreferredSize(lblDimension);
		lblLevel.setPreferredSize(lblDimension);
		lblScore.setPreferredSize(lblDimension);
		
		pnlInfo = new Panel();
		pnlInfo.setLayout(new GridLayout(3,1));
		pnlInfo.add(lblLines);
		pnlInfo.add(lblLevel);
		pnlInfo.add(lblScore);
		
		btnOK = new Button("OK");
		pnlOK = new Panel();
		pnlOK.add(btnOK);
		
		this.add(new Label(), BorderLayout.WEST);
		this.add(new Label(), BorderLayout.EAST);
		this.add(new Label(), BorderLayout.PAGE_START);
		this.add(pnlInfo, BorderLayout.CENTER);
		this.add(pnlOK, BorderLayout.PAGE_END);
		
		btnOK.addMouseListener(this);
      
		setVisible(true);	
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		System.exit(0);		
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