package testQuadriageLogique;

import javafx.scene.paint.Color;

public class BarrierHorizontal extends Barrier {
	protected final static int DEFAULT_NUMBER = 0;
	protected final static int DEFAULT_X = 0;
	protected final static int DEFAULT_Y = 0;
	protected final static int DEFAULT_WIDTH = 5;
	protected final static int DEFAULT_HEIGHT = 5;
	protected final static Color DEFAULT_COLOR = Color.CHARTREUSE;
	
	protected BarrierHorizontal(int number, double x, double y, double width, double height, Color color) {	
		super(number, x, y, width, height, color);
	}
	
	protected BarrierHorizontal() {
		this(DEFAULT_NUMBER, DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR);		
	}
}
