package developpement;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{
	
	@Override
    public void start(Stage primaryStage) {	
		AnchorPane root = new AnchorPane();	
		root.setId("root");
		
		Board board = new Board();
		root.getChildren().add(board);
		
		GridPane boardGrid = (GridPane) board.getChildrenUnmodifiable().get(0);
		board.show("Barrier Horizontal 47");
		board.show("Barrier Horizontal 56");
		board.show("Barrier Vertical 52");
		board.show("Barrier Vertical 53");
		
		board.updateAdjacencyList(boardGrid, true);
		
		board.areConnected(board.getAdjacencyList(), "Tile1", "Tile47", true);
		
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