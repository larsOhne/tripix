package de.game.plugin.swingcomponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Constants {

	public static final Color BACKGROUND_COLOR 		= new Color(43,43,43);
	public static final Color FOREGROUND_COLOR_1 	= new Color(61,61,61);
	public static final Color FOREGROUND_COLOR_1_B 	= FOREGROUND_COLOR_1.brighter().brighter();
	public static final Color FOREGROUND_COLOR_2 	= new Color(255,110,65);
	public static final Color FOREGROUND_COLOR_3 	= new Color(68,232,198);
	
	public static final Font FONT_LAYER_1 	= new Font("Calibri", Font.BOLD, 32);
	public static final Font FONT_LAYER_2 	= new Font("Calibri", Font.BOLD, 24);
	public static final Font FONT_LAYER_3 	= new Font("Calibri", Font.BOLD, 16);
	public static final Font FONT_LAYER_4 	= new Font("Calibri", Font.PLAIN, 16);
	public static final Font FONT_LAYER_5 	= new Font("Calibri", Font.ITALIC, 16);
	public static final Font FONT_LAYER_6 	= new Font("Calibri", Font.PLAIN, 14);

	public static final Font FONT_BACKBUTTON = new Font("Calibri", Font.BOLD, 42);
	
	public static BufferedImage LOGO = null;
	
	
	public static void loadGlobalGraphics(){
		try {
			LOGO = ImageIO.read(Constants.class.getClassLoader().getResourceAsStream("gfx/gameLogo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
