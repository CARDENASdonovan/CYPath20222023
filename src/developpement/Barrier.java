package developpement;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Barrier extends Rectangle {
	private final static int DEFAULT_NUMBER = 0;
	private final static int DEFAULT_X = 0;
	private final static int DEFAULT_Y = 0;
	private final static int DEFAULT_WIDTH = 5;
	private final static int DEFAULT_HEIGHT = 5;
	private final static Color DEFAULT_COLOR = Color.CHARTREUSE;
	private String IdTile1 = "";
	private String IdTile2 = "";
	
	protected String getIdTile1() {
		return this.IdTile1;
	}

	protected String getIdTile2() {
		return this.IdTile2;
	}
	
	protected void setIdTile1(String id) {
		this.IdTile1 = id;
	}
	
	protected void setIdTile2(String id) {
		this.IdTile2 = id;
	}
	
	protected Barrier(int idNumber, double x, double y, double width, double height, Color color) {	
		super(x, y, width, height);
		super.setId("Barrier" + Integer.toString(idNumber));
		super.setFill(color);
	}
	
	protected Barrier() {
		this(DEFAULT_NUMBER, DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR);		
	}
	
	@Override
	// Print a string with all the info of 
	public String toString() {
		return("Barrier number: " + getId() + "\n" + "X: " + getX() + "\n" + "Y: " + getY() + "\n" + "Width: " + getWidth() + "\n" + "Height: " + getHeight() + ".");
	}
	
	public boolean equals(Object obj) {
		return false;
	}
	
	// Methods
	protected void addBarrierTextId(String textString, Color textColor, int angle, Pane pane) {
		Text text = new Text(getX(), getY(), textString);
		text.setId("Barrier" + textString + "Text");
		text.setFill(textColor);
		text.setRotate(angle);
		pane.getChildren().add(text);
	}
}
