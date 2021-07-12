package de.game.plugin.swingcomponents;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Scaler {

	/**
	 * Skaliert Bilder. Ist toScale == null wird ebenfalls null zurückgeliefert. Ist eine der beiden neuen Maße == 0,
	 * dann wird proportional zur anderen größe skaliert.
	 * @param toScale das zu skalierende Bild
	 * @param width die neue Breite
	 * @param height die neue Höhe
	 * @return das skalierte Bild
	 */
	public static synchronized BufferedImage scale(BufferedImage toScale, int width,int height) {
		if (toScale == null)
			return null;
		if (width == 0) {
			int newWidth = toScale.getWidth() * height / toScale.getHeight();
			return scale(toScale, newWidth, height);
		} else if (height == 0) {
			int newHeight = toScale.getHeight()*width/toScale.getWidth();
			return scale(toScale,width,newHeight);
		} else {
			BufferedImage temp = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = temp.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
					RenderingHints.VALUE_ANTIALIAS_ON);
			
			g.drawImage(toScale, 0, 0, width, height, null);
			return temp;
		}
	}
	
	// found at: http://flyingdogz.wordpress.com/2008/02/11/image-rotate-in-java-2-easier-to-use/
	public static synchronized BufferedImage rotate(BufferedImage image, double angle) 
	{
	    float sin = (float) Math.abs(Math.sin(angle));
	    float cos = (float) Math.abs(Math.cos(angle));

	    int w = image.getWidth() ;
	    int h = image.getHeight();

	    int neww = (int) Math.round(w * cos + h * sin);
	    int newh = (int) Math.round(h * cos + w * sin);

	    //-----------------------MODIFIED--------------------------------------
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice gd = ge.getDefaultScreenDevice();
	    //---------------------------------------------------------------------

	    GraphicsConfiguration gc = gd.getDefaultConfiguration();

	    BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
	    Graphics2D g = result.createGraphics();

	    //-----------------------MODIFIED--------------------------------------
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON) ;
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC) ;
	    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY) ;
	    //---------------------------------------------------------------------

	    //-----------------------MODIFIED, BUT NOT CRUCIAL---------------------
	    AffineTransform at = AffineTransform.getTranslateInstance((neww-w)/2, (newh-h)/2);
	    at.rotate(angle, w/2, h/2);
	    //---------------------------------------------------------------------

	    g.drawRenderedImage(image, at);
	    g.dispose();

	    return result;
	}

}
