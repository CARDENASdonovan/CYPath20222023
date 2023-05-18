package testQuadriageLogique;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class Board extends Region{
	public Board(int lineTotalNumber, int columnTotalNumber) {    
	    final int barrierHorizontalWidth = 70;
	    final int barrierHorizontalHeight = 10;
	    
	    final int barrierVerticalWidth = barrierHorizontalHeight;
	    final int barrierVerticalHeight = barrierHorizontalWidth;
	    
	    final int tileWidth = barrierHorizontalWidth;
	    
	    int acc1 = 0;
	    int acc2 = 0;
	    int acc3 = 0;
	    int accSquareId = 1;
	    int accBarrierHorizontal = 1;
	    int accBarrierVertical = 1;
	    
	    final int initialX = 10;
	    final int initialY = 10;
	    
	    final int initialTileX = initialX + barrierHorizontalHeight;
	    final int initialTileY = initialY + barrierHorizontalHeight;
	    
	    Color barrierHorizontalColor = Color.GREY;
	    Color barrierVerticalColor = Color.GREY;
	    Color tileColor = Color.WHITE;
	    
	    AnchorPane boardPane = new AnchorPane();
	    
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
        			AnchorPane barrierPane = new AnchorPane();
        			barrierPane.setId(Integer.toString(accBarrierHorizontal));
        			
        			BarrierHorizontal barrierHorizontal = new BarrierHorizontal(accBarrierHorizontal, barrierHorizontalX, barrierHorizontalY, barrierHorizontalWidth, barrierHorizontalHeight, barrierHorizontalColor);
        			barrierPane.getChildren().add(barrierHorizontal);
        			
        			barrierHorizontal.addBarrierText(barrierHorizontal, "H" + Integer.toString(accBarrierHorizontal), Color.RED, 0, barrierPane);
        			
        			boardPane.getChildren().add(barrierPane);
        			
        			accBarrierHorizontal++;

    		        acc2++;
    			}
        		
    			else {
    				AnchorPane barrierPane = new AnchorPane();
    				
        			BarrierVertical barrierVertical = new BarrierVertical(accBarrierVertical, barrierVerticalX, barrierVerticalY, barrierVerticalWidth, barrierVerticalHeight, barrierVerticalColor);
        			barrierPane.getChildren().add(barrierVertical);      			
        			
        			barrierVertical.addBarrierText(barrierVertical, "V" + Integer.toString(accBarrierVertical), Color.BLUE, -90, barrierPane);
        			
        			boardPane.getChildren().add(barrierPane);
        			accBarrierVertical++;
        			
        			AnchorPane tilePane = new AnchorPane();
        			
    		        Tile tile = new Tile(accSquareId, tileX, tileY, tileWidth, tileColor);
    		        tile.toBack();
    		        tilePane.getChildren().add(tile);
    		        
    		        tile.addTileText(tile, Integer.toString(accSquareId), tilePane);
    		        
    		        boardPane.getChildren().add(tilePane);
			        
			        accSquareId++;
			        	
			        acc2++;
			        
			        if(columnNumber == columnTotalNumber - 1) {
			        	AnchorPane lastBarrierPane = new AnchorPane();
			        	
	        			BarrierVertical barrierVerticalLastColumn = new BarrierVertical(accBarrierVertical, barrierVerticalLastColumnX, barrierVerticalLastColumnY, barrierVerticalWidth, barrierVerticalHeight, barrierVerticalColor);
	        			barrierPane.getChildren().add(barrierVerticalLastColumn);
	        			
	        			barrierVerticalLastColumn.addBarrierText(barrierVerticalLastColumn, "V" + Integer.toString(accBarrierVertical), Color.BLUE, -90, barrierPane);
	        			
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
	}
	
	protected void addChildren(Node node) {
		this.getChildren().add(node);
	}
}
