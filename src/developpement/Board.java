package developpement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;
import java.util.Map.Entry;

import com.google.common.base.Splitter;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * The Board class represents the game board with everything on it : tiles, barriers and players. It also has methods to to change the visibility of every object, to move a player on the board, to place barriers and to check if a path to win exists for each player.
 * An adjacency list is added as an attribute to the class in order to be used in the methods that search for a path.
 * @author Cardenas D, Ruellan B, Machnik A, Johnson A, Guenneau R
 */

public class Board extends Region {	
	private HashMap<String, ArrayList<String>> adjacencyList;
	private String hoverTileId = "";
	private Player player1;
	private Player player2;
	private Player player3;
	private Player player4;
	
	private int numberOfPlayers = 0; 

	private int numberTotalBarriers = 0;

	private boolean needSecondBarrierHorizontal = false ;
	private boolean needSecondBarrierVertical = false ;

	private int temporaryBarrierHorizontalNumber = 0;
	private int temporaryBarrierVerticalNumber = 0;

	private String temporaryBarrierId = "";

	//variable intermediaire qui sert à stocker tous les joueurs, tableau de joueurs
	private final Player[] ArrayConstantOfInitialPlayer = {player1=new Player("Player 1", "Tile 5", true),player2=new Player("Player 2", "Tile 77", true),player3=new Player("Player 3", "Tile 45", true),player4=new Player("Player 4", "Tile 37", true)};
	//tableau de joueurs actif dans la partie
	private ArrayList<Player> listPlayer = new ArrayList<Player>();
	
	/**
	 * This method is used to restart a game 
	 * @param nbPlayer
	 */
		public void resetBoard(ChoiceBox<String> nbPlayer) {
			setNumberOfPlayers(Integer.parseInt(nbPlayer.getValue()));

			listPlayer = new ArrayList<Player>();
			hideAllBarrier();
			for(int i = 0; i<this.ArrayConstantOfInitialPlayer.length;i++) {
				removePlayerTile(ArrayConstantOfInitialPlayer[i]);
				listPlayer.add(ArrayConstantOfInitialPlayer[i]);
				removePlayerTile(listPlayer.get(i));
			}

			for(int i = 0; i < getNumberOfPlayers(); i++) {
				if(i == 0) {
					ArrayConstantOfInitialPlayer[i].setTurn(true);
				}
				else {
					ArrayConstantOfInitialPlayer[i].setTurn(false);
				}	
				ArrayConstantOfInitialPlayer[i].setCurrentTileId(ArrayConstantOfInitialPlayer[i].getIdStartTile());
				addPlayerTile(Integer.parseInt(listPlayer.get(i).getIdStartTile().substring(5)), listPlayer.get(i));
			}
		}		
		
		
		public Board(int initialX, int initialY, int tileRowsQuantity, int tileColumnsQuantity, Player player1, Player player2, Player player3, Player player4) {
			
			// Initialization of this instance attributes.
			// We need in total (tileColumnsQuantity*2 + 1) columns and (tileRowsQuantity*2 + 1) rows to fit the barriers.
			final int columnsTotalQuantity = tileColumnsQuantity*2 + 1;
			final int rowsTotalQuantity = tileRowsQuantity*2 + 1;

			// Dimensions of the barriers to build.
			final double barrierHorizontalWidth = 70;
			final double barrierHorizontalHeight = 10;

			final double barrierVerticalWidth = barrierHorizontalHeight;
			final double barrierVerticalHeight = barrierHorizontalWidth;

			// Dimensions of the tiles to build.
			final double tileWidth = barrierHorizontalWidth;

			// Useful accumulators for names or loops.
			int accBarrierHorizontalId = 1;
			int accBarrierVerticalId = 1;
			int accTileId = 1;

			// Useful variables to change the color of each elements type at once.
			Color cornerColor = Color.RED;
			Color barrierHorizontalColor = Color.RED;
			Color barrierVerticalColor = Color.RED;
			Color temporaryBarrierColor = Color.YELLOW;
			Color textColor = Color.BLACK;
			Color tileColor = Color.LIGHTGRAY;

		// Initialization of the board grid.
			// Creation of the Pane.
			GridPane boardGrid = new GridPane();

			// Set Id of boardPane.
			boardGrid.setId("boardGrid");

			// Align elements in the center of the grid.
			boardGrid.setAlignment(Pos.CENTER);

			// Set the position of the top-left corner of the board grid.
			boardGrid.setLayoutX(initialX);
			boardGrid.setLayoutY(initialY);

			// Set the columns of the grid.
			// Create all the columns needed.
			for(int columnNumber = 0; columnNumber < columnsTotalQuantity; columnNumber++) {
				// If even column :
				if(columnNumber % 2 == 0) {
					// Create a column and make it fit the width of vertical barriers.
					ColumnConstraints column = new ColumnConstraints(barrierVerticalWidth);

					// Add the column to the grid.
					boardGrid.getColumnConstraints().add(column);
				}

				// If odd column :
				else {
					// Create a column and make it fit the width of horizontal barriers.
					ColumnConstraints column = new ColumnConstraints(barrierHorizontalWidth);

					// Add the column to the grid.
					boardGrid.getColumnConstraints().add(column);
				}
			}

			// Set the rows of the grid.
			// Create all the rows needed.
			for(int rowNumber = 0; rowNumber < rowsTotalQuantity; rowNumber++) {
				// If even row :
				if(rowNumber%2 == 0) {
					// Create a row and make it fit the height of horizontal barriers.
					RowConstraints row = new RowConstraints(barrierHorizontalHeight);

					// Add the row to the grid.
					boardGrid.getRowConstraints().add(row);
				}
				// If odd row :
				else {
					// Create a row and make it fit the height of vertical barriers.
					RowConstraints row = new RowConstraints(barrierVerticalHeight);

					// Add the column to the grid.
					boardGrid.getRowConstraints().add(row);
				}
			}

			// Initialization and addition of barriers, corners and tiles.
			// We have to fill every row of the grid :
			for(int rowNumber = 0; rowNumber < rowsTotalQuantity; rowNumber++) {

				// We have to fill every column of the grid :
				for(int columnNumber = 0; columnNumber < columnsTotalQuantity; columnNumber++) {

					// If even row :
					if(rowNumber % 2 == 0) {

						// If even column :
						if(columnNumber % 2 == 0) {
							// Set a corner.
							// Create Rectangle "corner" which width is barrierVerticalWidth and height is barrierHorizontalHeight.
							Rectangle corner = new Rectangle(0,0,barrierVerticalWidth,barrierHorizontalHeight);

							// Set Id of the corner.
							corner.setId("Corner" + columnNumber + rowNumber);

							// Set corner color
							corner.setFill(cornerColor);

							// Add the corner to the grid.
							boardGrid.add(corner, columnNumber, rowNumber);
						}

						// If odd column and NOT on the border of the grid :
						else {
							// Set a horizontal barrier.
							// Create Barrier "barrierHorizontal" with the right dimensions.
							BarrierHorizontal barrierHorizontal = new BarrierHorizontal(accBarrierHorizontalId,barrierHorizontalWidth,barrierHorizontalHeight,barrierHorizontalColor,"","");   			

							// Add actions on events for barrierHorizontal.
							// If barrier hover is true :
							barrierHorizontal.hoverProperty().addListener((observable, oldValue, hoverBoolean) -> {
								if(barrierHorizontal.getOpacity() == 0) {
									if(hoverBoolean) {
										barrierHorizontal.setOpacity(1);
										barrierHorizontal.setFill(Color.GRAY);
									}
								}
								else {
									if(!hoverBoolean && barrierHorizontal.getFill() != barrierHorizontalColor && barrierHorizontal.getFill() != temporaryBarrierColor) {
										barrierHorizontal.setOpacity(0);
									}
								}
							});

							// Set an event when the barrier is clicked.    		
							barrierHorizontal.setOnMouseClicked(new EventHandler<MouseEvent>()
							{
								@Override
								// Set the action(s) to do.
								public void handle(MouseEvent event) {
									// If there is not 20 barriers already on the board :
									if(numberTotalBarriers < 20) {
										// If game has started
										if(numberOfPlayers >= 2) {
											// If a horizontal barrier was not clicked before :
											if(needSecondBarrierHorizontal == false && needSecondBarrierVertical == false) {
												// Get clicked barrier NUMBER.
												temporaryBarrierHorizontalNumber = Integer.parseInt(barrierHorizontal.getId().substring(19));
												// If the barrier is not active (red), temporarily selected (yellow) or hovered (gray) : 
												if(barrierHorizontal.getFill() != barrierHorizontalColor && barrierHorizontal.getFill() != temporaryBarrierColor && barrierHorizontal.getFill() != Color.WHITE) {
													// If the temporary barrier has an adjacent barrier at it's right AND at it's left:
													if(temporaryBarrierHorizontalNumber%9 - 1 != 0  && temporaryBarrierHorizontalNumber%9 != 0) {
														// Temporarily activate this barrier to allow dfs with the potential new adjacency list. 
														showBarrier(barrierHorizontal.getId(), temporaryBarrierColor);
														updateAdjacencyList(boardGrid);
														// Initialization of collections where booleans will we saved referring to the connectivity between a player current tile and one of his win condition tile.
														ArrayList<Boolean> resultAreConnected1 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected2 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected3 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected4 = new ArrayList<>();
														// Check if player 1 can win.
														for(String winningTile1 : player1.getIdWinningTiles()) {
															resultAreConnected1.add(areConnected(player1.getCurrentTileId(), winningTile1));
														}
														// Check if player 2 can win.
														for(String winningTile2 : player2.getIdWinningTiles()) {
															resultAreConnected2.add(areConnected(player2.getCurrentTileId(), winningTile2));
														}
														// When only 2 players are playing against, collections for players 3 and 4 must be neutral in the following conditions.
														resultAreConnected3.add(true);
														resultAreConnected4.add(true);
														// If 4 players are playing simultaneously :
														if(numberOfPlayers == 4) {
															// Initialize collections of booleans for players 3 and 4.
															resultAreConnected3 = new ArrayList<>();
															resultAreConnected4 = new ArrayList<>();
															// Check if player 3 can win.
															for(String winningTile3 : player3.getIdWinningTiles()) {
																resultAreConnected3.add(areConnected(player3.getCurrentTileId(), winningTile3));
															}
															// Check if player 4 can win.
															for(String winningTile4 : player4.getIdWinningTiles()) {
																resultAreConnected4.add(areConnected(player4.getCurrentTileId(), winningTile4));
															}
														}
														// If every player at least one win condition available :
														if(resultAreConnected1.contains(true) && resultAreConnected2.contains(true) && resultAreConnected3.contains(true) && resultAreConnected4.contains(true)) {
															// Get temporary horizontal barrier NUMBER from it's id for later.
															temporaryBarrierHorizontalNumber = Integer.parseInt(barrierHorizontal.getId().substring(19));
															// Save the id of the temporary selected barrier for later.
															temporaryBarrierId = barrierHorizontal.getId();
															// Notify that the player who just activated a barrier has to activate another one.
															needSecondBarrierHorizontal = true;
															// End event.
															return;
														}
														// If at least one cannot win :
														else {
															// Deactivate the temporary barrier.
															hideBarrier(barrierHorizontal.getId());
															// Remove changes from the adjacency list. 
															updateAdjacencyList(boardGrid);
															// Notify that the player can do any event.
															needSecondBarrierHorizontal = false;
															return;
														}
													}
												}
											}
											// If a horizontal barrier was clicked before :
											else if(needSecondBarrierHorizontal == true) {
												// Get horizontal barrier NUMBER from it's id.
												int barrierHorizontalNumber = Integer.parseInt(barrierHorizontal.getId().substring(19));
												// If clicked horizontal barrier is adjacent to the temporary barrier :
												if(barrierHorizontalNumber == temporaryBarrierHorizontalNumber+1 || barrierHorizontalNumber == temporaryBarrierHorizontalNumber-1) {
													// If the barrier is not active (red), temporarily selected (yellow) or hovered (gray) :
													if(barrierHorizontal.getFill() != barrierHorizontalColor && barrierHorizontal.getFill() != temporaryBarrierColor && barrierHorizontal.getFill() != Color.WHITE) {
														// Temporarily activate this barrier to allow dfs with the potential new adjacency list. 
														showBarrier(barrierHorizontal.getId(), temporaryBarrierColor);
														updateAdjacencyList(boardGrid);
														// Initialization of collections where booleans will we saved referring to the connectivity between a player current tile and one of his win condition tile.
														ArrayList<Boolean> resultAreConnected1 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected2 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected3 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected4 = new ArrayList<>();
														// Check if player 1 can win.
														for(String winningTile1 : player1.getIdWinningTiles()) {
															resultAreConnected1.add(areConnected(player1.getCurrentTileId(), winningTile1));
														}
														// Check if player 2 can win.
														for(String winningTile2 : player2.getIdWinningTiles()) {
															resultAreConnected2.add(areConnected(player2.getCurrentTileId(), winningTile2));
														}
														// When only 2 players are playing against, collections for players 3 and 4 must be neutral in the following conditions.
														resultAreConnected3.add(true);
														resultAreConnected4.add(true);
														// If 4 players are playing simultaneously :
														if(numberOfPlayers == 4) {
															// Initialize collections of booleans for players 3 and 4.
															resultAreConnected3 = new ArrayList<>();
															resultAreConnected4 = new ArrayList<>();

															// Check if player 3 can win.
															for(String winningTile3 : player3.getIdWinningTiles()) {
																resultAreConnected3.add(areConnected(player3.getCurrentTileId(), winningTile3));
															}
															// Check if player 4 can win.
															for(String winningTile4 : player4.getIdWinningTiles()) {
																resultAreConnected4.add(areConnected(player4.getCurrentTileId(), winningTile4));
															}
														}
														// If every player at least one win condition available :
														if(resultAreConnected1.contains(true) && resultAreConnected2.contains(true) && resultAreConnected3.contains(true) && resultAreConnected4.contains(true)) {
															// Activate both selected barriers.
															showBarrier(temporaryBarrierId, barrierHorizontalColor);
															showBarrier(barrierHorizontal.getId(), barrierHorizontalColor);
															// Increment total barrier on board number. MAX 20 !
															numberTotalBarriers++;
															// Pass the turn of the player that just played.
															changeTurn();
															// Notify that any event can be used.
															needSecondBarrierHorizontal = false;
														}
														else {
															// Remove both test barriers.
															hideBarrier(barrierHorizontal.getId());
															hideBarrier(temporaryBarrierId);
															// Remove changes from adjacency list.
															updateAdjacencyList(boardGrid);
															// Notify that any event can be used.
															needSecondBarrierHorizontal = false;
														}
													}
												}
											}
										}
									}
									else {
										System.out.println("Nbr max barrier");
									}
								}
							});

							// Add the barrierHorizontal to the right span.
							boardGrid.add(barrierHorizontal, columnNumber, rowNumber);

							// Create text on the barrierHorizontal.
							Label text = new Label("H" + Integer.toString(accBarrierHorizontalId));

							// Set Id of the text for barrierHorizontal.
							text.setId("Text " + barrierHorizontal.getId());

							// Set quick access to text's respective barrier.
							text.setLabelFor(barrierHorizontal);

							// Set text color.
							text.setTextFill(textColor);

							// Make mouse clicks pass through the text.
							text.setMouseTransparent(true);

							// Add the text to the right span.
							boardGrid.add(text, columnNumber, rowNumber);

							// Center the text in the span
							GridPane.setHalignment(text, HPos.CENTER);
							GridPane.setValignment(text, VPos.CENTER);

							// If the barrier is adjacent to 2 tiles :
							if(accBarrierHorizontalId > 9  && accBarrierHorizontalId < 82) {
								// Set id of the adjacent tiles in current barrier attributes.
								barrierHorizontal.setIdTile1("Tile "+Integer.toString(accBarrierHorizontalId-9));
								barrierHorizontal.setIdTile2("Tile "+Integer.toString(accBarrierHorizontalId));
							}

							// Increment accumulator to the next barrier number.
							accBarrierHorizontalId++;
						}
					}

					// If odd row :
					else {
						if(columnNumber % 2 == 0) {
							// Set a vertical barrier.
							// Create Barrier "barrierVertical" with the right dimensions.
							BarrierVertical barrierVertical = new BarrierVertical(accBarrierVerticalId, barrierVerticalWidth, barrierVerticalHeight, barrierVerticalColor,"","");

							// Add actions on events for barrierVertical.
							// If barrier hover is true :
							barrierVertical.hoverProperty().addListener((observable, oldValue, hoverBoolean) -> {
								if(barrierVertical.getOpacity() == 0) {
									if(hoverBoolean) {
										barrierVertical.setOpacity(1);
										barrierVertical.setFill(Color.GRAY);
									}
								}
								else {
									if((!hoverBoolean && barrierVertical.getFill() != temporaryBarrierColor && barrierVertical.getFill() != barrierVerticalColor)) {
										barrierVertical.setOpacity(0);
									}
								}
							});

							// Set an event when the barrier is clicked.
							barrierVertical.setOnMouseClicked(new EventHandler<MouseEvent>()
							{
								@Override
								// Set the action(s) to do.
								/**
								 * This method manages the differents possibilities that has a player
								 * @param event
								 */
								public void handle(MouseEvent event) {
									// If there is not 20 barriers already on the board :
									if(numberTotalBarriers < 20) {
										// If game has started
										if(numberOfPlayers >= 2) {
											// If a vertical barrier was not clicked before :
											if(needSecondBarrierVertical == false && needSecondBarrierHorizontal == false) {
												// Get clicked barrier NUMBER.
												temporaryBarrierVerticalNumber = Integer.parseInt(barrierVertical.getId().substring(17));
												// If the barrier is not active (red), temporarily selected (yellow) or hovered (gray) : 
												if(barrierVertical.getFill() != barrierVerticalColor && barrierVertical.getFill() != temporaryBarrierColor && barrierVertical.getFill() != Color.WHITE) {
													// If the temporary barrier has an adjacent barrier above AND under it:
													if(temporaryBarrierVerticalNumber + 10 < 91 && temporaryBarrierVerticalNumber - 10 > 0) {
														// Temporarily activate this barrier to allow dfs with the potential new adjacency list. 
														showBarrier(barrierVertical.getId(), temporaryBarrierColor);
														updateAdjacencyList(boardGrid);
														// Initialization of collections where booleans will we saved referring to the connectivity between a player current tile and one of his win condition tile.
														ArrayList<Boolean> resultAreConnected1 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected2 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected3 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected4 = new ArrayList<>();
														// Check if player 1 can win.
														for(String winningTile1 : player1.getIdWinningTiles()) {
															resultAreConnected1.add(areConnected(player1.getCurrentTileId(), winningTile1));
														}
														// Check if player 2 can win.
														for(String winningTile2 : player2.getIdWinningTiles()) {
															resultAreConnected2.add(areConnected(player2.getCurrentTileId(), winningTile2));
														}
														// When only 2 players are playing against, collections for players 3 and 4 must be neutral in the following conditions.
														resultAreConnected3.add(true);
														resultAreConnected4.add(true);
														// If 4 players are playing simultaneously :
														if(numberOfPlayers == 4) {
															// Initialize collections of booleans for players 3 and 4.
															resultAreConnected3 = new ArrayList<>();
															resultAreConnected4 = new ArrayList<>();
															// Check if player 3 can win.
															for(String winningTile3 : player3.getIdWinningTiles()) {
																resultAreConnected3.add(areConnected(player3.getCurrentTileId(), winningTile3));
															}
															// Check if player 4 can win.
															for(String winningTile4 : player4.getIdWinningTiles()) {
																resultAreConnected4.add(areConnected(player4.getCurrentTileId(), winningTile4));
															}
														}
														// If every player at least one win condition available :
														if(resultAreConnected1.contains(true) && resultAreConnected2.contains(true) && resultAreConnected3.contains(true) && resultAreConnected4.contains(true)) {
															// Get temporary vertical barrier NUMBER from it's id for later.
															temporaryBarrierVerticalNumber = Integer.parseInt(barrierVertical.getId().substring(17));
															// Save the id of the temporary selected barrier for later.
															temporaryBarrierId = barrierVertical.getId();
															// Notify that the player who just activated a barrier has to activate another one.
															needSecondBarrierVertical = true;
															// End event.
															return;
														}
														// If at least one cannot win :
														else {
															// Deactivate the temporary barrier.
															hideBarrier(barrierVertical.getId());
															// Remove changes from the adjacency list. 
															updateAdjacencyList(boardGrid);
															// Notify that the player can do any event.
															needSecondBarrierVertical = false;
															return;
														}
													}
												}
											}
											// If a vertical barrier was clicked before :
											else if(needSecondBarrierVertical == true) {
												// Get vertical barrier NUMBER from it's id.
												int barrierVerticalNumber = Integer.parseInt(barrierVertical.getId().substring(17));
												// If clicked vertical barrier is adjacent to the temporary barrier :
												if(barrierVerticalNumber == temporaryBarrierVerticalNumber+10 || barrierVerticalNumber == temporaryBarrierVerticalNumber-10) {
													// If the barrier is not active (red), temporarily selected (yellow) or hovered (gray) :
													if(barrierVertical.getFill() != barrierVerticalColor && barrierVertical.getFill() != temporaryBarrierColor && barrierVertical.getFill() != Color.WHITE) {
														// Temporarily activate this barrier to allow dfs with the potential new adjacency list. 
														showBarrier(barrierVertical.getId(), temporaryBarrierColor);
														updateAdjacencyList(boardGrid);
														// Initialization of collections where booleans will we saved referring to the connectivity between a player current tile and one of his win condition tile.
														ArrayList<Boolean> resultAreConnected1 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected2 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected3 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected4 = new ArrayList<>();
														// Check if player 1 can win.
														for(String winningTile1 : player1.getIdWinningTiles()) {
															resultAreConnected1.add(areConnected(player1.getCurrentTileId(), winningTile1));
														}
														// Check if player 2 can win.
														for(String winningTile2 : player2.getIdWinningTiles()) {
															resultAreConnected2.add(areConnected(player2.getCurrentTileId(), winningTile2));
														}
														// When only 2 players are playing against, collections for players 3 and 4 must be neutral in the following conditions.
														resultAreConnected3.add(true);
														resultAreConnected4.add(true);
														// If 4 players are playing simultaneously :
														if(numberOfPlayers == 4) {
															// Initialize collections of booleans for players 3 and 4.
															resultAreConnected3 = new ArrayList<>();
															resultAreConnected4 = new ArrayList<>();

															// Check if player 3 can win.
															for(String winningTile3 : player3.getIdWinningTiles()) {
																resultAreConnected3.add(areConnected(player3.getCurrentTileId(), winningTile3));
															}
															// Check if player 4 can win.
															for(String winningTile4 : player4.getIdWinningTiles()) {
																resultAreConnected4.add(areConnected(player4.getCurrentTileId(), winningTile4));
															}
														}
														// If every player at least one win condition available :
														if(resultAreConnected1.contains(true) && resultAreConnected2.contains(true) && resultAreConnected3.contains(true) && resultAreConnected4.contains(true)) {
															// Activate both selected barriers.
															showBarrier(temporaryBarrierId, barrierVerticalColor);
															showBarrier(barrierVertical.getId(), barrierVerticalColor);
															// Increment total barrier on board number. MAX 20 !
															numberTotalBarriers++;
															// Pass the turn of the player that just played.
															changeTurn();
															// Notify that any event can be used.
															needSecondBarrierVertical = false;
														}
														else {
															// Remove both test barriers.
															hideBarrier(barrierVertical.getId());
															hideBarrier(temporaryBarrierId);
															// Remove changes from adjacency list.
															updateAdjacencyList(boardGrid);
															// Notify that any event can be used.
															needSecondBarrierVertical = false;
														}
													}
												}
											}
										}
									}
									else {
										System.out.println("Nbr max barrier");
									}
								}
							});

							// Add the barrierVertical to the right span.
							boardGrid.add(barrierVertical, columnNumber, rowNumber);

							// Create text on the barrierHorizontal.
							Label text = new Label("V" + Integer.toString(accBarrierVerticalId));

							// Set Id for the text of barrierVertical.
							text.setId("Text " + barrierVertical.getId());

							// Set quick access to text's respective barrier.
							text.setLabelFor(barrierVertical);

							// Set text color.
							text.setTextFill(textColor);

							// Make mouse clicks pass through the text.
							text.setMouseTransparent(true);

							// Add the text to the right span.
							boardGrid.add(text, columnNumber, rowNumber);

							// Center the text in the span
							GridPane.setHalignment(text, HPos.CENTER);
							GridPane.setValignment(text, VPos.CENTER);

							text.setFont(new Font(barrierHorizontalHeight-4));

							// Rotate the text.
							text.setRotate(90);

							// If the barrier is adjacent to 2 tiles :
							if(accBarrierVerticalId % 10 != 0  && (accBarrierVerticalId-1) % 10 != 0) {
								// Set id of the adjacent tiles in current barrier attributes.
								barrierVertical.setIdTile1("Tile "+Integer.toString(accBarrierVerticalId-1-((int) accBarrierVerticalId/10)));
								barrierVertical.setIdTile2("Tile "+Integer.toString(accBarrierVerticalId-((int) accBarrierVerticalId/10)));
							}

							// Increment accumulator to the next barrier number.
							accBarrierVerticalId++;
						}
						else {
							// Set a tile.
							// Create Tile "tile" with the right dimensions.
							Tile tile = new Tile(accTileId, tileWidth, tileColor);

							// Add actions on events for tile.
							// If barrier hover is true :
							tile.hoverProperty().addListener((observable, oldValue, hoverBoolean) -> {
								hoverTileId = tile.getId();
								//System.out.println("Hovered tile: " + this.hoverTileId);

								// Color tile if hovered.
								if(hoverBoolean) {
									tile.setOpacity(1);
									tile.setFill(Color.CHOCOLATE);
								}

								if(!hoverBoolean && tile.getFill() != barrierHorizontalColor) {
									tile.setFill(tileColor);
								}
							});


							// Set an event when the tile is clicked.    		
							tile.setOnMouseClicked(new EventHandler<MouseEvent>()
							{	
								@Override
								// Set the action(s) to do.
								public void handle(MouseEvent event) {

									// Get hovered tile id and number.
									String tileNumberString;
									int tileNumber;

									// If tile id matches "Tile X" form, ("Tile 1", ..., "Tile 9") :
									if(hoverTileId.length() == 6) {
										tileNumberString = hoverTileId.substring(hoverTileId.length()-1);
										tileNumber = Integer.parseInt(tileNumberString);
									}
									// If tile id does not match "Tile X" form :
									else {
										tileNumberString = hoverTileId.substring(hoverTileId.length()-2);
										tileNumber = Integer.parseInt(tileNumberString);
									}

									// If remove is failed it means player was not exists in board.
									// Add to update position.

									if(needSecondBarrierHorizontal == false && needSecondBarrierVertical == false) {
										for(Player playerTurn : listPlayer) {
											if(playerTurn.isTurn()) {
												playTurn(playerTurn,tileNumber);
											}
										}
									}	
								}
							});

							// Add the tile to the right span.
							boardGrid.add(tile, columnNumber, rowNumber);

							// Create text on the tile.
							Label text = new Label("Tile " + Integer.toString(accTileId));

							// Set Id of the tile.
							text.setId("Text " + tile.getId());

							// Set quick access to text's respective barrier.
							text.setLabelFor(tile);

							// Set text color.
							text.setTextFill(textColor);

							// Make mouse clicks pass through the text.
							text.setMouseTransparent(true);

							// Add the text to the right span.
							boardGrid.add(text, columnNumber, rowNumber);

							// Center the text in the span
							GridPane.setHalignment(text, HPos.CENTER);
							GridPane.setValignment(text, VPos.CENTER);

							// Increment accumulator to the next barrier number.
							accTileId++;
						}
					}  
				}
			}
			// Add boardGrid to Region.
			this.getChildren().add(boardGrid);
			setNumberOfPlayers(2);

			listPlayer = new ArrayList<Player>();
			
			hideAllBarrier();
			for(int i = 0; i<this.ArrayConstantOfInitialPlayer.length;i++) {
				removePlayerTile(ArrayConstantOfInitialPlayer[i]);
				listPlayer.add(ArrayConstantOfInitialPlayer[i]);
				removePlayerTile(listPlayer.get(i));
			}

			for(int i = 0; i < getNumberOfPlayers(); i++) {
				if(i == 0) {
					ArrayConstantOfInitialPlayer[i].setTurn(true);
				}
				else {
					ArrayConstantOfInitialPlayer[i].setTurn(false);
				}	
				ArrayConstantOfInitialPlayer[i].setCurrentTileId(ArrayConstantOfInitialPlayer[i].getIdStartTile());
				addPlayerTile(Integer.parseInt(listPlayer.get(i).getIdStartTile().substring(5)), listPlayer.get(i));
			}
			// Set barriers visibility as false.
			hideAllBarrier();
		}

		
		
public Board(int initialX, int initialY, int tileRowsQuantity, int tileColumnsQuantity, Player player1, Player player2, Player player3, Player player4, boolean a) {
			
			// Initialization of this instance attributes.
			// We need in total (tileColumnsQuantity*2 + 1) columns and (tileRowsQuantity*2 + 1) rows to fit the barriers.
			final int columnsTotalQuantity = tileColumnsQuantity*2 + 1;
			final int rowsTotalQuantity = tileRowsQuantity*2 + 1;

			// Dimensions of the barriers to build.
			final double barrierHorizontalWidth = 70;
			final double barrierHorizontalHeight = 10;

			final double barrierVerticalWidth = barrierHorizontalHeight;
			final double barrierVerticalHeight = barrierHorizontalWidth;

			// Dimensions of the tiles to build.
			final double tileWidth = barrierHorizontalWidth;

			// Useful accumulators for names or loops.
			int accBarrierHorizontalId = 1;
			int accBarrierVerticalId = 1;
			int accTileId = 1;

			// Useful variables to change the color of each elements type at once.
			Color cornerColor = Color.RED;
			Color barrierHorizontalColor = Color.RED;
			Color barrierVerticalColor = Color.RED;
			Color temporaryBarrierColor = Color.YELLOW;
			Color textColor = Color.BLACK;
			Color tileColor = Color.LIGHTGRAY;

		// Initialization of the board grid.
			// Creation of the Pane.
			GridPane boardGrid = new GridPane();

			// Set Id of boardPane.
			boardGrid.setId("boardGrid");

			// Align elements in the center of the grid.
			boardGrid.setAlignment(Pos.CENTER);

			// Set the position of the top-left corner of the board grid.
			boardGrid.setLayoutX(initialX);
			boardGrid.setLayoutY(initialY);

			// Set the columns of the grid.
			// Create all the columns needed.
			for(int columnNumber = 0; columnNumber < columnsTotalQuantity; columnNumber++) {
				// If even column :
				if(columnNumber % 2 == 0) {
					// Create a column and make it fit the width of vertical barriers.
					ColumnConstraints column = new ColumnConstraints(barrierVerticalWidth);

					// Add the column to the grid.
					boardGrid.getColumnConstraints().add(column);
				}

				// If odd column :
				else {
					// Create a column and make it fit the width of horizontal barriers.
					ColumnConstraints column = new ColumnConstraints(barrierHorizontalWidth);

					// Add the column to the grid.
					boardGrid.getColumnConstraints().add(column);
				}
			}

			// Set the rows of the grid.
			// Create all the rows needed.
			for(int rowNumber = 0; rowNumber < rowsTotalQuantity; rowNumber++) {
				// If even row :
				if(rowNumber%2 == 0) {
					// Create a row and make it fit the height of horizontal barriers.
					RowConstraints row = new RowConstraints(barrierHorizontalHeight);

					// Add the row to the grid.
					boardGrid.getRowConstraints().add(row);
				}
				// If odd row :
				else {
					// Create a row and make it fit the height of vertical barriers.
					RowConstraints row = new RowConstraints(barrierVerticalHeight);

					// Add the column to the grid.
					boardGrid.getRowConstraints().add(row);
				}
			}

			// Initialization and addition of barriers, corners and tiles.
			// We have to fill every row of the grid :
			for(int rowNumber = 0; rowNumber < rowsTotalQuantity; rowNumber++) {

				// We have to fill every column of the grid :
				for(int columnNumber = 0; columnNumber < columnsTotalQuantity; columnNumber++) {

					// If even row :
					if(rowNumber % 2 == 0) {

						// If even column :
						if(columnNumber % 2 == 0) {
							// Set a corner.
							// Create Rectangle "corner" which width is barrierVerticalWidth and height is barrierHorizontalHeight.
							Rectangle corner = new Rectangle(0,0,barrierVerticalWidth,barrierHorizontalHeight);

							// Set Id of the corner.
							corner.setId("Corner" + columnNumber + rowNumber);

							// Set corner color
							corner.setFill(cornerColor);

							// Add the corner to the grid.
							boardGrid.add(corner, columnNumber, rowNumber);
						}

						// If odd column and NOT on the border of the grid :
						else {
							// Set a horizontal barrier.
							// Create Barrier "barrierHorizontal" with the right dimensions.
							BarrierHorizontal barrierHorizontal = new BarrierHorizontal(accBarrierHorizontalId,barrierHorizontalWidth,barrierHorizontalHeight,barrierHorizontalColor,"","");   			

							// Add actions on events for barrierHorizontal.
							// If barrier hover is true :
							barrierHorizontal.hoverProperty().addListener((observable, oldValue, hoverBoolean) -> {
								if(barrierHorizontal.getOpacity() == 0) {
									if(hoverBoolean) {
										barrierHorizontal.setOpacity(1);
										barrierHorizontal.setFill(Color.GRAY);
									}
								}
								else {
									if(!hoverBoolean && barrierHorizontal.getFill() != barrierHorizontalColor && barrierHorizontal.getFill() != temporaryBarrierColor) {
										barrierHorizontal.setOpacity(0);
									}
								}
							});

							// Set an event when the barrier is clicked.    		
							barrierHorizontal.setOnMouseClicked(new EventHandler<MouseEvent>()
							{
								@Override
								// Set the action(s) to do.
								public void handle(MouseEvent event) {
									// If there is not 20 barriers already on the board :
									if(numberTotalBarriers < 20) {
										// If game has started
										if(numberOfPlayers >= 2) {
											// If a horizontal barrier was not clicked before :
											if(needSecondBarrierHorizontal == false && needSecondBarrierVertical == false) {
												// Get clicked barrier NUMBER.
												temporaryBarrierHorizontalNumber = Integer.parseInt(barrierHorizontal.getId().substring(19));
												// If the barrier is not active (red), temporarily selected (yellow) or hovered (gray) : 
												if(barrierHorizontal.getFill() != barrierHorizontalColor && barrierHorizontal.getFill() != temporaryBarrierColor && barrierHorizontal.getFill() != Color.WHITE) {
													// If the temporary barrier has an adjacent barrier at it's right AND at it's left:
													if(temporaryBarrierHorizontalNumber%9 - 1 != 0  && temporaryBarrierHorizontalNumber%9 != 0) {
														// Temporarily activate this barrier to allow dfs with the potential new adjacency list. 
														showBarrier(barrierHorizontal.getId(), temporaryBarrierColor);
														updateAdjacencyList(boardGrid);
														// Initialization of collections where booleans will we saved referring to the connectivity between a player current tile and one of his win condition tile.
														ArrayList<Boolean> resultAreConnected1 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected2 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected3 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected4 = new ArrayList<>();
														// Check if player 1 can win.
														for(String winningTile1 : player1.getIdWinningTiles()) {
															resultAreConnected1.add(areConnected(player1.getCurrentTileId(), winningTile1));
														}
														// Check if player 2 can win.
														for(String winningTile2 : player2.getIdWinningTiles()) {
															resultAreConnected2.add(areConnected(player2.getCurrentTileId(), winningTile2));
														}
														// When only 2 players are playing against, collections for players 3 and 4 must be neutral in the following conditions.
														resultAreConnected3.add(true);
														resultAreConnected4.add(true);
														// If 4 players are playing simultaneously :
														if(numberOfPlayers == 4) {
															// Initialize collections of booleans for players 3 and 4.
															resultAreConnected3 = new ArrayList<>();
															resultAreConnected4 = new ArrayList<>();
															// Check if player 3 can win.
															for(String winningTile3 : player3.getIdWinningTiles()) {
																resultAreConnected3.add(areConnected(player3.getCurrentTileId(), winningTile3));
															}
															// Check if player 4 can win.
															for(String winningTile4 : player4.getIdWinningTiles()) {
																resultAreConnected4.add(areConnected(player4.getCurrentTileId(), winningTile4));
															}
														}
														// If every player at least one win condition available :
														if(resultAreConnected1.contains(true) && resultAreConnected2.contains(true) && resultAreConnected3.contains(true) && resultAreConnected4.contains(true)) {
															// Get temporary horizontal barrier NUMBER from it's id for later.
															temporaryBarrierHorizontalNumber = Integer.parseInt(barrierHorizontal.getId().substring(19));
															// Save the id of the temporary selected barrier for later.
															temporaryBarrierId = barrierHorizontal.getId();
															// Notify that the player who just activated a barrier has to activate another one.
															needSecondBarrierHorizontal = true;
															// End event.
															return;
														}
														// If at least one cannot win :
														else {
															// Deactivate the temporary barrier.
															hideBarrier(barrierHorizontal.getId());
															// Remove changes from the adjacency list. 
															updateAdjacencyList(boardGrid);
															// Notify that the player can do any event.
															needSecondBarrierHorizontal = false;
															return;
														}
													}
												}
											}
											// If a horizontal barrier was clicked before :
											else if(needSecondBarrierHorizontal == true) {
												// Get horizontal barrier NUMBER from it's id.
												int barrierHorizontalNumber = Integer.parseInt(barrierHorizontal.getId().substring(19));
												// If clicked horizontal barrier is adjacent to the temporary barrier :
												if(barrierHorizontalNumber == temporaryBarrierHorizontalNumber+1 || barrierHorizontalNumber == temporaryBarrierHorizontalNumber-1) {
													// If the barrier is not active (red), temporarily selected (yellow) or hovered (gray) :
													if(barrierHorizontal.getFill() != barrierHorizontalColor && barrierHorizontal.getFill() != temporaryBarrierColor && barrierHorizontal.getFill() != Color.WHITE) {
														// Temporarily activate this barrier to allow dfs with the potential new adjacency list. 
														showBarrier(barrierHorizontal.getId(), temporaryBarrierColor);
														updateAdjacencyList(boardGrid);
														// Initialization of collections where booleans will we saved referring to the connectivity between a player current tile and one of his win condition tile.
														ArrayList<Boolean> resultAreConnected1 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected2 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected3 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected4 = new ArrayList<>();
														// Check if player 1 can win.
														for(String winningTile1 : player1.getIdWinningTiles()) {
															resultAreConnected1.add(areConnected(player1.getCurrentTileId(), winningTile1));
														}
														// Check if player 2 can win.
														for(String winningTile2 : player2.getIdWinningTiles()) {
															resultAreConnected2.add(areConnected(player2.getCurrentTileId(), winningTile2));
														}
														// When only 2 players are playing against, collections for players 3 and 4 must be neutral in the following conditions.
														resultAreConnected3.add(true);
														resultAreConnected4.add(true);
														// If 4 players are playing simultaneously :
														if(numberOfPlayers == 4) {
															// Initialize collections of booleans for players 3 and 4.
															resultAreConnected3 = new ArrayList<>();
															resultAreConnected4 = new ArrayList<>();

															// Check if player 3 can win.
															for(String winningTile3 : player3.getIdWinningTiles()) {
																resultAreConnected3.add(areConnected(player3.getCurrentTileId(), winningTile3));
															}
															// Check if player 4 can win.
															for(String winningTile4 : player4.getIdWinningTiles()) {
																resultAreConnected4.add(areConnected(player4.getCurrentTileId(), winningTile4));
															}
														}
														// If every player at least one win condition available :
														if(resultAreConnected1.contains(true) && resultAreConnected2.contains(true) && resultAreConnected3.contains(true) && resultAreConnected4.contains(true)) {
															// Activate both selected barriers.
															showBarrier(temporaryBarrierId, barrierHorizontalColor);
															showBarrier(barrierHorizontal.getId(), barrierHorizontalColor);
															// Increment total barrier on board number. MAX 20 !
															numberTotalBarriers++;
															// Pass the turn of the player that just played.
															changeTurn();
															// Notify that any event can be used.
															needSecondBarrierHorizontal = false;
														}
														else {
															// Remove both test barriers.
															hideBarrier(barrierHorizontal.getId());
															hideBarrier(temporaryBarrierId);
															// Remove changes from adjacency list.
															updateAdjacencyList(boardGrid);
															// Notify that any event can be used.
															needSecondBarrierHorizontal = false;
														}
													}
												}
											}
										}
									}
									else {
										System.out.println("Nbr max barrier");
									}
								}
							});

							// Add the barrierHorizontal to the right span.
							boardGrid.add(barrierHorizontal, columnNumber, rowNumber);

							// Create text on the barrierHorizontal.
							Label text = new Label("H" + Integer.toString(accBarrierHorizontalId));

							// Set Id of the text for barrierHorizontal.
							text.setId("Text " + barrierHorizontal.getId());

							// Set quick access to text's respective barrier.
							text.setLabelFor(barrierHorizontal);

							// Set text color.
							text.setTextFill(textColor);

							// Make mouse clicks pass through the text.
							text.setMouseTransparent(true);

							// Add the text to the right span.
							boardGrid.add(text, columnNumber, rowNumber);

							// Center the text in the span
							GridPane.setHalignment(text, HPos.CENTER);
							GridPane.setValignment(text, VPos.CENTER);

							// If the barrier is adjacent to 2 tiles :
							if(accBarrierHorizontalId > 9  && accBarrierHorizontalId < 82) {
								// Set id of the adjacent tiles in current barrier attributes.
								barrierHorizontal.setIdTile1("Tile "+Integer.toString(accBarrierHorizontalId-9));
								barrierHorizontal.setIdTile2("Tile "+Integer.toString(accBarrierHorizontalId));
							}

							// Increment accumulator to the next barrier number.
							accBarrierHorizontalId++;
						}
					}

					// If odd row :
					else {
						if(columnNumber % 2 == 0) {
							// Set a vertical barrier.
							// Create Barrier "barrierVertical" with the right dimensions.
							BarrierVertical barrierVertical = new BarrierVertical(accBarrierVerticalId, barrierVerticalWidth, barrierVerticalHeight, barrierVerticalColor,"","");

							// Add actions on events for barrierVertical.
							// If barrier hover is true :
							barrierVertical.hoverProperty().addListener((observable, oldValue, hoverBoolean) -> {
								if(barrierVertical.getOpacity() == 0) {
									if(hoverBoolean) {
										barrierVertical.setOpacity(1);
										barrierVertical.setFill(Color.GRAY);
									}
								}
								else {
									if((!hoverBoolean && barrierVertical.getFill() != temporaryBarrierColor && barrierVertical.getFill() != barrierVerticalColor)) {
										barrierVertical.setOpacity(0);
									}
								}
							});

							// Set an event when the barrier is clicked.
							barrierVertical.setOnMouseClicked(new EventHandler<MouseEvent>()
							{
								@Override
								// Set the action(s) to do.
								/**
								 * This method manages the differents possibilities that has a player
								 * @param event
								 */
								public void handle(MouseEvent event) {
									// If there is not 20 barriers already on the board :
									if(numberTotalBarriers < 20) {
										// If game has started
										if(numberOfPlayers >= 2) {
											// If a vertical barrier was not clicked before :
											if(needSecondBarrierVertical == false && needSecondBarrierHorizontal == false) {
												// Get clicked barrier NUMBER.
												temporaryBarrierVerticalNumber = Integer.parseInt(barrierVertical.getId().substring(17));
												// If the barrier is not active (red), temporarily selected (yellow) or hovered (gray) : 
												if(barrierVertical.getFill() != barrierVerticalColor && barrierVertical.getFill() != temporaryBarrierColor && barrierVertical.getFill() != Color.WHITE) {
													// If the temporary barrier has an adjacent barrier above AND under it:
													if(temporaryBarrierVerticalNumber + 10 < 91 && temporaryBarrierVerticalNumber - 10 > 0) {
														// Temporarily activate this barrier to allow dfs with the potential new adjacency list. 
														showBarrier(barrierVertical.getId(), temporaryBarrierColor);
														updateAdjacencyList(boardGrid);
														// Initialization of collections where booleans will we saved referring to the connectivity between a player current tile and one of his win condition tile.
														ArrayList<Boolean> resultAreConnected1 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected2 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected3 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected4 = new ArrayList<>();
														// Check if player 1 can win.
														for(String winningTile1 : player1.getIdWinningTiles()) {
															resultAreConnected1.add(areConnected(player1.getCurrentTileId(), winningTile1));
														}
														// Check if player 2 can win.
														for(String winningTile2 : player2.getIdWinningTiles()) {
															resultAreConnected2.add(areConnected(player2.getCurrentTileId(), winningTile2));
														}
														// When only 2 players are playing against, collections for players 3 and 4 must be neutral in the following conditions.
														resultAreConnected3.add(true);
														resultAreConnected4.add(true);
														// If 4 players are playing simultaneously :
														if(numberOfPlayers == 4) {
															// Initialize collections of booleans for players 3 and 4.
															resultAreConnected3 = new ArrayList<>();
															resultAreConnected4 = new ArrayList<>();
															// Check if player 3 can win.
															for(String winningTile3 : player3.getIdWinningTiles()) {
																resultAreConnected3.add(areConnected(player3.getCurrentTileId(), winningTile3));
															}
															// Check if player 4 can win.
															for(String winningTile4 : player4.getIdWinningTiles()) {
																resultAreConnected4.add(areConnected(player4.getCurrentTileId(), winningTile4));
															}
														}
														// If every player at least one win condition available :
														if(resultAreConnected1.contains(true) && resultAreConnected2.contains(true) && resultAreConnected3.contains(true) && resultAreConnected4.contains(true)) {
															// Get temporary vertical barrier NUMBER from it's id for later.
															temporaryBarrierVerticalNumber = Integer.parseInt(barrierVertical.getId().substring(17));
															// Save the id of the temporary selected barrier for later.
															temporaryBarrierId = barrierVertical.getId();
															// Notify that the player who just activated a barrier has to activate another one.
															needSecondBarrierVertical = true;
															// End event.
															return;
														}
														// If at least one cannot win :
														else {
															// Deactivate the temporary barrier.
															hideBarrier(barrierVertical.getId());
															// Remove changes from the adjacency list. 
															updateAdjacencyList(boardGrid);
															// Notify that the player can do any event.
															needSecondBarrierVertical = false;
															return;
														}
													}
												}
											}
											// If a vertical barrier was clicked before :
											else if(needSecondBarrierVertical == true) {
												// Get vertical barrier NUMBER from it's id.
												int barrierVerticalNumber = Integer.parseInt(barrierVertical.getId().substring(17));
												// If clicked vertical barrier is adjacent to the temporary barrier :
												if(barrierVerticalNumber == temporaryBarrierVerticalNumber+10 || barrierVerticalNumber == temporaryBarrierVerticalNumber-10) {
													// If the barrier is not active (red), temporarily selected (yellow) or hovered (gray) :
													if(barrierVertical.getFill() != barrierVerticalColor && barrierVertical.getFill() != temporaryBarrierColor && barrierVertical.getFill() != Color.WHITE) {
														// Temporarily activate this barrier to allow dfs with the potential new adjacency list. 
														showBarrier(barrierVertical.getId(), temporaryBarrierColor);
														updateAdjacencyList(boardGrid);
														// Initialization of collections where booleans will we saved referring to the connectivity between a player current tile and one of his win condition tile.
														ArrayList<Boolean> resultAreConnected1 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected2 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected3 = new ArrayList<>();
														ArrayList<Boolean> resultAreConnected4 = new ArrayList<>();
														// Check if player 1 can win.
														for(String winningTile1 : player1.getIdWinningTiles()) {
															resultAreConnected1.add(areConnected(player1.getCurrentTileId(), winningTile1));
														}
														// Check if player 2 can win.
														for(String winningTile2 : player2.getIdWinningTiles()) {
															resultAreConnected2.add(areConnected(player2.getCurrentTileId(), winningTile2));
														}
														// When only 2 players are playing against, collections for players 3 and 4 must be neutral in the following conditions.
														resultAreConnected3.add(true);
														resultAreConnected4.add(true);
														// If 4 players are playing simultaneously :
														if(numberOfPlayers == 4) {
															// Initialize collections of booleans for players 3 and 4.
															resultAreConnected3 = new ArrayList<>();
															resultAreConnected4 = new ArrayList<>();

															// Check if player 3 can win.
															for(String winningTile3 : player3.getIdWinningTiles()) {
																resultAreConnected3.add(areConnected(player3.getCurrentTileId(), winningTile3));
															}
															// Check if player 4 can win.
															for(String winningTile4 : player4.getIdWinningTiles()) {
																resultAreConnected4.add(areConnected(player4.getCurrentTileId(), winningTile4));
															}
														}
														// If every player at least one win condition available :
														if(resultAreConnected1.contains(true) && resultAreConnected2.contains(true) && resultAreConnected3.contains(true) && resultAreConnected4.contains(true)) {
															// Activate both selected barriers.
															showBarrier(temporaryBarrierId, barrierVerticalColor);
															showBarrier(barrierVertical.getId(), barrierVerticalColor);
															// Increment total barrier on board number. MAX 20 !
															numberTotalBarriers++;
															// Pass the turn of the player that just played.
															changeTurn();
															// Notify that any event can be used.
															needSecondBarrierVertical = false;
														}
														else {
															// Remove both test barriers.
															hideBarrier(barrierVertical.getId());
															hideBarrier(temporaryBarrierId);
															// Remove changes from adjacency list.
															updateAdjacencyList(boardGrid);
															// Notify that any event can be used.
															needSecondBarrierVertical = false;
														}
													}
												}
											}
										}
									}
									else {
										System.out.println("Nbr max barrier");
									}
								}
							});

							// Add the barrierVertical to the right span.
							boardGrid.add(barrierVertical, columnNumber, rowNumber);

							// Create text on the barrierHorizontal.
							Label text = new Label("V" + Integer.toString(accBarrierVerticalId));

							// Set Id for the text of barrierVertical.
							text.setId("Text " + barrierVertical.getId());

							// Set quick access to text's respective barrier.
							text.setLabelFor(barrierVertical);

							// Set text color.
							text.setTextFill(textColor);

							// Make mouse clicks pass through the text.
							text.setMouseTransparent(true);

							// Add the text to the right span.
							boardGrid.add(text, columnNumber, rowNumber);

							// Center the text in the span
							GridPane.setHalignment(text, HPos.CENTER);
							GridPane.setValignment(text, VPos.CENTER);

							text.setFont(new Font(barrierHorizontalHeight-4));

							// Rotate the text.
							text.setRotate(90);

							// If the barrier is adjacent to 2 tiles :
							if(accBarrierVerticalId % 10 != 0  && (accBarrierVerticalId-1) % 10 != 0) {
								// Set id of the adjacent tiles in current barrier attributes.
								barrierVertical.setIdTile1("Tile "+Integer.toString(accBarrierVerticalId-1-((int) accBarrierVerticalId/10)));
								barrierVertical.setIdTile2("Tile "+Integer.toString(accBarrierVerticalId-((int) accBarrierVerticalId/10)));
							}

							// Increment accumulator to the next barrier number.
							accBarrierVerticalId++;
						}
						else {
							// Set a tile.
							// Create Tile "tile" with the right dimensions.
							Tile tile = new Tile(accTileId, tileWidth, tileColor);

							// Add actions on events for tile.
							// If barrier hover is true :
							tile.hoverProperty().addListener((observable, oldValue, hoverBoolean) -> {
								hoverTileId = tile.getId();
								//System.out.println("Hovered tile: " + this.hoverTileId);

								// Color tile if hovered.
								if(hoverBoolean) {
									tile.setOpacity(1);
									tile.setFill(Color.CHOCOLATE);
								}

								if(!hoverBoolean && tile.getFill() != barrierHorizontalColor) {
									tile.setFill(tileColor);
								}
							});


							// Set an event when the tile is clicked.    		
							tile.setOnMouseClicked(new EventHandler<MouseEvent>()
							{	
								@Override
								// Set the action(s) to do.
								public void handle(MouseEvent event) {

									// Get hovered tile id and number.
									String tileNumberString;
									int tileNumber;

									// If tile id matches "Tile X" form, ("Tile 1", ..., "Tile 9") :
									if(hoverTileId.length() == 6) {
										tileNumberString = hoverTileId.substring(hoverTileId.length()-1);
										tileNumber = Integer.parseInt(tileNumberString);
									}
									// If tile id does not match "Tile X" form :
									else {
										tileNumberString = hoverTileId.substring(hoverTileId.length()-2);
										tileNumber = Integer.parseInt(tileNumberString);
									}

									// If remove is failed it means player was not exists in board.
									// Add to update position.

									if(needSecondBarrierHorizontal == false && needSecondBarrierVertical == false) {
										for(Player playerTurn : listPlayer) {
											if(playerTurn.isTurn()) {
												playTurn(playerTurn,tileNumber);
											}
										}
									}	
								}
							});

							// Add the tile to the right span.
							boardGrid.add(tile, columnNumber, rowNumber);

							// Create text on the tile.
							Label text = new Label("Tile " + Integer.toString(accTileId));

							// Set Id of the tile.
							text.setId("Text " + tile.getId());

							// Set quick access to text's respective barrier.
							text.setLabelFor(tile);

							// Set text color.
							text.setTextFill(textColor);

							// Make mouse clicks pass through the text.
							text.setMouseTransparent(true);

							// Add the text to the right span.
							boardGrid.add(text, columnNumber, rowNumber);

							// Center the text in the span
							GridPane.setHalignment(text, HPos.CENTER);
							GridPane.setValignment(text, VPos.CENTER);

							// Increment accumulator to the next barrier number.
							accTileId++;
						}
					}  
				}
			}
			// Add boardGrid to Region.
			this.getChildren().add(boardGrid);
			setNumberOfPlayers(4);

			listPlayer = new ArrayList<Player>();
			listPlayer.add(player1);
			listPlayer.add(player2);
			listPlayer.add(player3);
			listPlayer.add(player4);
			System.out.println("b"+listPlayer.get(0).getCurrentTileId());
			
			/*
			for(int i = 0; i<this.ArrayConstantOfInitialPlayer.length;i++) {
				removePlayerTile(ArrayConstantOfInitialPlayer[i]);
				listPlayer.add(ArrayConstantOfInitialPlayer[i]);
				removePlayerTile(listPlayer.get(i));
			}
*/
			for(int i = 0; i < 4; i++) {	
				//ArrayConstantOfInitialPlayer[i].setCurrentTileId(ArrayConstantOfInitialPlayer[i].getIdStartTile());
				System.out.println("c " + listPlayer.get(i).getCurrentTileId().substring(5));
				addPlayerTile(Integer.parseInt(listPlayer.get(i).getCurrentTileId().substring(5)), listPlayer.get(i));
			}
			// Set barriers visibility as false.
			hideAllBarrier();
		}


		
		
	/**
	 * Return node list in particular coordinates.
	 * @param int column
	 * @param int row
	 */
	public ArrayList<Node> getNodeListColumnRow (int column, int row) {
		// Get board grid.
		GridPane boardGrid = (GridPane) this.getChildren().get(0);

		// Create ArrayList "result" where we will add the nodes to return. 
		ArrayList<Node> result = new ArrayList<Node>();

		// 
		for (Node node : boardGrid.getChildrenUnmodifiable()) {

			if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null) {
				if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
					result.add(node);
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Node getNodeById(String id) {
		// Get the board grid.
		GridPane boardGrid = (GridPane) this.getChildrenUnmodifiable().get(0);

		// For every node in the board :
		for(Node node: boardGrid.getChildrenUnmodifiable()) {
			// If the node with the id passed match :
			if(node.getId().equals(id)){
				return(node);
			}
		}
		return(null);
	}

	/**
	 * Sets barrier's opacity to 0 to hide every barrier on the board.
	 */
	public void hideAllBarrier() {
		// Get the board grid.
		GridPane boardGrid = (GridPane) this.getChildrenUnmodifiable().get(0);

		// For every node in the board :
		for(Node node: boardGrid.getChildrenUnmodifiable()) {
			// If the node is a barrier :
			if(node.getId().contains("Barrier") && !node.getId().contains("Text")){
				// Hide the barrier.
				node.setOpacity(0);
				Barrier barrier = (Barrier) node;
				barrier.setFill(Color.WHITE);
			}
		}
		// Set HashMap<String, ArrayList<String>> "adjacencyList".
		updateAdjacencyList(boardGrid);
	}

	/**
	 * Changes the visibility of any element on the board via id search.
	 * @param String idNode, id of the element to hide.
	 */
	public void showBarrier(String id, Color color) {
		// Get wanted barrier.
		Node node = getNodeById(id);

		// Hide the barrier.
		node.setOpacity(1);
		Barrier barrier = (Barrier) node;
		barrier.setFill(color);
	}

	/**
	 * Changes the visibility of any element on the board via id search.
	 * @param String idNode, id of the element to hide.
	 */
	public void hideBarrier(String id) {
		// Get wanted barrier.
		Node node = getNodeById(id);

		// Hide the barrier.
		node.setOpacity(0);
		Barrier barrier = (Barrier) node;
		barrier.setFill(Color.WHITE);
	}

	/**
	 * While printing helpful text in the console, change the visibility of any element on the board via id search.
	 * @param String idNode, id of the element to hide.
	 * @param boolean showConsoleText, show useful text.
	 */
	public void hide(String id) {
		// Get wanted barrier.
		Node node = getNodeById(id);

		// Hide the barrier.
		node.setOpacity(0);
	}

	public void show(String id) {
		// Get wanted barrier.
		Node node = getNodeById(id);

		// Hide the barrier.
		node.setOpacity(1);
	}

	// Methods: Add, remove, move Player.

	/**
	 * Adds a player on a tile.
	 * @param int wantedTileNumber, Number tile on which you add the player.
	 * @param Player player, object of Player class to put on the tile.
	 */	
	public boolean addPlayerTile(int wantedTileNumber, Player player) {
		// Get the board grid.
		GridPane boardGrid = (GridPane) this.getChildrenUnmodifiable().get(0);
		String wantedTileId = "Tile " + wantedTileNumber;

		// Find wanted tile attributes.
		// For every row in the board :
		for(int numberRow = 0; numberRow < 9; numberRow++) {
			// For every column in the board :
			for(int numberColumn = 0; numberColumn < 9; numberColumn++) {
				// For every node in that grid span :
				for(Node node: boardGrid.getChildrenUnmodifiable()) {
					// If node is wanted tile :
					if(node.getId().equals(wantedTileId)) {
						// Get tile span coordinates.
						int wantedTileColumn = GridPane.getColumnIndex(node);
						int wantedTileRow = GridPane.getRowIndex(node);

						// Check if the span contains a Player instance :
						// Get the list of nodes in the same span.
						ArrayList<Node> nodeList = getNodeListColumnRow(wantedTileColumn, wantedTileRow);

						// For every Node in nodeList :
						for(int i = 0; i < nodeList.size(); i++) {

							// If the span already contains a Player.
							if(nodeList.get(i) instanceof Player) {
								// Player cannot be added here.

								// Player could not be added.
								return(false);
							}
						}
						// If the span does not contain any player :
						// Add player in the tile span :
						boardGrid.add(player, wantedTileColumn, wantedTileRow);

						// Center the player in the span
						GridPane.setHalignment(player, HPos.CENTER);
						GridPane.setValignment(player, VPos.CENTER);

						// Create text on the barrierHorizontal.
						Label text = new Label(player.getId());

						// Set Id for the text of barrierVertical.
						text.setId("Text " + player.getId());

						// Center the text in the span
						GridPane.setHalignment(text, HPos.CENTER);
						GridPane.setValignment(text, VPos.CENTER);

						// Set quick access to text's respective barrier.
						text.setLabelFor(player);

						// Set text color.
						text.setTextFill(Color.BLACK);

						// Make mouse clicks pass through the text.
						text.setMouseTransparent(true);

						// Add the text to the right span.
						boardGrid.add(text, wantedTileColumn, wantedTileRow);

						// Set an event when the tile is clicked.    		
						player.setOnMouseClicked(new EventHandler<MouseEvent>()
						{
							@Override
							// Set the action(s) to do.
							public void handle(MouseEvent event) {

							}
						});

						// Player added successfully.
						return(true);
					}
				}
			}
		}
		// Case: there is no tile in the board grid.
		// Player could not be added.
		return(false);
	}
	/**
	 * Removes a player from a tile.
	 * @param int wantedTileNumber, number of the tile where the player must be removed.
	 * @param Player player, player that must be removed.
	 */	
	public boolean removePlayerTile(Player player) {		
		// Get the board grid.
		GridPane boardPane = (GridPane) this.getChildrenUnmodifiable().get(0);

		// Get name of the player to remove.
		String playerTextId = "Text " + player.getId();
		// Find wanted tile attributes.
		// For every node in the grid :
		for(Node node: boardPane.getChildrenUnmodifiable()) {			
			// If node is the wanted player or wanted player text : 
			if(node.getId().equals(playerTextId)){
				// Delete the text.
				boardPane.getChildren().remove(node);

				// Delete the player.
				boardPane.getChildren().remove(player);

				// Player removed successfully.
				return(true);
			}	
		}
		// Player could not be removed.
		return(false);
	}


	/**
	 * Moves a player to another tile.
	 * @param int newTileNumber, number of the tile where the player must be removed.
	 * @param Player player, player that must be moved.
	 */	
	public boolean movePlayerTile(int newTileNumber, Player player) {
		// Get and save player attributes for later.
		String playerName = player.getId();
		boolean playerTurn = player.isTurn();

		String currentTileId;
		// Remove player from the board.
		if(player.getCurrentTileId() == null) {

			currentTileId = player.getIdStartTile() ;
		}else {
			currentTileId = player.getCurrentTileId() ;
		}

		if(canMove(currentTileId,"Tile " + newTileNumber)) {
			if(removePlayerTile(player) == true) {
				// player instance was deleted so we have to construct it again... 
				player = new Player(playerName, "Tile " + newTileNumber, playerTurn);

				// Add player on the new tile.
				if(addPlayerTile(newTileNumber, player) == true){
					// Player removed successfully.
					return(true);	
				}		
			}
			else {
				// Cannot add player.
				// Player could not be moved.
				return(false);
			}
		}
		// Cannot remove player.
		// Player could not be removed.
		return(false);
	}



	// Methods: Setter, getter and special uses for the adjacency list of the board.
	/**
	 * Getter of adjacency list.
	 * @return HashMap<String, ArrayList<String>> adjacency list.
	 */	
	public HashMap<String, ArrayList<String>> getAdjacencyList(){
		return this.adjacencyList;		
	}

	/**
	 * Setter of adjacency list.
	 * @param HashMap<String, ArrayList<String>> newAdjacencyList.
	 */	
	public void setAdjacencyList(HashMap<String, ArrayList<String>> newAdjacencyList){
		this.adjacencyList = newAdjacencyList;
	}

	/**
	 * Initializes adjacency list.
	 * @param GridPane boardGrid, GridPane containing all the node of the board. 
	 * @param boolean showConsoleText, show useful text.
	 */	
	public HashMap<String, ArrayList<String>> createAdjacencyList(GridPane boardGrid){
		HashMap<String, ArrayList<String>> newAdjacencyList = new HashMap<>();
		for(Node node : boardGrid.getChildren()) {
			if(node.getId().contains("Barrier") && !node.getId().contains("Text")){//check for each barrier in board
				Barrier barrier =  (Barrier) node;
				// If the barriers id are both not empty.
				if(!((barrier.getIdTile1().equals("") || barrier.getIdTile2().equals(""))) && node.getOpacity() == 0) {
					// Add vertices to the edge matrix.
					addEdge(newAdjacencyList, barrier.getIdTile1(),barrier.getIdTile2());
				}
			}
		}
		return(newAdjacencyList);
	}

	/**
	 * Updates adjacency list initializing a new one and overwriting the existent one.
	 * @param GridPane boardGrid, GridPane containing all the node of the board. 
	 * @param boolean showConsoleText, show useful text.
	 */	
	public void updateAdjacencyList(GridPane boardGrid){
		setAdjacencyList(createAdjacencyList(boardGrid));
	}

	/**
	 * Updates adjacency list initializing a new one and overwriting the existent one.
	 * @param GridPane boardGrid, GridPane containing all the node of the board. 
	 * @param boolean showConsoleText, show useful text.
	 */	
	public void updateAdjacencyList(GridPane boardGrid, boolean showConsoleText){
		setAdjacencyList(createAdjacencyList(boardGrid));
		if(showConsoleText) {
			System.out.println("Adjacency List Updated :");
			System.out.println(getAdjacencyList());
			System.out.println();
		}
	}

	public boolean addEdge(HashMap<String, ArrayList<String>> adjacencyList, String idEdge1, String idEdge2) {
		if(adjacencyList.containsKey(idEdge1)) {
			//adjacencyList.get(idEdge1).add(idEdge2);

			if(adjacencyList.containsKey(idEdge2)) {
				adjacencyList.get(idEdge1).add(idEdge2);
				adjacencyList.get(idEdge2).add(idEdge1);
				return(true);
			}

			else {
				adjacencyList.get(idEdge1).add(idEdge2);

				ArrayList<String> b = new ArrayList<>();
				adjacencyList.put(idEdge2, b);
				adjacencyList.get(idEdge2).add(idEdge1);
				return(true);
			}
		}

		else if(adjacencyList.containsKey(idEdge2)) {
			//adjacencyList.get(idEdge1).add(idEdge2);

			if(adjacencyList.containsKey(idEdge1)) {
				adjacencyList.get(idEdge1).add(idEdge2);
				adjacencyList.get(idEdge2).add(idEdge1);
				return(true);
			}

			else {
				adjacencyList.get(idEdge2).add(idEdge1);

				ArrayList<String> b = new ArrayList<>();
				adjacencyList.put(idEdge1, b);
				adjacencyList.get(idEdge1).add(idEdge2);
				return(true);
			}
		}

		// Case: idEdge1 and idEdge2 don't exist in the adjacency list.
		else {
			ArrayList<String> b = new ArrayList<>();
			ArrayList<String> c = new ArrayList<>();

			adjacencyList.put(idEdge1, b);
			adjacencyList.put(idEdge2, c);
			adjacencyList.get(idEdge1).add(idEdge2);
			adjacencyList.get(idEdge2).add(idEdge1);

			return(true);
		}

	}
	/**
	 * This method is used to add an edge to the adjacencyList
	 * @param adjacencyList
	 * @param idEdge1
	 * @param idEdge2
	 * @param showConsoleText
	 * @return A boolean true if the action happened and false if the Edge can't be added
	 */

	public boolean addEdge(HashMap<String, ArrayList<String>> adjacencyList, String idEdge1, String idEdge2, boolean showConsoleText) {
		if(adjacencyList.containsKey(idEdge1)) {
			//adjacencyList.get(idEdge1).add(idEdge2);

			if(adjacencyList.containsKey(idEdge2)) {
				adjacencyList.get(idEdge1).add(idEdge2);
				adjacencyList.get(idEdge2).add(idEdge1);
				if(showConsoleText) {
					System.out.println(adjacencyList);
				}
				return(true);
			}

			else {
				adjacencyList.get(idEdge1).add(idEdge2);

				ArrayList<String> b = new ArrayList<>();
				adjacencyList.put(idEdge2, b);
				adjacencyList.get(idEdge2).add(idEdge1);

				if(showConsoleText) {
					System.out.println(adjacencyList);
				}
				return(true);
			}
		}

		else if(adjacencyList.containsKey(idEdge2)) {
			//adjacencyList.get(idEdge1).add(idEdge2);

			if(adjacencyList.containsKey(idEdge1)) {
				adjacencyList.get(idEdge1).add(idEdge2);
				adjacencyList.get(idEdge2).add(idEdge1);

				if(showConsoleText) {
					System.out.println(adjacencyList);
				}
				return(true);
			}

			else {
				adjacencyList.get(idEdge2).add(idEdge1);

				ArrayList<String> b = new ArrayList<>();
				adjacencyList.put(idEdge1, b);
				adjacencyList.get(idEdge1).add(idEdge2);

				if(showConsoleText) {
					System.out.println(adjacencyList);
				}
				return(true);
			}
		}

		// Case: idEdge1 and idEdge2 don't exist in the adjacency list.
		else {
			ArrayList<String> b = new ArrayList<>();
			ArrayList<String> c = new ArrayList<>();

			adjacencyList.put(idEdge1, b);
			if(showConsoleText) {
				System.out.println("Création de : " + idEdge1 + b);
			}
			adjacencyList.put(idEdge2, c);
			if(showConsoleText) {
				System.out.println("Création de : " + idEdge2 + c);
			}

			adjacencyList.get(idEdge1).add(idEdge2);
			adjacencyList.get(idEdge2).add(idEdge1);

			if(showConsoleText) {
				System.out.println("Adjacency updated" + adjacencyList);
				System.out.println("\n");
			}
			return(true);
		}
	}

/**
 * This method does a DFS starting from the startVertex Tile
 * @param startVertex
 * @return
 */
	public LinkedList<String> dfs(String startVertex) {
		HashMap<String, ArrayList<String>> adjacencyList = getAdjacencyList();

		Stack<String> stack = new Stack<>();

		LinkedList<String> visited = new LinkedList<>();
		visited.add(startVertex);

		LinkedList<String> notVisited = new LinkedList<>();

		ArrayList<String> vertexNeighborsNotVisited = new ArrayList<>() ;

		ArrayList<String> vertexNeighbors = adjacencyList.get(startVertex);


		for(String vertexName : vertexNeighbors) {
			if(!visited.contains(vertexName) && !stack.contains(vertexName)) {
				notVisited.add(vertexName);
			}
		}
		stack.addAll(notVisited);

		while(stack.size() > 0) {
			String stackFirstElement = stack.get(0);
			vertexNeighborsNotVisited.clear();

			if(!visited.contains(stackFirstElement)) {
				visited.add(stackFirstElement);
				vertexNeighbors = adjacencyList.get(stackFirstElement);

				for(String vertex : vertexNeighbors) {
					if(!visited.contains(vertex) && !stack.contains(vertex)) {
						vertexNeighborsNotVisited.add(vertex);
					}		
				}
				stack.addAll(vertexNeighborsNotVisited);
			}		
			stack.remove(0);
		}
		return(visited);
	}

	/**
	 * This method is used to get a boolean telling if two Tiles are connected
	 * @param verticeA
	 * @param verticeB
	 * @return a boolean : true if they are connected, false if not
	 */
	public boolean areConnected(String verticeA, String verticeB) {
		if(dfs(verticeA).contains(verticeB)){
			return(true);
		}
		else {
			return(false);
		}
	}

	/**
	 * this method is used to know if a tile is already occupied by a player
	 * @param TileA
	 * @return a boolean : true if the Tile is occupied, false if not
	 */

	public boolean isTileOccupied(String TileA) {
		if (numberOfPlayers == 2)
			return (player1.getCurrentTileId().equals(TileA) || player2.getCurrentTileId().equals(TileA));
		return (player1.getCurrentTileId().equals(TileA) || player2.getCurrentTileId().equals(TileA) || player3.getCurrentTileId().equals(TileA) ||player4.getCurrentTileId().equals(TileA));
	}

	/**
	 * This method tells us if we can move from TileA to TileB
	 * @param TileA
	 * @param TileB
	 * @return a boolean : true if the move is possible, false if not
	 */
	public boolean canMove(String TileA,String TileB) {
		if (isTileOccupied(TileB)) //can't move if the tile is occupied
			return false;

		ArrayList<String> adjTileA = getAdjacencyList().get(TileA);
		for(String x: adjTileA) {//can move if the tile is adjacent
			if (x.equals(TileB))
				return true;
		}
		return false;//can't move if the tile is not adjacent
	}

	/**
	 * This method is used to get a list of the available tiles when the player is on tileA 
	 * @param TileA
	 * @return A String with all the available tiles
	 */
	public String availableTiles(String TileA) {//print only
		ArrayList<String> adjTileA = getAdjacencyList().get(TileA);
		String available = "";
		for(String x: adjTileA) {//can move if the tile is adjacent
			if (!isTileOccupied(x))
			{

				available = available+x+" ";
			}
		}
		return available;
	}

/**
 * This method is used to change the player playing
 */
	public void changeTurn() {
		if(numberOfPlayers == 2) {
			player1.setTurn(!player1.isTurn());
			player2.setTurn(!player2.isTurn());
		}
		else {
			if(player1.isTurn()) {
				player1.setTurn(!player1.isTurn());
				player2.setTurn(!player2.isTurn());
			}
			else if(player2.isTurn()) {
				player2.setTurn(!player2.isTurn());
				player3.setTurn(!player3.isTurn());
			}
			else if(player3.isTurn()) {
				player3.setTurn(!player3.isTurn());
				player4.setTurn(!player4.isTurn());
			}
			else if(player4.isTurn()) {
				player4.setTurn(!player4.isTurn());
				player1.setTurn(!player1.isTurn());
			}
		}	
	}

	/**
	 * This method is used to manage a player's turn
	 * @param player
	 * @param tileNumber
	 * @return a boolean : true if the player played is turn, false if not
	 */
	public boolean playTurn(Player player, int tileNumber) {

		System.out.println();
		System.out.println(player.getId()+ "turn");
		// If player is not on board :

		String currentTile = null;
		// Remove player from the board.
		if(player.getCurrentTileId() == null) {
			currentTile = player.getIdStartTile() ;
		}else {
			currentTile = player.getCurrentTileId() ;
		}

		System.out.println();
		System.out.println();
		System.out.println("Position: " +  currentTile);
		System.out.println("Available tiles: " + availableTiles(currentTile));
		System.out.println();

		// If clicked tile is accessible :
		// Move
		if(movePlayerTile(tileNumber,player)) {
			//set
			player.setCurrentTileId(hoverTileId);

			// Check if player is on winner tile :
			for(String winnerTileId : player.getIdWinningTiles()) {
				System.out.println(winnerTileId);
				// If player1 is on a winner tile :
				if(player.getCurrentTileId().equals(winnerTileId)) {
					player.setWinner(true);
					System.out.println();
					System.out.println("WINNER"+ player.getId());
					System.out.println();
					System.exit(1);
				}
				// If player1 is NOT on a winner tile :
				else {
					player.setWinner(false);
				}
			}
			// Switch turns.
			System.out.println("WAITING ANOTHER CLICK...");
			System.out.println();
			changeTurn();
			return true;
		}
		else {
			System.out.println("NOPE");
			System.out.println("WAITING ANOTHER CLICK...");
			System.out.println();
			return false;
		}
	}

	/**
	 * This method is used to remove the link between tileA and tileB
	 * @param newAdjacencyList
	 * @param tileA
	 * @param tileB
	 * @return the grid actualised 
	 */

	public HashMap<String, ArrayList<String>> removeTileLink(HashMap<String, ArrayList<String>> newAdjacencyList,String tileA,String tileB){
		newAdjacencyList.get(tileA).remove(tileB);
		// check to erase the tile key if it is empty
		if (newAdjacencyList.get(tileA).isEmpty()) {
			newAdjacencyList.remove(tileA);
		}
		newAdjacencyList.get(tileB).remove(tileA);
		if (newAdjacencyList.get(tileB).isEmpty()) {
			newAdjacencyList.remove(tileB);
		}
		return newAdjacencyList;
	}
	
	
	public ArrayList<String> readSavedGame(String path) throws FileNotFoundException, IllegalArgumentException {
		HashMap<String, ArrayList<String>> readAdjacencyList = new HashMap<String, ArrayList<String>>();
		ArrayList<String> savedText = new ArrayList<>();
		try {
 	 		Scanner read;
 	 		
			read = new Scanner (new File(path));
			read.useDelimiter(";\\R");
	 		String listeAdjString, nbPlayersString,
	 			player1Name, player1CurrentTile, player1TurnString, 
		 		player2Name, player2CurrentTile, player2TurnString,
		 		player3Name, player3CurrentTile, player3TurnString,
		 		player4Name, player4CurrentTile, player4TurnString;
	 		try {
	 			if(!read.hasNext()) {
	 				System.out.println("Text file is empty");
	 				System.out.println();
	 				System.exit(0);
	 			}
		 		while(read.hasNext()) {
		 			// Read first text string in file until ";\\R" 
					listeAdjString = read.next();
					listeAdjString = listeAdjString.replace("[", "");
					listeAdjString = listeAdjString.replace("]", "");
					
					Map<String, String> map = Splitter.on("\\Z").withKeyValueSeparator("=").split(listeAdjString);
					
					String[] strSplit = new String[0];
					
					//map.remove("");
					
					for(Entry<String, String> pair : map.entrySet()) {
						
						String key = pair.getKey();
						
						String value = map.get(pair.getKey());
						
						strSplit = value.split(",");
						
						ArrayList<String> strList = new ArrayList<String>(Arrays.asList(strSplit));
		
						readAdjacencyList.put(key, strList);
					}
					setAdjacencyList(readAdjacencyList);
					System.out.println("listAdj: " + readAdjacencyList);
					System.out.println();
					
					nbPlayersString = read.next();
					savedText.add(nbPlayersString);
					
					player1Name = read.next();
					savedText.add(player1Name);
		
					player1CurrentTile = read.next();
					savedText.add(player1CurrentTile);
					
					player1TurnString = read.next();
					savedText.add(player1TurnString);
		
					System.out.println("player1Name " + player1Name + " player1CurrentTile " + player1CurrentTile + " player1Turn " + player1TurnString);
					
					player2Name = read.next();
					savedText.add(player2Name);
					
					player2CurrentTile = read.next();
					savedText.add(player2CurrentTile);
					
					player2TurnString = read.next();
					savedText.add(player2TurnString);
					
					System.out.println("player2Name " + player2Name + " player2CurrentTile " + player2CurrentTile + " player2Turn " + player2TurnString);
					
					player3Name = read.next();
					savedText.add(player3Name);
					
					player3CurrentTile = read.next();
					savedText.add(player3CurrentTile);
					
					player3TurnString = read.next();
					savedText.add(player3TurnString);
					
					System.out.println("player3Name " + player3Name + " player3CurrentTile " + player3CurrentTile + " player3Turn " + player3TurnString);
					
					player4Name = read.next();
					savedText.add(player4Name);
					
					player4CurrentTile = read.next();
					savedText.add(player4CurrentTile);
					
					player4TurnString = read.next();
					savedText.add(player4TurnString);
					
					System.out.println("player4Name " + player4Name + " player4CurrentTile " + player4CurrentTile + " player4Turn " + player4TurnString);
					System.out.println();
					System.out.println("nbPlayer: " + Integer.parseInt(nbPlayersString));
					System.out.println();
					return(savedText);
		 		}
		 		return(savedText);
	 		}
			catch(NoSuchElementException e) {
				System.out.println();
				System.out.println("Wrong amount of strings to read");
				System.out.println();
				return(savedText);
			}
 		}
 		catch(FileNotFoundException e){
 			System.out.println("Text file not found");
 			System.out.println();
 			return(savedText);
 		}
	}
	

/**
 * This method is used to save a game
 * @param player1
 * @param player2
 * @param player3
 * @param player4
 */
	public void saveGame(Player player1, Player player2, Player player3, Player player4) {
		//Creating an instance of file
		Path path = Paths.get("H:\\Documents\\GitHub\\CYPath20222023\\save.txt");

		HashMap<String, ArrayList<String>> adjacencyList = new HashMap<String, ArrayList<String>>();
		adjacencyList = getAdjacencyList();
		
		int numberOfPlayers = getNumberOfPlayers();
		
		// Custom string as an input
		String adjacencyListString = adjacencyList.toString(), numberOfPlayersString,
				player1Name, player1CurrentTile, player1TurnString,
				player2Name, player2CurrentTile, player2TurnString,
				player3Name, player3CurrentTile, player3TurnString,
				player4Name, player4CurrentTile, player4TurnString;

		String breakLine = ";\r\n";

		adjacencyListString = adjacencyListString.replace("{", "");
		adjacencyListString = adjacencyListString.replace("}", "");

		System.out.println(adjacencyListString);

		@SuppressWarnings("removal")
		Integer numberOfPlayersInteger = new Integer(numberOfPlayers);
		numberOfPlayersString = numberOfPlayersInteger.toString();

		ArrayList<String> stringsToFormat = new ArrayList<String>();
		stringsToFormat.add(adjacencyListString);
		stringsToFormat.add(numberOfPlayersString);

		player1Name = player1.getId();
		player1CurrentTile = player1.getCurrentTileId();
		player1TurnString = Boolean.toString(player1.isTurn());
		stringsToFormat.add(player1Name);
		stringsToFormat.add(player1CurrentTile);
		stringsToFormat.add(player1TurnString);

		player2Name = player2.getId();
		player2CurrentTile = player2.getCurrentTileId();
		player2TurnString = Boolean.toString(player2.isTurn());
		stringsToFormat.add(player2Name);
		stringsToFormat.add(player2CurrentTile);
		stringsToFormat.add(player2TurnString);

		player3Name = player3.getId();
		player3CurrentTile = player3.getCurrentTileId();
		player3TurnString = Boolean.toString(player3.isTurn());
		stringsToFormat.add(player3Name);
		stringsToFormat.add(player3CurrentTile);
		stringsToFormat.add(player3TurnString);

		player4Name = player4.getId();
		player4CurrentTile = player4.getCurrentTileId();
		player4TurnString = Boolean.toString(player4.isTurn());
		stringsToFormat.add(player4Name);
		stringsToFormat.add(player4CurrentTile);
		stringsToFormat.add(player4TurnString);

		String toSave = "";
		for(String str : stringsToFormat) {
			toSave += str + breakLine;
		}

		//System.out.println(toSave);

		// Try block to check for exceptions
		try {
			// Now calling Files.writeString() method
			// with path , content & standard charsets
			Files.writeString(path, toSave, StandardCharsets.UTF_8);
		}

		// Catch block to handle the exception
		catch (IOException e) {
			// Print message exception occurred as
			// invalid. directory local path is passed
			System.out.print("Invalid Path");
		}
	}
/**
 * This method gives the number of players in the game
 * @return the number player in the game
 */
	public int getNumberOfPlayers() {
		return(this.numberOfPlayers);
	}
	
	/**
	 * This method set the number of players
	 * @param numberOfplayers
	 */
	public void setNumberOfPlayers(int numberOfplayers) {
		this.numberOfPlayers = numberOfplayers;
	}
	
}