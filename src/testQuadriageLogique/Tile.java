package testQuadriageLogique;

import java.util.TreeMap;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Region{
	protected final int tileNumber;
	
	protected final int tileX;
	protected final int tileY;
	
	protected final Color tileColor;
	
	protected final int TILE_WiDTH_HEIGHT = 50; 
	
	
	public Tile(int tileNumber, int tileX, int tileY, Color tileColor) {
		this.tileNumber = tileNumber;
		this.tileX = tileX;
		this.tileY = tileY;
		this.tileColor = tileColor;
		Rectangle tile = new Rectangle(tileX, tileY, TILE_WiDTH_HEIGHT, TILE_WiDTH_HEIGHT);		
	}
	
	public TreeMap<Integer,Tile> createBoardEmpty(AnchorPane root){
		
		final TreeMap<Integer,Tile> boardEmpty = new TreeMap<>();
		
		for(int i = 0; i < 5; i++) {
			Tile tile = new Tile(i, 60*i, 60*i, Color.RED);
			root.getChildren().add(tile);
		}
		
		return boardEmpty;
	}
}
