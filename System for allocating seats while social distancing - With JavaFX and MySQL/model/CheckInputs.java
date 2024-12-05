package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

import database.DataBaseCommands;

public class CheckInputs {
	
	// Check name
	public static boolean checkName(String nameToCheck) {
		return Pattern.compile("^[A-Za-z](?=.{0,29}$)[A-Za-z]*(?:\\h+[A-Za-z]*)*$").matcher(nameToCheck).matches();
	}
	
	// Check email
	public static boolean checkEmail(String emailToCheck) {
		return Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(emailToCheck).matches();
	}
	
	// Check phone
	public static boolean checkPhone(String phoneNumToCheck) {
		return Pattern.compile("\\d{3}-\\d{7}").matcher(phoneNumToCheck).matches();
	}
	
	// Check permission
	public static boolean checkPermission(String permissionToCheck) {
		return (permissionToCheck.equals(DataBaseCommands.ADMIN_PERMISSION_NAME_IN_DATA_BASE) || 
				permissionToCheck.equals(DataBaseCommands.MANAGE_EVENTS_PERMISSION_NAME_IN_DATA_BASE) || 
				permissionToCheck.equals(DataBaseCommands.ORDER_EVENTS_PERMISSION_NAME_IN_DATA_BASE));
	}
	
	// Check event name
	public static boolean checkEventName(String eventNameToCheck) {
		return Pattern.compile("^[A-Za-z0-9](?=.{0,29}$)[A-Za-z0-9]*(?:\\h+[A-Za-z0-9]*)*$").matcher(eventNameToCheck).matches();
	}
	
	// Check neighborhood num
	public static boolean checkNeighborhoodNum(int neighborhoodNum) {
		if (neighborhoodNum <= 0)
			return false;
		return true;
	}
	
	// Check future and valid date
	public static boolean checkFutureAndValidDate(int year, int month, int day, int hours, int minutes) {
		// Create a calendar and set the values
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.set(year, month - 1, day, hours, minutes, 0);
        
        // Check that the date is in the future
        Calendar current = Calendar.getInstance();
        if (calendar.before(current))
            return false; // Date is in the past
		
        // Check valid date
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		try {
            sdf.parse(day + "/" + month + "/" + year);
        } catch (ParseException e) {
            return false;
        }
        return true;
	}
}