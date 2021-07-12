package de.anohnymus;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.anohnymus.folders.FileSystem;

public class GradientPixelsMain {
	
	public static FileSystem files;
	
	public static void main(String[] args) {

		//Ordner überprüfen
		launchFiles();
		
		
		startGUI();
//		
//		try {
//			throw new Exception();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	private static void startGUI() {
		// Look and Feel der GUI auf System-intern einstellen
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		
		GradientPixelsFrame f = new GradientPixelsFrame("TriPix by Anohnymus");
		
	}
	
	private static void launchFiles(){
		files = new FileSystem("NSA Data");
	}
}
