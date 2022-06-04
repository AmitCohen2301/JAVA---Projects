package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import model.Citizen;
import model.Party;

public class Manager {
	private model.Elections election = new model.Elections(12, 2021);
	
	//Sets
	public void setNameOfPartyToVote(String nameOfPartyToVote) {
		election.setNameOfPartyToVote(nameOfPartyToVote);
	}
	public void setIdOfCitizenThatVote(String idNum) {
		election.setIdOfCitizenThatVote(idNum);
	}
	
	//Gets
	public boolean getWereElections() {
		return election.getWereElections();
	}
	public int getNumOfVoter() {
		return election.getNumOfVoter();
	}
	public String getNameOfPartyToVote() {
		return election.getNameOfPartyToVote();
	}
	public String getIdOfCitizenThatVote() {
		return election.getIdOfCitizenThatVote();
	}	
	public ArrayList<model.Party> getListOfAllParties() {
		return election.getListOfAllParties();
	}
	public ArrayList<Citizen> getListOfAllVoters() {
		return election.getListOfAllVoters();
	}
	
	//Add ballot
	public boolean addBallot(int ballotType, String ballotAddress) throws Exception {
		try {
			return election.addBallot(ballotType, ballotAddress);
		}
		catch (Exception e){
			throw new Exception(e.getMessage());
		}
	}
	
	//Add citizen
	public String addCitizen(String name, String idNum, int yearOfBirth, boolean inInsulation, int daysInInsulation, boolean wearProtection, boolean carryWeapon) throws Exception {
		try{
			return election.addCitizen(name, idNum, yearOfBirth, inInsulation, daysInInsulation, wearProtection, carryWeapon);
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	//Add party
	public String addParty(String partyName, int partyTendency, LocalDate dateOfCreation, ArrayList<String> listOfId) throws Exception {
		try{
			return election.addParty(partyName, partyTendency, dateOfCreation, listOfId);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	//Add contestant to party
	public String addContestantToParty(String idNum, String partyName) throws Exception {
		try {
			return election.addContestantToParty(idNum, partyName);
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	//All the ballots in string
	public String allBallotsInString() {
		return election.allBallotsInString();
	}
	
	//All voters in string
	public String allVotersInString() {
		return election.allVotersInString();
	}
	
	//All parties in string
	public String allPartiesInString() {
		return election.allPartiesInString();
	}
	
	//Reset the number of voter that vote now
	public void resetNumOfVoter() {
		election.resetNumOfVoter();
	}
	
	//Move to the next voter
	public void numOfVoterPlusPlus() {
		election.numOfVoterPlusPlus();
	}
	
	//Question to voter if want to vote
	public String askVoterIfWantToVote(int numOfVoterInList) {
		return election.askVoterIfWantToVote(numOfVoterInList);
	}
	
	//Do the vote of the voter
	public void vote(String idOfCitizenThatVote, String nameOfPartyToVote) {
		election.vote(idOfCitizenThatVote, nameOfPartyToVote);
	}
	
	//Reset the old elections
	public void resetOldElections() {
		election.resetOldElections();
	}
	
	//Mark that there were eleections
	public void wereElections() {
		election.wereElections();
	}
	
	//Save the results of old vote
	public void saveOldVote() {
		election.saveOldVote();
	}
	
	//Show the results in grid box
	public GridPane showResultsInBox() {
		return election.showResultsInBox();
	}
	
	//Load data from past
	public String loadData() {
		try {
			election = election.loadData();
			return "Data load successfully";
		}
		catch(Exception e) {
			return "Failed to load old elections (not exist)";
		}
	}
	
	//Save the current data
	public String saveData() {
		try {
			election.saveData();
			return "Data saved successfully";
		}
		catch(Exception e) {
			return "Could not save the data";
		}
	}
}
