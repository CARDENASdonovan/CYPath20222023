package testQuadriageVisuel;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;




public class Main extends Application{
	public void start(Stage primaryStage) {
		
		
		 GameField root = new GameField();
		
		 Scene scene = new Scene(root.getRoot(), 650, 650);
		        
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
