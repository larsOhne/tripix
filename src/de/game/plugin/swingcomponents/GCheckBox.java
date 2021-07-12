package de.game.plugin.swingcomponents;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

import javax.swing.JCheckBox;

public class GCheckBox extends JCheckBox {

	private static final long serialVersionUID = 112L;
	
	public GCheckBox(){
		setBorder(null);
		setBackground(Constants.FOREGROUND_COLOR_1);
		setForeground(Constants.FOREGROUND_COLOR_2);
		setMinimumSize(new Dimension(40,20));
		setPreferredSize(getMinimumSize());
		setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setColor(getBackground());
		
		GeneralPath back = new GeneralPath();
		
		back.moveTo(getHeight()/2, 0);
		back.curveTo(0, 0, 0, getHeight(), getHeight()/2, getHeight());
		back.lineTo(getWidth()-getHeight()/2, getHeight());
		back.curveTo(getWidth(),getHeight(),getWidth(),0,getWidth()-getHeight()/2,0);
		
		back.closePath();
		g2.fill(back);
		
		
		
		if(isSelected()&& isEnabled()){
			g2.setColor(Constants.FOREGROUND_COLOR_2);
			g2.fillOval(getWidth()-getHeight(), 0, getHeight(), getHeight());
		}else{
			g2.setColor(Constants.FOREGROUND_COLOR_1_B);
			g2.fillOval(0, 0, getHeight(), getHeight());
		}
	}

}
