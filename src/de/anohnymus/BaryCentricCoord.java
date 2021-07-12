package de.anohnymus;

import delaunay_triangulation.Point_dt;

public class BaryCentricCoord {

	private static double EPS = 0.00001;
	
	private double l1, l2, l3;
	
	public BaryCentricCoord(double l1, double l2, double l3){
		this.l1 = l1;
		this.l2 = l2;
		if(1-l1-l2-l3 > EPS)throw new IllegalArgumentException("Lambdas didn't sum up to 1! (Epsilon: "+EPS);
		this.l3 = 1 - l1 - l2;
	}
	
	public BaryCentricCoord(Point_dt p, Triangle tr){
		this(p, tr.getA(), tr.getB(), tr.getC());
	}

	public BaryCentricCoord(Point_dt p, Point_dt a, Point_dt b, Point_dt c) {
		double t1 = b.y() - c.y();
		double t2 = c.x() - b.x();
		double t3 = p.y() - c.y();
		double t4 = a.y() - c.y();
		
		l1 = (t1*(p.x() - c.x()) + t2*t3)/(t1*(a.x() - c.x()) + t2*t4);
		l2 = (t3 - l1*t4)/t1;
		l3 = 1 - l1 - l2;
	}
	
	public Point_dt toKartesian(Triangle tr){
		return toKartesian(tr.getA(), tr.getB(), tr.getC());
	}

	public Point_dt toKartesian(Point_dt a, Point_dt b, Point_dt c) {
		double x = l1*a.x() + l2*b.x() + l3*c.x();
		double y = l1*a.y() + l2*b.y() + l3*c.y();
		
		return new Point_dt(x, y);
	}
	
	@Override
	public String toString() {
		return "Coords: " + l1 +"|" + l2 + "|" + l3;
	}
	
	public double getL1() {
		return l1;
	}
	
	public double getL2() {
		return l2;
	}
	
	public double getL3() {
		return l3;
	}
}
