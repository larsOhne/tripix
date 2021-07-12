package de.anohnymus;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class SVGGenerator extends SwingWorker<Void, String> {
	
	private File output;
	private ArrayList<Triangle> triangles;
	private int width;
	private int height;

	public SVGGenerator(File output, ArrayList<Triangle> triangles, int width, int height) {
		this.output = output;
		this.triangles = triangles;
		this.width = width;
		this.height = height;
	}

	@Override
	protected Void doInBackground() throws Exception {
		BufferedWriter buf = null;
		try{
		buf = new BufferedWriter(new FileWriter(output));
		
		buf.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		buf.write("\n");
		buf.write("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">");
		buf.write("\n");
		buf.write("<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0px\" y=\"0px\"");
		buf.write(" xml:space=\"preserve\"");
		buf.write(" width=\"" + width + "px\"");
		buf.write(" height=\"" + height + "px\"");
		buf.write(" viewbox=\" 0 0 "+width + " " + height +"\"");
		buf.write(">\n");
		
		for(Triangle p: triangles){
			buf.write("<polygon ");
			buf.write("fill=\""+p.getColorHex()+"\" ");
			buf.write("points=\""	+ p.getA().x() + "," + p.getA().y() + " " 
									+ p.getB().x() + "," + p.getB().y() + " " 
									+ p.getC().x() + "," + p.getC().y() + "\" " );
			buf.write("stroke=\"" + p.getColorHex() + "\" ");
			buf.write("stroke-miterlimit=\"" + 10 + "\" ");
			buf.write("/> \n");
		}
		
		buf.write("</svg>");
		}catch(IOException e){
			publish(e.getMessage());
		}finally {
			buf.flush();
			buf.close();
		}
		
		return null;
	}
	
	@Override
	protected void process(List<String> chunks) {
		JOptionPane.showMessageDialog(null, chunks.get(0));
	}

}
