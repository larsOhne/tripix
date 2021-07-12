package de.game.plugin.swingcomponents;

import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicArrowButton;

public class GSpinner extends JSpinner {

	private static final long serialVersionUID = 112L;
	
	public GSpinner(){
		setBackground(Constants.BACKGROUND_COLOR);
		setBorder(null);
		
		BasicArrowButton up = (BasicArrowButton) getComponent(0);
		BasicArrowButton down = (BasicArrowButton) getComponent(1);
		NumberEditor editor = (NumberEditor) getComponent(2);
		
		up.setBackground(Constants.FOREGROUND_COLOR_1);
		down.setBackground(Constants.FOREGROUND_COLOR_1);
		
		up.setBorder(new LineBorder(Constants.FOREGROUND_COLOR_1,5));
		down.setBorder(new LineBorder(Constants.FOREGROUND_COLOR_1,5));
		
		editor.getTextField().setColumns(3);
		editor.getTextField().setBackground(Constants.FOREGROUND_COLOR_1);
		editor.getTextField().setForeground(Constants.FOREGROUND_COLOR_1_B);
		editor.getTextField().setFont(Constants.FONT_LAYER_4);
		editor.getTextField().setCaretColor(Constants.FOREGROUND_COLOR_2);
		editor.getTextField().setSelectionColor(Constants.FOREGROUND_COLOR_1);
		editor.getTextField().setSelectedTextColor(Constants.FOREGROUND_COLOR_3);
		editor.getTextField().setHorizontalAlignment(JTextField.LEFT);
	}

}
