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
	 * @param int idNumber
	 * @param double width
	 * @param double height
	 * @param Color color
	 * @param String idTile1
	 * @param String idTile2
	 */
	protected BarrierHorizontal(int idNumber, double width, double height, Color color, String idTile1, String idTile2) {	
		super(idNumber, width, height, color, idTile1, idTile2);
		super.setId("Barrier Horizontal " +Integer.toString(idNumber));
	}
	
	/**
	 * This method is the constructor of the BarrierHorizontal class with default values for attributes, it uses the counterpart constructor of the Barrier class.
	 */
	protected BarrierHorizontal() {
		super();		
	}
}
