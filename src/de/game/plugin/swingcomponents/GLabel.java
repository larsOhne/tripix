package de.game.plugin.swingcomponents;

import javax.swing.JLabel;

/**
 * Instanziert ein Label, welches bereits an das Game.-Design angepasst wurde
 * 
 * @author Lars Ohnemus
 * @version 1.0
 *
 */
public class GLabel extends JLabel {

	private static final long serialVersionUID = 16091997L;
	
	/**
	 * Erzeugt ein leeres Label mit Akzent 1 als Schriftfarbe und Ebene4-Schriftart
	 */
	public GLabel(){
		super();
		setFont(Constants.FONT_LAYER_4);
		setForeground(Constants.FOREGROUND_COLOR_1);
	}

	/**
	 * Erzeugt ein normales Label mit Akzent 1 als Schriftfarbe und Ebene4-Schriftart
	 * @param text
	 */
	public GLabel(String text){
		this();
		setText(text);
	}
	
	/**
	 * Erzeugt ein leeres Label in einer spezifschen Textebene
	 * @param layer die Textebene des Labels:
	 * <table>
	 * <tr><td>Ebene</td><td>Schriftart</td><td>Schriftfarbe</td></tr>
	 * <tr><td>layer = 1</td><td>Futura</td><td>Akzent 2</td></tr>
	 * <tr><td>layer = 2</td><td>Futura</td><td>Akzent 2</td></tr>
	 * <tr><td>layer = 3</td><td>Futura</td><td>Akzent 3</td></tr>
	 * <tr><td>layer = 4</td><td>Futura</td><td>Akzent 1</td></tr>
	 * <tr><td>layer = 5</td><td>Futura</td><td>Akzent 1</td></tr>
	 * <tr><td>layer = 6</td><td>Futura</td><td>Akzent 1</td></tr>
	 * 
	 * </table>
	 */
	public GLabel(int layer){
		this();
		setLayer(layer);
	}
	
	public GLabel(int layer, String text){
		this(layer);
		setText(text);
	}
	
	public void setLayer(int layer){
		if(layer<1 || layer>6)return;
		switch(layer){
		case 1:
			setFont(Constants.FONT_LAYER_1);
			setForeground(Constants.FOREGROUND_COLOR_2);
			break;
		case 2:
			setFont(Constants.FONT_LAYER_2);
			setForeground(Constants.FOREGROUND_COLOR_2);
			break;
		case 3:
			setFont(Constants.FONT_LAYER_3);
			setForeground(Constants.FOREGROUND_COLOR_3);
			break;
		case 4:
			setFont(Constants.FONT_LAYER_4);
			setForeground(Constants.FOREGROUND_COLOR_1_B);
			break;
		case 5:
			setFont(Constants.FONT_LAYER_5);
			setForeground(Constants.FOREGROUND_COLOR_1_B);
			break;
		case 6:
			setFont(Constants.FONT_LAYER_6);
			setForeground(Constants.FOREGROUND_COLOR_1_B);
		}
	}

}
