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
		
		/*Adj L = new Adj();
		L.addEdge("Tile22" , "H22");
		L.addEdge("H22", "Tile13");
		L.addEdge("V15", "Tile14");*/
		
		
		AnchorPane root = new AnchorPane();
		
		Board board = new Board(9,9);
		root.getChildren().add(board);		
		//board.hideBarriers(false);

		board.addPlayerTile(1, 15, Color.BEIGE, false);
		board.addPlayerTile(50, 20, Color.BLUE, false);
		//board.movePlayerTile(50, 15, true);
		
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