package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class view {
	private BorderPane root;
	private Scene scene;
	
	private RadioButton option1, option2, option3, option4, option5, option6, option7, option8, option9, option10, option11; // buttons for the menu
	private ToggleGroup menuOptions; //Toggle for menu
	
	private ToggleGroup ballotOptions;//Toggle for ballots type
	private RadioButton ballotOption1, ballotOption2, ballotOption3, ballotOption4; // buttons for the ballots type
	private Button addBallotButton;
	private TextField ballotAddress;
	
	private TextField textFieldOfCitizenName;
	private TextField textFieldOfIdNum;
	private TextField textFieldOfBirthYear;
	private CheckBox checkBoxOfInInsulation;
	private CheckBox checkBoxOfWearProtection;
	private Text textOfDaysInInsulation;
	private TextField textFieldOfDaysInInsulation;
	private Button addCitizen;
	private CheckBox checkBoxOfCarryWeapon;
	
	private TextField textFieldOfPartyName;
	private TextField textFieldOfPartyCreationDay;
	private TextField textFieldOfPartyCreationMonth;
	private TextField textFieldOfPartyCreationYear;
	private ToggleGroup partyTendency;
	private RadioButton tendencyOption0;
	private RadioButton tendencyOption1;
	private RadioButton tendencyOption2;
	private Button buttonOfAddParty;
	
	private Button addContestantToParty;
	private TextField textFieldOfPartyNameToAddContestant;
	private TextField textFieldOfIdNumOfContestant;
	
	private CheckBox checkBoxWantToVote;
	private ComboBox<String> allPartiesNames;
	private Button buttonOfFinishVote;
	
	public view(Stage stage) {
		//Make the menu and make the buttons
		VBox menu = new VBox();
		menuOptions = new ToggleGroup();
		Text menuText = new Text("Menu : \n");
		menuText.setFill(Color.RED);
		option1 = new RadioButton("Add ballot");
		option2 = new RadioButton("Add citizen");
		option3 = new RadioButton("Add party");
		option4 = new RadioButton("Add contestant to party");
		option5 = new RadioButton("Show all ballots");
		option6 = new RadioButton("Show all citizens");
		option7 = new RadioButton("Show all parties");
		option8 = new RadioButton("Election day");
		option9 = new RadioButton("Show last results");
		option10 = new RadioButton("Save info");
		option11 = new RadioButton("Load old info");
		option1.setToggleGroup(menuOptions);
		option2.setToggleGroup(menuOptions);
		option3.setToggleGroup(menuOptions);
		option4.setToggleGroup(menuOptions);
		option5.setToggleGroup(menuOptions);
		option6.setToggleGroup(menuOptions);
		option7.setToggleGroup(menuOptions);
		option8.setToggleGroup(menuOptions);
		option9.setToggleGroup(menuOptions);
		option10.setToggleGroup(menuOptions);
		option11.setToggleGroup(menuOptions);
		
		addBallotButton = new Button("Add Ballot");
		ballotAddress = new TextField();
		ballotOptions = new ToggleGroup();
		ballotOption1 = new RadioButton("RegBallot");
		ballotOption2 = new RadioButton("MilitaryBallot");
		ballotOption3 = new RadioButton("CovidBallot");
		ballotOption4 = new RadioButton("MilitaryCovidBallot");
		ballotOption1.setToggleGroup(ballotOptions);
		ballotOption2.setToggleGroup(ballotOptions);
		ballotOption3.setToggleGroup(ballotOptions);
		ballotOption4.setToggleGroup(ballotOptions);
		
		textFieldOfCitizenName = new TextField();
		textFieldOfIdNum = new TextField();
		textFieldOfBirthYear = new TextField();
		checkBoxOfInInsulation = new CheckBox();
		checkBoxOfWearProtection = new CheckBox();
		textOfDaysInInsulation = new Text("Days in insulation:");
		textFieldOfDaysInInsulation = new TextField();
		setZeroAtDaysInInsulation();
		addCitizen = new Button("Add Citizen");
		checkBoxOfCarryWeapon = new CheckBox();
		
		textFieldOfPartyName = new TextField();
		textFieldOfPartyCreationDay = new TextField();
		textFieldOfPartyCreationMonth = new TextField();
		textFieldOfPartyCreationYear = new TextField();
		partyTendency = new ToggleGroup();
		tendencyOption0 = new RadioButton("Left");
		tendencyOption1 = new RadioButton("Center");
		tendencyOption2 = new RadioButton("Right");
		tendencyOption0.setToggleGroup(partyTendency);
		tendencyOption1.setToggleGroup(partyTendency);
		tendencyOption2.setToggleGroup(partyTendency);
		buttonOfAddParty = new Button("Add Party");
		
		addContestantToParty = new Button("Add Contestant");
		textFieldOfPartyNameToAddContestant = new TextField();
		textFieldOfIdNumOfContestant = new TextField();
		
		checkBoxWantToVote = new CheckBox();
		allPartiesNames = new ComboBox<String>();
		allPartiesNames.setVisible(false);
		buttonOfFinishVote = new Button("Next");
		
		//Add the relevant to show
		menu.getChildren().addAll(menuText, option1, option2, option3, option4, option5, option6, option7, option8, option9, option10, option11);
		menu.setAlignment(Pos.CENTER_LEFT);
		//Make the root
		root = new BorderPane();
		root.setLeft(menu);
		//Make the scene
		scene = new Scene(root, 1200, 600);
		//Connect scene to stage
		stage.setScene(scene);
		//Show the stage
		stage.show();
	}
	
	//Gets
	public ComboBox<String> getAllPartiesNames(){
		return allPartiesNames;
	}
	public Button getButtonOfFinishVote() {
		return buttonOfFinishVote;
	}
	public CheckBox getCheckBoxWantToVote() {
		return checkBoxWantToVote;
	}
	public Button getButtonOfAddContestantToParty() {
		return addContestantToParty;
	}
	public TextField getTextFieldOfPartyNameToAddContestant() {
		return textFieldOfPartyNameToAddContestant;
	}
	public TextField getTextFieldOfIdNumOfContestant() {
		return textFieldOfIdNumOfContestant;
	}
	public Button getButtonOfAddParty() {
		return buttonOfAddParty;
	}
	public RadioButton getOptionOfTendency0() {
		return tendencyOption0;
	}
	public RadioButton getOptionOfTendency1() {
		return tendencyOption1;
	}
	public RadioButton getOptionOfTendency2() {
		return tendencyOption2;
	}
	public TextField getTextFieldOfPartyName() {
		return textFieldOfPartyName;
	}
	public TextField getTextFieldOfCreationDay() {
		return textFieldOfPartyCreationDay;
	}
	public TextField getTextFieldOfCreationMonth() {
		return textFieldOfPartyCreationMonth;
	}
	public TextField getTextFieldOfCreationYear() {
		return textFieldOfPartyCreationYear;
	}
	public CheckBox getCheckBoxOfCarryWeapon() {
		return checkBoxOfCarryWeapon;
	}
	public Button getButtonOfAddCitizen() {//get the button of add citizen
		return addCitizen;
	}
	public TextField getTextFieldOfDaysInInsulation() {//Get the text field of days in insulation
		return textFieldOfDaysInInsulation;
	}
	public Text getTextOfDaysInInsulation() {
		return textOfDaysInInsulation;
	}
	public CheckBox getCheckBoxOfWearProtection() {//Get the check box of wear protection
		return checkBoxOfWearProtection;
	}
	public CheckBox getCheckBoxOfInInsulation() {//Get the check box of in insulation
		return checkBoxOfInInsulation;
	}
	public TextField getTextFieldOfBirthYear() {//Get the text field of birth year
		return textFieldOfBirthYear;
	}
	public TextField getTextFieldOfCitizenName() {//Get the text field of citizen name
		return textFieldOfCitizenName;
	}
	public TextField getTextFieldOfIdNum() {//Get the text field of id number
		return textFieldOfIdNum;
	}
	public RadioButton getBallotOption1() {//Get the button of first type of ballot
		return ballotOption1;
	}
	public RadioButton getBallotOption2() {//Get the button of second type of ballot
		return ballotOption2;
	}
	public RadioButton getBallotOption3() {//Get the button of third type of ballot
		return ballotOption3;
	}
	public RadioButton getBallotOption4() {//Get the button of fourth type of ballot
		return ballotOption4;
	}
	public Button getAddBallotButton() {//get the button of add ballot
		return addBallotButton;
	}
	public TextField getTextFieldOfAddress() {//get the text field to fill the address
		return ballotAddress;
	}
	public int getBallotType() {//get the selected type of the new ballot
		if(ballotOption1.isSelected())
			return 1;
		if(ballotOption2.isSelected())
			return 2;
		if(ballotOption3.isSelected())
			return 3;
		if(ballotOption4.isSelected())
			return 4;
		return 0;
	}
	public int getPartyTendency() {//get the selected type of the new ballot
		if(tendencyOption0.isSelected())
			return 0;
		if(tendencyOption1.isSelected())
			return 1;
		if(tendencyOption2.isSelected())
			return 2;
		return -1;
	}
	
	//Clear or default the fields boxes and buttons
	public void resetCheckBoxWantToVote() {
		checkBoxWantToVote.setSelected(false);
	}
	public void setZeroAtDaysInInsulation() {
		textFieldOfDaysInInsulation.setText("0");
	}
	public void resetMenuSelection() {
		menuOptions.selectToggle(null);
	}
	public void resetComboBoxOfAllPartiesNames() {
		allPartiesNames.getItems().clear();
	}
	public void clearTextFieldOfAddress() {
		ballotAddress.setText("");
	}
	public void resetBallotOptions() {
		ballotOptions.selectToggle(null);
	}
	public void resetTextFieldOfCitizenName() {
		textFieldOfCitizenName.setText("");
	}
	public void resetTextFieldOfIdNum() {
		textFieldOfIdNum.setText("");
	}
	public void resetTextFieldOfBirthYear() {
		textFieldOfBirthYear.setText("");
	}
	public void resetCheckBoxOfInInsulation() {
		checkBoxOfInInsulation.setSelected(false);
	}
	public void resetCheckBoxOfWearProtection() {
		checkBoxOfWearProtection.setSelected(false);
	}
	public void resetCheckBoxOfCarryWeapon() {
		checkBoxOfCarryWeapon.setSelected(false);
	}
	public void resetTextFieldOfPartyName() {
		textFieldOfPartyName.setText("");
	}
	public void resetTextFieldOfPartyCreationDay() {
		textFieldOfPartyCreationDay.setText("");
	}
	public void resetTextFieldOfPartyCreationMonth() {
		textFieldOfPartyCreationMonth.setText("");
	}
	public void resetTextFieldOfPartyCreationYear() {
		textFieldOfPartyCreationYear.setText("");
	}
	public void resetPartyTendency() {
		partyTendency.selectToggle(null);
	}
	public void resetTextFieldOfIdNumOfContestant() {
		textFieldOfIdNumOfContestant.setText("");
	}
	public void resetTextFieldOfPartyNameToAddContestant() {
		textFieldOfPartyNameToAddContestant.setText("");
	}
	
	//Change details to show
	public void showInCenter(GridPane newDetailsToShow) {
		root.setCenter(newDetailsToShow);
	}
	public void showInCenter(Text newDetailsToShow) {
		root.setCenter(newDetailsToShow);
	}
	
	//Add details to boxes
	public void addNameToComboBoxOfAllPartiesNames(String name) {
		allPartiesNames.getItems().add(name);
	}
	
	//functions to buttons
	public void clickOnButtonLoadOldInfoInMenu(EventHandler<ActionEvent> event) {
		option11.setOnAction(event);
	}
	public void clickOnButtonSaveInfoInMenu(EventHandler<ActionEvent> event) {
		option10.setOnAction(event);
	}
	public void clickOnShowResultsInMenu(EventHandler<ActionEvent> event) {
		option9.setOnAction(event);
	}
	public void clickOnFinishVote(EventHandler<ActionEvent> event) {
		buttonOfFinishVote.setOnAction(event);
	}
	public void clickOnCheckBoxWantToVote(EventHandler<ActionEvent> event) {
		checkBoxWantToVote.setOnAction(event);
	}
	public void clickOnButtonElectionsDayInMenu(EventHandler<ActionEvent> event) {
		option8.setOnAction(event);
	}
	public void clickOnButtonShowAllPartiesInMenu(EventHandler<ActionEvent> event) {
		option7.setOnAction(event);
	}
	public void clickOnButtonShowAllCitizensInMenu(EventHandler<ActionEvent> event) {
		option6.setOnAction(event);
	}
	public void clickOnButtonShowAllBallotsInMenu(EventHandler<ActionEvent> event) {
		option5.setOnAction(event);
	}
	public void clickOnButtonAddContestantToParty(EventHandler<ActionEvent> event) {
		addContestantToParty.setOnAction(event);
	}
	public void clickOnButtonAddContestantToPartyInMenu(EventHandler<ActionEvent> event) {
		option4.setOnAction(event);
	}
	public void clickOnButtonAddParty(EventHandler<ActionEvent> event) {
		buttonOfAddParty.setOnAction(event);
	}
	public void clickOnButtonAddPartyInMenu(EventHandler<ActionEvent> event) {
		option3.setOnAction(event);
	}
	public void clickOnButtonAddCitizen(EventHandler<ActionEvent> event) {
		addCitizen.setOnAction(event);
	}
	public void clickOnInInsulation(EventHandler<ActionEvent> event) {
		checkBoxOfInInsulation.setOnAction(event);
	}
	public void addCitizen(EventHandler<ActionEvent> event) {
		option2.setOnAction(event);
	}
	public void showDetailsToAddBallot(EventHandler<ActionEvent> event) { //Show the relevant info to add ballot
		option1.setOnAction(event);
	}
	public void addBallot(EventHandler<ActionEvent> clickOnButtonAddBallotAfterDetails) {
		addBallotButton.setOnAction(clickOnButtonAddBallotAfterDetails);
	}
}
