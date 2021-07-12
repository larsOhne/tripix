package de.anohnymus.folders;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class FoldersMain {
	public static void maint(String[] args) {
		Logger log = Logger.getLogger("Debug Log");
		FileHandler fh = null;

		try {
			fh = new FileHandler("C:/Users/Anohnymus/Desktop/Debug.log");
			log.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		
		FileSystem sys = new FileSystem("xxx");
		log.info("Source exists: " + sys.getSourceDir().exists());

		File f = sys.makeDir("pics");
		log.info("dir exists: " + f.exists());
		
		File f2 = sys.makeFile("bla.txt");
		log.info("file exists: " + f2.exists());
		
		File f3 = sys.makeFile("logi", FileSystem.LOG);
		log.info("log exists: " + f3.exists());
		
		try {
			sys.saveObject("Hallo", "test");
			log.info("String geschrieben");
			log.info((String) sys.loadObject("test"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
