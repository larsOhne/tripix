package de.game.plugin.swingcomponents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class HexagonPanel extends GPanel {

	private static final long serialVersionUID = 16091997L;
	
	private Hexagon hex = new Hexagon(0);
	
	public HexagonPanel(){
		super();
		setOpaque(false);
				
	}
	
	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		refreshHex();
		
		g.setColor(Constants.FOREGROUND_COLOR_1);
		g.fillPolygon(hex);
	}


	private void refreshHex(){
		hex.setRadius(Math.min(getWidth()/2, getHeight()/2));
		Rectangle bounding = hex.getBounds();
		hex.setOffsetX((getWidth()-bounding.width)/2);
		hex.setOffsetY((getHeight()-bounding.height)/2);
	}
}
