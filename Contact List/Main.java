//Name: Amit Cohen
//ID: 315876011

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		ContactList contactList = new ContactList("Contact list", null, null); 
		Scanner s = new Scanner(System.in);
		char choiceOption;
		do {
			System.out.println("- - - - - - - - - - - - - - -");
			System.out.println("- - -  Contact Editor:  - - -");
			System.out.println("- - - - - - - - - - - - - - -");
			System.out.println("Choose your option:");
			System.out.println("1. Add Contact");
			System.out.println("2. Delete Contact");
			System.out.println("3. Search Contact By Full Name");
			System.out.println("4. Search Contact By Mobile");
			System.out.println("5. Print All Contacts");
			System.out.println("6. EXIT");
			System.out.println("- - - - - - - - - - - - - - -");
			System.out.print("Your Option:");
			choiceOption = s.nextLine().charAt(0);
			System.out.println();
			String firstName, lastName, homeNumber, personalPhone;
			switch(choiceOption) {
				case '1':
					System.out.println("Add Contact:");
					System.out.print("Enter First Name (2 chars at least): ");
					firstName = s.nextLine();
					System.out.print("Enter Last Name (2 chars at least): ");
					lastName = s.nextLine();
					System.out.print("Enter Home Number (Format: XX-XXXXXXX or leave empty): ");
					homeNumber = s.nextLine();
					System.out.print("Enter Mobile Number (Format: XXX-XXXXXXX): ");
					personalPhone = s.nextLine();
					System.out.println();
					try {
						System.out.println(contactList.addContant(new Contact(firstName, lastName, homeNumber, personalPhone)) + "\n");
					}
					catch(Exception e) {
						System.out.println(e.getMessage() + "\n");
					}
					break;
				case '2':
					System.out.println("Delete Contact:");
					System.out.print("Enter First Name: ");
					firstName = s.nextLine();
					System.out.print("Enter Last Name: ");
					lastName = s.nextLine();
					System.out.println();
					System.out.println(contactList.deleteContact(firstName, lastName) + "\n");
					break;
				case '3':
					System.out.println("Search Contact By Full Name:");
					System.out.print("Enter First Name: ");
					firstName = s.nextLine();
					System.out.print("Enter Last Name: ");
					lastName = s.nextLine();
					System.out.println();
					contactList.searchContactByName(firstName, lastName);
					System.out.println();
					break;
				case '4':
					System.out.println("Search Contact By Mobile:");
					System.out.print("Enter Mobile Number: ");
					personalPhone = s.nextLine();
					System.out.println();
					contactList.searchContactByPhone(personalPhone);
					System.out.println();
					break;
				case '5':
					contactList.printAllContacts();
					break;
				case '6':
					System.out.println("Bye bye have a good day :)");
					break;
				default:
					System.out.println("You entered in valid option");
					break;
			}
		} while(choiceOption != '6');
	}

}
