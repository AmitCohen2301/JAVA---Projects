package model;

import java.io.Serializable;

public class Citizen implements Serializable{
	protected String name;
	protected String idNum;
	protected int yearOfBirth;
	protected RegBallot ballotVote;
	protected boolean isInsulation;
	protected Party belongParty;
	protected boolean wearProtection;
	protected int daysInInsulation;
	
	//Constructors
	public Citizen(String name, String idNum, int yearOfBirth, boolean isInsulation) {
		this(name, idNum, yearOfBirth, null, isInsulation, 0, null, false);
	}
	public Citizen(String name, String idNum, int yearOfBirth, boolean isInsulation, int daysInInsulation, boolean wearProtection) {
		this(name, idNum, yearOfBirth, null, isInsulation,daysInInsulation, null, wearProtection);
	}
	public Citizen(String name, String idNum, int yearOfBirth, boolean isInsulation, Party belongParty) {
		this(name, idNum, yearOfBirth, null, isInsulation, 0, belongParty, false);
	}
	public Citizen(String name, String idNum, int yearOfBirth, boolean isInsulation, int daysInInsulation, Party belongParty, boolean wearProtection) {
		this(name, idNum, yearOfBirth, null, isInsulation, daysInInsulation, belongParty, wearProtection);
	}
	public Citizen(String name, String idNum, int yearOfBirth, RegBallot ballotVote, boolean isInsulation, int daysInInsulation, Party belongParty, boolean wearProtection) {
		setName(name);
		setIdNum(idNum);
		setYearOfBirth(yearOfBirth);
		setBallotVote(ballotVote);
		setIsInsulation(isInsulation, daysInInsulation);
		setBelongParty(belongParty);
		setWearProtection(wearProtection);
	}
	
	//Sets
	private boolean setName(String name) {
		this.name = name;
		return true;
	}
	private boolean setIdNum(String idNum) {
		if ((idNum.isEmpty()) || (idNum.length() == 9)) {//Check if the length of the id is 9 
			this .idNum = idNum;
			return true;
		}
		return false;
	}
	private boolean setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
		return true;
	}
	public boolean setBallotVote(RegBallot ballotVote) {
		this.ballotVote = ballotVote;
		return true;
	}
	public boolean setIsInsulation(boolean isInsulation, int daysInInsulation) {
		this.isInsulation = isInsulation;
		setDaysInInsulation(daysInInsulation);
		return true;
	}
	public boolean setDaysInInsulation(int daysInInsulation) {
		this.daysInInsulation = daysInInsulation;
		return true;
	}
	public boolean setBelongParty (Party belongParty) {
		if(this.belongParty != null)//belong to party
			return false;
		this.belongParty = belongParty;
		return true;
	}
	public boolean setWearProtection(boolean wearProtection) {
		this.wearProtection = wearProtection;
		return true;
	}
	
	//gets
	public String getName() {
		return name;
	}
	public String getIdNum() {
		return idNum;
	}
	public boolean isInsulation() {
		return isInsulation;
	}
	public int getYearOfBirth() {
		return yearOfBirth;
	}
	public boolean getWearProtection() {
		return wearProtection;
	}
	public RegBallot getBallotVote() {
		return ballotVote;
	}
	public Party getBelongParty() {
		return belongParty;
	}
	public Boolean getInInsulation() {
		return isInsulation;
	}
	
	//equals
	@Override
	public boolean equals (Object citizen) {
		if (idNum.equals(((Citizen)citizen).getIdNum()))//check if they have the same id number
			return true;
		return false;
	}
	
	//toString
	public String toString() {
		return "Citizen name: " + name + ", Id No.: " + idNum + ", Year Of Birth: " + yearOfBirth + 
				", Is" + (isInsulation ? " in insulation " + daysInInsulation + " day's" : "n't in insulation") + 
				", " + (wearProtection ? "Wear protection" : "Don't wear protection") +
				", Vote in ballot No.: " + ballotVote.getBallotNum() + 
				", Is" + (belongParty != null ? " belong to party: " + belongParty.getName() : "n't belong to party") + "\n";
	}
}
