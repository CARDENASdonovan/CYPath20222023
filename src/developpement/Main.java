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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application{
	
	
	@Override
    public void start(Stage primaryStage) throws Exception {	
		mainMenu(500,400, primaryStage);
    }
	
	public void mainMenu(double width, double height, Stage stage) throws IOException {
	// Logo
		ImageView imgCyLogo = new ImageView("H:\\Documents\\GitHub\\CYPath20222023\\images\\CY_Tech.png");
		imgCyLogo.setFitWidth(width/3);
		imgCyLogo.setPreserveRatio(true);
		imgCyLogo.setSmooth(true);
		imgCyLogo.setCache(true);
		HBox imgCyLogoBox = new HBox(imgCyLogo);
		imgCyLogoBox.setAlignment(Pos.TOP_LEFT);
	// Title
		Label labelCyPath = new Label("CY Path");
		labelCyPath.setFont(new Font("Impact", 24));
		Label labelYear = new Label("2022 - 2023");
		labelYear.setFont(new Font("Impact", 24));
		
		VBox labelCyPathBox = new VBox(labelCyPath,labelYear);
		labelCyPathBox.setAlignment(Pos.CENTER);
		
	// Button new game.
		Button buttonNewGame = new Button("New Game");
		buttonNewGame.setPrefWidth(height/3);
		// Event.
		buttonNewGame.setOnMouseClicked(event ->{
			game(stage);
		});
		
		Button buttonLoadGame = new Button("Load Game");
		buttonLoadGame.setPrefWidth(height/3);
		
		VBox buttonsVBox = new VBox(buttonNewGame, buttonLoadGame);
		buttonsVBox.setAlignment(Pos.CENTER);
		
		Label labelAuthor1 = new Label("Cardenas Donovan");
		Label labelAuthor2 = new Label("Guenneau Romain");
		Label labelAuthor3 = new Label("Johnson Alexandre");
		Label labelAuthor4 = new Label("Machnik Adrien");
		Label labelAuthor5 = new Label("Ruellan Baptiste");
		VBox labelAuthorsBox = new VBox(labelAuthor1, labelAuthor2, labelAuthor3, labelAuthor4, labelAuthor5);
		labelAuthorsBox.setAlignment(Pos.BOTTOM_CENTER);
		
		// Create clickable hyperlink for opening the project overview.
		Hyperlink linkOverview = new Hyperlink();
		linkOverview.setText("Open Project Overview Pdf");
		linkOverview.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        System.out.println("This link is clicked");
		        File file = new File("H:\\Documents\\GitHub\\CYPath20222023\\doc\\sujetCypath.pdf");
		        Desktop desktop = Desktop.getDesktop();
		        try {
					desktop.open(file);
				}
		        catch (IOException e) {
					e.printStackTrace();
					System.out.println("File not found");
				}
		    }
		});
		// linkOverview pane to simplify visual alignment in grid.
		HBox linkOverviewBox = new HBox(linkOverview);
		linkOverviewBox.setAlignment(Pos.BOTTOM_CENTER);
		
		// Create clickable hyperlink for opening the project documentation.
		Hyperlink linkJavadoc = new Hyperlink();
		linkJavadoc.setText("Open Javadoc");
		linkJavadoc.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        System.out.println("This link is clicked");
		        File file = new File("H:\\Documents\\GitHub\\CYPath20222023\\doc\\index.html");
		        Desktop desktop = Desktop.getDesktop();
		        try {
					desktop.open(file);
				}
		        catch (IOException e) {
					e.printStackTrace();
					System.out.println("File not found");
				} 
		    }
		});
		// linkJavadoc pane to simplify visual alignment in grid.
		HBox linkJavadocBox = new HBox(linkJavadoc);
		linkJavadocBox.setSpacing(40);
		linkJavadocBox.setAlignment(Pos.BOTTOM_RIGHT);
		
		
		// Create main menu pane.
		GridPane mainMenuPane = new GridPane();
		//mainMenuPane.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");
		mainMenuPane.setId("mainMenuGrid");
		// Align elements in the center of the grid.

		
		for(int j = 0; j < 3; j++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setPercentWidth(33);
			mainMenuPane.getColumnConstraints().add(column);
		}
		
		RowConstraints rowFirst = new RowConstraints();
		rowFirst.setPercentHeight(30);
		mainMenuPane.getRowConstraints().add(rowFirst);
		
		RowConstraints rowSecond = new RowConstraints();
		rowSecond.setPercentHeight(90 - rowFirst.getPercentHeight());
		mainMenuPane.getRowConstraints().add(rowSecond);
		
		RowConstraints rowThird = new RowConstraints();
		rowThird.setPercentHeight(100 - rowSecond.getPercentHeight());
		mainMenuPane.getRowConstraints().add(rowThird);
		
		mainMenuPane.add(imgCyLogoBox, 0, 0);
		mainMenuPane.add(labelCyPathBox, 1, 0);
		mainMenuPane.add(buttonsVBox, 1, 1);
		mainMenuPane.add(labelAuthorsBox, 0, 2);
		mainMenuPane.add(linkOverviewBox, 1, 2);
		mainMenuPane.add(linkJavadocBox, 2, 2);
		
		Scene mainMenuScene = new Scene(mainMenuPane, width, height);
		stage.setScene(mainMenuScene);
		stage.show();
	}
	
	public void game(Stage stage) {
	// Create main view pane.
		GridPane gamePane = new GridPane();
		gamePane.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");
		gamePane.setId("gamePane");
		gamePane.setLayoutX(0);
		gamePane.setLayoutY(0);
	// Board.
		Board board = new Board(10,10,9,9);
		VBox boardBox = new VBox(board);
	// ChoiceBox.
		ChoiceBox<String> nbPlayer = new ChoiceBox<>();
		String[] players = {"2","4"};
		nbPlayer.getItems().addAll(players);
	// Button.
		Button button = new Button("Select the number of players");
		
		button.setOnAction(event ->{
			board.resetGame(nbPlayer);
		});
		
		VBox nbPlayerBox = new VBox(nbPlayer, button);
		gamePane.add(boardBox, 0, 0);
		gamePane.add(nbPlayerBox, 1, 0);
		
		GridPane boardGrid = (GridPane) board.getChildrenUnmodifiable().get(0);

		board.updateAdjacencyList(boardGrid);
		
		Scene mainMenuScene = new Scene(gamePane, 1000, 750);
		stage.setX(250);
		stage.setY(20);
		stage.setScene(mainMenuScene);
		stage.show();
	}
	
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
