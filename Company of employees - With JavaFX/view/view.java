package AmitCohen.view;

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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class view {
	private BorderPane root;
	private Scene scene;
	
	private RadioButton option1, option2, option3, option4, option5, option6, option7, option8; // radio buttons for the menu
	private ToggleGroup menuOptions; //Toggle for menu
	
	private TextField textFieldOfCompanyName; // text field for add company
	private Button createCompanyButton; // buttons for add company
	
	private TextField textFieldOfDepartmentName; // text field for add department
	private Button addDepartmentButton; // button for add department
	private CheckBox departmentCheckBoxChangeWorkingTime; // check box for add department
	
	private TextField textFieldOfRoleName; // text field for add role
	private Button addRoleButton; // button for add role
	private CheckBox roleCheckBoxChangeWorkingTime; // check box for add role
	private ComboBox<String> allDepartmentsNames; // combo box for add role
	private Text quest; // text for add role
	
	private TextField textFieldOfEmployeeName, textFieldOfPreferStartingWorkTime; // text field for add employee
	private ComboBox<String> departmentsWithFreeRoles, freeRolesInDepartment; // combo box for add employee
	private ToggleGroup employeeTypes, employeeWorkingTimePreference; //Toggle for add employee
	private RadioButton employeeOption1, employeeOption2, employeeOption3; // radio buttons for add employee
	private RadioButton employeeWorkingTimePreference1, employeeWorkingTimePreference2, employeeWorkingTimePreference3, employeeWorkingTimePreference4; // radio buttons for add employee
	private Button addEmployeeButton; // button for add employee
	private Text txt1, txt2; //Text for add employee
	
	private ComboBox<String> allDepartmentsNamesToChangeWorkMethodRole, allRolesNamesToChangeWorkMethodRole; // combo box for change role working method
	private CheckBox canChangeWorkTimeCheckBox; // check box for change role working method
	private Button checkChangeWorkMethodRoleButton; // button for change role working method
	private TextField newStartWorkHourTextField; // text field for change role working method
	
	private ComboBox<String> allDepartmentsNamesToChangeWorkMethodDepartment; // combo box for change department working method
	private Text chaneStartWorkTimeDepartmentText, startWorkTimeDepartmentText, possibleStartWorkingTimeDepartmentText; // text for change department working method
	private CheckBox canWorkFromHomeDepartmentCheckBox, wantToChaneStartWorkTimeDepartmentCheckBox; // check box for change department working method
	private TextField startWorkTimeDepartmentTextField; // text field for change department working method
	private Button checkChangeWorkMethodDepartmentButton; // button for change department working method
	
	private ComboBox<String> showOptionsToProfitOrLossComboBox, allDepartmentsComboBox, allEmployeesComboBox; // combo box for show profit or loss
	private Button showProfitOrLossButton; // button for show profit or loss
	private Text toWhichDepartmentText, toWhichEmployeeText; // text for show profit or loss
	
	//Constructor
	public view(Stage stage) {
		//Make the menu and make the buttons
		VBox menu = new VBox();
		menuOptions = new ToggleGroup();
		Text menuText = new Text("Menu : \n");
		menuText.setFill(Color.RED);
		option1 = new RadioButton("Make new company");
		option2 = new RadioButton("Add department");
		option3 = new RadioButton("Add role");
		option4 = new RadioButton("Add employee");
		option5 = new RadioButton("Show the company");
		option6 = new RadioButton("Change work method - Role");
		option7 = new RadioButton("Change work method - Department");
		option8 = new RadioButton("Show profit or loss to one of the component");
		option1.setToggleGroup(menuOptions);
		option2.setToggleGroup(menuOptions);
		option3.setToggleGroup(menuOptions);
		option4.setToggleGroup(menuOptions);
		option5.setToggleGroup(menuOptions);
		option6.setToggleGroup(menuOptions);
		option7.setToggleGroup(menuOptions);
		option8.setToggleGroup(menuOptions);
		
		textFieldOfCompanyName = new TextField();
		createCompanyButton = new Button("Create company");
		
		textFieldOfDepartmentName = new TextField();
		addDepartmentButton = new Button("Add department");
		departmentCheckBoxChangeWorkingTime = new CheckBox();
		
		textFieldOfRoleName = new TextField();
		addRoleButton = new Button("Add role");
		roleCheckBoxChangeWorkingTime = new CheckBox();
		allDepartmentsNames = new ComboBox<String>();
		quest = new Text("Does employee can change working time?");
		
		textFieldOfEmployeeName = new TextField();
		textFieldOfPreferStartingWorkTime = new TextField();
		departmentsWithFreeRoles = new ComboBox<String>();
		freeRolesInDepartment = new ComboBox<String>();
		employeeTypes = new ToggleGroup();
		employeeOption1 = new RadioButton("Monthly");
		employeeOption2 = new RadioButton("By hours");
		employeeOption3 = new RadioButton("Monthly plus sale bonus");
		employeeOption1.setToggleGroup(employeeTypes);
		employeeOption2.setToggleGroup(employeeTypes);
		employeeOption3.setToggleGroup(employeeTypes);
		employeeWorkingTimePreference = new ToggleGroup();
		employeeWorkingTimePreference1 = new RadioButton("Starting early");
		employeeWorkingTimePreference2 = new RadioButton("Starting late");
		employeeWorkingTimePreference3 = new RadioButton("Stay as before");
		employeeWorkingTimePreference4 = new RadioButton("Work from home");
		employeeWorkingTimePreference1.setToggleGroup(employeeWorkingTimePreference);
		employeeWorkingTimePreference2.setToggleGroup(employeeWorkingTimePreference);
		employeeWorkingTimePreference3.setToggleGroup(employeeWorkingTimePreference);
		employeeWorkingTimePreference4.setToggleGroup(employeeWorkingTimePreference);
		addEmployeeButton = new Button("Add employee");
		txt1 = new Text("Prefer starting work time:");
		txt2 = new Text("(0-15)");
		
		allDepartmentsNamesToChangeWorkMethodRole = new ComboBox<String>();
		allRolesNamesToChangeWorkMethodRole = new ComboBox<String>();
		canChangeWorkTimeCheckBox = new CheckBox();
		checkChangeWorkMethodRoleButton = new Button("Check the change");
		newStartWorkHourTextField = new TextField();
		
		allDepartmentsNamesToChangeWorkMethodDepartment = new ComboBox<String>();
		chaneStartWorkTimeDepartmentText = new Text("Change start work time?");
		startWorkTimeDepartmentText = new Text("Start working time:");
		possibleStartWorkingTimeDepartmentText = new Text("(0-15)");
		canWorkFromHomeDepartmentCheckBox = new CheckBox();
		wantToChaneStartWorkTimeDepartmentCheckBox = new CheckBox();
		startWorkTimeDepartmentTextField = new TextField();
		checkChangeWorkMethodDepartmentButton = new Button("Check the change");
		
		showOptionsToProfitOrLossComboBox = new ComboBox<String>();
		showOptionsToProfitOrLossComboBox.getItems().add("Company");
		showOptionsToProfitOrLossComboBox.getItems().add("Department");
		showOptionsToProfitOrLossComboBox.getItems().add("Employee");
		allDepartmentsComboBox = new ComboBox<String>();
		allEmployeesComboBox = new ComboBox<String>();
		showProfitOrLossButton = new Button("Show");
		toWhichDepartmentText = new Text("To which department:");
		toWhichEmployeeText = new Text("To which employee:");
		
		//Add the relevant to show
		menu.getChildren().addAll(menuText, option1, option2, option3, option4, option5, option6, option7, option8);
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
	public TextField getTextFieldOfCompanyName() {
		return textFieldOfCompanyName;
	}
	public Button getCreateCompanyButton() {
		return createCompanyButton;
	}
	public TextField getTextFieldOfDepartmentName() {
		return textFieldOfDepartmentName;
	}
	public Button getAddDepartmentButton() {
		return addDepartmentButton;
	}
	public CheckBox getDepartmentCheckBoxChangeWorkingTime() {
		return departmentCheckBoxChangeWorkingTime;
	}
	public TextField getTextFieldOfRoleName() {
		return textFieldOfRoleName;
	}
	public Button getAddRoleButton() {
		return addRoleButton;
	}
	public CheckBox getRoleCheckBoxChangeWorkingTime() {
		return roleCheckBoxChangeWorkingTime;
	}
	public ComboBox<String> getAllDepartmentsNamesComboBox(){
		return allDepartmentsNames;
	}
	public Text getQuest() {
		return quest;
	}
	public TextField getTextFieldOfEmployeeName() {
		return textFieldOfEmployeeName;
	}
	public TextField getTextFieldOfPreferStartingWorkTime() {
		return textFieldOfPreferStartingWorkTime;
	}
	public ComboBox<String> getAllDepartmentsWithFreeRolesComboBox(){
		return departmentsWithFreeRoles;
	}
	public ComboBox<String> getAllFreeRolesInDepartmentComboBox(){
		return freeRolesInDepartment;
	}
	public RadioButton getEmployeeTypeOneButton() {
		return employeeOption1;
	}
	public RadioButton getEmployeeTypeTwoButton() {
		return employeeOption2;
	}
	public RadioButton getEmployeeTypeThreeButton() {
		return employeeOption3;
	}
	public RadioButton getEmployeeWorkTimePreferenceOneButton() {
		return employeeWorkingTimePreference1;
	}
	public RadioButton getEmployeeWorkTimePreferenceTwoButton() {
		return employeeWorkingTimePreference2;
	}
	public RadioButton getEmployeeWorkTimePreferenceThreeButton() {
		return employeeWorkingTimePreference3;
	}
	public RadioButton getEmployeeWorkTimePreferenceFourButton() {
		return employeeWorkingTimePreference4;
	}
	public Button getAddEmployeeButton() {
		return addEmployeeButton;
	}
	public Text getText1() {
		return txt1;
	}
	public Text getText2() {
		return txt2;
	}
	public ToggleGroup getEmployeeTypes() {
		return employeeTypes;
	}
	public ToggleGroup getEmployeeWorkingTimePreference() {
		return employeeWorkingTimePreference;
	}
	public ComboBox<String> getAllDepartmentsNamesToChangeWorkMethodRole() {
		return allDepartmentsNamesToChangeWorkMethodRole;
	}
	public ComboBox<String> getAllRolesNamesToChangeWorkMethodRole() {
		return allRolesNamesToChangeWorkMethodRole;
	}
	public CheckBox getCanChangeWorkTimeCheckBox() {
		return canChangeWorkTimeCheckBox;
	}
	public Button getCheckChangeWorkMethodRoleButton() {
		return checkChangeWorkMethodRoleButton;
	}
	public TextField getNewStartWorkHourTextField() {
		return newStartWorkHourTextField;
	}
	public ComboBox<String> getAllDepartmentsNamesToChangeWorkMethodDepartment() {
		return allDepartmentsNamesToChangeWorkMethodDepartment;
	}
	public Text getChaneStartWorkTimeDepartmentText() {
		return chaneStartWorkTimeDepartmentText;
	}
	public Text getStartWorkTimeDepartmentText() {
		return startWorkTimeDepartmentText;
	}
	public Text getPossibleStartWorkingTimeDepartmentText() {
		return possibleStartWorkingTimeDepartmentText;
	}
	public CheckBox getCanWorkFromHomeDepartmentCheckBox() {
		return canWorkFromHomeDepartmentCheckBox;
	}
	public CheckBox getWantToChaneStartWorkTimeDepartmentCheckBox() {
		return wantToChaneStartWorkTimeDepartmentCheckBox;
	}
	public TextField getStartWorkTimeDepartmentTextField() {
		return startWorkTimeDepartmentTextField;
	}
	public Button getCheckChangeWorkMethodDepartmentButton() {
		return checkChangeWorkMethodDepartmentButton;
	}
	public ComboBox<String> getShowOptionsToProfitOrLossComboBox() {
		return showOptionsToProfitOrLossComboBox;
	}
	public ComboBox<String> getAllDepartmentsComboBox() {
		return allDepartmentsComboBox;
	}
	public ComboBox<String> getAllEmployeesComboBox() {
		return allEmployeesComboBox;
	}
	public Button getShowProfitOrLossButton() {
		return showProfitOrLossButton;
	}
	public Text getToWhichDepartmentText() {
		return toWhichDepartmentText;
	}
	public Text getToWhichEmployeeText() {
		return toWhichEmployeeText;
	}
	
	//Clear fields and choice options
	public void clearTextFieldOfCompanyName() {
		this.textFieldOfCompanyName.setText("");
	}
	public void resetMenuSelection() {
		menuOptions.selectToggle(null);
	}
	public void clearTextFieldOfDepartmentName() {
		this.textFieldOfDepartmentName.setText("");
	}
	public void clearDepartmentCheckBoxChangeWorkingTime() {
		departmentCheckBoxChangeWorkingTime.setSelected(false);
	}
	public void clearRoleCheckBoxChangeWorkingTime() {
		roleCheckBoxChangeWorkingTime.setSelected(false);
	}
	public void clearAllDepartmentsNames() {
		allDepartmentsNames.getItems().clear();
	}
	public void clearTextFieldOfRoleName() {
		textFieldOfRoleName.setText("");
	}
	public void clearTextFieldOfEmployeeName() {
		textFieldOfEmployeeName.setText("");
	}
	public void clearTextFieldOfPreferStartingWorkTime() {
		textFieldOfPreferStartingWorkTime.setText("");
	}
	public void clearAllDepartmentsWithFreeRoles() {
		departmentsWithFreeRoles.getItems().clear();
	}
	public void clearAllFreeRolesInDepartment() {
		freeRolesInDepartment.getItems().clear();
	}
	public void resetEmployeeTypes() {
		employeeTypes.selectToggle(null);
	}
	public void resetEmployeeWorkingTimePreference() {
		employeeWorkingTimePreference.selectToggle(null);
	}
	public void clearAllDepartmentsNamesToChangeWorkMethodRole() {
		allDepartmentsNamesToChangeWorkMethodRole.getItems().clear();
	}
	public void clearAllRolesNamesToChangeWorkMethodRole() {
		allRolesNamesToChangeWorkMethodRole.getItems().clear();
	}
	public void clearCanChangeWorkTimeCheckBox() {
		canChangeWorkTimeCheckBox.setSelected(false);
	}
	public void clearNewStartWorkHourTextField() {
		newStartWorkHourTextField.setText("");
	}
	public void clearAllDepartmentsNamesToChangeWorkMethodDepartment() {
		allDepartmentsNamesToChangeWorkMethodDepartment.getItems().clear();
	}
	public void clearCanWorkFromHomeDepartmentCheckBox() {
		canWorkFromHomeDepartmentCheckBox.setSelected(false);
	}
	public void clearWantToChaneStartWorkTimeDepartmentCheckBox() {
		wantToChaneStartWorkTimeDepartmentCheckBox.setSelected(false);
	}
	public void clearStartWorkTimeDepartmentTextField() {
		startWorkTimeDepartmentTextField.setText("");
	}	
	public void clearAllDepartmentsComboBox() {
		allDepartmentsComboBox.getItems().clear();
	}
	public void clearAllEmployeesComboBox() {
		allEmployeesComboBox.getItems().clear();
	}
	
	//Add name to all department combo box
	public void addNameToAllDepartmentsNamesComboBox(String departmentName) {
		allDepartmentsNames.getItems().add(departmentName);
	}
	
	//Add name to all departments with free role combo box
	public void addNameToAllDepartmentsWithFreeRoleComboBox(String departmentNameWithFreeRole) {
		departmentsWithFreeRoles.getItems().add(departmentNameWithFreeRole);
	}
	
	//Add name to all free roles in department
	public void addNameToAllFreeRolesInDepartment(String nameOfFreeRole) {
		freeRolesInDepartment.getItems().add(nameOfFreeRole);
	}
	
	//Add name to all departments in company
	public void addNameToAllDepartmentsNamesToChangeWorkMethodRole(String nameOfFreeRole) {
		allDepartmentsNamesToChangeWorkMethodRole.getItems().add(nameOfFreeRole);
	}
	
	//Add name to all roles names in department
	public void addNameToAllRolesNamesToChangeWorkMethodRole(String nameOfFreeRole) {
		allRolesNamesToChangeWorkMethodRole.getItems().add(nameOfFreeRole);
	}
	
	//Add name to all departments names to change work method department
	public void addNameToAllDepartmentsNamesToChangeWorkMethodDepartment(String nameOfDepartment) {
		allDepartmentsNamesToChangeWorkMethodDepartment.getItems().add(nameOfDepartment);
	}
	
	//Add name to all departments combo box
	public void addNameToAllDepartmentsComboBox(String nameOfDepartment) {
		allDepartmentsComboBox.getItems().add(nameOfDepartment);
	}
	
	//Add name to all employees combo box
	public void addNameToAllEmployeesComboBox(String nameOfDepartment) {
		allEmployeesComboBox.getItems().add(nameOfDepartment);
	}
	
	//Change details to show
	public void showInCenter(GridPane newDetailsToShow) {
		root.setCenter(newDetailsToShow);
	}
	public void showInCenter(Text newDetailsToShow) {
		root.setCenter(newDetailsToShow);
	}
	public void showInTop(Text newDetailsToShow) {
		root.setTop(newDetailsToShow);
	}
	
	//functions to buttons
	public void clickOnAddCompanyInMenu(EventHandler<ActionEvent> event) {
		option1.setOnAction(event);
	}
	public void clickOnCreateCompany(EventHandler<ActionEvent> event) {
		createCompanyButton.setOnAction(event);
	}
	public void clickOnAddDepartmentInMenu(EventHandler<ActionEvent> event) {
		option2.setOnAction(event);
	}
	public void clickOnAddDepartment(EventHandler<ActionEvent> event) {
		addDepartmentButton.setOnAction(event);
	}
	public void clickOnAddRoleInMenu(EventHandler<ActionEvent> event) {
		option3.setOnAction(event);
	}
	public void clickOnAddRole(EventHandler<ActionEvent> event) {
		addRoleButton.setOnAction(event);
	}
	public void clickOnComboBoxOfAllDepartmentsNames(EventHandler<ActionEvent> event) {
		allDepartmentsNames.setOnAction(event);
	}
	public void clickOnAddEmployeeInMenu(EventHandler<ActionEvent> event) {
		option4.setOnAction(event);
	}
	public void clickOnDepartmentWithFreeRole(EventHandler<ActionEvent> event) {
		departmentsWithFreeRoles.setOnAction(event);
	}
	public void clickOnWorkingTimePreference(EventHandler<ActionEvent> event) {
		employeeWorkingTimePreference1.setOnAction(event);
		employeeWorkingTimePreference2.setOnAction(event);
		employeeWorkingTimePreference3.setOnAction(event);
		employeeWorkingTimePreference4.setOnAction(event);
	}
	public void clickOnAddEmployee(EventHandler<ActionEvent> event) {
		addEmployeeButton.setOnAction(event);
	}
	public void clickOnShowCompanyInMenu(EventHandler<ActionEvent> event) {
		option5.setOnAction(event);
	}
	public void clickOnChangeWorkMethodRoleInMenu(EventHandler<ActionEvent> event) {
		option6.setOnAction(event);
	}
	public void clickOnAllDepartmentsNamesToChangeWorkMethodRole(EventHandler<ActionEvent> event) {
		allDepartmentsNamesToChangeWorkMethodRole.setOnAction(event);
	}
	public void clickOnCheckChangeWorkMethodRoleButton(EventHandler<ActionEvent> event) {
		checkChangeWorkMethodRoleButton.setOnAction(event);
	}
	public void clickOnCheckChangeWorkMethodDepartmentButtonInMenu(EventHandler<ActionEvent> event) {
		option7.setOnAction(event);
	}
	public void clickOnCanWorkFromHomeCheckBoxInChangeWorkMethodDepartment(EventHandler<ActionEvent> event) {
		canWorkFromHomeDepartmentCheckBox.setOnAction(event);
	}
	public void clickOnChangeStartWorkTimeCheckBoxInChangeWorkMethodDepartment(EventHandler<ActionEvent> event) {
		wantToChaneStartWorkTimeDepartmentCheckBox.setOnAction(event);
	}
	public void clickOnCheckTheChangeButtonDepartment(EventHandler<ActionEvent> event) {
		checkChangeWorkMethodDepartmentButton.setOnAction(event);
	}
	public void clickOnShowProfitOrLossInTheMenu(EventHandler<ActionEvent> event) {
		option8.setOnAction(event);
	}
	public void clickOnShowOptionsToProfitOrLossComboBox(EventHandler<ActionEvent> event) {
		showOptionsToProfitOrLossComboBox.setOnAction(event);
	}
	public void clickOnButtonCheckLossOrProfit(EventHandler<ActionEvent> event) {
		showProfitOrLossButton.setOnAction(event);
	}
}
