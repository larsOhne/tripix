package de.anohnymus.folders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSystem {
	
	private File sourceDir;
	
	//dir names
	public static SubFileSystem LOG = new SubFileSystem("logs", "log");
	public static SubFileSystem DATA = new SubFileSystem("data", "dat");
	public static SubFileSystem OBJ = new SubFileSystem("objects", "obj");
	public static SubFileSystem TEMP = new SubFileSystem("temp", "tmp");
	
	protected FileSystem(){
		super();
	}
	
	public FileSystem(String dirName){
		this();
		makeSystem(dirName);
	}

	public void makeSystem(String dirName){
		sourceDir = new File(System.getProperty("user.home")+"/" + dirName);
		
		if(!sourceDir.exists()){
			sourceDir.mkdir();
		}

	}
	
	public File makeDir(String dirName){
		File dir = new File(sourceDir.getAbsolutePath()+"/" + dirName);
		if(dir.isFile())throw new IllegalArgumentException(dirName + " denotes a file, not a directory");
		
		if(!dir.exists()){
			dir.mkdir();
		}
		
		return dir;
	}
	
	public File makeFile(String fileName){
		File f = new File(sourceDir.getAbsolutePath()+ "/" + fileName);
		if(f.isDirectory())throw new IllegalArgumentException(fileName + " denotes a directory, not a file");
		
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return f;
	}
	
	public File makeFile(String fileName, SubFileSystem sfs){
		return sfs.makeFile(this, fileName);
	}
	
	public void saveObject(Object obj, String fileName) throws IOException{
		File f = makeFile(fileName, OBJ);
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
		out.writeObject(obj);
		out.flush();
		out.close();
	}
	
	public Object loadObject(String fileName) throws IOException, ClassNotFoundException{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(sourceDir.getAbsolutePath() + "/" +OBJ.getName() + "/" +
				fileName + "." + OBJ.getFileExtension())));
		
		Object obj =  in.readObject();
		in.close();
		
		return obj;
	}
	
	
	public File getSourceDir() {
		return sourceDir;
	}
}
