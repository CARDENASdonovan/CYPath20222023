package developpement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
	private Player player1 = new Player("Player1", 20, Color.RED);
	private Player player2 = new Player("Player2", 20, Color.BLUE);
	private Player player3 = new Player("Player3", 20, Color.YELLOW);
	private Player player4 = new Player("Player4", 20, Color.GREEN);
	private int nbPlayers = 2;
	private ArrayList<String> winnerTiles1 = new ArrayList<String>();
	private ArrayList<String> winnerTiles2 = new ArrayList<String>();
	private ArrayList<String> winnerTiles3 = new ArrayList<String>();
	private ArrayList<String> winnerTiles4 = new ArrayList<String>();
	
	/**
	 * Constructs an (int rowTotalNumber) x (int columnTotalNumber) Object (Node) using JavaFX library.
	 * @param int rowTotalNumber
	 * @param int columnTotalNumber
	 */

	
	/**
	 * Constructs a (int rowTotalNumber) x (int columnTotalNumber) Object (Node) using JavaFX library.
	 * @param int initialX
	 * @param int initialX
	 * @param int rowTotalNumber
	 * @param int columnTotalNumber
	 */
	
	public Board(int initialX, int initialY, int tileRowsQuantity, int tileColumnsQuantity, int numberOfPlayers) {
		player1.setTurn(true);
	// Initialization of the winner tiles for each player.
		// For player 1.
		for(int i = 73; i < 82; i++) {
			winnerTiles1.add("Tile " + i);
		}
	
		// For player 2.
		for(int i = 1; i < 10; i++) {
			winnerTiles2.add("Tile " + i);
		}
		
		// For player 3.
		for(int i = 1; i < 74; i+=9) {
			winnerTiles3.add("Tile " + i);
		}
		
		// For player 4.
		for(int i = 9; i < 82; i+=9) {
			winnerTiles4.add("Tile " + i);
		}
		
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
    			
        			// If odd column :
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
	        					if(!hoverBoolean && barrierHorizontal.getFill() != barrierHorizontalColor) {
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
	    	                    if(barrierHorizontal.getFill() != barrierHorizontalColor) {
	    	                    	barrierHorizontal.setFill(barrierHorizontalColor);
	    	                    	updateAdjacencyList(boardGrid);
	    	                    }
	    	                    else {
	    	                    	barrierHorizontal.setOpacity(0);
	    	                    	updateAdjacencyList(boardGrid);
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
	        					if(!hoverBoolean && barrierVertical.getFill() != barrierHorizontalColor) {
		        					barrierVertical.setOpacity(0);
		        				}
	        				}
	        			});
	        			
	        			// Set an event when the barrier is clicked.
                        barrierVertical.setOnMouseClicked(new EventHandler<MouseEvent>()
                        {
                            @Override
                            // Set the action(s) to do.
                            public void handle(MouseEvent event) {
                                if(barrierVertical.getFill() != barrierHorizontalColor) {
                                    barrierVertical.setFill(barrierHorizontalColor);
                                    updateAdjacencyList(boardGrid);
                                }
                                else {
                                    barrierVertical.setOpacity(0);
                                    updateAdjacencyList(boardGrid);
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
	        				System.out.println("Hovered tile: " + this.hoverTileId);
	        				
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
	    	                	System.out.println();
	    	                	System.out.println();
	    	                	System.out.println();
    	                		System.out.println("Player1 turn?" + player1.isTurn());
    	                		System.out.println();
    	                		System.out.println("Player2 turn?" + player2.isTurn());
    	                		System.out.println();
    	                		
    	                		if(player1.isTurn() == true) {
    	                			System.out.println();
    	                			System.out.println("PLAYER 1 TURN");
    	                			// If player is not on board :
	    	                		if(player1.getTileId() == null) {
	    	                			// Try to remove it JUST IN CASE for less weird situations. ¯\_(ツ)_/¯
	    	                			removePlayerTile(player1, true);
	    	                			
	    	                			// set
		    	                		player1.setTileId(hoverTileId);
		    	                		addPlayerTile(tileNumber, player1, true);
		    	                		player1.setTileId(hoverTileId);
		    	                		System.out.println("Player is in " + player1.getTileId());
		    	                		System.out.println("Now: " + getAdjacencyList());
		    	                		System.out.println("WAITING ANOTHER CLICK...");
		    	                		System.out.println();
		    	                		
		    	                		
		    	                		// Check if player is on winner tile :
		    	                		for(String winnerTileId: winnerTiles1) {
		    	                			System.out.println(winnerTileId);
		    	                			// If player1 is on a winner tile :
		    	                			if(player1.getTileId().equals(winnerTileId)) {
		    	                				player1.setWinner(true);
		    	                				System.out.println();
		    	                				System.out.println("WINNER PLAYER 1");
		    	                				System.out.println();
		    	                				System.exit(1);
		    	                			}
		    	                			// If player1 is NOT on a winner tile :
		    	                			else {
		    	                				player1.setWinner(false);
		    	                			}
		    	                		}
		    	                		
		    	                		
	    	                		}
	    	                		// If it is not the first time player1 is added AND he has not won :
		    	                	else {
		    	                		
	    	                			System.out.println();
	    	                			System.out.println();
		    	                		System.out.println("Position: " + player1.getTileId());
	    	                			System.out.println("Available tiles: " + availableTiles(player1.getTileId()));
	    	                			System.out.println();
	    	                			
	    	                			// If clicked tile is accessible :
	    	                			// Move
	    	                			if(movePlayerTile(tileNumber,player1)) {
		    	                			//set
			    	                		player1.setTileId(hoverTileId);
			    	                		
			    	                		// Check if player is on winner tile :
			    	                		for(String winnerTileId: winnerTiles1) {
			    	                			System.out.println(winnerTileId);
			    	                			// If player1 is on a winner tile :
			    	                			if(player1.getTileId().equals(winnerTileId)) {
			    	                				player1.setWinner(true);
			    	                				System.out.println();
			    	                				System.out.println("WINNER PLAYER 1");
			    	                				System.out.println();
			    	                				System.exit(1);
			    	                			}
			    	                			// If player1 is NOT on a winner tile :
			    	                			else {
			    	                				player1.setWinner(false);
			    	                			}
			    	                		}
			    	    					changeTurn();
			    	                		// Switch turns.
			    	                		System.out.println("Player is in " + player2.getTileId());
			    	                		System.out.println("WAITING ANOTHER CLICK...");
			    	                		System.out.println();
	    	                			}
	    	                			else {
	    	                				System.out.println("NOPE");
	    	                				System.out.println("WAITING ANOTHER CLICK...");
	    	                				System.out.println();
	    	                			}
		    	                	}
    	                		}
    	                		// If turn player 2
    	                		else if(player2.isTurn() == true) {
    	                			System.out.println();
    	                			System.out.println("PLAYER 2 TURN");
    	                			// If player is not on board :
	    	                		if(player2.getTileId() == null) {
	    	                			// Try to remove it JUST IN CASE for less weird situations. ¯\_(ツ)_/¯
	    	                			removePlayerTile(player2, true);
	    	                			
	    	                			// set
	    	                			player2.setTileId(hoverTileId);
		    	                		addPlayerTile(tileNumber, player2, true);
		    	                		player2.setTileId(hoverTileId);
		    	                		System.out.println("Player is in " + player2.getTileId());
		    	                		System.out.println("Now: " + getAdjacencyList());
		    	                		System.out.println("WAITING ANOTHER CLICK...");
		    	                		System.out.println();
		    	                		
		    	                		
		    	                		// Check if player is on winner tile :
		    	                		for(String winnerTileId: winnerTiles2) {
		    	                			System.out.println(winnerTileId);
		    	                			// If player1 is on a winner tile :
		    	                			if(player2.getTileId().equals(winnerTileId)) {
		    	                				player2.setWinner(true);
		    	                				System.out.println();
		    	                				System.out.println("WINNER PLAYER 2");
		    	                				System.out.println();
		    	                				System.exit(1);
		    	                			}
		    	                			// If player1 is NOT on a winner tile :
		    	                			else {
		    	                				player2.setWinner(false);
		    	                			}
		    	                		}
		    	                		
		    	                		
	    	                		}
	    	                		// If it is not the first time player1 is added AND he has not won :
		    	                	else {
		    	                		
	    	                			System.out.println();
	    	                			System.out.println();
		    	                		System.out.println("Position: " + player2.getTileId());
	    	                			System.out.println("Available tiles: " + availableTiles(player2.getTileId()));
	    	                			System.out.println();
	    	                			
	    	                			// If clicked tile is accessible :
	    	                			// Move
	    	                			if(movePlayerTile(tileNumber,player2)) {
		    	                			//set
			    	                		player2.setTileId(hoverTileId);
			    	                		
			    	                		// Check if player is on winner tile :
			    	                		for(String winnerTileId: winnerTiles2) {
			    	                			System.out.println(winnerTileId);
			    	                			// If player1 is on a winner tile :
			    	                			if(player2.getTileId().equals(winnerTileId)) {
			    	                				player2.setWinner(true);
			    	                				System.out.println();
			    	                				System.out.println("WINNER PLAYER 2");
			    	                				System.out.println();
			    	                				System.exit(1);
			    	                			}
			    	                			// If player1 is NOT on a winner tile :
			    	                			else {
			    	                				player2.setWinner(false);
			    	                			}
			    	                		}
			    	    					changeTurn();
			    	                		// Switch turns.
			    	                		System.out.println("Player is in " + player1.getTileId());
			    	                		System.out.println("WAITING ANOTHER CLICK...");
			    	                		System.out.println();
	    	                			}
	    	                			else {
	    	                				System.out.println("NOPE");
	    	                				System.out.println("WAITING ANOTHER CLICK...");
	    	                				System.out.println();
	    	                			}
		    	                	}
    	                		}
	    	                	/*
	    	                    if(tile.getFill() != Color.GREEN) {
	    	                    	tile.setFill(Color.GREEN);
	    	                    }
	    	                    else {
	    	                    	tile.setFill(Color.BLUE);
	    	                    }*/
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
		
		//Initialize the players
		addPlayerTile(5, player1);
		player1.setTileId("Tile 5");
		addPlayerTile(77, player2);
		player2.setTileId("Tile 77");
		if(nbPlayers == 4) {
			addPlayerTile(37, player3);
			player3.setTileId("Tile 37");
			addPlayerTile(45, player4);
			player4.setTileId("Tile 45");
		}
		
		// Set barriers visibility as false.
		hideAllBarrier();
		
		// Set HashMap<String, ArrayList<String>> "adjacencyList".
		updateAdjacencyList(boardGrid);
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
			}
		}
	}

	/**
	 * While printing helpful text in the console, sets barrier's opacity to 0 to hide every barrier on the board.
	 * @param boolean showConsoleText, show useful text. 
	 */
	public void hideAllBarrier(boolean showConsoleText) {
		// Get the board grid.
		GridPane boardGrid = (GridPane) this.getChildrenUnmodifiable().get(0);
		
		// For every node in the board :
		for(Node node: boardGrid.getChildrenUnmodifiable()) {
			// If the node is a barrier :
			if(node.getId().contains("Barrier") && !node.getId().contains("Text")){
				// Hide the barrier.
				node.setOpacity(0);
				
				// Print useful text.
				if(showConsoleText) {
					System.out.println(node.getId() + " now hidden.");
					System.out.println();
				}
			}
		}
	}
	
	/**
 	* Changes the visibility of any element on the board via id search.
 	* @param String idNode, id of the element to hide.
 	*/
	public void show(String idNode) {
		// Get the board grid.
		GridPane boardGrid = (GridPane) this.getChildrenUnmodifiable().get(0);
		
		// For every node in the board :
		for(Node node: boardGrid.getChildrenUnmodifiable()) {
			// If the node with the id passed match :
			if(node.getId().equals(idNode)){
				// Hide the barrier.
				node.setOpacity(1);
			}
		}
	}
	
	/**
 	* Changes the visibility of any element on the board via id search.
 	* @param String idNode, id of the element to hide.
 	*/
	public void show(String idNode, boolean showConsoleText) {
		// Get the board grid.
		GridPane boardGrid = (GridPane) this.getChildrenUnmodifiable().get(0);
		
		// For every node in the board :
		for(Node node: boardGrid.getChildrenUnmodifiable()) {
			// If the node with the id passed match :
			if(node.getId().equals(idNode)){
				// Hide the barrier.
				node.setOpacity(1);
				if(showConsoleText) {
					System.out.println(node.getId() + " now visible.");
					System.out.println();
				}
			}
		}
	}
	
	/**
 	* Changes the visibility of any element on the board via id search.
 	* @param String idNode, id of the element to hide.
 	*/
	public void hide(String idNode) {
		// Get the board grid.
		GridPane boardGrid = (GridPane) this.getChildrenUnmodifiable().get(0);
		
		// For every node in the board :
		for(Node node: boardGrid.getChildrenUnmodifiable()) {
			// If the node with the id passed match :
			if(node.getId().equals(idNode)){
				// Hide the barrier.
				node.setOpacity(0);
			}
		}
	}
	
	/**
 	* While printing helpful text in the console, change the visibility of any element on the board via id search.
 	* @param String idNode, id of the element to hide.
	* @param boolean showConsoleText, show useful text.
 	*/
	public void hide(String idNode, boolean showConsoleText) {
		// Get the board grid.
		GridPane boardGrid = (GridPane) this.getChildrenUnmodifiable().get(0);
		
		// For every node in the board :
		for(Node node: boardGrid.getChildrenUnmodifiable()) {
			// If the node with the id passed match :
			if(node.getId().equals(idNode)){
				// Hide the barrier.
				node.setOpacity(0);
				
				// Print useful text.
				if(showConsoleText) {
					System.out.println(node.getId() + " now hidden.");
					System.out.println();
				}
			}
		}
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
	        			Label text = new Label(player.getPlayerName());

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
	    	            
	    	            player.setOnDragDetected(new EventHandler<MouseEvent>() {
	    	                public void handle(MouseEvent event) {
	    	                    /* drag was detected, start a drag-and-drop gesture*/
	    	                    /* allow any transfer mode */
	    	                    Dragboard db = player.startDragAndDrop(TransferMode.ANY);
	    	                    
	    	                    /* Put a string on a dragboard */
	    	                    ClipboardContent content = new ClipboardContent();
	    	                    content.putString("s");
	    	                    db.setContent(content);
	    	                    

	    	                    event.consume();
	    	                }
	    	            });
	    	           
					
						// Player added successfully.
						return(true);
					}
				}
			}
		}
		// Case: there is no tile in the board grid which is omega sus.
		// Player could not be added.
		return(false);
	}
	
	/**
	 * While printing helpful text in the console, add a player on a tile.
	 * @param int wantedTileNumber, Number tile on which you add the player.
	 * @param Player player, object of Player class to put on the tile.
	 * @param boolean showConsoleText, show useful text.
	 */	
	public boolean addPlayerTile(int wantedTileNumber, Player player, boolean showConsoleText) {
		// Get the board grid.
		GridPane boardGrid = (GridPane) this.getChildrenUnmodifiable().get(0);
		
		// Set wanted tile id.
		String wantedTileId = "Tile " + wantedTileNumber;
		
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
								
			        			// Print useful text.
			        			if(showConsoleText) {
			        				System.out.println(player.getPlayerName() + " cannot be added in " + nodeList.get(0).getId() + ".\nThere is already someone..." );
			        				System.out.println();
			        			}
			        			
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
	        			Label text = new Label(player.getPlayerName());

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
	                    
	                    player.setOnDragDetected(new EventHandler<MouseEvent>() {
	                        public void handle(MouseEvent event) {
	                            /* drag was detected, start a drag-and-drop gesture*/
	                            /* allow any transfer mode */
	                            Dragboard db = player.startDragAndDrop(TransferMode.ANY);
	                            
	                            /* Put a string on a dragboard */
	                            ClipboardContent content = new ClipboardContent();
	                            content.putString("s");
	                            db.setContent(content);
	                            
	                            event.consume();
	                        }
	                    });
                    
	        			// Print useful text.
	        			if(showConsoleText) {
	        				System.out.println(player.getPlayerName() + " added in " + nodeList.get(0).getId() + ".");
	        				System.out.println();
	        			}
	        			
	        			// Player added successfully.
						return(true);
					}
				}
			}
		}
		// Case: there is no tile in the board grid which is omega sus because tiles are added in the constructor.
		System.out.println("THERE IS NO TILE IN THE BOARD WHAT ARE YOU DOING???");
		System.out.println();

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
	 * While printing helpful text in the console, remove a player from a tile.
	 * @param int wantedTileNumber, number of the tile where the player must be removed.
	 * @param Player player, player that must be removed.
	 * @param boolean showConsoleText, show useful text.
	 */	
	public boolean removePlayerTile(Player player, boolean showConsoleText) {		
		// Get the board grid.
		GridPane boardPane = (GridPane) this.getChildrenUnmodifiable().get(0);
		
		// Get player id.
		String playerId = "Text " + player.getId();
		
		// Get name of the player to remove.
		String playerTextId = "Text " + player.getId();
		
		// For every node in the grid :
		for(Node node: boardPane.getChildrenUnmodifiable()) {
			// If node is the wanted player or wanted player text : 
			if(node.getId().equals(playerTextId)){
				// Delete the text.
				boardPane.getChildren().remove(node);
				
				// Delete the player.
				boardPane.getChildren().remove(player);
				
				// Print useful text.
				if(showConsoleText) {
					System.out.println(playerId + " removed successfully.");
					System.out.println();
				}
				
				// Player removed successfully.
				return(true);
			}	
		}
		// Print useful text.
		if(showConsoleText) {
			System.out.println(playerId + " could not be removed.");
			System.out.println();
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
		String playerName = player.getPlayerName();
		Circle playerShape = (Circle) player;
		Color playerColor = (Color) playerShape.getFill();
		double playerRadius = player.getRadius();
		
		// Remove player from the board. 
		if(canMove(player.getTileId(),"Tile " + newTileNumber)) {
			if(removePlayerTile(player) == true) {
				// player instance was deleted so we have to construct it again... 
				player = new Player(playerName, playerRadius, playerColor);
				
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
	
	/**
	 * While printing helpful text in the console, move a player to another tile.
	 * @param int newTileNumber, number of the tile where the player must be removed.
	 * @param Player player, player that must be moved.
	 * @param boolean showConsoleText, show useful text.
	 */	
	public boolean movePlayerTile(int newTileNumber, Player player, boolean showConsoleText) {
		// Get and save player attributes for later.
		String playerName = player.getPlayerName();
		Circle playerShape = (Circle) player;
		Color playerColor = (Color) playerShape.getFill();
		double playerRadius = player.getRadius();
		
		// Remove player from the board.
		if(removePlayerTile(player) == true) {
			// Player instance was deleted so we have to construct it again...
			player = new Player(playerName, playerRadius, playerColor);

			// Add player on the new tile.
			if(addPlayerTile(newTileNumber, player) == true){
				if(showConsoleText) {
					System.out.println(player.getId() + " moved to Tile" + Integer.toString(newTileNumber) + ".");
					System.out.println();
				}
				// Player removed successfully.
				return(true);	
			}		
		}
		else {
			// Cannot add player.
			// Player could not be moved.
			return(false);
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
	
	public LinkedList<String> dfs(String startVertex) {
		HashMap<String, ArrayList<String>> adjacencyList = getAdjacencyList();
		
		Stack<String> stack = new Stack<>();
		
		LinkedList<String> visited = new LinkedList<>();
		visited.add(startVertex);
		
		LinkedList<String> notVisited = new LinkedList<>();

		ArrayList<String> vertexNeighborsNotVisited = new ArrayList<>() ;
		
		ArrayList<String> vertexNeighbors = adjacencyList.get(startVertex);
		if(vertexNeighbors == null) {
			LinkedList<String> debug = new LinkedList<>();
			return(debug);
		}
	
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
	
	public boolean areConnected(HashMap<String, ArrayList<String>> adjacencyList, String verticeA, String verticeB) {
		return(dfs(verticeA).contains(verticeB));
	}
	
	public boolean areConnected(HashMap<String, ArrayList<String>> adjacencyList, String verticeA, String verticeB, boolean showConsoleText) {
		if(dfs(verticeA).contains(verticeB)){
			if(showConsoleText) {
				System.out.println(verticeA + " is connected to " + verticeB + ".");
				System.out.println();
			}
			return(true);
		}
		else {
			if(showConsoleText) {
				System.out.println(verticeA + " is NOT connected to " + verticeB + ".");
				System.out.println();
			}
		}
		return(false);
	}
	
	
	public boolean isTileOccupied(String TileA) {
		if (nbPlayers == 2)
			return (player1.getTileId().equals(TileA) || player2.getTileId().equals(TileA));
		return (player1.getTileId().equals(TileA) || player2.getTileId().equals(TileA) || player3.getTileId().equals(TileA) ||player4.getTileId().equals(TileA));
	}
	
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
	
	
	public void changeTurn() {
		if(nbPlayers == 2) {
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
}