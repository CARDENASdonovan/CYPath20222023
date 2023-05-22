package developpement;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * The Barrier class represents the separation between every tile of the game board. This class inherit from the Rectangle class from javafx with all its attributes and methods.
 * This class has 8 attributes : a number to set the ID of the object, two coordinates (x and y), a width, a height, a color and two String attributes that represents the ID of the adjacent tiles of the barrier.
 * @author Cardenas D, Ruellan B, Machnik A, Johnson A, Guenneau R
 * 
 *
 */
public class Barrier extends Rectangle {
	private final static int DEFAULT_NUMBER = 0;
	private final static int DEFAULT_X = 0;
	private final static int DEFAULT_Y = 0;
	private final static int DEFAULT_WIDTH = 5;
	private final static int DEFAULT_HEIGHT = 5;
	private final static Color DEFAULT_COLOR = Color.CHARTREUSE;
	private String idTile1 = "";
	private String idTile2 = "";
	
	/**
	 * This method is the constructor of the Barrier class with attributes given.
	 * @param int idNumber
	 * @param double width
	 * @param double height
	 * @param Color color
	 * @param String idTile1
	 * @param String idTile2
	 */
	public Barrier(int idNumber, double width, double height, Color color, String idTile1, String idTile2) {	
		super(0, 0, width, height);
		super.setId("Barrier" + Integer.toString(idNumber));
		super.setFill(color);
		this.idTile1 = idTile1;
		this.idTile2 = idTile2;
	}
	
	/**
	 * This method is the constructor of the Barrier class with default values for every attributes.
	 */
	
	public Barrier() {
		this(DEFAULT_NUMBER, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR,"","");		
	}
	
	/**
	 * This method is used to add text on a barrier.
	 * @param String textString
	 * @param Color textColor
	 * @param int angle
	 * @param Pane pane
	 */
	/*
	protected void addBarrierTextId(String textString, Color textColor, int angle, GridPane pane) {
		Text text = new Text(getX(), getY(), textString);
		text.setId("Barrier" + textString + "Text");
		text.setFill(textColor);
		text.setRotate(angle);
		pane.add(text, column, row);
	}*/
	
	/**
	 * This method returns the id of one of the tile attached to the barrier.
	 * @return the id (String) of one of the adjacent tile of the barrier
	 */
	protected String getIdTile1() {
		return this.idTile1;
	}
	
	/**
	 * This method is identical to the previous one (getIdTile1()) but gives the id of the other tile.
	 * @return the id (String) of the other adjacent tile.
	 */

	protected String getIdTile2() {
		return this.idTile2;
	}
	
	/**
	 * This method allows to change the id of the tile adjacent to the barriers.
	 * @param String id, the id of the new adjacent tile
	 */
	protected void setIdTile1(String id) {
		this.idTile1 = id;
	}
	
	/**
	 * This method is identical to the previous one and allows to change the id of the other tile adjacent to the barriers.
	 * @param String id, the id of the other new adjacent tile
	 */
	
	protected void setIdTile2(String id) {
		this.idTile2 = id;
	}
	
	@Override
	/**
	 * This method allows to print every attributes of the Barrier object in the terminal.
	 * @return a String to print
	 */
	public String toString() {
		return("Barrier idNumber: " + getId() + "\n" + "X: " + getX() + "\n" + "Y: " + getY() + "\n" + "Width: " + getWidth() + "\n" + "Height: " + getHeight() + "\n" + "idTile1: " + getIdTile1() + "\n" + "idTile2: " + getIdTile2() + ".");
	}
	
	@Override
	/**
	 * This method compares two objects of Barrier type based on their ID.
	 * @param Object obj, an object to compare with the current one
	 * @return a boolean (true if the objects are equal, false if there are not)
	 */
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		
		Barrier otherBar = (Barrier) obj;
		return this.getId().equals(otherBar.getId()); 
	}
}
