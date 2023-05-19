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
		
		Adj L = new Adj();
		
		
		AnchorPane root = new AnchorPane();
		
		Board board = new Board(9,9);
		
		AnchorPane boardPane = (AnchorPane) board.getChildrenUnmodifiable().get(0);
		
		for(Node x : boardPane.getChildren()) {
			if(x.getId().contains("Barrier")){//check for each barrier in board
				System.out.println(x.getId());
				Barrier barrierX =  (Barrier) ((AnchorPane) x).getChildren().get(0);//we do 2 lines at once to not use 2 variable 
				if(!( barrierX.getIdTile1().equals("") || barrierX.getIdTile2().equals("") )){//if the barrier have both IdTiles not empty
					L.addEdge(barrierX.getIdTile1(),barrierX.getIdTile2());//we add vertices to the edge matrice
				}
			}
		}
		
		System.out.println(L);
		
		root.getChildren().add(board);		
		//board.hideBarriers(false);

		//board.addPlayerTile(1, 15, Color.BEIGE, false);
		//board.addPlayerTile(50, 20, Color.BLUE, false);
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