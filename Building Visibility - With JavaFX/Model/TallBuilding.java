package Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TallBuilding extends Building {

	public TallBuilding() {
		changeMainRectangle(110,90,140,320);
		changeMainColor(Color.DARKSALMON);
		changeRoof(110, 90, 140, 50);
		door =  new Rectangle(170,380,20,30);
		door.setFill(Color.SADDLEBROWN);
		door.setStroke(Color.BLACK);
		 
	}
	
	public TallBuilding(boolean r, boolean w, boolean d) {
		this();
		setProperty("Roof", r);
		setProperty("Windows", w);
		setProperty("Door", d);
		// add widows
		int hgap,vgap,width,height;
		int windows_in_line = 5, windows_in_column = 10;		
		int num_windows = windows_in_line*windows_in_column;
		hgap = (int)mainRectangle.getWidth()/11;
		vgap = (int)mainRectangle.getHeight()/21;
		width = hgap;
		height = vgap;
		for(int i = 0;i<num_windows;i++) { // create and add all windows to the building
			Rectangle wnd = new Rectangle(mainRectangle.getX()+hgap+2*hgap*(i%windows_in_line),
					vgap+mainRectangle.getY()+2*vgap*(i/windows_in_line),width,height);
			wnd.setFill(Color.BLACK);
			allWindows.add(wnd);
		}
	}

}
