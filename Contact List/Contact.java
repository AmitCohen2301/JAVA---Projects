//Name: Amit Cohen
//ID: 315876011

import java.io.Serializable;

public class Contact extends Human implements Serializable, Comparable<Contact> {
	private String homePhone;
	private String personalPhone;
	
	//Constructors
	public Contact(String firstName, String lastName, String homePhone, String personalPhone) throws Exception {
		super(firstName, lastName);
		setHomePhone(homePhone);
		setPersonalPhone(personalPhone);
	}
	
	//Sets
	private void setHomePhone(String homePhone) throws Exception {
		if(CheckInputs.checkHomePhone(homePhone)) {
			if(homePhone != null)
				this.homePhone = homePhone;
			else
				this.homePhone = " ";
		}
		else
			throw new Exception("ERROR! Wrong Home Number Entered!");
	}
	private void setPersonalPhone(String personalPhone) throws Exception {
		if(CheckInputs.checkPersonalPhone(personalPhone))
			this.personalPhone = personalPhone;
		else
			throw new Exception("ERROR! Wrong Mobile Number Entered!");
	}
	
	//Gets
	public String getHomePhone() {
		return homePhone;
	}
	public String getPersonalPhone() {
		return personalPhone;
	}
	
	//To string
	public String toString() {
		return super.getFirstName() + "\t" + super.getLastName() + "\t" + homePhone + "\t" + personalPhone;
	}
	
	//Compare to
	@Override
	public int compareTo(Contact contact) {
		if(personalPhone.equals(contact.getPersonalPhone()))
			return 0;
		else
			return -1;
	}
}
