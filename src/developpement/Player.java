package developpement;

import java.util.ArrayList;

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
	private String playerName;
	private int playerNumber;
	private ArrayList<String> idWinningTiles;
	private String idStartTiles;
	
	/**
	 * This method is the constructor of the Player class. It assigns a start tile and a list of winning tiles to the player depending on the player number.
	 * @param int playerNumber
	 * @param String playerName
	 * @param double radius
	 * @param Color color
	 */

	public Player(int playerNumber, String playerName, double radius, Color color) {
		super(-1000,-1000, radius);
		this.setPlayerNumber(playerNumber);
		String t = "Tile";
		switch(playerNumber) {
		case 1 : for (int i=1; i<=9; i++) {
			idWinningTiles.add(t+(72+i));
			setIdStartTiles("Tile5");
		}
		
		case 2 : for(int i=1; i<=9; i++) {
			idWinningTiles.add(t+i);
			setIdStartTiles("Tile77");
		}
		
		case 3 : for (int i=0; i<=8; i++) {
			idWinningTiles.add(t+(1+i*9));
			setIdStartTiles("Tile45");
		}
		
		case 4 : for (int i=1; i<=9; i++) {
			idWinningTiles.add(t+(i*9));
			setIdStartTiles("Tile37");
		}
		
		}
		super.setId(playerName);
		super.setFill(color);
	}
	
	/**
	 * Getter of playerName.
	 * @return String playerName
	 */
	protected String getPlayerName() {
		return(this.playerName);
	}
	
	/**
	 * Setter of playerName.
	 * @param String playerName.
	 */
	protected void setPlayerName(String playerName) {
		this.playerName = playerName;

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
	public String getIdStartTiles() {
		return idStartTiles;
	}

	/**
	 * This method is used to set the id of the starting tile of a player.
	 * @param String idStartTiles
	 */
	public void setIdStartTiles(String idStartTiles) {
		this.idStartTiles = idStartTiles;
	}

	public Player(String playerName, double radius, Color color) {
		super(0,0, radius);
		this.playerName = playerName;
		super.setId("Player " + playerName);
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
