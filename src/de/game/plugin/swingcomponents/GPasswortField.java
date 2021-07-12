package de.game.plugin.swingcomponents;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

public class GPasswortField extends JPasswordField {

	private static final long serialVersionUID = 112L;

	public GPasswortField(){
		super();
		setBackground(Constants.BACKGROUND_COLOR);
		setForeground(Constants.FOREGROUND_COLOR_1_B);
		setFont(Constants.FONT_LAYER_4);
		setCaretColor(Constants.FOREGROUND_COLOR_2);
		setSelectionColor(Constants.FOREGROUND_COLOR_1);
		setSelectedTextColor(Constants.FOREGROUND_COLOR_3);
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.FOREGROUND_COLOR_1));
	}
}
