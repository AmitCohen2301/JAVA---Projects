package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Party implements Serializable {
	private String partyName;
	private int partyTendency;//0 - left, 1 - center, 2 - right
	private LocalDate partyCreatedDate;
	private ArrayList<Citizen> listOfContestants;
	
	//Constructors
	public Party(String partyName, int partyTendency, LocalDate partyCreatedDate, ArrayList<Citizen> listOfContestants) throws Exception {
		try {
			setName(partyName);
			setPartyTendency(partyTendency);
			setPartyCreatedDate(partyCreatedDate);
			setListOfContestants(listOfContestants);
		}
		catch (Exception e){
			throw new Exception(e.getMessage());
		}
	}
	
	//Sets
	public boolean setName(String name) throws Exception {
		if((name == null) || (name.length() == 0))
			throw new Exception("Name of party should contain at least one char");
		partyName = name;
		return true;
	}
	public boolean setPartyTendency(int partyTendency) throws Exception {
		if((partyTendency > 2) || (partyTendency < 0))
			throw new Exception("In valid value to party tendency");
		this.partyTendency = partyTendency;
		return true;
	}
	public boolean setPartyCreatedDate(LocalDate partyCreatedDate) throws Exception {
		if(partyCreatedDate.isAfter(LocalDate.now()))
			throw new Exception("There was no date yet");
		this.partyCreatedDate = partyCreatedDate;
		return true;
	}
	public boolean setListOfContestants(ArrayList<Citizen> listOfContestants) {
		this.listOfContestants = new ArrayList<Citizen>();
		for (int i = 0; i < listOfContestants.size(); i++)
			this.listOfContestants.add(listOfContestants.get(i));
		return true;
	}
	
	//Gets
	public String getName() {
		return partyName;
	}
	public ArrayList<Citizen> getListOfContestants() {
		return listOfContestants;
	}
	
	//Add candidate
	protected String addContestant(Citizen citizenToAdd) {
		listOfContestants.add(citizenToAdd);
		citizenToAdd.setBelongParty(this);
		return "Contestant add Successfully";
	}
	
	//equals
	@Override
	public boolean equals(Object party) {
		if(partyName.equals(((Party)party).getName()))
			return true;
		return false;
	}
	
	//toString
	public String toString() {
		String partyTendencyInWords;
		if (partyTendency >= 1) {
			if (partyTendency == 1)
				partyTendencyInWords = "center";
			else
				partyTendencyInWords = "right";
		}
		else
			partyTendencyInWords = "left";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		int numOfContestants = listOfContestants.size();
		StringBuffer str = new StringBuffer("The party name: " + partyName + ", Belongs to " + partyTendencyInWords 
				+ ", Date had been created: " + partyCreatedDate.format(dtf) + ", Has " + numOfContestants + " contestant"
				+ (numOfContestants == 1 ? "" : "'s") + (numOfContestants == 0 ? "\n" : ": \n"));
		for(int i = 0; i < listOfContestants.size(); i++)
				str.append("  " + (i+1) + ") " + listOfContestants.get(i).toString());
		return str.toString();
	}
	
}
