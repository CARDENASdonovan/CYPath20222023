package developpement;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Tile extends Rectangle {
	private final static int DEFAULT_idNumber = 0;
	private final static int DEFAULT_X = 0;
	private final static int DEFAULT_Y = 0;
	private final static int DEFAULT_WIDTH_HEIGHT = 50;
	private final static Color DEFAULT_COLOR = Color.CHARTREUSE;
	
	
	protected Tile(int idNumber, double x, double y, double widthHeight, Color color) {
		super(x, y, widthHeight, widthHeight);
		super.setId("Tile" + Integer.toString(idNumber));
		super.setFill(color);
	}

	protected Tile() {
		this(DEFAULT_idNumber, DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH_HEIGHT, DEFAULT_COLOR);		
	}
	
	@Override
	// Print a string with all the info of 
	public String toString() {
		return("Tile idNumber: " + getId() + "\n" + "X: " + getX() + "\n" + "Y: " + getY() + "\n" + "Width / Height: " + getWidth() + ".");
		
	}
	
	public boolean equals(Object obj) {
		return false;
	}
	
	// Methods
	protected void addTileTextId(String textString, Pane pane) {
		Text text = new Text(30 + getX(), 35 + getY(), textString);
		text.setId("Tile" + textString + "Text");
		pane.getChildren().add(text);
	}

/*	
	protected TreeMap<Integer,Tile> createBoardEmpty(AnchorPane root){
		
		final TreeMap<Integer,Tile> boardEmpty = new TreeMap<>();
		
		for(int i = 0; i < 5; i++) {
			Tile  = new Tile(i, 60*i, 60*i, Color.RED);
			root.getChildren().add();
		}
		
		return boardEmpty;
	}*/
}
