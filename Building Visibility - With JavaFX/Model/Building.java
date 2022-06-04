package Model;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Building {
	protected boolean hasRoof, hasWindows,hasDoor;
	protected Rectangle mainRectangle,door;
	protected Polygon roof;
	protected Color mainColor, roofColor;
	protected ArrayList<Rectangle> allWindows;


public Building() {
	allWindows = new ArrayList<>(); // empty because in Building we don't know nothing about windows
	mainRectangle = new Rectangle(80,150,200,200);
	mainColor = Color.WHITE;
	mainRectangle.setFill(mainColor);
	mainRectangle.setStroke(Color.BLACK); // adds border to mainRectangle
	roofColor = Color.RED;
	double [] points = {80,150,180,50,280,150};
	roof = new Polygon(points);
	roof.setFill(roofColor);
	roof.setStroke(Color.BLACK);
	
	door = new Rectangle(170,320,20,30);
	door.setFill(Color.SADDLEBROWN);
	door.setStroke(Color.BLACK);
	
	hasRoof = false;
	hasWindows = false;
	hasDoor = false;
}

public void changeMainColor(Color c)
{
	mainColor = c;
	mainRectangle.setFill(c);
} 

public void changeRoofColor(String color)
{
	switch(color) {
	case "Red":
		roofColor = Color.RED;
		break;
	case "Green":
		roofColor = Color.GREEN;
		break;
	case "Gray":
		roofColor = Color.GRAY;
		break;

	}
	roof.setFill(roofColor);
} 

public void changeMainRectangle(int left, int top, int width, int height) {
	mainRectangle.setX(left);
	mainRectangle.setY(top);
	mainRectangle.setWidth(width);
	mainRectangle.setHeight(height);
}

public void changeRoof(int left, int top, int width, int height)
{
	Double [] Points = new Double [] {(double)left,(double)top,(double)left+width/2,(double)top-height,
													(double)left+width,(double)top};
	roof.getPoints().clear();// remove old points
	roof.getPoints().addAll(Points); // add new points
}
	
public void show(Group root) {
	root.getChildren().add(mainRectangle);
	if(hasRoof) { // add roof
		root.getChildren().add(roof);
	}
	if(hasWindows) //add windows
	{
//		root.getChildren().addAll(allWindows); // add array list of Rectangles
	}
	if(hasDoor) { // add door
		root.getChildren().add(door);
	}
}

public void setProperty(String property, boolean value) {
	switch(property) {
	case "Roof":
		hasRoof = value;
		break;
	case "Windows":
		hasWindows = value;
		break;
	case "Door":
		hasDoor = value;
		break;
	}
}

public int getNumWindows() {
	return allWindows.size();
}

public Rectangle getWindow(int index) {
	return allWindows.get(index);
}

public void changeWindowColor(int index) {
	if(allWindows.get(index).getFill()==Color.BLACK)
	{
		allWindows.get(index).setFill(Color.YELLOW);
	}
	else
		allWindows.get(index).setFill(Color.BLACK);
}

}