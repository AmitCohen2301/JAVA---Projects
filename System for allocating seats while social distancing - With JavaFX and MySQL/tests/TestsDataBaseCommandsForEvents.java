package tests;

import java.util.Set;
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

public class TestsDataBaseCommandsForEvents {
	
	// Test search event id by event details (test searchEventIdByEventDetailsInDataBase function)
	@Test
	public void testSearchEventIdByEventDetails() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Search exist event
			int seatPrices1[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices1[rowNum][colNum] = 1;
			Event eventInDb = new Event("party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices1);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb);
			int eventInDbId = DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(eventInDb);
			
			// Search not exist event (null)
			Event eventNotInDb1 = null;
			int eventNotInDbId1 = DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(eventNotInDb1);
			
			// Search not exist event
			int seatPrices2[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices2[rowNum][colNum] = 1;
			Event eventNotInDb2 = new Event("party2", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, seatPrices2);
			int eventNotInDbId2 = DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(eventNotInDb2);
			
			// Print results
			assert((eventInDbId != DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE) && (eventNotInDbId1 == DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE) && 
					(eventNotInDbId2 == DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE));
			System.out.println("Test search event id by event details - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test search event id by event details - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test search event by event id (test searchEventByEventIdInDataBase function)
	@Test
	public void testSearchEventByEventId() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add event
			int seatPrices[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices[rowNum][colNum] = 1;
			Event eventInDb = new Event("party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb);
			int eventInDbId = DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(eventInDb);
			
			// Search exist user
			Event eventFromDb1 = DataBaseCommandsForEvents.searchEventByEventIdInDataBase(eventInDbId);
			
			// Search not exist exist
			Event eventFromDb2 = DataBaseCommandsForEvents.searchEventByEventIdInDataBase(DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE);
			
			// Print results
			assert((eventFromDb1 != null) && (eventFromDb2 == null));
			System.out.println("Test search event by event id - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test search event by event id - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test add event (test addEventToDataBase function)
	@Test
	public void testAddEvent() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add not exist event
			int seatPrices1[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices1[rowNum][colNum] = 1;
			Event eventNotInDbToAdd1 = new Event("garbage party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices1);
			boolean eventNotInDbAdded1 = DataBaseCommandsForEvents.addEventToDataBase(eventNotInDbToAdd1);
			
			// Add not exist event (null)
			Event eventNotInDbToAdd2 = null;
			boolean eventNotInDbAdded2 = DataBaseCommandsForEvents.addEventToDataBase(eventNotInDbToAdd2);
			
			// Add exist event
			int seatPrices3[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices3[rowNum][colNum] = 1;
			Event eventInDbToAdd = new Event("garbage party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, seatPrices3);
			boolean eventInDbAdded = DataBaseCommandsForEvents.addEventToDataBase(eventInDbToAdd);
			
			// Print results
			assert(eventNotInDbAdded1 && !eventNotInDbAdded2 && !eventInDbAdded);
			System.out.println("Test add event - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test add event - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test update event (test updateEventInDataBase function)
	@Test
	public void testUpdateEvent() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Update exist event to not exist
			int seatPrices1[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices1[rowNum][colNum] = 1;
			Event eventInDb1 = new Event("party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices1);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb1);
			int eventInDbId1 = DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(eventInDb1);
			eventInDb1.setEventName("Updated party");
			eventInDb1.setEventCity("Updated tel aviv");
			boolean eventInDbUpdated1 = DataBaseCommandsForEvents.updateEventInDataBase(eventInDb1, eventInDbId1);
			
			// Update not exist event (null)
			Event eventNotInDb1 = null;
			int eventNotInDbId1 = DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(eventNotInDb1);
			boolean eventNotInDbUpdated1 = DataBaseCommandsForEvents.updateEventInDataBase(eventNotInDb1, eventNotInDbId1);
			
			// Update not exist event
			int seatPrices2[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices2[rowNum][colNum] = 1;
			Event eventNotInDb2 = new Event("party2", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, seatPrices2);
			int eventNotInDbId2 = DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(eventNotInDb2);
			boolean eventNotInDbUpdated2 = DataBaseCommandsForEvents.updateEventInDataBase(eventNotInDb2, eventNotInDbId2);
			
			// Update exist event to exist
			int seatPrices3[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices3[rowNum][colNum] = 1;
			Event eventInDb2 = new Event("party2", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices3);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb2);
			int eventInDbId2 = DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(eventInDb2);
			eventInDb2.setEventName("Updated party");
			eventInDb2.setEventCity("Updated tel aviv");
			boolean eventInDbUpdated2 = DataBaseCommandsForEvents.updateEventInDataBase(eventInDb2, eventInDbId2);
			
			// Print results
			assert(eventInDbUpdated1 && !eventNotInDbUpdated1 && !eventNotInDbUpdated2 && !eventInDbUpdated2);
			System.out.println("Test update event - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test update event - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test remove event (test removeEventFromDataBase function)
	@Test
	public void testRemoveEvent() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add permission to data base
			DataBaseCommandsForPermissions.addPermissionToDataBase("Test");
			
			// Add user to data base
			User userInDb = new User("User in db", "UserInDb@gmail.com", "000-0000000", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb);
			
			// Add event to data base
			int seatPrices1[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices1[rowNum][colNum] = 1;
			Event eventInDb = new Event("party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices1);
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
			
			// Remove exist event
			boolean eventInDbRemoved = DataBaseCommandsForEvents.removeEventFromDataBase(eventInDb);
			
			// Remove not exist event (null)
			Event eventNotInDb1 = null;
			boolean eventNotInDb1Removed = DataBaseCommandsForEvents.removeEventFromDataBase(eventNotInDb1);
			
			// Remove not exist event
			int seatPrices2[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices2[rowNum][colNum] = 1;
			Event eventNotInDb2 = new Event("Event not in db", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices2);
			boolean eventNotInDb2Removed = DataBaseCommandsForEvents.removeEventFromDataBase(eventNotInDb2);
			
			// Print results
			assert(eventInDbRemoved && !eventNotInDb1Removed && !eventNotInDb2Removed);
			System.out.println("Test remove event - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test remove event - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test get all events ids (test getAllEventsIdsInDataBase function)
	@Test
	public void testGetAllEventsIds() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Get all events ids (0 events)
			Set<Integer> allEventsId1 = DataBaseCommandsForEvents.getAllEventsIdsInDataBase();
			
			// Add event
			int seatPrices1[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices1[rowNum][colNum] = 1;
			Event eventInDb1 = new Event("garbage party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices1);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb1);
			
			// Add event
			int seatPrices2[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices2[rowNum][colNum] = 1;
			Event eventInDb2 = new Event("garbage party2", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices2);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb2);
			
			// Get all events ids (2 events)
			Set<Integer> allEventsId2 = DataBaseCommandsForEvents.getAllEventsIdsInDataBase();
			
			// Remove event
			DataBaseCommandsForEvents.removeEventFromDataBase(eventInDb1);
			
			// Get all events ids (1 events)
			Set<Integer> allEventsId3 = DataBaseCommandsForEvents.getAllEventsIdsInDataBase();
			
			// Print results
			assert((allEventsId1.size() == 1) && (allEventsId2.size() == 3) && (allEventsId3.size() == 2));	
			System.out.println("Test remove user from being manager - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test remove user from being manager - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
}