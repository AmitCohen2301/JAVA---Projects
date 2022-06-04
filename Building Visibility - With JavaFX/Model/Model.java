package Model;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class Model {
	private Building theBuilding;
	
	public Model() {// initial kind of building
		theBuilding = new SmallHouse();
	}
	
	public void show(Group root) {
		theBuilding.show(root);
	}
	
	// universal method that gets the property name (e.g. "Roof", "Windows", "Door") and its value (true/false)
	public void setProperty(String property, boolean value) {
		theBuilding.setProperty(property,value);
	}

	public void update(String kind, boolean roof, boolean windows, boolean door) {
		if(kind == "House")
			theBuilding = new SmallHouse(roof, windows,door);
		else if(kind == "Building")
			theBuilding = new TallBuilding(roof, windows,door);
		else if(kind == "Castle")
			theBuilding = new Castle(roof, windows,door);
	}
	
	public void changeRoofColor(String Color) {
		theBuilding.changeRoofColor(Color);
	}
	
	public int getNumWindows() {
		return theBuilding.getNumWindows();
	}
	
	public Rectangle getWindow(int index) {
		return theBuilding.getWindow(index);
	}
	
	public void changeWindowColor(int index) {
		theBuilding.changeWindowColor(index);
	}
	
}
