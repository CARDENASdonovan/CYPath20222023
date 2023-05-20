package developpement;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Player extends Circle {
	public Player(String playerName, double radius, Color color) {
		super(-1000,-1000, radius);
		super.setId(playerName);
		super.setFill(color);
	}
	
	@Override
	// Print a string with all the info of 
	public String toString() {
		return("Player idNumber: " + getId() + "\n" + "centerX: " + getCenterX() + "\n" + "centerY: " + getCenterY() + "\n" + "Radius: " + getRadius() + "." + "\n");
	}
	
	public boolean equals(Object obj) {
		return false;
	}
	
	// Methods	
	protected void addPlayerTextId(String textString, AnchorPane pane) {
		Text text = new Text(getCenterX() - getRadius(), getCenterY(), textString);
		text.setId("Player" + textString + "Text");
		pane.getChildren().add(text);
	}
}
