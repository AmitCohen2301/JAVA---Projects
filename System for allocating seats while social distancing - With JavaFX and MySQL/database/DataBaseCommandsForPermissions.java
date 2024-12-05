package database;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Dictionary;
import java.util.Hashtable;

public class DataBaseCommandsForPermissions {
	
	// Search permission id by permission name in data base
	public static int searchPermissionIdByPermissionNameInDataBase(String permissionName) throws Exception {
		int foundPermissionId = DataBaseCommands.PERMISSION_ID_NOT_FOUND_IN_DATA_BASE;
		
		if(permissionName == null) // Permission name is null, so not exist
			return DataBaseCommands.PERMISSION_ID_NOT_FOUND_IN_DATA_BASE;
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_PERMISSIONS_TABLE + " " +
				"WHERE permission_name LIKE '" + permissionName + "'";
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) // Get the permission id that found
	        	foundPermissionId = rs.getInt("permission_id");
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT permission id by permission name!");
		}
	       
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
		
		return foundPermissionId;
	}
		
	// Search permission name by permission id in data base
	public static String searchPermissionNameByPermissionIdInDataBase(int permissionId) throws Exception {
		String foundPermissionName = null;
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_PERMISSIONS_TABLE + " " +
				"WHERE permission_id = " + permissionId;
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) // Get the permission that found
				foundPermissionName = rs.getString("permission_name");
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT permission name by permission id!");
		}
	       
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	       
	    return foundPermissionName;
	}
		
	// Add permission to data base
	public static boolean addPermissionToDataBase(String permissionNameToAdd) throws Exception {
		int numOfNewRows = 0;
		
		if(permissionNameToAdd == null) // Permission name to add is null, so not add
			return false;
		
		// Permission name to add exist in data base, so not add
		if(searchPermissionIdByPermissionNameInDataBase(permissionNameToAdd) != DataBaseCommands.PERMISSION_ID_NOT_FOUND_IN_DATA_BASE)
			return false;
		
		String stringInsert = "INSERT INTO " + DataBaseCommands.NAME_OF_PERMISSIONS_TABLE + " (permission_name) " +
				"VALUES ('" + permissionNameToAdd + "')";
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfNewRows = stmt.executeUpdate(stringInsert); // Execute INSERT
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to INSERT new permission!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	       
	    return numOfNewRows == 1;
	}
	
	// Update permission name in data base
	public static boolean updatePermissionNameInDataBase(String permissionOldName, String permissionNewName) throws Exception {
		int numOfUpdatedRows = 0;
		String stringUpdate = "UPDATE " + DataBaseCommands.NAME_OF_PERMISSIONS_TABLE + " " +
				"SET permission_name = '" + permissionNewName + "' " + 
				"WHERE permission_name LIKE '" + permissionOldName + "'";
		Statement stmt = null;
		
		if(permissionNewName == null) // Permission new name is null, so not update
			return false;
		
		// Permission new name already exist in data base, so not update
		if(searchPermissionIdByPermissionNameInDataBase(permissionNewName) != DataBaseCommands.PERMISSION_ID_NOT_FOUND_IN_DATA_BASE)
			return false;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfUpdatedRows = stmt.executeUpdate(stringUpdate); // Execute UPDATE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to UPDATE permission!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	       
	    return numOfUpdatedRows == 1;
	}
	
	// Remove permission from data base
	public static boolean removePermissionFromDataBase(String permissionNameToRemove) throws Exception {
		int numOfDeletedRows = 0;
		String stringDelete = "DELETE FROM " + DataBaseCommands.NAME_OF_PERMISSIONS_TABLE + " " +
				"WHERE permission_name LIKE '" + permissionNameToRemove + "'";
		Statement stmt = null;
		
		if(permissionNameToRemove == null) // Permission name is null, so not remove
			return false;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfDeletedRows = stmt.executeUpdate(stringDelete); // Execute DELETE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to DELETE permission!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	       
	    return numOfDeletedRows == 1;
	}
	
	// Remove all permissions from data base
	public static boolean removeAllPermissionsFromDataBase() throws Exception {	
		int numOfDeletedRows = 0;
		String stringDelete = "DELETE FROM " + DataBaseCommands.NAME_OF_PERMISSIONS_TABLE;
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfDeletedRows = stmt.executeUpdate(stringDelete); // Execute DELETE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to DELETE all permissions!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
		
		return numOfDeletedRows > 0;
	}
	
	// Get all permissions ids and names in data base
	public static Dictionary<Integer, String> getAllPermissionsIdsAndNamesInDataBase() throws Exception {
		Dictionary<Integer, String> allPermissionsIdsAndNames = new Hashtable<>();
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_PERMISSIONS_TABLE;
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) // Get the permissions ids and names that found
				allPermissionsIdsAndNames.put(rs.getInt("permission_id"), rs.getString("permission_name"));
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT all permissions ids and names!");
		}
	       
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	       
	    return allPermissionsIdsAndNames;
	}
}