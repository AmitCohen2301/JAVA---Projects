package model;

import java.io.Serializable;
import java.util.ArrayList;

public class RegBallot implements Serializable {
	protected int ballotNum;
	protected static int numOfNextBallot = 1;
	protected String ballotAddress;
	protected ArrayList<Citizen> listOfVoters;
	protected double votePrecent;
	protected ArrayList<Integer> votersInEachParty;
	protected ArrayList<Party> listOfParties;
	protected int numOfVotersInBaloot;
	protected int numOfVotersThatVote;
	protected int oldNumOfVotersInBaloot;
	
	//Constructors
	public RegBallot(String ballotAddress) {
		this(numOfNextBallot, ballotAddress, null, 0, null, null);
	}
	public RegBallot(String ballotAddress, ArrayList<Party> listOfAllParties) {
		this(numOfNextBallot, ballotAddress, null, 0, null, listOfAllParties);
	}
	public RegBallot(String ballotAddress, ArrayList<Citizen> listOfVoters, ArrayList<Party> listOfParties) {
		this(numOfNextBallot, ballotAddress, listOfVoters, 0, null, listOfParties);
	}
	public RegBallot(int numOfNextBallot, String ballotAddress, ArrayList<Citizen> listOfVoters, double votePrecent, ArrayList<Integer> votersInEachParty, ArrayList<Party> listOfParties) {
		setBallotNum(numOfNextBallot);
		setBallotAddress(ballotAddress);
		setListOfVoters(listOfVoters);
		setVotePrecent(votePrecent);
		setListOfParties(listOfParties);
		setVotersInEachParty(votersInEachParty);
		numOfVotersInBaloot = 0;
		numOfVotersThatVote = 0;
	}
	
	//Sets
	protected void setNumOfNextBallot(int i) {
		numOfNextBallot = i;
	}
	protected boolean setBallotNum(int ballotNum) {
		this.ballotNum = ballotNum;
		numOfNextBallot++;
		return true;
	}
	protected boolean setBallotAddress(String ballotAddress) {
		this.ballotAddress = ballotAddress;
		return true;
	}
	public boolean setListOfVoters(ArrayList<Citizen> listOfVoters) {
		this.listOfVoters = new ArrayList<Citizen>();
		if(listOfVoters != null) {
			for(int i = 0; i < listOfVoters.size(); i++)
				this.listOfVoters.add(listOfVoters.get(i));
		}
		return true;
	}
	public boolean setVotePrecent(double votePrecent) {
		this.votePrecent = votePrecent;
		return true;
	}
	public boolean setVotersInEachParty(ArrayList<Integer> votersInEachParty) {
		this.votersInEachParty = new ArrayList<Integer>();
		if(votersInEachParty == null)//List doesn't exist
			return true;
		for(int i = 0; i < votersInEachParty.size(); i++)//copy the votes
			this.votersInEachParty.add(votersInEachParty.get(i));
		return true;
	}
	protected boolean setListOfParties(ArrayList<Party> listOfParties) {
		this.listOfParties = listOfParties;//same list of all the elections, if it change in the main election so is here
		setVotersInEachParty(votersInEachParty);
		return true;
	}
	
	//Gets
	public int getOldNumOfVotersInBaloot() {
		return oldNumOfVotersInBaloot;
	}
	public int getBallotNum() {
		return ballotNum;
	}
	public ArrayList<Citizen> getListOfVoters() {
		return listOfVoters;
	}
	public String getAddress() {
		return ballotAddress;
	}
	public double getVotePrecent() {
		return votePrecent;
	}
	public ArrayList<Integer> getVotersInEachParty() {
		return votersInEachParty;
	}
	public int getNumOfVotersInBaloot() {
		return numOfVotersInBaloot;
	}
	public int getNumOfVotersThatVote() {
		return numOfVotersThatVote;
	}
	
	//Add citizen
	public void addCitizen(Citizen citizen) {
		listOfVoters.add(citizen);
		numOfVotersInBaloot++;
		return;
	}
	
	//Add one in the results of the selected party and one to voters that vote
	public void votersInEachPartyPlusPlus(int numOfParty) {
		votersInEachParty.set(numOfParty, votersInEachParty.get(numOfParty) + 1);
		numOfVotersThatVote++;
	}
	
	//Reset number of voters that vote
	protected boolean resetNumOfVotersThatVote() {
		numOfVotersThatVote = 0;
		return true;
	}
	
	//Save old vote
	protected void saveOldVote() {
		this.oldNumOfVotersInBaloot = numOfVotersInBaloot;
	}
	
	//equals
	@Override
	public boolean equals(Object ballot) {
		if (ballotAddress.equals(((RegBallot)ballot).getAddress()))
			return true;
		return false;
	}
	
	//toString
	public String toString() {
		String str = "The ballot number: " + ballotNum + ", Address: " + ballotAddress +
				", Type: regular, Has " + numOfVotersInBaloot + " citizen" + (listOfVoters.size() == 1 ? "" : "'s") +
				" that register to that ballot \n";
		return str;
	}
}
