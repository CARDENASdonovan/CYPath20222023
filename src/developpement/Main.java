package developpement;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.common.base.Splitter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
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
		

		// Main menu creation.
		Button newGameButton = new Button("New Game");
		newGameButton.setPrefWidth(750/3);
		newGameButton.setOnMouseClicked(event ->{
			primaryStage.setScene(scene);
		});
		
		Button loadGameButton = new Button("Load Game");
		loadGameButton.setPrefWidth(750/3);
		
		VBox buttonsVBox = new VBox(newGameButton, loadGameButton);
		buttonsVBox.setAlignment(Pos.CENTER);
		// Create clickable hyperlink.
		Hyperlink linkJavadoc = new Hyperlink();
		linkJavadoc.setText("Open Javadoc");
		linkJavadoc.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		        System.out.println("This link is clicked");
		        File file = new File("H:\\Documents\\GitHub\\CYPath20222023\\doc\\index.html");
		        Desktop desktop = Desktop.getDesktop();
		        try {
					desktop.open(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
		    }
		});
		// linkJavadoc pane to simplify visual alignment in grid.
		HBox linkHBox = new HBox(linkJavadoc);
		linkHBox.setAlignment(Pos.BASELINE_RIGHT);
		
		// Create main menu pane.
		GridPane mainMenuPane = new GridPane();
		mainMenuPane.setMinSize(1200, 750);
		mainMenuPane.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");
		mainMenuPane.setId("mainMenuGrid");
		// Align elements in the center of the grid.
		GridPane.setHalignment(mainMenuPane,HPos.CENTER);
		
		for(int j = 0; j < 3; j++) {
			ColumnConstraints column = new ColumnConstraints(1200/3);
			mainMenuPane.getColumnConstraints().add(column);
		}


		/*
		for(int j = 0; j < 3; j++) {
			
			RowConstraints row = new RowConstraints(750/3);
			mainMenuPane.getRowConstraints().add(row);
		}
		*/
		mainMenuPane.add(buttonsVBox, 1, 1);
		mainMenuPane.add(linkHBox, 2, 2);
		Scene mainMenu = new Scene(mainMenuPane, 1200, 750);
		
        // primaryStage.setScene(scene);
		primaryStage.setScene(mainMenu);
        primaryStage.show();
    }
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
       
    }
}
