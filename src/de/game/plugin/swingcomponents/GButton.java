package de.game.plugin.swingcomponents;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingConstants;

public class GButton extends GPanel {

	private static final long serialVersionUID = 112L;
	
	private GLabel lbl;
	private ArrayList<ActionListener> actions = new ArrayList<ActionListener>();

	public GButton(){
		setBackground(Constants.FOREGROUND_COLOR_1);
		setLayout(new GridLayout(0, 1, 0, 0));
		
		setLbl(new GLabel(2));
		getLbl().setHorizontalAlignment(SwingConstants.CENTER);
		getLbl().setText("Ein Label");
		add(getLbl());
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!isEnabled())return;
				
				for(ActionListener a : actions){
					a.actionPerformed(new ActionEvent(this, 0, null));
				}
			}
		});
	}
	
	public GButton(String text){
		this();
		getLbl().setText(text);
	}
	
	public GButton(String text, int layer){
		this(text);
		getLbl().setLayer(layer);
	}
	
	public void addActionListener(ActionListener a){
		actions.add(a);
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		
		if(enabled)lbl.setForeground(Constants.FOREGROUND_COLOR_2);
		else lbl.setForeground(Constants.FOREGROUND_COLOR_1_B);
	}

	public GLabel getLbl() {
		return lbl;
	}

	public void setLbl(GLabel lbl) {
		this.lbl = lbl;
	}
}
