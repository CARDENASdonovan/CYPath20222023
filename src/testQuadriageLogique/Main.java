package testQuadriageLogique;

import java.util.Iterator;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{
	
	@Override
    public void start(Stage primaryStage) {

		
		
		AnchorPane root = new AnchorPane();
		
		
		Board board = new Board(9,9);
	
		
		
		root.getChildren().add(board);		
		//board.hideBarriers(false);
		
		System.out.println(board.getAdjList());

		board.addPlayerTile(1, 15, Color.BEIGE, false);
		board.addPlayerTile(50, 20, Color.BLUE, false);
		board.movePlayerTile(49, 15, true);
		
		Scene scene = new Scene(root, 900, 750);
		
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