package testQuadriageVisuel;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends Region{
	
	int x;
	int y;
	int width;
	final int DEFAULT_BORDER_WIDTH = 0;
	
	public Square(int x,int y,int width) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		
		Rectangle rect1 = new Rectangle(x,y,width,width);
		rect1.setFill(Color.AQUA);
		Rectangle borderBottom = new Rectangle(x,y+width,width,width);
		borderBottom.setFill(Color.BLACK);
		
	}
	
	public void a() {
		for(int i = 0; i<2; i++) {
			Rectangle rect = new Rectangle(0,0,10,10);
		}
		
	}
}
