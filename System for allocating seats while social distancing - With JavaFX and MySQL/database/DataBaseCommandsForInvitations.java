package database;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import model.Event;
import model.Invitation;
import model.User;

public class DataBaseCommandsForInvitations {
	
	// Search invitation id by invitation details in data base
	public static int searchInvitationIdByInvitationDetailsInDataBase(Invitation invitation) throws Exception {
		int invitationId = DataBaseCommands.INVITATION_ID_NOT_FOUND_IN_DATA_BASE;
		
		if(invitation == null) // Invitation is null, so not exist
			return DataBaseCommands.INVITATION_ID_NOT_FOUND_IN_DATA_BASE;
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_INVITATIONS_TABLE + " " + 
				"WHERE invitation_num = " + invitation.getInvitationNum() + " " +
				"AND invitation_event_belong_to = " + DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(invitation.getEventInvitationBelongTo());
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) // Get the invitation id that found
				invitationId = rs.getInt("invitation_id");
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT invitation id by invitation details!");
		}
	    
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return invitationId;
	}
	
	// Search invitation by invitation id in data base
	public static Invitation searchInvitationByInvitationIdInDataBase(int invitationId) throws Exception {
		Invitation foundInvitation = null;
		int invitationNum = DataBaseCommands.INVITATION_ID_NOT_FOUND_IN_DATA_BASE;
		int invitationUserId = DataBaseCommands.USER_ID_NOT_FOUND_IN_DATA_BASE;
		int invitationEventId = DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE;
		int invitationNumOfSeats = 1;
		boolean invitationSeatTogether = true;
		int invitationNumOfSpaceSeatsSear = 0;
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_INVITATIONS_TABLE + " " +
				"WHERE invitation_id = " + invitationId;
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			
			while (rs.next()) { // Get the invitation that found
				invitationNum = rs.getInt("invitation_num");
				invitationUserId = rs.getInt("invitation_user_belong_to");
				invitationEventId = rs.getInt("invitation_event_belong_to");
				invitationNumOfSeats = rs.getInt("invitation_num_of_seats");
				invitationSeatTogether = rs.getBoolean("invitation_seat_together");
				invitationNumOfSpaceSeatsSear = rs.getInt("invitation_num_of_space_seats_near");
			}
			
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT invitation by invitation id!");
		}
	    
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    if(invitationUserId != DataBaseCommands.USER_ID_NOT_FOUND_IN_DATA_BASE) // Found invitation
	    	foundInvitation = new Invitation(invitationNum, DataBaseCommandsForUsers.searchUserByUserIdInDataBase(invitationUserId), 
	    			DataBaseCommandsForEvents.searchEventByEventIdInDataBase(invitationEventId), 
	    			invitationNumOfSeats, invitationSeatTogether, invitationNumOfSpaceSeatsSear);
	    
	    return foundInvitation;
	}
		
	// Add invitation to data base
	public static boolean addInvitationToDataBase(Invitation invitationToAdd) throws Exception {
		int numOfNewRows = 0;
		
		if(invitationToAdd == null) // Invitation is null, so not add
			return false;
		if(DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(invitationToAdd.getEventInvitationBelongTo()) == 
				DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE) // Event not exist, so not add
			return false;
		if(DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(invitationToAdd.getUserInvitationBelongTo()) == 
				DataBaseCommands.USER_ID_NOT_FOUND_IN_DATA_BASE) // User not exist, so not add
			return false;
		if(searchInvitationIdByInvitationDetailsInDataBase(invitationToAdd) != DataBaseCommands.INVITATION_ID_NOT_FOUND_IN_DATA_BASE) // Invitation exist, so not add
			return false;
		
		String stringInsert = "INSERT INTO " + DataBaseCommands.NAME_OF_INVITATIONS_TABLE + " (invitation_num, invitation_user_belong_to, " +
				"invitation_event_belong_to, invitation_num_of_seats, invitation_seat_together, invitation_num_of_space_seats_near) " +
				"VALUES (" + invitationToAdd.getInvitationNum() + ", " +
				DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(invitationToAdd.getUserInvitationBelongTo()) + ", " +
				DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(invitationToAdd.getEventInvitationBelongTo()) + ", " +
				invitationToAdd.getNumOfSeatsInInvitation() + ", " +
				invitationToAdd.getSeatTogether() + ", " +
				invitationToAdd.getNumOfSpaceSeatsNear() + ")";
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfNewRows = stmt.executeUpdate(stringInsert); // Execute INSERT
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to INSERT new invitation!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return numOfNewRows == 1;
	}
	
	// Update invitation in data base
	public static boolean updateInvitationInDataBase(Invitation updatedInvitation, int invitationIdInDB) throws Exception {
		int numOfUpdatedRows = 0;
		int updatedInvitationId = searchInvitationIdByInvitationDetailsInDataBase(updatedInvitation);
		
		if(updatedInvitation == null) // Invitation is null, so not update
			return false;
		if(DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(updatedInvitation.getEventInvitationBelongTo()) == 
				DataBaseCommands.EVENT_ID_NOT_FOUND_IN_DATA_BASE) // Event not exist, so not update
			return false;
		if(DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(updatedInvitation.getUserInvitationBelongTo()) == 
				DataBaseCommands.USER_ID_NOT_FOUND_IN_DATA_BASE) // User not exist, so not update
			return false;
		
		// Invitation exist, so not update
		if((updatedInvitationId != DataBaseCommands.INVITATION_ID_NOT_FOUND_IN_DATA_BASE) && (updatedInvitationId != invitationIdInDB))
			return false;
		
		String stringUpdate = "UPDATE " + DataBaseCommands.NAME_OF_INVITATIONS_TABLE + " " +
				"SET invitation_num = " + updatedInvitation.getInvitationNum() + ", " +
				"invitation_user_belong_to = " + DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(updatedInvitation.getUserInvitationBelongTo()) + ", " +
				"invitation_event_belong_to = " + DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(updatedInvitation.getEventInvitationBelongTo()) + ", " +
				"invitation_num_of_seats = " + updatedInvitation.getNumOfSeatsInInvitation() + ", " +
				"invitation_seat_together = " + updatedInvitation.getSeatTogether() + ", " +
				"invitation_num_of_space_seats_near = " + updatedInvitation.getNumOfSpaceSeatsNear() + " " +
				"WHERE invitation_id = " + invitationIdInDB;
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfUpdatedRows = stmt.executeUpdate(stringUpdate); // Execute UPDATE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to UPDATE invitation!");
		}
		
	    DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return numOfUpdatedRows == 1;
	}
		
	// Remove invitation from data base
	public static boolean removeInvitationFromDataBase(Invitation invitationToRemove) throws Exception {
		int numOfDeletedRows = 0;
		
		if(invitationToRemove == null) // Invitation is null, so not remove
			return false;
		
		String stringDelete = "DELETE FROM " + DataBaseCommands.NAME_OF_INVITATIONS_TABLE + " " +
				"WHERE invitation_num = " + invitationToRemove.getInvitationNum() + " " +
				"AND invitation_user_belong_to = " + DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(invitationToRemove.getUserInvitationBelongTo()) + " " +
				"AND invitation_event_belong_to = " + DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(invitationToRemove.getEventInvitationBelongTo()) + " ";
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfDeletedRows = stmt.executeUpdate(stringDelete); // Execute DELETE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to DELETE invitation!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return numOfDeletedRows == 1;
	}
	
	// Remove invitations of event id from data base
	public static boolean removeInvitationsOfEventIdFromDataBase(int eventId) throws Exception {
		int numOfDeletedRows = 0;
		String stringDelete = "DELETE FROM " + DataBaseCommands.NAME_OF_INVITATIONS_TABLE + " " +
				"WHERE invitation_event_belong_to = " + eventId;
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfDeletedRows = stmt.executeUpdate(stringDelete); // Execute DELETE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to DELETE invitations of event id!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return numOfDeletedRows > 0;
	}
	
	// Remove all invitations from data base
	public static boolean removeAllInvitationsFromDataBase() throws Exception {	
		int numOfDeletedRows = 0;
		String stringDelete = "DELETE FROM " + DataBaseCommands.NAME_OF_INVITATIONS_TABLE;
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfDeletedRows = stmt.executeUpdate(stringDelete); // Execute DELETE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to DELETE all invitations!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
		
		return numOfDeletedRows > 0;
	}
	
	// Get invitations ids of user in data base
	public static Set<Integer> getInvitationsIdsOfUserInDataBase(User user) throws Exception {
		Set<Integer> invitationsIdsOfUser = new HashSet<Integer>();
		
		if(user == null) // User is null, so not exist
			return invitationsIdsOfUser;
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_INVITATIONS_TABLE + " " +
				"WHERE invitation_user_belong_to = " + DataBaseCommandsForUsers.searchUserIdByUserDetailsInDataBase(user);
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		// Get all invitations ids that belong to the user
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			
			while (rs.next()) // Get the invitations that found
				invitationsIdsOfUser.add(rs.getInt("invitation_id"));
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT invitations ids by user!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
		
		return invitationsIdsOfUser;
	}
	
	// Get invitations of user in data base
	public static Set<Invitation> getInvitationsOfUserInDataBase(User user) throws Exception {
		Set<Invitation> invitationsOfUser = new HashSet<Invitation>();
		Set<Integer> invitationsIdsOfUser = getInvitationsIdsOfUserInDataBase(user);
		
		if(user == null) // User is null, so not exist
			return invitationsOfUser;
		
		// Get all invitations by invitation ids that belong to the user
		try {
			for(int invitationId : invitationsIdsOfUser)
				invitationsOfUser.add(searchInvitationByInvitationIdInDataBase(invitationId));
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT invitations by invitations ids!");
		}
		
		return invitationsOfUser;
	}
	
	// Get invitations to event in data base
	public static Set<Invitation> getInvitationsToEventInDataBase(Event event) throws Exception {
		Set<Invitation> invitationsToEvent = new HashSet<Invitation>();	
		Set<Integer> invitationsIdsToEvent = new HashSet<Integer>();
		
		if(event == null) // Event is null, so not exist
			return invitationsToEvent;
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_INVITATIONS_TABLE + " " +
				"WHERE invitation_event_belong_to = " + DataBaseCommandsForEvents.searchEventIdByEventDetailsInDataBase(event);
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		// Get all invitations ids that belong to the event
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			
			while (rs.next()) // Get the invitations that found
				invitationsIdsToEvent.add(rs.getInt("invitation_id"));
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT invitations ids by event!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
		
		// Get all invitations by invitation ids that belong to the event
		try {
			for(int invitationId : invitationsIdsToEvent)
				invitationsToEvent.add(searchInvitationByInvitationIdInDataBase(invitationId));
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT invitations by invitations ids!");
		}
		
		return invitationsToEvent;
	}
}