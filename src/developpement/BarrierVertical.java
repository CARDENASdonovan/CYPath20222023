package developpement;

import javafx.scene.paint.Color;

/**
 * The BarrierVertical class is a sub class of the Barrier class and inherit of all its methods and attributes.
 * @author Cardenas D, Ruellan B, Machnik A, Johnson A, Guenneau R
 *
 */

public class BarrierVertical extends Barrier {

	/**
	 * This method is the constructor of the BarrierVertical class using parameters, it uses the counterpart constructor of the Barrier class.
	 * @param number
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param color
	 * @param idTile1
	 * @param idTile2
	 */
	protected BarrierVertical(int number, double x, double y, double width, double height, Color color, String idTile1, String idTile2) {	
		super(number, x, y, width, height, color, idTile1, idTile2);
		super.setId("Barrier Vertical " + Integer.toString(number));
	}
	
	/**
	 * This method is the constructor of the BarrierHorizontal class with default values for attributes, it uses the counterpart constructor of the Barrier class.
	 */
	protected BarrierVertical() {
		super();		
	}
}
