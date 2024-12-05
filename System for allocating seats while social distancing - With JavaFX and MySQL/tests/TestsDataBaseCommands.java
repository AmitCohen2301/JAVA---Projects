package tests;

import org.junit.Test;

import database.DataBaseCommands;
import database.DataBaseCommandsForEvents;
import database.DataBaseCommandsForInvitations;
import database.DataBaseCommandsForManagers;
import database.DataBaseCommandsForPermissions;
import database.DataBaseCommandsForSeats;
import database.DataBaseCommandsForUsers;
import model.Event;
import model.Invitation;
import model.Seat;
import model.User;

public class TestsDataBaseCommands {
	
	// Test make and close connection (test makeConnectionToMySQL & closeConnectionToMySQL functions)
	@Test
	public void testMakeAndCloseConnection() throws Exception {
		try {
			// Make connection
			boolean makeConnectionSucceed = DataBaseCommands.makeConnectionToMySQL();
			
			// Close connection
			boolean closeConnectionSucceed = DataBaseCommands.closeConnectionToMySQL();
			
			// Print results
			assert(makeConnectionSucceed && closeConnectionSucceed);
			System.out.println("Test make and close connection - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test make and close connection - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test clear data base (test clearDataBase function)
	@Test
	public void testClearDataBase() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add permission to data base
			DataBaseCommandsForPermissions.addPermissionToDataBase("Test");
			
			// Add user to data base
			User userInDb = new User("User in db", "UserInDb@gmail.com", "000-0000000", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb);
			
			// Add event to data base
			int seatPrices[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices[rowNum][colNum] = 1;
			Event eventInDb = new Event("party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb);
			
			// Add manager to data base
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb, eventInDb);
			
			// Add invitation
			Invitation invitationInDb = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			DataBaseCommandsForInvitations.addInvitationToDataBase(invitationInDb);
			
			// Add seat
			Seat seatInDb = eventInDb.getEventSeats()[0][0];
			DataBaseCommandsForSeats.addSeatToDataBase(seatInDb);
			
			// Clear data base
			boolean clearDataBaseOccur = DataBaseCommands.clearDataBase();
			
			// Print results
			assert(clearDataBaseOccur);
			System.out.println("Test clear data base - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test clear data base - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test init data base (test initDataBase, initPermissions & initUsers & initManagers & initHealthSpace functions)
	@Test
	public void testInitDataBase() throws Exception {
		try {
			// Init data base
			boolean initSucceed = DataBaseCommands.initDataBase();
			
			// Print results
			assert(initSucceed);
			System.out.println("Test init data base - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test init data base - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test init garbage information (test initGarbageInformationToDataBase function)
	@Test
	public void initGarbageInformation() throws Exception {
		try {
			// Init garbage information to data base
			boolean initGarbageSucceed = DataBaseCommands.initGarbageInformationToDataBase();
			
			// Print results
			assert(initGarbageSucceed);
			System.out.println("Test init garbage information to data base - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test init garbage information to data base - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test is email exist (test isEmailExist function)
	@Test
	public void testIsEmailExist() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Check email exist
			boolean emailExist = DataBaseCommands.isEmailExist("amitcohen2301@gmail.com");
			
			// Check email not exist
			boolean emailNotExist1 = DataBaseCommands.isEmailExist("amitcohen2301");
			
			// Check email not exist (null)
			boolean emailNotExist2 = DataBaseCommands.isEmailExist(null);
			
			// Print results
			assert(emailExist && !emailNotExist1 && !emailNotExist2);
			System.out.println("Test check if email exist - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test check if email exist - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test is phone exist (test isPhoneExist function)
	@Test
	public void testIsPhoneExist() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Check phone exist
			boolean phoneExist = DataBaseCommands.isPhoneExist("050-4025106");
			
			// Check phone not exist
			boolean phoneNotExist1 = DataBaseCommands.isPhoneExist("050-402510");
			
			// Check phone not exist (null)
			boolean phoneNotExist2 = DataBaseCommands.isPhoneExist(null);
			
			// Print results
			assert(phoneExist && !phoneNotExist1 && !phoneNotExist2);
			System.out.println("Test check if phone exist - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test check if phone exist - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
}