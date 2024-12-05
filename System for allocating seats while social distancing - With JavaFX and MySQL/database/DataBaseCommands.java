package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;

import model.Event;
import model.Invitation;
import model.Seat;
import model.User;

public class DataBaseCommands {
	private static final String DB_URL_FOR_MYSQL = "jdbc:mysql://localhost:3306/";
	private static final String USER_NAME_FOR_MYSQL = "";
	private static final String PASSWORD_FOR_MYSQL = "";
	
	protected static final String NAME_OF_DATA_BASE = "final_project";
	
	protected static final String NAME_OF_PERMISSIONS_TABLE = "all_permissions";
	protected static final String NAME_OF_HEALTH_STATUS_TABLE = "all_health_status";
	protected static final String NAME_OF_USERS_TABLE = "all_users";
	protected static final String NAME_OF_EVENTS_TABLE = "all_events";
	protected static final String NAME_OF_INVITATIONS_TABLE = "all_invitations";
	protected static final String NAME_OF_SEATS_TABLE = "all_seats";
	protected static final String NAME_OF_MANAGERS_TABLE = "all_managers";
	
	public static final int PERMISSION_ID_NOT_FOUND_IN_DATA_BASE = -1;
	public static final int HEALTH_STATUS_ID_NOT_FOUND_IN_DATA_BASE = -1;
	public static final int USER_ID_NOT_FOUND_IN_DATA_BASE = -1;
	public static final int EVENT_ID_NOT_FOUND_IN_DATA_BASE = -1;
	public static final int INVITATION_ID_NOT_FOUND_IN_DATA_BASE = -1;
	public static final int SEAT_ID_NOT_FOUND_IN_DATA_BASE = -1;
	public static final int MANAGER_ID_NOT_FOUND_IN_DATA_BASE = -1;
	
	public static final String ADMIN_PERMISSION_NAME_IN_DATA_BASE = "Admin";
	public static final String MANAGE_EVENTS_PERMISSION_NAME_IN_DATA_BASE = "Manage_Events";
	public static final String ORDER_EVENTS_PERMISSION_NAME_IN_DATA_BASE = "Order_Events";
	
	public static final String HEALTHY_HEALTH_STATUS_NAME_IN_DATA_BASE = "Healthy";
	public static final String COVID_19_HEALTH_STATUS_NAME_IN_DATA_BASE = "COVID-19";
	
	protected static Connection con = null;
	
// General commands
	
	// Make connection to MySQL
	public static boolean makeConnectionToMySQL() throws Exception {
		try {
			if(con == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(DB_URL_FOR_MYSQL + NAME_OF_DATA_BASE, USER_NAME_FOR_MYSQL, PASSWORD_FOR_MYSQL);
			}
			return true;
		}
		catch (Exception e)
		{
			throw new Exception("Failed to make connection to MySQL data base!");
		}
	}
	
	// Close connection to MySQL
	public static boolean closeConnectionToMySQL() throws Exception {
		try {
			if (con != null)
				con.close();
			
			con = null;
			return true;
		}
		catch (Exception e)
		{
			throw new Exception("Failed to close connection to MySQL data base!");
		}
	}
	
	// Init permissions
	public static int initPermissions() throws Exception {
		int numOfNewPermissions = 0;
		String stringInsertToPermissions = "INSERT INTO " + NAME_OF_PERMISSIONS_TABLE + " (permission_name) " +
				"VALUES ('" + ADMIN_PERMISSION_NAME_IN_DATA_BASE + "'), " +
				"('" + MANAGE_EVENTS_PERMISSION_NAME_IN_DATA_BASE + "'), " +
				"('" + ORDER_EVENTS_PERMISSION_NAME_IN_DATA_BASE + "')";
		Statement stmt = null;
		
		makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = con.createStatement(); // Make statement
			numOfNewPermissions = stmt.executeUpdate(stringInsertToPermissions); // Execute INSERT
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to INSERT init permissions!");
		}
		
		closeConnectionToMySQL(); // Close connection
		
		return numOfNewPermissions;
	}
	
	// Init users
	public static int initUsers() throws Exception {
		int numOfNewUsers = 0;
		int adminId = DataBaseCommandsForPermissions.searchPermissionIdByPermissionNameInDataBase(ADMIN_PERMISSION_NAME_IN_DATA_BASE);
		String stringInsertToUsers = "INSERT INTO " + NAME_OF_USERS_TABLE + "(user_name, user_email, user_phone_number, user_permission) " +
				"VALUES ('Amit', 'amitcohen2301@gmail.com', '050-4025106', " + adminId + "), " +
				"('Shahar', 'shaharyehuda12@gmail.com', '054-3000885', " + adminId + ")";
		Statement stmt = null;
		
		makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = con.createStatement(); // Make statement
			numOfNewUsers = stmt.executeUpdate(stringInsertToUsers); // Execute INSERT
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to INSERT init users!");
		}
		
		closeConnectionToMySQL(); // Close connection
		
		return numOfNewUsers;
	}
	
	// Init events
	public static boolean initEvents() throws Exception {
		
		// Add event
		int seatPrices[][] = new int[10][10];
		for(int rowNum = 0; rowNum < 10; rowNum++)
			for(int colNum = 0; colNum < 10; colNum++)
				seatPrices[rowNum][colNum] = 1;
		Event eventInDb = new Event("party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 5, 5, seatPrices);
		DataBaseCommandsForEvents.addEventToDataBase(eventInDb);
		
		// Add seats
		Seat[][] eventSeats = eventInDb.getEventSeats();
		for(int rowNum = 0; rowNum < eventInDb.getEventNumOfRows(); rowNum++) // Move on every row
			for(int seatNum = 0; seatNum < eventInDb.getEventMaxNumOfSeatsInRow(); seatNum++) // Move on every seat
				DataBaseCommandsForSeats.addSeatToDataBase(eventSeats[rowNum][seatNum]);
		
		return true;
	}
	
	// Init managers
	public static boolean initManagers() throws Exception {
		
		boolean returnValue = true;
		
		// Event that in data base (from init)
		int seatPrices[][] = new int[10][10];
		for(int rowNum = 0; rowNum < 10; rowNum++)
			for(int colNum = 0; colNum < 10; colNum++)
				seatPrices[rowNum][colNum] = 1;
		Event eventInDb = new Event("party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 5, 5, seatPrices);
		
		// Add the event as managed event to each admin in data base
		Set<Integer> idsOfAdminsUsers = DataBaseCommandsForUsers.searchUsersIdsWithThePermissionInTheDataBase(
				DataBaseCommands.ADMIN_PERMISSION_NAME_IN_DATA_BASE);
		for(int adminId : idsOfAdminsUsers)
			returnValue = (returnValue && DataBaseCommandsForManagers.addManagerToDataBase(DataBaseCommandsForUsers.searchUserByUserIdInDataBase(adminId), eventInDb));
		
		return returnValue;
	}
	
	// Init health space
	public static boolean initHealthSpace() throws Exception {
		
		int numOfNewHealthSpaces = 0;
		String stringInsertToHealthSpaces = "INSERT INTO " + NAME_OF_HEALTH_STATUS_TABLE + "(health_status_name, health_status_space) " +
				"VALUES ('" + HEALTHY_HEALTH_STATUS_NAME_IN_DATA_BASE + "', 1), " +	"('" + COVID_19_HEALTH_STATUS_NAME_IN_DATA_BASE + "', 5)";
		Statement stmt = null;
		
		makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = con.createStatement(); // Make statement
			numOfNewHealthSpaces = stmt.executeUpdate(stringInsertToHealthSpaces); // Execute INSERT
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to INSERT init health space!");
		}
		
		closeConnectionToMySQL(); // Close connection
		
		return (numOfNewHealthSpaces == 2);
		
	}
	
	// Clear data base
	public static boolean clearDataBase() throws Exception {
		boolean managersRemoved = DataBaseCommandsForManagers.removeAllManagersFromDataBase(); // Delete all managers
		boolean seatsRemoved = DataBaseCommandsForSeats.removeAllSeatsFromDataBase(); // Delete all seats
		boolean invitationsRemoved = DataBaseCommandsForInvitations.removeAllInvitationsFromDataBase(); // Delete all invitations
		boolean eventsRemoved = DataBaseCommandsForEvents.removeAllEventsFromDataBase();// Delete all events
		boolean usersRemoved = DataBaseCommandsForUsers.removeAllUsersFromDataBase();// Delete all users
		boolean healthStatusesRemoved = DataBaseCommandsForHealthStatus.removeAllHealthStatusFromDataBase(); // Delete all health statuses
		boolean permissionsRemoved = DataBaseCommandsForPermissions.removeAllPermissionsFromDataBase(); // Delete all permissions
		
		return (permissionsRemoved && usersRemoved && eventsRemoved && invitationsRemoved && seatsRemoved && healthStatusesRemoved && managersRemoved);
	}
	
	// Init data base
	public static boolean initDataBase() throws Exception {
		int numOfNewRowsInPermissions = 0;
		int numOfNewRowsInUsers = 0;
		boolean initEventsSucceed = false;
		boolean initManagersSucceed = false;
		boolean initHealthSpace = false;
		
		clearDataBase(); // Clear data base
		numOfNewRowsInPermissions = initPermissions(); // Init permissions
		numOfNewRowsInUsers = initUsers(); // Init users
		initEventsSucceed = initEvents(); // Init events
		initManagersSucceed = initManagers(); // Init managers
		initHealthSpace = initHealthSpace(); // Init health space
		
        return (numOfNewRowsInPermissions == 3) && (numOfNewRowsInUsers == 2) && (initEventsSucceed) && (initManagersSucceed) && (initHealthSpace);
	}
	
	// Init garbage information to data base
	public static boolean initGarbageInformationToDataBase() throws Exception {
		// Init data base
		initDataBase();
		
		// Add permission to data base
		boolean addPermissionSucceed = DataBaseCommandsForPermissions.addPermissionToDataBase("Test");
		
		// Add user to data base
		User user1InDb = new User("First user in db", "User1InDb@gmail.com", "000-0000001", "Admin");
		boolean addUser1Succeed = DataBaseCommandsForUsers.addUserToDataBase(user1InDb);
		
		// Add user to data base
		User user2InDb = new User("Second user in db", "User2InDb@gmail.com", "000-0000002", "Admin");
		boolean addUser2Succeed = DataBaseCommandsForUsers.addUserToDataBase(user2InDb);
		
		// Add event1
		int seatPrices1[][] = new int[10][10];
		for(int rowNum = 0; rowNum < 10; rowNum++)
			for(int colNum = 0; colNum < 10; colNum++)
				seatPrices1[rowNum][colNum] = 1;
		Event event1InDb = new Event("garbage party", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices1);
		boolean addEvent1Succeed = DataBaseCommandsForEvents.addEventToDataBase(event1InDb);
		int event1InDbId = DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(event1InDb);
		
		// Add event2
		int seatPrices2[][] = new int[10][10];
		for(int rowNum = 0; rowNum < 10; rowNum++)
			for(int colNum = 0; colNum < 10; colNum++)
				seatPrices2[rowNum][colNum] = 1;
		Event event2InDb = new Event("garbage party2", "tel aviv", "dereh hashalom", 7, 2025, 1, 1, 20, 0, 10, 10, 8, seatPrices2);
		boolean addEvent2Succeed = DataBaseCommandsForEvents.addEventToDataBase(event2InDb);
		int event2InDbId = DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(event2InDb);
		
		// Add manager1
		boolean addManager1Succeed = DataBaseCommandsForManagers.addManagerToDataBase(user1InDb, event1InDb);
		
		// Add manager2
		boolean addManager2Succeed = DataBaseCommandsForManagers.addManagerToDataBase(user1InDb, event2InDb);
		
		// Add manager3
		boolean addManager3Succeed = DataBaseCommandsForManagers.addManagerToDataBase(user2InDb, event2InDb);
		
		// Add invitation1
		Invitation invitation1InDb = new Invitation(event1InDb.getEventNextInvitationNum(), user1InDb, event1InDb, 2, true, 0);
		boolean addInvitation1Succeed = DataBaseCommandsForInvitations.addInvitationToDataBase(invitation1InDb);
		
		// Add invitation2
		Invitation invitation2InDb = new Invitation(event1InDb.getEventNextInvitationNum(), user2InDb, event1InDb, 2, true, 0);
		boolean addInvitation2Succeed = DataBaseCommandsForInvitations.addInvitationToDataBase(invitation2InDb);
		
		// Add invitation3
		Invitation invitation3InDb = new Invitation(event2InDb.getEventNextInvitationNum(), user1InDb, event2InDb, 2, true, 0);
		boolean addInvitation3Succeed = DataBaseCommandsForInvitations.addInvitationToDataBase(invitation3InDb);
		
		// Update event1
		boolean updateEvent1Succeed = DataBaseCommandsForEvents.updateEventInDataBase(event1InDb, event1InDbId);
		
		// Update event2
		boolean updateEvent2Succeed = DataBaseCommandsForEvents.updateEventInDataBase(event2InDb, event2InDbId);
		
		// Add seat1
		Seat seat1InDb = event1InDb.getEventSeats()[0][0];
		seat1InDb.setSeatInvitationBelongTo(invitation1InDb);
		boolean addSeat1Succeed = DataBaseCommandsForSeats.addSeatToDataBase(seat1InDb);
		
		// Add seat2
		Seat seat2InDb = event1InDb.getEventSeats()[0][1];
		seat2InDb.setSeatInvitationBelongTo(invitation2InDb);
		boolean addSeat2Succeed = DataBaseCommandsForSeats.addSeatToDataBase(seat2InDb);
		
		// Add seat3
		Seat seat3InDb = event2InDb.getEventSeats()[0][0];
		boolean addSeat3Succeed = DataBaseCommandsForSeats.addSeatToDataBase(seat3InDb);
		
		return (addPermissionSucceed && addUser1Succeed && addUser2Succeed && addEvent1Succeed && addEvent2Succeed && addManager1Succeed && addManager2Succeed &&
				addManager3Succeed && addInvitation1Succeed && addInvitation2Succeed && addInvitation3Succeed && updateEvent1Succeed && updateEvent2Succeed &&
				addSeat1Succeed && addSeat2Succeed && addSeat3Succeed);
	}
	
	// Is email exist
	public static boolean isEmailExist(String emailToCheck) throws Exception {
		boolean emailExist = false;
		
		if(emailToCheck == null) // Email is null, so not exist
			return false;
		
		String stringFilter = "SELECT * FROM " + NAME_OF_USERS_TABLE + " " +
				"WHERE user_email LIKE '" + emailToCheck + "'";
		Statement stmt = null;
		ResultSet rs = null;
		
		makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) // Get if email exist
				emailExist = true;
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to check if email exist!");
		}
        
        closeConnectionToMySQL(); // Close connection
        
        return emailExist;
	}
	
	// Is phone exist
	public static boolean isPhoneExist(String phoneToCheck) throws Exception {
		boolean phoneExist = false;
		
		if(phoneToCheck == null) // Phone is null, so not exist
			return false;
		
		String stringFilter = "SELECT * FROM " + NAME_OF_USERS_TABLE + " " +
				"WHERE user_phone_number LIKE '" + phoneToCheck + "'";
		Statement stmt = null;
		ResultSet rs = null;
		
		makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) // Get if phone exist
				phoneExist = true;
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to check if phone exist!");
		}
        
        closeConnectionToMySQL(); // Close connection
        
        return phoneExist;
	}
}