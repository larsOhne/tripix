package de.game.plugin.swingcomponents;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GBar extends GPanel {

	private static final long serialVersionUID = 16091997;

	private double filledPercentage = 1;
	
	private GPanel filledArea = new GPanel();
	
	public GBar(double filledPercentage){
		setFilledPercentage(filledPercentage);
		filledArea.setBackground(Constants.FOREGROUND_COLOR_3);
		
		setLayout(null);
		
		add(filledArea);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				filledArea.setBounds(0,(int) (getHeight()*(1-getFilledPercentage())), getWidth(), (int)
						(getHeight()*getFilledPercentage()));
			}
		});
	}
	
	
	@Override
	public void setForeground(Color fg) {
		super.setForeground(fg);
		if(filledArea != null)filledArea.setBackground(getForeground());
	}

	

	/**
	 * Gibt an, wie viel des Panels mit der Vordergrundfarbe gefüllt ist
	 * @return Anteil als Dezimalzahl zwischen 0 und 1
	 */
	public double getFilledPercentage() {
		return filledPercentage;
	}

	/**
	 * Setz, wie viel des Panels mit Vordergrundfarbe gefüllt sein soll
	 * @param filledPercentage Der gewünschte Anteil zwischen 0 und 1
	 */
	public void setFilledPercentage(double filledPercentage) {
		this.filledPercentage = filledPercentage;
		validate();
	}


}
