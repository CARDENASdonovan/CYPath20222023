package testQuadriageLogique;

import javafx.scene.paint.Color;

public class BarrierVertical extends Barrier {
	private final static int DEFAULT_NUMBER = 0;
	private final static int DEFAULT_X = 0;
	private final static int DEFAULT_Y = 0;
	private final static int DEFAULT_WIDTH = 5;
	private final static int DEFAULT_HEIGHT = 5;
	private final static Color DEFAULT_COLOR = Color.CHARTREUSE;
	
	protected BarrierVertical(int number, int x, int y, int width, int height, Color color) {	
		super(number, x, y, width, height, color);
	}
	
	protected BarrierVertical() {
		this(DEFAULT_NUMBER, DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR);		
	}
}
