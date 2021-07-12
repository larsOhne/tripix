package de.game.plugin.swingcomponents;

import java.awt.GridLayout;

import javax.swing.SwingConstants;

public class HexagonLabel extends HexagonPanel {

	private static final long serialVersionUID = 16091997L;

	private GLabel label;
	
	/**
	 * Erzeugt ein leeres, sechseckiges Label
	 */
	public HexagonLabel(){
		
		setLayout(new GridLayout(0, 1, 0, 0));
		label = new GLabel();
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label);
	}
	
	public HexagonLabel(String text){
		this();
		setText(text);
	}
	
	public HexagonLabel(int layer){
		this();
		label.setLayer(layer);
	}
	
	public HexagonLabel(int layer, String text){
		this(layer);
		setText(text);
	}

	public GLabel getLabel() {
		return label;
	}

	public void setLabel(GLabel label) {
		this.label = label;
	}

	public void setText(String text) {
		label.setText(text);
	}
	
	
	
}
