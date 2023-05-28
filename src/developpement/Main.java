package developpement;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
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
			newGame(stage);
		});
		
		Button buttonLoadGame = new Button("Load Game");
		buttonLoadGame.setPrefWidth(height/3);
		// Event.
		buttonLoadGame.setOnMouseClicked(event ->{
			try {
				loadGame(stage);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
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
		
		// Create clickable hyperlink for opening the project overview.
		Hyperlink linkRapport = new Hyperlink();
		linkRapport.setText("Open Rapport Pdf");
		linkRapport.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        System.out.println("This link is clicked");
		        File file = new File("H:\\Documents\\GitHub\\CYPath20222023\\rapportProjetJava.pdf");
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
				VBox linkOverviewBox = new VBox(linkOverview, linkRapport);
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
	
	public void newGame(Stage stage) {
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
		Button buttonReset = new Button("Select the number of players");

		buttonReset.setOnAction(event ->{
			board.resetBoard(nbPlayer);
		});
		
		Button buttonLoad = new Button("Save game");

		buttonLoad.setOnAction(event ->{
			board.saveGame(board.getPlayer1(), board.getPlayer2(), board.getPlayer3(), board.getPlayer4());
		});
		
		VBox nbPlayerBox = new VBox(nbPlayer, buttonReset, buttonLoad);
		nbPlayerBox.setSpacing(10.0);
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
	
	public void loadGame(Stage stage) throws FileNotFoundException, IllegalArgumentException {
		// Create main view pane.
		GridPane gamePane = new GridPane();
		gamePane.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");
		gamePane.setId("gamePane");
		gamePane.setLayoutX(0);
		gamePane.setLayoutY(0);
		// Board.
		Player player1 = new Player("Player 1", "", true);
		Player player2 = new Player("Player 2", "", false);
		Player player3 = new Player("Player 3", "", false);
		Player player4 = new Player("Player 4", "", false);
		
		Board board = new Board(10,10,9,9);

		String path = "H:\\Documents\\GitHub\\CYPath20222023\\read.txt";
		
		ArrayList<String> savedText = board.readSavedGame(path);
		System.out.println(savedText.get(2));
		
		int numberOfPlayers = Integer.parseInt(savedText.get(0));
		board.setNumberOfPlayers(numberOfPlayers);
		
		int startPlayer1Data = 1;
		Boolean player1Turn = Boolean.valueOf(savedText.get(startPlayer1Data+2));
		Player newPlayer1 = new Player(savedText.get(startPlayer1Data), savedText.get(startPlayer1Data+1), player1Turn);
		
		int startPlayer2Data = 4;
		Boolean player2Turn = Boolean.valueOf(savedText.get(startPlayer2Data+2));
		Player newPlayer2 = new Player(savedText.get(startPlayer2Data), savedText.get(startPlayer2Data+1), player2Turn);
		
		int startPlayer3Data = 7;
		Boolean player3Turn = Boolean.valueOf(savedText.get(startPlayer3Data+2));
		Player newPlayer3 = new Player(savedText.get(startPlayer3Data), savedText.get(startPlayer3Data+1), player3Turn);
		
		int startPlayer4Data = 10;
		Boolean player4Turn = Boolean.valueOf(savedText.get(startPlayer4Data+2));
		Player newPlayer4 = new Player(savedText.get(startPlayer4Data), savedText.get(startPlayer4Data+1), player4Turn);
		
		board.removePlayerTile(player1);
		board.removePlayerTile(player2);
		board.removePlayerTile(player3);
		board.removePlayerTile(player4);
		board.setPlayers(newPlayer1, newPlayer2, newPlayer3, newPlayer4);
		board.addPlayerTile(Integer.parseInt(board.listPlayer.get(0).getCurrentTileId().substring(5)), player1);
		board.addPlayerTile(Integer.parseInt(board.listPlayer.get(1).getCurrentTileId().substring(5)), player2);
		
		if(numberOfPlayers == 4) {
			board.addPlayerTile(Integer.parseInt(board.listPlayer.get(2).getCurrentTileId().substring(5)), player3);
			board.addPlayerTile(Integer.parseInt(board.listPlayer.get(3).getCurrentTileId().substring(5)), player4);
		}

		VBox boardBox = new VBox(board);
		// ChoiceBox.
		ChoiceBox<String> nbPlayer = new ChoiceBox<>();
		String[] players = {"2","4"};
		ArrayList<Player> a = new ArrayList<>();
		a.add(newPlayer4);
		System.out.println(a);
		nbPlayer.getItems().addAll(players);
		// Button.
		Button buttonReset = new Button("Select the number of players");

		buttonReset.setOnAction(event ->{
			board.resetBoard(nbPlayer);
		});

		Button buttonLoad = new Button("Save Game");

		buttonLoad.setOnAction(event ->{
			board.saveGame(board.getPlayer1(), board.getPlayer2(), board.getPlayer3(), board.getPlayer4());
		});
		
		VBox nbPlayerBox = new VBox(nbPlayer, buttonReset, buttonLoad);
		nbPlayerBox.setSpacing(10.0);
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
}
