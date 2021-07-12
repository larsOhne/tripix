package de.game.plugin.swingcomponents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class GScrollBarUI extends BasicScrollBarUI {
	
	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		g.setColor(Constants.FOREGROUND_COLOR_1);
		if(c.getHeight()>c.getWidth()){//Vertikal
//			g.fillOval(thumbBounds.x, thumbBounds.y+thumbBounds.height-thumbBounds.width/2, thumbBounds.width, thumbBounds.width);
//			g.fillOval(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.width);
//			g.fillRect(thumbBounds.x, thumbBounds.y+thumbBounds.width/2, thumbBounds.width, thumbBounds.height-thumbBounds.width/2);
			
			Graphics2D g2 = (Graphics2D) g;
			
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			GeneralPath p = new GeneralPath();
			
			p.moveTo(thumbBounds.x, thumbBounds.y+thumbBounds.width);
			p.curveTo(thumbBounds.x, thumbBounds.y, thumbBounds.getMaxX(), thumbBounds.y, thumbBounds.getMaxX(), thumbBounds.y+thumbBounds.width);
			p.lineTo(thumbBounds.getMaxX(), thumbBounds.getMaxY()-thumbBounds.width);
			p.curveTo(thumbBounds.getMaxX(), thumbBounds.getMaxY(), thumbBounds.x, thumbBounds.getMaxY(), 
					thumbBounds.x, thumbBounds.getMaxY()-thumbBounds.width);
			
			p.closePath();
			
			g2.fill(p);
		}
	}
	
	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		g.setColor(Constants.BACKGROUND_COLOR);
		g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
	}
	
	

}
