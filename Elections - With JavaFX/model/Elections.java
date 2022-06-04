package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Elections implements Serializable{
	private boolean thereWereEllecions = false;
	private SetGenerics<Citizen> citizens = new SetGenerics<Citizen>();
	private int monthOfElection;
	private int yearOfElection;
	private ArrayList<Citizen> listOfAllVoters;
	private ArrayList<Party> listOfAllParties;
	private ArrayList<RegBallot> listOfAllBallots;
	private int numOfParties;
	private int numOfBallots;
	private int numOfAllVoters;
	private int numOfVoter;
	private ArrayList<Party> listOfAllPartiesInTheOldElections;
	private ArrayList<RegBallot> listOfAllBallotsInTheOldElections;
	private String nameOfPartyToVote;
	private String idOfCitizenThatVote;
	private int oldNumOfAllVoters;
	
	//Constructors
	public Elections(int monthOfElection, int yearOfElection) {
		this(monthOfElection, yearOfElection, null, null, null);
	}
	private Elections(int monthOfElection, int yearOfElection, ArrayList<Citizen> listOfAllVoters, ArrayList<Party> listOfAllParties, ArrayList<RegBallot> listOfAllBallots) {
		setMonthOfElection(monthOfElection);
		setYearOfElection(yearOfElection);
		setListOfAllVoters(listOfAllVoters);
		setListOfAllParties(listOfAllParties);
		setListOfAllBallots(listOfAllBallots);
		numOfAllVoters = 0;
		numOfVoter = -1;
	}
	
	//Sets
	public void setNameOfPartyToVote(String partyName) {
		nameOfPartyToVote = partyName;
	}
	public void setIdOfCitizenThatVote(String idNum) {
		idOfCitizenThatVote = idNum;
	}
	public boolean setMonthOfElection(int monthOfElection) {
		this.monthOfElection = monthOfElection;
		return true;
	}
	public boolean setYearOfElection(int yearOfElection) {
		this.yearOfElection = yearOfElection;
		return true;
	}
	public boolean setListOfAllVoters(ArrayList<Citizen> listOfAllVoters) {
		this.listOfAllVoters = new ArrayList<Citizen>();
		if(listOfAllVoters == null)//List doesn't exist
			return true;
		for(int i = 0; i < listOfAllVoters.size(); i++)
			this.listOfAllVoters.add(listOfAllVoters.get(i));
		return true;
	}
	private boolean setListOfAllParties(ArrayList<Party> listOfAllParties) {
		this.listOfAllParties = new ArrayList<Party>();
		numOfParties = 0;
		if(listOfAllParties == null)//List doesn't exist
			return true;
		for(int i = 0; i < listOfAllParties.size(); i++) {//copy the parties
			this.listOfAllParties.add(listOfAllParties.get(i));
			numOfParties++;
		}
		return true;
	}
	private boolean setListOfAllBallots(ArrayList<RegBallot> listOfAllBallots) {
		this.listOfAllBallots = new ArrayList<RegBallot>();
		numOfBallots = 0;
		if(listOfAllBallots == null)//List doesn't exist
			return true;
		for(int i = 0; i < listOfAllBallots.size(); i++) {//copy the ballots
			this.listOfAllBallots.add(listOfAllBallots.get(i));
			numOfBallots++;
		}
		return true;
	}
	
	//Gets
	public int getNumOfVoter() {
		return numOfVoter;
	}
	public boolean getWereElections() {
		return thereWereEllecions;
	}
	public String getNameOfPartyToVote() {
		return nameOfPartyToVote;
	}
	public String getIdOfCitizenThatVote() {
		return idOfCitizenThatVote;
	}
	public ArrayList<Party> getListOfAllParties() {
		return listOfAllParties;
	}
	public ArrayList<RegBallot> getListOfAllBallots() {
		return listOfAllBallots;
	}
	public ArrayList<Citizen> getListOfAllVoters() {
		return listOfAllVoters;
	}
	public int getMonthOfElection() {
		return monthOfElection;
	}
	public int getYearOfElection() {
		return yearOfElection;
	}
	public int getNumOfAllVoters() {
		return numOfAllVoters;
	}
	
	//Add ballot
	public boolean addBallot(int ballotType, String ballotAddress) throws Exception {
		RegBallot ballot = null;
		if((ballotAddress == null) || (ballotAddress.length() == 0))
			throw new Exception("You need to fill the ballot address");
		switch(ballotType) {
		case 1://Regular ballot
			for(int i = 0; i < listOfAllBallots.size(); i++) {
				if((listOfAllBallots.get(i).getAddress().equals(ballotAddress)) 
						&& !(listOfAllBallots.get(i).getClass().getSimpleName().equals("MilitaryBallot"))
						&& !(listOfAllBallots.get(i).getClass().getSimpleName().equals("CovidBallot"))
						&& !(listOfAllBallots.get(i).getClass().getSimpleName().equals("MilitaryCovidBallot")))
					throw new Exception("Ballot is already exist");
			}
			ballot = new RegBallot(ballotAddress, listOfAllParties);
			break;
		case 2://Military ballot
			for(int i = 0; i < listOfAllBallots.size(); i++) {
				if((listOfAllBallots.get(i).getAddress().equals(ballotAddress)) 
						&& (listOfAllBallots.get(i).getClass().getSimpleName().equals("MilitaryBallot")))
					throw new Exception("Ballot is already exist");
			}
			ballot = new MilitaryBallot(ballotAddress, listOfAllParties);
			break;
		case 3://COVID ballot
			for(int i = 0; i < listOfAllBallots.size(); i++) {
				if((listOfAllBallots.get(i).getAddress().equals(ballotAddress)) 
						&& (listOfAllBallots.get(i).getClass().getSimpleName().equals("CovidBallot")))
					throw new Exception("Ballot is already exist");
			}
			ballot = new CovidBallot(ballotAddress, listOfAllParties);
			break;
		case 4://Military-COVID Ballot
			for(int i = 0; i < listOfAllBallots.size(); i++) {
				if((listOfAllBallots.get(i).getAddress().equals(ballotAddress)) 
						&& (listOfAllBallots.get(i).getClass().getSimpleName().equals("MilitaryCovidBallot")))
					throw new Exception("Ballot is already exist");
			}
			ballot = new MilitaryCovidBallot(ballotAddress, listOfAllParties);
			break;
		default: //Entered invalid choice
			throw new Exception("You need to choose type of ballot");
		}
		listOfAllBallots.add(ballot);
		numOfBallots++;
		return true;
	}
	
	//Add citizen
	public String addCitizen(String name, String idNum, int yearOfBirth, boolean inInsulation, int daysInInsulation, boolean wearProtection, boolean carryWeapon) throws Exception{
		if((name == null) || (name.length() == 0))
			throw new Exception("You should fill the name");
		try {
			Integer.parseInt(idNum);
		}
		catch(Exception e) {
			throw new Exception("The id number should contain only numbers");
		}
		if(LocalDate.now().getYear() - yearOfBirth <= 21) //Soldier
			return addSoldier(name, idNum, yearOfBirth, inInsulation, daysInInsulation, wearProtection, carryWeapon);
		Citizen citizen = new Citizen(name, idNum, yearOfBirth, inInsulation, daysInInsulation, wearProtection);
		try {//Check if exist and can vote
			if(citizens.addGenerics(citizen)) {
				int numOfRegBallotWithLowestCitizens = -1;
				int numOfCovidBallotWithLowestCitizens = -1;
				if(inInsulation) {//need to vote in COVID ballot
					//COVID with protection
					for(int i = 0; i < listOfAllBallots.size(); i++) {//Check the number of the COVID ballot with lowest citizens
						if((listOfAllBallots.get(i).getClass().getSimpleName().equals("CovidBallot"))) {//check if exist and is COVID type
							if(numOfCovidBallotWithLowestCitizens == -1)//first COVID ballot found
								numOfCovidBallotWithLowestCitizens = i;//save the position of the first COVID ballot
							else {//not the first COVID ballot
								if(listOfAllBallots.get(i).getNumOfVotersInBaloot() < listOfAllBallots.get(numOfCovidBallotWithLowestCitizens).getNumOfVotersInBaloot())//Check if there are less voters in the found ballot
									numOfCovidBallotWithLowestCitizens = i;
							}
						}
					}
					if(numOfCovidBallotWithLowestCitizens == -1) {//There are no COVID ballot
						citizens.removeLastGenerics();
						throw new Exception("Sorry, there are still no relevent ballots opened");
					}
					citizen.setBallotVote(listOfAllBallots.get(numOfCovidBallotWithLowestCitizens));//Put the place of the voting in civilian voting place
					listOfAllBallots.get(numOfCovidBallotWithLowestCitizens).addCitizen(citizen);//Add the citizen to the ballot voters list
					listOfAllVoters.add(citizen);
					numOfAllVoters++;
					return "Succeed, vote in COVID ballot no.: " + citizen.getBallotVote().getBallotNum();
				}
				//regular ballot
				for(int i = 0; i < listOfAllBallots.size(); i++) {//Check the number of the regular ballot with lowest citizens
					if(!(listOfAllBallots.get(i).getClass().getSimpleName().equals("CovidBallot"))
							&& !(listOfAllBallots.get(i).getClass().getSimpleName().equals("MilitaryCovidBallot"))
							&& !(listOfAllBallots.get(i).getClass().getSimpleName().equals("MilitaryBallot"))) {//check if exist and is not COVID and military types (polymorphism)
						if(numOfRegBallotWithLowestCitizens == -1)//first regular ballot found
							numOfRegBallotWithLowestCitizens = i;//save the position of the first regular ballot
						else {//not the first regular ballot
							if(listOfAllBallots.get(i).getNumOfVotersInBaloot() < listOfAllBallots.get(numOfRegBallotWithLowestCitizens).getNumOfVotersInBaloot())//Check if there are less voters in the found ballot
								numOfRegBallotWithLowestCitizens = i;
						}
					}
				}
				if(numOfRegBallotWithLowestCitizens == -1) {//There are no regular ballot
					citizens.removeLastGenerics();
					throw new Exception("Sorry, there are still no ballots opened so you can't vote");
				}
				citizen.setBallotVote(listOfAllBallots.get(numOfRegBallotWithLowestCitizens));//Put the place of the voting in civilian voting place
				listOfAllBallots.get(numOfRegBallotWithLowestCitizens).addCitizen(citizen);//Add the citizen to the ballot voters list
				listOfAllVoters.add(citizen);
				numOfAllVoters++;
				return "Succeed, vote in regular ballot no.: " + citizen.getBallotVote().getBallotNum();
			}
		}
		catch(Exception e) {
			return e.getMessage();
		}
		return "";
	}
	//Add soldier
	private String addSoldier(String name, String idNum, int yearOfBirth, boolean inInsulation, int daysInInsulation, boolean wearProtection, boolean carryWeapon) {
		Soldier soldier = new Soldier(name, idNum, yearOfBirth, inInsulation, daysInInsulation, wearProtection, carryWeapon);
		try {//Check if exist and can vote
			if(citizens.addGenerics(soldier)) {
				int numOfRegBallotWithLowestCitizens = -1;
				int numOfMilitaryBallotWithLowestCitizens = -1;
				int numOfMilitaryCovidBallotWithLowestCitizens = -1;
				int numOfCovidBallotWithLowestCitizens = -1;
				if(inInsulation) {//need to vote in COVID ballot (military or regular) 
					//COVID with protection
					for(int i = 0; i < listOfAllBallots.size(); i++) {//Check the number of the military COVID ballot with lowest citizens
						if((listOfAllBallots.get(i).getClass().getSimpleName().equals("MilitaryCovidBallot"))) {//check if exist and is military COVID type
							if(numOfMilitaryCovidBallotWithLowestCitizens == -1)//first military COVID ballot found
								numOfMilitaryCovidBallotWithLowestCitizens = i;//save the position of the first COVID ballot
							else {//not the first military COVID ballot
								if(listOfAllBallots.get(i).getNumOfVotersInBaloot() < listOfAllBallots.get(numOfMilitaryCovidBallotWithLowestCitizens).getNumOfVotersInBaloot())//Check if there are less voters in the found ballot
									numOfMilitaryCovidBallotWithLowestCitizens = i;
							}
						}
					}
					if(numOfMilitaryCovidBallotWithLowestCitizens == -1) {//There are no Military COVID ballot
						for(int i = 0; i < listOfAllBallots.size(); i++) {//Check the number of the COVID ballot with lowest citizens
							if(listOfAllBallots.get(i).getClass().getSimpleName().equals("CovidBallot")) {//check if exist and is COVID type
								if(numOfCovidBallotWithLowestCitizens == -1)//first COVID ballot found
									numOfCovidBallotWithLowestCitizens = i;//save the position of the first COVID ballot
								else {//not the first COVID ballot
									if(listOfAllBallots.get(i).getNumOfVotersInBaloot() < listOfAllBallots.get(numOfCovidBallotWithLowestCitizens).getNumOfVotersInBaloot())//Check if there are less voters in the found ballot
										numOfCovidBallotWithLowestCitizens = i;
								}
							}
						}
						if(numOfCovidBallotWithLowestCitizens == -1) {//There are no COVID ballot at all
							citizens.removeLastGenerics();
							throw new Exception("Sorry, there are still no relevent ballots opened");
						}
					}
					if(numOfMilitaryCovidBallotWithLowestCitizens != -1) {
						soldier.setBallotVote(listOfAllBallots.get(numOfMilitaryCovidBallotWithLowestCitizens));//Put the place of the voting in civilian voting place
						listOfAllBallots.get(numOfMilitaryCovidBallotWithLowestCitizens).addCitizen(soldier);//Add the citizen to the ballot voters list
					}
					else {
						soldier.setBallotVote(listOfAllBallots.get(numOfCovidBallotWithLowestCitizens));//Put the place of the voting in civilian voting place
						listOfAllBallots.get(numOfCovidBallotWithLowestCitizens).addCitizen(soldier);//Add the citizen to the ballot voters list
					}
					listOfAllVoters.add(soldier);
					numOfAllVoters++;
					if(numOfMilitaryCovidBallotWithLowestCitizens != -1)
						return "Succeed, vote in military-COVID ballot no.: " + soldier.getBallotVote().getBallotNum();
					else
						return "Succeed, vote in COVID ballot no.: " + soldier.getBallotVote().getBallotNum();
				}
				//Soldier without COVID
				for(int i = 0; i < listOfAllBallots.size(); i++) {//Check the number of the military ballot with lowest citizens
					if(listOfAllBallots.get(i).getClass().getSimpleName().equals("MilitaryBallot")) {//check if exist and is military type
						if(numOfMilitaryBallotWithLowestCitizens == -1)//first military ballot found
							numOfMilitaryBallotWithLowestCitizens = i;//save the position of the first military ballot
						else {//not the first military ballot
							if(listOfAllBallots.get(i).getNumOfVotersInBaloot() < listOfAllBallots.get(numOfMilitaryBallotWithLowestCitizens).getNumOfVotersInBaloot())//Check if there are less voters in the found ballot
								numOfMilitaryBallotWithLowestCitizens = i;
						}
					}
				}
				if(numOfMilitaryBallotWithLowestCitizens != -1) {//There are military ballot
					soldier.setBallotVote(listOfAllBallots.get(numOfMilitaryBallotWithLowestCitizens));//Put the place of the voting in civilian voting place
					listOfAllBallots.get(numOfMilitaryBallotWithLowestCitizens).addCitizen(soldier);//Add the citizen to the ballot voters list
					listOfAllVoters.add(soldier);
					numOfAllVoters++;
					return "Succeed, vote in military ballot no.: " + soldier.getBallotVote().getBallotNum();
				}	
				//no military ballot so will vote in regular ballot
				for(int i = 0; i < listOfAllBallots.size(); i++) {//Check the number of the regular ballot with lowest citizens
					if(!(listOfAllBallots.get(i).getClass().getSimpleName().equals("CovidBallot"))
							&& !(listOfAllBallots.get(i).getClass().getSimpleName().equals("MilitaryCovidBallot"))) {//check if exist and is not COVID (polymorphism)
						if(numOfRegBallotWithLowestCitizens == -1)//first regular ballot found
							numOfRegBallotWithLowestCitizens = i;//save the position of the first regular ballot
						else {//not the first regular ballot
							if(listOfAllBallots.get(i).getNumOfVotersInBaloot() < listOfAllBallots.get(numOfRegBallotWithLowestCitizens).getNumOfVotersInBaloot())//Check if there are less voters in the found ballot
								numOfRegBallotWithLowestCitizens = i;
						}
					}
				}
				if(numOfRegBallotWithLowestCitizens == -1) {//There are no regular ballot
					citizens.removeLastGenerics();
					throw new Exception("Sorry, there are still no ballots opened so you can't vote");
				}
				soldier.setBallotVote(listOfAllBallots.get(numOfRegBallotWithLowestCitizens));//Put the place of the voting in civilian voting place
				listOfAllBallots.get(numOfRegBallotWithLowestCitizens).addCitizen(soldier);//Add the citizen to the ballot voters list
				listOfAllVoters.add(soldier);
				numOfAllVoters++;
				return "Succeed, vote in regular ballot no.: " + soldier.getBallotVote().getBallotNum();
			}
		}
		catch(Exception e) {
			return e.getMessage();
		}
		return "";
	}
	
	//Add party
	public String addParty(String partyName, int partyTendency, LocalDate partyCreatedDate, ArrayList<String> idList) throws Exception {
		if((partyName == null) || (partyName.length() == 0))
			throw new Exception("You need to fill the party name");
		if(numOfParties > 0) {//Check if there are parties
			for(int i = 0; i < listOfAllParties.size(); i++) {
				if((listOfAllParties.get(i) != null) && (listOfAllParties.get(i).getName().equals(partyName)))//Check if exist and equal
					throw new Exception("Party is already exist");
			}
		}
		ArrayList<Citizen> listOfContestants = new ArrayList<Citizen>();
		for(int i = 0; (idList != null) && (i < idList.size()); i++) {//Search the citizen by id number - check every id number in the list
			for(int z = 0; (listOfAllVoters != null) && (z < listOfAllVoters.size()); z++) {//Search the citizen in the voters list
				if(listOfAllVoters.get(z).getIdNum().equals(idList.get(i))) {//Check if that is the citizen
					if(listOfAllVoters.get(z).getBelongParty() == null)
						listOfContestants.add(listOfAllVoters.get(z));
					else
						System.out.println("Citizen with id No." + idList.get(i) + " is already belong to another party so he is't contestant in the new party!");
					break;
				}
				if(z == listOfAllVoters.size() - 1)//Citizen doesn't exist and there is id number in the list 
					System.out.println("There is no citizen with id num " + idList.get(i) + ", so he doesnt register as contestant");
			}
		}
		try {
			Party newParty = new Party(partyName, partyTendency, partyCreatedDate, listOfContestants);//Create new party
			//update the list of all parties and if necessary update all the ballots (if new list created)
			listOfAllParties.add(newParty);
			numOfParties++;
			if(newParty.getListOfContestants() != null) {//update the citizen that he belong to party
				ArrayList<Citizen> list = newParty.getListOfContestants();
				for(int i = 0; i < list.size(); i++)
					list.get(i).setBelongParty(newParty);
			}
			//                                                               thereWereEllecions = false; // need to do election once again
			return "Succeed, the party " + newParty.getName() + " running in the elections";
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	//Add contestant to party
	public String addContestantToParty(String idNum, String partyName) throws Exception {
		Citizen citizenToAdd = null;
		Party party = null;
		for(int i = 0; i < listOfAllVoters.size(); i++) {//Search the civilian
			if(listOfAllVoters.get(i).getIdNum().equals(idNum)) {//citizen exist
				citizenToAdd = listOfAllVoters.get(i);
				if(citizenToAdd.getBelongParty() != null)//Citizen belong to other party
					throw new Exception("Citizen is already belong to other party");
				break;
			}
			if(i == listOfAllVoters.size() - 1)//Citizen doesn't exist
				throw new Exception("There is no citizen with that id number");
		}
		if (citizenToAdd == null)
			throw new Exception("There are no citizens yet");
		for(int i = 0; i < listOfAllParties.size(); i++) {//Search the party
			if(listOfAllParties.get(i).getName().equals(partyName)) {//Party exist
				party = listOfAllParties.get(i);
				break;
			}
			if(i == listOfAllParties.size() - 1)
				throw new Exception("There is no party with that name");
		}
		if (party == null)
			throw new Exception("There are no parties yet");
		return party.addContestant(citizenToAdd);
	}
	
	//All ballot in string
	public String allBallotsInString() {
		if (listOfAllBallots == null || listOfAllBallots.size() == 0)
			return "There are 0 ballots";
		StringBuffer str = new StringBuffer("There " + (numOfBallots == 1 ? "is " + numOfBallots + " ballot \n" : "are " + numOfBallots + " ballots \n"));
		for(int i = 0; i < listOfAllBallots.size(); i++) {
			str.append("-" + listOfAllBallots.get(i));//add the ballot to the string
		}
		return str.toString();
	}
	
	//All voters in string
	public String allVotersInString() {
		if (listOfAllVoters == null || listOfAllVoters.size() == 0)
			return "There are 0 voters";
		StringBuffer str = new StringBuffer("There " + (numOfAllVoters == 1 ? "is " + numOfAllVoters + " voter \n" : "are " + numOfAllVoters + " voters \n"));
		for(int i = 0; i < listOfAllVoters.size(); i++) {
			str.append("-" + listOfAllVoters.get(i));//add the voter to the string
		}
		return str.toString();
	}
	
	//All parties in string
	public String allPartiesInString() {
		if (listOfAllParties == null || listOfAllParties.size() == 0)
			return "There are 0 parties";
		StringBuffer str = new StringBuffer("There " + (numOfParties == 1 ? "is " + numOfParties + " party \n" : "are " + numOfParties + " parties \n"));
		for(int i = 0; i < listOfAllParties.size(); i++) {
			str.append("-" + listOfAllParties.get(i));
		}
		return str.toString();
	}
	
	//Make the question to voter if want to vote
	public String askVoterIfWantToVote(int numOfVoterInList) {
		return "Hi " + listOfAllVoters.get(numOfVoterInList).getName() + ", do you want to vote?";
	}
	
	//Reset old elections
	public void resetOldElections() {
		for(int ballotNum = 0; ballotNum < listOfAllBallots.size(); ballotNum++) {//go on all the ballots
			listOfAllBallots.get(ballotNum).setVotersInEachParty(null);
			listOfAllBallots.get(ballotNum).resetNumOfVotersThatVote();//reset number of voters that vote
			for(int partyNum = 0; partyNum < listOfAllParties.size(); partyNum++) //go on all the results of each party
				listOfAllBallots.get(ballotNum).getVotersInEachParty().add(0);//reset votes
		}
	}
	
	//Do the vote of citizen
	public void vote(String idOfCitizenThatVote, String nameOfPartyToVote) {
		//Reset the results of old election
		Citizen citizenThatVote = null;
		int numOfParty = 0;
		for(int citizenNum = 0; citizenNum < listOfAllVoters.size(); citizenNum++) {
			if(listOfAllVoters.get(citizenNum).getIdNum().equals(idOfCitizenThatVote)) {
				citizenThatVote = listOfAllVoters.get(citizenNum);
				break;
			}
		}
		for(numOfParty = 0; numOfParty < listOfAllParties.size(); numOfParty++) {
			if(listOfAllParties.get(numOfParty).getName().equals(nameOfPartyToVote))
				break;
		}
		citizenThatVote.getBallotVote().votersInEachPartyPlusPlus(numOfParty);//add the vote to the results in ballot
	}
	
	//Move to next voter
	public void numOfVoterPlusPlus() {
		numOfVoter++;
	}
	
	//Mark that were elections
	public void wereElections() {
		thereWereEllecions = true;
	}
	
	//Reset the number of voter that vote now
	public void resetNumOfVoter() {
		numOfVoter = -1;
	}
	
	//Show vote options
	public void showVoteOptions() {
		System.out.println("To which party do you want to vote? (number)");
		for(int i = 0; i < listOfAllParties.size(); i++) {
			System.out.println(i + " - " + listOfAllParties.get(i).getName());
		}
	}
	
	//Save the old vote
	public void saveOldVote() {
		oldNumOfAllVoters = numOfAllVoters;
		this.listOfAllPartiesInTheOldElections = new ArrayList<Party>();//Save the list of all parties in the old elections
		for(int i = 0; i < listOfAllParties.size(); i++) 
			this.listOfAllPartiesInTheOldElections.add(listOfAllParties.get(i));
		this.listOfAllBallotsInTheOldElections = new ArrayList<RegBallot>();//Save the list of all ballots in the old elections
		for(int i = 0; i < listOfAllBallots.size(); i++)
			this.listOfAllBallotsInTheOldElections.add(listOfAllBallots.get(i));
		for(int i = 0; i < listOfAllBallots.size(); i++) {//Save the info of old vote in each ballot
			listOfAllBallots.get(i).saveOldVote();
		}
	}
	
	//Results in grid box
	public GridPane showResultsInBox() {
		GridPane results = new GridPane();
		Text txt;
		for(int partyNumInList = 0; partyNumInList < listOfAllPartiesInTheOldElections.size(); partyNumInList++) {//Insert the parties names to the pane
			txt = new Text(listOfAllPartiesInTheOldElections.get(partyNumInList).getName());
			txt.setTextAlignment(TextAlignment.CENTER);
			results.add(txt, (partyNumInList + 1), 0);
		}
		txt = new Text("Vote precent");
		txt.setTextAlignment(TextAlignment.CENTER);
		results.add(txt, (listOfAllPartiesInTheOldElections.size() + 1), 0);
		for(int ballotNumInList = 0; ballotNumInList < listOfAllBallotsInTheOldElections.size(); ballotNumInList++) {//Insert the ballots numbers to the pane
			txt = new Text(String.valueOf(listOfAllBallotsInTheOldElections.get(ballotNumInList).getBallotNum()));
			txt.setTextAlignment(TextAlignment.CENTER);
			results.add(txt, 0, (ballotNumInList + 1));
		}
		int sumOfVotersToParty = 0;
		int numOfPeopleThatVote = 0;
		for(int partyNumInList = 0; partyNumInList < listOfAllPartiesInTheOldElections.size(); partyNumInList++) {//Go on all the parties
			for(int ballotNumInList = 0; ballotNumInList < listOfAllBallotsInTheOldElections.size(); ballotNumInList++) {//Go on all the ballots
				int numOfVotersToPartyInBallot = listOfAllBallotsInTheOldElections.get(ballotNumInList).getVotersInEachParty().get(partyNumInList);
				sumOfVotersToParty += numOfVotersToPartyInBallot;
				txt = new Text(String.valueOf(numOfVotersToPartyInBallot));
				txt.setTextAlignment(TextAlignment.CENTER);
				results.add(txt, (partyNumInList + 1), (ballotNumInList + 1));
			}
			txt = new Text(String.valueOf(sumOfVotersToParty));
			txt.setTextAlignment(TextAlignment.CENTER);
			results.add(txt, (partyNumInList + 1), (listOfAllBallotsInTheOldElections.size() + 1));
			numOfPeopleThatVote += sumOfVotersToParty;
			sumOfVotersToParty = 0;
		}
		txt = new Text("Total votes");
		txt.setTextAlignment(TextAlignment.CENTER);
		results.add(txt, 0, (listOfAllBallotsInTheOldElections.size() + 1));
		for(int ballotNumInList = 0; ballotNumInList < listOfAllBallotsInTheOldElections.size(); ballotNumInList++) {//Add the vote percents
			double votePrecentInBallot = ((double)listOfAllBallotsInTheOldElections.get(ballotNumInList).getNumOfVotersThatVote() * 100)
					/ (listOfAllBallotsInTheOldElections.get(ballotNumInList).getOldNumOfVotersInBaloot());
			txt = new Text(String.valueOf(votePrecentInBallot));
			txt.setTextAlignment(TextAlignment.CENTER);
			results.add(txt, (listOfAllPartiesInTheOldElections.size() + 1), (ballotNumInList + 1));
		}
		double generalVotePercent = ((double)(numOfPeopleThatVote * 100) / (oldNumOfAllVoters));
		txt = new Text(String.valueOf(generalVotePercent));
		txt.setTextAlignment(TextAlignment.CENTER);
		results.add(txt, (listOfAllPartiesInTheOldElections.size() + 1), (listOfAllBallotsInTheOldElections.size() + 1));
		results.setGridLinesVisible(true);
		results.setAlignment(Pos.CENTER);
		return results;
	}
	
	//Load data
	public Elections loadData() throws ClassNotFoundException, IOException {
		File file = new File("OldElections.dat");
		file.setReadable(true);
		ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(file));
		Elections election = (Elections)inFile.readObject();
		inFile.close();
		election.listOfAllBallots.get(0).setNumOfNextBallot(election.listOfAllBallots.size() + 1);//change the number of the next ballot (that a static variable)
		return election;
	}
	
	//Save data
	public void saveData() throws FileNotFoundException, IOException {
		File file = new File("OldElections.dat");
		file.setWritable(true);
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(file));
		outFile.writeObject(this);
		outFile.close();
	}
	
	//equals
	@Override
	public boolean equals(Object election) {
		if((monthOfElection == ((Elections)election).getMonthOfElection()) 
			&& (yearOfElection == ((Elections)election).getYearOfElection()))
			return true;
		return false;
	}
	
	//toString
	public String toString() {
		StringBuffer str = new StringBuffer("The election will be in: " + monthOfElection + "/" + yearOfElection
				+ ", " + listOfAllParties.size() + " part" + (listOfAllParties.size() == 1 ? "y " : "ies ")
				+ "will be competing" + (listOfAllParties.size() == 0 ? "\n" : ": \n"));
		for(int i = 0; i < listOfAllParties.size(); i++)
			str.append(listOfAllParties.get(i).toString());
		return str.toString();
	}
}

