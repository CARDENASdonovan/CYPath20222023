package testQuadriageLogique;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Player extends Circle {
	public Player(int number, int x, int y, int radius, Color color) {
		super(x,y, radius);
		super.setId("Player" + Integer.toString(number));
		super.setFill(color);
	}
	
	@Override
	// Print a string with all the info of 
	public String toString() {
		return("Player number: " + getId() + "\n" + "centerX: " + getCenterX() + "\n" + "centerY: " + getCenterY() + "\n" + "Radius: " + getRadius() + "." + "\n");
	}
	
	public boolean equals(Object obj) {
		return false;
	}
	
	// Methods	
	protected void addPlayerText(Tile tile, String textString, AnchorPane pane) {
		Text text = new Text(getCenterX(), getCenterY(), textString);
		pane.getChildren().add(text);
	}
}
