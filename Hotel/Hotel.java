//name: AmitCohen
//ID: 315876011
//H.W Number: 3 exercise 2
package Hotel;

public class Hotel {
	private int numOfUsedRooms;
	private Room[][] allRooms;
	//Builder
	public Hotel(int numOfFloors, int numOfRoomsInFloor) {
		numOfUsedRooms = 0;
		allRooms = new Room[numOfFloors][numOfRoomsInFloor];
		for (int floorNum = 0; floorNum < allRooms.length; floorNum++) {//Creating the rooms
			for (int roomNum = 0; roomNum < allRooms[floorNum].length; roomNum++) {
				allRooms [floorNum][roomNum] = new Room();
			}
		}
	}
	//Add guests
	public int addGuest(Guest[] guestsToPut) {
		for (int floorNum = 0; floorNum < allRooms.length; floorNum++) {//Check all the rooms and floors
			for (int roomNum = 0; roomNum < allRooms[floorNum].length; roomNum++) {
				if (allRooms[floorNum][roomNum].getNumOfBeds() == guestsToPut.length){//Not enough space in the room
					if (allRooms[floorNum][roomNum].getAllGuestsInRoom() == null){
						allRooms[floorNum][roomNum].setGuests(guestsToPut); 
						numOfUsedRooms++;
						return ((floorNum + 1) * 100) + (roomNum + 1);
					}	
				}
			}
		}
		return -1;
	}
	//Find guest by ID
	public int findGuestByPassportNum(int passportNum) {
		for (int floorNum = 0; (floorNum < allRooms.length) && (numOfUsedRooms != 0); floorNum++) {
			for (int roomNum = 0; roomNum < allRooms[floorNum].length; roomNum++) {
				if (allRooms[floorNum][roomNum].getAllGuestsInRoom() != null) {//Room full
					Guest[] listOfGuestsInRooom = allRooms[floorNum][roomNum].getAllGuestsInRoom();//List of guests in the room
					for(int guestNum = 0; guestNum < listOfGuestsInRooom.length; guestNum++) {
						if(listOfGuestsInRooom[guestNum].getPassportNumber() == passportNum)
							return ((floorNum + 1) * 100) + (roomNum + 1);
					}
				}
			}
		}
		return -1;
	}
	//Floor with the most available rooms
	public int floorWithTheMostAvailableRooms() {
		int counterOfFreeRooms = 0;
		int counterOfMostFreeRooms = 0;
		int numOfFloorWithTheMostFreeRoom = 0;
		for (int floorNum = 0; floorNum < allRooms.length; floorNum++) {
			for (int roomNum = 0; roomNum < allRooms[floorNum].length; roomNum++) {//Check how many free rooms in the floor
				if (allRooms[floorNum][roomNum].getAllGuestsInRoom() == null)
					counterOfFreeRooms++;
			}
			if (floorNum == 0) {//The first floor to check
				counterOfMostFreeRooms = counterOfFreeRooms;
				counterOfFreeRooms = 0;
				numOfFloorWithTheMostFreeRoom = floorNum;
			}
			else {
				if (counterOfFreeRooms > counterOfMostFreeRooms) {
					counterOfMostFreeRooms = counterOfFreeRooms;
					counterOfFreeRooms = 0;
					numOfFloorWithTheMostFreeRoom = floorNum;
				}
			}
		}
		return (numOfFloorWithTheMostFreeRoom + 1);
	}
	//To string
	public String toString() {
		StringBuffer str = new StringBuffer("The hotel has: " + allRooms.length + " floor" + (allRooms.length == 1 ? " " : "'s ")
				+ "and each floor has " + allRooms[0].length + " room" + (allRooms[0].length == 1 ? ": \n" : "'s: \n"));
		for (int floorNum = 0; floorNum < allRooms.length; floorNum++) {
			str.append("Floor No." + (floorNum + 1) + ": \n");
			for (int roomNum = 0; roomNum < allRooms[floorNum].length; roomNum++) {
				str.append("\t Room No." + (roomNum + 1) + ": " + allRooms[floorNum][roomNum].toString());
			}
		}
		return str.toString();
	}
}
