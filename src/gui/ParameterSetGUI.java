package gui;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import util.*;

public class ParameterSetGUI extends Frame implements MouseListener, AdjustmentListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Panel pnlM,pnlN,pnlS,pnlPara,pnlOK,pnlWidth,pnlHeight;
	Label lblM,lblN,lblS,lblWidth,lblHeight,lblValueM,lblValueN,lblValueS,lblValueWidth,lblValueHeight;
	Scrollbar scrbarM,scrbarN,scrbarS,scrbarWidth,scrbarHeight;
	Button btnOK; 
	

	public static void main(String[] args){
		
		new ParameterSetGUI();

		}
	
	public ParameterSetGUI(){
		
		super("Parameter Set GUI");
		   
		addWindowListener(new WindowAdapter()
        {
			public void windowClosing(WindowEvent e){				
				System.exit(0);
			}
		}
		);
		
		setSize (400, 300);
		this.setResizable(false);
		Font font = new Font("",Font.BOLD,18);
		Dimension barDimension = new Dimension(200,20);
		Dimension lblDimension = new Dimension(80,20);
		this.setFont(font);
		
		pnlM = new Panel();
		pnlM.setLayout(new FlowLayout());
		
		lblM = new Label("M: ", Label.LEFT);
		scrbarM = new Scrollbar(Scrollbar.HORIZONTAL,1,1,1,11);	
		lblValueM = new Label(""+scrbarM.getValue());
		
		scrbarM.setPreferredSize(barDimension);
		lblM.setPreferredSize(lblDimension);
		lblValueM.setPreferredSize(lblDimension);
		
		pnlM.add(lblM);
		pnlM.add(scrbarM);
		pnlM.add(lblValueM);
		
		pnlN = new Panel();
		pnlN.setLayout(new FlowLayout());
		
		lblN = new Label("N: ", Label.LEFT);	
		scrbarN = new Scrollbar(Scrollbar.HORIZONTAL,20,1,20,51);		
		lblValueN = new Label(""+scrbarN.getValue());
		
		scrbarN.setPreferredSize(barDimension);
		lblN.setPreferredSize(lblDimension);
		lblValueN.setPreferredSize(lblDimension);
		
		pnlN.add(lblN);
		pnlN.add(scrbarN);
		pnlN.add(lblValueN);
		
		pnlS = new Panel();
		pnlS.setLayout(new FlowLayout());
		
		lblS = new Label("S: ", Label.LEFT);	
		scrbarS = new Scrollbar(Scrollbar.HORIZONTAL,1,1,1,11);
		DecimalFormat df = new DecimalFormat("#.#");
		lblValueS = new Label(""+df.format(scrbarS.getValue()*0.1));
		
		scrbarS.setPreferredSize(barDimension);
		lblS.setPreferredSize(lblDimension);
		lblValueS.setPreferredSize(lblDimension);
		
		pnlS.add(lblS);
		pnlS.add(scrbarS);
		pnlS.add(lblValueS);	
		
		pnlWidth = new Panel();
		pnlWidth.setLayout(new FlowLayout());
		
		lblWidth = new Label("Width: ", Label.LEFT);	
		scrbarWidth = new Scrollbar(Scrollbar.HORIZONTAL,10,1,10,21);		
		lblValueWidth = new Label(""+scrbarWidth.getValue());
		
		scrbarWidth.setPreferredSize(barDimension);
		lblWidth.setPreferredSize(lblDimension);
		lblValueWidth.setPreferredSize(lblDimension);
		
		pnlWidth.add(lblWidth);
		pnlWidth.add(scrbarWidth);
		pnlWidth.add(lblValueWidth);
		
		pnlHeight = new Panel();
		pnlHeight.setLayout(new FlowLayout());
		
		lblHeight = new Label("Height: ", Label.LEFT);	
		scrbarHeight = new Scrollbar(Scrollbar.HORIZONTAL,20,1,20,31);		
		lblValueHeight = new Label(""+scrbarHeight.getValue());
		
		scrbarHeight.setPreferredSize(barDimension);
		lblHeight.setPreferredSize(lblDimension);
		lblValueHeight.setPreferredSize(lblDimension);
		
		pnlHeight.add(lblHeight);
		pnlHeight.add(scrbarHeight);
		pnlHeight.add(lblValueHeight);
		
		pnlPara = new Panel();
		pnlPara.setLayout(new GridLayout(5,1));
		
		pnlPara.add(pnlM);
		pnlPara.add(pnlN);
		pnlPara.add(pnlS);
		pnlPara.add(pnlWidth);
		pnlPara.add(pnlHeight);
		
		pnlOK = new Panel();
		btnOK = new Button("OK");
		pnlOK.add(btnOK);
		
		this.add(new Label(""), BorderLayout.PAGE_START);
		this.add(new Label(""), BorderLayout.WEST);
		this.add(new Label(""), BorderLayout.EAST);
		this.add(pnlPara, BorderLayout.CENTER);
		this.add(pnlOK, BorderLayout.PAGE_END);
		this.validate();
      
		setVisible(true);
		btnOK.addMouseListener(this);
	
		scrbarM.addAdjustmentListener(this);
		scrbarN.addAdjustmentListener(this);
		scrbarS.addAdjustmentListener(this);
		scrbarWidth.addAdjustmentListener(this);
		scrbarHeight.addAdjustmentListener(this);		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Util.setM(scrbarM.getValue());
		Util.setN(scrbarN.getValue());
		Util.setS(scrbarS.getValue());
		Util.setWidth(scrbarWidth.getValue());
		Util.setHeight(scrbarHeight.getValue());
		
		new DrawTetrisGUI();
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

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		if(e.getSource()==this.scrbarM)
			this.lblValueM.setText(""+this.scrbarM.getValue());
		else if(e.getSource()==this.scrbarN)
			this.lblValueN.setText(""+this.scrbarN.getValue());
		else if(e.getSource()==this.scrbarS)
		{
			DecimalFormat df = new DecimalFormat("#.#");
			df.setRoundingMode(RoundingMode.CEILING);
			this.lblValueS.setText(""+df.format(this.scrbarS.getValue()*0.1));
		}			
		else if(e.getSource()==this.scrbarWidth)
			this.lblValueWidth.setText(""+this.scrbarWidth.getValue());
		else if(e.getSource()==this.scrbarHeight)
			this.lblValueHeight.setText(""+this.scrbarHeight.getValue());		
	}


}
