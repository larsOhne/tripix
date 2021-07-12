package de.game.plugin.swingcomponents;

import javax.swing.JScrollBar;

public class GScrollBar extends JScrollBar {

	private static final long serialVersionUID = 112L;
	
	public GScrollBar(){
		setUI(new GScrollBarUI());
		setOpaque(false);
		remove(1);
		remove(0);
	}

}
