//Name: Amit Cohen
//ID: 315876011

import java.io.Serializable;

public class Human implements Serializable {
	protected String firstName;
	protected String lastName;
	
	//Constructors
	public Human(String firstName, String lastName) throws Exception {
		setFirstName(firstName);
		setLastName(lastName);
	}
	
	//Sets
	private void setFirstName(String firstName) throws Exception {
		if(CheckInputs.checkName(firstName)) {
			firstName.toLowerCase();
			firstName.replaceFirst(Character.toString(firstName.charAt(0)), Character.toString(firstName.charAt(0) - 32));
			this.firstName = firstName;
		}
		else
			throw new Exception("ERROR! Wrong First Name Entered!");
	}
	private void setLastName(String lastName) throws Exception {
		if(CheckInputs.checkName(lastName))
			this.lastName = lastName;
		else
			throw new Exception("ERROR! Wrong Last Name Entered!");
	}
	
	//Gets
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
}
