package developpement;

import javafx.scene.paint.Color;

/**
 * The BarrierHorizontal class is a sub class of the Barrier class and inherit of all its methods and attributes.
 * @author Cardenas D, Ruellan B, Machnik A, Johnson A, Guenneau R
 *
 */
public class BarrierHorizontal extends Barrier {

	/**
	 * This method is the constructor of the BarrierHorizontal class using parameters, it uses the counterpart constructor of the Barrier class.
	 * @param int number
	 * @param double x
	 * @param double y
	 * @param double width
	 * @param double height
	 * @param Color color
	 * @param String idTile1
	 * @param String idTile2
	 */
	protected BarrierHorizontal(int number, double x, double y, double width, double height, Color color, String idTile1, String idTile2) {	
		super(number, x, y, width, height, color, idTile1, idTile2);
		super.setId("Barrier Horizontal " +Integer.toString(number));
	}
	
	/**
	 * This method is the constructor of the BarrierHorizontal class with default values for attributes, it uses the counterpart constructor of the Barrier class.
	 */
	protected BarrierHorizontal() {
		super();		
	}
}
