package database;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import model.Event;

public class DataBaseCommandsForEvents {
	
	// Search event id by event details in data base
	public static int searchEventIdByEventDetailsInDataBase(Event event) throws Exception {
		int eventId = DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE;
		
		if(event == null) // Event is null, so not exist
			return DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE;
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_EVENTS_TABLE + " " +
				"WHERE event_name LIKE '" + event.getEventName() + "' " +
				"AND event_city LIKE '" + event.getEventCity() + "' " +
				"AND event_neighborhood LIKE '" + event.getEventNeighborhood() + "' " +
				"AND event_neighborhood_num = " + event.getEventNeighborhoodNum() + " " +
				"AND event_year = " + event.getEventYear() + " " +
				"AND event_month = " + event.getEventMonth() + " " +
				"AND event_day = " + event.getEventDay() + " " +
				"AND event_hour = " + event.getEventHour() + " " +
				"AND event_minute = " + event.getEventMinute();
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) // Get the event id that found
				eventId = rs.getInt("event_id");
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT event id by event details!");
		}
	    
	    DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return eventId;
	}
		
	// Search event by event id in data base
	public static Event searchEventByEventIdInDataBase(int eventId) throws Exception {
		Event foundEvent = null;
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_EVENTS_TABLE + " " +
				"WHERE event_id = " + eventId;
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) { // Get the event that found
				// Generate "trash" seats prices
				int seatsPrices[][] = new int[rs.getInt("event_num_of_rows")][rs.getInt("event_max_num_of_seats_in_row")];
				for(int rowNum = 0; rowNum < rs.getInt("event_num_of_rows"); rowNum++)
					for(int colNum = 0; colNum < rs.getInt("event_max_num_of_seats_in_row"); colNum++)
						seatsPrices[rowNum][colNum] = 1;
				
				foundEvent = new Event(rs.getString("event_name"), rs.getString("event_city"), rs.getString("event_neighborhood"), rs.getInt("event_neighborhood_num")
						, rs.getInt("event_year"), rs.getInt("event_month"), rs.getInt("event_day"), rs.getInt("event_hour"), rs.getInt("event_minute")
						, rs.getInt("event_num_of_rows"), rs.getInt("event_max_num_of_seats_in_row"), rs.getInt("event_next_invitation_num"), seatsPrices);
			}
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT event by event id!");
		}
	    
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return foundEvent;
	}
		
	// Add event to data base
	public static boolean addEventToDataBase(Event eventToAdd) throws Exception {
		int numOfNewRows = 0;
		
		if(eventToAdd == null) // Event is null, so not add
			return false;
		if(searchEventIdByEventDetailsInDataBase(eventToAdd) != DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE) // Event is exist, so not add
			return false;
		
		String stringInsert = "INSERT INTO " + DataBaseCommands.NAME_OF_EVENTS_TABLE + " (event_name, event_city, event_neighborhood, event_neighborhood_num, " +
				"event_year, event_month, event_day, event_hour, event_minute, event_num_of_rows, event_max_num_of_seats_in_row, event_next_invitation_num) " +
				"VALUES ('" + eventToAdd.getEventName() + "', " +
				"'"+ eventToAdd.getEventCity() +"', " +
				"'" + eventToAdd.getEventNeighborhood() + "', " +
				eventToAdd.getEventNeighborhoodNum() + ", " +
				eventToAdd.getEventYear() + ", " +
				eventToAdd.getEventMonth() + ", " +
				eventToAdd.getEventDay() + ", " +
				eventToAdd.getEventHour() + ", " +
				eventToAdd.getEventMinute() + ", " +
				eventToAdd.getEventNumOfRows() + ", " +
				eventToAdd.getEventMaxNumOfSeatsInRow() + ", " +
				eventToAdd.getEventNextInvitationNumWithoutIncrease() + ")";
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfNewRows = stmt.executeUpdate(stringInsert); // Execute INSERT
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to INSERT new event!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
		
	    return numOfNewRows == 1;
	}
		
	// Update event in data base
	public static boolean updateEventInDataBase(Event updatedEvent, int eventIdInDB) throws Exception {
		int numOfUpdatedRows = 0;
		int updatedEventId = searchEventIdByEventDetailsInDataBase(updatedEvent);
		
		if(updatedEvent == null) // Event is null, so not update
			return false;
		if((updatedEventId != DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE) && (updatedEventId != eventIdInDB)) // Event is exist, so not add
			return false;
		
		String stringUpdate = "UPDATE " + DataBaseCommands.NAME_OF_EVENTS_TABLE + " " +
				"SET event_name = '" + updatedEvent.getEventName() + "', " +
				"event_city = '" + updatedEvent.getEventCity() + "', " +
				"event_neighborhood = '" + updatedEvent.getEventNeighborhood() + "', " +
				"event_neighborhood_num = " + updatedEvent.getEventNeighborhoodNum() + ", " +
				"event_year = " + updatedEvent.getEventYear() + ", " +
				"event_month = " + updatedEvent.getEventMonth() + ", " +
				"event_day = " + updatedEvent.getEventDay() + ", " +
				"event_hour = " + updatedEvent.getEventHour() + ", " +
				"event_minute = " + updatedEvent.getEventMinute() + ", " +
				"event_num_of_rows = " + updatedEvent.getEventNumOfRows() + ", " +
				"event_max_num_of_seats_in_row = " + updatedEvent.getEventMaxNumOfSeatsInRow() + ", " +
				"event_next_invitation_num = " + updatedEvent.getEventNextInvitationNumWithoutIncrease() + " " +
				"WHERE event_id = " + eventIdInDB;
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfUpdatedRows = stmt.executeUpdate(stringUpdate); // Execute UPDATE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to UPDATE event!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return numOfUpdatedRows == 1;
	}
		
	// Remove event from data base
	public static boolean removeEventFromDataBase(Event eventToRemove) throws Exception {
		int numOfDeletedRows = 0;
		
		if(eventToRemove == null) // Event is null, so not remove
			return false;
		
		String stringDelete = "DELETE FROM " + DataBaseCommands.NAME_OF_EVENTS_TABLE + " " +
				"WHERE event_name LIKE '" + eventToRemove.getEventName() + "' " +
				"AND event_city LIKE '" + eventToRemove.getEventCity() + "' " +
				"AND event_neighborhood LIKE '" + eventToRemove.getEventNeighborhood() + "' " +
				"AND event_neighborhood_num = " + eventToRemove.getEventNeighborhoodNum() + " " +
				"AND event_year = " + eventToRemove.getEventYear() + " " +
				"AND event_month = " + eventToRemove.getEventMonth() + " " +
				"AND event_day = " + eventToRemove.getEventDay() + " " +
				"AND event_hour = " + eventToRemove.getEventHour() + " " +
				"AND event_minute = " + eventToRemove.getEventMinute();
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfDeletedRows = stmt.executeUpdate(stringDelete); // Execute DELETE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to DELETE event!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
		
        return numOfDeletedRows == 1;
	}
		
	// Remove all events from data base
	public static boolean removeAllEventsFromDataBase() throws Exception {
		int numOfDeletedRows = 0;
		String stringDelete = "DELETE FROM " + DataBaseCommands.NAME_OF_EVENTS_TABLE;
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfDeletedRows = stmt.executeUpdate(stringDelete); // Execute DELETE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to DELETE all events!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
		
		return numOfDeletedRows > 0;
	}
		
	// Get all events ids in data base
	public static Set<Integer> getAllEventsIdsInDataBase() throws Exception {
		Set<Integer> allEventsIds = new HashSet<>();
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_EVENTS_TABLE;
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) // Get the event that found
				allEventsIds.add(rs.getInt("event_id"));
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT event by event id!");
		}
	    
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return allEventsIds;
	}
}