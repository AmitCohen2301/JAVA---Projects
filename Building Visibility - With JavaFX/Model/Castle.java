package Model;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Castle extends Building {
	private Rectangle leftTower, rightTower;
	private Polygon rightTowerRoof;
	
	public Castle() {
		changeMainColor(Color.DARKGRAY);
		changeMainRectangle(100, 200, 160, 150);
		changeRoof(40, 120, 60, 100); // left tower roof
		// init the towers
		leftTower = new Rectangle(40,120,60,230);
		leftTower.setFill(mainColor);
		leftTower.setStroke(Color.BLACK);
		
		rightTower = new Rectangle(260,120,60,230);
		rightTower.setFill(mainColor);
		rightTower.setStroke(Color.BLACK);
		
		double [] points = {260,120,290,20,320,120};
		rightTowerRoof = new Polygon(points);
		rightTowerRoof.setFill(roofColor);
		rightTowerRoof.setStroke(Color.BLACK);
	
	
	}
	
	public Castle(boolean r, boolean w, boolean d) {
		this();
		setProperty("Roof", r);
		setProperty("Windows", w);
		setProperty("Door", d);
		// add widows
		int hgap,vgap,width,height;
		hgap = width = (int)leftTower.getWidth()/3;
		vgap = (int)leftTower.getHeight()/8;
		height = 2*vgap;
		Rectangle ltw = new Rectangle(leftTower.getX()+hgap,leftTower.getY()+vgap,width,height);
		Rectangle rtw = new Rectangle(rightTower.getX()+hgap,leftTower.getY()+vgap,width,height);
		Rectangle lbw = new Rectangle(leftTower.getX()+hgap,leftTower.getY()+4*vgap,width,height);
		Rectangle rbw = new Rectangle(rightTower.getX()+hgap,leftTower.getY()+4*vgap,width,height);
		allWindows.add(ltw);
		allWindows.add(rtw);
		allWindows.add(lbw);
		allWindows.add(rbw);
	}
	
	public void show(Group root)
	{
		root.getChildren().addAll(leftTower,rightTower);
		rightTowerRoof.setFill(roofColor);
		if(hasRoof) root.getChildren().add(rightTowerRoof);
		super.show(root);
	}
}
