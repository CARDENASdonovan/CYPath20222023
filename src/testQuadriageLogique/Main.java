package testQuadriageLogique;

import java.util.TreeMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{
	
	@Override
    public void start(Stage primaryStage) {
		
		AnchorPane root = new AnchorPane();
		
		Tile a = new Tile(1, 0, 0, Color.RED);
		//a.getChildrenUnmodifiable().add(root);
		
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