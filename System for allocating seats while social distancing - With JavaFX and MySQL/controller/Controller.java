package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import database.DataBaseCommands;
import database.DataBaseCommandsForEvents;
import database.DataBaseCommandsForHealthStatus;
import database.DataBaseCommandsForInvitations;
import database.DataBaseCommandsForManagers;
import database.DataBaseCommandsForPermissions;
import database.DataBaseCommandsForSeats;
import database.DataBaseCommandsForUsers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Event;
import model.Invitation;
import model.PlaceForInvitation;
import model.Seat;
import model.User;

public class Controller {
	
	Scanner s = new Scanner (System.in);
	String clearInput;
	
	public User connectedUser = null;
	public boolean exitFlag = false;
	private view.View view;
	
	// Constructor
    public Controller(view.View view) {
        this.view = view;
        setUpEventHandlers();
        
        try {
        	Set<Integer> adminUsers = DataBaseCommandsForUsers.searchUsersIdsWithThePermissionInTheDataBase(DataBaseCommands.ADMIN_PERMISSION_NAME_IN_DATA_BASE);
        	
        	if(adminUsers.size() == 0) // No admin users in data base
        		DataBaseCommands.initDataBase();
        }
        catch(Exception e) {
			Text failText = new Text(e.getMessage());
			failText.setId("failedText");
			
			view.showInCenter(failText);
        }
    }
	
	// Show new menu
	private void showNewMenu() {
		if(connectedUser == null) // Not connected user
			view.setMenuToUnconnectedUser();
		else if(connectedUser.getUserPermission().equals(DataBaseCommands.ADMIN_PERMISSION_NAME_IN_DATA_BASE)) // Admin user
			view.setMenuToAdminUser();
		else if(connectedUser.getUserPermission().equals(DataBaseCommands.MANAGE_EVENTS_PERMISSION_NAME_IN_DATA_BASE)) // Manage events user
			view.setMenuToManageEventsUser();
		else // Order events user
			view.setMenuToOrderEventsUser();
	}
	
	// Show login form
	private void showLoginForm() {
		
		// Grid pane of head line
		GridPane gridOfHeadLine = new GridPane();
		gridOfHeadLine.setHgap(10);
		gridOfHeadLine.setVgap(10);
		gridOfHeadLine.add(view.getLoginFormHeadLine(), 0, 0);
		
		// Grid pane of form
		GridPane gridPaneOfForm = new GridPane();
		gridPaneOfForm.setHgap(10);
		gridPaneOfForm.setVgap(10);
		gridPaneOfForm.add(view.getEmailTextToLogin(), 0, 1);
		gridPaneOfForm.add(view.getClearTextFieldOfEmailToLogin(), 1, 1);
		
		// Grid pane of button
		GridPane gridPaneOfButton = new GridPane();
		gridPaneOfButton.add(view.getLoginButton(), 0, 0);
		
		// Grid pane of all
		GridPane newDetailsToShow = new GridPane();
		newDetailsToShow.setHgap(10);
		newDetailsToShow.setVgap(10);
		newDetailsToShow.add(gridOfHeadLine, 0, 0);
		newDetailsToShow.add(gridPaneOfForm, 0, 1);
		newDetailsToShow.add(gridPaneOfButton, 0, 2);
		newDetailsToShow.setAlignment(Pos.CENTER);
		
		view.showInCenter(newDetailsToShow);
	}
	
	// Try to login
	private void tryToLogin() {
		try {
			String email = view.getTextFieldOfEmailToLogin().getText();
			connectedUser = DataBaseCommandsForUsers.searchUserByEmailInDataBase(email); // Search user by email
			if(connectedUser == null) { // Not found user with that email
				Text failText = new Text("Login failed! \nWrong email!");
				failText.setId("failedText");
				
				view.showInCenter(failText);
			}
			else { // Found user with that email
				Text succeedText = new Text("Login successfully!");
				succeedText.setId("succeedText");
				Text helloText = new Text("Hello " + connectedUser.getUserName() + "!");
				helloText.setId("helloText");
				
				view.showInCenter(succeedText);
				view.showInTop(helloText);
			}
		}
		catch(Exception e) {
			Text failText = new Text("Login failed! \nCan't connect to data base!");
			failText.setId("failedText");
			
			view.showInCenter(failText);
		}
		
		showNewMenu();
	}
	
	// Show register form
	private void showRegisterForm() {
		
		// Grid pane of head line
		GridPane gridOfHeadLine = new GridPane();
		gridOfHeadLine.setHgap(10);
		gridOfHeadLine.setVgap(10);
		gridOfHeadLine.add(view.getRegisterFormHeadLine(), 0, 0);
		
		// Grid pane of form
		GridPane gridPaneOfForm = new GridPane();
		gridPaneOfForm.setHgap(10);
		gridPaneOfForm.setVgap(10);
		gridPaneOfForm.add(view.getNameTextToRegister(), 0, 0);
		gridPaneOfForm.add(view.getClearTextFieldOfNameToRegister(), 1, 0);
		gridPaneOfForm.add(view.getEmailTextToRegister(), 0, 1);
		gridPaneOfForm.add(view.getClearTextFieldOfEmailToRegister(), 1, 1);
		gridPaneOfForm.add(view.getPhoneTextToRegister(), 0, 2);
		gridPaneOfForm.add(view.getClearTextFieldOfPhoneToRegister(), 1, 2);
		
		// Grid pane of button
		GridPane gridPaneOfButton = new GridPane();
		gridPaneOfButton.add(view.getRegisterButton(), 0, 0);
		
		// Grid pane of all
		GridPane newDetailsToShow = new GridPane();
		newDetailsToShow.setHgap(10);
		newDetailsToShow.setVgap(10);
		newDetailsToShow.add(gridOfHeadLine, 0, 0);
		newDetailsToShow.add(gridPaneOfForm, 0, 1);
		newDetailsToShow.add(gridPaneOfButton, 0, 2);
		newDetailsToShow.setAlignment(Pos.CENTER);
		
		view.showInCenter(newDetailsToShow);
	}
	
	// Try to register
	private void tryToRegister() {		
		try {
			String name = view.getTextFieldOfNameToRegister().getText();
			String email = view.getTextFieldOfEmailToRegister().getText();
			String phoneNumber = view.getTextFieldOfPhoneToRegister().getText();
			
			if(DataBaseCommands.isEmailExist(email)) // Check if email is already exist
				throw new Exception("Email is already exist!");
			
			if(DataBaseCommands.isPhoneExist(phoneNumber)) // Check if phone is already exist
				throw new Exception("Phone number is already exist!");
			
			User newUser = new User(name, email, phoneNumber); // Make new user
			
			if(DataBaseCommandsForUsers.addUserToDataBase(newUser)) { // Add user to data base
				Text succeedText = new Text("Register successfully!");
				succeedText.setId("succeedText");
				
				view.showInCenter(succeedText);
			}
			else // Failed add user to data base				
				throw new Exception("User not added to data base!");
		} catch (Exception e) {
			Text failText = new Text("Register failed! \n" + e.getMessage());
			failText.setId("failedText");
			
			view.showInCenter(failText);
		}
	}
	
	// Show create event form
	private void showCreateEventForm() {
		
		// Grid pane of head line
		GridPane gridOfHeadLine = new GridPane();
		gridOfHeadLine.setHgap(10);
		gridOfHeadLine.setVgap(10);
		gridOfHeadLine.add(view.getCreateEventFormHeadLine(), 0, 0);
		
		// Grid pane of form
		GridPane gridPaneOfForm = new GridPane();
		gridPaneOfForm.setHgap(10);
		gridPaneOfForm.setVgap(10);
		gridPaneOfForm.add(view.getNameTextToCreateEvent(), 0, 0);
		gridPaneOfForm.add(view.getClearTextFieldOfNameToCreateEvent(), 1, 0);
		gridPaneOfForm.add(view.getCityTextToCreateEvent(), 0, 1);
		gridPaneOfForm.add(view.getClearTextFieldOfCityToCreateEvent(), 1, 1);
		gridPaneOfForm.add(view.getNeighborhoodTextToCreateEvent(), 0, 2);
		gridPaneOfForm.add(view.getClearTextFieldOfNeighborhoodToCreateEvent(), 1, 2);
		gridPaneOfForm.add(view.getNeighborhoodNumberTextToCreateEvent(), 0, 3);
		gridPaneOfForm.add(view.getClearTextFieldOfNeighborhoodNumberToCreateEvent(), 1, 3);
		gridPaneOfForm.add(view.getYearTextToCreateEvent(), 0, 4);
		gridPaneOfForm.add(view.getClearTextFieldOfYearToCreateEvent(), 1, 4);
		gridPaneOfForm.add(view.getMonthTextToCreateEvent(), 0, 5);
		gridPaneOfForm.add(view.getClearTextFieldOfMonthToCreateEvent(), 1, 5);
		gridPaneOfForm.add(view.getDayTextToCreateEvent(), 0, 6);
		gridPaneOfForm.add(view.getClearTextFieldOfDayToCreateEvent(), 1, 6);
		gridPaneOfForm.add(view.getHourTextToCreateEvent(), 0, 7);
		gridPaneOfForm.add(view.getClearTextFieldOfHourToCreateEvent(), 1, 7);
		gridPaneOfForm.add(view.getMinuteTextToCreateEvent(), 0, 8);
		gridPaneOfForm.add(view.getClearTextFieldOfMinuteToCreateEvent(), 1, 8);
		gridPaneOfForm.add(view.getNumberOfRowsTextToCreateEvent(), 0, 9);
		gridPaneOfForm.add(view.getClearTextFieldOfNumberOfRowsToCreateEvent(), 1, 9);
		gridPaneOfForm.add(view.getMaxNumberOfSeatsInRowTextToCreateEvent(), 0, 10);
		gridPaneOfForm.add(view.getClearTextFieldOfMaxNumberOfSeatsInRowToCreateEvent(), 1, 10);
		
		// Grid pane of button
		GridPane gridPaneOfButton = new GridPane();
		gridPaneOfButton.add(view.getContinueToSetSeatsCostsButton(), 0, 0);
		
		// Grid pane of all
		GridPane newDetailsToShow = new GridPane();
		newDetailsToShow.setHgap(10);
		newDetailsToShow.setVgap(10);
		newDetailsToShow.add(gridOfHeadLine, 0, 0);
		newDetailsToShow.add(gridPaneOfForm, 0, 1);
		newDetailsToShow.add(gridPaneOfButton, 0, 2);
		newDetailsToShow.setAlignment(Pos.CENTER);
		
		view.showInCenter(newDetailsToShow);
	}
	
	// Show set seats costs form
	private void showSetSeatsCostsForm() {
		try {
			int numOfRows = Integer.parseInt(view.getTextFieldOfNumberOfRowsToCreateEvent().getText());
			int numOfMaxSeatsInEachRow = Integer.parseInt(view.getTextFieldOfMaxNumberOfSeatsInRowToCreateEvent().getText());
			if((numOfRows < 1) || (numOfMaxSeatsInEachRow < 1))
				throw new Exception("Number of rows & max seats in each row should be bigger than 0!");
			
			// Grid pane of head line and sub title
			GridPane gridOfHeadLineAndSubTitle = new GridPane();
			gridOfHeadLineAndSubTitle.setHgap(10);
			gridOfHeadLineAndSubTitle.setVgap(10);
			gridOfHeadLineAndSubTitle.add(view.getSetSeatsCostsFormHeadLine(), 0, 0);
			gridOfHeadLineAndSubTitle.add(view.getSetSeatsCostsFormSubTitle(), 0, 1);
			
			// Grid pane of set cost to row or column
			GridPane gridOfSetCostToRowOrColumn = new GridPane();
			gridOfSetCostToRowOrColumn.setHgap(10);
			gridOfSetCostToRowOrColumn.setVgap(10);
			gridOfSetCostToRowOrColumn.add(view.getSetTextToSetCostsToSeats(), 0, 0);
			gridOfSetCostToRowOrColumn.add(view.getComboBoxToRowOrColOptionWithOutSelectedOption(), 1, 0);
			gridOfSetCostToRowOrColumn.add(view.getNumberTextToSetCostsToSeats(), 2, 0);
			gridOfSetCostToRowOrColumn.add(view.getClearTextFieldOfRowOrColNumToSetPrices(), 3, 0);
			gridOfSetCostToRowOrColumn.add(view.getCostToTextToSetCostsToSeats(), 4, 0);
			gridOfSetCostToRowOrColumn.add(view.getClearTextFieldOfCostToRowOrColToSetPrices(), 5, 0);
			gridOfSetCostToRowOrColumn.add(view.getSetRowOrColSeatsCostButton(), 6, 0);
			gridOfSetCostToRowOrColumn.add(view.getNotValidRowOrColOptionTextToSetCostsToSeatsAsNotVisible(), 7, 0);
			gridOfSetCostToRowOrColumn.add(view.getNotValidNumberTextToSetCostsToSeatsAsNotVisible(), 7, 0);
			gridOfSetCostToRowOrColumn.add(view.getNotValidCostTextToSetCostsToSeatsAsNotVisible(), 7, 0);
			
			// Grid pane of form
			GridPane gridPaneOfForm = new GridPane();
			gridPaneOfForm.setHgap(10);
			gridPaneOfForm.setVgap(10);
			TextField[][] textFields = new TextField[numOfRows][numOfMaxSeatsInEachRow];
			int rowNumInGridPaneOfForm = 0;
			for(int rowNum = 0; rowNum < numOfRows; rowNum++) {
				Text numberOfSeatsInRowText = new Text("Row number " + (rowNum + 1) + ": ");
				numberOfSeatsInRowText.setId("textInForm");
				gridPaneOfForm.add(numberOfSeatsInRowText, 0, rowNumInGridPaneOfForm);
				for(int seatNum = 0; seatNum < numOfMaxSeatsInEachRow; seatNum++) {
					TextField textFieldToAdd = new TextField();
					textFieldToAdd.setId("textFieldToSeatPrice");
					gridPaneOfForm.add(textFieldToAdd, seatNum + 1, rowNumInGridPaneOfForm);
					textFields[rowNum][seatNum] = textFieldToAdd;
				}
				rowNumInGridPaneOfForm++;
			}
			
			// Grid pane of button
			GridPane gridPaneOfButton = new GridPane();
			gridPaneOfButton.add(view.getCreateEventButton(), 0, 0);
			
			// Grid pane of all
			GridPane newDetailsToShow = new GridPane();
			newDetailsToShow.setHgap(10);
			newDetailsToShow.setVgap(10);
			newDetailsToShow.add(gridOfHeadLineAndSubTitle, 0, 0);
			newDetailsToShow.add(gridOfSetCostToRowOrColumn, 0, 1);
			newDetailsToShow.add(gridPaneOfForm, 0, 2);
			newDetailsToShow.add(gridPaneOfButton, 0, 3);
			newDetailsToShow.setAlignment(Pos.CENTER);
			
            // Scroll pane (can be a lot of seats)
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(newDetailsToShow);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            
			view.showInCenter(scrollPane);
			
			// Set row or column seats price on action of set price button
			view.getSetRowOrColSeatsCostButton().setOnAction(eventHandler -> { setCostToRowOrColumn(textFields, numOfRows, numOfMaxSeatsInEachRow); });
			
			// Send text fields of costs to try to create event on action of create event button
			view.getCreateEventButton().setOnAction(eventHandler -> { tryToCreateEvent(textFields); });
		}
		catch(Exception e) {
			Text failText = new Text("Continue to set seats costs failed! \nThe number of rows & seats in each row should be bigger than 0!");
			failText.setId("failedText");
			
			view.showInCenter(failText);
		}
	}
	
	// Set cost to row or column
	private void setCostToRowOrColumn(TextField[][] textFieldsOfSeatPrices, int numOfRows, int numOfMaxSeatsInEachRow) {
		boolean selectRowOrColumn = false;
		boolean validRowOrColumnNum = false;
		try {
			if(view.getComboBoxToRowOrColOption().getValue() == null) // Didn't selected row or column
				throw new Exception("Need to choose row / column!");
			selectRowOrColumn = true;
			int selectedRowOrColumn = Integer.parseInt(view.getTextFieldOfRowOrColNumToSetPrices().getText());
			if(view.getComboBoxToRowOrColOption().getValue().equals("Row")) { // Want to set cost to row
				if((selectedRowOrColumn > numOfMaxSeatsInEachRow) || (numOfMaxSeatsInEachRow <= 0))
					throw new Exception("Invalid row number!");
				validRowOrColumnNum = true;
				int priceToSet = Integer.parseInt(view.getTextFieldOfCostToRowOrColToSetPrices().getText());
				if(priceToSet < 0)
					throw new Exception("Invalid price!");
				for(int seatIndex = 0; seatIndex < numOfMaxSeatsInEachRow; seatIndex++)
					textFieldsOfSeatPrices[selectedRowOrColumn - 1][seatIndex].setText("" + priceToSet);
			}
			else { // Want to set cost to column
				if((selectedRowOrColumn > numOfMaxSeatsInEachRow) || (numOfMaxSeatsInEachRow <= 0))
					throw new Exception("Invalid column number");
				validRowOrColumnNum = true;
				int priceToSet = Integer.parseInt(view.getTextFieldOfCostToRowOrColToSetPrices().getText());
				if(priceToSet < 0)
					throw new Exception("Invalid price!");
				for(int rowIndex = 0; rowIndex < numOfRows; rowIndex++)
					textFieldsOfSeatPrices[rowIndex][selectedRowOrColumn - 1].setText("" + priceToSet);
			}
			view.getNotValidRowOrColOptionTextToSetCostsToSeatsAsNotVisible().setVisible(false);
			view.getNotValidNumberTextToSetCostsToSeatsAsNotVisible().setVisible(false);
			view.getNotValidCostTextToSetCostsToSeatsAsNotVisible().setVisible(false);
		}
		catch (Exception e) {
			if(!selectRowOrColumn) { // Not selected row or column option
				view.getNotValidRowOrColOptionTextToSetCostsToSeatsAsNotVisible().setVisible(true);
				view.getNotValidNumberTextToSetCostsToSeatsAsNotVisible().setVisible(false);
				view.getNotValidCostTextToSetCostsToSeatsAsNotVisible().setVisible(false);
			}
			else if(!validRowOrColumnNum) { // Not valid row or column number
				view.getNotValidRowOrColOptionTextToSetCostsToSeatsAsNotVisible().setVisible(false);
				view.getNotValidNumberTextToSetCostsToSeatsAsNotVisible().setVisible(true);
				view.getNotValidCostTextToSetCostsToSeatsAsNotVisible().setVisible(false);
			}
			else { // Not valid price
				view.getNotValidRowOrColOptionTextToSetCostsToSeatsAsNotVisible().setVisible(false);
				view.getNotValidNumberTextToSetCostsToSeatsAsNotVisible().setVisible(false);
				view.getNotValidCostTextToSetCostsToSeatsAsNotVisible().setVisible(true);
			}
		}
	}
	
	// Try to create event
	public void tryToCreateEvent(TextField[][] textFieldsOfSeatPrices) {
		try {
			String name = view.getTextFieldOfNameToCreateEvent().getText();
			String city = view.getTextFieldOfCityToCreateEvent().getText();
			String neighborhood = view.getTextFieldOfNeighborhoodToCreateEvent().getText();
			int neighborhoodNum, year, month, day, hour, minute;
			try {
				neighborhoodNum = Integer.parseInt(view.getTextFieldOfNeighborhoodNumberToCreateEvent().getText());
			}
			catch(Exception e) {
				throw new Exception("The neighborhood number of the event is not valid! \nIt should be bigger than 0!");
			}
			try {
				year = Integer.parseInt(view.getTextFieldOfYearToCreateEvent().getText());
				month = Integer.parseInt(view.getTextFieldOfMonthToCreateEvent().getText());
				day = Integer.parseInt(view.getTextFieldOfDayToCreateEvent().getText());
				hour = Integer.parseInt(view.getTextFieldOfHourToCreateEvent().getText());
				minute = Integer.parseInt(view.getTextFieldOfMinuteToCreateEvent().getText());
			}
			catch(Exception e) {
				throw new Exception("The date of the event is not valid! \nIt should be in the future and valid!");
			}
			int eventNumOfRows = Integer.parseInt(view.getTextFieldOfNumberOfRowsToCreateEvent().getText());
			int eventNumOfSeatsInRow  = Integer.parseInt(view.getTextFieldOfMaxNumberOfSeatsInRowToCreateEvent().getText());
			int seatPrices[][] = retrieveSeatsPricesFromTextField(textFieldsOfSeatPrices, eventNumOfSeatsInRow);
			int searchEventId;
			Event newEvent = null;
			
			// Make event
			newEvent = new Event(name, city, neighborhood, neighborhoodNum, year, month, day, hour, minute, eventNumOfRows, eventNumOfSeatsInRow, 1, seatPrices);
			// Search event in data base
			searchEventId = DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(newEvent);
			// Event already exist
			if(searchEventId != DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE)
				throw new Exception("Event is already exist!");
			// Event not exist
			else {
				if(DataBaseCommandsForEvents.addEventToDataBase(newEvent)) { // Add event to data base
					// Add seats to data base
					Seat[][] eventSeats = newEvent.getEventSeats();
					for(int rowNum = 0; rowNum < newEvent.getEventNumOfRows(); rowNum++) // Move on every row
						for(int seatNum = 0; seatNum < newEvent.getEventMaxNumOfSeatsInRow(); seatNum++) // Move on every seat
							DataBaseCommandsForSeats.addSeatToDataBase(eventSeats[rowNum][seatNum]);
					// Add the event as managed event to each admin in data base
					Set<Integer> idsOfAdminsUsers = DataBaseCommandsForUsers.searchUsersIdsWithThePermissionInTheDataBase(
							DataBaseCommands.ADMIN_PERMISSION_NAME_IN_DATA_BASE);
					for(int adminId : idsOfAdminsUsers)
							DataBaseCommandsForManagers.addManagerToDataBase(DataBaseCommandsForUsers.searchUserByUserIdInDataBase(adminId), newEvent);
					// Add the event as managed event to the user that create the event (if not admin)
					if(!connectedUser.getUserPermission().equals(DataBaseCommands.ADMIN_PERMISSION_NAME_IN_DATA_BASE))
						DataBaseCommandsForManagers.addManagerToDataBase(connectedUser, newEvent);
					
					Text succeedText = new Text("Event created successfully!");
					succeedText.setId("succeedText");
					
					view.showInCenter(succeedText);
				}
				else
					throw new Exception("Event not added to data base!");
			}
		}
		catch(Exception e) {
			Text failText = new Text("Create event failed! \n" + e.getMessage());
			failText.setId("failedText");
			
			view.showInCenter(failText);
		}
	}
	
	// Retrieve seats prices from text fields
	private int[][] retrieveSeatsPricesFromTextField(TextField[][] textFieldsOfSeatPrices, int numOfMaxSeatsInRow) throws Exception {
		try {
			int seatPrices[][] = new int[textFieldsOfSeatPrices.length][numOfMaxSeatsInRow];
			for(int rowNum = 0; rowNum < textFieldsOfSeatPrices.length; rowNum++) {
				for(int seatNum = 0; seatNum < numOfMaxSeatsInRow; seatNum++) {
					//System.out.println("(" + rowNum + "," + seatNum + ") : " + textFieldsOfSeatPrices[rowNum][seatNum].getText());
					seatPrices[rowNum][seatNum] = Integer.parseInt(textFieldsOfSeatPrices[rowNum][seatNum].getText());
				}
			}
			return seatPrices;
		}
		catch(Exception e) {
			throw new Exception("Seats prices are not valid! \nShould be 0 or bigger!");
		}
	}
	
	// Show select event to edit date form
	private void showSelectEventToEditDateForm() {
		try {
			Set<Integer> idsOfEventsThatManage = DataBaseCommandsForManagers.getEventsIdsThatManagedByTheUserInDataBase(connectedUser);
			if(idsOfEventsThatManage.size() == 0)
				throw new Exception("You don't have events that you manage!");
			
			// Grid pane of head line
			GridPane gridOfHeadLine = new GridPane();
			gridOfHeadLine.setHgap(10);
			gridOfHeadLine.setVgap(10);
			gridOfHeadLine.add(view.getSelectEventToEditDateFormHeadLine(), 0, 0);
			
			// Grid pane of form
			GridPane gridPaneOfForm = new GridPane();
			gridPaneOfForm.setHgap(10);
			gridPaneOfForm.setVgap(10);
			gridPaneOfForm.add(view.getSelectEventToEditFormEventIdSubTitle(), 0, 0);
			gridPaneOfForm.add(view.getSelectEventToEditFormEventDetailsSubTitle(), 1, 0);
			int rowNumInGridPaneOfForm = 1;
			for(int eventId : idsOfEventsThatManage) { // Move on every event id that the user manage
				Text eventIdInText = new Text("" + eventId);
				eventIdInText.setId("textInForm");
				gridPaneOfForm.add(eventIdInText, 0, rowNumInGridPaneOfForm);
				Text eventSummeryText = new Text(DataBaseCommandsForEvents.searchEventByEventIdInDataBase(eventId).getSummeryOfEvent());
				eventSummeryText.setId("textInForm");
				gridPaneOfForm.add(eventSummeryText, 1, rowNumInGridPaneOfForm);
				rowNumInGridPaneOfForm++;
			}
			
			// Grid pane of select event
			GridPane gridPaneOfSelectEvent = new GridPane();
			gridPaneOfSelectEvent.setHgap(10);
			gridPaneOfSelectEvent.setVgap(10);
			gridPaneOfSelectEvent.add(view.getSelectEventIdTextInSelectEventToEdit(), 0, 0);
			gridPaneOfSelectEvent.add(view.getClearTextFieldOfEventIdToEditEventDate(), 1, 0);
			
			// Grid pane of button
			GridPane gridPaneOfButton = new GridPane();
			gridPaneOfButton.add(view.getSelectEventToEditDateButton(), 0, 0);
			
			// Grid pane of all
			GridPane newDetailsToShow = new GridPane();
			newDetailsToShow.setHgap(10);
			newDetailsToShow.setVgap(10);
			newDetailsToShow.add(gridOfHeadLine, 0, 0);
			newDetailsToShow.add(gridPaneOfForm, 0, 1);
			newDetailsToShow.add(gridPaneOfSelectEvent, 0, 2);
			newDetailsToShow.add(gridPaneOfButton, 0, 3);
			newDetailsToShow.setAlignment(Pos.CENTER);
			
            // Scroll pane (can be a lot of events)
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(newDetailsToShow);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            
			view.showInCenter(scrollPane);
		}
		catch(Exception e) {
			Text failText = new Text("Show select event to edit date failed! \n" + e.getMessage());
			failText.setId("failedText");
			
			view.showInCenter(failText);
		}
	}
	
	// Show edit event date form
	private void showEditEventDateForm() {
		try {
			int selectedEventId;
			Event selectedEvent;
			try {
				selectedEventId = Integer.parseInt(view.getTextFieldOfEventIdToEditEventDate().getText());
				selectedEvent = DataBaseCommandsForEvents.searchEventByEventIdInDataBase(selectedEventId);
				Set<Integer> idsOfEventsThatManage = DataBaseCommandsForManagers.getEventsIdsThatManagedByTheUserInDataBase(connectedUser);
				if(!idsOfEventsThatManage.contains(selectedEventId))
					throw new Exception("You entered invalid event id!");
			}
			catch(Exception e) {
				throw new Exception("You entered invalid event id!");
			}
			
			// Grid pane of head line
			GridPane gridOfHeadLine = new GridPane();
			gridOfHeadLine.setHgap(10);
			gridOfHeadLine.setVgap(10);
			gridOfHeadLine.add(view.getEditEventDateFormHeadLine(), 0, 0);
			
			// Grid pane of form
			GridPane gridPaneOfForm = new GridPane();
			gridPaneOfForm.setHgap(10);
			gridPaneOfForm.setVgap(10);
			gridPaneOfForm.add(view.getYearTextToEditEvent(), 0, 1);
			view.clearTextFieldOfYearToEditEventDate();
			view.getTextFieldOfYearToEditEventDate().setText("" + selectedEvent.getEventYear());
			gridPaneOfForm.add(view.getTextFieldOfYearToEditEventDate(), 1, 1);
			gridPaneOfForm.add(view.getMonthTextToEditEvent(), 0, 2);
			view.clearTextFieldOfMonthToEditEventDate();
			view.getTextFieldOfMonthToEditEventDate().setText("" + selectedEvent.getEventMonth());
			gridPaneOfForm.add(view.getTextFieldOfMonthToEditEventDate(), 1, 2);
			gridPaneOfForm.add(view.getDayTextToEditEvent(), 0, 3);
			view.clearTextFieldOfDayToEditEventDate();
			view.getTextFieldOfDayToEditEventDate().setText("" + selectedEvent.getEventDay());
			gridPaneOfForm.add(view.getTextFieldOfDayToEditEventDate(), 1, 3);
			gridPaneOfForm.add(view.getHourTextToEditEvent(), 0, 4);
			view.clearTextFieldOfHourToEditEventDate();
			view.getTextFieldOfHourToEditEventDate().setText("" + selectedEvent.getEventHour());
			gridPaneOfForm.add(view.getTextFieldOfHourToEditEventDate(), 1, 4);
			gridPaneOfForm.add(view.getMinuteTextToEditEvent(), 0, 5);
			view.clearTextFieldOfMinuteToEditEventDate();
			view.getTextFieldOfMinuteToEditEventDate().setText("" + selectedEvent.getEventMinute());
			gridPaneOfForm.add(view.getTextFieldOfMinuteToEditEventDate(), 1, 5);
			
			
			// Grid pane of button
			GridPane gridPaneOfButton = new GridPane();
			gridPaneOfButton.add(view.getEditEventDateButton(), 0, 0);
			
			// Grid pane of all
			GridPane newDetailsToShow = new GridPane();
			newDetailsToShow.setHgap(10);
			newDetailsToShow.setVgap(10);
			newDetailsToShow.add(gridOfHeadLine, 0, 0);
			newDetailsToShow.add(gridPaneOfForm, 0, 1);
			newDetailsToShow.add(gridPaneOfButton, 0, 2);
			newDetailsToShow.setAlignment(Pos.CENTER);
			
			view.showInCenter(newDetailsToShow);
		}
		catch(Exception e) {
			Text failText = new Text("Select event to edit date failed! \nYou entered invalid event id!");
			failText.setId("failedText");
			
			view.showInCenter(failText);
		}
	}
	
	// Try edit event date
	private void tryEditEventDate() {
		try {
			int year, month, day, hour, minute;
			try {
				year = Integer.parseInt(view.getTextFieldOfYearToEditEventDate().getText());
				month = Integer.parseInt(view.getTextFieldOfMonthToEditEventDate().getText());
				day = Integer.parseInt(view.getTextFieldOfDayToEditEventDate().getText());
				hour = Integer.parseInt(view.getTextFieldOfHourToEditEventDate().getText());
				minute = Integer.parseInt(view.getTextFieldOfMinuteToEditEventDate().getText());
			}
			catch(Exception e) {
				throw new Exception("You entered invalid values!");
			}
			
			int selectedEventId = Integer.parseInt(view.getTextFieldOfEventIdToEditEventDate().getText());
			Event selectedEvent = DataBaseCommandsForEvents.searchEventByEventIdInDataBase(selectedEventId);
			
			if(selectedEvent.setEventDate(year, month, day, hour, minute)) { // Valid date
				DataBaseCommandsForEvents.updateEventInDataBase(selectedEvent, selectedEventId);
				Text succeedText = new Text("Event date changed successfully!");
				succeedText.setId("succeedText");
				
				view.showInCenter(succeedText);
			}
			else // Invalid date
				throw new Exception("You entered invalid event date! \nIt should be in the future and valid!");
			
		} catch (Exception e) {
			Text failText = new Text("Edit event date failed! \n" + e.getMessage());
			failText.setId("failedText");
			
			view.showInCenter(failText);
		}
	}
	
	// Show cancel event form
	private void showCancelEventForm() {
		try {
			Set<Integer> idsOfEventsThatManage = DataBaseCommandsForManagers.getEventsIdsThatManagedByTheUserInDataBase(connectedUser);
			if(idsOfEventsThatManage.size() == 0)
				throw new Exception("You don't have events that you manage!");
			
			// Grid pane of head line
			GridPane gridOfHeadLine = new GridPane();
			gridOfHeadLine.setHgap(10);
			gridOfHeadLine.setVgap(10);
			gridOfHeadLine.add(view.getCancelEventFormHeadLine(), 0, 0);
			
			// Grid pane of form
			GridPane gridPaneOfForm = new GridPane();
			gridPaneOfForm.setHgap(10);
			gridPaneOfForm.setVgap(10);
			gridPaneOfForm.add(view.getCancelEventFormEventIdSubTitle(), 0, 0);
			gridPaneOfForm.add(view.getCancelEventFormEventDetailsSubTitle(), 1, 0);
			int rowNumInGridPaneOfForm = 1;
			for(int eventId : idsOfEventsThatManage) { // Move on every event id that the user manage
				Text eventIdInText = new Text("" + eventId);
				eventIdInText.setId("textInForm");
				gridPaneOfForm.add(eventIdInText, 0, rowNumInGridPaneOfForm);
				Text eventSummeryText = new Text(DataBaseCommandsForEvents.searchEventByEventIdInDataBase(eventId).getSummeryOfEvent());
				eventSummeryText.setId("textInForm");
				gridPaneOfForm.add(eventSummeryText, 1, rowNumInGridPaneOfForm);
				rowNumInGridPaneOfForm++;
			}
			
			// Grid pane of select event
			GridPane gridPaneOfSelectEvent = new GridPane();
			gridPaneOfSelectEvent.setHgap(10);
			gridPaneOfSelectEvent.setVgap(10);
			gridPaneOfSelectEvent.add(view.getEventIdTextToCancelEvent(), 0, 0);
			gridPaneOfSelectEvent.add(view.getClearTextFieldOfEventIdToCancel(), 1, 0);
			
			
			// Grid pane of button
			GridPane gridPaneOfButton = new GridPane();
			gridPaneOfButton.add(view.getCancelEventButton(), 0, 0);
			
			// Grid pane of all
			GridPane newDetailsToShow = new GridPane();
			newDetailsToShow.setHgap(10);
			newDetailsToShow.setVgap(10);
			newDetailsToShow.add(gridOfHeadLine, 0, 0);
			newDetailsToShow.add(gridPaneOfForm, 0, 1);
			newDetailsToShow.add(gridPaneOfSelectEvent, 0, 2);
			newDetailsToShow.add(gridPaneOfButton, 0, 3);
			newDetailsToShow.setAlignment(Pos.CENTER);
			
            // Scroll pane (can be a lot of events)
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(newDetailsToShow);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            
			view.showInCenter(scrollPane);
		}
		catch(Exception e) {
			Text failText = new Text("Show cancel event failed! \n" + e.getMessage());
			failText.setId("failedText");
			
			view.showInCenter(failText);
		}
	}
	
	// Try cancel event
	private void tryCancelEvent() {		
		try {
			int selectedEventId;
			try {
				selectedEventId = Integer.parseInt(view.getTextFieldOfEventIdToCancel().getText());
				Set<Integer> idsOfEventsThatManage = DataBaseCommandsForManagers.getEventsIdsThatManagedByTheUserInDataBase(connectedUser);
				if(!idsOfEventsThatManage.contains(selectedEventId))
					throw new Exception("You entered invalid event id!");
			}
			catch(Exception e) {
				throw new Exception("You entered invalid event id!");
			}
			
			// Remove event from data base
			DataBaseCommandsForEvents.removeEventFromDataBase(DataBaseCommandsForEvents.searchEventByEventIdInDataBase(selectedEventId));
			Text succeedText = new Text("Event canceled successfully!");
			succeedText.setId("succeedText");
			view.showInCenter(succeedText);
		} catch (Exception e) {
			Text failText = new Text("Cancel event failed! \n" + e.getMessage());
			failText.setId("failedText");
			view.showInCenter(failText);
		}
	}
	
	// Show select event to make invitation to form
	private void showSelectEventToMakeInvitationToForm() {
		try {
			Set<Integer> idsOfEvents = DataBaseCommandsForEvents.getAllEventsIdsInDataBase();
			if(idsOfEvents.size() == 0)
				throw new Exception("There are no events!");
			
			// Grid pane of head line
			GridPane gridOfHeadLine = new GridPane();
			gridOfHeadLine.setHgap(10);
			gridOfHeadLine.setVgap(10);
			gridOfHeadLine.add(view.getSelectEventToMakeInvitationFormHeadLine(), 0, 0);
			
			// Grid pane of form
			GridPane gridPaneOfForm = new GridPane();
			gridPaneOfForm.setHgap(10);
			gridPaneOfForm.setVgap(10);
			gridPaneOfForm.add(view.getMakeInvitationFormEventIdSubTitle(), 0, 0);
			gridPaneOfForm.add(view.getMakeInvitationFormEventDetailsSubTitle(), 1, 0);
			int rowNumInGridPaneOfForm = 1;
			for(int eventId : idsOfEvents) { // Move on every event id
				Text eventIdInText = new Text("" + eventId);
				eventIdInText.setId("textInForm");
				gridPaneOfForm.add(eventIdInText, 0, rowNumInGridPaneOfForm);
				Text eventSummeryText = new Text(DataBaseCommandsForEvents.searchEventByEventIdInDataBase(eventId).getSummeryOfEvent());
				eventSummeryText.setId("textInForm");
				gridPaneOfForm.add(eventSummeryText, 1, rowNumInGridPaneOfForm);
				rowNumInGridPaneOfForm++;
			}
			
			// Grid pane of select event
			GridPane gridPaneOfSelectEvent = new GridPane();
			gridPaneOfSelectEvent.setHgap(10);
			gridPaneOfSelectEvent.setVgap(10);
			gridPaneOfSelectEvent.add(view.getEventIdTextToMakeInvitation(), 0, 0);
			gridPaneOfSelectEvent.add(view.getClearTextFieldOfEventIdToMakeInvitation(), 1, 0);
			
			// Grid pane of button
			GridPane gridPaneOfButton = new GridPane();
			gridPaneOfButton.add(view.getSelectEventToMakeInvitationToButton(), 0, 0);
			
			// Grid pane of all
			GridPane newDetailsToShow = new GridPane();
			newDetailsToShow.setHgap(10);
			newDetailsToShow.setVgap(10);
			newDetailsToShow.add(gridOfHeadLine, 0, 0);
			newDetailsToShow.add(gridPaneOfForm, 0, 1);
			newDetailsToShow.add(gridPaneOfSelectEvent, 0, 2);
			newDetailsToShow.add(gridPaneOfButton, 0, 3);
			newDetailsToShow.setAlignment(Pos.CENTER);
			
			// Scroll pane (can be a lot of events)
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(newDetailsToShow);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            
			view.showInCenter(scrollPane);
		}
		catch(Exception e) {
			Text failText = new Text("Show select event to make invitation to failed! \n" + e.getMessage());
			failText.setId("failedText");
			view.showInCenter(failText);
		}
	}
	
	// Show make invitation to event form
	private void showMakeInvitationToEventForm() {
		try {
			try {
				int selectedEventId = Integer.parseInt(view.getTextFieldOfEventIdToMakeInvitation().getText());
				Event selectedEvent = DataBaseCommandsForEvents.searchEventByEventIdInDataBase(selectedEventId);
				if(selectedEvent == null)
					throw new Exception("You entered invalid event id!");
			}
			catch(Exception e) {
				throw new Exception("You entered invalid event id!");
			}
			
			// Grid pane of head line
			GridPane gridOfHeadLine = new GridPane();
			gridOfHeadLine.setHgap(10);
			gridOfHeadLine.setVgap(10);
			gridOfHeadLine.add(view.getMakeInvitationToEventFormHeadLine(), 0, 0);
			
			// Grid pane of form
			GridPane gridPaneOfForm = new GridPane();
			gridPaneOfForm.setHgap(10);
			gridPaneOfForm.setVgap(10);
			gridPaneOfForm.add(view.getNumberOfSeatsTextToMakeInvitationToEvent(), 0, 0);
			gridPaneOfForm.add(view.getClearTextFieldOfNumOfSeatsInInvitationToMakeInvitation(), 1, 0);
			gridPaneOfForm.add(view.getHealthStatusTextToMakeInvitationToEvent(), 0, 1);
			Set<String> allHealthStatusNames = DataBaseCommandsForHealthStatus.getAllHealthStatusesNamesInDataBase();
			List<String> sortedList = new ArrayList<>(allHealthStatusNames);
			Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
			view.clearComboBoxOfHealthStatusToMakeInvitation();
			for(String healthStatus : sortedList)
				view.addNameToAllDepartmentsNamesComboBox(healthStatus);
			view.getComboBoxOfHealthStatusToMakeInvitation().setValue(DataBaseCommands.HEALTHY_HEALTH_STATUS_NAME_IN_DATA_BASE);
			gridPaneOfForm.add(view.getComboBoxOfHealthStatusToMakeInvitation(), 1, 1);
			gridPaneOfForm.add(view.getSeatPriceTextToMakeInvitationToEvent(), 0, 2);
			gridPaneOfForm.add(view.getClearTextFieldOfSeatPriceToMakeInvitation(), 1, 2);
			
			// Grid pane of button
			GridPane gridPaneOfButton = new GridPane();
			gridPaneOfButton.add(view.getShowOfferSeatsInvitationButton(), 0, 0);
			
			// Grid pane of all
			GridPane newDetailsToShow = new GridPane();
			newDetailsToShow.setHgap(10);
			newDetailsToShow.setVgap(10);
			newDetailsToShow.add(gridOfHeadLine, 0, 0);
			newDetailsToShow.add(gridPaneOfForm, 0, 1);
			newDetailsToShow.add(gridPaneOfButton, 0, 2);
			newDetailsToShow.setAlignment(Pos.CENTER);
			
			view.showInCenter(newDetailsToShow);
		}
		catch(Exception e) {
			Text failText = new Text("Select event to make invitation to failed! \n" + e.getMessage());
			failText.setId("failedText");
			
			view.showInCenter(failText);
		}
	}
	
	// Show offer seats invitation to event form
	private void showOfferSeatsInvitationToEventForm() {
		try {
			int eventIdChoice = Integer.parseInt(view.getTextFieldOfEventIdToMakeInvitation().getText());
			int numOfSpaceSeatsNear = DataBaseCommandsForHealthStatus.getSpaceOfHealthStatusInDataBase(view.getComboBoxOfHealthStatusToMakeInvitation().getValue());
			int numOfSeatsInInvitation, seatPrice;
			boolean allSeatsNeedToBeTogether = true;
			Set<PlaceForInvitation> placesForInvitation;
			Event selectedEvent;
			Invitation newInvitation;
			try {
				numOfSeatsInInvitation = Integer.parseInt(view.getTextFieldOfNumOfSeatsInInvitationToMakeInvitation().getText());
			}
			catch(Exception e) {
				throw new Exception("You entered invalid number of seats!");
			}
			try {
				seatPrice = Integer.parseInt(view.getTextFieldOfSeatPriceToMakeInvitation().getText());
			}
			catch(Exception e) {
				throw new Exception("You entered invalid seat price!");
			}
			
			// Get event and seat from data base
			selectedEvent = DataBaseCommandsForEvents.searchEventByEventIdInDataBase(eventIdChoice);			
			selectedEvent.setEventSeats(DataBaseCommandsForSeats.getSeatsOfEventInDataBase(selectedEvent));
			
			// Make new invitation
			newInvitation = new Invitation(connectedUser, selectedEvent, numOfSeatsInInvitation, allSeatsNeedToBeTogether, numOfSpaceSeatsNear);
			
			// Try to allocate invitation
			placesForInvitation = selectedEvent.tryToAllocateInvitation(newInvitation, seatPrice);
			
			if(placesForInvitation != null) { // There is place in the event to the invitation
				
				// Grid pane of head line
				GridPane gridOfHeadLine = new GridPane();
				gridOfHeadLine.setHgap(10);
				gridOfHeadLine.setVgap(10);
				gridOfHeadLine.add(view.getOfferSeatsInvitationToEventFormHeadLine(), 0, 0);
				
				// Grid pane of form
				GridPane gridPaneOfForm = new GridPane();
				gridPaneOfForm.setHgap(10);
				gridPaneOfForm.setVgap(10);
				gridPaneOfForm.add(view.getOfferSeatsInvitationToEventFormRowNumSubTitle(), 0, 0);
				gridPaneOfForm.add(view.getOfferSeatsInvitationToEventFormSeatsNumAndCostsSubTitle(), 1, 0);
				int rowNumInGridPaneOfForm = 1;
				for(PlaceForInvitation places : placesForInvitation) {
					Text rowText = new Text("" + (places.getRowIndex() + 1));
					rowText.setId("textInForm");
					gridPaneOfForm.add(rowText, 0, rowNumInGridPaneOfForm);
					String stringOfSeatsNumsAndCosts = "";
					for(int seatNumToShow = 0; seatNumToShow < places.getNumOfSeats(); seatNumToShow++) {
						int seatCost = selectedEvent.getEventSeats()[places.getRowIndex()][places.getSeatIndex() + seatNumToShow].getSeatPrice();
						stringOfSeatsNumsAndCosts += (places.getSeatIndex() + seatNumToShow + 1) + " - costs " + seatCost;
						if(seatNumToShow != places.getNumOfSeats() - 1)
							stringOfSeatsNumsAndCosts += "\n";
					}
					Text seatsAndCosts = new Text(stringOfSeatsNumsAndCosts);
					seatsAndCosts.setId("textInForm");
					gridPaneOfForm.add(seatsAndCosts, 1, rowNumInGridPaneOfForm);
					rowNumInGridPaneOfForm++;
				}
				
				// Grid pane of buttons
				GridPane gridPaneOfButtons = new GridPane();
				gridPaneOfButtons.add(view.getMakeInvitationToEventButton(), 0, 0);
				gridPaneOfButtons.add(new Text("     "), 1, 0);
				gridPaneOfButtons.add(view.getDontMakeInvitationToEventButton(), 2, 0);
				
				// Grid pane of all
				GridPane newDetailsToShow = new GridPane();
				newDetailsToShow.setHgap(10);
				newDetailsToShow.setVgap(10);
				newDetailsToShow.add(gridOfHeadLine, 0, 0);
				newDetailsToShow.add(gridPaneOfForm, 0, 1);
				newDetailsToShow.add(gridPaneOfButtons, 0, 2);
				newDetailsToShow.setAlignment(Pos.CENTER);
				
	            // Scroll pane (can be a lot of offered seats)
	            ScrollPane scrollPane = new ScrollPane();
	            scrollPane.setContent(newDetailsToShow);
	            scrollPane.setFitToWidth(true);
	            scrollPane.setFitToHeight(true);
				
				view.showInCenter(scrollPane);
				
				// Send want make invitation to try make invitation to event on action			
				view.getMakeInvitationToEventButton().setOnAction(e -> { tryMakeInvitationToEvent(true, selectedEvent, eventIdChoice, placesForInvitation, 
						newInvitation); });
				
				// Send don't want make invitation to try make invitation to event on action
				view.getDontMakeInvitationToEventButton().setOnAction(e -> { tryMakeInvitationToEvent(false, selectedEvent, eventIdChoice, placesForInvitation, 
						newInvitation); });
			}
			else // There is no place for the invitation in the event
				throw new Exception("There is no place for that invitation in that event!");
		} catch (Exception e) {
			Text failText = new Text("Show offer seats to invitation failed! \n" + e.getMessage());
			failText.setId("failedText");
			
			view.showInCenter(failText);
		}
	}
	
	// Try make invitation to event
	private void tryMakeInvitationToEvent(boolean wantTheOffersSeats, Event selectedEvent, int eventIdChoice, 
			Set<PlaceForInvitation> placesForInvitation, Invitation newInvitation) {
		try {
			if(wantTheOffersSeats) { // User want the offer seats
				// Allocate invitation
				selectedEvent.allocateInvitation(placesForInvitation, newInvitation);
				
				// Check if seats are together or not together
				if(placesForInvitation.size() == 1)
					newInvitation.setSeatTogether(true);
				else
					newInvitation.setSeatTogether(false);
				
				// Add the invitation to data base
				DataBaseCommandsForInvitations.addInvitationToDataBase(newInvitation);
				
				// Update the event
				DataBaseCommandsForEvents.updateEventInDataBase(selectedEvent, eventIdChoice);
				
				// Update seats
				Seat eventSeats[][] = selectedEvent.getEventSeats();
				for(int rowNum = 0; rowNum < selectedEvent.getEventNumOfRows(); rowNum++) {
					for(int seatNum = 0; seatNum < selectedEvent.getEventMaxNumOfSeatsInRow(); seatNum++) {
						int seatIdInDb = DataBaseCommandsForSeats.searchSeatIdBySeatDetailsInDataBase(eventSeats[rowNum][seatNum]);
						DataBaseCommandsForSeats.updateSeatInDataBase(eventSeats[rowNum][seatNum], seatIdInDb);
					}
				}
				Text succeedText = new Text("Invitation made successfully!");
				succeedText.setId("succeedText");
				
				view.showInCenter(succeedText);
			}
			else // User don't want the offer seats
				throw new Exception("Invitation didn't made because you didn't want the offered seats!");
		}
		catch(Exception e) {
			Text failText = new Text("Make invitation to event failed! \n" + e.getMessage());
			failText.setId("failedText");
			
			view.showInCenter(failText);
		}
	}
	
	// Show my invitations
	private void showMyInvitations() {
		try {
			Set<Invitation> userInvitations = DataBaseCommandsForInvitations.getInvitationsOfUserInDataBase(connectedUser);
			int numOfInvitations = userInvitations.size();
			if(numOfInvitations == 0)
				throw new Exception("You don't have invitations!");
			
			// Grid pane of head line
			GridPane gridOfHeadLine = new GridPane();
			gridOfHeadLine.setHgap(10);
			gridOfHeadLine.setVgap(10);
			gridOfHeadLine.add(view.getShowMyInvitationsFormHeadLine(), 0, 0);
			
			// Grid pane of form
			GridPane gridPaneOfForm = new GridPane();
			gridPaneOfForm.setHgap(10);
			gridPaneOfForm.setVgap(10);
			int rowInGridPaneOfForm = 0;
			for(Invitation invitation : userInvitations) {
				Text invitationText = new Text(invitation.toString());
				invitationText.setId("textInForm");
				gridPaneOfForm.add(invitationText, 0, rowInGridPaneOfForm);
				rowInGridPaneOfForm++;
			}
			
			// Grid pane of all
			GridPane newDetailsToShow = new GridPane();
			newDetailsToShow.setHgap(10);
			newDetailsToShow.setVgap(10);
			newDetailsToShow.add(gridOfHeadLine, 0, 0);
			newDetailsToShow.add(gridPaneOfForm, 0, 1);
			newDetailsToShow.setAlignment(Pos.CENTER);
			
            // Scroll pane (can be a lot of invitations)
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(newDetailsToShow);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            
			view.showInCenter(scrollPane);
		}
		catch(Exception e) {
			Text failText = new Text("Show my invitations failed! \n" + e.getMessage());
			failText.setId("failedText");
			
			view.showInCenter(failText);
		}
	}
	
	// Show change user permissions form
	private void showChangeUserPermissionsForm() {
		
		// Grid pane of head line
		GridPane gridOfHeadLine = new GridPane();
		gridOfHeadLine.setHgap(10);
		gridOfHeadLine.setVgap(10);
		gridOfHeadLine.add(view.getChangeUserPermissionsFormHeadLine(), 0, 0);
		
		// Grid pane of form
		GridPane gridPaneOfForm = new GridPane();
		gridPaneOfForm.setHgap(10);
		gridPaneOfForm.setVgap(10);
		gridPaneOfForm.add(view.getEmailTextToChangeUserPermissions(), 0, 0);
		gridPaneOfForm.add(view.getClearTextFieldOfEmailToChangeUsersPermissions(), 1, 0);
		gridPaneOfForm.add(view.getNewPermissionsOfTheUserTextToChangeUserPermissions(), 0, 1);
		gridPaneOfForm.add(view.getClearTextFieldOfPermissionToChangeUsersPermissions(), 1, 1);
		
		// Grid pane of button
		GridPane gridPaneOfButton = new GridPane();
		gridPaneOfButton.add(view.getChangeUserPermissionsButton(), 0, 0);
		
		// Grid pane of all
		GridPane newDetailsToShow = new GridPane();
		newDetailsToShow.setHgap(10);
		newDetailsToShow.setVgap(10);
		newDetailsToShow.add(gridOfHeadLine, 0, 0);
		newDetailsToShow.add(gridPaneOfForm, 0, 1);
		newDetailsToShow.add(gridPaneOfButton, 0, 2);
        newDetailsToShow.setAlignment(Pos.CENTER);
        
		view.showInCenter(newDetailsToShow);
	}
	
	// Try change users permissions
	private void tryChangeUsersPermissions() {
		try {
			String email = view.getTextFieldOfEmailToChangeUsersPermissions().getText();
			User userToChange = DataBaseCommandsForUsers.searchUserByEmailInDataBase(email);
			String permissionName = view.getTextFieldOfPermissionToChangeUsersPermissions().getText();
			int newPermissionId = DataBaseCommandsForPermissions.searchPermissionIdByPermissionNameInDataBase(permissionName);
			
			if(userToChange == null)
				throw new Exception("There is no user with that email!");
			
			if(newPermissionId == DataBaseCommands.PERMISSION_ID_NOT_FOUND_IN_DATA_BASE)
				throw new Exception("You entered invalid permission!");
			
			DataBaseCommandsForUsers.updateUserPermissionsInDataBase(userToChange, newPermissionId);
			
			// Change permissions to Admin - add all the events as manage event to user
			if(permissionName.equals(DataBaseCommands.ADMIN_PERMISSION_NAME_IN_DATA_BASE)) {
				Set<Integer> allEventsIds = DataBaseCommandsForEvents.getAllEventsIdsInDataBase();
				for(int eventId : allEventsIds)
					DataBaseCommandsForManagers.addManagerToDataBase(userToChange, DataBaseCommandsForEvents.searchEventByEventIdInDataBase(eventId));
			}
			
			// Change permissions to Order_Events - remove managed events
			else if(permissionName.equals(DataBaseCommands.ORDER_EVENTS_PERMISSION_NAME_IN_DATA_BASE))
				DataBaseCommandsForManagers.removeUserFromBeingManagerFromDataBase(userToChange);
			
			connectedUser = DataBaseCommandsForUsers.searchUserByEmailInDataBase(connectedUser.getUserEmail());
			
			Text succeedText = new Text("The permissions changed successfully!");
			succeedText.setId("succeedText");
			
			view.showInCenter(succeedText);
		} catch (Exception e) {
			Text failText = new Text("Change users permissions failed! \n" + e.getMessage());
			failText.setId("failedText");
			
			view.showInCenter(failText);
		}
		
		showNewMenu();
	}
	
	// Show change my information form
	private void showChangeMyInformationForm() {
		
		// Grid pane of head line
		GridPane gridOfHeadLine = new GridPane();
		gridOfHeadLine.setHgap(10);
		gridOfHeadLine.setVgap(10);
		gridOfHeadLine.add(view.getChangeMyInformationFormHeadLine(), 0, 0);
		
		// Grid pane of form
		GridPane gridPaneOfForm = new GridPane();
		gridPaneOfForm.setHgap(10);
		gridPaneOfForm.setVgap(10);
		gridPaneOfForm.add(view.getNameTextToChangeMyInformation(), 0, 0);		
		view.clearTextFieldOfNameToChangeMyInformation();
		view.getTextFieldOfNameToChangeMyInformation().setText(connectedUser.getUserName());
		gridPaneOfForm.add(view.getTextFieldOfNameToChangeMyInformation(), 1, 0);
		gridPaneOfForm.add(view.getEmailTextToChangeMyInformation(), 0, 1);
		view.clearTextFieldOfEmailToChangeMyInformation();
		view.getTextFieldOfEmailToChangeMyInformation().setText(connectedUser.getUserEmail());
		gridPaneOfForm.add(view.getTextFieldOfEmailToChangeMyInformation(), 1, 1);
		gridPaneOfForm.add(view.getPhoneTextToChangeMyInformation(), 0, 2);
		view.clearTextFieldOfPhoneToChangeMyInformation();
		view.getTextFieldOfPhoneToChangeMyInformation().setText(connectedUser.getUserPhoneNumber());
		gridPaneOfForm.add(view.getTextFieldOfPhoneToChangeMyInformation(), 1, 2);
		
		// Grid pane of button
		GridPane gridPaneOfButton = new GridPane();
		gridPaneOfButton.add(view.getChangeMyInformationButton(), 0, 0);
		
		// Grid pane of all
		GridPane newDetailsToShow = new GridPane();
		newDetailsToShow.setHgap(10);
		newDetailsToShow.setVgap(10);
		newDetailsToShow.add(gridOfHeadLine, 0, 0);
		newDetailsToShow.add(gridPaneOfForm, 0, 1);
		newDetailsToShow.add(gridPaneOfButton, 0, 2);
        newDetailsToShow.setAlignment(Pos.CENTER);
		
		view.showInCenter(newDetailsToShow);
	}
	
	// Try change my information
	private void tryChangeMyInformation() {
		try {
			int userId = DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(connectedUser);
			String name = view.getTextFieldOfNameToChangeMyInformation().getText();
			String email = view.getTextFieldOfEmailToChangeMyInformation().getText();
			String phoneNumber = view.getTextFieldOfPhoneToChangeMyInformation().getText();
			User searchUser;
			
			// Check if email is already register and not connected user
			searchUser = DataBaseCommandsForUsers.searchUserByEmailInDataBase(email);
			if((searchUser != null) && (!searchUser.getUserEmail().equals(connectedUser.getUserEmail())))
				throw new Exception("Email is already exist!");
			
			// Check if phone is already register and not connected user
			searchUser = DataBaseCommandsForUsers.searchUserByPhoneInDataBase(phoneNumber);
			if((searchUser != null) && (!searchUser.getUserPhoneNumber().equals(connectedUser.getUserPhoneNumber())))
				throw new Exception("Phone number is already exist!");
			
			User updatedUser = new User(name, email, phoneNumber);
			
			DataBaseCommandsForUsers.updateUserInDataBase(updatedUser, userId);
			
			Text succeedText = new Text("Change my information successfully!");
			succeedText.setId("succeedText");
			
			view.showInCenter(succeedText);
			
			connectedUser = DataBaseCommandsForUsers.searchUserByEmailInDataBase(email);
			
		} catch (Exception e) {
			Text failText = new Text("Change my information failed! \n" + e.getMessage());
			failText.setId("failedText");
			
			view.showInCenter(failText);
		}
		
		Text helloText = new Text("Hello " + connectedUser.getUserName() + "!");
		helloText.setId("helloText");
		
		view.showInTop(helloText);
	}
	
	// Show select event to show form
	private void showSelectEventToShowForm() {
		try {
			Set<Integer> idsOfEvents = DataBaseCommandsForEvents.getAllEventsIdsInDataBase();
			if(idsOfEvents.size() == 0)
				throw new Exception("There are no events!");
			
			// Grid pane of head line
			GridPane gridOfHeadLine = new GridPane();
			gridOfHeadLine.setHgap(10);
			gridOfHeadLine.setVgap(10);
			gridOfHeadLine.add(view.getSelectEventToShowFormHeadLine(), 0, 0);
			
			// Grid pane of form
			GridPane gridPaneOfForm = new GridPane();
			gridPaneOfForm.setHgap(10);
			gridPaneOfForm.setVgap(10);
			gridPaneOfForm.add(view.getSelectEventToShowFormEventIdSubTitle(), 0, 0);
			gridPaneOfForm.add(view.getSelectEventToShowFormEventDetailsSubTitle(), 1, 0);
			int rowNumInNewDetailsToShow = 1;
			for(int eventId : idsOfEvents) { // Move on every event id
				Text eventIdInText = new Text("" + eventId);
				eventIdInText.setId("textInForm");
				gridPaneOfForm.add(eventIdInText, 0, rowNumInNewDetailsToShow);
				Text eventSummeryText = new Text(DataBaseCommandsForEvents.searchEventByEventIdInDataBase(eventId).getSummeryOfEvent());
				eventSummeryText.setId("textInForm");
				gridPaneOfForm.add(eventSummeryText, 1, rowNumInNewDetailsToShow);
				rowNumInNewDetailsToShow++;
			}
			
			// Grid pane of select event
			GridPane gridPaneOfSelectEvent = new GridPane();
			gridPaneOfSelectEvent.setHgap(10);
			gridPaneOfSelectEvent.setVgap(10);
			gridPaneOfSelectEvent.add(view.getSelectedEventIdTextToShowEventSeats(), 0, 0);
			gridPaneOfSelectEvent.add(view.getClearTextFieldOfEventIdToShowEventSeats(), 1, 0);
			
			// Grid pane of button
			GridPane gridPaneOfButton = new GridPane();
			gridPaneOfButton.add(view.getShowEventSeatsButton(), 0, 0);
			
			// Grid pane of all
			GridPane newDetailsToShow = new GridPane();
			newDetailsToShow.setHgap(10);
			newDetailsToShow.setVgap(10);
			newDetailsToShow.add(gridOfHeadLine, 0, 0);
			newDetailsToShow.add(gridPaneOfForm, 0, 1);
			newDetailsToShow.add(gridPaneOfSelectEvent, 0, 2);
			newDetailsToShow.add(gridPaneOfButton, 0, 3);
	        newDetailsToShow.setAlignment(Pos.CENTER);
			
            // Scroll pane (can be a lot of seats)
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(newDetailsToShow);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            
			view.showInCenter(scrollPane);
		}
		catch(Exception e) {
			Text failText = new Text("Show select event to show failed! \n" + e.getMessage());
			failText.setId("failedText");
			
			view.showInCenter(failText);
		}
	}
	
	// Try show event seats
	private void tryShowEventSeats() {		
		try {
			int eventId;
			Event selectedEvent;
			try{
				eventId = Integer.parseInt(view.getTextFieldOfEventIdToShowEventSeats().getText());
				selectedEvent = DataBaseCommandsForEvents.searchEventByEventIdInDataBase(eventId);
				if(selectedEvent == null)
					throw new Exception("You entered invalid event id!");
				selectedEvent.setEventSeats(DataBaseCommandsForSeats.getSeatsOfEventInDataBase(selectedEvent));
			}
			catch(Exception e) {
				throw new Exception("You entered invalid event id!");
			}
			
			// Grid pane of head line
			GridPane gridOfHeadLine = new GridPane();
			gridOfHeadLine.setHgap(10);
			gridOfHeadLine.setVgap(10);
			gridOfHeadLine.add(view.getShowEventSeatsFormHeadLine(), 0, 0);
			
			// Grid pane of legend
			GridPane gridOfLegend = new GridPane();
			gridOfLegend.setHgap(10);
			gridOfLegend.setVgap(10);
			HBox legend = new HBox(20);
			legend.setPadding(new Insets(10));
			legend.getChildren().add(view.getLegendTextToShowEventSeats());
	        String[] statuses = { "Taken", "Not Available", "Available" };
	        Color[] colors = { Color.RED, Color.GRAY, Color.GREEN };
	        for(int statusIndex = 0; statusIndex < statuses.length; statusIndex++) {
	            HBox legendItem = Seat.createLegendItem(statuses[statusIndex], colors[statusIndex]);
	            legend.getChildren().add(legendItem);
	        }
	        gridOfLegend.add(legend, 0, 0);
	        
			// Grid pane of form
			GridPane gridPaneOfForm = new GridPane();
			gridPaneOfForm.setHgap(10);
			gridPaneOfForm.setVgap(10);
			Seat allSeats[][] = selectedEvent.getEventSeats();
			for(int rowNum = 0; rowNum < selectedEvent.getEventNumOfRows(); rowNum++) {
				Text rowNumText = new Text("Row number " + (rowNum + 1) + ": ");
				rowNumText.setId("textInForm");
				gridPaneOfForm.add(rowNumText, 0, rowNum);
				for(int seatNum = 0; seatNum < selectedEvent.getEventMaxNumOfSeatsInRow(); seatNum++)
					gridPaneOfForm.add(allSeats[rowNum][seatNum].getSeatAsRectangle(selectedEvent.getEventNumOfRows(), gridPaneOfForm), seatNum + 1, rowNum);
			}
	        
			// Grid pane of all
			GridPane newDetailsToShow = new GridPane();
			newDetailsToShow.setHgap(10);
			newDetailsToShow.setVgap(10);
			newDetailsToShow.add(gridOfHeadLine, 0, 0);
			newDetailsToShow.add(gridOfLegend, 0, 1);
			newDetailsToShow.add(gridPaneOfForm, 0, 2);
	        newDetailsToShow.setAlignment(Pos.CENTER);
			
            // Scroll pane (can be a lot of seats)
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(newDetailsToShow);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            
			view.showInCenter(scrollPane);
		}
		catch(Exception e) {
			Text failText = new Text("Show event seats failed! \n" + e.getMessage());
			failText.setId("failedText");
			
			view.showInCenter(failText);
		}
	}
	
	// Show add health status form
	private void showAddHealthStatusForm() {
		
		// Grid pane of head line
		GridPane gridOfHeadLine = new GridPane();
		gridOfHeadLine.setHgap(10);
		gridOfHeadLine.setVgap(10);
		gridOfHeadLine.add(view.getAddHelathStatusFormHeadLine(), 0, 0);
		
		// Grid pane of form
		GridPane gridPaneOfForm = new GridPane();
		gridPaneOfForm.setHgap(10);
		gridPaneOfForm.setVgap(10);
		gridPaneOfForm.add(view.getNameTextToAddHealthStatus(), 0, 0);
		gridPaneOfForm.add(view.getClearTextFieldOfNameToAddHealthStatus(), 1, 0);
		gridPaneOfForm.add(view.getSpaceNeededTextToAddHealthStatus(), 0, 1);
		gridPaneOfForm.add(view.getClearTextFieldOfSpaceToAddHealthStatus(), 1, 1);
		
		// Grid pane of button
		GridPane gridPaneOfButton = new GridPane();
		gridPaneOfButton.add(view.getAddHealthStatusButton(), 0, 0);
		
		// Grid pane of all
		GridPane newDetailsToShow = new GridPane();
		newDetailsToShow.setHgap(10);
		newDetailsToShow.setVgap(10);
		newDetailsToShow.add(gridOfHeadLine, 0, 0);
		newDetailsToShow.add(gridPaneOfForm, 0, 1);
		newDetailsToShow.add(gridPaneOfButton, 0, 2);
        newDetailsToShow.setAlignment(Pos.CENTER);
		
		view.showInCenter(newDetailsToShow);
	}
	
	// Try add health status
	private void tryAddHealthStatus() {
		try {
			String nameOfHealthStatus = view.getTextFieldOfNameToAddHealthStatus().getText();
			int spaceOfHealthStatus;
			try {
				spaceOfHealthStatus = Integer.parseInt(view.getTextFieldOfSpaceToAddHealthStatus().getText());
				if(spaceOfHealthStatus < 0)
					throw new Exception("You entered invalid space for health status! \nIt should be 0 or above!");
			}
			catch(Exception e) {
				throw new Exception("You entered invalid space for health status! \nIt should be 0 or above!");
			}
			
			if((nameOfHealthStatus == null) || (nameOfHealthStatus.length() < 1))
					throw new Exception("You entered invalid name for health status! \nIt should contain at least one char!");
			int searchHealthStatusId = DataBaseCommandsForHealthStatus.searchHealthStatusIdByHealthStatusNameInDataBase(nameOfHealthStatus);
			if(searchHealthStatusId != DataBaseCommands.HEALTH_STATUS_ID_NOT_FOUND_IN_DATA_BASE)
				throw new Exception("Health status is already exist!");
			
			if(!DataBaseCommandsForHealthStatus.addHealthStatusToDataBase(nameOfHealthStatus, spaceOfHealthStatus))
				throw new Exception("Failed to insert to database!");
			
			Text succeedText = new Text("Add health status successfully!");
			succeedText.setId("succeedText");
			
			view.showInCenter(succeedText);
		}
		catch(Exception e) {
			Text failText = new Text("Add health status failed! \n" + e.getMessage());
			failText.setId("failedText");
			
			view.showInCenter(failText);
		}
	}
	
	// Try init data base
	private void tryInitDataBase() {		
		try {
			DataBaseCommands.initDataBase();
			connectedUser = null;
			view.showInTop(new Text(""));
			Text succeedText = new Text("Init data base successfully! \nPlease login again!");
			succeedText.setId("succeedText");
			
			view.showInCenter(succeedText);
		}
		catch(Exception e) {
			Text failText = new Text("Init data base failed! \n" + e.getMessage());
			failText.setId("failedText");
			
			view.showInCenter(failText);
		}
		
		showNewMenu();
	}
	
	// Try logout
	private void tryLogout() {		
		try {
			if(connectedUser == null)
				throw new Exception("You are not connected!");
			String nameOfUserToLogout = connectedUser.getUserName();
			connectedUser = null;
			
			view.showInTop(new Text(""));
			
			Text succeedText = new Text("Logout successfully! \nBye bye " + nameOfUserToLogout + "!");
			succeedText.setId("succeedText");
			
			view.showInCenter(succeedText);
		}
		catch(Exception e) {
			Text failText = new Text("Logout failed! \n" + e.getMessage());
			failText.setId("failedText");
			
			view.showInCenter(failText);
		}
		
		showNewMenu();
	}
	
	// Set up event handlers
	private void setUpEventHandlers() {
		
		// Click on login in the menu
		view.getLoginButtonToMenu().setOnAction(new EventHandler<ActionEvent>() { 
			@Override
			public void handle(ActionEvent event) {
				showLoginForm();
			}
		});
		
		// Click on login button
		view.getLoginButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tryToLogin();
			}
		});
		
		// Click on register in the menu
		view.getRegisterButtonToMenu().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showRegisterForm();
			}
		});
		
		// Click on register button
		view.getRegisterButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tryToRegister();
			}
		});
		
		// Click on create event in the menu
		view.getCreateEventButtonToMenu().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showCreateEventForm();
			}
		});
		
		// Click on continue to seats costs button in create event form
		view.getContinueToSetSeatsCostsButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSetSeatsCostsForm();
			}
		});
		
		// Click on edit event date in menu
		view.getEditEventDateButtonToMenu().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSelectEventToEditDateForm();
			}
		});
		
		// Click on select event to edit button in select event to edit date form
		view.getSelectEventToEditDateButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showEditEventDateForm();
			}
		});
		
		// Click on edit event date button
		view.getEditEventDateButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tryEditEventDate();
			}
		});
		
		// Click on cancel event in menu
		view.getCancelEventButtonToMenu().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showCancelEventForm();
			}
		});
		
		// Click on cancel event button
		view.getCancelEventButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tryCancelEvent();
			}
		});
		
		// Click on make invitation to event in menu
		view.getMakeInvitationToEventButtonToMenu().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSelectEventToMakeInvitationToForm();
			}
		});
		
		// Click on select event in select event to make invitation to form
		view.getSelectEventToMakeInvitationToButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showMakeInvitationToEventForm();
			}
		});
		
		// Click on show offered invitation in make invitation to event form
		view.getShowOfferSeatsInvitationButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showOfferSeatsInvitationToEventForm();
			}
		});
		
		// Click on show my invitations in menu
		view.getShowMyInvitationsButtonToMenu().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showMyInvitations();
			}
		});
		
		// Click on change users permissions in menu
		view.getChangeUsersPermissionsButtonToMenu().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showChangeUserPermissionsForm();
			}
		});
		
		// Click on change user permissions in change user permissions form
		view.getChangeUserPermissionsButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tryChangeUsersPermissions();
			}
		});
		
		// Click on change my information in menu
		view.getChangeMyInformationButtonToMenu().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showChangeMyInformationForm();
			}
		});
		
		// Click on change my information in change my information form
		view.getChangeMyInformationButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tryChangeMyInformation();
			}
		});
		
		// Click on show event seats in menu
		view.getShowEventSeatsButtonToMenu().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSelectEventToShowForm();
			}
		});
		
		// Click on show event seats in select event to show form
		view.getShowEventSeatsButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tryShowEventSeats();
			}
		});
		
		// Click on add health status in menu
		view.getAddHealthStatusButtonToMenu().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showAddHealthStatusForm();
			}
		});
		
		// Click on add health status in add health status form
		view.getAddHealthStatusButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tryAddHealthStatus();
			}
		});
		
		// Click on init data base in menu
		view.getInitDataBaseButtonToMenu().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tryInitDataBase();
			}
		});
		
		// Click on logout in menu
		view.getLogoutButtonToMenu().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tryLogout();
			}
		});
	}
}