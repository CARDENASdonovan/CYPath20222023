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
		AnchorPane conteneurGeneral = (AnchorPane) board.getChildrenUnmodifiable().get(0);
		
		System.out.println(conteneurGeneral.getChildren());
		
		for(Node child: conteneurGeneral.getChildrenUnmodifiable()) {
			//System.out.println(child);
			if(child.getId() != null){
				System.out.println(child);
				System.out.println(child.getId());
				child.setVisible(false);
				System.out.println("unabled");
			}
		}
		
		Player player1 = new Player(1,200,300,30,Color.BLUE);
		player1.toFront();
		
		root.getChildren().add(player1);

		
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