package database;

import java.sql.ResultSet;
import java.sql.Statement;

import model.Event;
import model.Seat;

public class DataBaseCommandsForSeats {
	
	// Search seat id by seat details in data base
	public static int searchSeatIdBySeatDetailsInDataBase(Seat seat) throws Exception {
		int seatId = DataBaseCommands.SEAT_ID_NOT_FOUND_IN_DATA_BASE;
		
		if(seat == null) // Seat is null, so not exist
			return DataBaseCommands.SEAT_ID_NOT_FOUND_IN_DATA_BASE;
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_SEATS_TABLE + " " + 
				"WHERE seat_row_num = " + seat.getSeatRowNum() + " " +
				"AND seat_num = " + seat.getSeatNum() + " " +
				"AND seat_event_belong_to = " + DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(seat.getSeatEventBelongTo());
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) // Get the seat id that found
				seatId = rs.getInt("seat_id");
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT seat id by seat details!");
		}
	    
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return seatId;
	}
	
	// Search seat by seat id in data base
	public static Seat searchSeatBySeatIdInDataBase(int seatIdInDB) throws Exception {
		Seat foundSeat = null;
		int seatRowNum = 0;
		int seatNum = 0;
		int seatPrice = 0;
		boolean seatIsTaken = true;
		boolean seatIsAvailable = false;
		boolean seatIsExist = true;
		int seatInvitationId = DataBaseCommands.INVITATION_ID_NOT_FOUND_IN_DATA_BASE;
		int seatEventId = DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE;
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_SEATS_TABLE + " " +
				"WHERE seat_id = " + seatIdInDB;
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			
			while (rs.next()) { // Get the seat that found
				seatRowNum = rs.getInt("seat_row_num");
				seatNum = rs.getInt("seat_num");
				seatPrice = rs.getInt("seat_price");
				seatIsTaken = rs.getBoolean("seat_is_taken");
				seatIsAvailable = rs.getBoolean("seat_is_available");
				seatIsExist = rs.getBoolean("seat_is_exist");
				if(rs.getString("seat_invitation_belong_to") != null) // Invitation is not null
					seatInvitationId = rs.getInt("seat_invitation_belong_to");
				seatEventId = rs.getInt("seat_event_belong_to");
			}
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT seat by seat id!");
		}
	    
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    if(seatEventId != DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE) { // Found seat in data base
	      	if(seatInvitationId != DataBaseCommands.INVITATION_ID_NOT_FOUND_IN_DATA_BASE) // There is invitation
	      		foundSeat = new Seat(seatRowNum, seatNum, seatPrice, seatIsTaken, seatIsAvailable, seatIsExist, 
	      				DataBaseCommandsForInvitations.searchInvitationByInvitationIdInDataBase(seatInvitationId), 
	      				DataBaseCommandsForEvents.searchEventByEventIdInDataBase(seatEventId));
	      	else // There is no invitation
	     		foundSeat = new Seat(seatRowNum, seatNum, seatPrice, seatIsTaken, seatIsAvailable, seatIsExist, null, 
	     				DataBaseCommandsForEvents.searchEventByEventIdInDataBase(seatEventId));
	    }
	    
	    return foundSeat;
	}
	
	// Add seat to data base
	public static boolean addSeatToDataBase(Seat seatToAdd) throws Exception {
		int numOfNewRows = 0;
		
		if(seatToAdd == null) // Seat is null, so not add
			return false;
		if(DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(seatToAdd.getSeatEventBelongTo()) == 
				DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE) // Event not exist, so not update
			return false;
		if(searchSeatIdBySeatDetailsInDataBase(seatToAdd) != DataBaseCommands.SEAT_ID_NOT_FOUND_IN_DATA_BASE) // Seat exist, so not add
			return false;
		
		String stringInsert;
		if(seatToAdd.getSeatInvitationBelongTo() == null) { // There is no invitation
			stringInsert = "INSERT INTO " + DataBaseCommands.NAME_OF_SEATS_TABLE + " (seat_row_num, seat_num, seat_price, seat_is_taken, seat_is_available, " +
					"seat_is_exist, seat_event_belong_to) " +
					"VALUES (" + 
					seatToAdd.getSeatRowNum() + ", " +
					seatToAdd.getSeatNum() + ", " +
					seatToAdd.getSeatPrice() + ", " +
					seatToAdd.getSeatIsTaken() + ", " +
					seatToAdd.getSeatIsAvailable() + ", " +
					seatToAdd.getSeatIsExist() + ", " +
					DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(seatToAdd.getSeatEventBelongTo()) + ")";
		}
		else { // There is invitation
			stringInsert = "INSERT INTO " + DataBaseCommands.NAME_OF_SEATS_TABLE + " (seat_row_num, seat_num, seat_price, seat_is_taken, seat_is_available, " +
					"seat_is_exist, seat_invitation_belong_to, seat_event_belong_to) " +
					"VALUES (" + 
					seatToAdd.getSeatRowNum() + ", " +
					seatToAdd.getSeatNum() + ", " +
					seatToAdd.getSeatPrice() + ", " +
					seatToAdd.getSeatIsTaken() + ", " +
					seatToAdd.getSeatIsAvailable() + ", " +
					seatToAdd.getSeatIsExist() + ", " +
					DataBaseCommandsForInvitations.searchInvitationIdByInvitationDetailsInDataBase(seatToAdd.getSeatInvitationBelongTo()) + ", " +
					DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(seatToAdd.getSeatEventBelongTo()) + ")";
		}
		
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfNewRows = stmt.executeUpdate(stringInsert); // Execute INSERT
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to INSERT new seat!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
        
        return numOfNewRows == 1;
	}
	
	// Update seat in data base
	public static boolean updateSeatInDataBase(Seat updatedSeat, int seatIdInDB) throws Exception {
		int numOfUpdatedRows = 0;
		int updatedSeatId = searchSeatIdBySeatDetailsInDataBase(updatedSeat);
		
		if(updatedSeat == null) // Seat is null, so not update
			return false;
		
		// Event not exist, so not update
		if(DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(updatedSeat.getSeatEventBelongTo()) == DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE)
			return false;
		if((updatedSeatId != DataBaseCommands.SEAT_ID_NOT_FOUND_IN_DATA_BASE) && (updatedSeatId != seatIdInDB)) // Seat exist, so not update
			return false;
		
		String stringUpdate;
		if(updatedSeat.getSeatInvitationBelongTo() == null) { // There is no invitation
			stringUpdate = "UPDATE " + DataBaseCommands.NAME_OF_SEATS_TABLE + " " +
					"SET seat_row_num = " + updatedSeat.getSeatRowNum() + ", " +
					"seat_num = " + updatedSeat.getSeatNum() + ", " +
					"seat_price = " + updatedSeat.getSeatPrice() + ", " +
					"seat_is_taken = " + updatedSeat.getSeatIsTaken() + ", " +
					"seat_is_available = " + updatedSeat.getSeatIsAvailable() + ", " +
					"seat_is_exist = " + updatedSeat.getSeatIsExist() + ", " +
					"seat_invitation_belong_to = NULL, " +
					"seat_event_belong_to = " + DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(updatedSeat.getSeatEventBelongTo()) + " " +
					"WHERE seat_id = " + seatIdInDB;
		}
		else { // There is invitation
			stringUpdate = "UPDATE " + DataBaseCommands.NAME_OF_SEATS_TABLE + " " +
					"SET seat_row_num = " + updatedSeat.getSeatRowNum() + ", " +
					"seat_num = " + updatedSeat.getSeatNum() + ", " +
					"seat_price = " + updatedSeat.getSeatPrice() + ", " +
					"seat_is_taken = " + updatedSeat.getSeatIsTaken() + ", " +
					"seat_is_available = " + updatedSeat.getSeatIsAvailable() + ", " +
					"seat_is_exist = " + updatedSeat.getSeatIsExist() + ", " +
					"seat_invitation_belong_to = " + DataBaseCommandsForInvitations.searchInvitationIdByInvitationDetailsInDataBase(
							updatedSeat.getSeatInvitationBelongTo()) + ", " +
					"seat_event_belong_to = " + DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(updatedSeat.getSeatEventBelongTo()) + " " +
					"WHERE seat_id = " + seatIdInDB;
		}
		
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfUpdatedRows = stmt.executeUpdate(stringUpdate); // Execute UPDATE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to UPDATE seat!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
        
        return numOfUpdatedRows == 1;
	}
	
	// Remove seat from data base
	public static boolean removeSeatFromDataBase(Seat seatToRemove) throws Exception {
		int numOfDeletedRows = 0;
		
		if(seatToRemove == null) // Seat is null, so not remove
			return false;
		
		String stringDelete = "DELETE FROM " + DataBaseCommands.NAME_OF_SEATS_TABLE + " " +
				"WHERE seat_row_num = " + seatToRemove.getSeatRowNum() + " " +
				"AND seat_num =" + seatToRemove.getSeatRowNum() + " " +
				"AND seat_event_belong_to = " + DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(seatToRemove.getSeatEventBelongTo());
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfDeletedRows = stmt.executeUpdate(stringDelete); // Execute DELETE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to DELETE seat!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return numOfDeletedRows == 1;
	}
	
	// Remove seats of event id from data base
	public static boolean removeSeatsOfEventIdFromDataBase(int eventId) throws Exception {
		int numOfDeletedRows = 0;
		String stringDelete = "DELETE FROM " + DataBaseCommands.NAME_OF_SEATS_TABLE + " " +
				"WHERE seat_event_belong_to = " + eventId;
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfDeletedRows = stmt.executeUpdate(stringDelete); // Execute DELETE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to DELETE seats of event id!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
		return numOfDeletedRows > 0;
	}
	
	// Remove all seats from data base
	public static boolean removeAllSeatsFromDataBase() throws Exception {	
		int numOfDeletedRows = 0;
		String stringDelete = "DELETE FROM " + DataBaseCommands.NAME_OF_SEATS_TABLE;
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfDeletedRows = stmt.executeUpdate(stringDelete); // Execute DELETE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to DELETE all seats!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
		
		return numOfDeletedRows > 0;
	}
	
	// Get seats of event in data base
	public static Seat[][] getSeatsOfEventInDataBase(Event event) throws Exception {
		Seat[][] eventSeats;
		int[][] invitationsIds;
		
		if(event == null) // Event is null, so not exist
			return null;
		
		int eventId = DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(event);
		
		if(eventId == DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE) // Event not in db, so not exist
			return null;
		
		eventSeats = new Seat[event.getEventNumOfRows()][event.getEventMaxNumOfSeatsInRow()];	
		invitationsIds = new int[event.getEventNumOfRows()][event.getEventMaxNumOfSeatsInRow()];
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_SEATS_TABLE + " " +
				"WHERE seat_event_belong_to = " + eventId;
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			
			while (rs.next()) { // Get the seats that found
				eventSeats[rs.getInt("seat_row_num") - 1][rs.getInt("seat_num") - 1] = new Seat(rs.getInt("seat_row_num"), rs.getInt("seat_num"), 
						rs.getInt("seat_price"), rs.getBoolean("seat_is_taken"), rs.getBoolean("seat_is_available"), rs.getBoolean("seat_is_exist"), null, event);
				if(rs.getString("seat_invitation_belong_to") != null) // There is invitation the seat belong to
					invitationsIds[rs.getInt("seat_row_num") - 1][rs.getInt("seat_num") - 1] = rs.getInt("seat_invitation_belong_to");
				else // No invitation the seat belong to
					invitationsIds[rs.getInt("seat_row_num") - 1][rs.getInt("seat_num") - 1] = DataBaseCommands.INVITATION_ID_NOT_FOUND_IN_DATA_BASE;
			}
			
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT seats by event!");
		}
	    
	    DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    // Get invitations by invitations id
	    for(int rowNum = 0; rowNum < event.getEventNumOfRows(); rowNum++) { // Move on every row
	       	for(int seatNum = 0; seatNum < event.getEventMaxNumOfSeatsInRow(); seatNum++) { // Move on every seat
	       		if(invitationsIds[rowNum][seatNum] != DataBaseCommands.INVITATION_ID_NOT_FOUND_IN_DATA_BASE) // The seat belong to invitation
	       			eventSeats[rowNum][seatNum].setSeatInvitationBelongTo(DataBaseCommandsForInvitations
	       					.searchInvitationByInvitationIdInDataBase(invitationsIds[rowNum][seatNum]));
	       	}
	    }
	    
	    return eventSeats;
	}
}