package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View {
	private Stage stage;
    private BorderPane rootPane;
	
	// Menu
	private VBox menuLayout;
	private Label menuTitleLabel;
	private Button loginButtonToMenu, registerButtonToMenu, createEventButtonToMenu, editEventDateButtonToMenu, cancelEventButtonToMenu, makeInvitationToEventButtonToMenu, 
						showMyInvitationsButtonToMenu, changeUsersPermissionsButtonToMenu, changeMyInformationButtonToMenu, showEventSeatsButtonToMenu, 
						addHealthStatusButtonToMenu, initDataBaseButtonToMenu, logoutButtonToMenu;
	
	// Login
	private Text loginFormHeadLine, emailTextToLogin;
	private TextField textFieldOfEmailToLogin;
	private Button loginButton;
	
	// Register
	private Text registerFormHeadLine, nameTextToRegister, emailTextToRegister, phoneTextToRegister;
	private TextField textFieldOfNameToRegister, textFieldOfEmailToRegister, textFieldOfPhoneToRegister;
	private Button registerButton;
	
	// Create event
	private Text createEventFormHeadLine, nameTextToCreateEvent, cityTextToCreateEvent, neighborhoodTextToCreateEvent, neighborhoodNumberTextToCreateEvent, 
						yearTextToCreateEvent, monthTextToCreateEvent, dayTextToCreateEvent, hourTextToCreateEvent, minuteTextToCreateEvent, numberOfRowsTextToCreateEvent, 
						maxNumberOfSeatsInRowTextToCreateEvent, setSeatsCostsFormHeadLine, setSeatsCostsFormSubTitle, setTextToSetCostsToSeats, 
						numberTextToSetCostsToSeats, costToTextToSetCostsToSeats, notValidRowOrColOptionTextToSetCostsToSeats, 
						notValidNumberTextToSetCostsToSeats, notValidCostTextToSetCostsToSeats;
	private TextField textFieldOfNameToCreateEvent, textFieldOfCityToCreateEvent, textFieldOfNeighborhoodToCreateEvent, textFieldOfNeighborhoodNumberToCreateEvent, 
						textFieldOfYearToCreateEvent, textFieldOfMonthToCreateEvent, textFieldOfDayToCreateEvent, textFieldOfHourToCreateEvent, 
						textFieldOfMinuteToCreateEvent,	textFieldOfNumberOfRowsToCreateEvent, textFieldOfMaxNumberOfSeatsInRowToCreateEvent, 
						textFieldOfRowOrColNumToSetPrices, textFieldOfCostToRowOrColToSetPrices;
	private ComboBox<String> comboBoxToRowOrColOption;
	private Button continueToSetSeatsCostsButton, setRowOrColSeatsCostButton, createEventButton;
	
	// Edit event date
	private Text selectEventToEditDateFormHeadLine, selectEventToEditFormEventIdSubTitle, selectEventToEditFormEventDetailsSubTitle, selectEventIdTextInSelectEventToEdit, 
						editEventDateFormHeadLine, yearTextToEditEvent, monthTextToEditEvent, dayTextToEditEvent, hourTextToEditEvent, minuteTextToEditEvent;
	private TextField textFieldOfEventIdToEditEventDate, textFieldOfYearToEditEventDate, textFieldOfMonthToEditEventDate, textFieldOfDayToEditEventDate, 
						textFieldOfHourToEditEventDate, textFieldOfMinuteToEditEventDate;
	private Button selectEventToEditDateButton, editEventDateButton;
	
	// Cancel event
	private Text cancelEventFormHeadLine, cancelEventFormEventIdSubTitle, cancelEventFormEventDetailsSubTitle, eventIdTextToCancelEvent;
	private TextField textFieldOfEventIdToCancel;
	private Button cancelEventButton;
	
	// Make invitation to event
	private Text selectEventToMakeInvitationFormHeadLine, makeInvitationFormEventIdSubTitle, makeInvitationFormEventDetailsSubTitle, eventIdTextToMakeInvitation, 
						makeInvitationToEventFormHeadLine, numberOfSeatsTextToMakeInvitationToEvent, healthStatusTextToMakeInvitationToEvent, 
						seatPriceTextToMakeInvitationToEvent, offerSeatsInvitationToEventFormHeadLine, offerSeatsInvitationToEventFormRowNumSubTitle, 
						offerSeatsInvitationToEventFormSeatsNumAndCostsSubTitle;
	private TextField textFieldOfEventIdToMakeInvitation, textFieldOfNumOfSeatsInInvitationToMakeInvitation, textFieldOfSeatPriceToMakeInvitation;
	private ComboBox<String> comboBoxOfHealthStatusToMakeInvitation;
	private Button selectEventToMakeInvitationToButton, showOfferSeatsInvitationButton, makeInvitationToEventButton, dontMakeInvitationToEventButton;
	
	// Show my invitations
	private Text showMyInvitationsFormHeadLine;
	
	// Change users permissions
	private Text changeUserPermissionsFormHeadLine, emailTextToChangeUserPermissions, newPermissionsOfTheUserTextToChangeUserPermissions;
	private TextField textFieldOfEmailToChangeUsersPermissions, textFieldOfPermissionToChangeUsersPermissions;
	private Button changeUserPermissionsButton;
	
	// Change my information
	private Text changeMyInformationFormHeadLine, nameTextToChangeMyInformation, emailTextToChangeMyInformation, phoneTextToChangeMyInformation;
	private TextField textFieldOfNameToChangeMyInformation, textFieldOfEmailToChangeMyInformation, textFieldOfPhoneToChangeMyInformation;
	private Button changeMyInformationButton;
	
	// Show event seats
	private Text selectEventToShowFormHeadLine, selectEventToShowFormEventIdSubTitle, selectEventToShowFormEventDetailsSubTitle, selectedEventIdTextToShowEventSeats, 
						showEventSeatsFormHeadLine, legendTextToShowEventSeats;
	private TextField textFieldOfEventIdToShowEventSeats;
	private Button showEventSeatsButton;
	
	// Add health status
	private Text addHelathStatusFormHeadLine, nameTextToAddHealthStatus, spaceNeededTextToAddHealthStatus;
	private TextField textFieldOfNameToAddHealthStatus, textFieldOfSpaceToAddHealthStatus;
	private Button addHealthStatusButton;
	
	// Constructor
	public View(Stage stage) {
		this.stage = stage;
		initializeView();
	}
	
	// Initialize view
	public void initializeView() {
		// Make root pane
		rootPane = new BorderPane();
		String css = getClass().getResource("/css/CSSDesign.css").toExternalForm();
		rootPane.getStylesheets().add(css);
		
		// Menu
		menuLayout = new VBox();
		menuLayout.getStyleClass().add("menu");
        menuTitleLabel = new Label("Menu");
        menuTitleLabel.getStyleClass().add("menu-title");
		loginButtonToMenu = new Button("Login");
		loginButtonToMenu.getStyleClass().add("menu-button");
		registerButtonToMenu = new Button("Register");
		registerButtonToMenu.getStyleClass().add("menu-button");
		createEventButtonToMenu = new Button("Create event");
		createEventButtonToMenu.getStyleClass().add("menu-button");
		editEventDateButtonToMenu = new Button("Edit event date");
		editEventDateButtonToMenu.getStyleClass().add("menu-button");
		cancelEventButtonToMenu = new Button("Cancel event");
		cancelEventButtonToMenu.getStyleClass().add("menu-button");
		makeInvitationToEventButtonToMenu = new Button("Make invitation to event");
		makeInvitationToEventButtonToMenu.getStyleClass().add("menu-button");
		showMyInvitationsButtonToMenu = new Button("Show my invitations");
		showMyInvitationsButtonToMenu.getStyleClass().add("menu-button");
		changeUsersPermissionsButtonToMenu = new Button("Change users permissions");
		changeUsersPermissionsButtonToMenu.getStyleClass().add("menu-button");
		changeMyInformationButtonToMenu = new Button("Change my information");
		changeMyInformationButtonToMenu.getStyleClass().add("menu-button");
		showEventSeatsButtonToMenu = new Button("Show events seats");
		showEventSeatsButtonToMenu.getStyleClass().add("menu-button");
		addHealthStatusButtonToMenu = new Button("Add health status");
		addHealthStatusButtonToMenu.getStyleClass().add("menu-button");
		initDataBaseButtonToMenu = new Button("Init data base");
		initDataBaseButtonToMenu.getStyleClass().add("menu-button");
		logoutButtonToMenu = new Button("Logout");
		logoutButtonToMenu.getStyleClass().add("menu-button");
		
		// Login
		loginFormHeadLine = new Text("Login form");
		loginFormHeadLine.setId("headLineInForm");
		emailTextToLogin = new Text("Email: ");
		emailTextToLogin.setId("textInForm");
		textFieldOfEmailToLogin = new TextField();
		loginButton = new Button("Login");
		
		// Register
		registerFormHeadLine = new Text("Register form");
		registerFormHeadLine.setId("headLineInForm");
		nameTextToRegister =  new Text("Name: ");
		nameTextToRegister.setId("textInForm");
		emailTextToRegister = new Text("Email: ");
		emailTextToRegister.setId("textInForm");
		phoneTextToRegister = new Text("Phone: ");
		phoneTextToRegister.setId("textInForm");
		textFieldOfNameToRegister = new TextField();
		textFieldOfEmailToRegister = new TextField();
		textFieldOfPhoneToRegister = new TextField();
		registerButton = new Button("Register");
		
		// Create event
		createEventFormHeadLine = new Text("Create event form");
		createEventFormHeadLine.setId("headLineInForm");
		nameTextToCreateEvent = new Text("Name: ");
		nameTextToCreateEvent.setId("textInForm");
		cityTextToCreateEvent =  new Text("City: ");
		cityTextToCreateEvent.setId("textInForm");
		neighborhoodTextToCreateEvent =  new Text("Neighborhood: ");
		neighborhoodTextToCreateEvent.setId("textInForm");
		neighborhoodNumberTextToCreateEvent =  new Text("Neighborhood number: ");
		neighborhoodNumberTextToCreateEvent.setId("textInForm");
		yearTextToCreateEvent = new Text("Year: ");
		yearTextToCreateEvent.setId("textInForm");
		monthTextToCreateEvent = new Text("Month: ");
		monthTextToCreateEvent.setId("textInForm");
		dayTextToCreateEvent = new Text("Day: ");
		dayTextToCreateEvent.setId("textInForm");
		hourTextToCreateEvent = new Text("Hour: ");
		hourTextToCreateEvent.setId("textInForm");
		minuteTextToCreateEvent = new Text("Minute: ");
		minuteTextToCreateEvent.setId("textInForm");
		numberOfRowsTextToCreateEvent = new Text("Number of rows: ");
		numberOfRowsTextToCreateEvent.setId("textInForm");
		maxNumberOfSeatsInRowTextToCreateEvent  = new Text("Max number of seats in row: ");
		maxNumberOfSeatsInRowTextToCreateEvent.setId("textInForm");
		setSeatsCostsFormHeadLine = new Text("Set seats costs form");
		setSeatsCostsFormHeadLine.setId("headLineInForm");
		setSeatsCostsFormSubTitle = new Text("0 for seat not exist");
		setSeatsCostsFormSubTitle.setId("subTitleInForm");
		setTextToSetCostsToSeats = new Text("Set ");
		setTextToSetCostsToSeats.setId("textInForm");
		numberTextToSetCostsToSeats = new Text("nubmer");
		numberTextToSetCostsToSeats.setId("textInForm");
		costToTextToSetCostsToSeats = new Text("cost to");
		costToTextToSetCostsToSeats.setId("textInForm");
		notValidRowOrColOptionTextToSetCostsToSeats = new Text("Invalid row or column option!");
		notValidRowOrColOptionTextToSetCostsToSeats.setId("errorTextInForm");
		notValidNumberTextToSetCostsToSeats = new Text("Invalid number of row / column!");
		notValidNumberTextToSetCostsToSeats.setId("errorTextInForm");
		notValidCostTextToSetCostsToSeats = new Text("Invalid cost!");
		notValidCostTextToSetCostsToSeats.setId("errorTextInForm");
		textFieldOfNameToCreateEvent = new TextField();
		textFieldOfCityToCreateEvent = new TextField();
		textFieldOfNeighborhoodToCreateEvent = new TextField();
		textFieldOfNeighborhoodNumberToCreateEvent = new TextField();
		textFieldOfYearToCreateEvent = new TextField();
		textFieldOfMonthToCreateEvent = new TextField();
		textFieldOfDayToCreateEvent = new TextField();
		textFieldOfHourToCreateEvent = new TextField();
		textFieldOfMinuteToCreateEvent = new TextField();
		textFieldOfNumberOfRowsToCreateEvent = new TextField();
		textFieldOfMaxNumberOfSeatsInRowToCreateEvent = new TextField();
		textFieldOfRowOrColNumToSetPrices = new TextField();
		textFieldOfCostToRowOrColToSetPrices = new TextField();
		comboBoxToRowOrColOption = new ComboBox<String>();
		comboBoxToRowOrColOption.getItems().addAll("Row", "Column");
		continueToSetSeatsCostsButton = new Button("Continue to set seats costs");
		setRowOrColSeatsCostButton = new Button("Set cost");
		createEventButton = new Button("Create Event");
		
		// Edit event date
		selectEventToEditDateFormHeadLine = new Text("Select event to edit date form");
		selectEventToEditDateFormHeadLine.setId("headLineInForm");
		selectEventToEditFormEventIdSubTitle = new Text("Event ID");
		selectEventToEditFormEventIdSubTitle.setId("subTitleInForm");
		selectEventToEditFormEventDetailsSubTitle = new Text("Event details");
		selectEventToEditFormEventDetailsSubTitle.setId("subTitleInForm");
		selectEventIdTextInSelectEventToEdit = new Text("Enter selected event id: ");
		selectEventIdTextInSelectEventToEdit.setId("textInForm");
		editEventDateFormHeadLine = new Text("Edit event date form");
		editEventDateFormHeadLine.setId("headLineInForm");
		yearTextToEditEvent = new Text("Year: ");
		yearTextToEditEvent.setId("textInForm");
		monthTextToEditEvent = new Text("Month: ");
		monthTextToEditEvent.setId("textInForm");
		dayTextToEditEvent = new Text("Day: ");
		dayTextToEditEvent.setId("textInForm");
		hourTextToEditEvent = new Text("Hour: ");
		hourTextToEditEvent.setId("textInForm");
		minuteTextToEditEvent = new Text("Minute: ");
		minuteTextToEditEvent.setId("textInForm");
		textFieldOfEventIdToEditEventDate = new TextField();
		textFieldOfYearToEditEventDate = new TextField();
		textFieldOfMonthToEditEventDate = new TextField();
		textFieldOfDayToEditEventDate = new TextField();
		textFieldOfHourToEditEventDate = new TextField();
		textFieldOfMinuteToEditEventDate = new TextField();
		selectEventToEditDateButton = new Button("Select Event To Edit Date");
		editEventDateButton = new Button("Edit Event Date");
		
		// Cancel event
		cancelEventFormHeadLine = new Text("Cancel event form");
		cancelEventFormHeadLine.setId("headLineInForm");
		cancelEventFormEventIdSubTitle = new Text("Event ID");
		cancelEventFormEventIdSubTitle.setId("subTitleInForm");
		cancelEventFormEventDetailsSubTitle = new Text("Event details");
		cancelEventFormEventDetailsSubTitle.setId("subTitleInForm");
		eventIdTextToCancelEvent = new Text("Enter selected event id: ");
		eventIdTextToCancelEvent.setId("textInForm");
		textFieldOfEventIdToCancel = new TextField();
		cancelEventButton = new Button("Cancel Event");
		
		// Make invitation to event
		selectEventToMakeInvitationFormHeadLine = new Text("Select event to make invitation to form");
		selectEventToMakeInvitationFormHeadLine.setId("headLineInForm");
		makeInvitationFormEventIdSubTitle = new Text("Event ID");
		makeInvitationFormEventIdSubTitle.setId("subTitleInForm");
		makeInvitationFormEventDetailsSubTitle = new Text("Event details");
		makeInvitationFormEventDetailsSubTitle.setId("subTitleInForm");
		eventIdTextToMakeInvitation = new Text("Enter selected event id: ");
		eventIdTextToMakeInvitation.setId("textInForm");
		makeInvitationToEventFormHeadLine = new Text("Make invitation to event form");
		makeInvitationToEventFormHeadLine.setId("headLineInForm");
		numberOfSeatsTextToMakeInvitationToEvent = new Text("Number of seats: ");
		numberOfSeatsTextToMakeInvitationToEvent.setId("textInForm");
		healthStatusTextToMakeInvitationToEvent = new Text("Health status: ");
		healthStatusTextToMakeInvitationToEvent.setId("textInForm");
		seatPriceTextToMakeInvitationToEvent = new Text("Seat price: ");
		seatPriceTextToMakeInvitationToEvent.setId("textInForm");
		offerSeatsInvitationToEventFormHeadLine = new Text("Offer seats invitation to event form");
		offerSeatsInvitationToEventFormHeadLine.setId("headLineInForm");
		offerSeatsInvitationToEventFormRowNumSubTitle = new Text("Row number");
		offerSeatsInvitationToEventFormRowNumSubTitle.setId("subTitleInForm");
		offerSeatsInvitationToEventFormSeatsNumAndCostsSubTitle = new Text("Seat numbers and costs");
		offerSeatsInvitationToEventFormSeatsNumAndCostsSubTitle.setId("subTitleInForm");
		textFieldOfEventIdToMakeInvitation = new TextField();
		textFieldOfNumOfSeatsInInvitationToMakeInvitation = new TextField();
		textFieldOfSeatPriceToMakeInvitation = new TextField();
		comboBoxOfHealthStatusToMakeInvitation = new ComboBox<String>();
		selectEventToMakeInvitationToButton = new Button("Select Event To Make Invitation To");
		showOfferSeatsInvitationButton = new Button("Show Offer Seats To Invitation");
		makeInvitationToEventButton = new Button("Make Invitation To Event");
		dontMakeInvitationToEventButton = new Button("Don't Make Invitation To Event");
		
		// Show my invitations
		showMyInvitationsFormHeadLine = new Text("My invitations form");
		showMyInvitationsFormHeadLine.setId("headLineInForm");
		
		// Change users permissions
		changeUserPermissionsFormHeadLine = new Text("Change user permissions form");
		changeUserPermissionsFormHeadLine.setId("headLineInForm");
		emailTextToChangeUserPermissions = new Text("Email of user to change permissions:");
		emailTextToChangeUserPermissions.setId("textInForm");
		newPermissionsOfTheUserTextToChangeUserPermissions = new Text("New permissions of the user:");
		newPermissionsOfTheUserTextToChangeUserPermissions.setId("textInForm");
		textFieldOfEmailToChangeUsersPermissions = new TextField();
		textFieldOfPermissionToChangeUsersPermissions = new TextField();
		changeUserPermissionsButton = new Button("Change User Permissions");
		
		// Change my information
		changeMyInformationFormHeadLine = new Text("Change my information form");
		changeMyInformationFormHeadLine.setId("headLineInForm");
		nameTextToChangeMyInformation = new Text("Name: ");
		nameTextToChangeMyInformation.setId("textInForm");
		emailTextToChangeMyInformation = new Text("Email: ");
		emailTextToChangeMyInformation.setId("textInForm");
		phoneTextToChangeMyInformation = new Text("Phone: ");
		phoneTextToChangeMyInformation.setId("textInForm");
		textFieldOfNameToChangeMyInformation = new TextField();
		textFieldOfEmailToChangeMyInformation = new TextField();
		textFieldOfPhoneToChangeMyInformation = new TextField();
		changeMyInformationButton = new Button("Change My Information");
		
		// Show event seats
		selectEventToShowFormHeadLine = new Text("Select event to show form");
		selectEventToShowFormHeadLine.setId("headLineInForm");
		selectEventToShowFormEventIdSubTitle = new Text("Event ID");
		selectEventToShowFormEventIdSubTitle.setId("subTitleInForm");
		selectEventToShowFormEventDetailsSubTitle = new Text("Event details");
		selectEventToShowFormEventDetailsSubTitle.setId("subTitleInForm");
		selectedEventIdTextToShowEventSeats = new Text("Enter selected event id: ");
		selectedEventIdTextToShowEventSeats.setId("textInForm");
		showEventSeatsFormHeadLine = new Text("Show event seats form");
		showEventSeatsFormHeadLine.setId("headLineInForm");
		legendTextToShowEventSeats = new Text("Legend: ");
		legendTextToShowEventSeats.setId("subTitleInForm");
		textFieldOfEventIdToShowEventSeats = new TextField();
		showEventSeatsButton = new Button("Show Event Seats");
		
		// Add health status
		addHelathStatusFormHeadLine = new Text("Add health status form");
		addHelathStatusFormHeadLine.setId("headLineInForm");
		nameTextToAddHealthStatus = new Text("Name: ");
		nameTextToAddHealthStatus.setId("textInForm");
		spaceNeededTextToAddHealthStatus = new Text("Space needed: ");
		spaceNeededTextToAddHealthStatus.setId("textInForm");
		textFieldOfNameToAddHealthStatus = new TextField();
		textFieldOfSpaceToAddHealthStatus = new TextField();
		addHealthStatusButton = new Button("Add Health Status");
		
		// Add the relevant to show
		menuLayout.getChildren().addAll(menuTitleLabel, loginButtonToMenu, registerButtonToMenu);
		menuLayout.setAlignment(Pos.CENTER_LEFT);
		
        // Create ScrollPane and set content
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(menuLayout);
        scrollPane.setFitToWidth(true); // Fit scroll pane width to scene width
        scrollPane.setFitToHeight(true); // Fit scroll pane height to scene height
		
		// Make the root
		rootPane.setLeft(scrollPane);
		rootPane.setTop(new Text(""));
		
		// Make the scene
		Scene scene = new Scene(rootPane, 1300, 700);
		
		// Connect scene to stage
		stage.setScene(scene);
	}
	
	// Show view
    public void showView() {
        stage.show();
    }
	
	// Gets for menu
    public VBox getMenuLayout() {
    	return menuLayout;
    }
    
    public Label getMenuTitleLabel() {
    	return menuTitleLabel;
    }
    
	public Button getLoginButtonToMenu() {
		return loginButtonToMenu;
	}
	
	public Button getRegisterButtonToMenu() {
		return registerButtonToMenu;
	}
	
	public Button getCreateEventButtonToMenu() {
		return createEventButtonToMenu;
	}
	
	public Button getEditEventDateButtonToMenu() {
		return editEventDateButtonToMenu;
	}
	
	public Button getCancelEventButtonToMenu() {
		return cancelEventButtonToMenu;
	}
	
	public Button getMakeInvitationToEventButtonToMenu() {
		return makeInvitationToEventButtonToMenu;
	}
	
	public Button getShowMyInvitationsButtonToMenu() {
		return showMyInvitationsButtonToMenu;
	}
	
	public Button getChangeUsersPermissionsButtonToMenu() {
		return changeUsersPermissionsButtonToMenu;
	}
	
	public Button getChangeMyInformationButtonToMenu() {
		return changeMyInformationButtonToMenu;
	}
	
	public Button getShowEventSeatsButtonToMenu() {
		return showEventSeatsButtonToMenu;
	}
	
	public Button getAddHealthStatusButtonToMenu() {
		return addHealthStatusButtonToMenu;
	}
	
	public Button getInitDataBaseButtonToMenu() {
		return initDataBaseButtonToMenu;
	}
	
	public Button getLogoutButtonToMenu() {
		return logoutButtonToMenu;
	}
	
	// Gets for login
	public Text getLoginFormHeadLine() {
		return loginFormHeadLine;
	}
	
	public Text getEmailTextToLogin() {
		return emailTextToLogin;
	}
		
	public TextField getTextFieldOfEmailToLogin() {
		return textFieldOfEmailToLogin;
	}
	
	public TextField getClearTextFieldOfEmailToLogin() {
		textFieldOfEmailToLogin.setText("");
		return textFieldOfEmailToLogin;
	}
	
	public Button getLoginButton() {
		return loginButton;
	}
	
	// Gets for register
	public Text getRegisterFormHeadLine() {
		return registerFormHeadLine;
	}
	
	public Text getNameTextToRegister() {
		return nameTextToRegister;
	}
	
	public Text getEmailTextToRegister() {
		return emailTextToRegister;
	}
	
	public Text getPhoneTextToRegister() {
		return phoneTextToRegister;
	}
	
	public TextField getTextFieldOfNameToRegister() {
		return textFieldOfNameToRegister;
	}
	
	public TextField getClearTextFieldOfNameToRegister() {
		textFieldOfNameToRegister.setText("");
		return textFieldOfNameToRegister;
	}

	public TextField getTextFieldOfEmailToRegister() {
		return textFieldOfEmailToRegister;
	}
	
	public TextField getClearTextFieldOfEmailToRegister() {
		textFieldOfEmailToRegister.setText("");
		return textFieldOfEmailToRegister;
	}
	
	public TextField getTextFieldOfPhoneToRegister() {
		return textFieldOfPhoneToRegister;
	}
	
	public TextField getClearTextFieldOfPhoneToRegister() {
		textFieldOfPhoneToRegister.setText("");
		return textFieldOfPhoneToRegister;
	}
	
	public Button getRegisterButton() {
		return registerButton;
	}
	
	// Gets for create event
	public Text getCreateEventFormHeadLine() {
		return createEventFormHeadLine;
	}
	
	public Text getNameTextToCreateEvent() {
		return nameTextToCreateEvent;
	}
	
	public Text getCityTextToCreateEvent() {
		return cityTextToCreateEvent;
	}
	
	public Text getNeighborhoodTextToCreateEvent() {
		return neighborhoodTextToCreateEvent;
	}
	
	public Text getNeighborhoodNumberTextToCreateEvent() {
		return neighborhoodNumberTextToCreateEvent;
	}
	
	public Text getYearTextToCreateEvent() {
		return yearTextToCreateEvent;
	}
	
	public Text getMonthTextToCreateEvent() {
		return monthTextToCreateEvent;
	}
	
	public Text getDayTextToCreateEvent() {
		return dayTextToCreateEvent;
	}
	
	public Text getHourTextToCreateEvent() {
		return hourTextToCreateEvent;
	}
	
	public Text getMinuteTextToCreateEvent() {
		return minuteTextToCreateEvent;
	}
	
	public Text getNumberOfRowsTextToCreateEvent() {
		return numberOfRowsTextToCreateEvent;
	}
	
	public Text getMaxNumberOfSeatsInRowTextToCreateEvent() {
		return maxNumberOfSeatsInRowTextToCreateEvent;
	}
	
	public Text getSetSeatsCostsFormHeadLine() {
		return setSeatsCostsFormHeadLine;
	}
	
	public Text getSetSeatsCostsFormSubTitle() {
		return setSeatsCostsFormSubTitle;
	}
	
	public Text getSetTextToSetCostsToSeats() {
		return setTextToSetCostsToSeats;
	}
	
	public Text getNumberTextToSetCostsToSeats() {
		return numberTextToSetCostsToSeats;
	}
	
	public Text getCostToTextToSetCostsToSeats() {
		return costToTextToSetCostsToSeats;
	}
	
	public Text getNotValidRowOrColOptionTextToSetCostsToSeatsAsNotVisible() {
		notValidRowOrColOptionTextToSetCostsToSeats.setVisible(false);
		return notValidRowOrColOptionTextToSetCostsToSeats;
	}
	
	public Text getNotValidNumberTextToSetCostsToSeatsAsNotVisible() {
		notValidNumberTextToSetCostsToSeats.setVisible(false);
		return notValidNumberTextToSetCostsToSeats;
	}
	
	public Text getNotValidCostTextToSetCostsToSeatsAsNotVisible() {
		notValidCostTextToSetCostsToSeats.setVisible(false);
		return notValidCostTextToSetCostsToSeats;
	}
	
	public TextField getTextFieldOfNameToCreateEvent() {
		return textFieldOfNameToCreateEvent;
	}
	
	public TextField getClearTextFieldOfNameToCreateEvent() {
		textFieldOfNameToCreateEvent.setText("");
		return textFieldOfNameToCreateEvent;
	}
	
	public TextField getTextFieldOfCityToCreateEvent() {
		return textFieldOfCityToCreateEvent;
	}
	
	public TextField getClearTextFieldOfCityToCreateEvent() {
		textFieldOfCityToCreateEvent.setText("");
		return textFieldOfCityToCreateEvent;
	}
	
	public TextField getTextFieldOfNeighborhoodToCreateEvent() {
		return textFieldOfNeighborhoodToCreateEvent;
	}
	
	public TextField getClearTextFieldOfNeighborhoodToCreateEvent() {
		textFieldOfNeighborhoodToCreateEvent.setText("");
		return textFieldOfNeighborhoodToCreateEvent;
	}
	
	public TextField getTextFieldOfNeighborhoodNumberToCreateEvent() {
		return textFieldOfNeighborhoodNumberToCreateEvent;
	}
	
	public TextField getClearTextFieldOfNeighborhoodNumberToCreateEvent() {
		textFieldOfNeighborhoodNumberToCreateEvent.setText("");
		return textFieldOfNeighborhoodNumberToCreateEvent;
	}
	
	public TextField getTextFieldOfYearToCreateEvent() {
		return textFieldOfYearToCreateEvent;
	}
	
	public TextField getClearTextFieldOfYearToCreateEvent() {
		textFieldOfYearToCreateEvent.setText("");
		return textFieldOfYearToCreateEvent;
	}
	
	public TextField getTextFieldOfMonthToCreateEvent() {
		return textFieldOfMonthToCreateEvent;
	}
	
	public TextField getClearTextFieldOfMonthToCreateEvent() {
		textFieldOfMonthToCreateEvent.setText("");
		return textFieldOfMonthToCreateEvent;
	}
	
	public TextField getTextFieldOfDayToCreateEvent() {
		return textFieldOfDayToCreateEvent;
	}
	
	public TextField getClearTextFieldOfDayToCreateEvent() {
		textFieldOfDayToCreateEvent.setText("");
		return textFieldOfDayToCreateEvent;
	}
	
	public TextField getTextFieldOfHourToCreateEvent() {
		return textFieldOfHourToCreateEvent;
	}
	
	public TextField getClearTextFieldOfHourToCreateEvent() {
		textFieldOfHourToCreateEvent.setText("");
		return textFieldOfHourToCreateEvent;
	}
	
	public TextField getTextFieldOfMinuteToCreateEvent() {
		return textFieldOfMinuteToCreateEvent;
	}
	
	public TextField getClearTextFieldOfMinuteToCreateEvent() {
		textFieldOfMinuteToCreateEvent.setText("");
		return textFieldOfMinuteToCreateEvent;
	}
	
	public TextField getTextFieldOfNumberOfRowsToCreateEvent() {
		return textFieldOfNumberOfRowsToCreateEvent;
	}
	
	public TextField getClearTextFieldOfNumberOfRowsToCreateEvent() {
		textFieldOfNumberOfRowsToCreateEvent.setText("");
		return textFieldOfNumberOfRowsToCreateEvent;
	}
	
	public TextField getTextFieldOfMaxNumberOfSeatsInRowToCreateEvent() {
		return textFieldOfMaxNumberOfSeatsInRowToCreateEvent;
	}
	
	public TextField getClearTextFieldOfMaxNumberOfSeatsInRowToCreateEvent() {
		textFieldOfMaxNumberOfSeatsInRowToCreateEvent.setText("");
		return textFieldOfMaxNumberOfSeatsInRowToCreateEvent;
	}
	
	public TextField getTextFieldOfRowOrColNumToSetPrices() {
		return textFieldOfRowOrColNumToSetPrices;
	}
	
	public TextField getClearTextFieldOfRowOrColNumToSetPrices() {
		textFieldOfRowOrColNumToSetPrices.setText("");
		return textFieldOfRowOrColNumToSetPrices;
	}
	
	public TextField getTextFieldOfCostToRowOrColToSetPrices() {
		return textFieldOfCostToRowOrColToSetPrices;
	}
	
	public TextField getClearTextFieldOfCostToRowOrColToSetPrices() {
		textFieldOfCostToRowOrColToSetPrices.setText("");
		return textFieldOfCostToRowOrColToSetPrices;
	}
	
	public ComboBox<String> getComboBoxToRowOrColOption() {
		return comboBoxToRowOrColOption;
	}
	
	public ComboBox<String> getComboBoxToRowOrColOptionWithOutSelectedOption() {
		comboBoxToRowOrColOption.setValue(null);
		return comboBoxToRowOrColOption;
	}
	
	public Button getContinueToSetSeatsCostsButton() {
		return continueToSetSeatsCostsButton;
	}
	
	public Button getCreateEventButton() {
		return createEventButton;
	}
	
	public Button getSetRowOrColSeatsCostButton() {
		return setRowOrColSeatsCostButton;
	}
	// Gets for edit event date
	public Text getSelectEventToEditDateFormHeadLine() {
		return selectEventToEditDateFormHeadLine;
	}
	
	public Text getSelectEventToEditFormEventIdSubTitle() {
		return selectEventToEditFormEventIdSubTitle;
	}
	
	public Text getSelectEventToEditFormEventDetailsSubTitle() {
		return selectEventToEditFormEventDetailsSubTitle;
	}
	
	public Text getSelectEventIdTextInSelectEventToEdit() {
		return selectEventIdTextInSelectEventToEdit;
	}
	
	public Text getEditEventDateFormHeadLine() {
		return editEventDateFormHeadLine;
	}
	
	public Text getYearTextToEditEvent() {
		return yearTextToEditEvent;
	}
	
	public Text getMonthTextToEditEvent() {
		return monthTextToEditEvent;
	}
	
	public Text getDayTextToEditEvent() {
		return dayTextToEditEvent;
	}
	
	public Text getHourTextToEditEvent() {
		return hourTextToEditEvent;
	}
	
	public Text getMinuteTextToEditEvent() {
		return minuteTextToEditEvent;
	}
	
	public TextField getTextFieldOfEventIdToEditEventDate() {
		return textFieldOfEventIdToEditEventDate;
	}
	
	public TextField getClearTextFieldOfEventIdToEditEventDate() {
		textFieldOfEventIdToEditEventDate.setText("");
		return textFieldOfEventIdToEditEventDate;
	}
	
	public TextField getTextFieldOfYearToEditEventDate() {
		return textFieldOfYearToEditEventDate;
	}
	
	public TextField getTextFieldOfMonthToEditEventDate() {
		return textFieldOfMonthToEditEventDate;
	}
	
	public TextField getTextFieldOfDayToEditEventDate() {
		return textFieldOfDayToEditEventDate;
	}
	
	public TextField getTextFieldOfHourToEditEventDate() {
		return textFieldOfHourToEditEventDate;
	}
	
	public TextField getTextFieldOfMinuteToEditEventDate() {
		return textFieldOfMinuteToEditEventDate;
	}
	
	public Button getSelectEventToEditDateButton() {
		return selectEventToEditDateButton;
	}
	
	public Button getEditEventDateButton() {
		return editEventDateButton;
	}
	
	// Gets for cancel event
	public Text getCancelEventFormHeadLine() {
		return cancelEventFormHeadLine;
	}
	
	public Text getCancelEventFormEventIdSubTitle() {
		return cancelEventFormEventIdSubTitle;
	}
	
	public Text getCancelEventFormEventDetailsSubTitle() {
		return cancelEventFormEventDetailsSubTitle;
	}
	
	public Text getEventIdTextToCancelEvent() {
		return eventIdTextToCancelEvent;
	}
	
	public TextField getTextFieldOfEventIdToCancel() {
		return textFieldOfEventIdToCancel;
	}
	
	public TextField getClearTextFieldOfEventIdToCancel() {
		textFieldOfEventIdToCancel.setText("");
		return textFieldOfEventIdToCancel;
	}
	
	public Button getCancelEventButton() {
		return cancelEventButton;
	}
	
	// Gets for make invitation to event
	public Text getSelectEventToMakeInvitationFormHeadLine() {
		return selectEventToMakeInvitationFormHeadLine;
	}
	
	public Text getMakeInvitationFormEventIdSubTitle() {
		return makeInvitationFormEventIdSubTitle;
	}
	
	public Text getMakeInvitationFormEventDetailsSubTitle() {
		return makeInvitationFormEventDetailsSubTitle;
	}
	
	public Text getEventIdTextToMakeInvitation() {
		return eventIdTextToMakeInvitation;
	}
	
	public Text getMakeInvitationToEventFormHeadLine() {
		return makeInvitationToEventFormHeadLine;
	}
	
	public Text getNumberOfSeatsTextToMakeInvitationToEvent() {
		return numberOfSeatsTextToMakeInvitationToEvent;
	}
	
	public Text getHealthStatusTextToMakeInvitationToEvent() {
		return healthStatusTextToMakeInvitationToEvent;
	}
	
	public Text getSeatPriceTextToMakeInvitationToEvent() {
		return seatPriceTextToMakeInvitationToEvent;
	}
	
	public Text getOfferSeatsInvitationToEventFormHeadLine() {
		return offerSeatsInvitationToEventFormHeadLine;
	}
	
	public Text getOfferSeatsInvitationToEventFormRowNumSubTitle() {
		return offerSeatsInvitationToEventFormRowNumSubTitle;
	}
	
	public Text getOfferSeatsInvitationToEventFormSeatsNumAndCostsSubTitle() {
		return offerSeatsInvitationToEventFormSeatsNumAndCostsSubTitle;
	}
	
	public TextField getTextFieldOfEventIdToMakeInvitation() {
		return textFieldOfEventIdToMakeInvitation;
	}
	
	public TextField getClearTextFieldOfEventIdToMakeInvitation() {
		textFieldOfEventIdToMakeInvitation.setText("");
		return textFieldOfEventIdToMakeInvitation;
	}
	
	public TextField getTextFieldOfNumOfSeatsInInvitationToMakeInvitation() {
		return textFieldOfNumOfSeatsInInvitationToMakeInvitation;
	}
	
	public TextField getClearTextFieldOfNumOfSeatsInInvitationToMakeInvitation() {
		textFieldOfNumOfSeatsInInvitationToMakeInvitation.setText("");
		return textFieldOfNumOfSeatsInInvitationToMakeInvitation;
	}
	
	public TextField getTextFieldOfSeatPriceToMakeInvitation() {
		return textFieldOfSeatPriceToMakeInvitation;
	}
	
	public TextField getClearTextFieldOfSeatPriceToMakeInvitation() {
		textFieldOfSeatPriceToMakeInvitation.setText("");
		return textFieldOfSeatPriceToMakeInvitation;
	}
	
	public ComboBox<String> getComboBoxOfHealthStatusToMakeInvitation() {
		return comboBoxOfHealthStatusToMakeInvitation;
	}
	
	public Button getSelectEventToMakeInvitationToButton() {
		return selectEventToMakeInvitationToButton;
	}
	
	public Button getShowOfferSeatsInvitationButton() {
		return showOfferSeatsInvitationButton;
	}
	
	public Button getMakeInvitationToEventButton() {
		return makeInvitationToEventButton;
	}
	
	public Button getDontMakeInvitationToEventButton() {
		return dontMakeInvitationToEventButton;
	}
	
	// Gets for show my invitations
	public Text getShowMyInvitationsFormHeadLine() {
		return showMyInvitationsFormHeadLine;
	}
	
	// Gets for change users permissions
	public Text getChangeUserPermissionsFormHeadLine() {
		return changeUserPermissionsFormHeadLine;
	}
	
	public Text getEmailTextToChangeUserPermissions() {
		return emailTextToChangeUserPermissions;
	}
	
	public Text getNewPermissionsOfTheUserTextToChangeUserPermissions() {
		return newPermissionsOfTheUserTextToChangeUserPermissions;
	}
	
	public TextField getTextFieldOfEmailToChangeUsersPermissions() {
		return textFieldOfEmailToChangeUsersPermissions;
	}
	
	public TextField getClearTextFieldOfEmailToChangeUsersPermissions() {
		textFieldOfEmailToChangeUsersPermissions.setText("");
		return textFieldOfEmailToChangeUsersPermissions;
	}
	
	public TextField getTextFieldOfPermissionToChangeUsersPermissions() {
		return textFieldOfPermissionToChangeUsersPermissions;
	}
	
	public TextField getClearTextFieldOfPermissionToChangeUsersPermissions() {
		textFieldOfPermissionToChangeUsersPermissions.setText("");
		return textFieldOfPermissionToChangeUsersPermissions;
	}
	
	public Button getChangeUserPermissionsButton() {
		return changeUserPermissionsButton;
	}
	
	// Gets for change my information
	public Text getChangeMyInformationFormHeadLine() {
		return changeMyInformationFormHeadLine;
	}
	
	public Text getNameTextToChangeMyInformation() {
		return nameTextToChangeMyInformation;
	}
	
	public Text getEmailTextToChangeMyInformation() {
		return emailTextToChangeMyInformation;
	}
	
	public Text getPhoneTextToChangeMyInformation() {
		return phoneTextToChangeMyInformation;
	}
	
	public TextField getTextFieldOfNameToChangeMyInformation() {
		return textFieldOfNameToChangeMyInformation;
	}

	public TextField getTextFieldOfEmailToChangeMyInformation() {
		return textFieldOfEmailToChangeMyInformation;
	}
	
	public TextField getTextFieldOfPhoneToChangeMyInformation() {
		return textFieldOfPhoneToChangeMyInformation;
	}
	
	public Button getChangeMyInformationButton() {
		return changeMyInformationButton;
	}
	
	// Gets for show event seats
	public Text getSelectEventToShowFormHeadLine() {
		return selectEventToShowFormHeadLine;
	}
	
	public Text getSelectEventToShowFormEventIdSubTitle() {
		return selectEventToShowFormEventIdSubTitle;
	}
	
	public Text getSelectEventToShowFormEventDetailsSubTitle() {
		return selectEventToShowFormEventDetailsSubTitle;
	}
	
	public Text getSelectedEventIdTextToShowEventSeats() {
		return selectedEventIdTextToShowEventSeats;
	}
	
	public Text getShowEventSeatsFormHeadLine() {
		return showEventSeatsFormHeadLine;
	}
	
	public Text getLegendTextToShowEventSeats() {
		return legendTextToShowEventSeats;
	}
	
	public TextField getTextFieldOfEventIdToShowEventSeats() {
		return textFieldOfEventIdToShowEventSeats;
	}
	
	public TextField getClearTextFieldOfEventIdToShowEventSeats() {
		textFieldOfEventIdToShowEventSeats.setText("");
		return textFieldOfEventIdToShowEventSeats;
	}
	
	public Button getShowEventSeatsButton() {
		return showEventSeatsButton;
	}
	
	// Gets for add health status
	public Text getAddHelathStatusFormHeadLine() {
		return addHelathStatusFormHeadLine;
	}
	
	public Text getNameTextToAddHealthStatus() {
		return nameTextToAddHealthStatus;
	}
	
	public Text getSpaceNeededTextToAddHealthStatus() {
		return spaceNeededTextToAddHealthStatus;
	}
	
	public TextField getTextFieldOfNameToAddHealthStatus() {
		return textFieldOfNameToAddHealthStatus;
	}
	
	public TextField getClearTextFieldOfNameToAddHealthStatus() {
		textFieldOfNameToAddHealthStatus.setText("");
		return textFieldOfNameToAddHealthStatus;
	}
	
	public TextField getTextFieldOfSpaceToAddHealthStatus() {
		return textFieldOfSpaceToAddHealthStatus;
	}
	
	public TextField getClearTextFieldOfSpaceToAddHealthStatus() {
		textFieldOfSpaceToAddHealthStatus.setText("");
		return textFieldOfSpaceToAddHealthStatus;
	}

	public Button getAddHealthStatusButton() {
		return addHealthStatusButton;
	}
	
	// Set menu to unconnected user
	public void setMenuToUnconnectedUser() {
		menuLayout = new VBox();
		menuLayout.getStyleClass().add("menu");
		
		menuLayout.getChildren().addAll(menuTitleLabel, loginButtonToMenu, registerButtonToMenu);
		menuLayout.setAlignment(Pos.CENTER_LEFT);
		
        // Create ScrollPane and set content
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(menuLayout);
        scrollPane.setFitToWidth(true); // Fit scroll pane width to scene width
        scrollPane.setFitToHeight(true); // Fit scroll pane height to scene height
		
		rootPane.setLeft(scrollPane);
	}
	
	// Set menu to admin user
	public void setMenuToAdminUser() {
		menuLayout = new VBox();
		menuLayout.getStyleClass().add("menu");
		
		menuLayout.getChildren().addAll(menuTitleLabel, createEventButtonToMenu, editEventDateButtonToMenu, cancelEventButtonToMenu, makeInvitationToEventButtonToMenu, 
									showMyInvitationsButtonToMenu, changeUsersPermissionsButtonToMenu, changeMyInformationButtonToMenu, showEventSeatsButtonToMenu, 
									addHealthStatusButtonToMenu, initDataBaseButtonToMenu, logoutButtonToMenu);
		menuLayout.setAlignment(Pos.CENTER_LEFT);
		
        // Create ScrollPane and set content
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(menuLayout);
        scrollPane.setFitToWidth(true); // Fit scroll pane width to scene width
        scrollPane.setFitToHeight(true); // Fit scroll pane height to scene height
		
		rootPane.setLeft(scrollPane);
	}
	
	// Set menu to manage events user
	public void setMenuToManageEventsUser() {
		menuLayout = new VBox();
		menuLayout.getStyleClass().add("menu");
		
		menuLayout.getChildren().addAll(menuTitleLabel, createEventButtonToMenu, editEventDateButtonToMenu, cancelEventButtonToMenu, makeInvitationToEventButtonToMenu, 
									showMyInvitationsButtonToMenu, changeMyInformationButtonToMenu, showEventSeatsButtonToMenu, logoutButtonToMenu);
		menuLayout.setAlignment(Pos.CENTER_LEFT);
		
        // Create ScrollPane and set content
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(menuLayout);
        scrollPane.setFitToWidth(true); // Fit scroll pane width to scene width
        scrollPane.setFitToHeight(true); // Fit scroll pane height to scene height
        
		rootPane.setLeft(scrollPane);
	}
	
	// Set menu to order events user
	public void setMenuToOrderEventsUser() {
		menuLayout = new VBox();
		menuLayout.getStyleClass().add("menu");
		
		menuLayout.getChildren().addAll(menuTitleLabel, makeInvitationToEventButtonToMenu, showMyInvitationsButtonToMenu, changeMyInformationButtonToMenu, 
				showEventSeatsButtonToMenu, logoutButtonToMenu);
		menuLayout.setAlignment(Pos.CENTER_LEFT);
		
        // Create ScrollPane and set content
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(menuLayout);
        scrollPane.setFitToWidth(true); // Fit scroll pane width to scene width
        scrollPane.setFitToHeight(true); // Fit scroll pane height to scene height
		
		rootPane.setLeft(scrollPane);
	}
	
	// Clear text field of year to edit event date
	public void clearTextFieldOfYearToEditEventDate() {
		textFieldOfYearToEditEventDate.setText("");
	}
	
	// Clear text field of month to edit event date
	public void clearTextFieldOfMonthToEditEventDate() {
		textFieldOfMonthToEditEventDate.setText("");
	}
	
	// Clear text field of dat to edit event date
	public void clearTextFieldOfDayToEditEventDate() {
		textFieldOfDayToEditEventDate.setText("");
	}
	
	// Clear text field of hour to edit event date
	public void clearTextFieldOfHourToEditEventDate() {
		textFieldOfHourToEditEventDate.setText("");
	}
	
	// Clear text field of minute to edit event date
	public void clearTextFieldOfMinuteToEditEventDate() {
		textFieldOfMinuteToEditEventDate.setText("");
	}
	
	// Clear combo box of health status to make invitation
	public void clearComboBoxOfHealthStatusToMakeInvitation() {
		comboBoxOfHealthStatusToMakeInvitation.getItems().clear();
	}
	
	// Clear text field of name to change my information
	public void clearTextFieldOfNameToChangeMyInformation() {
		textFieldOfNameToChangeMyInformation.setText("");
	}
	
	// Clear text field of email to register
	public void clearTextFieldOfEmailToChangeMyInformation() {
		textFieldOfEmailToChangeMyInformation.setText("");
	}
	
	// Clear text field of name to register
	public void clearTextFieldOfPhoneToChangeMyInformation() {
		textFieldOfPhoneToChangeMyInformation.setText("");
	}
	
	// Add name to combo box of health status to make invitation
	public void addNameToAllDepartmentsNamesComboBox(String healthStatus) {
		comboBoxOfHealthStatusToMakeInvitation.getItems().add(healthStatus);
	}
	
	// Show in center
    public void showInCenter(GridPane newDetailsToShow) {
    	rootPane.setCenter(newDetailsToShow);
    }
    
	// Show in center
    public void showInCenter(ScrollPane newDetailsToShow) {
    	rootPane.setCenter(newDetailsToShow);
    }
	
	// Show in center
	public void showInCenter(Text newDetailsToShow) {
		rootPane.setCenter(newDetailsToShow);
	}
	
	// Show in top
	public void showInTop(Text newDetailsToShow) {
		rootPane.setTop(newDetailsToShow);
	}
}