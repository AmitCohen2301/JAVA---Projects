package AmitCohen.View;

import java.sql.*;
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

public class View {
	private BorderPane root;
	private Scene scene;

//Define the buttons, groups, texts and texts fields
	//Hello
	private Text helloText;
	
	//Menu
	private RadioButton registerButtonInMenu, loginButtonInMenu, serchButtonInMenu, addButtonInMenu, updateGameInMenu, updatePersonalInfoButtonInMenu, logoutButtonInMenu;
	private ToggleGroup menuOptions;
	
	//Register
	private TextField textFieldOfFirstName, textFieldOfLastName, textFieldOfEmail, textFieldOfUserID, textFieldOfPassword;
	private RadioButton radioButtonOfMaleOption, radioButtonOfFemaleOption;
	private ToggleGroup genderOptions;
	private Button registerButton;
	
	//Login
	private Button loginButton;
	
	//Search
	private Text textToSearch, textMakafToManufactureYear, textMakafToPrice;
	private CheckBox checkBoxOfGameName, checkBoxOfDistributionCompany, checkBoxOfCreativeCompany, checkBoxOfGenre, checkBoxOfManufactureYear, 
						checkBoxOfPrice;
	private TextField textFieldOfGameName, textFieldOfDistributionCompany, textFieldOfCreativeCompany, textFieldOfStartManufactureYear,
						textFieldOfEndManufactureYear, textFieldOfStartPrice, textFieldOfEndPrice;
	private ComboBox<String> comboBoxOfGenres;
	private Button searchButton;
	
	//Add
	private Text textOfNameOfSeries, textReqCharsForSeriesName;
	private TextField textFieldOfDistributionDate, textFieldOfTrailer, textFieldOfDescription, textFieldOfManufactureYear, textFieldOfSeriesName,
						textFieldOfPrice;
	private CheckBox checkBoxOfPartOfSeries;
	private Button addButton;
	
	//Update game
	private ComboBox<String> namesOfAllGames;
	private Button selectGameButton, updateGameButton;
	
	//Update personal info
	private Button savePersonalInfoButton;
	
	//Logout
	private Text textToLogout;
	private Button yesButton, noButton;
	
//Constructor
	public View(Stage stage) {
	//Make the buttons, groups, texts and texts fields
		
		//Hello
		helloText = new Text("");
		
		//Menu
		VBox menu = new VBox();
		menuOptions = new ToggleGroup();
		Text menuText = new Text("Menu : \n");
		menuText.setFill(Color.RED);
		registerButtonInMenu = new RadioButton("Register");
		loginButtonInMenu = new RadioButton("Login");
		serchButtonInMenu = new RadioButton("Search game");
		addButtonInMenu = new RadioButton("Add game");
		updateGameInMenu = new RadioButton("Update game");
		updatePersonalInfoButtonInMenu = new RadioButton("Update personal info");
		logoutButtonInMenu = new RadioButton("Logout");
		registerButtonInMenu.setToggleGroup(menuOptions);
		loginButtonInMenu.setToggleGroup(menuOptions);
		serchButtonInMenu.setToggleGroup(menuOptions);
		addButtonInMenu.setToggleGroup(menuOptions);
		updateGameInMenu.setToggleGroup(menuOptions);
		updatePersonalInfoButtonInMenu.setToggleGroup(menuOptions);
		logoutButtonInMenu.setToggleGroup(menuOptions);
		menu.getChildren().addAll(menuText, registerButtonInMenu, loginButtonInMenu, serchButtonInMenu, addButtonInMenu, updateGameInMenu, updatePersonalInfoButtonInMenu, logoutButtonInMenu);
		menu.setAlignment(Pos.CENTER_LEFT);
		
		//Register
		textFieldOfFirstName = new TextField();
		textFieldOfLastName = new TextField();
		genderOptions = new ToggleGroup();
		radioButtonOfMaleOption = new RadioButton("Male");
		radioButtonOfFemaleOption = new RadioButton("Female");
		radioButtonOfMaleOption.setToggleGroup(genderOptions);
		radioButtonOfFemaleOption.setToggleGroup(genderOptions);
		textFieldOfEmail = new TextField();
		textFieldOfUserID = new TextField();
		textFieldOfPassword = new TextField();
		registerButton = new Button("Register");
		
		//Login
		loginButton = new Button("Login");
		
		//Search
		textToSearch = new Text("Choose according to what you are looking for:");
		textMakafToManufactureYear = new Text("-");
		textMakafToPrice = new Text("-");
		textToSearch.setFill(Color.RED);
		checkBoxOfGameName = new CheckBox();
		checkBoxOfDistributionCompany = new CheckBox();
		checkBoxOfCreativeCompany = new CheckBox();
		checkBoxOfGenre = new CheckBox();
		checkBoxOfManufactureYear = new CheckBox();
		checkBoxOfPrice = new CheckBox();
		textFieldOfGameName = new TextField();
		textFieldOfDistributionCompany = new TextField();
		textFieldOfCreativeCompany = new TextField();
		textFieldOfStartManufactureYear = new TextField();
		textFieldOfEndManufactureYear = new TextField();
		textFieldOfStartPrice = new TextField();
		textFieldOfEndPrice = new TextField();
		comboBoxOfGenres = new ComboBox<String>();
		comboBoxOfGenres.getItems().add("Action");
		comboBoxOfGenres.getItems().add("Adventure");
		comboBoxOfGenres.getItems().add("Driving");
		comboBoxOfGenres.getItems().add("Roles");
		comboBoxOfGenres.getItems().add("Simulation");
		comboBoxOfGenres.getItems().add("Sports");
		comboBoxOfGenres.getItems().add("Strategy");
		searchButton = new Button("Search");
		
		//Add
		textOfNameOfSeries = new Text("Name of series: ");
		textReqCharsForSeriesName = new Text("At least one char");
		textFieldOfDistributionDate = new TextField();
		textFieldOfTrailer = new TextField();
		textFieldOfDescription = new TextField();
		textFieldOfManufactureYear = new TextField();
		textFieldOfSeriesName = new TextField();
		textFieldOfPrice = new TextField();
		checkBoxOfPartOfSeries = new CheckBox();
		addButton = new Button("Add");
		
		//Update game
		namesOfAllGames = new ComboBox<String>();
		selectGameButton = new Button("Select");
		updateGameButton = new Button("Update");
		
		//Update personal info
		savePersonalInfoButton = new Button("Save");
		
		//Logout
		textToLogout = new Text("Are you sure you want to logout?");
		textToLogout.setFill(Color.RED);
		yesButton = new Button("Yes");
		noButton = new Button("No");
		
	//Make the root and add the relevant in the first time
		root = new BorderPane();
		root.setLeft(menu);
		root.setTop(helloText);
		
	//Make the scene
		scene = new Scene(root, 1200, 600);
		
	//Connect scene to stage
		stage.setScene(scene);
		
	//Show the stage
		stage.show();
	}
	
//Gets
	//Menu
	public RadioButton getRegisterButtonInMenu() {
		return registerButtonInMenu;
	}
	public RadioButton getLoginButtonInMenu() {
		return loginButtonInMenu;
	}
	public RadioButton getSerchButtonInMenu() {
		return serchButtonInMenu;
	}
	public RadioButton getAddButtonInMenu() {
		return addButtonInMenu;
	}
	public RadioButton getUpdateGameInMenu() {
		return updateGameInMenu;
	}
	public RadioButton getUpdatePersonalInfoButtonInMenu() {
		return updatePersonalInfoButtonInMenu;
	}
	public RadioButton getLogoutButtonInMenu() {
		return logoutButtonInMenu;
	}
	//Register
	public TextField getTextFieldOfFirstName() {
		return textFieldOfFirstName;
	}
	public TextField getTextFieldOfLastName() {
		return textFieldOfLastName;
	}
	public RadioButton getRadioButtonOfMaleOption() {
		return radioButtonOfMaleOption;
	}
	public RadioButton getRadioButtonOfFemaleOption() {
		return radioButtonOfFemaleOption;
	}
	public TextField getTextFieldOfEmail() {
		return textFieldOfEmail;
	}
	public TextField getTextFieldOfUserID() {
		return textFieldOfUserID;
	}
	public TextField getTextFieldOfPassword() {
		return textFieldOfPassword;
	}
	public Button getButtonOfRegister() {
		return registerButton;
	}
	public ToggleGroup getGenderOptions() {
		return genderOptions;
	}
	//Login
	public Button getButtonOfLogin() {
		return loginButton;
	}
	//Search
	public Text getTextToSearch() {
		return textToSearch;
	}
	public Text getTextMakafToManufactureYear() {
		return textMakafToManufactureYear;
	}
	public Text getTextMakafToPrice() {
		return textMakafToPrice;
	}
	public CheckBox getCheckBoxOfGameName() {
		return checkBoxOfGameName;
	}
	public CheckBox getCheckBoxOfDistributionCompany() {
		return checkBoxOfDistributionCompany;
	}
	public CheckBox getCheckBoxOfCreativeCompany() {
		return checkBoxOfCreativeCompany;
	}
	public CheckBox getCheckBoxOfGenre() {
		return checkBoxOfGenre;
	}
	public CheckBox getCheckBoxOfManufactureYear() {
		return checkBoxOfManufactureYear;
	}
	public CheckBox getCheckBoxOfPrice() {
		return checkBoxOfPrice;
	}
	public TextField getTextFieldOfGameName() {
		return textFieldOfGameName;
	}
	public TextField getTextFieldOfDistributionCompany() {
		return textFieldOfDistributionCompany;
	}
	public TextField getTextFieldOfCreativeCompany() {
		return textFieldOfCreativeCompany;
	}
	public TextField getTextFieldOfStartManufactureYear() {
		return textFieldOfStartManufactureYear;
	}
	public TextField getTextFieldOfEndManufactureYear() {
		return textFieldOfEndManufactureYear;
	}
	public TextField getTextFieldOfStartPrice() {
		return textFieldOfStartPrice;
	}
	public TextField getTextFieldOfEndPrice() {
		return textFieldOfEndPrice;
	}
	public ComboBox<String> getComboBoxOfGenres() {
		return comboBoxOfGenres;
	}
	public ComboBox<String> getComboBoxOfGenresFromBeginning() {
		comboBoxOfGenres.getItems().clear();
		comboBoxOfGenres.getItems().add("Action");
		comboBoxOfGenres.getItems().add("Adventure");
		comboBoxOfGenres.getItems().add("Driving");
		comboBoxOfGenres.getItems().add("Roles");
		comboBoxOfGenres.getItems().add("Simulation");
		comboBoxOfGenres.getItems().add("Sports");
		comboBoxOfGenres.getItems().add("Strategy");
		return comboBoxOfGenres;
	}
	public Button getSearchButton() {
		return searchButton;
	}
	//Add
	public Text getTextOfNameOfSeries() {
		return textOfNameOfSeries;
	}
	public Text getTextReqCharsForSeriesName() {
		return textReqCharsForSeriesName;
	}
	public TextField getTextFieldOfDistributionDate() {
		return textFieldOfDistributionDate;
	}
	public TextField getTextFieldOfTrailer() {
		return textFieldOfTrailer;
	}
	public TextField getTextFieldOfDescription() {
		return textFieldOfDescription;
	}
	public TextField getTextFieldOfManufactureYear() {
		return textFieldOfManufactureYear;
	}
	public TextField getTextFieldOfSeriesName() {
		return textFieldOfSeriesName;
	}
	public TextField getTextFieldOfPrice() {
		return textFieldOfPrice;
	}
	public CheckBox getCheckBoxOfPartOfSeries() {
		return checkBoxOfPartOfSeries;
	}
	public Button getAddButton() {
		return addButton;
	}
	//Update game
	public ComboBox<String> getComboBoxOfNamesOfAllGamesFromBegining() throws Exception {
		int counterOfGames = 0;
		namesOfAllGames.getItems().clear();
		String dbUrl = "jdbc:mysql://localhost:3306/my_db";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, "root", "ac230196");
		}
		catch(Exception e) {
			throw new Exception("Data base not found");
		}
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM game ORDER BY game_name");
		while(rs.next()) {
			counterOfGames++;
			namesOfAllGames.getItems().add(rs.getString("game_name"));
		}
		conn.close();
		conn = null;
		rs.close();
		rs = null;
		stmt.close();
		stmt = null;
		if(counterOfGames == 0) {
			throw new Exception("There are no games yet");
		}
		return namesOfAllGames;
	}
	public ComboBox<String> getComboBoxOfNamesOfAllGames() {
		return namesOfAllGames;
	}
	public Button getSelectGameButton() {
		return selectGameButton;
	}
	public Button getUpdateGameButton() {
		return updateGameButton;
	}	
	//Update personal info
	public Button getSavePersonalInfoButton() {
		return savePersonalInfoButton;
	}
	//Logout
	public Text getTextToLogout() {
		return textToLogout;
	}
	public Button getYesButton() {
		return yesButton;
	}
	public Button getNoButton() {
		return noButton;
	}
	
//Clear fields and choice options
	//Menu
	public void resetMenuSelection() {
		menuOptions.selectToggle(null);
	}
	//Register
	public void clearTextFieldOfFirstName() {
		this.textFieldOfFirstName.setText("");
	}
	public void clearTextFieldOfLastName() {
		this.textFieldOfLastName.setText("");
	}
	public void resetGenderOptions() {
		genderOptions.selectToggle(null);
	}
	public void clearTextFieldOfEmail() {
		this.textFieldOfEmail.setText("");
	}
	public void clearTextFieldOfUserID() {
		this.textFieldOfUserID.setText("");
	}
	public void clearTextFieldOfPassword() {
		this.textFieldOfPassword.setText("");
	}
	//Search
	public void clearCheckBoxOfGameName() {
		this.checkBoxOfGameName.setSelected(false);
	}
	public void clearCheckBoxOfDistributionCompany() {
		this.checkBoxOfDistributionCompany.setSelected(false);
	}
	public void clearCheckBoxOfCreativeCompany() {
		this.checkBoxOfCreativeCompany.setSelected(false);
	}
	public void clearCheckBoxOfGenre() {
		this.checkBoxOfGenre.setSelected(false);
	}
	public void clearCheckBoxOfManufactureYear() {
		this.checkBoxOfManufactureYear.setSelected(false);
	}
	public void clearCheckBoxOfPrice() {
		this.checkBoxOfPrice.setSelected(false);
	}
	public void clearTextFieldOfGameName() {
		this.textFieldOfGameName.setText("");
	}
	public void clearTextFieldOfDistributionCompany() {
		this.textFieldOfDistributionCompany.setText("");
	}
	public void clearTextFieldOfCreativeCompany() {
		this.textFieldOfCreativeCompany.setText("");
	}
	public void clearTextFieldOfStartManufactureYear() {
		this.textFieldOfStartManufactureYear.setText("");
	}
	public void clearTextFieldOfEndManufactureYear() {
		this.textFieldOfEndManufactureYear.setText("");
	}
	public void clearTextFieldOfStartPrice() {
		this.textFieldOfStartPrice.setText("");
	}
	public void clearTextFieldOfEndPrice() {
		this.textFieldOfEndPrice.setText("");
	}
	//Add
	public void clearTextFieldOfDistributionDate() {
		this.textFieldOfDistributionDate.setText("");
	}
	public void clearTextFieldOfTrailer() {
		this.textFieldOfTrailer.setText("");
	}
	public void clearTextFieldOfDescription() {
		this.textFieldOfDescription.setText("");
	}
	public void clearTextFieldOfManufactureYear() {
		this.textFieldOfManufactureYear.setText("");
	}
	public void clearTextFieldOfSeriesName() {
		this.textFieldOfSeriesName.setText("");
	}
	public void clearTextFieldOfPrice() {
		this.textFieldOfPrice.setText("");
	}
	public void clearCheckBoxOfPartOfSeries() {
		this.checkBoxOfPartOfSeries.setSelected(false);
	}	
	
//Change details to show
	//In center
	public void showInCenter(GridPane newDetailsToShow) {
		root.setCenter(newDetailsToShow);
	}
	public void showInCenter(Text newDetailsToShow) {
		root.setCenter(newDetailsToShow);
	}
	public void showInCenter(VBox newDetailsToShow) {
		root.setCenter(newDetailsToShow);
	}
	//In top
	public void showInTop(Text newDetailsToShow) {
		root.setTop(newDetailsToShow);
	}
	//In left
	public void showInLeft(GridPane newDetailsToShow) {
		root.setLeft(newDetailsToShow);
	}
	//In right
	public void showInRight(GridPane newDetailsToShow) {
		root.setRight(newDetailsToShow);
	}
	public void showInRight(Text newDetailsToShow) {
		root.setRight(newDetailsToShow);
	}
	public void showNothingInRight() {
		root.setRight(new Text(""));
	}
	//In bottom
	public void showInBottom(Text textToShow) {
		root.setBottom(textToShow);
	}
	public void showNothingInBottom() {
		root.setBottom(new Text(""));
	}
	//Connected mode
	public void setConnectedMode(String name) {
		helloText = new Text("Hello " + name);
		helloText.setFill(Color.GREEN);
		showInTop(helloText);
		getRegisterButtonInMenu().setVisible(false);
		getLoginButtonInMenu().setVisible(false);
		getAddButtonInMenu().setVisible(true);
		getUpdateGameInMenu().setVisible(true);
		getLogoutButtonInMenu().setVisible(true);
		getUpdatePersonalInfoButtonInMenu().setVisible(true);
	}
	//Guest mode
	public void setGuestMode() {
		helloText = new Text("Hello guest");
		helloText.setFill(Color.GREEN);
		showInTop(helloText);
		getRegisterButtonInMenu().setVisible(true);
		getLoginButtonInMenu().setVisible(true);
		getAddButtonInMenu().setVisible(false);
		getUpdateGameInMenu().setVisible(false);
		getLogoutButtonInMenu().setVisible(false);
		getUpdatePersonalInfoButtonInMenu().setVisible(false);
	}
	
//Functions to buttons
	//Menu
	public void clickOnRegisterInMenu(EventHandler<ActionEvent> event) {
		registerButtonInMenu.setOnAction(event);
	}
	public void clickOnLoginInMenu(EventHandler<ActionEvent> event) {
		loginButtonInMenu.setOnAction(event);
	}
	public void clickOnSearchGameInMenu(EventHandler<ActionEvent> event) {
		serchButtonInMenu.setOnAction(event);
	}
	public void clickOnAddGameInMenu(EventHandler<ActionEvent> event) {
		addButtonInMenu.setOnAction(event);
	}
	public void clickOnUpdateGameInMenu(EventHandler<ActionEvent> event) {
		updateGameInMenu.setOnAction(event);
	}
	public void clickOnUpdatePersonalInfoInMenu(EventHandler<ActionEvent> event) {
		updatePersonalInfoButtonInMenu.setOnAction(event);
	}
	public void clickOnLogoutInMenu(EventHandler<ActionEvent> event) {
		logoutButtonInMenu.setOnAction(event);
	}
	//Register
	public void clickOnRegisterInRegisterWindow(EventHandler<ActionEvent> event) {
		registerButton.setOnAction(event);
	}
	//Login
	public void clickOnLoginInLoginWindow(EventHandler<ActionEvent> event) {
		loginButton.setOnAction(event);
	}
	//Search
	public void clickOnGameNameInSearch(EventHandler<ActionEvent> event) {
		checkBoxOfGameName.setOnAction(event);
	}
	public void clickOnDistributionCompanyInSearch(EventHandler<ActionEvent> event) {
		checkBoxOfDistributionCompany.setOnAction(event);
	}
	public void clickOnCreativeCompanyInSearch(EventHandler<ActionEvent> event) {
		checkBoxOfCreativeCompany.setOnAction(event);
	}
	public void clickOnGenreInSearch(EventHandler<ActionEvent> event) {
		checkBoxOfGenre.setOnAction(event);
	}
	public void clickOnManufactureYearInSearch(EventHandler<ActionEvent> event) {
		checkBoxOfManufactureYear.setOnAction(event);
	}
	public void clickOnPriceInSearch(EventHandler<ActionEvent> event) {
		checkBoxOfPrice.setOnAction(event);
	}
	public void clickOnSearchInSearchWindow(EventHandler<ActionEvent> event) {
		searchButton.setOnAction(event);
	}
	//Add
	public void clickOnPartOfSeriesInAddWindow(EventHandler<ActionEvent> event) {
		checkBoxOfPartOfSeries.setOnAction(event);
	}
	public void clickOnAddInAddWindow(EventHandler<ActionEvent> event) {
		addButton.setOnAction(event);
	}
	//Update game
	public void clickOnSelectGameInUpdateGameWindow(EventHandler<ActionEvent> event) {
		selectGameButton.setOnAction(event);
	}
	public void clickOnUpdateGameInUpdateGameWindow(EventHandler<ActionEvent> event) {
		updateGameButton.setOnAction(event);
	}	
	//Update personal info
	public void clickOnUpdateInUpdatePersonalInfoWindow(EventHandler<ActionEvent> event) {
		savePersonalInfoButton.setOnAction(event);
	}
	//Logout
	public void clickOnYesButtomInLogoutWindow(EventHandler<ActionEvent> event) {
		yesButton.setOnAction(event);
	}
	public void clickOnNoButtomInLogoutWindow(EventHandler<ActionEvent> event) {
		noButton.setOnAction(event);
	}
}
