package AmitCohen.Controller;

import AmitCohen.MySqlCommands;
import AmitCohen.Model.SeriesOfGames;
import AmitCohen.Model.VideoGame;
import AmitCohen.Model.checkValidInputs;
import AmitCohen.View.View;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {
	protected View view;
	protected String connectedUser, oldEmail = "";
	//Constructor
	public Controller(Stage stage) {
		this.view = new View(stage);
		view.setGuestMode();
		
	//Events handlers
		
		//Menu
		//EventHandler for click on register in menu
		EventHandler<ActionEvent> clickOnRegisterInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//Make the new buttons and details to show
				GridPane newDetailsToShow = new GridPane();
				newDetailsToShow.setHgap(10);
				newDetailsToShow.setVgap(10);
				newDetailsToShow.add(new Text("First name: "), 1, 0);
				view.clearTextFieldOfFirstName();
				newDetailsToShow.add(view.getTextFieldOfFirstName(), 2, 0);
				newDetailsToShow.add(new Text("Last name: "), 1, 1);
				view.clearTextFieldOfLastName();
				newDetailsToShow.add(view.getTextFieldOfLastName(), 2, 1);
				newDetailsToShow.add(new Text("Gender: "), 1, 2);
				view.resetGenderOptions();
				newDetailsToShow.add(view.getRadioButtonOfMaleOption(), 2, 2);
				newDetailsToShow.add(view.getRadioButtonOfFemaleOption(), 3, 2);
				newDetailsToShow.add(new Text("Email: "), 1, 3);
				view.clearTextFieldOfEmail();
				newDetailsToShow.add(view.getTextFieldOfEmail(), 2, 3);
				newDetailsToShow.add(new Text("User ID: "), 1, 4);
				view.clearTextFieldOfUserID();
				newDetailsToShow.add(view.getTextFieldOfUserID(), 2, 4);
				newDetailsToShow.add(new Text("Password: "), 1, 5);
				view.clearTextFieldOfPassword();
				newDetailsToShow.add(view.getTextFieldOfPassword(), 2, 5);
				newDetailsToShow.add(view.getButtonOfRegister(), 1, 6);
				newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
				view.showInCenter(newDetailsToShow);
				view.showNothingInBottom();
				view.showNothingInRight();
			}
		};
		view.clickOnRegisterInMenu(clickOnRegisterInMenu);
		
		//EventHandler for click on login in menu
		EventHandler<ActionEvent> clickOnLoginInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//Make the new buttons and details to show
				GridPane newDetailsToShow = new GridPane();
				newDetailsToShow.setHgap(10);
				newDetailsToShow.setVgap(10);
				newDetailsToShow.add(new Text("User ID: "), 1, 0);
				view.clearTextFieldOfUserID();
				newDetailsToShow.add(view.getTextFieldOfUserID(), 2, 0);
				newDetailsToShow.add(new Text("Password: "), 1, 1);
				view.clearTextFieldOfPassword();
				newDetailsToShow.add(view.getTextFieldOfPassword(), 2, 1);
				newDetailsToShow.add(view.getButtonOfLogin(), 1, 2);
				newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
				view.showInCenter(newDetailsToShow);
				view.showNothingInBottom();
				view.showNothingInRight();
			}
		};
		view.clickOnLoginInMenu(clickOnLoginInMenu);
		
		//EventHandler for click on search game in menu
		EventHandler<ActionEvent> clickOnSerchGameInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.getSerchButtonInMenu().setSelected(true);
				//Make the new buttons and details to show
				GridPane newDetailsToShow = new GridPane();
				newDetailsToShow.setHgap(10);
				newDetailsToShow.setVgap(10);
				view.clearCheckBoxOfGameName();
				newDetailsToShow.add(view.getCheckBoxOfGameName(), 0, 1);
				newDetailsToShow.add(new Text("Game name"), 1, 1);
				view.clearTextFieldOfGameName();
				newDetailsToShow.add(view.getTextFieldOfGameName(), 2, 1);
				view.getTextFieldOfGameName().setVisible(false);
				view.clearCheckBoxOfDistributionCompany();
				newDetailsToShow.add(view.getCheckBoxOfDistributionCompany(), 0, 2);
				newDetailsToShow.add(new Text("Distribution company"), 1, 2);
				view.clearTextFieldOfDistributionCompany();
				newDetailsToShow.add(view.getTextFieldOfDistributionCompany(), 2, 2);
				view.getTextFieldOfDistributionCompany().setVisible(false);
				view.clearCheckBoxOfCreativeCompany();
				newDetailsToShow.add(view.getCheckBoxOfCreativeCompany(), 0, 3);
				newDetailsToShow.add(new Text("Creative company"), 1, 3);
				view.clearTextFieldOfCreativeCompany();
				newDetailsToShow.add(view.getTextFieldOfCreativeCompany(), 2, 3);
				view.getTextFieldOfCreativeCompany().setVisible(false);
				view.clearCheckBoxOfGenre();
				newDetailsToShow.add(view.getCheckBoxOfGenre(), 0, 4);
				newDetailsToShow.add(new Text("Genre"), 1, 4);
				newDetailsToShow.add(view.getComboBoxOfGenresFromBeginning(), 2, 4);
				view.getComboBoxOfGenresFromBeginning().setVisible(false);
				view.clearCheckBoxOfManufactureYear();
				newDetailsToShow.add(view.getCheckBoxOfManufactureYear(), 0, 5);
				newDetailsToShow.add(new Text("Manufacture year"), 1, 5);
				view.clearTextFieldOfStartManufactureYear();
				newDetailsToShow.add(view.getTextFieldOfStartManufactureYear(), 2, 5);
				view.getTextFieldOfStartManufactureYear().setVisible(false);
				newDetailsToShow.add(view.getTextMakafToManufactureYear(), 3, 5);
				view.getTextMakafToManufactureYear().setVisible(false);
				view.clearTextFieldOfEndManufactureYear();
				newDetailsToShow.add(view.getTextFieldOfEndManufactureYear(), 4, 5);
				view.getTextFieldOfEndManufactureYear().setVisible(false);
				view.clearCheckBoxOfPrice();
				newDetailsToShow.add(view.getCheckBoxOfPrice(), 0, 6);
				newDetailsToShow.add(new Text("Price"), 1, 6);
				view.clearTextFieldOfStartPrice();
				newDetailsToShow.add(view.getTextFieldOfStartPrice(), 2, 6);
				view.getTextFieldOfStartPrice().setVisible(false);
				newDetailsToShow.add(view.getTextMakafToPrice(), 3, 6);
				view.getTextMakafToPrice().setVisible(false);
				view.clearTextFieldOfEndPrice();
				newDetailsToShow.add(view.getTextFieldOfEndPrice(), 4, 6);
				view.getTextFieldOfEndPrice().setVisible(false);
				newDetailsToShow.add(view.getSearchButton(), 0, 7);
				newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
				VBox newDetailsToShow2 = new VBox();
				newDetailsToShow2.getChildren().addAll(view.getTextToSearch(), newDetailsToShow);
				newDetailsToShow2.setAlignment(Pos.CENTER_LEFT);
				view.showInCenter(newDetailsToShow2);
				view.showNothingInBottom();
				view.showNothingInRight();
			}
		};
		view.clickOnSearchGameInMenu(clickOnSerchGameInMenu);
		
		//EventHandler for click on add game in menu
		EventHandler<ActionEvent> clickOnAddGameInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//Make the new buttons and details to show
				GridPane newDetailsToShow = new GridPane();
				newDetailsToShow.setHgap(10);
				newDetailsToShow.setVgap(10);
				newDetailsToShow.add(new Text("Game name: "), 0, 0);
				view.clearTextFieldOfGameName();
				newDetailsToShow.add(view.getTextFieldOfGameName(), 1, 0);
				view.getTextFieldOfGameName().setVisible(true);
				newDetailsToShow.add(new Text("At least one char"), 2, 0);
				newDetailsToShow.add(new Text("Distribution date: "), 0, 1);
				view.clearTextFieldOfDistributionDate();
				newDetailsToShow.add(view.getTextFieldOfDistributionDate(), 1, 1);
				view.getTextFieldOfDistributionDate().setVisible(true);
				newDetailsToShow.add(new Text("dd/mm/yyyy"), 2, 1);
				newDetailsToShow.add(new Text("Distribution company: "), 0, 2);
				view.clearTextFieldOfDistributionCompany();
				newDetailsToShow.add(view.getTextFieldOfDistributionCompany(), 1, 2);
				view.getTextFieldOfDistributionCompany().setVisible(true);
				newDetailsToShow.add(new Text("At least one char"), 2, 2);
				newDetailsToShow.add(new Text("Creative company: "), 0, 3);
				view.clearTextFieldOfCreativeCompany();
				newDetailsToShow.add(view.getTextFieldOfCreativeCompany(), 1, 3);
				view.getTextFieldOfCreativeCompany().setVisible(true);
				newDetailsToShow.add(new Text("At least one char"), 2, 3);
				newDetailsToShow.add(new Text("Trailer: "), 0, 4);
				view.clearTextFieldOfTrailer();
				newDetailsToShow.add(view.getTextFieldOfTrailer(), 1, 4);
				view.getTextFieldOfTrailer().setVisible(true);
				newDetailsToShow.add(new Text("Description: "), 0, 5);
				view.clearTextFieldOfDescription();
				newDetailsToShow.add(view.getTextFieldOfDescription(), 1, 5);
				view.getTextFieldOfDescription().setVisible(true);
				newDetailsToShow.add(new Text("Genre: "), 0, 6);
				newDetailsToShow.add(view.getComboBoxOfGenresFromBeginning(), 1, 6);
				view.getComboBoxOfGenresFromBeginning().setVisible(true);
				newDetailsToShow.add(new Text("Must select option"), 2, 6);
				newDetailsToShow.add(new Text("Manufacture year: "), 0, 7);
				view.clearTextFieldOfManufactureYear();
				newDetailsToShow.add(view.getTextFieldOfManufactureYear(), 1, 7);
				view.getTextFieldOfManufactureYear().setVisible(true);
				newDetailsToShow.add(new Text("Must fill in number"), 2, 7);
				newDetailsToShow.add(new Text("Part of series: "), 0, 8);
				view.clearCheckBoxOfPartOfSeries();
				newDetailsToShow.add(view.getCheckBoxOfPartOfSeries(), 1, 8);
				view.getCheckBoxOfPartOfSeries().setVisible(true);
				newDetailsToShow.add(view.getTextOfNameOfSeries(), 2, 8);
				view.getTextOfNameOfSeries().setVisible(false);
				newDetailsToShow.add(view.getTextFieldOfSeriesName(), 3, 8);
				view.getTextFieldOfSeriesName().setVisible(false);
				newDetailsToShow.add(view.getTextReqCharsForSeriesName(), 4, 8);
				view.getTextReqCharsForSeriesName().setVisible(false);
				newDetailsToShow.add(new Text("Price"), 0, 9);
				view.clearTextFieldOfPrice();
				newDetailsToShow.add(view.getTextFieldOfPrice(), 1, 9);
				view.getTextFieldOfPrice().setVisible(true);
				newDetailsToShow.add(new Text("Must fill with positive number"), 2, 9);
				newDetailsToShow.add(view.getAddButton(), 0, 10);
				newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
				view.showInCenter(newDetailsToShow);
				view.showNothingInBottom();
				view.showNothingInRight();
			}
		};
		view.clickOnAddGameInMenu(clickOnAddGameInMenu);
		
		//EventHandler for click on update game in menu
		EventHandler<ActionEvent> clickOnUpdateGameInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					GridPane newDetailsToShow = new GridPane();
					newDetailsToShow.setHgap(10);
					newDetailsToShow.setVgap(10);
					newDetailsToShow.add(new Text("Select game to update: "), 0, 0);
					newDetailsToShow.add(view.getComboBoxOfNamesOfAllGamesFromBegining(), 1, 0);
					newDetailsToShow.add(view.getSelectGameButton(), 2, 0);
					newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
					view.showInCenter(newDetailsToShow);
					view.showNothingInBottom();
					view.showNothingInRight();
				}
				catch(Exception e) {
					Text errorText = new Text(e.getMessage());
					errorText.setFill(Color.RED);
					view.showInBottom(errorText);
				}
			}
		};
		view.clickOnUpdateGameInMenu(clickOnUpdateGameInMenu);
		
		//EventHandler for click on update personal info in menu
		EventHandler<ActionEvent> clickOnUpdatePersonalInfoInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					String firstName = "";
					String lastName = "";
					String gender = "";
					String email = "";
					String password = "";
					int user_info_id = -1;
					Connection conn = null;
					conn = MySqlCommands.makeConnectionToMySQL();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM users");
					while(rs.next()) {
						if(rs.getString("user_id").equals(connectedUser)) {
							password = rs.getString("user_password");
							user_info_id = rs.getInt("user_info_id");
						}
					}
					conn.close();
					conn = null;
					rs.close();
					rs = null;
					stmt.close();
					stmt = null;
					conn = MySqlCommands.makeConnectionToMySQL();
					stmt = conn.createStatement();
					rs = stmt.executeQuery("SELECT * FROM users_info WHERE user_info_id = " + user_info_id);
					while(rs.next()) {
						firstName = rs.getString("user_info_first_name");
						lastName = rs.getString("user_info_last_name");
						gender = rs.getString("user_info_gender");
						email = rs.getString("user_info_email");
					}
					conn.close();
					conn = null;
					rs.close();
					rs = null;
					stmt.close();
					stmt = null;
					//Make the new buttons and details to show
					GridPane newDetailsToShow = new GridPane();
					newDetailsToShow.setHgap(10);
					newDetailsToShow.setVgap(10);
					newDetailsToShow.add(new Text("First name: "), 1, 0);
					view.clearTextFieldOfFirstName();
					view.getTextFieldOfFirstName().setText(firstName);
					newDetailsToShow.add(view.getTextFieldOfFirstName(), 2, 0);
					newDetailsToShow.add(new Text("Last name: "), 1, 1);
					view.clearTextFieldOfLastName();
					view.getTextFieldOfLastName().setText(lastName);
					newDetailsToShow.add(view.getTextFieldOfLastName(), 2, 1);
					newDetailsToShow.add(new Text("Gender: "), 1, 2);
					view.resetGenderOptions();
					if(gender.equals("Male"))
						view.getRadioButtonOfMaleOption().setSelected(true);
					else
						view.getRadioButtonOfFemaleOption().setSelected(true);
					newDetailsToShow.add(view.getRadioButtonOfMaleOption(), 2, 2);
					newDetailsToShow.add(view.getRadioButtonOfFemaleOption(), 3, 2);
					newDetailsToShow.add(new Text("Email: "), 1, 3);
					view.clearTextFieldOfEmail();
					view.getTextFieldOfEmail().setText(email);
					newDetailsToShow.add(view.getTextFieldOfEmail(), 2, 3);
					newDetailsToShow.add(new Text("User ID: "), 1, 4);
					view.clearTextFieldOfUserID();
					view.getTextFieldOfUserID().setText(connectedUser);
					newDetailsToShow.add(view.getTextFieldOfUserID(), 2, 4);
					newDetailsToShow.add(new Text("Password: "), 1, 5);
					view.clearTextFieldOfPassword();
					view.getTextFieldOfPassword().setText(password);
					newDetailsToShow.add(view.getTextFieldOfPassword(), 2, 5);
					newDetailsToShow.add(view.getSavePersonalInfoButton(), 1, 6);
					newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
					oldEmail = email;
					view.showInCenter(newDetailsToShow);
					view.showNothingInBottom();
					view.showNothingInRight();
				}
				catch(Exception e) {
					view.showInCenter(new Text(e.getMessage()));
				}
			}
		};
		view.clickOnUpdatePersonalInfoInMenu(clickOnUpdatePersonalInfoInMenu);
		
		//EventHandler for click on logout in menu
		EventHandler<ActionEvent> clickOnLogoutInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//Make the new buttons and details to show
				GridPane newDetailsToShow = new GridPane();
				newDetailsToShow.setHgap(10);
				newDetailsToShow.setVgap(10);
				newDetailsToShow.add(view.getYesButton(), 0, 1);
				newDetailsToShow.add(view.getNoButton(), 1, 1);
				newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
				VBox newDetailsToShow2 = new VBox();
				newDetailsToShow2.getChildren().addAll(view.getTextToLogout(), newDetailsToShow);
				newDetailsToShow2.setAlignment(Pos.CENTER_LEFT);
				view.showInCenter(newDetailsToShow2);
				view.showNothingInBottom();
				view.showNothingInRight();
			}
		};
		view.clickOnLogoutInMenu(clickOnLogoutInMenu);
		
		//Register
		//EventHandler for click on register in register window
		EventHandler<ActionEvent> clickOnRegisterInRegisterWindow = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					//Check valids
					checkValidInputs.getSingleCheckValidInputsObject().checkFirstNameInput(view.getTextFieldOfFirstName().getText());
					checkValidInputs.getSingleCheckValidInputsObject().checkLastNameInput(view.getTextFieldOfLastName().getText());
					checkValidInputs.getSingleCheckValidInputsObject().checkGenderInput(view.getGenderOptions());
					checkValidInputs.getSingleCheckValidInputsObject().checkEmailInput(view.getTextFieldOfEmail().getText());
					checkValidInputs.getSingleCheckValidInputsObject().checkUserNameInput(view.getTextFieldOfUserID().getText());
					checkValidInputs.getSingleCheckValidInputsObject().checkPasswordInput(view.getTextFieldOfPassword().getText());
					//Insert to data base of users info
					Connection conn = null;
					conn = MySqlCommands.makeConnectionToMySQL();
					Statement stmt = conn.createStatement();
					String s = "Insert INTO users_info (user_info_first_name, user_info_last_name, user_info_email, user_info_gender) "
							+ "VALUES ('" + view.getTextFieldOfFirstName().getText() + "', '" + view.getTextFieldOfLastName().getText() + "', '" + view.getTextFieldOfEmail().getText() + "', 'Female')";
					if(view.getRadioButtonOfMaleOption().isSelected()) {
						s = "Insert INTO users_info (user_info_first_name, user_info_last_name, user_info_email, user_info_gender) "
								+ "VALUES ('" + view.getTextFieldOfFirstName().getText() + "', '" + view.getTextFieldOfLastName().getText() + "', '" + view.getTextFieldOfEmail().getText() + "', 'Male')";
					}
					stmt.executeUpdate(s);
					conn.close();
					conn = null;
					stmt.close();
					stmt = null;
					//Insert to data base of users
					conn = MySqlCommands.makeConnectionToMySQL();
					stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM users_info WHERE user_info_email = '" + view.getTextFieldOfEmail().getText() +"'");
					int numOfUserId = -1;
					while(rs.next()) {
						numOfUserId = rs.getInt("user_info_id");
					}
					conn.close();
					conn = null;
					rs.close();
					rs = null;
					stmt.close();
					stmt = null;
					conn = MySqlCommands.makeConnectionToMySQL();
					stmt = conn.createStatement();
					s = "Insert INTO users (user_id, user_password, user_info_id) "
							+ "VALUES ('" + view.getTextFieldOfUserID().getText() + "', '" + view.getTextFieldOfPassword().getText() + "', " + numOfUserId + ")";
					stmt.executeUpdate(s);
					conn.close();
					conn = null;
					stmt.close();
					stmt = null;
					view.setConnectedMode(view.getTextFieldOfFirstName().getText());
					view.showInCenter(new Text("You registered successfully"));
					view.showNothingInBottom();
					connectedUser = view.getTextFieldOfUserID().getText();
				}
				catch(Exception e) {
					Text errorText = new Text(e.getMessage());
					errorText.setFill(Color.RED);
					view.showInBottom(errorText);
				}
			}
		};
		view.clickOnRegisterInRegisterWindow(clickOnRegisterInRegisterWindow);
		
		//Login
		//EventHandler for click on login in login window
		EventHandler<ActionEvent> clickOnLoginInLoginMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					int userInfoId = checkValidInputs.getSingleCheckValidInputsObject().checkLoginInput(view.getTextFieldOfUserID().getText()
							, view.getTextFieldOfPassword().getText());
					Connection conn = null;
					conn = MySqlCommands.makeConnectionToMySQL();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM users_info WHERE user_info_id = " + userInfoId);
					while(rs.next()) {
						view.setConnectedMode(rs.getString("user_info_first_name"));
					}
					view.showNothingInBottom();
					connectedUser = view.getTextFieldOfUserID().getText();
					conn.close();
					conn = null;
					rs.close();
					rs = null;
					stmt.close();
					stmt = null;
					view.showInCenter(new Text("You've logged in successfully"));
					view.showNothingInBottom();
				}
				catch(Exception e) {
					Text errorText = new Text(e.getMessage());
					errorText.setFill(Color.RED);
					view.showInBottom(errorText);
				}
			}
		};
		view.clickOnLoginInLoginWindow(clickOnLoginInLoginMenu);
		
		//Search
		//EventHandler for click on game name in search
		EventHandler<ActionEvent> clickOnGameNameInSearch = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.getTextFieldOfGameName().setText("");
				if(view.getCheckBoxOfGameName().isSelected())
					view.getTextFieldOfGameName().setVisible(true);
				else
					view.getTextFieldOfGameName().setVisible(false);
			}
		};
		view.clickOnGameNameInSearch(clickOnGameNameInSearch);
		
		//EventHandler for click on distribution company in search
		EventHandler<ActionEvent> clickOnDistributionCompanyInSearch = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.getTextFieldOfDistributionCompany().setText("");
				if(view.getCheckBoxOfDistributionCompany().isSelected())
					view.getTextFieldOfDistributionCompany().setVisible(true);
				else
					view.getTextFieldOfDistributionCompany().setVisible(false);
			}
		};
		view.clickOnDistributionCompanyInSearch(clickOnDistributionCompanyInSearch);
		
		//EventHandler for click on creative company in search
		EventHandler<ActionEvent> clickOnCreativeCompanyInSearch = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.getCheckBoxOfCreativeCompany().setText("");
				if(view.getCheckBoxOfCreativeCompany().isSelected())
					view.getTextFieldOfCreativeCompany().setVisible(true);
				else
					view.getTextFieldOfCreativeCompany().setVisible(false);
			}
		};
		view.clickOnCreativeCompanyInSearch(clickOnCreativeCompanyInSearch);
		
		//EventHandler for click on genre in search
		EventHandler<ActionEvent> clickOnGenreInSearch = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(view.getCheckBoxOfGenre().isSelected())
					view.getComboBoxOfGenresFromBeginning().setVisible(true);
				else
					view.getComboBoxOfGenresFromBeginning().setVisible(false);
			}
		};
		view.clickOnGenreInSearch(clickOnGenreInSearch);
		
		//EventHandler for click on manufacture year in search
		EventHandler<ActionEvent> clickOnManufactureYearInSearch = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.getTextFieldOfStartManufactureYear().setText("");
				view.getTextFieldOfEndManufactureYear().setText("");
				if(view.getCheckBoxOfManufactureYear().isSelected()) {
					view.getTextFieldOfStartManufactureYear().setVisible(true);
					view.getTextMakafToManufactureYear().setVisible(true);
					view.getTextFieldOfEndManufactureYear().setVisible(true);
				}
				else {
					view.getTextFieldOfStartManufactureYear().setVisible(false);
					view.getTextMakafToManufactureYear().setVisible(false);
					view.getTextFieldOfEndManufactureYear().setVisible(false);
				}
			}
		};
		view.clickOnManufactureYearInSearch(clickOnManufactureYearInSearch);
		
		//EventHandler for click on price in search
		EventHandler<ActionEvent> clickOnPriceInSearch = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.getTextFieldOfStartPrice().setText("");
				view.getTextFieldOfEndPrice().setText("");
				if(view.getCheckBoxOfPrice().isSelected()) {
					view.getTextFieldOfStartPrice().setVisible(true);
					view.getTextMakafToPrice().setVisible(true);
					view.getTextFieldOfEndPrice().setVisible(true);
				}
				else {
					view.getTextFieldOfStartPrice().setVisible(false);
					view.getTextMakafToPrice().setVisible(false);
					view.getTextFieldOfEndPrice().setVisible(false);
				}
			}
		};
		view.clickOnPriceInSearch(clickOnPriceInSearch);
		
		//EventHandler for click on search in search window
		EventHandler<ActionEvent> clickOnSearchInSearchWindow = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;
				String stringFilter = "Select * FROM game WHERE (1=1)";
				try {
					if(view.getCheckBoxOfGameName().isSelected())
						stringFilter = stringFilter + " AND (game_name LIKE '" + view.getTextFieldOfGameName().getText() + "')";
					if(view.getCheckBoxOfDistributionCompany().isSelected())
						stringFilter = stringFilter + " AND (game_distribution_company LIKE '" + view.getTextFieldOfDistributionCompany().getText() + "')";
					if(view.getCheckBoxOfCreativeCompany().isSelected())
						stringFilter = stringFilter + " AND (game_creative_company LIKE '" + view.getTextFieldOfCreativeCompany().getText() + "')";
					if(view.getCheckBoxOfGenre().isSelected()) {
						int gameGenre = -1;
						if((view.getComboBoxOfGenres().getValue() == null) || (view.getComboBoxOfGenres().getValue().equals(""))) //Not select genre
							throw new Exception("You must select genre or not filter according to genre");
						conn = MySqlCommands.makeConnectionToMySQL();
						stmt = conn.createStatement();
						rs = stmt.executeQuery("SELECT * FROM genre WHERE genre_name LIKE '" + view.getComboBoxOfGenres().getValue() + "'");
						while(rs.next())
							gameGenre = rs.getInt("genre_id");
						conn.close();
						conn = null;
						rs.close();
						rs = null;
						stmt.close();
						stmt = null;
						stringFilter = stringFilter + " AND (game_genre_id = " + gameGenre + ")";
					}
					if(view.getCheckBoxOfManufactureYear().isSelected()) {
						checkValidInputs.getSingleCheckValidInputsObject().checkYearsAreFilledProperly(view.getTextFieldOfStartManufactureYear(), view.getTextFieldOfEndManufactureYear());
						stringFilter = stringFilter + " AND (game_manufacture_year >= " + view.getTextFieldOfStartManufactureYear().getText() + ")";
						stringFilter = stringFilter + " AND (game_manufacture_year <= " + view.getTextFieldOfEndManufactureYear().getText() + ")";
					}
					if(view.getCheckBoxOfPrice().isSelected()) {
						checkValidInputs.getSingleCheckValidInputsObject().checkPriceAreFilledProperly(view.getTextFieldOfStartPrice(), view.getTextFieldOfEndPrice());
						stringFilter = stringFilter + " AND (game_price >= " + view.getTextFieldOfStartPrice().getText() + ")";
						stringFilter = stringFilter + " AND (game_price <= " + view.getTextFieldOfEndPrice().getText() + ")";
					}
					conn = MySqlCommands.makeConnectionToMySQL();
					stmt = conn.createStatement();
					rs = stmt.executeQuery(stringFilter);
					int foundGames = 0;
					GridPane newDetailsToShow = new GridPane();
					newDetailsToShow.add(new Text("Game name"), 1, 0);
					newDetailsToShow.add(new Text("Distribution date"), 2, 0);
					newDetailsToShow.add(new Text("Distribution company"), 3, 0);
					newDetailsToShow.add(new Text("Creative company"), 4, 0);
					newDetailsToShow.add(new Text("Trailer"), 5, 0);
					newDetailsToShow.add(new Text("Description"), 6, 0);
					newDetailsToShow.add(new Text("Genre"), 7, 0);
					newDetailsToShow.add(new Text("Manufacture year"), 8, 0);
					newDetailsToShow.add(new Text("Series"), 9, 0);
					newDetailsToShow.add(new Text("Price"), 10, 0);
					String genreName = "";
					while(rs.next()) {
						foundGames++;
						newDetailsToShow.add(new Text(rs.getString("game_name")), 1, foundGames);
						newDetailsToShow.add(new Text(rs.getString("game_distribution_date")), 2, foundGames);
						newDetailsToShow.add(new Text(rs.getString("game_distribution_company")), 3, foundGames);
						newDetailsToShow.add(new Text(rs.getString("game_creative_company")), 4, foundGames);
						newDetailsToShow.add(new Text(rs.getString("game_trailer")), 5, foundGames);
						newDetailsToShow.add(new Text(rs.getString("game_description")), 6, foundGames);
						genreName = "";
						int numOfSeriesId = -1;
						switch(rs.getInt("game_genre_id")) {
						case 1:
							genreName = "Action";
							break;
						case 2:
							genreName = "Adventure";
							break;
						case 3:
							genreName = "Driving";
							break;
						case 4:
							genreName = "Roles";
							break;
						case 5:
							genreName = "Simulation";
							break;
						case 6:
							genreName = "Sports";
							break;
						case 7:
							genreName = "Strategy";
							break;
						}
						newDetailsToShow.add(new Text(genreName), 7, foundGames);
						newDetailsToShow.add(new Text(String.valueOf(rs.getInt("game_manufacture_year"))), 8, foundGames);
						numOfSeriesId = rs.getInt("game_series_id");
						Connection conn2 = null;
						conn2 = MySqlCommands.makeConnectionToMySQL();
						Statement stmt2 = conn2.createStatement();
						ResultSet rs2 = stmt2.executeQuery("SELECT * FROM series WHERE series_id = " + numOfSeriesId);
						while(rs2.next()) {
							newDetailsToShow.add(new Text(rs2.getString("series_name")), 9, foundGames);
						}
						conn2.close();
						conn2 = null;
						stmt2.close();
						stmt2 = null;
						rs2.close();
						rs2 = null;
						newDetailsToShow.add(new Text(String.valueOf(rs.getInt("game_price"))), 10, foundGames);
					}
					conn.close();
					conn = null;
					rs.close();
					rs = null;
					stmt.close();
					stmt = null;
					newDetailsToShow.setGridLinesVisible(true);
					if(foundGames == 0) {
						Text noGames = new Text("No games found");
						noGames.setFill(Color.RED);
						newDetailsToShow = new GridPane();
						newDetailsToShow.setHgap(10);
						newDetailsToShow.setVgap(10);
						newDetailsToShow.add(noGames, 0, 0);
					}
					newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
					view.showInRight(newDetailsToShow);
					view.showNothingInBottom();
				}
				catch(Exception e) {
					view.showNothingInRight();
					Text errorText = new Text(e.getMessage());
					errorText.setFill(Color.RED);
					view.showInBottom(errorText);
				}
			}
		};
		view.clickOnSearchInSearchWindow(clickOnSearchInSearchWindow);
		
		//Add
		//EventHandler for click on part of series in add window
		EventHandler<ActionEvent> clickOnPartOfSeriesInAddWindow = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.getTextFieldOfSeriesName().setText("");
				if(view.getCheckBoxOfPartOfSeries().isSelected()) {
					view.getTextFieldOfSeriesName().setVisible(true);
					view.getTextOfNameOfSeries().setVisible(true);
					view.getTextReqCharsForSeriesName().setVisible(true);
				}
				else {
					view.getTextFieldOfSeriesName().setVisible(false);
					view.getTextOfNameOfSeries().setVisible(false);
					view.getTextReqCharsForSeriesName().setVisible(false);
				}
			}
		};
		view.clickOnPartOfSeriesInAddWindow(clickOnPartOfSeriesInAddWindow);
		
		//EventHandler for click on add in add window
		EventHandler<ActionEvent> clickOnAddInAddWindow = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;
				String insertGameSentence = "";
				try {
					String videoGameName = view.getTextFieldOfGameName().getText();
					String videoGameDistributionDate = view.getTextFieldOfDistributionDate().getText();
					String videoGameDistributionCompanyName = view.getTextFieldOfDistributionCompany().getText();
					String videoGameCreativeCompanyName = view.getTextFieldOfCreativeCompany().getText();
					String videoGameTrailerLink = view.getTextFieldOfTrailer().getText();
					String videoGameDescription = view.getTextFieldOfDescription().getText();
					String videoGameGenreName = view.getComboBoxOfGenres().getValue();
					int videoGameGenreID = -1;
					int videoGameManufactureYear;
					String videoGameSeriesOfGamesName = view.getTextFieldOfSeriesName().getText();
					int videoGameSeriesOfGamesNameID = -1;
					SeriesOfGames videoGameSeriesOfGames = null;
					int videoGamePrice;
					//Check the genre is selected
					checkValidInputs.getSingleCheckValidInputsObject().checkGenreInput(view.getComboBoxOfGenres());
					//Check the manufacture year is only numbers 
					checkValidInputs.getSingleCheckValidInputsObject().checkIfManufactureYearIsOnlyNums(view.getTextFieldOfManufactureYear().getText());
					videoGameManufactureYear = Integer.parseInt(view.getTextFieldOfManufactureYear().getText());
					//Check the price is only numbers
					checkValidInputs.getSingleCheckValidInputsObject().checkIfPriceIsOnlyNums(view.getTextFieldOfPrice().getText());
					videoGamePrice = Integer.parseInt(view.getTextFieldOfPrice().getText());
					if(view.getCheckBoxOfPartOfSeries().isSelected()) { //Part of series
						videoGameSeriesOfGames = new SeriesOfGames(videoGameSeriesOfGamesName);
						//Get the series of games id
						conn = MySqlCommands.makeConnectionToMySQL();
						stmt = conn.createStatement();
						rs = stmt.executeQuery("SELECT * FROM series WHERE series_name LIKE '" + videoGameSeriesOfGamesName + "'");
						while(rs.next())
							videoGameSeriesOfGamesNameID = rs.getInt("series_id");
						conn.close();
						conn = null;
						rs.close();
						rs = null;
						stmt.close();
						stmt = null;
					}
					VideoGame videoGameToAdd = new VideoGame(videoGameName, videoGameDistributionDate, videoGameDistributionCompanyName
							, videoGameCreativeCompanyName, videoGameTrailerLink, videoGameDescription, videoGameGenreName, videoGameManufactureYear
							, videoGameSeriesOfGames, videoGamePrice); //Make the new video game
					//Get genre name id
					conn = MySqlCommands.makeConnectionToMySQL();
					stmt = conn.createStatement();
					rs = stmt.executeQuery("SELECT * FROM genre WHERE genre_name LIKE '" + videoGameGenreName + "'");
					while(rs.next())
						videoGameGenreID = rs.getInt("genre_id");
					conn.close();
					conn = null;
					rs.close();
					rs = null;
					stmt.close();
					stmt = null;
					if(videoGameSeriesOfGames == null) { //Not in series
						insertGameSentence = "Insert INTO game (game_name, game_distribution_date, game_distribution_company, game_creative_company, game_trailer,"
								+ "game_description, game_genre_id, game_manufacture_year, game_price) "
								+ "VALUES ('" + videoGameToAdd.getVideoGameName() + "', '" + videoGameToAdd.getVideoGameDistributionDate() + "', "
										+ "'" + videoGameToAdd.getVideoGameDistributionCompany() + "', '" + videoGameToAdd.getVideoGameCreativeCompany()
										+ "', '" + videoGameToAdd.getVideoGameTrailerLink() + "', '" + videoGameToAdd.getVideoGameDescription() 
										+ "', "	+ videoGameGenreID + ", " + videoGameToAdd.getVideoGameManufactureYear() 
										+ ", " + videoGameToAdd.getVideoGamePrice() + ")";
					}
					else { //In series
						if(videoGameSeriesOfGamesNameID == -1) { //Series not exist in data base
							//Insert series into database
							conn = MySqlCommands.makeConnectionToMySQL();
							String s = "Insert INTO series (series_name) VALUES ('" + videoGameSeriesOfGamesName + "')";
							stmt = conn.createStatement();
							stmt.executeUpdate(s);
							conn.close();
							conn = null;
							stmt.close();
							stmt = null;
							//Get the series id
							conn = MySqlCommands.makeConnectionToMySQL();
							stmt = conn.createStatement();
							rs = stmt.executeQuery("SELECT * FROM series WHERE series_name LIKE '" + videoGameSeriesOfGamesName + "'");
							while(rs.next())
								videoGameSeriesOfGamesNameID = rs.getInt("series_id");
							conn.close();
							conn = null;
							rs.close();
							rs = null;
							stmt.close();
							stmt = null;
						}
						insertGameSentence = "Insert INTO game (game_name, game_distribution_date, game_distribution_company, game_creative_company, game_trailer,"
								+ "game_description, game_genre_id, game_manufacture_year, game_series_id, game_price) "
								+ "VALUES ('" + videoGameToAdd.getVideoGameName() + "', '" + videoGameToAdd.getVideoGameDistributionDate() + "', "
										+ "'" + videoGameToAdd.getVideoGameDistributionCompany() + "', '" + videoGameToAdd.getVideoGameCreativeCompany()
										+ "', '" + videoGameToAdd.getVideoGameTrailerLink() + "', '" + videoGameToAdd.getVideoGameDescription() + "', "
										+ videoGameGenreID + ", " + videoGameToAdd.getVideoGameManufactureYear() + ", " + videoGameSeriesOfGamesNameID + ""
										+ ", " + videoGameToAdd.getVideoGamePrice() + ")";
					}
					conn = MySqlCommands.makeConnectionToMySQL();
					stmt = conn.createStatement();
					stmt.executeUpdate(insertGameSentence);
					conn.close();
					conn = null;
					stmt.close();
					stmt = null;
					view.resetMenuSelection();
					view.showNothingInBottom();
					view.showInCenter(new Text("Game added successfully"));
				}
				catch(Exception e) {
					Text errorText = new Text(e.getMessage());
					errorText.setFill(Color.RED);
					view.showInBottom(errorText);
				}
			}
		};
		view.clickOnAddInAddWindow(clickOnAddInAddWindow);
		
		//Update game
		//EventHandler for click on select game in update game window
		EventHandler<ActionEvent> clickOnSelectGameInUpdateGameWindow = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					if((view.getComboBoxOfNamesOfAllGames().getValue() == null) || (view.getComboBoxOfNamesOfAllGames().getValue().equals("")))
						throw new Exception("You must select game!");
					else {
						view.resetMenuSelection();
						GridPane newDetailsToShow = new GridPane();
						newDetailsToShow.setHgap(10);
						newDetailsToShow.setVgap(10);
						newDetailsToShow.add(new Text("Game name: "), 0, 0);
						view.clearTextFieldOfGameName();
						view.getTextFieldOfGameName().setText(view.getComboBoxOfNamesOfAllGames().getValue());
						newDetailsToShow.add(view.getTextFieldOfGameName(), 1, 0);
						view.getTextFieldOfGameName().setVisible(true);
						newDetailsToShow.add(new Text("At least one char"), 2, 0);
						newDetailsToShow.add(new Text("Distribution date: "), 0, 1);
						view.clearTextFieldOfDistributionDate();
						newDetailsToShow.add(view.getTextFieldOfDistributionDate(), 1, 1);
						view.getTextFieldOfDistributionDate().setVisible(true);
						newDetailsToShow.add(new Text("dd/mm/yyyy"), 2, 1);
						newDetailsToShow.add(new Text("Distribution company: "), 0, 2);
						view.clearTextFieldOfDistributionCompany();
						newDetailsToShow.add(view.getTextFieldOfDistributionCompany(), 1, 2);
						view.getTextFieldOfDistributionCompany().setVisible(true);
						newDetailsToShow.add(new Text("At least one char"), 2, 2);
						newDetailsToShow.add(new Text("Creative company: "), 0, 3);
						view.clearTextFieldOfCreativeCompany();
						newDetailsToShow.add(view.getTextFieldOfCreativeCompany(), 1, 3);
						view.getTextFieldOfCreativeCompany().setVisible(true);
						newDetailsToShow.add(new Text("At least one char"), 2, 3);
						newDetailsToShow.add(new Text("Trailer: "), 0, 4);
						view.clearTextFieldOfTrailer();
						newDetailsToShow.add(view.getTextFieldOfTrailer(), 1, 4);
						view.getTextFieldOfTrailer().setVisible(true);
						newDetailsToShow.add(new Text("Description: "), 0, 5);
						view.clearTextFieldOfDescription();
						newDetailsToShow.add(view.getTextFieldOfDescription(), 1, 5);
						view.getTextFieldOfDescription().setVisible(true);
						newDetailsToShow.add(new Text("Genre: "), 0, 6);
						newDetailsToShow.add(view.getComboBoxOfGenresFromBeginning(), 1, 6);
						view.getComboBoxOfGenresFromBeginning().setVisible(true);
						newDetailsToShow.add(new Text("Must select option"), 2, 6);
						newDetailsToShow.add(new Text("Manufacture year: "), 0, 7);
						view.clearTextFieldOfManufactureYear();
						newDetailsToShow.add(view.getTextFieldOfManufactureYear(), 1, 7);
						view.getTextFieldOfManufactureYear().setVisible(true);
						newDetailsToShow.add(new Text("Must fill in number"), 2, 7);
						newDetailsToShow.add(new Text("Part of series: "), 0, 8);
						view.clearCheckBoxOfPartOfSeries();
						newDetailsToShow.add(view.getCheckBoxOfPartOfSeries(), 1, 8);
						view.getCheckBoxOfPartOfSeries().setVisible(true);
						newDetailsToShow.add(view.getTextOfNameOfSeries(), 2, 8);
						view.getTextOfNameOfSeries().setVisible(false);
						newDetailsToShow.add(view.getTextFieldOfSeriesName(), 3, 8);
						view.getTextFieldOfSeriesName().setVisible(false);
						newDetailsToShow.add(view.getTextReqCharsForSeriesName(), 4, 8);
						view.getTextReqCharsForSeriesName().setVisible(false);
						newDetailsToShow.add(new Text("Price"), 0, 9);
						view.clearTextFieldOfPrice();
						newDetailsToShow.add(view.getTextFieldOfPrice(), 1, 9);
						view.getTextFieldOfPrice().setVisible(true);
						newDetailsToShow.add(new Text("Must fill with positive number"), 2, 9);
						newDetailsToShow.add(view.getUpdateGameButton(), 0, 10);
						Connection conn = null;
						Statement stmt = null;
						ResultSet rs = null;
						int seriesId = -1;
						int genreId = -1;
						//Fill in the old info except from genre, series
						conn = MySqlCommands.makeConnectionToMySQL();
						stmt = conn.createStatement();
						rs = stmt.executeQuery("SELECT * FROM game WHERE game_name LIKE '" + view.getComboBoxOfNamesOfAllGames().getValue() + "'");
						while(rs.next()) {
							view.getTextFieldOfDistributionDate().setText(rs.getString("game_distribution_date"));
							view.getTextFieldOfDistributionCompany().setText(rs.getString("game_distribution_company"));
							view.getTextFieldOfCreativeCompany().setText(rs.getString("game_creative_company"));
							view.getTextFieldOfTrailer().setText(rs.getString("game_trailer"));
							if(rs.getString("game_trailer").equals("Not have link")) {
								view.getTextFieldOfTrailer().setText("");
							}
							view.getTextFieldOfDescription().setText(rs.getString("game_description"));
							if(rs.getString("game_description").equals("Not have description")) {
								view.getTextFieldOfDescription().setText("");
							}
							genreId = rs.getInt("game_genre_id");
							view.getTextFieldOfManufactureYear().setText(Integer.toString(rs.getInt("game_manufacture_year")));
							if(rs.getString("game_series_id") != null) { //Part of series
								view.getCheckBoxOfPartOfSeries().setSelected(true);
								view.getTextOfNameOfSeries().setVisible(true);
								view.getTextFieldOfSeriesName().setVisible(true);
								view.getTextReqCharsForSeriesName().setVisible(true);
								seriesId = rs.getInt("game_series_id");
							}
							else { //Not part of series
								view.getCheckBoxOfPartOfSeries().setSelected(false);
								view.getTextFieldOfSeriesName().setText("");
							}
							view.getTextFieldOfPrice().setText(Integer.toString(rs.getInt("game_price")));
						}
						conn.close();
						conn = null;
						rs.close();
						rs = null;
						stmt.close();
						stmt = null;
						//Fill in genre
						conn = MySqlCommands.makeConnectionToMySQL();
						stmt = conn.createStatement();
						rs = stmt.executeQuery("SELECT * FROM genre WHERE genre_id = " + genreId);
						while(rs.next()) {
							view.getComboBoxOfGenres().setValue(rs.getString("genre_name"));
						}
						conn.close();
						conn = null;
						rs.close();
						rs = null;
						stmt.close();
						stmt = null;
						if(seriesId != -1) { //Part of series
							//Fill in series
							conn = MySqlCommands.makeConnectionToMySQL();
							stmt = conn.createStatement();
							rs = stmt.executeQuery("SELECT * FROM series WHERE series_id = " + seriesId);
							while(rs.next()) {
								view.getTextFieldOfSeriesName().setText(rs.getString("series_name"));
							}
							conn.close();
							conn = null;
							rs.close();
							rs = null;
							stmt.close();
							stmt = null;
						}
						newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
						view.showInCenter(newDetailsToShow);
						view.showNothingInBottom();
						view.showNothingInRight();
					}
				}
				catch(Exception e) {
					Text errorText = new Text(e.getMessage());
					errorText.setFill(Color.RED);
					view.showInBottom(errorText);
				}
			}
		};
		view.clickOnSelectGameInUpdateGameWindow(clickOnSelectGameInUpdateGameWindow);
		
		//EventHandler for click on update game in update game window
		EventHandler<ActionEvent> clickOnUpdateGameInUpdateGameWindow = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					String updateStatement;
					Connection conn = null;
					ResultSet rs = null;
					Statement stmt = null;
					String videoGameNewName;
					String videoGameNewDistributionDate;
					String videoGameNewDistributionCompany;
					String videoGameNewCreativeCompany;
					String videoGameNewTrailerLink;
					String videoGameNewDescription;
					String videoGameNewGenre;
					int videoGameNewGenreId = -1;
					int videoGameNewManufactureYear;
					String videoGameNewSeriesOfGames;
					int videoGameNewSeriesOfGamesId = -1;
					int videoGameNewPrice;
					checkValidInputs.getSingleCheckValidInputsObject().checkGameNameUpdateIsOk(view.getTextFieldOfGameName().getText(), view.getComboBoxOfNamesOfAllGames().getValue()); //Check the name
					videoGameNewName = view.getTextFieldOfGameName().getText();
					checkValidInputs.getSingleCheckValidInputsObject().checkDistributionDateInput(view.getTextFieldOfDistributionDate().getText()); //Check distribution date
					videoGameNewDistributionDate = view.getTextFieldOfDistributionDate().getText();
					checkValidInputs.getSingleCheckValidInputsObject().checkDistributionCompanyName(view.getTextFieldOfDistributionCompany().getText()); //Check distribution company
					videoGameNewDistributionCompany = view.getTextFieldOfDistributionCompany().getText();
					checkValidInputs.getSingleCheckValidInputsObject().checkCreativeCompanyName(view.getTextFieldOfCreativeCompany().getText()); //Check creative company
					videoGameNewCreativeCompany = view.getTextFieldOfCreativeCompany().getText();
					videoGameNewTrailerLink = checkValidInputs.getSingleCheckValidInputsObject().checkTrailerLink(view.getTextFieldOfTrailer().getText()); //Check trailer link
					videoGameNewDescription = checkValidInputs.getSingleCheckValidInputsObject().checkGameDescription(view.getTextFieldOfDescription().getText()); //Check game description
					checkValidInputs.getSingleCheckValidInputsObject().checkGenreInput(view.getComboBoxOfGenres()); //Check game genre
					videoGameNewGenre = view.getComboBoxOfGenres().getValue();
					checkValidInputs.getSingleCheckValidInputsObject().checkIfManufactureYearIsOnlyNums(view.getTextFieldOfManufactureYear().getText()); //Check manufacture year
					checkValidInputs.getSingleCheckValidInputsObject().checkManufactureYearInput(Integer.parseInt(view.getTextFieldOfManufactureYear().getText())); //Check manufacture year
					videoGameNewManufactureYear = Integer.parseInt(view.getTextFieldOfManufactureYear().getText());
					if(view.getCheckBoxOfPartOfSeries().isSelected()) { //Part of series
						checkValidInputs.getSingleCheckValidInputsObject().checkSeriesOfGamesNameIsOk(view.getTextFieldOfSeriesName().getText()); //Check game series of games
						videoGameNewSeriesOfGames = view.getTextFieldOfSeriesName().getText();
					}
					else { //Not part of series
						videoGameNewSeriesOfGames = null;
					}					
					checkValidInputs.getSingleCheckValidInputsObject().checkIfPriceIsOnlyNums(view.getTextFieldOfPrice().getText()); //Check the price
					checkValidInputs.getSingleCheckValidInputsObject().checkPriceInput(Integer.parseInt(view.getTextFieldOfPrice().getText())); //Check the price
					videoGameNewPrice = Integer.parseInt(view.getTextFieldOfPrice().getText());
					//Get genre id
					conn = MySqlCommands.makeConnectionToMySQL();
					stmt = conn.createStatement();
					rs = stmt.executeQuery("SELECT * FROM genre WHERE genre_name LIKE '" + videoGameNewGenre + "'");
					while(rs.next())
						videoGameNewGenreId = rs.getInt("genre_id");
					conn.close();
					conn = null;
					rs.close();
					rs = null;
					stmt.close();
					stmt = null;
					if(view.getCheckBoxOfPartOfSeries().isSelected()) { //Part of series
						//Get series of games id
						conn = MySqlCommands.makeConnectionToMySQL();
						stmt = conn.createStatement();
						rs = stmt.executeQuery("SELECT * FROM series WHERE series_name LIKE '" + videoGameNewGenre + "'");
						while(rs.next())
							videoGameNewSeriesOfGamesId = rs.getInt("series_id");
						conn.close();
						conn = null;
						rs.close();
						rs = null;
						stmt.close();
						stmt = null;
						if(videoGameNewSeriesOfGamesId == -1) { //Series not exist
							//Insert series
							conn = MySqlCommands.makeConnectionToMySQL();
							stmt = conn.createStatement();
							stmt.executeUpdate("INSERT INTO series (series_name) VALUES ('" + videoGameNewSeriesOfGames + "')");
							conn.close();
							conn = null;
							stmt.close();
							stmt = null;
							//Get series id
							conn = MySqlCommands.makeConnectionToMySQL();
							stmt = conn.createStatement();
							rs = stmt.executeQuery("SELECT * FROM series WHERE series_name LIKE '" + videoGameNewSeriesOfGames + "'");
							while(rs.next())
								videoGameNewSeriesOfGamesId = rs.getInt("series_id");
							conn.close();
							conn = null;
							rs.close();
							rs = null;
							stmt.close();
							stmt = null;
						}
						updateStatement = "UPDATE game "
								+ "SET game_name = '" + videoGameNewName + "', "
								+ "game_distribution_date = '" + videoGameNewDistributionDate + "', "
								+ "game_distribution_company = '" + videoGameNewDistributionCompany + "', "
								+ "game_creative_company = '" + videoGameNewCreativeCompany + "', "
								+ "game_trailer = '" + videoGameNewTrailerLink + "', "
								+ "game_description = '" + videoGameNewDescription + "', "
								+ "game_genre_id = " + videoGameNewGenreId + ", "
								+ "game_manufacture_year = " + videoGameNewManufactureYear + ", "
								+ "game_series_id = " + videoGameNewSeriesOfGamesId + ", "
								+ "game_price = " + videoGameNewPrice + " "
								+ "WHERE game_name LIKE '" + view.getComboBoxOfNamesOfAllGames().getValue() + "'";
					}
					else { //Not part of series
						updateStatement = "UPDATE game "
								+ "SET game_name = '" + videoGameNewName + "', "
								+ "game_distribution_date = '" + videoGameNewDistributionDate + "', "
								+ "game_distribution_company = '" + videoGameNewDistributionCompany + "', "
								+ "game_creative_company = '" + videoGameNewCreativeCompany + "', "
								+ "game_trailer = '" + videoGameNewTrailerLink + "', "
								+ "game_description = '" + videoGameNewDescription + "', "
								+ "game_genre_id = " + videoGameNewGenreId + ", "
								+ "game_manufacture_year = " + videoGameNewManufactureYear + ", "
								+ "game_series_id = null, "
								+ "game_price = " + videoGameNewPrice + " "
								+ "WHERE game_name LIKE '" + view.getComboBoxOfNamesOfAllGames().getValue() + "'";
					}
					//Update the game
					conn = MySqlCommands.makeConnectionToMySQL();
					stmt = conn.createStatement();
					stmt.executeUpdate(updateStatement);
					conn.close();
					conn = null;
					stmt.close();
					stmt = null;
					view.showNothingInBottom();
					view.showNothingInRight();
					view.showInCenter(new Text("Game updated successfully"));
				}
				catch(Exception e) {
					Text errorText = new Text(e.getMessage());
					errorText.setFill(Color.RED);
					view.showInBottom(errorText);
				}
			}
		};
		view.clickOnUpdateGameInUpdateGameWindow(clickOnUpdateGameInUpdateGameWindow);
		
		//Update personal info
		//EventHandler for click on update in update personal info window
		EventHandler<ActionEvent> clickOnUpdateInUpdatePersonalInfoWindow = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					checkValidInputs.getSingleCheckValidInputsObject().checkFirstNameInput(view.getTextFieldOfFirstName().getText());
					checkValidInputs.getSingleCheckValidInputsObject().checkLastNameInput(view.getTextFieldOfLastName().getText());
					checkValidInputs.getSingleCheckValidInputsObject().checkGenderInput(view.getGenderOptions());
					checkValidInputs.getSingleCheckValidInputsObject().checkEmailUpdateInput(view.getTextFieldOfEmail().getText(), oldEmail);
					checkValidInputs.getSingleCheckValidInputsObject().checkUserNameUpdateInput(view.getTextFieldOfUserID().getText(), connectedUser);
					checkValidInputs.getSingleCheckValidInputsObject().checkPasswordInput(view.getTextFieldOfPassword().getText());
					String gender = "Female";
					if(view.getRadioButtonOfMaleOption().isSelected())
						gender = "Male";
					Connection conn = null;
					Statement stmt = null;
					ResultSet rs = null;
					String updateStatement = "";
					int idOfConnectedUser = -1;
					//Get the id number of connected user
					conn = MySqlCommands.makeConnectionToMySQL();
					stmt = conn.createStatement();
					rs = stmt.executeQuery("SELECT * FROM users WHERE user_id = '" + connectedUser + "'");
					while(rs.next())
						idOfConnectedUser = rs.getInt("user_info_id");
					conn.close();
					conn = null;
					rs.close();
					rs = null;
					stmt.close();
					stmt = null;
					//Update on users info
					conn = MySqlCommands.makeConnectionToMySQL();
					stmt = conn.createStatement();
					updateStatement = "UPDATE users_info "
											+ "SET user_info_first_name = '" + view.getTextFieldOfFirstName().getText() + "', "
											+ "user_info_last_name = '" + view.getTextFieldOfLastName().getText() + "', "
											+ "user_info_email = '" + view.getTextFieldOfEmail().getText() + "', "
											+ "user_info_gender = '" + gender + "' "
											+ "WHERE user_info_id = " + idOfConnectedUser;
					stmt.executeUpdate(updateStatement);
					conn.close();
					conn = null;
					stmt.close();
					stmt = null;
					//Update on users
					conn = MySqlCommands.makeConnectionToMySQL();
					stmt = conn.createStatement();
					updateStatement = "UPDATE users "
									+ "SET user_password = '" + view.getTextFieldOfPassword().getText() + "', "
									+ "user_id = '" + view.getTextFieldOfUserID().getText() + "' "
									+ "WHERE user_info_id = " + idOfConnectedUser;
					stmt.executeUpdate(updateStatement);
					conn.close();
					conn = null;
					stmt.close();
					stmt = null;
					connectedUser = view.getTextFieldOfUserID().getText();
					view.setConnectedMode(view.getTextFieldOfFirstName().getText());
					view.showInCenter(new Text("Updated successfully"));
					view.resetMenuSelection();
					view.showNothingInBottom();
				}
				catch(Exception e) {
					Text errorText = new Text(e.getMessage());
					errorText.setFill(Color.RED);
					view.showInBottom(errorText);
				}
			}
		};
		view.clickOnUpdateInUpdatePersonalInfoWindow(clickOnUpdateInUpdatePersonalInfoWindow);
		
		//Logout
		//EventHandler for click on yes in logout window
		EventHandler<ActionEvent> clickOnYesButtomInLogoutWindow = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.setGuestMode();
				connectedUser = "";
				view.showInCenter(new Text("You logout successfully, hope to see you soon"));
			}
		};
		view.clickOnYesButtomInLogoutWindow(clickOnYesButtomInLogoutWindow);
		
		//EventHandler for click on no in logout window
		view.clickOnNoButtomInLogoutWindow(clickOnSerchGameInMenu);
	}
}
