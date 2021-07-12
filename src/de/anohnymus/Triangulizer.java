package de.anohnymus;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.SimpleFormatter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import de.game.plugin.swingcomponents.GButton;
import delaunay_triangulation.Delaunay_Triangulation;
import delaunay_triangulation.Point_dt;
import delaunay_triangulation.Triangle_dt;

public class Triangulizer extends SwingWorker<ArrayList<Triangle>, String> {
	
	private Settings settings = new Settings();
	
	private JLabel container;
	private JTextArea progress;
	
	private BufferedImage toProcess;
	private BufferedImage processed;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("[HH:mm:ss]");

	private GButton btnEx;

	public Triangulizer(BufferedImage toProcess, JTextArea textArea, JLabel container, Settings settings, GButton btnEx) {
		this.toProcess = toProcess;
		this.progress = textArea;
		this.container = container;
		this.settings = settings;
		this.btnEx = btnEx;
		
		textArea.append("\n");
		textArea.append("##### "+ dateFormat.format(Calendar.getInstance().getTime()) +" my Master told me to work #####");
		textArea.append("\n");
	}

	@Override
	protected ArrayList<Triangle> doInBackground() throws Exception {
		processed = new BufferedImage(toProcess.getWidth(), toProcess.getHeight(), BufferedImage.TYPE_INT_ARGB);
		int[][] colorMatrix = new int[toProcess.getWidth()][toProcess.getHeight()];
		int[][] gradMatrix = new int[toProcess.getWidth()][toProcess.getHeight()];
		
		publish("converting image to greyscales...");
		
		//In SW-konvertieren
		for (int x = 0; x < colorMatrix.length; x++) {
			for (int y = 0; y < colorMatrix[0].length; y++) {
				Color c = new Color(toProcess.getRGB(x, y));
				colorMatrix[x][y] = (c.getRed()+c.getGreen()+c.getBlue())/3;
			}
		}
		publish("conversion successful!");
		
		publish("calculate greyscaled gradients...");
		//Gradienten für jedes pixel ermitteln
		for (int x = 0; x < colorMatrix.length; x++) {
			for (int y = 0; y < colorMatrix[0].length; y++) {
				int sum = 0;
				if(x != 0)sum += Math.abs(colorMatrix[x][y]-colorMatrix[x-1][y]);
				if(x != colorMatrix.length -1) sum += Math.abs(colorMatrix[x][y]-colorMatrix[x+1][y]);
				if(y != 0)sum += Math.abs(colorMatrix[x][y]-colorMatrix[x][y-1]);
				if(y != colorMatrix[0].length-1)sum += Math.abs(colorMatrix[x][y]-colorMatrix[x][y+1]);
				
				sum /= 4;
				gradMatrix[x][y] = sum;
			}
		}
		
		publish("calculation successful!");
		
		
		//Delaunay initialisieren:
		publish("initialize Delaunay Triangulation...");
		Delaunay_Triangulation dt = new Delaunay_Triangulation(weightedRandomPoints(gradMatrix,settings.getNumOfVertices()));
		publish("start Triangulation...");
		publish("feed weighted points");
		
		publish("feed " + settings.getNumOfRandoms() + " random points to algorithm");
		for(int i=0; i< settings.getNumOfRandoms(); i++){
			dt.insertPoint(new Point_dt(Math.random()*toProcess.getWidth(), Math.random()*toProcess.getHeight()));
		}
		
		//Punkte am Rand des Bilds generieren
		publish("insert points at the corners for smooth look");
		dt.insertPoint(new Point_dt(0, 0));
		dt.insertPoint(new Point_dt(toProcess.getWidth(), 0));
		dt.insertPoint(new Point_dt(0, toProcess.getHeight()));
		dt.insertPoint(new Point_dt(toProcess.getWidth(), toProcess.getHeight()));
		
		//zusätzliche Punkte an der Oberkante und Unterkante
		publish("feed " + settings.getAdditionalBorderVertices() + " points at borders");
		for(int i=1; i < settings.getAdditionalBorderVertices()+1; i++){
			double newVal = map(i,0,settings.getAdditionalBorderVertices()+2, 0, toProcess.getWidth());
			dt.insertPoint(new Point_dt(newVal, 0));
			dt.insertPoint(new Point_dt(newVal, toProcess.getHeight()));
		}
		
		//zusätzliche Punkte an der linken und rechten Kante
		for(int i=1; i < settings.getAdditionalBorderVertices()+1; i++){
			double newVal = map(i,0,settings.getAdditionalBorderVertices()+2, 0, toProcess.getHeight());
			dt.insertPoint(new Point_dt(0, newVal));
			dt.insertPoint(new Point_dt(toProcess.getWidth(),newVal));
		}
		
		//Dreiecke berechnen
		publish("create triangles");
		Iterator<Triangle_dt> trianglesIterator = dt.trianglesIterator();
		ArrayList<Triangle> triangles = new ArrayList<>();
		Graphics2D g2 = processed.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		while(trianglesIterator.hasNext()){
			Triangle p = null;
			Triangle_dt tr = trianglesIterator.next();
			if(tr.p1() != null && tr.p2() != null && tr.p3() != null){
				p = new Triangle(tr);
				triangles.add(p);
				
				//Bestimme die Farben der Dreiecke
				int a=0,r=0,g=0,b=0;
				int numOfPixels = (int) (p.getArea()*settings.getColorDenssity()) + 1;
				for(int j =0; j<numOfPixels;j++){
					Point_dt testPixel = p.randomPoint();
					
						Color c = new Color(toProcess.getRGB((int)testPixel.x(), (int) testPixel.y()));
						r += c.getRed();
						g += c.getGreen();
						b += c.getBlue();
						a += c.getAlpha();
				}
				
				r /= numOfPixels;
				g /= numOfPixels;
				b /= numOfPixels;
				a /= numOfPixels;
				
				p.setColor(new Color(r,g,b,a));
				p.draw(g2);
				
				publish(triangles.size() +  " triangles created");
			}
			
		}


		
		return triangles;
	}
	
	private Point_dt[] weightedRandomPoints(int[][] matrix, int numOfVertices) {
		WeightedPoint[] points = new WeightedPoint[numOfVertices];
		
		//Alle objekte Initialisieren
		publish("create "+points.length +" weighted points");
		for (int i = 0; i < points.length; i++) {
			points[i] = new WeightedPoint(Math.random()*toProcess.getWidth(),Math.random()*toProcess.getHeight(),Double.MIN_VALUE);
		}
		
		//die komplette Matrix durchlaufen
		publish("fit points to Gradients");
		for (int x = 0; x < matrix.length; x++) {
			for (int y = 0; y < matrix[0].length; y++) {
				if(points[0].getWeight()< matrix[x][y]){
					WeightedPoint newP = new WeightedPoint(x, y, Math.random()*matrix[x][y]);
					points[0] = points[0].melt(newP, settings.getPercentageOfOld());
					Arrays.sort(points);
				}
			}
		}
		
		return points;
	}

	@Override
	protected void process(List<String> chunks) {
		super.process(chunks);
		for(String s : chunks){
			progress.append(dateFormat.format(Calendar.getInstance().getTime()));
			progress.append(s);
			progress.append("\n");
		}
		
	}
	
	@Override
	protected void done() {
		super.done();
		
		progress.append("\n");
		progress.append("##### "+ dateFormat.format(Calendar.getInstance().getTime()) +" that's it for today #####");
		
		//Das Bild anzeigen
		container.setIcon(new ImageIcon(processed));
		btnEx.setEnabled(true);
	}
	
	private double map(double val, double min1, double max1, double min2, double max2){
		return (val-min1)/(max1-min1)*(max2-min2)+min2;
	}

	public BufferedImage getProcessed() {
		return processed;
	}

	public void setProcessed(BufferedImage processed) {
		this.processed = processed;
	}
}
