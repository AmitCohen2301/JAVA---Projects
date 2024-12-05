package tests;

import java.util.Set;
import org.junit.Test;

import database.DataBaseCommands;
import database.DataBaseCommandsForEvents;
import database.DataBaseCommandsForInvitations;
import database.DataBaseCommandsForManagers;
import database.DataBaseCommandsForPermissions;
import database.DataBaseCommandsForUsers;
import model.Event;
import model.Invitation;
import model.User;

public class TestsDataBaseCommandsForInvitations {
	
	// Test search invitation id by invitation details (test searchInvitationIdByInvitationDetailsInDataBase function)
	@Test
	public void testSearchInvitationIdByInvitationDetails() throws Exception {
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
			
			// Search exist invitation
			int invitationInDbId = DataBaseCommandsForInvitations.searchInvitationIdByInvitationDetailsInDataBase(invitationInDb);
			
			// Search not exist event (null)
			Invitation invitationNotInDb1 = null;
			int invitationNotInDb1Id = DataBaseCommandsForInvitations.searchInvitationIdByInvitationDetailsInDataBase(invitationNotInDb1);
			
			// Search not exist event
			Invitation invitationNotInDb2 = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			int invitationNotInDb2Id = DataBaseCommandsForInvitations.searchInvitationIdByInvitationDetailsInDataBase(invitationNotInDb2);
			
			// Print results
			assert((invitationInDbId != DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE) && 
					(invitationNotInDb1Id == DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE) && 
					(invitationNotInDb2Id == DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE));
			System.out.println("Test search invitation id by invitation details - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test search invitation id by invitation details - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test search invitation by invitation id (test searchInvitationByInvitationIdInDataBase function)
	@Test
	public void testSearchInvitationByInvitationId() throws Exception {
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
			int invitationInDbId = DataBaseCommandsForInvitations.searchInvitationIdByInvitationDetailsInDataBase(invitationInDb);
			
			// Search exist invitation
			Invitation invitationFromDb1 = DataBaseCommandsForInvitations.searchInvitationByInvitationIdInDataBase(invitationInDbId);
			
			// Search not exist invitation
			Invitation invitationFromDb2 = DataBaseCommandsForInvitations.searchInvitationByInvitationIdInDataBase(
					DataBaseCommands.INVITATION_ID_NOT_FOUND_IN_DATA_BASE);
			
			// Print results
			assert((invitationFromDb1 != null) && (invitationFromDb2 == null));
			System.out.println("Test search invitation by invitation id - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test search invitation by invitation id - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test add invitation (test addInvitationToDataBase function)
	@Test
	public void testAddInvitation() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add user
			User userInDb = new User("User in db", "UserInDb@gmail.com", "000-0000000", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb);
			
			// Add Event
			int seatPrices1[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices1[rowNum][colNum] = 1;
			Event eventInDb = new Event("party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices1);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb);
			
			// Add not exist invitation
			Invitation invitationNotInDb1 = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			boolean invitationNotInDb1Added = DataBaseCommandsForInvitations.addInvitationToDataBase(invitationNotInDb1);
			
			// Add not exist invitation (null)
			Invitation invitationNotInDb2 = null;
			boolean invitationNotInDb2Added = DataBaseCommandsForInvitations.addInvitationToDataBase(invitationNotInDb2);
			
			// Add exist invitation
			Invitation invitationInDb = new Invitation(invitationNotInDb1.getInvitationNum(), userInDb, eventInDb, 2, true, 0);
			boolean invitationInDbAdded = DataBaseCommandsForInvitations.addInvitationToDataBase(invitationInDb);
			
			// Print results
			assert(invitationNotInDb1Added && !invitationNotInDb2Added && !invitationInDbAdded);
			System.out.println("Test add invitation - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test add invitation - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test update invitation (test updateInvitationInDataBase function)
	@Test
	public void testUpdateInvitation() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add user
			User userInDb = new User("User in db", "UserInDb@gmail.com", "000-0000000", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(userInDb);
			
			// Add Event
			int seatPrices1[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices1[rowNum][colNum] = 1;
			Event eventInDb = new Event("party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices1);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb);
			
			// Add invitation
			Invitation invitationInDb1 = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			DataBaseCommandsForInvitations.addInvitationToDataBase(invitationInDb1);
			Invitation invitationInDb2 = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			DataBaseCommandsForInvitations.addInvitationToDataBase(invitationInDb2);
			
			// Update exist invitation to not exist
			int invitationInDbId1 = DataBaseCommandsForInvitations.searchInvitationIdByInvitationDetailsInDataBase(invitationInDb1);
			invitationInDb1.setSeatTogether(false);
			invitationInDb1.setNumOfSeatsInInvitation(5);
			boolean invitationInDb1Updated = DataBaseCommandsForInvitations.updateInvitationInDataBase(invitationInDb1, invitationInDbId1);
			
			// Update not exist invitation (null)
			Invitation invitationNotInDb1 = null;
			int invitationNotInDbId1 = DataBaseCommandsForInvitations.searchInvitationIdByInvitationDetailsInDataBase(invitationNotInDb1);
			boolean invitationNotInDb1Updated = DataBaseCommandsForInvitations.updateInvitationInDataBase(invitationNotInDb1, invitationNotInDbId1);
			
			// Update not exist invitation
			Invitation invitationNotInDb2 = new Invitation(invitationInDb1.getInvitationNum() - 1, userInDb, eventInDb, 2, true, 0);
			int invitationNotInDbId2 = DataBaseCommandsForInvitations.searchInvitationIdByInvitationDetailsInDataBase(invitationNotInDb2);
			boolean invitationNotInDb2Updated = DataBaseCommandsForInvitations.updateInvitationInDataBase(invitationNotInDb2, invitationNotInDbId2);
			
			// Update exist invitation to exist
			int invitationInDbId2 = DataBaseCommandsForInvitations.searchInvitationIdByInvitationDetailsInDataBase(invitationInDb2);
			invitationInDb2.setInvitationNum(invitationInDb1.getInvitationNum());
			boolean invitationInDb2Updated = DataBaseCommandsForInvitations.updateInvitationInDataBase(invitationInDb2, invitationInDbId2);
			
			// Print results
			assert(invitationInDb1Updated && !invitationNotInDb1Updated && !invitationNotInDb2Updated && !invitationInDb2Updated);
			System.out.println("Test update invitation - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test update invitation - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test remove invitation (test removeInvitationFromDataBase function)
	@Test
	public void testRemoveInvitation() throws Exception {
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
			
			// Add invitation
			Invitation invitationInDbToRemove = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			DataBaseCommandsForInvitations.addInvitationToDataBase(invitationInDbToRemove);
			
			// Remove exist invitation
			boolean invitationInDbRemoved = DataBaseCommandsForInvitations.removeInvitationFromDataBase(invitationInDbToRemove);
			
			// Remove not exist invitation (null)
			boolean invitationNotInDb1Removed = DataBaseCommandsForInvitations.removeInvitationFromDataBase(null);
			
			// Remove not exist invitation
			Invitation invitationNotInDbToRemove = new Invitation(invitationInDbToRemove.getInvitationNum() - 1, userInDb, eventInDb, 2, true, 0);
			boolean invitationNotInDb2Removed = DataBaseCommandsForInvitations.removeInvitationFromDataBase(invitationNotInDbToRemove);
			
			// Print results
			assert(invitationInDbRemoved && !invitationNotInDb1Removed && !invitationNotInDb2Removed);	
			System.out.println("Test remove invitation - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test remove invitation - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test remove invitations of event id (test removeInvitationsOfEventIdFromDataBase function)
	@Test
	public void testRemoveInvitationsOfEventId() throws Exception {
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
			int eventIdInvitationToRemove = DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(eventInDb);
			
			// Add event
			int seatPrices2[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices2[rowNum][colNum] = 1;
			Event eventInDb2 = new Event("party2", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices2);
			DataBaseCommandsForEvents.addEventToDataBase(eventInDb2);
			
			// Add invitation
			Invitation invitationInDb = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			DataBaseCommandsForInvitations.addInvitationToDataBase(invitationInDb);
			
			// Add invitation
			invitationInDb = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			DataBaseCommandsForInvitations.addInvitationToDataBase(invitationInDb);
			
			// Add invitation
			invitationInDb = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			DataBaseCommandsForInvitations.addInvitationToDataBase(invitationInDb);
			
			// Add invitation
			invitationInDb = new Invitation(eventInDb2.getEventNextInvitationNum(), userInDb, eventInDb2, 2, true, 0);
			DataBaseCommandsForInvitations.addInvitationToDataBase(invitationInDb);
			
			// Remove invitation of exist event id
			boolean invitationOfEventInDbRemoved = DataBaseCommandsForInvitations.removeInvitationsOfEventIdFromDataBase(eventIdInvitationToRemove);
			
			// Remove invitation of not exist event id
			boolean invitationOfEventNotInDbRemoved = DataBaseCommandsForInvitations.removeInvitationsOfEventIdFromDataBase(
					DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE);
			
			// Print results
			assert(invitationOfEventInDbRemoved && !invitationOfEventNotInDbRemoved);
			System.out.println("Test remove invitations of event id - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test remove invitations of event id - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test get invitation ids of user (test function)
	@Test
	public void testGetInvitationsIdsOfUser() throws Exception {
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
			
			// Add invitation1
			Invitation invitation1InDb = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			DataBaseCommandsForInvitations.addInvitationToDataBase(invitation1InDb);
			
			// Add invitation2
			Invitation invitation2InDb = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			DataBaseCommandsForInvitations.addInvitationToDataBase(invitation2InDb);
			
			// Search invitations ids of exist user
			Set<Integer> invitationsOfUserFromDb1 = DataBaseCommandsForInvitations.getInvitationsIdsOfUserInDataBase(userInDb);
			
			// Search invitations ids of not exist user (null)
			Set<Integer> invitationsOfUserFromDb2 = DataBaseCommandsForInvitations.getInvitationsIdsOfUserInDataBase(null);
			
			// Search invitations ids of not exist user
			User userNotInDb = new User("First user not in db", "User1NotInDb@gmail.com", "000-0000002", "Admin");
			Set<Integer> invitationsOfUserFromDb3 = DataBaseCommandsForInvitations.getInvitationsIdsOfUserInDataBase(userNotInDb);
			
			// Print results
			assert((invitationsOfUserFromDb1.size() == 2) && (invitationsOfUserFromDb2.size() == 0) && (invitationsOfUserFromDb3.size() == 0));
			System.out.println("Test search invitations ids of user - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test search invitations ids of user - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test get invitations of user (test getInvitationsOfUserInDataBase function)
	@Test
	public void testGetInvitationsOfUser() throws Exception {
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
			
			// Add invitation1
			Invitation invitation1InDb = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			DataBaseCommandsForInvitations.addInvitationToDataBase(invitation1InDb);
			
			// Add invitation2
			Invitation invitation2InDb = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			DataBaseCommandsForInvitations.addInvitationToDataBase(invitation2InDb);
			
			// Search invitations of exist user
			Set<Invitation> invitationsOfUserFromDb1 = DataBaseCommandsForInvitations.getInvitationsOfUserInDataBase(userInDb);
			
			// Search invitations of not exist user (null)
			Set<Invitation> invitationsOfUserFromDb2 = DataBaseCommandsForInvitations.getInvitationsOfUserInDataBase(null);
			
			// Search invitations of not exist user
			User userNotInDb = new User("First user not in db", "User1NotInDb@gmail.com", "000-0000002", "Admin");
			Set<Invitation> invitationsOfUserFromDb3 = DataBaseCommandsForInvitations.getInvitationsOfUserInDataBase(userNotInDb);
			
			// Print results
			assert((invitationsOfUserFromDb1.size() == 2) && (invitationsOfUserFromDb2.size() == 0) && (invitationsOfUserFromDb3.size() == 0));
			System.out.println("Test search invitations of user - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test search invitations of user - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test get invitations to event (test getInvitationsToEventInDataBase function)
	@Test
	public void testGetInvitationsToEvent() throws Exception {
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
			
			// Add invitation1
			Invitation invitation1InDb = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			DataBaseCommandsForInvitations.addInvitationToDataBase(invitation1InDb);
			
			// Add invitation2
			Invitation invitation2InDb = new Invitation(eventInDb.getEventNextInvitationNum(), userInDb, eventInDb, 2, true, 0);
			DataBaseCommandsForInvitations.addInvitationToDataBase(invitation2InDb);
			
			// Search invitations of exist event
			Set<Invitation> invitationsToEventFromDb1 = DataBaseCommandsForInvitations.getInvitationsToEventInDataBase(eventInDb);
			
			// Search invitations of not exist event (null)
			Set<Invitation> invitationsToEventFromDb2 = DataBaseCommandsForInvitations.getInvitationsToEventInDataBase(null);
			
			// Search invitations of not exist event
			int seatPrices2[][] = new int[10][10];
			for(int rowNum = 0; rowNum < 10; rowNum++)
				for(int colNum = 0; colNum < 10; colNum++)
					seatPrices2[rowNum][colNum] = 1;
			Event eventNotInDb = new Event("party2", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices2);
			Set<Invitation> invitationsToEventFromDb3 = DataBaseCommandsForInvitations.getInvitationsToEventInDataBase(eventNotInDb);
			
			// Print results
			assert((invitationsToEventFromDb1.size() == 2) && (invitationsToEventFromDb2.size() == 0) && invitationsToEventFromDb3.size() == 0);
			System.out.println("Test search invitations of event - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test search invitations of event - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
}