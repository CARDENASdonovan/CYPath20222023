package developpement;

import java.util.ArrayList;
import java.util.HashMap;

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
	HashMap<String, Color> mapPlayerColor = new HashMap<>();

	private int playerNumber;
	private boolean winner = false;
	private boolean isTurn = false;
	private ArrayList<String> idWinningTiles = new ArrayList<>();
	private String idStartTile;
	private String currentTileId;
	/**
	 * This method is the constructor of the Player class. It assigns a start tile and a list of winning tiles to the player depending on the player number.
	 * @param int playerNumber
	 * @param String playerName
	 * @param double radius
	 * @param Color color
	 */
	private boolean doubleTurn = true;
	
	public boolean getDoubleTurn() {
	    return this.doubleTurn;
	}

	public void setDoubleTurn(boolean x) {
	    this.doubleTurn = x;
	}

	public Player(String playerName, String currentTileid, boolean isTurn) {
		super(-1000,-1000, 25);
		super.setId(playerName);

		mapPlayerColor.put("Player 1", Color.RED);
		mapPlayerColor.put("Player 2", Color.LIGHTBLUE);
		mapPlayerColor.put("Player 3", Color.YELLOW);
		mapPlayerColor.put("Player 4", Color.LIGHTGREEN);
		
		super.setFill(mapPlayerColor.get(playerName));
		
		playerNumber = Integer.parseInt(playerName.substring(7));
		this.setPlayerNumber(playerNumber);
		
		String t = "Tile ";
		switch(playerNumber) {
			
			case 1 : 
				for (int i=1; i<=9; i++) {
					idWinningTiles.add(t+(72+i));		
				}
				this.setIdStartTiles("Tile 5");
				this.setCurrentTileId(currentTileid);
				this.setTurn(isTurn);
			break;
			
			case 2 :
				for(int i=1; i<=9; i++) {
					idWinningTiles.add(t+i);
				}
				this.setIdStartTiles("Tile 77");
				this.setCurrentTileId(currentTileid);
				this.setTurn(isTurn);
			 break;
			
			case 3 :
				for (int i=0; i<=8; i++) {
					idWinningTiles.add(t+(1+i*9));
				}
				this.setIdStartTiles("Tile 45");
				this.setCurrentTileId(currentTileid);
				this.setTurn(isTurn);
			break;
			
			case 4 : 
				for (int i=1; i<=9; i++) {
					idWinningTiles.add(t+(i*9));
				}
				this.setIdStartTiles("Tile 37");
				this.setCurrentTileId(currentTileid);
				this.setTurn(isTurn);
			break;
			 
			default:
			break;
		}
	}
	
	/**
	 * This method is used to get the number of a player.
	 * @return the player number (int)
	 */
	public int getPlayerNumber() {
		return this.playerNumber;
	}
	
	/**
	 * This method is used to set the number of a player.
	 * @param int playerNumber
	 */
	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}
	
	/**
	 * This method is used to get the list of winning tiles for a player.
	 * @return a list of winning tiles
	 */
	public ArrayList<String> getIdWinningTiles(){
		return this.idWinningTiles;
	}
	
	/**
	 * this method is used to set the list of winning tiles for a player.
	 * @param ArrayList(String) idWinningTiles
	 */
	public void setIdWinningTiles(ArrayList<String> idWinningTiles) {
		this.idWinningTiles = idWinningTiles;
	}
	
	/**
	 * This method is used to get the id of the starting tile of a player.
	 * @return the id of the starting tile of the player (String)
	 */
	public String getIdStartTile() {
		return this.idStartTile;
	}

	/**
	 * This method is used to set the id of the starting tile of a player.
	 * @param String idStartTiles
	 */
	public void setIdStartTiles(String idStartTile) {
		this.idStartTile = idStartTile;
	}
	
	/**
	 * This method is used to get the id of the current tile 
	 * @return the id of the tile where the player is
	 */
	public String getCurrentTileId() {
		return(this.currentTileId);
	}
	
	/**
	 * This method is used to set the Tile id for the player
	 * @param String currentTileId
	 */
	public void setCurrentTileId(String currentTileId) {
		this.currentTileId = currentTileId;
	}

	/**
	 * This method is used to get a boolean telling if a player won or not
	 * @return a boolean
	 */
	public boolean isWinner() {
		return winner;
	}
	/**
	 * This method is used to set the winner boolean
	 * @param winner
	 */

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	/**
	 * This method is used to get the argument isTurn of the player
	 * @return a boolean saying if it's the player's turn or not
	 */
	public boolean isTurn() {
		return isTurn;
	}

	/**
	 * This method is used to set the isTurn boolean
	 * @param isTurn
	 */
	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}

	@Override
	/**
	 * This method allows to print every attributes of the Player object in the terminal.
	 * @return a String to print
	 */
	public String toString() {
		return("Player idNumber: " + getId() + "\n" + "CenterX: " + getCenterX() + "\n" + "CenterY: " + getCenterY() + "\n" + "Radius: " + getRadius() + "\n" +"*Current Tile : " + getCurrentTileId() + "\n" + "startTile"+ getIdStartTile()+".");
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
	public void addPlayerTextId(String textString, AnchorPane pane) {
		Text text = new Text(getCenterX() - getRadius(), getCenterY(), textString);
		text.setId("Player" + textString + "Text");
		pane.getChildren().add(text);
	}


	

	
}