//name: AmitCohen
//ID: 315876011
//H.W Number: 3 exercise 2
package Hotel;

public class Room {
	private int numOfBeds;
	private Guest[] allGuest;
	//Builder
	public Room() {
		numOfBeds = (int)((Math.random() * 4) + 1);
	}
	//Gets and Set
	public int getNumOfBeds() {
		return numOfBeds;
	}
	public Guest[] getAllGuestsInRoom() {
		return allGuest;
	}
	public void setGuests(Guest[] allGuest) {
		this.allGuest = allGuest;
	}
	//To string
	public String toString() {
		int i;
		StringBuffer str = new StringBuffer();
		if (allGuest == null)//Check if there are guests in the room
			return "Empty \n";
		for (i = 0; (i < allGuest.length) && (allGuest[i] != null); i++) {
			str.append("\t \t Guest No." + (i + 1) + ": " + allGuest[i].toString());
		}		
		return "There are " + i + " guest" + (i == 1 ? ": \n" : "'s: \n") + str.toString();
	}
}
