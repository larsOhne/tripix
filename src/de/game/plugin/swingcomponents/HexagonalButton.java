package de.game.plugin.swingcomponents;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class HexagonalButton extends JButton {

	private static final long serialVersionUID = 16091997L;
	private Hexagon h;
	private JLabel lbl = new JLabel();
	
	public HexagonalButton(){
		super();
		onConstruction();
	}
	
	public HexagonalButton(Icon icon){
		super(icon);
		onConstruction();
	}
	
	public HexagonalButton(String title){
		super(title);
		lbl.setText(title);
		onConstruction();
	}
	
	private void onConstruction(){
		h = new Hexagon(0);
		h.setScaleFactor(0.98);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(lbl);
		
		setOpaque(false);
		setBorder(null);
		setBackground(Constants.FOREGROUND_COLOR_3);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				h.setScaleFactor(1);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				h.setScaleFactor(0.98);
			}
		});

	}
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		h.setRadius(Math.min(getWidth()/2, getHeight()/2));
		h.setOffsetX(h.getRadius());
		h.setOffsetY(h.getRadius());
		System.out.println(getBounds());
		
		g2.setColor(getBackground());
		g2.fillPolygon(h);
	}

}
