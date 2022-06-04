package View;


import java.util.ArrayList;

import Model.Model;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View {
	private Group root;
	private RadioButton rbHouse, rbBuilding, rbCastle;
	private CheckBox cbRoof, cbWindows, cbDoor;
	private ComboBox<String> roofColors;
	private ToggleGroup tg;
	private BorderPane bp; // arrangement of Nodes to areas: LEFT,TOP,RIGHT,BOTTOM, CENTER
	private HBox hb,hb1;
	private Text txt,title;
	private ArrayList<Rectangle> allWindows;
	public View(Stage stage)
	{
		root = new Group();
		
		tg = new ToggleGroup();
		rbHouse = new RadioButton("House");
		rbHouse.setSelected(true);
		rbHouse.setToggleGroup(tg);
		rbBuilding = new RadioButton("Building");
		rbBuilding.setToggleGroup(tg);
		rbCastle = new RadioButton("Castle");
		rbCastle.setToggleGroup(tg);
		
		cbRoof = new CheckBox("Roof");
		cbWindows = new CheckBox("Windows");
		cbDoor = new CheckBox("Door");
		
		VBox vbLeft = new VBox();//vertical box
		vbLeft.getChildren().addAll(rbHouse, rbBuilding, rbCastle);
		vbLeft.setAlignment(Pos.CENTER_LEFT);
		
		VBox vbRight = new VBox();
		vbRight.getChildren().addAll(cbRoof, cbWindows, cbDoor);
		vbRight.setAlignment(Pos.CENTER_LEFT);
		
		Pane drawPane = new Pane();
		drawPane.getChildren().add(root);
		
		// add windows as controls
		allWindows = new ArrayList<>();
		
		
		// roof colors
		roofColors = new ComboBox<>();
		roofColors.getItems().addAll("Red","Green","Gray");
		roofColors.setValue("Red");
		txt = new Text("Roof Colors: ");
		
		// Horizontal box for combobox and its title
		hb = new HBox();//horizontal box
//		hb.getChildren().addAll(txt,roofColors);
		hb.setAlignment(Pos.CENTER);
		
		hb1 = new HBox();
		title = new Text("Choose the house of your dream");
		hb1.getChildren().add(title);
		hb1.setAlignment(Pos.CENTER);
				
		
		// border pane
		bp = new BorderPane();
		bp.setLeft(vbLeft);
		bp.setRight(vbRight);
		bp.setCenter(drawPane);
		bp.setBottom(hb);
		bp.setTop(hb1);
		
		Scene scene = new Scene(bp,500,500);
		stage.setScene(scene);
		stage.show();
	}
	
	public void update(Model m)
	{
		root.getChildren().clear(); // clean the previous view
		m.show(root);
		if(cbWindows.isSelected())
			root.getChildren().addAll(allWindows);
	}
	
	public String getKind() {
		if(rbHouse.isSelected())
			return rbHouse.getText();
		else if(rbBuilding.isSelected())
			return rbBuilding.getText();
		else return rbCastle.getText();
	}
	
	// change listener has been created in Control
	public void addChangeListenerToToggleGroup(ChangeListener<Toggle> chl) {
		tg.selectedToggleProperty().addListener(chl);
	}
	
	public void addChangeListenerToRoof(ChangeListener<Boolean> cl) {
		cbRoof.selectedProperty().addListener(cl);
	}
	
	public void addChangeListenerToRoofColors(ChangeListener<String> cl) {
		roofColors.valueProperty().addListener(cl); // connect roofColors to cl that was defined in Controller
	}
	
	public boolean getRoofIsSelected() {
		return cbRoof.isSelected();
	}
	
	public boolean getWindowsIsSelected() {
		return cbWindows.isSelected();
	}
	
	public boolean getDoorIsSelected() {
		return cbDoor.isSelected();
	}
	
	public String getRoofColor() {
		return roofColors.getValue();
	}
	
	public void addRoofColorsComboBox() {
		hb.getChildren().addAll(txt,roofColors);
	}
	public void hideRoofColorsComboBox() {
		hb.getChildren().clear();
	}
	
	public void addChangeListenerToDoor(ChangeListener<Boolean> listener) {
		cbDoor.selectedProperty().addListener(listener);
	}
	
	public void addChangeListenerToWindows(ChangeListener<Boolean> listener) {
		cbWindows.selectedProperty().addListener(listener);
	}
	
	// when the windows check box is unselected we don't need windows
	public void removeWindows() {
		allWindows.clear();
	}
	
	public void addWindow(Rectangle window) {
		allWindows.add(window);
	}
	
	public int getWindowIndex(Object o) {
		return allWindows.indexOf(o);
	}
	
	public void changeWindowColor(int index) {
		if(allWindows.get(index).getFill()==Color.BLACK) {
			allWindows.get(index).setFill(Color.YELLOW);
		}
		else 	
			allWindows.get(index).setFill(Color.BLACK);

	}
}
