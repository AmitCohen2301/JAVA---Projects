package tests;

import java.util.Set;
import org.junit.Test;

import database.DataBaseCommands;
import database.DataBaseCommandsForEvents;
import database.DataBaseCommandsForManagers;
import database.DataBaseCommandsForPermissions;
import database.DataBaseCommandsForUsers;
import model.Event;
import model.User;

public class TestsDataBaseCommandsForManagers {
	
	// Test search manager id by user id and event id (test searchManagerIdByUserIdAndEventIdInDataBase function)
	@Test
	public void testSearchManagerIdByUserIdAndEventId() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add permission to data base
			DataBaseCommandsForPermissions.addPermissionToDataBase("Test");
			
			// Add user to data base
			User userInDb = new User("User in db", "UserInDb@gmail.com", "000-0000000", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb);
			int userInDbId = DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(userInDb);
			
			// Add event to data base
			int seatPrices1[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices1[rowNum][colNum] = 1;
			Event eventInDb1 = new Event("party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices1);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb1);
			int eventInDbId1 = DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(eventInDb1);
			
			// Add event to data base
			int seatPrices2[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices2[rowNum][colNum] = 1;
			Event eventInDb2 = new Event("party2", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices2);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb2);
			int eventInDbId2 = DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(eventInDb2);
			
			// Add manager to data base
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb, eventInDb1);
			
			// Search exist manager
			int managerInDbId = DataBaseCommandsForManagers.searchManagerIdByUserIdAndEventIdInDataBase(userInDbId, eventInDbId1);
			
			// Search not exist manager
			int managerNotInDbId = DataBaseCommandsForManagers.searchManagerIdByUserIdAndEventIdInDataBase(userInDbId, eventInDbId2);
			
			// Print results
			assert((managerInDbId != DataBaseCommands.MANAGER_ID_NOT_FOUND_IN_DATA_BASE) && 
					(managerNotInDbId == DataBaseCommands.MANAGER_ID_NOT_FOUND_IN_DATA_BASE));
			System.out.println("Test search manager id by user id and event id - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test search manager id by user id and event id - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test add manager (test addManagerToDataBase function)
	@Test
	public void testAddManager() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add user
			User userInDb = new User("User in db", "UserInDb@gmail.com", "000-0000000", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb);
			
			// Add event
			int seatPrices1[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices1[rowNum][colNum] = 1;
			Event eventInDb = new Event("party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices1);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb);
			
			// Add not exist manager
			boolean managerNotInDbAdded1 = DataBaseCommandsForManagers.addManagerToDataBase(userInDb, eventInDb);
			
			// Add not exist manager (user null)
			boolean managerNotInDbAdded2 = DataBaseCommandsForManagers.addManagerToDataBase(null, eventInDb);
			
			// Add not exist manager (event null)
			boolean managerNotInDbAdded3 = DataBaseCommandsForManagers.addManagerToDataBase(userInDb, null);
			
			// Add not exist manager (user not in db)
			User userNotInDb = new User("User not in db", "UserNotInDb@gmail.com", "010-0000000", "Admin");
			boolean managerNotInDbAdded4 = DataBaseCommandsForManagers.addManagerToDataBase(userNotInDb, eventInDb);
			
			// Add not exist manager (event not in db)
			int seatPrices2[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices2[rowNum][colNum] = 1;
			Event eventNotInDb = new Event("Event not in db", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices2);
			boolean managerNotInDbAdded5 = DataBaseCommandsForManagers.addManagerToDataBase(userInDb, eventNotInDb);
			
			// Add exist manager
			boolean managerInDbAdded = DataBaseCommandsForManagers.addManagerToDataBase(userInDb, eventInDb);
			
			// Print results
			assert(managerNotInDbAdded1 && !managerNotInDbAdded2 && !managerNotInDbAdded3 && !managerNotInDbAdded4 && !managerNotInDbAdded5 && !managerInDbAdded);
			System.out.println("Test add manager - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test add manager - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test remove manager (test removeManagerFromDataBase function)
	@Test
	public void testRemoveManager() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add user
			User userInDb1 = new User("First user in db", "User1InDb@gmail.com", "000-0000001", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb1);
			int userInDbId1 = DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(userInDb1);
			
			// Add user
			User userInDb2 = new User("Second user in db", "User2InDb@gmail.com", "000-0000002", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb2);
			
			// Add event
			int seatPrices1[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices1[rowNum][colNum] = 1;
			Event eventInDb1 = new Event("party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices1);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb1);
			int eventInDbId1 = DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(eventInDb1);
			
			// Add event
			int seatPrices2[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices2[rowNum][colNum] = 1;
			Event eventInDb2 = new Event("party2", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices2);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb2);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb1, eventInDb1);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb1, eventInDb2);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb2, eventInDb1);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb2, eventInDb2);
			
			// Remove exist manager
			boolean managerInDbRemoved = DataBaseCommandsForManagers.removeManagerFromDataBase(userInDbId1, eventInDbId1);
			
			// Remove not exist manager (user not in db)
			boolean managerNotInDb1Removed = DataBaseCommandsForManagers.removeManagerFromDataBase(DataBaseCommands.USER_ID_NOT_FOUND_IN_DATA_BASE, eventInDbId1);
			
			// Remove not exist manager (event not in db)
			boolean managerNotInDb2Removed = DataBaseCommandsForManagers.removeManagerFromDataBase(userInDbId1, DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE);
			
			// Remove not exist manager (user and event in db)
			boolean managerNotInDb3Removed = DataBaseCommandsForManagers.removeManagerFromDataBase(userInDbId1, eventInDbId1);
			
			// Print results
			assert(managerInDbRemoved && !managerNotInDb1Removed && !managerNotInDb2Removed && !managerNotInDb3Removed);
			System.out.println("Test remove manager - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test remove manager - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test remove user from being manager (test removeUserFromBeingManagerFromDataBase function)
	@Test
	public void testRemoveUserFromBeingManager() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add user
			User userInDb1 = new User("First user in db", "User1InDb@gmail.com", "000-0000001", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb1);
			
			// Add user
			User userInDb2 = new User("Second user in db", "User2InDb@gmail.com", "000-0000002", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb2);
			
			// Add event
			int seatPrices1[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices1[rowNum][colNum] = 1;
			Event eventInDb1 = new Event("party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices1);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb1);
			
			// Add event
			int seatPrices2[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices2[rowNum][colNum] = 1;
			Event eventInDb2 = new Event("party2", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices2);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb2);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb1, eventInDb1);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb1, eventInDb2);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb2, eventInDb1);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb2, eventInDb2);
			
			// Remove exist user from being manager
			boolean userRemovedFromBeingManagerRemoved1 = DataBaseCommandsForManagers.removeUserFromBeingManagerFromDataBase(userInDb1);
			
			// Remove not exist user from being manager (null)
			boolean userRemovedFromBeingManagerRemoved2 = DataBaseCommandsForManagers.removeUserFromBeingManagerFromDataBase(null);
			
			// Remove not exist user from being manager
			boolean userRemovedFromBeingManagerRemoved3 = DataBaseCommandsForManagers.removeUserFromBeingManagerFromDataBase(userInDb1);
			
			// Print results
			assert(userRemovedFromBeingManagerRemoved1 && !userRemovedFromBeingManagerRemoved2 && !userRemovedFromBeingManagerRemoved3);
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
	
	// Test remove managers of event id (test removeManagersOfEventIdFromDataBase function)
	@Test
	public void testRemoveManagersOfEventId() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add user
			User userInDb1 = new User("First user in db", "User1InDb@gmail.com", "000-0000001", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb1);
			
			// Add user
			User userInDb2 = new User("Second user in db", "User2InDb@gmail.com", "000-0000002", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb2);
			
			// Add event
			int seatPrices1[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices1[rowNum][colNum] = 1;
			Event eventInDb1 = new Event("party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices1);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb1);
			int eventInDbId1 = DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(eventInDb1);
			
			// Add event
			int seatPrices2[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices2[rowNum][colNum] = 1;
			Event eventInDb2 = new Event("party2", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices2);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb2);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb1, eventInDb1);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb1, eventInDb2);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb2, eventInDb1);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb2, eventInDb2);
			
			// Remove managers of exist event id
			boolean managerOfExistEventRemoved = DataBaseCommandsForManagers.removeManagersOfEventIdFromDataBase(eventInDbId1);
			
			// Remove managers of exist event with no managers
			boolean managerOfExistEvent2Removed = DataBaseCommandsForManagers.removeManagersOfEventIdFromDataBase(eventInDbId1);
			
			// Remove managers of not exist event id
			boolean managerOfNotExistEventRemoved = DataBaseCommandsForManagers.removeManagersOfEventIdFromDataBase(DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE);
			
			// Print results
			assert(managerOfExistEventRemoved && !managerOfExistEvent2Removed && !managerOfNotExistEventRemoved);
			System.out.println("Test remove managers of event id - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test remove managers of event id - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test get users ids that manage the event (test getUsersIdsThatManageTheEventInDataBase function)
	@Test
	public void testGetUsersIdsThatManageTheEvent() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add user
			User userInDb1 = new User("First user in db", "User1InDb@gmail.com", "000-0000001", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb1);
			
			// Add user
			User userInDb2 = new User("Second user in db", "User2InDb@gmail.com", "000-0000002", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb2);
			
			// Add event
			int seatPrices1[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices1[rowNum][colNum] = 1;
			Event eventInDb1 = new Event("party1", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices1);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb1);
			
			// Add event
			int seatPrices2[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices2[rowNum][colNum] = 1;
			Event eventInDb2 = new Event("party2", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices2);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb2);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb1, eventInDb1);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb1, eventInDb2);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb2, eventInDb2);
			
			// Get managers ids of exist event
			Set<Integer> managerIdsOfExistEvent = DataBaseCommandsForManagers.getUsersIdsThatManageTheEventInDataBase(eventInDb1);
			
			// Get managers ids of not exist event (null)
			Set<Integer> managerIdsOfNotExistEvent1 = DataBaseCommandsForManagers.getUsersIdsThatManageTheEventInDataBase(null);
			
			// Get managers ids of not exist event
			int seatPrices3[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices3[rowNum][colNum] = 1;
			Event eventNotInDb = new Event("not in db", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices3);
			Set<Integer> managerIdsOfNotExistEvent2 = DataBaseCommandsForManagers.getUsersIdsThatManageTheEventInDataBase(eventNotInDb);
			
			// Print results
			assert((managerIdsOfExistEvent.size() == 1) && (managerIdsOfNotExistEvent1.size() == 0) && (managerIdsOfNotExistEvent2.size() == 0));
			System.out.println("Test get users ids that manage the event - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test get users ids that manage the event - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test get users that manage the event (test getUsersThatManageTheEventInDataBase function)
	@Test
	public void testGetUsersThatManageTheEvent() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add user
			User userInDb1 = new User("First user in db", "User1InDb@gmail.com", "000-0000001", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb1);
			
			// Add user
			User userInDb2 = new User("Second user in db", "User2InDb@gmail.com", "000-0000002", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb2);
			
			// Add event
			int seatPrices1[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices1[rowNum][colNum] = 1;
			Event eventInDb1 = new Event("party1", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices1);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb1);
			
			// Add event
			int seatPrices2[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices2[rowNum][colNum] = 1;
			Event eventInDb2 = new Event("party2", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices2);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb2);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb1, eventInDb1);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb1, eventInDb2);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb2, eventInDb2);
			
			// Get managers of exist event
			Set<User> managersOfEvent1 = DataBaseCommandsForManagers.getUsersThatManageTheEventInDataBase(eventInDb1);
			
			// Get managers of not exist event (null)
			Set<User> managersOfEvent2 = DataBaseCommandsForManagers.getUsersThatManageTheEventInDataBase(null);
			
			// Get managers of not exist event
			int seatPrices3[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices3[rowNum][colNum] = 1;
			Event eventInDb3 = new Event("party3", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices3);
			Set<User> managersOfEvent3 = DataBaseCommandsForManagers.getUsersThatManageTheEventInDataBase(eventInDb3);
			
			// Print results
			assert((managersOfEvent1.size() == 1) && (managersOfEvent2.size() == 0) && (managersOfEvent3.size() == 0));
			System.out.println("Test get users the manage the event - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test get users the manage the event - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test get events ids that managed by the user in data base (test getEventsIdsThatManagedByTheUserInDataBase function)
	@Test
	public void testGetEventsIdsThatManagedByTheUser() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add user
			User userInDb1 = new User("First user in db", "User1InDb@gmail.com", "000-0000001", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb1);
			
			// Add user
			User userInDb2 = new User("Second user in db", "User2InDb@gmail.com", "000-0000002", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb2);
			
			// Add event
			int seatPrices1[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices1[rowNum][colNum] = 1;
			Event eventInDb1 = new Event("party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices1);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb1);
			
			// Add event
			int seatPrices2[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices2[rowNum][colNum] = 1;
			Event eventInDb2 = new Event("party2", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices2);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb2);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb1, eventInDb1);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb1, eventInDb2);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb2, eventInDb2);
			
			// Get event ids of exist manager
			Set<Integer> eventIdsOfExistManager = DataBaseCommandsForManagers.getEventsIdsThatManagedByTheUserInDataBase(userInDb1);
			
			// Get event ids of not exist manager (null)
			Set<Integer> eventIdsOfNotExistManager1 = DataBaseCommandsForManagers.getEventsIdsThatManagedByTheUserInDataBase(null);
			
			// Get event ids of not exist manager
			User userNotInDb = new User("User not in db", "UserNotInDb@gmail.com", "000-0000222", "Admin");
			Set<Integer> eventIdsOfNotExistManager2 = DataBaseCommandsForManagers.getEventsIdsThatManagedByTheUserInDataBase(userNotInDb);
			
			// Print results
			assert((eventIdsOfExistManager.size() == 2) && (eventIdsOfNotExistManager1.size() == 0) && (eventIdsOfNotExistManager2.size() == 0));
			System.out.println("Test get events ids that managed by the user - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test get events ids that managed by the user - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test get events that managed by the user (test getEventsThatManagedByTheUserInDataBase function)
	@Test
	public void testGetEventsThatManagedByTheUser() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add user
			User userInDb1 = new User("First user in db", "User1InDb@gmail.com", "000-0000001", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb1);
			
			// Add user
			User userInDb2 = new User("Second user in db", "User2InDb@gmail.com", "000-0000002", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb2);
			
			// Add event
			int seatPrices1[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices1[rowNum][colNum] = 1;
			Event eventInDb1 = new Event("party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices1);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb1);
			
			// Add event
			int seatPrices2[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices2[rowNum][colNum] = 1;
			Event eventInDb2 = new Event("party2", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices2);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb2);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb1, eventInDb1);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb1, eventInDb2);
			
			// Add manager
			DataBaseCommandsForManagers.addManagerToDataBase(userInDb2, eventInDb2);
			
			// Get event of exist manager
			Set<Event> eventIdsOfExistManager = DataBaseCommandsForManagers.getEventsThatManagedByTheUserInDataBase(userInDb1);
			
			// Get event of not exist manager (null)
			Set<Event> eventIdsOfNotExistManager1 = DataBaseCommandsForManagers.getEventsThatManagedByTheUserInDataBase(null);
			
			// Get event of not exist manager
			User userNotInDb = new User("User not in db", "UserNotInDb@gmail.com", "000-0000222", "Admin");
			Set<Event> eventIdsOfNotExistManager2 = DataBaseCommandsForManagers.getEventsThatManagedByTheUserInDataBase(userNotInDb);
			
			// Print results
			assert((eventIdsOfExistManager.size() == 2) && (eventIdsOfNotExistManager1.size() == 0) && (eventIdsOfNotExistManager2.size() == 0));
			System.out.println("Test get events that managed by the user - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test get events that managed by the user - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
}