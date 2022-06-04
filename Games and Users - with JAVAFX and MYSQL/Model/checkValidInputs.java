package AmitCohen.Model;

import java.time.LocalDate;
import java.time.Year;

import AmitCohen.MySqlCommands;

import java.sql.*;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class checkValidInputs {
	static final int LENGTH_OF_DATE = 10;
	static final int PLACE_OF_FIRST_SLASH = 2;
	static final int PLACE_OF_SECOND_SLASH = 5;
	private static checkValidInputs singleCheckValidInputsObject = new checkValidInputs();
	
//Constructor
	private checkValidInputs() {}
	
//Get single object
	public static checkValidInputs getSingleCheckValidInputsObject() {
		return singleCheckValidInputsObject;
	}
	
//Register
	//Check the valid of first name
	public void checkFirstNameInput(String firstName) throws Exception {
		if((firstName == null) || (firstName.length() == 0))
			throw new Exception("First name should contain at least one char!");
		for(int charPlaceInName = 0; charPlaceInName < firstName.length(); charPlaceInName++) {
			if((firstName.charAt(charPlaceInName) < 'a' || firstName.charAt(charPlaceInName) > 'z') 
					&& (firstName.charAt(charPlaceInName) < 'A' || firstName.charAt(charPlaceInName) > 'Z'))
				throw new Exception("First name should contain only letters!");
		}
	}
	
	//Check the valid of last name
	public void checkLastNameInput(String lastName) throws Exception {
		if((lastName == null) || (lastName.length() == 0))
			throw new Exception("Last name should contain at least one char!");
		for(int charPlaceInName = 0; charPlaceInName < lastName.length(); charPlaceInName++) {
			if((lastName.charAt(charPlaceInName) < 'a' || lastName.charAt(charPlaceInName) > 'z') 
					&& (lastName.charAt(charPlaceInName) < 'A' || lastName.charAt(charPlaceInName) > 'Z'))
				throw new Exception("Last name should contain only letters!");
		}
	}
	
	//Check the valid of gender
	public void checkGenderInput(ToggleGroup genderOptions) throws Exception {
		if(genderOptions.getSelectedToggle() == null)
			throw new Exception("You need to select gender!");
	}
	
	//Check the valid of email
	public void checkEmailInput(String emailAddress) throws Exception {
		//Check that valid
		int counterOfAlt = 0;
		int placeOfFirstAlt = -1;
		int placeInEmailAddress;
		boolean thereIsAtLeastOneDotAfterFirstAlt = false;
		boolean nextPlaceCanBeDot = false;
		if((emailAddress == null) || (emailAddress.length() == 0))
			throw new Exception("Email can't be empty!");
		for(placeInEmailAddress = 0; placeInEmailAddress < emailAddress.length(); placeInEmailAddress++) { //Count the number of alt in the mail
			if(emailAddress.charAt(placeInEmailAddress) == '@') {
				if(counterOfAlt == 0)
					placeOfFirstAlt = placeInEmailAddress;
				counterOfAlt++;
			}
		}
		if(counterOfAlt != 1) //More or less than 1 alt
			throw new Exception("Structure of email is not valid (****@****.****)!");
		if((placeOfFirstAlt == 0) || (placeOfFirstAlt == emailAddress.length() - 1)) //Email start or end with alt
			throw new Exception("Structure of email is not valid (****@****.****)!");
		placeInEmailAddress = placeOfFirstAlt + 1;
		while(placeInEmailAddress < emailAddress.length()) {
			if(emailAddress.charAt(placeInEmailAddress) != '.')
				nextPlaceCanBeDot = true;
			else { //There is dot now
				if(nextPlaceCanBeDot == false) //2 dots in a row or dot after alt
					throw new Exception("Structure of email is not valid (****@****.****)!");
				thereIsAtLeastOneDotAfterFirstAlt = true;
				nextPlaceCanBeDot = false;
			}
			placeInEmailAddress++;
		}
		if((thereIsAtLeastOneDotAfterFirstAlt == false) || (nextPlaceCanBeDot == false)) //There is no dot at all after alt or end with dot
			throw new Exception("Structure of email is not valid (****@****.****)!");
		//Check that not exists
		Connection conn = null;
		conn = MySqlCommands.makeConnectionToMySQL();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM users_info");
		while(rs.next()) {
			if(rs.getString("user_info_email").equals(emailAddress))
				throw new Exception("Email address is already exists");
		}
		conn.close();
		conn = null;
		rs.close();
		rs = null;
		stmt.close();
		stmt = null;
	}
	
	//Check the valid of user name
	public void checkUserNameInput(String userName) throws Exception {
		//Check that valid
		int counterOfNums = 0;
		int counterOfSmallLetters = 0;
		int counterOfCapitalLetters = 0;
		if((userName == null) || (userName.length() == 0))
			throw new Exception("User name can't be empty!");
		for(int placeInUserName = 0; placeInUserName < userName.length(); placeInUserName++) {
			if((userName.charAt(placeInUserName) >= '0') && (userName.charAt(placeInUserName) <= '9'))
				counterOfNums++;
			if((userName.charAt(placeInUserName) >= 'a') && (userName.charAt(placeInUserName) <= 'z'))
				counterOfSmallLetters++;
			if((userName.charAt(placeInUserName) >= 'A') && (userName.charAt(placeInUserName) <= 'Z'))
				counterOfCapitalLetters++;
		}
		if((counterOfCapitalLetters == 0) || (counterOfSmallLetters == 0) || (counterOfNums == 0))
			throw new Exception("User name is not valid (Should contain at least one capital letter, one low letter and one number!");
		//Check that not exists
		Connection conn = null;
		conn = MySqlCommands.makeConnectionToMySQL();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM users");
		while(rs.next()) {
			if(rs.getString("user_id").equals(userName))
				throw new Exception("User name is already exists");
		}
		conn.close();
		conn = null;
		rs.close();
		rs = null;
		stmt.close();
		stmt = null;
	}
		
	//Check the valid of password
	public void checkPasswordInput(String password) throws Exception {
		int counterOfNums = 0;
		int counterOfSmallLetters = 0;
		int counterOfCapitalLetters = 0;
		if((password == null) || (password.length() < 6))
			throw new Exception("Password must contain at least 6 chars!");
		for(int placeInPassword = 0; placeInPassword < password.length(); placeInPassword++) {
			if((password.charAt(placeInPassword) >= '0') && (password.charAt(placeInPassword) <= '9'))
				counterOfNums++;
			if((password.charAt(placeInPassword) >= 'a') && (password.charAt(placeInPassword) <= 'z'))
				counterOfSmallLetters++;
			if((password.charAt(placeInPassword) >= 'A') && (password.charAt(placeInPassword) <= 'Z'))
				counterOfCapitalLetters++;
		}
		if((counterOfCapitalLetters == 0) || (counterOfSmallLetters == 0) || (counterOfNums == 0))
			throw new Exception("Password is not valid (Should contain at least one capital letter, one low letter and one number!");
	}
	
//Login
	//Check the valid of login
	public int checkLoginInput(String userName, String password) throws Exception {
		Connection conn = null;
		conn = MySqlCommands.makeConnectionToMySQL();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM users");
		while(rs.next()) {
			if((rs.getString("user_id").equals(userName)) && (rs.getString("user_password").equals(password))) {
				int userInfoId = rs.getInt("user_info_id");
				conn.close();
				conn = null;
				rs.close();
				rs = null;
				stmt.close();
				stmt = null;
				return userInfoId;
			}
		}
		conn.close();
		conn = null;
		rs.close();
		rs = null;
		stmt.close();
		stmt = null;
		throw new Exception("User name or password are in correct");
	}
	
//Search
	//Check years are filled
	public void checkYearsAreFilledProperly(TextField startYear, TextField endYear) throws Exception {
		if((startYear.getText() == null) || (endYear.getText() == null) || (startYear.getText().equals("")) || (endYear.getText().equals("")))
			throw new Exception("You must fill in the years or not filter according to years");
		String textInStartYear = startYear.getText();
		String textInEndYear = endYear.getText();
		for(int placeInStartYearString = 0; placeInStartYearString < textInStartYear.length(); placeInStartYearString++) {
			if((textInStartYear.charAt(placeInStartYearString) < '0') || (textInStartYear.charAt(placeInStartYearString) > '9'))
				throw new Exception("Years can be only numbers!");
		}
		for(int placeInEndYearString = 0; placeInEndYearString < textInEndYear.length(); placeInEndYearString++) {
			if((textInEndYear.charAt(placeInEndYearString) < '0') || (textInEndYear.charAt(placeInEndYearString) > '9'))
				throw new Exception("Years can be only numbers!");
		}
	}
	
	//Check price are filled
	public void checkPriceAreFilledProperly(TextField startPrice, TextField endPrice) throws Exception {
		if((startPrice.getText() == null) || (endPrice.getText() == null) || (startPrice.getText().equals("")) || (endPrice.getText().equals("")))
			throw new Exception("You must fill in the prices or not filter according to price");
		String textInStartPrice = startPrice.getText();
		String textInEndPrice = endPrice.getText();
		for(int placeInStartYearString = 0; placeInStartYearString < textInStartPrice.length(); placeInStartYearString++) {
			if((textInStartPrice.charAt(placeInStartYearString) < '0') || (textInStartPrice.charAt(placeInStartYearString) > '9'))
				throw new Exception("Price can be only numbers!");
		}
		for(int placeInEndYearString = 0; placeInEndYearString < textInEndPrice.length(); placeInEndYearString++) {
			if((textInEndPrice.charAt(placeInEndYearString) < '0') || (textInEndPrice.charAt(placeInEndYearString) > '9'))
				throw new Exception("Price can be only numbers!");
		}
	}
	
//Add game
	//Check the valid of genre
	public void checkGenreInput(ComboBox<String> genreOptions) throws Exception {
		if((genreOptions.getValue() == null) || (genreOptions.getValue().equals("")))
			throw new Exception("You need to select genre!");
	}
	
	//Check if manufacture year is only numbers
	public void checkIfManufactureYearIsOnlyNums(String manufactureYearStr) throws Exception {
		if((manufactureYearStr == null) || (manufactureYearStr.equals("")))
			throw new Exception("Manufacture year can't be empty");
		for(int placeInStr = 0; placeInStr < manufactureYearStr.length(); placeInStr++) {
			if((manufactureYearStr.charAt(placeInStr) < '0') || (manufactureYearStr.charAt(placeInStr) > '9'))
				throw new Exception("Manufacture year must contain only numbers");
		}
	}
	
	//Check if price is only numbers
	public void checkIfPriceIsOnlyNums(String priceStr) throws Exception {
		if((priceStr == null) || (priceStr.equals("")))
			throw new Exception("Price can't be empty");
		for(int placeInStr = 0; placeInStr < priceStr.length(); placeInStr++) {
			if((priceStr.charAt(placeInStr) < '0') || (priceStr.charAt(placeInStr) > '9'))
				throw new Exception("Price must contain only numbers");
		}
	}
	
//Video game
	//Check game name is OK and not in use
	public void checkGameNameIsOk(String gameName) throws Exception {
		boolean haveOneGoodChar = false;
		if((gameName == null) || (gameName.equals("")))
			throw new Exception("Game name cannot be empty!");
		for(int placeInName = 0; (placeInName < gameName.length()) && (haveOneGoodChar == false); placeInName++) {
			if(gameName.charAt(placeInName) != ' ')
				haveOneGoodChar = true;
		}
		if(haveOneGoodChar == false)
			throw new Exception("Game name must contain at least one char that not space");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		conn = MySqlCommands.makeConnectionToMySQL();
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM game");
		while(rs.next()) {
			if(rs.getString("game_name").equals(gameName))
				throw new Exception("The game is already exsist");
		}
		conn.close();
		conn = null;
		rs.close();
		rs = null;
		stmt.close();
		stmt = null;
	}
	
	//Check the distribution date is valid
	public void checkDistributionDateInput(String distributionDate) throws Exception {
		if((distributionDate == null) || (distributionDate.length() != LENGTH_OF_DATE)) //Date not in right length
			throw new Exception("Distribution date should contain ten chars!");
		for (int placeInDate = 0; placeInDate < LENGTH_OF_DATE; placeInDate++) { //Check every char in the date
			if((placeInDate != PLACE_OF_FIRST_SLASH) && (placeInDate != PLACE_OF_SECOND_SLASH)) { //Not slash places
				int x = distributionDate.charAt(placeInDate) - '0';
				if((x < 0) || (x > 9))
					throw new Exception("Distribution date should contain only numbers and two slash!");
			}
			else { //Slash places
				if((distributionDate.charAt(placeInDate) != '/') || (distributionDate.charAt(placeInDate) != '/'))
					throw new Exception("Distribution date structure is not valid!"); 
			}
		}
		int day = (distributionDate.charAt(0) - '0') * 10 + distributionDate.charAt(1) - '0';
		int month = (distributionDate.charAt(3) - '0') * 10 + distributionDate.charAt(4) - '0';
		int year = (distributionDate.charAt(6) - '0') * 1000 + (distributionDate.charAt(7) - '0') * 100 + (distributionDate.charAt(8) - '0') * 10 + distributionDate.charAt(9) - '0';
		
		if((day < 1) || (day > 32) || (month < 1) || (month > 12)) //Check number of month and date is valid
			throw new Exception("Distribution date is not valid!");
		switch(month) { //Check that number of days in month valid
		case 2:
			if(day > 28)
				throw new Exception("Distribution date is not valid!");
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			if(day > 30)
				throw new Exception("Distribution date is not valid!");
			break;
		}		
		//Check that the date is already passed
		if(year > Year.now().getValue()) //Year is not been yet
			throw new Exception("Distribution date is not been yet!");
		else {
			if(year == Year.now().getValue()) { //Same year like now
				if(month > LocalDate.now().getMonthValue()) //Month is not been yet
					throw new Exception("Distribution date is not been yet!");
				else {
					if(month == LocalDate.now().getMonthValue()) { //Same month like now
						if(day > LocalDate.now().getDayOfMonth()) //Day is not been yet
							throw new Exception("Distribution date is not been yet!");
					}
				}
			}
		}
	}
	
	//Check that distribution company name contain at least 1 char
	public void checkDistributionCompanyName(String name) throws Exception {
		if((name == null) || (name.length() == 0))
			throw new Exception("Distribution company name should contain at least one char!");
	}
	
	//Check that creative company name contain at least 1 char
	public void checkCreativeCompanyName(String name) throws Exception {
		if((name == null) || (name.length() == 0))
			throw new Exception("Creative company name should contain at least one char!");
	}
	
	//Check trailer link
	public String checkTrailerLink(String trailerLink) throws Exception {
		if((trailerLink == null) || (trailerLink.length() == 0))
			return "Not have link";
		else
			return trailerLink;
	}
	
	//Check description
	public String checkGameDescription(String description) throws Exception {
		if((description == null) || (description.length() == 0))
			return "Not have description";
		else
			return description;
	}
	
	//Check manufacture year
	public void checkManufactureYearInput(int year) throws Exception {
		if(year > Year.now().getValue())
			throw new Exception("Manufacture year is not valid - it's steel not " + year + "!");
	}
	
	//Check price
	public void checkPriceInput(int price) throws Exception {
		if(price < 0)
			throw new Exception("Price can't be negative!");
	}

//Series of games
	//checkSeriesOfGamesNameIsOk
	public void checkSeriesOfGamesNameIsOk(String seriesOfGamesName) throws Exception {
		boolean haveOneGoodChar = false;
		if((seriesOfGamesName == null) || (seriesOfGamesName.equals("")))
			throw new Exception("Series of games name cannot be empty!");
		for(int placeInName = 0; (placeInName < seriesOfGamesName.length()) && (haveOneGoodChar == false); placeInName++) {
			if(seriesOfGamesName.charAt(placeInName) != ' ')
				haveOneGoodChar = true;
		}
		if(haveOneGoodChar == false)
			throw new Exception("Series of games name must contain at least one char that not space");
	}
	
//Update game
	//Check game name update is OK
	public void checkGameNameUpdateIsOk(String newName, String oldName) throws Exception {
		if(newName.equals(oldName))
			return;
		checkGameNameIsOk(newName);
	}
	
//Update personal info
	//Check user name update input
	public void checkUserNameUpdateInput(String newUserName, String oldUserName) throws Exception {
		if(newUserName.equals(oldUserName)) //Not change
			return;
		checkUserNameInput(newUserName);
	}
	
	//Check email update input
	public void checkEmailUpdateInput(String newEmail, String oldEmail) throws Exception {
		if(oldEmail.equals(newEmail))
			return;
		checkEmailInput(newEmail);
	}
}
