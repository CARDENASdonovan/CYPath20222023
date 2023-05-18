package testQuadriageLogique;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{
	
	@Override
    public void start(Stage primaryStage) {
		
		AnchorPane root = new AnchorPane();
		
		Board board = new Board(9,9);
		root.getChildren().add(board);
		
		System.out.println(board.getChildrenUnmodifiable());

		Player player1 = new Player(1,200,300,30,Color.YELLOW);
		player1.toFront();
		
		root.getChildren().add(player1);
		
		System.out.println(root.getChildren());
		
		
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