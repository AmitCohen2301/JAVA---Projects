package model;

import java.io.Serializable;
import java.util.ArrayList;

public class MilitaryBallot extends RegBallot implements Serializable {
	
	//Constructors
	public MilitaryBallot(String ballotAddress) {
		super(numOfNextBallot, ballotAddress, null, 0, null, null);
	}
	public MilitaryBallot(String ballotAddress, ArrayList<Party> listOfAllParties) {
		super(numOfNextBallot, ballotAddress, null, 0, null, listOfAllParties);
	}
	public MilitaryBallot(String ballotAddress, ArrayList<Citizen> listOfVoters, ArrayList<Party> listOfParties) {
		super(numOfNextBallot, ballotAddress, listOfVoters, 0, null, listOfParties);
	}
	public MilitaryBallot(int numOfNextBallot, String ballotAddress, ArrayList<Citizen> listOfVoters, double votePrecent, ArrayList<Integer> votersInEachParty, ArrayList<Party> listOfParties) {
		super(numOfNextBallot, ballotAddress, listOfVoters, votePrecent, votersInEachParty, listOfParties);
	}
	
	//toString
	@Override
	public String toString() {
		String str = "The ballot number: " + ballotNum + ", Address: " + ballotAddress +
				", Type: military, Has " + numOfVotersInBaloot + " citizen" + (listOfVoters.size() == 1 ? "" : "'s") +
				" that register to that ballot \n";
		return str;
	}
}

