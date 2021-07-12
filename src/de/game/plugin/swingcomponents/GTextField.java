package de.game.plugin.swingcomponents;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class GTextField extends JTextField {

	private static final long serialVersionUID = 112L;

	public GTextField(){
		super();
		setBackground(Constants.BACKGROUND_COLOR);
		setForeground(Constants.FOREGROUND_COLOR_1_B);
		setFont(Constants.FONT_LAYER_4);
		setCaretColor(Constants.FOREGROUND_COLOR_2);
		setSelectionColor(Constants.FOREGROUND_COLOR_1);
		setSelectedTextColor(Constants.FOREGROUND_COLOR_3);
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.FOREGROUND_COLOR_1));
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		System.out.println("GTextField.setEnabled()");
		if(enabled){
			setForeground(Constants.FOREGROUND_COLOR_1_B);
		}else{
			setForeground(Constants.FOREGROUND_COLOR_1);
		}
	}
}
