package database;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class DataBaseCommandsForHealthStatus {
	
	// Search health status id by health status name in data base
	public static int searchHealthStatusIdByHealthStatusNameInDataBase(String healthStatusName) throws Exception {
		int foundHealthStatusNameId = DataBaseCommands.HEALTH_STATUS_ID_NOT_FOUND_IN_DATA_BASE;
		
		if(healthStatusName == null) // Health status name is null, so not exist
			return DataBaseCommands.HEALTH_STATUS_ID_NOT_FOUND_IN_DATA_BASE;
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_HEALTH_STATUS_TABLE + " " +
				"WHERE health_status_name LIKE '" + healthStatusName + "'";
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) // Get the health status id that found
	        	foundHealthStatusNameId = rs.getInt("health_status_id");
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT health status id by health status name!");
		}
	       
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
		
		return foundHealthStatusNameId;
	}
	
	// Search health status name by health status id in data base
	public static String searchHealthStatusNameByHealthStatusIdInDataBase(int healthStatusId) throws Exception {
		String foundHealthStatusName = null;
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_HEALTH_STATUS_TABLE + " " +
				"WHERE health_status_id = " + healthStatusId;
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) // Get the health status that found
				foundHealthStatusName = rs.getString("health_status_name");
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT health status name by health status id!");
		}
	       
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	       
	    return foundHealthStatusName;
	}
	
	// Add health status to data base
	public static boolean addHealthStatusToDataBase(String healthStatusNameToAdd, int healthStatusSpace) throws Exception {
		int numOfNewRows = 0;
		
		if(healthStatusNameToAdd == null) // Health status name to add is null, so not add
			return false;
		
		if(healthStatusSpace < 0) // Health status space is less than 0, so not update
			return false;
		
		// Health status name to add exist in data base, so not add
		if(searchHealthStatusIdByHealthStatusNameInDataBase(healthStatusNameToAdd) != DataBaseCommands.HEALTH_STATUS_ID_NOT_FOUND_IN_DATA_BASE)
			return false;
		
		String stringInsert = "INSERT INTO " + DataBaseCommands.NAME_OF_HEALTH_STATUS_TABLE + " (health_status_name, health_status_space) " +
				"VALUES ('" + healthStatusNameToAdd + "', " + healthStatusSpace + ")";
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfNewRows = stmt.executeUpdate(stringInsert); // Execute INSERT
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to INSERT new health status!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	       
	    return numOfNewRows == 1;
	}
	
	// Update health status name in data base
	public static boolean updateHealthStatusNameInDataBase(String healthStatusOldName, String healthStatusNewName) throws Exception {
		int numOfUpdatedRows = 0;
		String stringUpdate = "UPDATE " + DataBaseCommands.NAME_OF_HEALTH_STATUS_TABLE + " " +
				"SET health_status_name = '" + healthStatusNewName + "' " + 
				"WHERE health_status_name LIKE '" + healthStatusOldName + "'";
		Statement stmt = null;
		
		if(healthStatusNewName == null) // Health status new name is null, so not update
			return false;
		
		// Health status new name already exist in data base, so not update
		if(searchHealthStatusIdByHealthStatusNameInDataBase(healthStatusNewName) != DataBaseCommands.HEALTH_STATUS_ID_NOT_FOUND_IN_DATA_BASE)
			return false;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfUpdatedRows = stmt.executeUpdate(stringUpdate); // Execute UPDATE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to UPDATE health status name!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	       
	    return numOfUpdatedRows == 1;
	}
	
	// Update health status space in data base
	public static boolean updateHealthStatusSpaceInDataBase(String healthStatus, int healthStatusNewSpace) throws Exception {
		int numOfUpdatedRows = 0;
		String stringUpdate = "UPDATE " + DataBaseCommands.NAME_OF_HEALTH_STATUS_TABLE + " " +
				"SET health_status_space = " + healthStatusNewSpace + " " + 
				"WHERE health_status_name LIKE '" + healthStatus + "'";
		Statement stmt = null;
		
		if(healthStatusNewSpace < 0) // Health status space is less than 0, so not update
			return false;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfUpdatedRows = stmt.executeUpdate(stringUpdate); // Execute UPDATE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to UPDATE health status space!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	       
	    return numOfUpdatedRows == 1;
	}
	
	// Remove health status from data base
	public static boolean removeHealthStatusFromDataBase(String healthStatusNameToRemove) throws Exception {
		int numOfDeletedRows = 0;
		String stringDelete = "DELETE FROM " + DataBaseCommands.NAME_OF_HEALTH_STATUS_TABLE + " " +
				"WHERE health_status_name LIKE '" + healthStatusNameToRemove + "'";
		Statement stmt = null;
		
		if(healthStatusNameToRemove == null) // Health status name is null, so not remove
			return false;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfDeletedRows = stmt.executeUpdate(stringDelete); // Execute DELETE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to DELETE health status!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	       
	    return numOfDeletedRows == 1;
	}
	
	// Remove all health status from data base
	public static boolean removeAllHealthStatusFromDataBase() throws Exception {	
		int numOfDeletedRows = 0;
		String stringDelete = "DELETE FROM " + DataBaseCommands.NAME_OF_HEALTH_STATUS_TABLE;
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfDeletedRows = stmt.executeUpdate(stringDelete); // Execute DELETE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to DELETE all health statuses!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
		
		return numOfDeletedRows > 0;
	}
	
	// Get all health statuses names in data base
	public static Set<String> getAllHealthStatusesNamesInDataBase() throws Exception {
		Set<String> allHealthStatusesNamesAndSpaces = new HashSet<>();
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_HEALTH_STATUS_TABLE;
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) // Get the health status names and spaces that found
				allHealthStatusesNamesAndSpaces.add(rs.getString("health_status_name"));
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT all health statuses names!");
		}
	       
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	       
	    return allHealthStatusesNamesAndSpaces;
	}
	
	// Get space of health status in data base
	public static int getSpaceOfHealthStatusInDataBase(String healthStatusName) throws Exception {
		int numOfSpace = DataBaseCommands.HEALTH_STATUS_ID_NOT_FOUND_IN_DATA_BASE;
		
		if(healthStatusName == null) // Health status name is null, so not exist
			return DataBaseCommands.HEALTH_STATUS_ID_NOT_FOUND_IN_DATA_BASE;
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_HEALTH_STATUS_TABLE + " " +
				"WHERE health_status_name LIKE '" + healthStatusName + "'";
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) // Get the health status id that found
				numOfSpace = rs.getInt("health_status_space");
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT health status id by health status name!");
		}
	       
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
		
		return numOfSpace;
	}
}