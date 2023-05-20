package developpement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board extends Region{
	private HashMap<String, ArrayList<String>> adjacencyList = new HashMap<>();
	
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
		hideAllBarrier();	
		updateAdjacencyList(boardPane);
	}	
	
	
	// Methods: Change Pane visibility.
	protected void show(String idPane) {
		AnchorPane collectionBoardNodes = (AnchorPane) this.getChildrenUnmodifiable().get(0);
		
		for(Node pane: collectionBoardNodes.getChildrenUnmodifiable()) {
			if(pane.getId().equals(idPane)){
				pane.setVisible(true);
			}
		}
	}
	
	protected void show(String idPane, boolean showCommandText) {
		AnchorPane collectionBoardNodes = (AnchorPane) this.getChildrenUnmodifiable().get(0);
		
		for(Node pane: collectionBoardNodes.getChildrenUnmodifiable()) {
			if(pane.getId().equals(idPane)){
				pane.setVisible(true);
				if(showCommandText == true) {
					System.out.println(pane + pane.getId() + " now visible.");
				}
			}
		}
	}
	
	protected void hide(String idPane) {
		AnchorPane collectionBoardNodes = (AnchorPane) this.getChildrenUnmodifiable().get(0);
		
		for(Node pane: collectionBoardNodes.getChildrenUnmodifiable()) {
			if(pane.getId().equals(idPane)){
				pane.setVisible(false);
			}
		}
	}
	
	protected void hide(String idPane, boolean showCommandText) {
		AnchorPane collectionBoardNodes = (AnchorPane) this.getChildrenUnmodifiable().get(0);
		
		for(Node pane: collectionBoardNodes.getChildrenUnmodifiable()) {
			if(pane.getId().equals(idPane)){
				pane.setVisible(false);
				if(showCommandText) {
					System.out.println(pane + pane.getId() + " now hidden.");
				}
			}
		}
	}
	
	protected void hideAllBarrier() {
		AnchorPane collectionBoardNodes = (AnchorPane) this.getChildrenUnmodifiable().get(0);
		
		for(Node pane: collectionBoardNodes.getChildrenUnmodifiable()) {
			if(pane.getId().contains("Barrier")){
				pane.setVisible(false);
			}
		}
	}
	
	protected void hideAllBarrier(boolean showCommandText) {
		AnchorPane collectionBoardNodes = (AnchorPane) this.getChildrenUnmodifiable().get(0);
		
		for(Node pane: collectionBoardNodes.getChildrenUnmodifiable()) {
			if(pane.getId().contains("Barrier")){
				pane.setVisible(false);
				if(showCommandText == true) {
					System.out.println(pane + pane.getId() + " now hidden.");
				}
			}
		}
	}
	
	protected boolean addPlayerTile(int tileIdNumber, int playerIdNumber, Color color) {
		AnchorPane collectionBoardNodes = (AnchorPane) this.getChildrenUnmodifiable().get(0);
		
		for(Node child: collectionBoardNodes.getChildrenUnmodifiable()) {
			if(child.getId().equals("TilePane" + Integer.toString(tileIdNumber))){
				AnchorPane anchorPaneTile = (AnchorPane) child;
				
				for(Node child1: anchorPaneTile.getChildrenUnmodifiable()) {
					if(child1 instanceof Player) {
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

				return(true);
			}
		}
		// Case there are no TilePane in board collection.
		return(false);
	}
	
	
	// Methods: Add, remove, move Player.
	protected boolean addPlayerTile(int tileIdNumber, int playerIdNumber, Color color, boolean showConsoleText) {
		AnchorPane collectionBoardNodes = (AnchorPane) this.getChildrenUnmodifiable().get(0);
		
		for(Node child: collectionBoardNodes.getChildrenUnmodifiable()) {
			if(child.getId().equals("TilePane" + Integer.toString(tileIdNumber))){
				AnchorPane anchorPaneTile = (AnchorPane) child;
				
				for(Node child1: anchorPaneTile.getChildrenUnmodifiable()) {
					if(child1 instanceof Player) {
						if(showConsoleText) {
							System.out.println(child1.getId() + " is already there !");
						}
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
				
				if(showConsoleText == true) {
					System.out.println(child);
					System.out.println(child.getId());
					System.out.println(tile);
					System.out.println(player.getId() + " added.");
					System.out.println("\n");
				}
				return(true);
			}
		}
		// Case there are no TilePane in board collection.
		return(false);
	}
	
	protected boolean removePlayerTile(int playerIdNumber) {
		AnchorPane collectionBoard = (AnchorPane) this.getChildrenUnmodifiable().get(0);
		
		for(Node child: collectionBoard.getChildrenUnmodifiable()) {
			
			if(child.getId().contains("TilePane")){
				AnchorPane tilePane = (AnchorPane) child;
				
				for(Node child1: tilePane.getChildren()) {
					if(child1.getId().equals("Player" + Integer.toString(playerIdNumber))) {
						tilePane.getChildren().remove(2);
						tilePane.getChildren().remove(2);
						return(true);
					}	
				}
			}
		}
		return(false);
	}
	
	protected boolean removePlayerTile(int playerIdNumber, boolean showConsoleText) {
		AnchorPane collectionBoard = (AnchorPane) this.getChildrenUnmodifiable().get(0);
		
		for(Node child: collectionBoard.getChildrenUnmodifiable()) {
			
			if(child.getId().contains("TilePane")){
				AnchorPane tilePane = (AnchorPane) child;
				
				for(Node child1: tilePane.getChildren()) {
					if(child1.getId().equals("Player" + Integer.toString(playerIdNumber))) {
						tilePane.getChildren().remove(2);
						tilePane.getChildren().remove(2);
						if(showConsoleText) {
							System.out.println(child1);
							System.out.println(child1.getId().equals("Player" + Integer.toString(playerIdNumber)) + "removed.");
							System.out.println("\n");
						}
						return(true);
					}	
				}
			}
		}
		return(false);
	}
	
	protected boolean movePlayerTile(int newTileIdNumber, int playerIdNumber) {
		if(addPlayerTile(newTileIdNumber, playerIdNumber, Color.BEIGE, false) == true) {
			if(removePlayerTile(playerIdNumber, false) == true){
				return(true);	
			}		
		}
		else {
			return(false);
		}
		return(false);
	}
	
	protected boolean movePlayerTile(int newTileIdNumber, int playerIdNumber, boolean showConsoleText) {
		if(addPlayerTile(newTileIdNumber, playerIdNumber, Color.BEIGE, false) == true) {
			if(removePlayerTile(playerIdNumber, false) == true){
				if(showConsoleText) {
					System.out.println("Player" + playerIdNumber + " moved to Tile" + Integer.toString(newTileIdNumber) + ".");
					System.out.println("\n");
				}
				return(true);	
			}		
		}
		else {
			if(showConsoleText) {
				System.out.println("Cannot move there... Try again.");
				System.out.println("\n");
			}
			return(false);
		}
		return(false);
	}

	
	// Methods: Initialize, modify and special uses for the adjacency list of the board.
	protected HashMap<String, ArrayList<String>> getAdjacencyList(){
		return this.adjacencyList;		
	}
	
	protected void setAdjacencyList(HashMap<String, ArrayList<String>> newAdjacencyList){
		this.adjacencyList = newAdjacencyList;
	}
	
	protected HashMap<String, ArrayList<String>> createAdjacencyList(AnchorPane boardPane){
		HashMap<String, ArrayList<String>> newAdjacencyList = new HashMap<>();
		for(Node paneNode : boardPane.getChildren()) {
			if(paneNode.getId().contains("Barrier")){//check for each barrier in board
				Barrier barrierX =  (Barrier) ((AnchorPane) paneNode).getChildren().get(0);//we do 2 lines at once to not use 2 variable
				if(!((barrierX.getIdTile1().equals("") || barrierX.getIdTile2().equals(""))) && !paneNode.isVisible() ){//if the barrier have both IdTiles not empty
					addEdge(newAdjacencyList, barrierX.getIdTile1(),barrierX.getIdTile2());//we add vertices to the edge matrix
				}
			}
		}
		return(newAdjacencyList);
	}
	
	protected void updateAdjacencyList(AnchorPane boardPane){
		setAdjacencyList(createAdjacencyList(boardPane));
		System.out.println(getAdjacencyList());	
	}
	
	protected boolean addEdge(HashMap<String, ArrayList<String>> adjacencyList, String idEdge1, String idEdge2) {
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
	
	protected boolean addEdge(HashMap<String, ArrayList<String>> adjacencyList, String idEdge1, String idEdge2, boolean showConsoleText) {
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
				System.out.println("Cr�ation de : " + idEdge1 + b);
			}
			adjacencyList.put(idEdge2, c);
			if(showConsoleText) {
				System.out.println("Cr�ation de : " + idEdge2 + c);
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
	
	protected LinkedList<String> dfs(String startVertex) {
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
	
	protected LinkedList<String> dfs(String startVertex, boolean showConsoleText) {
		HashMap<String, ArrayList<String>> adjacencyList = getAdjacencyList();
		
		Stack<String> stack = new Stack<>();
		
		LinkedList<String> visited = new LinkedList<>();
		visited.add(startVertex);
		
		LinkedList<String> notVisited = new LinkedList<>();

		ArrayList<String> vertexNeighborsNotVisited = new ArrayList<>() ;
		
		ArrayList<String> vertexNeighbors = adjacencyList.get(startVertex);
		if(showConsoleText) {
			System.out.println("vertexNeighbors: " + vertexNeighbors);
		}
		
		for(String vertexName : vertexNeighbors) {
			if(!visited.contains(vertexName) && !stack.contains(vertexName)) {
				notVisited.add(vertexName);
			}
		}
		if(showConsoleText) {
			System.out.println("\n");
			System.out.println("visited START: " + visited);
			System.out.println("stack START: " + stack);
		}
		
		stack.addAll(notVisited);
	
		while(stack.size() > 0) {
			if(showConsoleText) {
				System.out.println("\n");
				System.out.println("DFS Start");
			}
			
			String stackFirstElement = stack.get(0);
			vertexNeighborsNotVisited.clear();
			
			if(!visited.contains(stackFirstElement)) {
				visited.add(stackFirstElement);
				vertexNeighbors = adjacencyList.get(stackFirstElement);
				
				if(showConsoleText) {
					System.out.println("visited: " + visited);
					System.out.println("vertexNeighbors: " + vertexNeighbors);
				}
				
				for(String vertex : vertexNeighbors) {
					if(!visited.contains(vertex) && !stack.contains(vertex)) {
						vertexNeighborsNotVisited.add(vertex);
					}		
				}
				if(showConsoleText) {
					System.out.println("vertexNeighborsNotVisited: " + vertexNeighborsNotVisited);
				}
				
				stack.addAll(vertexNeighborsNotVisited);
			}		
			stack.remove(0);
			if(showConsoleText) {
				System.out.println("visited FINAL: " + visited);
				System.out.println("stack FINAL: " + stack);
			}
		}
		return(visited);
	}
	
	protected boolean areConnected(HashMap<String, ArrayList<String>> adjacencyList, String verticeA, String verticeB) {
		return(dfs(verticeA).contains(verticeB));
	}
	
	protected boolean areConnected(HashMap<String, ArrayList<String>> adjacencyList, String verticeA, String verticeB, boolean showConsoleText) {
		if(dfs(verticeA).contains(verticeB)){
			if(showConsoleText) {
				System.out.println(verticeA + " is connected to " + verticeB); 
			}
			return(true);
		}
		return(false);
	}
}