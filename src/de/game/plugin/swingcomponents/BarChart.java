package de.game.plugin.swingcomponents;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
/**
 * Die Klasse BarChart erzeugt ein Balkendiagramm, welches Werte von bestimmten 
 * Labels anzeigt
 * 
 * @author Lars Ohnemus
 * @version 1.0
 * 
 */
public class BarChart extends GPanel {

	private static final long serialVersionUID = 16091997L;

	private double values[];
	private String names[];
	private String levelNames[];
	private double levelValues[];
	private String title;
	
	private double minValue = Double.MAX_VALUE; 
	private double maxValue = Double.MIN_VALUE; 
	
	private GLabel titleLabel = new GLabel(1);
	private GPanel barPanel = new GPanel();
	private GPanel valuePanel = new GPanel();
	private GPanel namePanel = new GPanel();
	
	/**
	 * Erzeugt ein leeres Diagramm
	 */
	public BarChart(){
		setLayout(new BorderLayout(0, 0));
		
		// Den Titel zentriert in den oberen Teil des Diagramms
		GPanel wrapperPanel = new GPanel();
		add(wrapperPanel, BorderLayout.NORTH);
		wrapperPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		wrapperPanel.add(titleLabel);
		
		GPanel wrapperPanel2 = new GPanel();
		wrapperPanel2.setLayout(new BorderLayout(0, 0));
		wrapperPanel2.add(barPanel, BorderLayout.CENTER);
		add(wrapperPanel2, BorderLayout.CENTER);
		barPanel.setLayout(new GridLayout(1, 0,10,10));
		
		namePanel.setLayout(barPanel.getLayout());
		wrapperPanel2.add(namePanel, BorderLayout.SOUTH);
		
		add(valuePanel, BorderLayout.WEST);
	}
	

	/**
	 * Erzeugt ein Balkendiagramm mit folgenden Eigenschaften
	 * @param values die anzuzeigenden Werte
	 * @param names die Namen der Balken
	 * @param title der Titel des Diagramms
	 */
	public BarChart(double[] values, String[] names, String title){
		this();
		
		this.title = title;
		titleLabel.setText(getTitle());
		setValues(values);
		this.names = names;
		
		// Die Balken einfügen
		for (int i = 0; i < values.length; i++) {
			barPanel.add(new GBar(map(values[i], 0, maxValue, 0, 1)));
			GPanel wrapperPanel = new GPanel();
			wrapperPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			wrapperPanel.add(new GLabel(3,names[i]));
			namePanel.add(wrapperPanel);
		}
		
	}
	
	/**
	 * liefert die Werte aller Balken zurück
	 * @return die Werte der Balken
	 */
	public double[] getValues() {
		return values;
	}
	
	/**
	 * setzt die Werte aller Balken neu und repainted das Diagramm
	 * @param values die neuen Werte
	 */
	public void setValues(double values[]) {
		this.values = values;
		
		for (int i = 0; i < values.length; i++) {
			if(values[i]<minValue)minValue = values[i];
			else if (values[i]>maxValue)maxValue = values[i];
		}
	}

	/**
	 * Gibt die Namen aller Balken zurück
	 * @return die Namen der Balken
	 */
	public String[] getNames() {
		return names;
	}

	/**
	 * setzt die Namen aller Balken neu und repainted das Diagramm
	 * @param names die neuen Namen
	 */
	public void setNames(String names[]) {
		this.names = names;
	}

	/**
	 * Gibt die spezifischen Beschriftungen der Werte-Achse zurück
	 * @return <b>null</b>, wenn keine spezifischen Beschriftungen definiert wurden, ansonsten 
	 * die spezifischen Beschriftungen
	 */
	public String[] getLevelNames() {
		return levelNames;
	}
	
	/**
	 * setzt die spezifischen Beschriftungen der Werte-Achse neu und repainted das Diagramm.
	 * @param levelNames die neuen Beschriftungen. Ist  <b> levelNames == null </b> 
	 * werden lediglich die numerischen Beschriftungen der Werte-Achse angezeigt.
	 */
	public void setLevelNames(String levelNames[]) {
		this.levelNames = levelNames;
	}

	/**
	 * 
	 * @return die Werte der spezifischen Beschriftungen
	 */
	public double[] getLevelValues() {
		return levelValues;
	}

	/**
	 * Setzt die Werte der spezifischen Beschriftungen der Werte-Achse neu und repainted 
	 * das Diagramm
	 * @param levelValues die neuen Werte
	 */
	public void setLevelValues(double levelValues[]) {
		this.levelValues = levelValues;
	}

	/**
	 * 
	 * @return den Titel des Diagramms
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setzt den Titel des Diagramms neu und repainted es.
	 * @param title der neue Titel
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 
	 * @return den Wert des größten Balkens
	 */
	public double getMaxValue(){
		return maxValue;
	}

	/**
	 * 
	 * @return den Wert des kleinsten Balkens
	 */
	public double getMinValue(){
		return minValue;
	}
	
	private double map(double val, double  min1, double max1,double min2, double max2){
		if(max1-min2 == 0)return 0;
		return (val-min1)/(max1-min1)*(max2-min2)+min2;
	}
	
}
