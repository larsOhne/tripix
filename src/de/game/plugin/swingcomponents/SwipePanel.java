package de.game.plugin.swingcomponents;

import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.util.ArrayList;

public class SwipePanel extends GPanel {

	private static final long serialVersionUID = 16091997L;
	
	private ArrayList<GPanel> views = new ArrayList<GPanel>();
	private GPanel currentView;
	
	public SwipePanel(){
		setLayout(new FlowLayout());
	}
	
	public SwipePanel(GPanel...panels){
		for(GPanel g : panels){
			views.add(g);
			add(g);
			g.setBounds(new Rectangle());
		}
		setCurrentView(panels[0]);
	}

	/**
	 * gibt alle Panels zurück,welche angezeigt werden können
	 * @return die Panels
	 */
	public ArrayList<GPanel> getViews() {
		return views;
	}

	/**
	 * @param views die neuen Panels
	 */
	public void setViews(ArrayList<GPanel> views) {
		this.views = views;
	}

	/**
	 * @return die aktuelle Anzeige
	 */
	public GPanel getCurrentView() {
		return currentView;
	}

	public void setCurrentView(GPanel currentView) {
		this.currentView = currentView;
		System.out.println(currentView.getSize());
		currentView.setBounds(getBounds());
		System.out.println(currentView.getSize());
	}

}
