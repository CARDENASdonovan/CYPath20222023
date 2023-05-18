package testQuadriageLogique;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Tile extends Rectangle {
	private final static int DEFAULT_NUMBER = 0;
	private final static int DEFAULT_X = 0;
	private final static int DEFAULT_Y = 0;
	private final static int DEFAULT_WIDTH_HEIGHT = 50;
	private final static Color DEFAULT_COLOR = Color.CHARTREUSE;
	
	
	protected Tile(int number, int x, int y, int widthHeight, Color color) {
		super(x, y, widthHeight, widthHeight);
		super.setId("Tile" + Integer.toString(number));
		super.setFill(color);
	}

	protected Tile() {
		this(DEFAULT_NUMBER, DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH_HEIGHT, DEFAULT_COLOR);		
	}
	
	@Override
	// Print a string with all the info of 
	public String toString() {
		return("Tile number: " + getId() + "\n" + "X: " + getX() + "\n" + "Y: " + getY() + "\n" + "Width / Height: " + getWidth() + "." + "\n");
	}
	
	public boolean equals(Object obj) {
		return false;
	}
	
	// Methods
	protected void addTileText(Tile tile, String textString, Pane pane) {
		Text text = new Text(getX(), getY(), textString);
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
