package model;

import java.io.Serializable;
import java.util.ArrayList;

public class MilitaryCovidBallot extends RegBallot implements Serializable {
	//Constructors
	public MilitaryCovidBallot(String ballotAddress) {
		super(numOfNextBallot, ballotAddress, null, 0, null, null);
	}
	public MilitaryCovidBallot(String ballotAddress, ArrayList<Party> listOfAllParties) {
		super(numOfNextBallot, ballotAddress, null, 0, null, listOfAllParties);
	}
	public MilitaryCovidBallot(String ballotAddress, ArrayList<Citizen> listOfVoters, ArrayList<Party> listOfParties) {
		super(numOfNextBallot, ballotAddress, listOfVoters, 0, null, listOfParties);
	}
	public MilitaryCovidBallot(int numOfNextBallot, String ballotAddress, ArrayList<Citizen> listOfVoters, double votePrecent, ArrayList<Integer> votersInEachParty, ArrayList<Party> listOfParties) {
		super(numOfNextBallot, ballotAddress, listOfVoters, votePrecent, votersInEachParty, listOfParties);
	}
	
	//toString
	@Override
	public String toString() {
		String str = "The ballot number: " + ballotNum + ", Address: " + ballotAddress +
				", Type: military-covid, Has " + numOfVotersInBaloot + " citizen" + (listOfVoters.size() == 1 ? "" : "'s") +
				" that register to that ballot \n";
		return str;
	}
}
