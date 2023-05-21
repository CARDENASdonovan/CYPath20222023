package developpement;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * The Player class is used to create a player object to use on the board, it inherits from the Circle class with all its attributes and methods.
 * Most of the methods to interact with it are defined in the Board class.
 * @author Cardenas D, Ruellan B, Machnik A, Johnson A, Guenneau R
 */

public class Player extends Circle {
	/**
	 * This method is the constructor of the Player class.
	 * @param String playerName
	 * @param double radius
	 * @param Color color
	 */
	public Player(String playerName, double radius, Color color) {
		super(-1000,-1000, radius);
		super.setId(playerName);
		super.setFill(color);
	}
	
	@Override
	/**
	 * This method allows to print every attributes of the Player object in the terminal.
	 * @return a String to print
	 */
	public String toString() {
		return("Player idNumber: " + getId() + "\n" + "centerX: " + getCenterX() + "\n" + "centerY: " + getCenterY() + "\n" + "Radius: " + getRadius() + "." + "\n");
	}
	
	@Override
	/**
	 * This method compares two objects of Player type based on their ID.
	 * @param Object obj, an object to compare with the current one
	 * @return a boolean (true if the objects are equal, false if there are not)
	 */
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		
		Player otherPlayer = (Player) obj;
		return this.getId().equals(otherPlayer.getId()); 
	}
	
	/**
	 * This method is used to add text on a player.
	 * @param String textString
	 * @param AnchorPane pane
	 */
	protected void addPlayerTextId(String textString, AnchorPane pane) {
		Text text = new Text(getCenterX() - getRadius(), getCenterY(), textString);
		text.setId("Player" + textString + "Text");
		pane.getChildren().add(text);
	}
}
