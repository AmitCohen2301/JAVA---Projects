package database;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import model.User;

public class DataBaseCommandsForUsers {
	
	// Search user id by user details in data base
	public static int searchUserIdByUserDetailsInDataBase(User userToSearch) throws Exception {
		int userId = DataBaseCommands.USER_ID_NOT_FOUND_IN_DATA_BASE;
		
		if(userToSearch == null) // User is null, so not exist
			return DataBaseCommands.USER_ID_NOT_FOUND_IN_DATA_BASE;
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_USERS_TABLE + " " +
				"WHERE user_email LIKE '" + userToSearch.getUserEmail() + "'";
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) // Get the user id that found
				userId = rs.getInt("user_id");
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT user id by user details!");
		}
	       
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	       
	    return userId;
	}
	
	// Search user id by user phone in data base
	public static int searchUserIdByUserPhoneInDataBase(User userToSearch) throws Exception {
		int userId = DataBaseCommands.USER_ID_NOT_FOUND_IN_DATA_BASE;
		
		if(userToSearch == null) // User is null, so not exist
			return DataBaseCommands.USER_ID_NOT_FOUND_IN_DATA_BASE;
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_USERS_TABLE + " " +
				"WHERE user_phone_number LIKE '" + userToSearch.getUserPhoneNumber() + "'";
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) // Get the user id that found
				userId = rs.getInt("user_id");
			rs.close(); // Close ResultSet
		       stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT user id by user phone!");
		}
	       
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	       
	    return userId;
	}
		
	// Search users ids with the permission in the data base
	public static Set<Integer> searchUsersIdsWithThePermissionInTheDataBase(String permissionName) throws Exception {
		Set<Integer> idsOfUsers = new HashSet<Integer>();
		
		if(permissionName == null) // Permission name is null, so there are no users with that permission
			return idsOfUsers;
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_USERS_TABLE + " " +
				"WHERE user_permission = " + DataBaseCommandsForPermissions.searchPermissionIdByPermissionNameInDataBase(permissionName);
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) // Get the user id that found
				idsOfUsers.add(rs.getInt("user_id"));
			rs.close(); // Close ResultSet
		       stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT users id by permission!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
		
		return idsOfUsers;
	}
		
	// Search user by user id in data base
	public static User searchUserByUserIdInDataBase(int userId) throws Exception {
		User foundUser = null;
		int permissionIdOfUser = DataBaseCommands.PERMISSION_ID_NOT_FOUND_IN_DATA_BASE;
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_USERS_TABLE + " " +
				"WHERE user_id = " + userId;
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) { // Get the user that found
				foundUser = new User(rs.getString("user_name"), rs.getString("user_email"), rs.getString("user_phone_number"));
				permissionIdOfUser = rs.getInt("user_permission");
			}
			rs.close(); // Close ResultSet
	        stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT user by id!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection 
		
		if(foundUser != null) // Found user in data base, so put the permissions
			foundUser.setUserPermission(DataBaseCommandsForPermissions.searchPermissionNameByPermissionIdInDataBase(permissionIdOfUser));
		
		return foundUser;
	}
		
	// Search user by email in data base
	public static User searchUserByEmailInDataBase(String email) throws Exception {
		User foundUser = null;
		int permissionIdOfUser = DataBaseCommands.PERMISSION_ID_NOT_FOUND_IN_DATA_BASE;
		
		if(email == null) // Email is null, so not exist
			return null;
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_USERS_TABLE + " " +
				"WHERE user_email LIKE '" + email + "'";
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) { // Get the user that found
				foundUser = new User(rs.getString("user_name"), rs.getString("user_email"), rs.getString("user_phone_number"));
				permissionIdOfUser = rs.getInt("user_permission");
			}
			rs.close(); // Close ResultSet
		    stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT user by email!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection 
		
		if(foundUser != null) // Found user in data base, so put the permissions
			foundUser.setUserPermission(DataBaseCommandsForPermissions.searchPermissionNameByPermissionIdInDataBase(permissionIdOfUser));
		
		return foundUser;
	}
	
	// Search user by phone in data base
	public static User searchUserByPhoneInDataBase(String phone) throws Exception {
		User foundUser = null;
		int permissionIdOfUser = DataBaseCommands.PERMISSION_ID_NOT_FOUND_IN_DATA_BASE;
		
		if(phone == null) // Phone is null, so not exist
			return null;
		
		String stringFilter = "SELECT * FROM " + DataBaseCommands.NAME_OF_USERS_TABLE + " " +
				"WHERE user_phone_number  LIKE '" + phone + "'";
		Statement stmt = null;
		ResultSet rs = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			rs = stmt.executeQuery(stringFilter); // Execute SELECT
			while (rs.next()) { // Get the user that found
				foundUser = new User(rs.getString("user_name"), rs.getString("user_email"), rs.getString("user_phone_number"));
				permissionIdOfUser = rs.getInt("user_permission");
			}
			rs.close(); // Close ResultSet
		    stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to SELECT user by phone!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection 
		
		if(foundUser != null) // Found user in data base, so put the permissions
			foundUser.setUserPermission(DataBaseCommandsForPermissions.searchPermissionNameByPermissionIdInDataBase(permissionIdOfUser));
		
		return foundUser;
	}
		
	// Add user to data base
	public static boolean addUserToDataBase(User userToAdd) throws Exception {
		int numOfNewRows = 0;
		
		if(userToAdd == null) // User is null, so not add
			return false;
		if(DataBaseCommands.isEmailExist(userToAdd.getUserEmail())) // Email is already exist, so not add
			return false;
		if(DataBaseCommands.isPhoneExist(userToAdd.getUserPhoneNumber())) // Phone is already exist, so not add
			return false;
		if(DataBaseCommandsForPermissions.searchPermissionIdByPermissionNameInDataBase(userToAdd.getUserPermission()) == 
				DataBaseCommands.PERMISSION_ID_NOT_FOUND_IN_DATA_BASE) // Permission is not exist, so not add
			return false;
		
		String stringInsert = "INSERT INTO " + DataBaseCommands.NAME_OF_USERS_TABLE + " (user_name, user_email, user_phone_number, user_permission) " +
				"VALUES ('" + userToAdd.getUserName() + "', " +
				"'"+ userToAdd.getUserEmail() +"', " +
				"'" + userToAdd.getUserPhoneNumber() + "', " +
				DataBaseCommandsForPermissions.searchPermissionIdByPermissionNameInDataBase(userToAdd.getUserPermission()) + ")";
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfNewRows = stmt.executeUpdate(stringInsert); // Execute INSERT
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to INSERT new user!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
		
	    return numOfNewRows == 1;
	}
		
	// Update user in data base
	public static boolean updateUserInDataBase(User updatedUser, int userIdToUpdate) throws Exception {
		int numOfUpdatedRows = 0;
		int userIdByDetails = searchUserIdByUserDetailsInDataBase(updatedUser);
		int userIdByPhone = searchUserIdByUserPhoneInDataBase(updatedUser);
		
		if(updatedUser == null) // User is null, so not update
			return false;
		
		// Email is already exist and not the user to update, so not update
		if((DataBaseCommands.isEmailExist(updatedUser.getUserEmail())) && (userIdByDetails != userIdToUpdate))
			return false;
		
		// Phone is already exist and not the user to update, so not update
		if((DataBaseCommands.isPhoneExist(updatedUser.getUserPhoneNumber())) && (userIdByPhone != userIdToUpdate))
			return false;
		
		String stringUpdate = "UPDATE " + DataBaseCommands.NAME_OF_USERS_TABLE + " " +
				"SET user_name = '" + updatedUser.getUserName() + "', " +
				"user_email = '" + updatedUser.getUserEmail() + "', " +
				"user_phone_number = '" + updatedUser.getUserPhoneNumber() + "' " +
				"WHERE user_id = " + userIdToUpdate;
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfUpdatedRows = stmt.executeUpdate(stringUpdate); // Execute UPDATE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to UPDATE user!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return numOfUpdatedRows == 1;
	}
		
	// Update user permissions in data base
	public static boolean updateUserPermissionsInDataBase(User userToUpdate, int permissionsIdToPut) throws Exception {
		int numOfUpdatedRows = 0;
		
		if(userToUpdate == null) // User is null, so not update
			return false;
		if(DataBaseCommandsForPermissions.searchPermissionNameByPermissionIdInDataBase(permissionsIdToPut) == null) // Permission id is not exist, so not update
			return false;
		
		String stringUpdate = "UPDATE " + DataBaseCommands.NAME_OF_USERS_TABLE + " " +
				"SET user_permission = " + permissionsIdToPut + " " +
				"WHERE user_email LIKE '" + userToUpdate.getUserEmail() + "'";
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfUpdatedRows = stmt.executeUpdate(stringUpdate); // Execute UPDATE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to UPDATE user permissions!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return numOfUpdatedRows == 1;
	}
		
	// Remove user from data base
	public static boolean removeUserFromDataBase(User userToRemove) throws Exception {
		int numOfDeletedRows = 0;
		
		if(userToRemove == null) // User is null, so not remove
			return false;
		
		String stringDelete = "DELETE FROM " + DataBaseCommands.NAME_OF_USERS_TABLE + " " +
				"WHERE user_email LIKE '" + userToRemove.getUserEmail() + "'";
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfDeletedRows = stmt.executeUpdate(stringDelete); // Execute DELETE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to DELETE user!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
	    
	    return numOfDeletedRows == 1;
	}
		
	// Remove all users from data base
	public static boolean removeAllUsersFromDataBase() throws Exception {
		int numOfDeletedRows = 0;
		String stringDelete = "DELETE FROM " + DataBaseCommands.NAME_OF_USERS_TABLE;
		Statement stmt = null;
		
		DataBaseCommands.makeConnectionToMySQL(); // Make connection
		
		try {
			stmt = DataBaseCommands.con.createStatement(); // Make statement
			numOfDeletedRows = stmt.executeUpdate(stringDelete); // Execute DELETE
			stmt.close(); // Close statement
		}
		catch(Exception e) {
			throw new Exception("Failed to DELETE all users!");
		}
		
		DataBaseCommands.closeConnectionToMySQL(); // Close connection
		
		return numOfDeletedRows > 0;
	}
}