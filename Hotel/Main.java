package Hotel;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Hotel hotel;
		int choiceOption, numOfFloors, numOfRooms;
		System.out.println("Enter the number of floors the hotel has");
		numOfFloors = s.nextInt();
		System.out.println("Enter the number of rooms every floor has");
		numOfRooms = s.nextInt();
		hotel = new Hotel(numOfFloors, numOfRooms);
		do {
			System.out.println("Enter which option you want");
			System.out.println("1- Add guest");
			System.out.println("2- Find guest by Passport number");
			System.out.println("3- Show hotel capacity details");
			System.out.println("4- Show witch floor has the most number of free rooms");
			System.out.println("5- Exit");
			choiceOption = s.nextInt();
			switch(choiceOption) {
			case 1:
				System.out.println("How many guests you want to add? (1-4)");
				int numOfGuests = s.nextInt();
				String nameOfGuest;
				int passportId;
				Guest[] listOfGuest = new Guest[numOfGuests];
				for (int i = 0; i < listOfGuest.length; i++) {
					System.out.println("Enter the name of guest No." + (i+1));
					nameOfGuest = s.next();
					System.out.println("Enter the passport ID of guest No." + (i+1));
					passportId = s.nextInt();
					listOfGuest[i] = new Guest(nameOfGuest, passportId);
				}
				int roomNo = hotel.addGuest(listOfGuest);
				if (roomNo == -1)
					System.out.println("We are sorry, there is no space for the list of the guest");
				else
					System.out.println("The room is: " + roomNo);
				break;
			case 2:
				System.out.println("Enter the passport ID of the guest");
				int roomNumOfTheGuest = hotel.findGuestByPassportNum(s.nextInt());
				if (roomNumOfTheGuest == -1)
					System.out.println("The guest is not in the hotel");
				else
					System.out.println("The guest is in room: " + roomNumOfTheGuest);
				break;
			case 3:
				System.out.println(hotel.toString());
				break;
			case 4:
				System.out.println("The floor with the most number of free rooms is floor No.: " + hotel.floorWithTheMostAvailableRooms());
				break;
			case 5:
				System.out.println("Bye Bye have a good day :)");
				break;
			default: 
				System.out.println("You entered a not valid choice");
			}
		} while (choiceOption != 5);
	}

}
