package testQuadriageVisuel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Player extends StackPane{  // Le extends doit influer sur le problème dans le main, à regarder

	    public Player(int taille) {
	        // Créer un cercle avec un rayon de 50 pixels
	        Circle cercle = new Circle(taille);
	        
	        // Définir la position du cercle
	        cercle.setCenterX(150);
	        cercle.setCenterY(150);
	        
	        // Définir la couleur de remplissage du cercle
	        cercle.setFill(Color.RED);

	}
}
