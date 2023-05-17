package testQuadriageVisuel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameField extends Node{
	
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
    int acc3 = 0;
    int accSquareId = 1;
    int accBorderTopId = 1;
    int accBorderRightId = 1;
    int accBorderBottomId = 1;
    int accBorderLeftId = 1;
	private AnchorPane root;
	
	public GameField() {
 
        AnchorPane root = new AnchorPane();
       
        // Create board one line per iteration.
        // i must be nbLines * 2 + 1
        // j is nbColumns
        for(int i = 0; i < 19; i++) {
        	for(int j = 0; j < 9; j++) {
        		if(i % 2 == 0) {
        			/*
    		        Rectangle cornerTopLeft = new Rectangle(debutX + (widthCorner + widthSquare) * acc2, debutY + (widthCorner + widthSquare) * acc1, widthCorner, heightCorner);
    		        cornerTopLeft.setFill(Color.RED);
    		        root.getChildren().add(cornerTopLeft);
    		        */
    		        Rectangle borderTop = new Rectangle(debutX + widthCorner + (widthCorner + widthSquare) * acc2, debutY + (widthCorner + widthSquare) * acc1, widthBorder, heightBorder);
    		        borderTop.setFill(Color.GREY);
    		        borderTop.setId("BorderTop" + Integer.toString(accBorderTopId));
    		        accBorderTopId++;
    		        root.getChildren().add(borderTop);
    		        /*
    		        Rectangle cornerTopRight = new Rectangle(debutX + widthCorner + widthSquare + (widthCorner + widthSquare) * acc2, debutY + (widthCorner + widthSquare) * acc1, widthCorner, heightCorner);
    		        cornerTopRight.setFill(Color.RED);
    		        root.getChildren().add(cornerTopRight);	
    		        */
    		        acc2++;
    		        
    			}
        		
    			else {
        			Rectangle borderLeft = new Rectangle(debutX + (widthCorner + widthSquare) * acc2, debutY + heightCorner + (heightSquare + heightCorner) * acc3, heightBorder, widthBorder);
    		        borderLeft.setFill(Color.GREY);
    		        borderLeft.setId("BorderLeft" + Integer.toString(accBorderLeftId));
    		        accBorderLeftId++;
    		        root.getChildren().add(borderLeft);
    		        
    		        Rectangle rect1 = new Rectangle(debutX + widthCorner + (heightSquare + heightCorner) * acc2, debutY + heightCorner + (heightSquare + heightCorner) * acc3, widthSquare, heightSquare);
			        rect1.setFill(Color.WHITE);				        
			        rect1.setId("Square" + Integer.toString(accSquareId));
			        root.getChildren().add(rect1);
			        
			        Text tileNumber = new Text(20 + debutX + widthCorner + (heightSquare + heightCorner) * acc2, 30 + debutY + heightCorner + (heightSquare + heightCorner) * acc3, Integer.toString(accSquareId));
			        tileNumber.setId(Integer.toString(accSquareId));
			        root.getChildren().add(tileNumber);
			        accSquareId++;
			        
			        Rectangle borderRight = new Rectangle(debutX + widthCorner + widthSquare + (heightSquare + heightCorner) * acc2, debutY + heightCorner + (heightSquare + heightCorner) * acc3, heightBorder, widthBorder);
			        borderRight.setFill(Color.GREY);
    		        borderRight.setId("BorderRight" + Integer.toString(accBorderLeftId));
    		        accBorderRightId++;
			        root.getChildren().add(borderRight);
			        	
			        acc2++;
			        
    			}
        	}
        	if(i%2!=0) {
        		acc3++;
        	}
        	if(i%2==0) {
        		acc1++;
        	}
        	acc2 = 0;

        }
        
        System.out.println(root.getChildren().get(0).getClass().getSimpleName());
        ((Shape) root.getChildren().get(16)).setFill(Color.RED);
        
        
        System.out.println(root.getChildren().size());
        for(int i = 0; i < root.getChildren().size(); i++) {
        	if(root.getChildren().get(i).getClass().getSimpleName().equals("Text")) {
        		System.out.println(root.getChildren().get(i).getId());
        		System.out.println(root.getChildren().get(i));
        	}
        }
        Player a = new Player(300);  // Sensé ajouter un cercle mais ne fonctionne pas pour le moment
        root.getChildren().add(a);
        
        Button button = new Button("Poser une barrière"); // Add a button
        AnchorPane.setTopAnchor(button, 0.0);
        AnchorPane.setLeftAnchor(button,570.0 );
        
        button.setOnAction(new EventHandler<ActionEvent>(){
        	@Override
        	public void handle(ActionEvent event) {
        		System.out.println("caca");
        	}
        	
        });
        
        root.getChildren().add(button);
        
        
        
        
        
    }
	
	public AnchorPane getRoot() {
        return root;
    }
	}

