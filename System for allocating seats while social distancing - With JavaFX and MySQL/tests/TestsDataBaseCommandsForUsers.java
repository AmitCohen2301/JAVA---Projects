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

public class TestsDataBaseCommandsForUsers {
	
	// Test search user id by user details (test searchUserIdByUserDetailsInDataBase function)
	@Test
	public void testSearchUserIdByUserDetails() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Search exist user
			User userInDb = new User("Amit Cohen", "amitcohen2301@gmail.com", "050-4025106", "Admin");
			int userIdInDB = DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(userInDb);
			
			// Search not exist user (null)
			int user1IdNotInDB = DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(null);
			
			// Search not exist user
			User user2NotInDb = new User("AmitCohen", "amit2301@gmail.com", "050-5185104", "Admin");
			int user2IdNotInDB = DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(user2NotInDb);
			
			// Print results
			assert((userIdInDB != DataBaseCommands.USER_ID_NOT_FOUND_IN_DATA_BASE) && 
					(user1IdNotInDB == DataBaseCommands.USER_ID_NOT_FOUND_IN_DATA_BASE) &&
					(user2IdNotInDB == DataBaseCommands.USER_ID_NOT_FOUND_IN_DATA_BASE));
			System.out.println("Test search user id by user details - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test search user id by user details - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test search user id by user phone (test searchUserIdByUserPhoneInDataBase function)
	@Test
	public void testSearchUserIdByUserPhone() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Search exist user
			User userInDb = new User("Amit Cohen", "amitcohen2301@gmail.com", "050-4025106", "Admin");
			int userIdInDB = DataBaseCommandsForUsers.searchUserIdByUserPhoneInDataBase(userInDb);
			
			// Search not exist user (null)
			int user1IdNotInDB = DataBaseCommandsForUsers.searchUserIdByUserPhoneInDataBase(null);
			
			// Search not exist user
			User user2NotInDb = new User("AmitCohen", "amit2301@gmail.com", "050-5185104", "Admin");
			int user2IdNotInDB = DataBaseCommandsForUsers.searchUserIdByUserPhoneInDataBase(user2NotInDb);
			
			// Print results
			assert((userIdInDB != DataBaseCommands.USER_ID_NOT_FOUND_IN_DATA_BASE) && 
					(user1IdNotInDB == DataBaseCommands.USER_ID_NOT_FOUND_IN_DATA_BASE) &&
					(user2IdNotInDB == DataBaseCommands.USER_ID_NOT_FOUND_IN_DATA_BASE));
			System.out.println("Test search user id by user phone - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test search user id by user phone - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test search users ids with the permission (test searchUsersIdsWithThePermissionInDataBase function)
	@Test
	public void testSearchUsersIdsWithThePermission() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Search all users ids with exist permission
			String adminPermissionName =  DataBaseCommands.ADMIN_PERMISSION_NAME_IN_DATA_BASE;
			Set<Integer> idsOfUsersWithExistPermission = DataBaseCommandsForUsers.searchUsersIdsWithThePermissionInTheDataBase(adminPermissionName);
			
			// Search all users ids with not exist permission
			Set<Integer> idsOfUsersWithNotExistPermission = DataBaseCommandsForUsers.searchUsersIdsWithThePermissionInTheDataBase("not exist");
			
			// Print results
			assert((idsOfUsersWithExistPermission.size() == 2) && (idsOfUsersWithNotExistPermission.size() == 0));
			System.out.println("Test search users ids with the permission - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test search users ids with the permission - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test search user by user id (test searchUserByUserIdInDataBase function)
	@Test
	public void testSearchUserByUserId() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add user to data base
			User user1InDb = new User("First user in db", "User1InDb@gmail.com", "000-0000001", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(user1InDb);
			int user1InDbId = DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(user1InDb);
			
			// Search exist user
			User user1FromDb = DataBaseCommandsForUsers.searchUserByUserIdInDataBase(user1InDbId);
			
			// Search not exist user
			User user2FromDb = DataBaseCommandsForUsers.searchUserByUserIdInDataBase(DataBaseCommands.USER_ID_NOT_FOUND_IN_DATA_BASE);
			
			// Print results
			assert((user1FromDb != null) && (user2FromDb == null));
			System.out.println("Test search user by user id - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test search user by user id - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test search user by email (test searchUserByEmailInDataBase function)
	@Test
	public void testSearchUserByEmail() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add user to data base
			User user1InDb = new User("First user in db", "User1InDb@gmail.com", "000-0000001", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(user1InDb);
			
			// Search exist user
			User user1FromDb = DataBaseCommandsForUsers.searchUserByEmailInDataBase("User1InDb@gmail.com");
			
			// Search not exist user (null)
			User user2FromDb = DataBaseCommandsForUsers.searchUserByEmailInDataBase(null);
			
			// Search not exist user
			User user3FromDb = DataBaseCommandsForUsers.searchUserByEmailInDataBase("noMail");
			
			// Print results
			assert((user1FromDb != null) && (user2FromDb == null) && (user3FromDb == null));
			System.out.println("Test search user by email - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test search user by email - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test search user by phone (test searchUserByPhoneInDataBase function)
	@Test
	public void testSearchUserByPhone() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add user to data base
			User user1InDb = new User("First user in db", "User1InDb@gmail.com", "000-0000001", "Admin");
			DataBaseCommandsForUsers.addUserToDataBase(user1InDb);
			
			// Search exist user
			User user1FromDb = DataBaseCommandsForUsers.searchUserByPhoneInDataBase("000-0000001");
			
			// Search not exist user (null)
			User user2FromDb = DataBaseCommandsForUsers.searchUserByPhoneInDataBase(null);
			
			// Search not exist user
			User user3FromDb = DataBaseCommandsForUsers.searchUserByPhoneInDataBase("noPhone");
			
			// Print results
			assert((user1FromDb != null) && (user2FromDb == null) && (user3FromDb == null));
			System.out.println("Test search user by phone - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test search user by phone - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test add user (test addUserToDataBase function)
	@Test
	public void testAddUser() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add not exist user
			User userNotInDb1 = new User("Not in db", "notInDb1@gmail.com", "000-0000000", "Admin");
			boolean userNotInDb1Added = DataBaseCommandsForUsers.addUserToDataBase(userNotInDb1);
			
			// Add not exist user (null)
			User userNotInDb2 = null;
			boolean userNotInDb2Added = DataBaseCommandsForUsers.addUserToDataBase(userNotInDb2);
			
			// Add exist user (email exist)
			User userInDb1 = new User("Amit", "amitcohen2301@gmail.com", "012-3456789", "Admin");
			boolean userInDb1Added = DataBaseCommandsForUsers.addUserToDataBase(userInDb1);
			
			// Add exist user (phone exist)
			User userInDb2 = new User("Amit", "emailnotindb@gmail.com", "050-4025106", "Admin");
			boolean userInDb2Added = DataBaseCommandsForUsers.addUserToDataBase(userInDb2);
			
			// Print results
			assert(userNotInDb1Added && !userNotInDb2Added && !userInDb1Added && !userInDb2Added);
			System.out.println("Test add user - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test add user - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test update user (test updateUserInDataBase function)
	@Test
	public void testUpdateUser() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Update exist user to not exist user
			User userInDb1 = new User("Amit", "amitcohen2301@gmail.com", "050-4025106", "Admin");
			int userIdInDb1 = DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(userInDb1);
			userInDb1.setUserEmail("tryUpdateExist@gmail.com");
			boolean userInDb1Updated = DataBaseCommandsForUsers.updateUserInDataBase(userInDb1, userIdInDb1);
			
			// Update not exist user (null)
			User userNotInDb1 = null;
			int userIdNotInDb1 = DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(userNotInDb1);
			boolean userNotInDb1Updated = DataBaseCommandsForUsers.updateUserInDataBase(userNotInDb1, userIdNotInDb1);
			
			// Update not exist user
			User userNotInDb2 = new User("not exist", "notexist@gmail.com", "000-0000000", "Admin");
			int userIdNotInDb2 = DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(userNotInDb2);
			userNotInDb2.setUserEmail("tryUpdateNotExist@gmail.com");
			boolean userNotInDb2Updated = DataBaseCommandsForUsers.updateUserInDataBase(userNotInDb2, userIdNotInDb2);
			
			// Update exist user to exist user (email exist)
			User userInDb2 = new User("Shahar", "shaharyehuda12@gmail.com", "054-3000885", "Admin");
			int userIdInDb2 = DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(userInDb2);
			userInDb2.setUserEmail(userInDb1.getUserEmail());
			boolean userInDb2Updated = DataBaseCommandsForUsers.updateUserInDataBase(userInDb2, userIdInDb2);
			
			// Update exist user to exist user (phone exist)
			User userInDb3 = new User("Shahar", "shaharyehuda12@gmail.com", "054-3000885", "Admin");
			int userIdInDb3 = DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(userInDb3);
			userInDb3.setUserPhoneNumber(userInDb1.getUserPhoneNumber());
			boolean userInDb3Updated = DataBaseCommandsForUsers.updateUserInDataBase(userInDb3, userIdInDb3);
			
			// Print results
			assert(userInDb1Updated && !userNotInDb1Updated && !userNotInDb2Updated && !userInDb2Updated && !userInDb3Updated);
			System.out.println("Test update user - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test update user - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test update user permissions (test updateUserPermissionsInDataBase function)
	@Test
	public void testUpdateUserPermissions() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Permissions
			int permissionsIdInDb = DataBaseCommandsForPermissions.searchPermissionIdByPermissionNameInDataBase
					(DataBaseCommands.MANAGE_EVENTS_PERMISSION_NAME_IN_DATA_BASE);
			int permissionsIdNotInDb = DataBaseCommands.PERMISSION_ID_NOT_FOUND_IN_DATA_BASE;
			
			// Update exist user to valid permission
			User userInDb1 = new User("Amit", "amitcohen2301@gmail.com", "050-4025106", "Admin");
			boolean userInDb1Updated = DataBaseCommandsForUsers.updateUserPermissionsInDataBase(userInDb1, permissionsIdInDb);
			
			// Update not exist user (null)
			User userNotInDb1 = null;
			boolean userNotInDb1Updated = DataBaseCommandsForUsers.updateUserPermissionsInDataBase(userNotInDb1, permissionsIdInDb);
			
			// Update not exist user
			User userNotInDb2 = new User("not in db", "notindb@gmail.com", "000-0000000", "Admin");
			boolean userNotInDb2Updated = DataBaseCommandsForUsers.updateUserPermissionsInDataBase(userNotInDb2, permissionsIdInDb);
			
			// Update exist user to invalid permission
			User userInDb2 = new User("Shahar", "shaharyehuda12@gmail.com", "054-3000885", "Admin");
			boolean userInDb2Updated = DataBaseCommandsForUsers.updateUserPermissionsInDataBase(userInDb2, permissionsIdNotInDb);
			
			// Print results
			assert(userInDb1Updated && !userNotInDb1Updated && !userNotInDb2Updated && !userInDb2Updated);
			System.out.println("Test update user permissions - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test update user permissions - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test remove user (test removeUserFromDataBase function)
	@Test
	public void testRemoveUser() throws Exception {
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
			seatInDb.setSeatInvitationBelongTo(invitationInDb);
			DataBaseCommandsForSeats.addSeatToDataBase(seatInDb);
			
			// Remove exist user
			boolean userInDbRemoved = DataBaseCommandsForUsers.removeUserFromDataBase(userInDb);
			
			// Remove not exist user (null)
			User userNotInDb1 = null;
			boolean userNotInDb1Removed = DataBaseCommandsForUsers.removeUserFromDataBase(userNotInDb1);
			
			// Remove not exist user
			User userNotInDb2 = new User("User not in db", "UserNotInDb@gmail.com", "000-0000020", "Admin");
			boolean userNotInDb2Removed = DataBaseCommandsForUsers.removeUserFromDataBase(userNotInDb2);
			
			// Print results
			assert(userInDbRemoved && !userNotInDb1Removed && !userNotInDb2Removed);
			System.out.println("Test remove user - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test remove user - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
}