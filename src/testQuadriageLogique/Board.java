package testQuadriageLogique;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board extends Region{
	
	 Adj adjList = new Adj();
	 private HashMap<String,ArrayList<String>> hashAdj; 
	
	
	
	public Board(int lineTotalNumber, int columnTotalNumber) { 
		
		
	    final int barrierHorizontalWidth = 70;
	    final int barrierHorizontalHeight = 10;
	    
	    final int barrierVerticalWidth = barrierHorizontalHeight;
	    final int barrierVerticalHeight = barrierHorizontalWidth;
	    
	    final int tileWidth = barrierHorizontalWidth;
	    
	    int acc1 = 0;
	    int acc2 = 0;
	    int acc3 = 0;
	    int accTileId = 1;
	    int accBarrierHorizontal = 1;
	    int accBarrierVertical = 1;
	    
	    final int initialX = 10;
	    final int initialY = 10;
	    
	    final int initialTileX = initialX + barrierHorizontalHeight;
	    final int initialTileY = initialY + barrierHorizontalHeight;
	    
	   
	    
	    Color barrierHorizontalColor = Color.GREY;
	    Color barrierVerticalColor = Color.GREY;
	    Color tileColor = Color.LIGHTCORAL;
	    
	    AnchorPane boardPane = new AnchorPane();
	    boardPane.setId("BoardPane");
	    
		for(int lineNumber = 0; lineNumber < lineTotalNumber*2 + 1; lineNumber++) {
        	for(int columnNumber = 0; columnNumber < columnTotalNumber; columnNumber++) {
        		int tileX = initialTileX + (tileWidth + barrierHorizontalHeight) * acc2;
        	    int tileY = initialTileY + (tileWidth + barrierHorizontalHeight) * acc3;
        		
        		int barrierHorizontalX = initialX + barrierHorizontalHeight + (barrierHorizontalHeight + tileWidth) * acc2;
        	    int barrierHorizontalY = initialY + (barrierHorizontalHeight + tileWidth) * acc1;
        	    
        	    int barrierVerticalX = initialX + (barrierHorizontalHeight + tileWidth) * acc2;
        	    int barrierVerticalY = initialY + barrierHorizontalHeight + (tileWidth + barrierHorizontalHeight) * acc3;
        	    
        	    int barrierVerticalLastColumnX = initialX + barrierHorizontalHeight + tileWidth + (tileWidth + barrierHorizontalHeight) * acc2;
        	    int barrierVerticalLastColumnY = initialY + barrierHorizontalHeight + (tileWidth + barrierHorizontalHeight) * acc3;
        	    
        		if(lineNumber % 2 == 0) {
        			AnchorPane barrierHPane = new AnchorPane();
        			barrierHPane.setId("BarrierHPane" + Integer.toString(accBarrierHorizontal));
        			
        			BarrierHorizontal barrierHorizontal = new BarrierHorizontal(accBarrierHorizontal, barrierHorizontalX, barrierHorizontalY, barrierHorizontalWidth, barrierHorizontalHeight, barrierHorizontalColor);
        			barrierHPane.getChildren().add(barrierHorizontal);
        			
        			if(accBarrierHorizontal > 9 && accBarrierHorizontal < 82) {//if the barrier is IN the board, set adjacents tiles id for current barrier
        				barrierHorizontal.setIdTile1("TilePane"+Integer.toString(accBarrierHorizontal-9));
        				barrierHorizontal.setIdTile2("TilePane"+Integer.toString(accBarrierHorizontal));
        			}
        			/*verif
        			System.out.println("Barrier Horizontal "+accBarrierHorizontal+" :");
        			System.out.println("Id Tile 1 : "+barrierHorizontal.getIdTile1());
        			System.out.println("Id Tile 2 : "+barrierHorizontal.getIdTile2()+"\n\n\n");*/
        			
        			
        			barrierHorizontal.addBarrierTextId("H" + Integer.toString(accBarrierHorizontal), Color.RED, 0, barrierHPane);
        			boardPane.getChildren().add(barrierHPane);
        			
        			accBarrierHorizontal++;

    		        acc2++;
    			}
        		
    			else {
    				AnchorPane barrierVPane = new AnchorPane();
    				barrierVPane.setId("BarrierVPane" + Integer.toString(accBarrierVertical));
    				
        			BarrierVertical barrierVertical = new BarrierVertical(accBarrierVertical, barrierVerticalX, barrierVerticalY, barrierVerticalWidth, barrierVerticalHeight, barrierVerticalColor);
        			barrierVPane.getChildren().add(barrierVertical);     
        			
        			if(accBarrierVertical % 10 != 0  && (accBarrierVertical-1) % 10 != 0) {//if the barrier is IN the board, set adjacents tiles id for current barrier
        				barrierVertical.setIdTile1("TilePane"+Integer.toString(accBarrierVertical-1-((int) accBarrierVertical/10)));
        				barrierVertical.setIdTile2("TilePane"+Integer.toString(accBarrierVertical-((int) accBarrierVertical/10)));
        			}
        			/*verif
        			System.out.println("Barrier Vertical "+accBarrierVertical+" :");
        			System.out.println("Id Tile 1 : "+barrierVertical.getIdTile1());
        			System.out.println("Id Tile 2 : "+barrierVertical.getIdTile2()+"\n\n\n");*/
        			
        			barrierVertical.addBarrierTextId("V" + Integer.toString(accBarrierVertical), Color.BLUE, -90, barrierVPane);
        			boardPane.getChildren().add(barrierVPane);
        			
        			accBarrierVertical++;
        			
        			AnchorPane tilePane = new AnchorPane();
        			tilePane.setId("TilePane" + Integer.toString(accTileId));
        			
    		        Tile tile = new Tile(accTileId, tileX, tileY, tileWidth, tileColor);
    		        tile.toBack();
    		        tilePane.getChildren().add(tile);
    		        
    		        tile.addTileTextId(Integer.toString(accTileId), tilePane);
    		        
    		        boardPane.getChildren().add(tilePane);
			        
			        accTileId++;
			        	
			        acc2++;
			        
			        if(columnNumber == columnTotalNumber - 1) {
			        	AnchorPane lastBarrierPane = new AnchorPane();
			        	lastBarrierPane.setId("BarrierVPane" + Integer.toString(accBarrierHorizontal));
			        	
	        			BarrierVertical barrierVerticalLastColumn = new BarrierVertical(accBarrierVertical, barrierVerticalLastColumnX, barrierVerticalLastColumnY, barrierVerticalWidth, barrierVerticalHeight, barrierVerticalColor);
	        			lastBarrierPane.getChildren().add(barrierVerticalLastColumn);
	        			
	        			barrierVerticalLastColumn.addBarrierTextId("V" + Integer.toString(accBarrierVertical), Color.BLUE, -90, barrierVPane);
	        			
	        			boardPane.getChildren().add(lastBarrierPane);
	        			accBarrierVertical++;
	        		}
			    }
        	}
        	
        	if(lineNumber % 2 != 0) {
        		acc3++;
        	}
        	
        	if(lineNumber % 2 == 0) {
        		acc1++;
        	}        	
        	acc2 = 0;
        }
		this.getChildren().add(boardPane);
		
		
		Adj L = new Adj();
		 
			
			for(Node x : boardPane.getChildren()) {
				if(x.getId().contains("Barrier")){//check for each barrier in board
					System.out.println(x.getId());
					Barrier barrierX =  (Barrier) ((AnchorPane) x).getChildren().get(0);//we do 2 lines at once to not use 2 variable 
					if(!( barrierX.getIdTile1().equals("") || barrierX.getIdTile2().equals("") )){//if the barrier have both IdTiles not empty
						L.addEdge(barrierX.getIdTile1(),barrierX.getIdTile2());//we add vertices to the edge matrice
					}
				}
			}
			
			this.adjList =  L;
			this.hashAdj = L.getAdjacencyList();
	}
	
	
	//getter adjList
	protected Adj getAdjList() { return this.adjList;};
	
	
	protected void addChildren(Node node) {
		this.getChildren().add(node);
	}
	
	protected void hideBarriers(boolean showCommandText) {
		AnchorPane collectionBoardNodes = (AnchorPane) this.getChildrenUnmodifiable().get(0);
		
		for(Node child: collectionBoardNodes.getChildrenUnmodifiable()) {
			if(child.getId().contains("Barrier")){
				child.setVisible(false);
				if(showCommandText == true) {
					System.out.println(child);
					System.out.println(child.getId());
					System.out.println("unabled");
				}
			}
		}
	}
	
	protected boolean addPlayerTile(int tileIdNumber, int playerIdNumber, Color color, boolean showCommandText) {
		
		AnchorPane collectionBoardNodes = (AnchorPane) this.getChildrenUnmodifiable().get(0);
		
		for(Node child: collectionBoardNodes.getChildrenUnmodifiable()) {
			if(child.getId().equals("TilePane" + Integer.toString(tileIdNumber))){
				AnchorPane anchorPaneTile = (AnchorPane) child;
				
				for(Node child1: anchorPaneTile.getChildrenUnmodifiable()) {
					if(child1 instanceof Player) {
						System.out.println(child1.getId() + " is already there !");
						return(false);
					}
				}
				
				Tile tile = (Tile) anchorPaneTile.getChildren().get(0);
				
				final int centerX = (int) (tile.getX() + tile.getWidth()/2);
				final int centerY = (int) (tile.getY() + tile.getWidth()/2);
				final int radius = (int) (tile.getWidth()/2 - 10);
				Player player = new Player(playerIdNumber, centerX, centerY, radius, color);
				anchorPaneTile.getChildren().add(player);
				
				player.addPlayerTextId(Integer.toString(playerIdNumber), anchorPaneTile);
				
				if(showCommandText == true) {
					System.out.println(child);
					System.out.println(child.getId());
					System.out.println(tile);
				}
				System.out.println(player.getId() + " added.");
				System.out.println("\n");
				return(true);
			}
		}
		// Case there are no TilePane in board collection.
		return(false);
	}
	
	protected boolean removePlayerTile(int playerIdNumber, boolean showCommandText) {
		AnchorPane collectionBoard = (AnchorPane) this.getChildrenUnmodifiable().get(0);
		
		for(Node child: collectionBoard.getChildrenUnmodifiable()) {
			
			if(child.getId().contains("TilePane")){
				AnchorPane tilePane = (AnchorPane) child;
				
				for(Node child1: tilePane.getChildren()) {
					if(child1.getId().equals("Player" + Integer.toString(playerIdNumber))) {
						tilePane.getChildren().remove(2);
						tilePane.getChildren().remove(2);
						System.out.println(child1);
						System.out.println(child1.getId().equals("Player" + Integer.toString(playerIdNumber)) + "removed.");
						System.out.println("\n");
						return(true);
					}	
				}
			}
		}
		return(false);
	}
	
	private boolean checkAjdMoveLegal(int playerIdNumber,int newTileIdNumber) {
		if(hashAdj.containsKey("TilePane"+ Integer.toString(playerIdNumber))) {
			System.out.println(hashAdj.get("TilePane"+ Integer.toString(playerIdNumber)));
		}
	}
	
	protected boolean movePlayerTile(int newTileIdNumber, int playerIdNumber) {
		
		if(addPlayerTile(newTileIdNumber, playerIdNumber, Color.BEIGE, false) == true) {
			//we're checking if we can move on the tile by looking at the adjList, in our hashmap, we got something like {key, value} where value is an arraylist, so we check key==playerIdNumber and then if value contain newtilenumber
			//if(this.adjList.getAdjacencyList())
			if(removePlayerTile(playerIdNumber, false) == true){
				System.out.println("Player" + playerIdNumber + " moved to Tile" + Integer.toString(newTileIdNumber) + ".");
				System.out.println("\n");
				return(true);	
			}		
		}
		else {
			System.out.println("Cannot move there... Try again.");
			System.out.println("\n");
			return(false);
		}
		return(false);
	}
}
