

package developpement;


import java.util.ArrayList;
import java.util.HashMap;

import com.google.common.base.Splitter;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{
	
	@Override
    public void start(Stage primaryStage) {	
		AnchorPane root = new AnchorPane();	
	
		
		root.setId("root");
		
		Board board = new Board(300,10,9,9);
		root.getChildren().add(board);
		  
		GridPane boardGrid = (GridPane) board.getChildrenUnmodifiable().get(0);
		
		ChoiceBox<String> nbPlayer = new ChoiceBox<>();
		String[] players = {"2","4"};
		nbPlayer.getItems().addAll(players);
		Button button = new Button("Select the number of players");
		
		button.setOnAction(event ->{
			board.resetGame(nbPlayer);
		});
		
		root.getChildren().addAll(nbPlayer,button);
		
		
        AnchorPane.setTopAnchor(nbPlayer, 40.0);
        AnchorPane.setLeftAnchor(nbPlayer, 10.0);
       
        AnchorPane.setTopAnchor(button, 10.0);
        AnchorPane.setLeftAnchor(button, 10.0);

		board.updateAdjacencyList(boardGrid, true);
		
		Scene scene = new Scene(root, 1200, 750);
		
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
