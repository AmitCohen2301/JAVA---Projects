//Name: Amit Cohen
//ID: 315876011

public interface ManageContacts {
	
	public String addContant(Contact contactToAdd); //Add contact
    public String deleteContact(String firstNameOfContactToDelete, String lastNameOfContactToDelete); //Delete contact
	public void searchContactByName(String firstNameOfContact, String lastNameOfContact); //Search contact by name
	public void searchContactByPhone(String phoneNumber);
	public void printAllContacts(); //Print all contacts
}
