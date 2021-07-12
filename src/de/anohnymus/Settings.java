package de.anohnymus;

import java.io.Serializable;

public class Settings implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final String SRC = "data/last.txt";
	private int numOfVertices = 1000;
	private int numOfRandoms = 1000;
	private int additionalBorderVertices = 0;
	private double percentageOfOld = 0.;
	private double colorDensity = 0.3;
	private String pathToSource = "";

	public int getNumOfVertices() {
		return numOfVertices;
	}

	public void setNumOfVertices(int numOfVertices) {
		this.numOfVertices = numOfVertices;
	}

	public int getAdditionalBorderVertices() {
		return additionalBorderVertices;
	}

	public void setAdditionalBorderVertices(int additionalBorderVertices) {
		this.additionalBorderVertices = additionalBorderVertices;
	}

	public int totalVertices() {
		return 4 + additionalBorderVertices*4 + numOfVertices;
	}

	public int getNumOfRandoms() {
		return numOfRandoms;
	}

	public void setNumOfRandoms(int numOfRandoms) {
		this.numOfRandoms = numOfRandoms;
	}

	public double getPercentageOfOld() {
		return percentageOfOld;
	}

	public void setPercentageOfOld(double percentageOfOld) {
		this.percentageOfOld = percentageOfOld;
	}

	public double getColorDenssity() {
		return colorDensity;
	}

	public void setColorDensity(double testPixels) {
		this.colorDensity = testPixels;
	}

	public String getPathToSource() {
		return pathToSource;
	}

	public void setPathToSource(String pathToSource) {
		this.pathToSource = pathToSource;
	}
}
