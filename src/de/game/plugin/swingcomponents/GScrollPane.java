package de.game.plugin.swingcomponents;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

public class GScrollPane extends JScrollPane {

	private static final long serialVersionUID = 112L;
	
	public GScrollPane(){
		setBorder(null);
		setViewportBorder(null);
		setOpaque(false);
		getViewport().setOpaque(false);
		
		setVerticalScrollBar(new GScrollBar());
		
//		getVerticalScrollBar().setUI(new GScrollBarUI());
//		getHorizontalScrollBar().setUI(new GScrollBarUI());
		
//		for(Component c : getVerticalScrollBar().getComponents())System.out.println(c);
		
	}

	public GScrollPane(JComponent viewport) {
		this();
		setViewportView(viewport);
	}
	

}
