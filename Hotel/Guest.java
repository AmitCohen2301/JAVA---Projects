//name: AmitCohen
//ID: 315876011
//H.W Number: 3 exercise 2
package Hotel;

public class Guest {
	private String name;
	private int passportNumber;
	//Builder
	public Guest(String name, int passportNumber) {
		this.name = name;
		this.passportNumber = passportNumber;
	}
	public Guest(Guest guest) {
		name = guest.getName();
		passportNumber = guest.getPassportNumber();
	}
	//Gets
	public String getName() {
		return name;
	}
	public int getPassportNumber() {
		return passportNumber;
	}
	//To string
	public String toString() {
		return "Guest name: " + name + ", Passport number: " + passportNumber + "\n";
	}
}
