package de.game.plugin.swingcomponents;

import javax.swing.JPanel;
/**
 * Instanziert ein JPanel, welches bereits an das Game.-Design angepasst wurde
 * 
 * @author Lars Ohnemus
 * @version 1.0
 *
 */
public class GPanel extends JPanel {

	private static final long serialVersionUID = 16091997L;

	/**
	 * Erzeugt ein JPanel mit game.-Design
	 */
	public GPanel(){
		super();
		setBackground(Constants.BACKGROUND_COLOR);
		setForeground(Constants.FOREGROUND_COLOR_1);
	}
	
}
