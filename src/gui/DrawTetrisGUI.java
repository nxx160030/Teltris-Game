package gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class DrawTetrisGUI  extends Frame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DrawTetrisGUI()
	{  
		super("Tetris GUI");
   
		addWindowListener(new WindowAdapter()
        {
			public void windowClosing(WindowEvent e){
				
				System.exit(0);
			}
		}
		);
		
		setSize (800, 800);
		
		CanvasPaint canvasP = new CanvasPaint();		
	
		add(canvasP, BorderLayout.CENTER);
      
		setVisible(true);

	} 

}
