package Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SmallHouse extends Building {
	public SmallHouse() {
		changeMainColor(Color.DARKKHAKI);
		// setting windows
		setWindows();
	}
	
	public SmallHouse(boolean roof, boolean windows, boolean door) {
		this(); // call to default constructor
		setProperty("Roof", roof);
		setProperty("Windows", windows);
		setProperty("Door", door);
	}
	
	public void setWindows() {
		int height=40, width = 40 , hGap = 40, vGap=30;
		int numWindowsInRow=2, numFloors=2;
		int i,numW =numWindowsInRow* numFloors;
		
		for(i=0;i<numW;i++)
		{
			Rectangle window = new Rectangle( 
					mainRectangle.getX()+hGap*(1+i%numWindowsInRow)+width*(i%numWindowsInRow),
					mainRectangle.getY()+vGap*(1+i/numFloors)+height*(i/numFloors),width,height);
			window.setStroke(Color.BLACK);
			allWindows.add(window);
		}
	}
}
