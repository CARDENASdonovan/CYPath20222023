package developpement;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * The Tile class is used to create a tile of the board on which you can place the players, it inherits from the Rectangle class from javafx.
 * @author CYTech Student
 *
 */
public class Tile extends Rectangle {
	private final static int DEFAULT_idNumber = 0;
	private final static int DEFAULT_X = 0;
	private final static int DEFAULT_Y = 0;
	private final static int DEFAULT_WIDTH_HEIGHT = 50;
	private final static Color DEFAULT_COLOR = Color.CHARTREUSE;
	
	/**
	 * This method is the constructor of the Tile class with attributes given. 
	 * @param int idNumber
	 * @param double x
	 * @param double y
	 * @param double widthHeight
	 * @param Color color
	 */
	protected Tile(int idNumber, double widthHeight, Color color) {
		super(0, 0, widthHeight, widthHeight);
		super.setId("Tile" + Integer.toString(idNumber));
		super.setFill(color);
	}
	
	/**
	 * This method is the constructor of the Tile class with default values for every attributes.
	 */
	protected Tile() {
		this(DEFAULT_idNumber, DEFAULT_WIDTH_HEIGHT, DEFAULT_COLOR);		
	}
	
	@Override
	/**
	 * This method allows to print every attributes of the Barrier object in the terminal.
	 * @return a String to print
	 */ 
	public String toString() {
		return("Tile idNumber: " + getId() + "\n" + "X: " + getX() + "\n" + "Y: " + getY() + "\n" + "Width / Height: " + getWidth() + ".");
		
	}
	
	@Override
	/**
	 * This method compares two objects of Tile type based on their ID.
	 * @param Object obj, an object to compare with the current one
	 * @return a boolean (true if the objects are equal, false if there are not)
	 */
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		
		Tile otherTile = (Tile) obj;
		return this.getId().equals(otherTile.getId()); 
	}
	
	/**
	 * This method is used to add text on a player.
	 * @param String textString
	 * @param Pane pane
	 */
	protected void addTileTextId(String textString, Pane pane) {
		Text text = new Text(30 + getX(), 35 + getY(), textString);
		text.setId("Tile" + textString + "Text");
		pane.getChildren().add(text);
	}
}
