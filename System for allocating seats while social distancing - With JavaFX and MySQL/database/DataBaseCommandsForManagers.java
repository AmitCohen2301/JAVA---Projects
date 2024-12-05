package database;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import model.Event;
import model.User;

public class DataBaseCommandsForManagers {
	
	// Search manager id by user id and event id in data base
	public static int searchManagerIdByUserIdAndEventIdInDataBase(int userId, int eventId) throws Exception {
		int managerIdFound = DataBaseCommands.MANAGER_ID_NOT_FOUND_IN_DATA_BASE;
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_MANAGERS_TABLE + " " +
				"WHERE manager_user_id = " + userId + " " +
				"AND manager_event_id = " + eventId;
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) // Get the manager id that found
				managerIdFound = rs.getInt("manager_id");
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT manager id by user id and event id!");
		}
	       
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	       
	    return managerIdFound;
	}
	
	// Add manager to data base
	public static boolean addManagerToDataBase(User manager, Event managedEvent) throws Exception {
		int numOfNewRows = 0;
		int userId = DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(manager);
		int managedEventId = DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(managedEvent);
		
		if(userId == DataBaseCommands.USER_ID_NOT_FOUND_IN_DATA_BASE) // User not exist, so not add
			return false;
		if(managedEventId == DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE) // Event not exist, so not add
			return false;
		
		// Manager already exist, so not add
		if(searchManagerIdByUserIdAndEventIdInDataBase(userId, managedEventId) != DataBaseCommands.MANAGER_ID_NOT_FOUND_IN_DATA_BASE)
			return false;
		
		String stringInsert = "INSERT INTO " + DataBaseCommands.NAME_OF_MANAGERS_TABLE + " (manager_user_id, manager_event_id) " +
				"VALUES (" + userId + ", " + managedEventId + ")";
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfNewRows = stmt.executeUpdate(stringInsert); // Execute INSERT
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to INSERT new manager!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return numOfNewRows == 1;
	}
	
	// Remove manager from data base
	public static boolean removeManagerFromDataBase(int managerIdToRemove, int managedEventIdToRemove) throws Exception {
		int numOfDeletedRows = 0;
		String stringDelete = "DELETE FROM " + DataBaseCommands.NAME_OF_MANAGERS_TABLE + " " +
				"WHERE manager_user_id = " + managerIdToRemove + " " +
				"AND manager_event_id =" + managedEventIdToRemove;
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfDeletedRows = stmt.executeUpdate(stringDelete); // Execute DELETE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to DELETE manager!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return numOfDeletedRows == 1;
	}
	
	// Remove user from being manager from data base
	public static boolean removeUserFromBeingManagerFromDataBase(User user) throws Exception {
		int numOfDeletedRows = 0;
		int userIdInDb = DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(user);
		
		if(userIdInDb == DataBaseCommands.USER_ID_NOT_FOUND_IN_DATA_BASE) // User not exist, so not remove
			return false;
		
		String stringDelete = "DELETE FROM " + DataBaseCommands.NAME_OF_MANAGERS_TABLE + " " +
				"WHERE manager_user_id = " + userIdInDb;
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfDeletedRows = stmt.executeUpdate(stringDelete); // Execute DELETE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to DELETE user from being manager!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return numOfDeletedRows > 0;
	}
	
	// Remove managers of event from data base
	public static boolean removeManagersOfEventIdFromDataBase(int managedEventId) throws Exception {
		int numOfDeletedRows = 0;
		String stringDelete = "DELETE FROM " + DataBaseCommands.NAME_OF_MANAGERS_TABLE + " " +
				"WHERE manager_event_id = " + managedEventId;
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfDeletedRows = stmt.executeUpdate(stringDelete); // Execute DELETE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to DELETE managers of event id!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return numOfDeletedRows > 0;
	}
	
	// Remove all managers from data base
	public static boolean removeAllManagersFromDataBase() throws Exception {
		int numOfDeletedRows = 0;
		String stringDelete = "DELETE FROM " + DataBaseCommands.NAME_OF_MANAGERS_TABLE;
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfDeletedRows = stmt.executeUpdate(stringDelete); // Execute DELETE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to DELETE all managers!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
		
		return numOfDeletedRows > 0;
	}
	
	// Get users ids that manage the event in data base
	public static Set<Integer> getUsersIdsThatManageTheEventInDataBase(Event event) throws Exception {
		Set<Integer> eventManagersIds = new HashSet<Integer>();
		
		if(event == null) // Event is null, so not exist
			return eventManagersIds;
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_MANAGERS_TABLE + " " +
				"WHERE manager_event_id = " + DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(event);
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			
			while (rs.next()) // Get the managers ids that found
				eventManagersIds.add(rs.getInt("manager_user_id"));
			
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT users ids that manage the event!");
		}
	    
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return eventManagersIds;
	}
	
	// Get users that manage the event in data base
	public static Set<User> getUsersThatManageTheEventInDataBase(Event event) throws Exception {
		Set<User> eventManagers = new HashSet<User>();
		
		if(event == null) // Event is null, so not exist
			return eventManagers;
		
		Set<Integer> eventManagersIds = getUsersIdsThatManageTheEventInDataBase(event); // Get users ids that manage the event
		
		for(int managerId : eventManagersIds) // Get users by users ids that manage the event
			eventManagers.add(DataBaseCommandsForUsers.searchUserByUserIdInDataBase(managerId));
	    
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return eventManagers;
	}
	
	// Get events ids that managed by the user in data base
	public static Set<Integer> getEventsIdsThatManagedByTheUserInDataBase(User user) throws Exception {
		Set<Integer> eventsIdsThatManage = new HashSet<Integer>();
		
		if(user == null) // Manager is null, so not exist
			return eventsIdsThatManage;
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_MANAGERS_TABLE + " " +
				"WHERE manager_user_id = " + DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(user);
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			
			while (rs.next()) // Get the events ids that found
				eventsIdsThatManage.add(rs.getInt("manager_event_id"));
			
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT events ids of manager!");
		}
	    
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return eventsIdsThatManage;
	}
	
	// Get events that managed by the user in data base
	public static Set<Event> getEventsThatManagedByTheUserInDataBase(User manageUser) throws Exception {
		Set<Event> eventsThatManage = new HashSet<Event>();
		
		if(manageUser == null) // Manager is null, so not exist
			return eventsThatManage;
		
		Set<Integer> eventManagersIds = getEventsIdsThatManagedByTheUserInDataBase(manageUser); // Get events ids that managed by the user
		
		for(int eventId : eventManagersIds) // Get events by events ids that managed by the user
			eventsThatManage.add(DataBaseCommandsForEvents.searchEventByEventIdInDataBase(eventId));
	    
	    DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return eventsThatManage;
	}
}