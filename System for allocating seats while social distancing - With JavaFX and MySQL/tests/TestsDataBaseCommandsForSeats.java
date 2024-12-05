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

public class TestsDataBaseCommandsForSeats {
	
	// Test search seat id by seat details (test searchSeatIdBySeatDetailsInDataBase function)
	@Test
	public void testSearchSeatIdBySeatDetails() throws Exception {
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
			Event eventInDb = new Event("garbage party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb);
			
			// Add manager to data base
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb, eventInDb);
			
			// Add invitation
			Invitation invitationInDb = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			DataBaseCommandsForInvitations.addInvitationToDataBase(invitationInDb);
			
			// Add seat
			Seat seatInDb = eventInDb.getEventSeats()[0][0];
			seatInDb.setSeatInvitationBelongTo(invitationInDb);
			DataBaseCommandsForSeats.addSeatToDataBase(seatInDb);
			
			// Search exist seat
			int seatInDbId = DataBaseCommandsForSeats.searchSeatIdBySeatDetailsInDataBase(seatInDb);
			
			// Search not exist seat (null)
			Seat seatNotInDb1 = null;
			int seatNotInDb1Id = DataBaseCommandsForSeats.searchSeatIdBySeatDetailsInDataBase(seatNotInDb1);
			
			// Search not exist seat
			Seat seatNotInDb2 = eventInDb.getEventSeats()[0][1];
			int seatNotInDb2Id = DataBaseCommandsForSeats.searchSeatIdBySeatDetailsInDataBase(seatNotInDb2);
			
			// Print results
			assert((seatInDbId != DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE) && 
					(seatNotInDb1Id == DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE) && 
					(seatNotInDb2Id == DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE));
			System.out.println("Test search seat id by seat details - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test search seat id by seat details - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test search seat by seat id (test searchSeatBySeatIdInDataBase function)
	@Test
	public void testSearchSeatBySeatId() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add user
			User userInDb = new User("First user in db", "User1InDb@gmail.com", "000-0000001", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb);
			
			// Add event
			int seatPrices[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices[rowNum][colNum] = 1;
			Event eventInDb = new Event("party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb);
			
			// Add invitation1
			Invitation invitation1InDb = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			DataBaseCommandsForInvitations.addInvitationToDataBase(invitation1InDb);
			
			// Add seat1 (with invitation)
			Seat seatInDb1 = eventInDb.getEventSeats()[0][0];
			seatInDb1.setSeatInvitationBelongTo(invitation1InDb);
			DataBaseCommandsForSeats.addSeatToDataBase(seatInDb1);
			int seatInDb1Id = DataBaseCommandsForSeats.searchSeatIdBySeatDetailsInDataBase(seatInDb1);
			
			// Add seat2 (without invitation)
			Seat seatInDb2 = eventInDb.getEventSeats()[0][1];
			DataBaseCommandsForSeats.addSeatToDataBase(seatInDb2);
			int seatInDb2Id = DataBaseCommandsForSeats.searchSeatIdBySeatDetailsInDataBase(seatInDb2);
			
			// Search exist seat (with invitation)
			Seat foundSeatInDb1 = DataBaseCommandsForSeats.searchSeatBySeatIdInDataBase(seatInDb1Id);
			
			// Search exist seat (with out invitation)
			Seat foundSeatInDb2 = DataBaseCommandsForSeats.searchSeatBySeatIdInDataBase(seatInDb2Id);
			
			// Search not exist seat
			Seat foundSeatInDb3 = DataBaseCommandsForSeats.searchSeatBySeatIdInDataBase(DataBaseCommands.SEAT_ID_NOT_FOUND_IN_DATA_BASE);
			
			// Print results
			assert((foundSeatInDb1 != null) && (foundSeatInDb2 != null) && (foundSeatInDb3 == null));
			System.out.println("Test search seat by seat id - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test search seat by seat id - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test add seat (test addSeatToDataBase function)
	@Test
	public void testAddSeat() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add user
			User userInDb = new User("User in db", "UserInDb@gmail.com", "000-0000000", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb);
			
			// Add event
			int seatPrices[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices[rowNum][colNum] = 1;
			Event eventInDb = new Event("garbage party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb);
			
			// Add invitation
			Invitation invitationInDb = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			DataBaseCommandsForInvitations.addInvitationToDataBase(invitationInDb);
			
			// Add not exist seat with invitation
			Seat seatNotInDb1 = eventInDb.getEventSeats()[0][0];
			seatNotInDb1.setSeatInvitationBelongTo(invitationInDb);
			boolean seatNotInDb1Added = DataBaseCommandsForSeats.addSeatToDataBase(seatNotInDb1);
			
			// Add not exist seat with out invitation
			Seat seatNotInDb2 = eventInDb.getEventSeats()[0][1];
			boolean seatNotInDb2Added = DataBaseCommandsForSeats.addSeatToDataBase(seatNotInDb2);
			
			// Add not exist seat (null)
			Seat seatNotInDb3 = null;
			boolean seatNotInDb3Added = DataBaseCommandsForSeats.addSeatToDataBase(seatNotInDb3);
			
			// Add exist seat
			Seat seatInDb = eventInDb.getEventSeats()[0][0];
			boolean seatInDbAdded = DataBaseCommandsForSeats.addSeatToDataBase(seatInDb);
			
			// Print results
			assert(seatNotInDb1Added && seatNotInDb2Added && !seatNotInDb3Added && !seatInDbAdded);
			System.out.println("Test add seat - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test add seat - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test update seat (test updateSeatInDataBase function)
	@Test
	public void testUpdateSeat() throws Exception {
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
			Event eventInDb = new Event("garbage party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb);
			
			// Add manager to data base
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb, eventInDb);
			
			// Add invitation
			Invitation invitationInDb = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			DataBaseCommandsForInvitations.addInvitationToDataBase(invitationInDb);
			
			// Add seat
			Seat seatInDb1 = eventInDb.getEventSeats()[0][0];
			seatInDb1.setSeatInvitationBelongTo(invitationInDb);
			DataBaseCommandsForSeats.addSeatToDataBase(seatInDb1);
			
			// Update exist seat to not exist
			int seatInDb1Id = DataBaseCommandsForSeats.searchSeatIdBySeatDetailsInDataBase(seatInDb1);
			seatInDb1.setSeatIsTaken(true);
			seatInDb1.setSeatInvitationBelongTo(null);
			boolean seatInDb1Updated = DataBaseCommandsForSeats.updateSeatInDataBase(seatInDb1, seatInDb1Id);
			
			// Update not exist seat (null)
			Seat seatNotInDb1 = null;
			int seatNotInDb1Id = DataBaseCommandsForSeats.searchSeatIdBySeatDetailsInDataBase(seatNotInDb1);
			boolean seatNotInDb1Updated = DataBaseCommandsForSeats.updateSeatInDataBase(seatNotInDb1, seatNotInDb1Id);
			
			// Update not exist seat
			Seat seatNotInDb2 = eventInDb.getEventSeats()[0][1];
			int seatNotInDb2Id = DataBaseCommandsForSeats.searchSeatIdBySeatDetailsInDataBase(seatNotInDb2);
			seatNotInDb2.setSeatIsTaken(true);
			boolean seatNotInDb2Updated = DataBaseCommandsForSeats.updateSeatInDataBase(seatNotInDb2, seatNotInDb2Id);
			
			// Update exist seat to exist
			Seat seatInDb2 = eventInDb.getEventSeats()[0][2];
			DataBaseCommandsForSeats.addSeatToDataBase(seatInDb1);
			int seatInDb2Id = DataBaseCommandsForSeats.searchSeatIdBySeatDetailsInDataBase(seatInDb2);
			seatInDb2.setSeatRowNum(seatInDb1.getSeatRowNum());
			seatInDb2.setSeatNum(seatInDb1.getSeatNum());
			boolean seatInDb2Updated = DataBaseCommandsForSeats.updateSeatInDataBase(seatInDb2, seatInDb2Id);
			
			// Print results
			assert(seatInDb1Updated && !seatNotInDb1Updated && !seatNotInDb2Updated && !seatInDb2Updated);
			System.out.println("Test update seat - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test update seat - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test remove seat (test removeSeatFromDataBase function)
	@Test
	public void testRemoveSeat() throws Exception {
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
			Seat seatInDb1 = eventInDb.getEventSeats()[0][0];
			seatInDb1.setSeatInvitationBelongTo(invitationInDb);
			DataBaseCommandsForSeats.addSeatToDataBase(seatInDb1);
			
			// Remove exist seat
			boolean seatInDbRemoved = DataBaseCommandsForSeats.removeSeatFromDataBase(seatInDb1);
			
			// Remove not exist seat (null)
			Seat seatNotInDb1 = null;
			boolean seatNotInDb1Removed = DataBaseCommandsForSeats.removeSeatFromDataBase(seatNotInDb1);
			
			// Remove not exist seat
			Seat seatNotInDb2 = eventInDb.getEventSeats()[0][1];
			boolean seatNotInDb2Removed = DataBaseCommandsForSeats.removeSeatFromDataBase(seatNotInDb2);
			
			// Print results
			assert(seatInDbRemoved && !seatNotInDb1Removed && !seatNotInDb2Removed);
			System.out.println("Test remove seat - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test remove seat - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test remove seats of event id (test removeSeatsOfEventIdFromDataBase function)
	@Test
	public void testRemoveSeatsOfEventId() throws Exception {
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
			int eventInDbId = DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(eventInDb);
			
			// Add manager to data base
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb, eventInDb);
			
			// Add invitation
			Invitation invitationInDb = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			DataBaseCommandsForInvitations.addInvitationToDataBase(invitationInDb);
			
			// Add seat
			Seat seatInDb1 = eventInDb.getEventSeats()[0][0];
			seatInDb1.setSeatInvitationBelongTo(invitationInDb);
			DataBaseCommandsForSeats.addSeatToDataBase(seatInDb1);
			
			// Add seat
			Seat seatInDb2 = eventInDb.getEventSeats()[0][1];
			DataBaseCommandsForSeats.addSeatToDataBase(seatInDb2);
			
			// Remove seats of exist event
			boolean seatsOfEventInDbRemoved = DataBaseCommandsForSeats.removeSeatsOfEventIdFromDataBase(eventInDbId);
			
			// Remove seats of not exist event
			boolean seatsOfEventNotInDbRemoved = DataBaseCommandsForSeats.removeSeatsOfEventIdFromDataBase(DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE);
			
			// Print results
			assert(seatsOfEventInDbRemoved && !seatsOfEventNotInDbRemoved);
			System.out.println("Test remove seats of event id - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test remove seats of event id - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test get seats of event (test getSeatsOfEventInDataBase function)
	@Test
	public void testGetSeatsOfEvent() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add user
			User userInDb = new User("First user in db", "User1InDb@gmail.com", "000-0000001", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb);
			
			// Add event
			int seatPrices1[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices1[rowNum][colNum] = 1;
			Event eventInDb = new Event("party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices1);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb);
			
			// Add invitation
			Invitation invitationInDb = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			DataBaseCommandsForInvitations.addInvitationToDataBase(invitationInDb);
			
			// Add invitation to seat
			eventInDb.getEventSeats()[0][0].setSeatInvitationBelongTo(invitationInDb);
			eventInDb.getEventSeats()[0][0].setSeatIsAvailable(false);
			eventInDb.getEventSeats()[0][0].setSeatIsTaken(true);
			
			// Add seats of event
			for(int rowNum = 0; rowNum < eventInDb.getEventNumOfRows(); rowNum++)
				for(int seatNum = 0; seatNum < eventInDb.getEventMaxNumOfSeatsInRow(); seatNum++)
					DataBaseCommandsForSeats.addSeatToDataBase(eventInDb.getEventSeats()[rowNum][seatNum]);
			
			// Get seats of exist event
			Seat seatsInDb1[][] = DataBaseCommandsForSeats.getSeatsOfEventInDataBase(eventInDb);
			
			// Get seats of not exist event (null)
			Seat seatsInDb2[][] = DataBaseCommandsForSeats.getSeatsOfEventInDataBase(null);
			
			// Get seats of not exist event
			int seatPrices3[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices3[rowNum][colNum] = 1;
			Event eventNotInDb = new Event("party2", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices3);
			Seat seatsInDb3[][] = DataBaseCommandsForSeats.getSeatsOfEventInDataBase(eventNotInDb);
			
			// Print results
			assert((seatsInDb1 != null) && (seatsInDb2 == null) && (seatsInDb3 == null));
			System.out.println("Test get events seats - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test get events seats - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
}