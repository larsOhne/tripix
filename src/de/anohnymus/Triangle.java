package de.anohnymus;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import delaunay_triangulation.Point_dt;
import delaunay_triangulation.Triangle_dt;

public class Triangle extends Polygon {
	
	private Color color = Color.BLACK;
	private Point_dt a,b,c;	//vertices of this Triangle
	
	public Triangle(){
		super();
	}
	
	public Triangle(Point_dt a, Point_dt b, Point_dt c) {
		this();
		this.a = a;
		this.b = b;
		this.c = c;
		
		addPoint((int)a.x(),(int) a.y());
		addPoint((int)b.x(),(int) b.y());
		addPoint((int)c.x(),(int) c.y());
	}
	
	public Triangle(Triangle_dt tr){
		this(tr.p1(), tr.p2(), tr.p3());
	}

	public void draw(Graphics2D g) {
		g.setColor(color);
		g.setStroke(new BasicStroke(2.0f));
		g.draw(this);
		g.fill(this);
	}
	
	public Point_dt getCenter(){
		BaryCentricCoord center = new BaryCentricCoord(0.33333333, 0.33333333, 0.33333333);
		return center.toKartesian(this);
	}
	
	public double getArea(){
		return 0.5*(a.x()*(b.y() - c.y()) + b.x()*(c.y() - a.y()) + c.x()*(a.y() - b.y()));
	}
	
	public Point_dt randomPoint(){
		double l1 = Math.random(),l2 = Math.random(),l3 = Math.random();
		double sum = l1+l2+l3;
		l1 /= sum;
		l2 /= sum;
		l3 /= sum;
		
		return new BaryCentricCoord(l1, l2, l3).toKartesian(this);
	}
	
	public Color getColor() {
		return color;
	}
	
	public String getColorHex(){
		return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public boolean contains(Point2D p) {
		return contains(p.getX(), p.getY());
	}

	@Override
	public boolean contains(Rectangle2D r) {
		return contains(r.getMinX(), r.getMinY(), r.getWidth(), r.getHeight());
	}

	@Override
	public boolean contains(double x, double y) {
		BaryCentricCoord p = new BaryCentricCoord(new Point_dt(x,y),this);
		return (Math.abs(p.getL1()) + Math.abs(p.getL2()) + Math.abs(p.getL3())) <= 1;
	}

	@Override
	public boolean contains(double x, double y, double width, double height) {
		return contains(x, y) && contains(x, y + height) 
			   && contains(x + width, y + height) && contains(x+width, y);
	}
	
	@Override
	public String toString() {
		return "Triangle: A=" +a.toString() + "\t B: " + b.toString() + "\t C:" + c.toString();
	}

	public Point_dt getA() {
		return a;
	}

	public Point_dt getB() {
		return b;
	}

	public Point_dt getC() {
		return c;
	}

	
	

}
