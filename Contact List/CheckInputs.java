//Name: Amit Cohen
//ID: 315876011

public class CheckInputs {
	
	//Check name
	public static boolean checkName(String name) {
		if((name != null) && (name.length() > 1)) { //Name at least 2 chars
			for(int i = 0; i < name.length(); i++) { //Check if every char is a letter
				if((name.charAt(i) < 'A') || (name.charAt(i) > 'z')) //Not letter
					return false;
				if((name.charAt(i) > 'Z') && (name.charAt(i) < 'a')) //Not letter
					return false;
			}
			return true;
		}
		return false;
	}
	
	//Check home phone
	public static boolean checkHomePhone(String homePhone) {
		if((homePhone == null) || (homePhone.length() == 0)) //No home phone
			return true;
		if(homePhone.length() == 10) { //Check if length is OK - 10 chars
			if(homePhone.charAt(2) == '-') { //Check if the format is OK
				try { //Check if all chars are numbers
					Integer.parseInt(homePhone.substring(0, 2));
					Integer.parseInt(homePhone.substring(3));
					return true;
				}
				catch(Exception e) {
					return false;
				}
			}
		}
		return false;
	}
	
	//Check personal phone
	public static boolean checkPersonalPhone(String personalPhone) {
		if((personalPhone != null) && (personalPhone.length() == 11)) { //Check if length is OK - 11 chars
			if(personalPhone.charAt(3) == '-') { //Check if the format is OK
				try { //Check if all chars are numbers
					Integer.parseInt(personalPhone.substring(0, 3));
					Integer.parseInt(personalPhone.substring(4));
					return true;
				}
				catch(Exception e) {
					return false;
				}
			}
		}
		return false;
	}
}
