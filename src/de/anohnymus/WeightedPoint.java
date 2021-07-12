package de.anohnymus;

import delaunay_triangulation.Point_dt;

public class WeightedPoint extends Point_dt implements Comparable<WeightedPoint> {
	
	private double weight = 0;

	public WeightedPoint(){
		super();
	}
	
	public WeightedPoint(double x, double y, double weight) {
		super(x, y);
		this.setWeight(weight);
	}

	@Override
	public int compareTo(WeightedPoint toCompare) {
		if(getWeight() < toCompare.getWeight()) return -1;
		else if(getWeight()== toCompare.getWeight()) return 0;
		else return 1;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public WeightedPoint melt(WeightedPoint p, double percentageOfNew){
		double x = weightedAverage(x(), p.x(), percentageOfNew);
		double y = weightedAverage(y(), p.y(), percentageOfNew);
		double w = weightedAverage(getWeight(), p.getWeight(), percentageOfNew);
		
		return new WeightedPoint(x, y, w);
	}
	
	@Override
	public String toString() {
		return super.toString() + "[weight: " + getWeight() + "]";
	}
	
	private double weightedAverage(double val1, double val2, double perc){
		return perc*val1 + (1-perc)*val2;
	}

}
