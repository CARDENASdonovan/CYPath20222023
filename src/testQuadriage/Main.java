package testQuadriage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Main extends Application {

	String topWidth = "0";
	String rightWidth = "0";
	String bottomWidth = "0";
	String leftWidth = "0";
	
    int debutX = 10;
    int debutY = 10;
    
    int widthCorner = 10;
    int heightCorner = widthCorner;
    
    int widthBorder = 50;
    int heightBorder = widthCorner;
    
    int widthSquare = widthBorder;
    int heightSquare = widthSquare;
    
    int acc1 = 0;
    int acc2 = 0;
    int accSquareId = 1;
    int accBorderTopId = 1;
    int accBorderRightId = 1;
    int accBorderBottomId = 1;
    int accBorderLeftId = 1w;
	
    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
       
        for(int j = 0; j < 10; j++) {        	
        	for(int i = 0; i < 21; i++) {
        		if(i == 0) {
        			acc1 = 0;
        			acc2 = 0;
        		}
        		
        		if(j == 0) {
        			if(i % 2 == 0) {
        				
	    		        Rectangle cornerTopLeft = new Rectangle(debutX, debutY + (heightCorner + heightSquare) * acc1, widthCorner, heightCorner);
	    		        cornerTopLeft.setFill(Color.RED);
	    		        root.getChildren().add(cornerTopLeft);
	    		        
	    		        Rectangle borderTop = new Rectangle(debutX + widthCorner, debutY + (heightCorner + heightSquare) * acc1, widthBorder, heightBorder);
	    		        borderTop.setFill(Color.BLACK);
	    		        borderTop.setId("BorderTop" + Integer.toString(accBorderTopId));
	    		        accBorderTopId++;
	    		        root.getChildren().add(borderTop);
	    		        
	    		        Rectangle cornerTopRight = new Rectangle(debutX + widthCorner + widthSquare, debutY + (heightCorner + heightSquare) * acc1, widthCorner, heightCorner);
	    		        cornerTopRight.setFill(Color.RED);
	    		        root.getChildren().add(cornerTopRight);	
	    		        
	    		        acc1++;
        			}
        		
        			else {
	        			Rectangle borderLeft = new Rectangle(debutX, debutY + heightCorner + (heightSquare + heightCorner) * acc2, heightBorder, widthBorder);
	    		        borderLeft.setFill(Color.BLACK);
	    		        borderLeft.setId("BorderLeft" + Integer.toString(accBorderLeftId));
	    		        accBorderLeftId++;
	    		        root.getChildren().add(borderLeft);
	    		        
	    		        Rectangle rect1 = new Rectangle(debutX + widthCorner, debutY + heightCorner + (heightSquare + heightCorner) * acc2, widthSquare, heightSquare);
				        rect1.setFill(Color.WHITE);				        
				        rect1.setId("Square" + Integer.toString(accSquareId));
				        accSquareId++;
				        root.getChildren().add(rect1);
				        System.out.println(rect1.getId());
				        
				        Rectangle borderRight = new Rectangle(debutX + widthCorner + widthSquare, debutY + heightCorner + (heightSquare + heightCorner) * acc2, heightBorder, widthBorder);
				        borderRight.setFill(Color.BLACK);
	    		        borderRight.setId("BorderRight" + Integer.toString(accBorderLeftId));
	    		        accBorderRightId++;
				        root.getChildren().add(borderRight);
			        
				        acc2++;
        			}
        		}
        		
        		else {
    				if(i % 2 == 0) {
	    		        Rectangle borderTop = new Rectangle(debutX + (widthCorner + widthSquare + widthCorner) + (widthBorder + widthCorner) * (j-1), debutY + (heightCorner + heightSquare) * acc1, widthBorder, heightBorder);
	    		        borderTop.setFill(Color.BLACK);
	    		        borderTop.setId("BorderTop" + Integer.toString(accBorderTopId));
	    		        accBorderTopId++;
	    		        root.getChildren().add(borderTop);
	    		        
	    		        Rectangle cornerTopRight = new Rectangle(debutX + (widthCorner + widthSquare + widthCorner + widthBorder) + (widthCorner + widthBorder) * (j-1), debutY + (heightCorner + heightSquare) * acc1, widthCorner, heightCorner);
	    		        cornerTopRight.setFill(Color.RED);
	    		        root.getChildren().add(cornerTopRight);	
	    		        
	    		        acc1++;
    				}
    				
    				else {
	    		        Rectangle rect1 = new Rectangle(debutX + (widthCorner + widthSquare + widthCorner) + (widthSquare + widthCorner) * (j-1), debutY + heightCorner + (heightSquare + heightCorner) * acc2, widthSquare, heightSquare);
				        rect1.setFill(Color.WHITE);
				        rect1.setId(Integer.toString(accSquareId));
				        accSquareId++;
				        root.getChildren().add(rect1);
				        System.out.println(rect1.getId());
				        
				        Rectangle borderRight = new Rectangle(debutX + (widthCorner + widthSquare + widthCorner + widthBorder) + (widthCorner + widthBorder) * (j-1), debutY + heightCorner + (heightSquare + heightCorner) * acc2, heightBorder, widthBorder);
				        borderRight.setFill(Color.BLACK);
	    		        borderRight.setId("BorderRight" + Integer.toString(accBorderLeftId));
	    		        accBorderRightId++;
				        root.getChildren().add(borderRight);
			        
				        acc2++;
    				}
        		}
        	}
        }
        
        System.out.println(root.getChildren());
        ((Shape) root.getChildren().get(16)).setFill(Color.RED);
        
        Scene scene = new Scene(root, 650, 650);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
