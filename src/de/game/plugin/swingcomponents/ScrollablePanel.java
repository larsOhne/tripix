package de.game.plugin.swingcomponents;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.Scrollable;


public class ScrollablePanel extends GPanel implements Scrollable {

	private static final long serialVersionUID = 112L;

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return new Dimension(200,200);
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		return 0;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		return true;
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		return 0;
	}

}
