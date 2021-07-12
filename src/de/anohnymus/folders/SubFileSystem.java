package de.anohnymus.folders;

import java.io.File;

public class SubFileSystem {
	
	private String name;
	private String fileExtension;

	SubFileSystem(String name, String fileExtension) {
		this.name = name;
		this.fileExtension = fileExtension;
		
	}
	
	File makeFile(FileSystem sys, String fileName){
		sys.makeDir(name);
		return sys.makeFile(name +"/" + fileName + "." + fileExtension);
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getFileExtension() {
		return fileExtension;
	}
}
