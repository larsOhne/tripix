package de.game.plugin.swingcomponents;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

public class Hexagon extends Polygon{

	private static final long serialVersionUID = 16091997L;
	
	public static double EDGE_ANGLE = Math.PI/3;
	public static int STANDARD_THICKNESS = 20;
	
	private int radius = 0;
	private int offsetX = 0;
	private int offsetY = 0;
	private int thickness = STANDARD_THICKNESS;
	
	private boolean isFilled = true;
	private double scaleFactor = 1;
	
	public Hexagon(int radius){
		this.radius = radius;
		offsetX = radius; 
		offsetY = radius; 
		refreshPolygon();
	}

	

	private void refreshPolygon() {
		reset();
		int x = 0;
		int y = (int) (-radius*scaleFactor);
		
		Point middle = getMiddle();
		
		for(double alpha = 0;alpha <=2*Math.PI; alpha+=EDGE_ANGLE ){
			int newX = middle.x  	+ (int) (Math.cos(alpha)*x-Math.sin(alpha)*y);
			int newY = middle.y 	+ (int) (Math.sin(alpha)*x + Math.cos(alpha)*y);
			addPoint(newX, newY);
		}
		
		if(!isFilled()){
			y +=thickness;
			
			for(double alpha = 0;alpha <=2*Math.PI; alpha+=EDGE_ANGLE ){
				int newX = middle.x 	+ (int) (Math.cos(alpha)*x-Math.sin(alpha)*y);
				int newY = middle.y  	+ (int) (Math.sin(alpha)*x + Math.cos(alpha)*y);
				addPoint(newX, newY);
			}
		}
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(offsetX,offsetY,radius*2,radius*2);
	}
	
	public Rectangle getContainedArea(){
		Rectangle r = new Rectangle();
		
		Point middle = getMiddle();
		
		r.width = (int) (radius*Math.sqrt(2));
		r.x = (int) (middle.x-r.width/2);
		r.y = middle.y-radius/2;
		r.height = radius;
		
		return r;
	}
	
public Point getMiddle(){
		return new Point(offsetX+radius,offsetY+radius);
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
		refreshPolygon();
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
		refreshPolygon();
	}

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
		refreshPolygon();
	}

	public boolean isFilled() {
		return isFilled;
	}

	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
		refreshPolygon();
	}

	public int getThickness() {
		return thickness;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

	public double getScaleFactor() {
		return scaleFactor;
	}

	public void setScaleFactor(double scaleFactor) {
		this.scaleFactor = scaleFactor;
		refreshPolygon();
	}

}
