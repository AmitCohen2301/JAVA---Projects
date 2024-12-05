package model;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Objects;

import database.DataBaseCommandsForPermissions;

public class User {
	
	//public static enum UserPermissions {ADMIN, MANAGE_EVENTS, ORDER_EVENTS};
	//public static String userPermissionsInString[] = { "ADMIN", "MANAGE_EVENTS", "ORDER_EVENTS" };
	
	private String userName; // The name of the user
	private String userEmail; // The email of the user
	private String userPhoneNumber; // The phone of the user
	private String userPermission; // The permissions of the user
	
	// Constructor
	public User(String userName, String userEmail, String userPhoneNumber) throws Exception {
		if(!setUserName(userName))
			throw new Exception("The name of the user is not valid! \nIt should begin with letter, and contain only letters and spaces!");
		if(!setUserEmail(userEmail))
			throw new Exception("The email of the user is not valid!");
		if(!setUserPhoneNumber(userPhoneNumber))
			throw new Exception("The phone number of the user is not valid! \nIt should be in format XXX-XXXXXXX!");
		userPermission = "ORDER_EVENTS";
	}

	public User(String userName, String userEmail, String userPhoneNumber, String userPermission) throws Exception {
		if(!setUserName(userName))
			throw new Exception("The name of the user is not valid! \nIt should begin with letter, and contain only letters and spaces!");
		if(!setUserEmail(userEmail))
			throw new Exception("The email of the user is not valid!");
		if(!setUserPhoneNumber(userPhoneNumber))
			throw new Exception("The phone number of the user is not valid! \nIt should be in format XXX-XXXXXXX!");
		if(!setUserPermission(userPermission))
			throw new Exception("The permissions of the user is not valid!");
	}
	
	public User(User otherUser) {
		setUserName(otherUser.userName);
		setUserEmail(otherUser.userEmail);
		setUserPhoneNumber(otherUser.userPhoneNumber);
		setUserPermission(otherUser.userPermission);
	}
	
	// Gets
	public String getUserName() {
		return userName;
	}

	public String getUserEmail() {
		return userEmail;
	}
	
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	
	public String getUserPermission() {
		return userPermission;
	}
	
	// Sets
	public boolean setUserName(String userName) {
		if(!CheckInputs.checkName(userName))
			return false;
		
		this.userName = userName;
		return true;
	}

	public boolean setUserEmail(String userEmail) {
		if(!CheckInputs.checkEmail(userEmail))
			return false;
		
		this.userEmail = userEmail;
		return true;
	}

	public boolean setUserPhoneNumber(String userPhoneNumber) {
		if(!CheckInputs.checkPhone(userPhoneNumber))
			return false;
		
		this.userPhoneNumber = userPhoneNumber;
		return true;
	}

	public boolean setUserPermission(String userPermission) {
		if(!CheckInputs.checkPermission(userPermission))
			return false;
		this.userPermission = userPermission;
		return true;
	}
	
	// Get all user permissions in id and string
	public static String getAllUserPermissionsInIdAndString() throws Exception {
		String str = "";
		Dictionary<Integer, String> permissionsIdsAndNames = DataBaseCommandsForPermissions.getAllPermissionsIdsAndNamesInDataBase();
		Enumeration<Integer> k = permissionsIdsAndNames.keys();
		
		while (k.hasMoreElements()) { // Move on all keys
            int key = k.nextElement();
            str += (key + " = " + permissionsIdsAndNames.get(key));
            if(k.hasMoreElements())
            	str += ", ";
        }
		
		return str;
	}
	
	// Equals
	@Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) 
        	return true;
        if ((otherObj == null) || (getClass() != otherObj.getClass())) 
        	return false;
        User otherUser = (User) otherObj;
        return (Objects.equals(userEmail, otherUser.userEmail));
    }

	// Hash code
    @Override
    public int hashCode() {
        return Objects.hash(userEmail);
    }
	
	// Print user
	public void printUser() {
		System.out.println("User: " + userName + ", Email: " + userEmail + ", Phone number: " + userPhoneNumber + ", User permission: " + userPermission);
	}
		
	// To string
	@Override
	public String toString() {
		return "User: " + userName + ", Email: " + userEmail + ", Phone number: " + userPhoneNumber + ", User permission: " + userPermission;
	}
}