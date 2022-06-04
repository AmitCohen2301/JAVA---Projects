package model;

import java.io.Serializable;

public class Soldier extends Citizen implements Serializable{
	private boolean carryWeapon;
	
	//Constructors
	public Soldier(String name, String idNum, int yearOfBirth, boolean isInsulation, int daysInInsulation, boolean wearProtection, boolean carryWeapon) {
		this(name, idNum, yearOfBirth, null, isInsulation, daysInInsulation, null, wearProtection, carryWeapon);
	}
	public Soldier(String name, String idNum, int yearOfBirth, RegBallot ballotVote, boolean isInsulation, int daysInInsulation, Party belongParty, boolean wearProtection, boolean carryWeapon) {
		super(name, idNum, yearOfBirth, null, isInsulation, daysInInsulation, belongParty, wearProtection);
		setCarryWeapon(carryWeapon);
	}
	
	//Set
	protected boolean setCarryWeapon(boolean carryWeapon) {
		this.carryWeapon = carryWeapon;
		return true;
	}
	
	//Get
	public boolean getCarryWeapon() {
		return carryWeapon;
	}
	
	//Carry weapon
	public boolean carryWeapon(boolean carryWeapon) {
		return setCarryWeapon(carryWeapon);
	}
	
	@Override
	//toString
	public String toString() {
		return "Citizen(soldier) name: " + name + ", Id No.: " + idNum + ", Year Of Birth: " + yearOfBirth + 
				", " + (carryWeapon ? "Carry weapon" : "Don't carry weapon") + 
				", Is" + (isInsulation ? " in insulation " + daysInInsulation + " day's" : "n't in insulation") + 
				", " + (wearProtection ? "Wear protection" : "Don't wear protection") +
				", Vote in ballot No.: " + ballotVote.getBallotNum() + 
				", Is" + (belongParty != null ? " belong to party: " + belongParty.getName() : "n't belong to party") + "\n";
	}
}
