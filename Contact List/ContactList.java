//Name: Amit Cohen
//ID: 315876011

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ContactList implements SortByName, ManageContacts {
	private static int nextListNum = 1;
	private int listNum;
	private String listName;
	private ArrayList<Contact> listOfContacts;
	private HashMap<String, Contact> mapOfContacts;
	
	//Constructors
	public ContactList(String listName, ArrayList<Contact> listOfContacts, HashMap<String, Contact> mapOfContacts) {
		setListName(listName);
		setListOfContacts(listOfContacts);
		setMapOfContacts(mapOfContacts);
		this.listNum = nextListNum;
		nextListNum++;
	}
	
	//Sets
	private void setListName(String listName) {
		this.listName = listName;
	}
	private void setListOfContacts(ArrayList<Contact> listOfContacts) {
		this.listOfContacts = new ArrayList<Contact>();
		if(listOfContacts != null)
			this.listOfContacts = listOfContacts;
	}
	private void setMapOfContacts(HashMap<String, Contact> mapOfContacts) {
		this.mapOfContacts = new HashMap<String, Contact>();
		if(mapOfContacts != null)
			this.mapOfContacts = mapOfContacts;
	}
	
	//Gets
	public int getListNum() {
		return listNum;
	}
	public String getListName() {
		return listName;
	}
	public ArrayList<Contact> getListOfContacts() {
		return listOfContacts;
	}
	public HashMap<String, Contact> getMapOfContacts() {
		return mapOfContacts;
	}
	
	//Functions
	
	//Sort by name
	@Override
	public void sortByName() {
		for(int i = listOfContacts.size() - 1; i > 0; i--) {
			for(int j = 0; j < i; j++) {
				String firstName1 = listOfContacts.get(j).getFirstName();
				String firstName2 = listOfContacts.get(j + 1).getFirstName();
				if(firstName1.compareTo(firstName2) > 0) { //Need to replace
					Collections.swap(listOfContacts, i, j);
				}
				else {
					if(firstName1.compareTo(firstName2) == 0) { //Same first name so need to check according to last name
						String lastName1 = listOfContacts.get(j).getLastName();
						String lastName2 = listOfContacts.get(j + 1).getLastName();
						if(lastName1.compareTo(lastName2) > 0) { //Need to replace
							Collections.swap(listOfContacts, i, j);
						}
					}
				}
			}
		}
	}
	
	//Add contact
	@Override
	public String addContant(Contact contactToAdd) {
		boolean exist = false;
		String firstNameOfContactToAdd = contactToAdd.getFirstName();
		String lastNameOfContactToAdd = contactToAdd.getLastName();
		String personalPhoneOfContactToAdd = contactToAdd.getPersonalPhone();
		for(int i = 0; i < listOfContacts.size(); i++) { //Check if exist
			String firstNameOfContact = listOfContacts.get(i).getFirstName();
			String lastNameOfContact = listOfContacts.get(i).getLastName();
			String personalPhoneOfContact = listOfContacts.get(i).getPersonalPhone();
			if((firstNameOfContact.compareTo(firstNameOfContactToAdd) == 0) && (lastNameOfContact.compareTo(lastNameOfContactToAdd) == 0)) { //Same names
				exist = true;
				listOfContacts.set(i, contactToAdd);
				mapOfContacts.remove(personalPhoneOfContact);
				break;
			}
			else {
				if(personalPhoneOfContact.compareTo(personalPhoneOfContactToAdd) == 0) { //Same phone
					exist = true;
					listOfContacts.set(i, contactToAdd);
					break;
				}
			}
		}
		if(!exist) {
			listOfContacts.add(contactToAdd); //Not exist
		}
		sortByName();
		mapOfContacts.put(personalPhoneOfContactToAdd, contactToAdd);
		return "Contact Added or Updated!";
	}
	
	//Delete contact
	@Override
	public String deleteContact(String firstNameOfContactToDelete, String lastNameOfContactToDelete) {
		for(int i = 0; i < listOfContacts.size(); i++) {
			if(listOfContacts.get(i).getFirstName().compareTo(firstNameOfContactToDelete) == 0) { //Same first name
				if(listOfContacts.get(i).getLastName().compareTo(lastNameOfContactToDelete) == 0) { //Same last name
					mapOfContacts.remove(listOfContacts.get(i).getPersonalPhone()); //Remove from map
					listOfContacts.remove(i); //Delete from list
					return "Contact Deleted!";
				}
			}
		}
		return "Contact Not Found!";
	}
	
	//Search contact by name
	@Override
	public void searchContactByName(String firstNameOfContact, String lastNameOfContact) {
		for(int i =0; i < listOfContacts.size(); i++) {
			if((listOfContacts.get(i).getFirstName().compareTo(firstNameOfContact) == 0) && (listOfContacts.get(i).getLastName().compareTo(lastNameOfContact) == 0)) {
				System.out.println(listOfContacts.get(i).toString());
				return;
			}
		}
		System.out.println("Contact Not Found!");
	}
	
	//Search contact by phone
	@Override
	public void searchContactByPhone(String phoneNumber) {
		if(mapOfContacts.containsKey(phoneNumber)) {
			System.out.println(mapOfContacts.get(phoneNumber).toString());
		}
		else
			System.out.println("Mobile Not Found!");
	}
	
	//Print all contacts
	@Override
	public void printAllContacts() {
		if((listOfContacts == null) || (listOfContacts.size() == 0))
			System.out.println("There are no contacts");
		else {
			for(int i = 0; i < listOfContacts.size(); i++) {
				System.out.println((i + 1) + ")" + listOfContacts.get(i).toString());
			}
		}
	}
}
