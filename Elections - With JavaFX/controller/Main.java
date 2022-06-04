package controller;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application implements UserInterface{
	@Override
	public void showMessage(String msg) {
		System.out.println(msg);
	}
	@Override
	public String getString(Scanner s) throws Exception {
		return s.nextLine();
	}
	@Override
	public int getInt(Scanner s) throws Exception {
		return s.nextInt();
	}
	@Override
	public boolean getBoolean(Scanner s) throws Exception {
		return s.nextBoolean();
	}
	
	public static boolean getBoolean() throws Exception {
		Scanner s = new Scanner(System.in);
		return s.nextBoolean();
	}
	public static int getInt() throws Exception {
		Scanner s = new Scanner(System.in);
		return s.nextInt();
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Manager manager1 = new Manager();
		view.view view = new view.view(stage);
		
		//Hard code
		ArrayList<String> str1 = new ArrayList<String>();
		str1.add("315876011");
		str1.add("123456789");
		ArrayList<String> str2 = new ArrayList<String>();
		str2.add("987654321");
		str2.add("111111111");
		LocalDate someDate = LocalDate.of(1900, 1, 1);
		try {	
			manager1.addBallot(1, "Yehud");
			manager1.addBallot(2, "Haifa");
			manager1.addBallot(3, "Eilat");
			manager1.addBallot(4, "Beer Sheva");
		}
		catch(Exception e) {
			
		}
		try {
			manager1.addCitizen("amit", "315876011", 1996, false, 0, false, false);
			manager1.addCitizen("haim", "123456789", 2002, true, 10, true, false);
			manager1.addCitizen("shalom", "987654321", 2002, false, 0, false, false);
			manager1.addCitizen("leon", "111111111", 1986, false, 0, false, false);
			manager1.addCitizen("moshe", "222222222", 2001, false, 0, false, false);
			manager1.addCitizen("rotem", "098989586", 1965, true, 26, true, false);
			manager1.addParty("Party1", 2, someDate, str1);
			manager1.addParty("Party2", 2, someDate, str2);
		}
		catch(Exception e) {
			
		}
		//End of hard code
		
		
	 	//Events handler to buttons in menu
		
		//Click on add ballot in the menu
		EventHandler<ActionEvent> clickOnButtonAddBallotAtMenu = new EventHandler<ActionEvent>() { 
			@Override
			public void handle(ActionEvent event) {
				//Make the new buttons and details to show
				GridPane newDetailsToShow = new GridPane();
				newDetailsToShow.setGridLinesVisible(false);
				newDetailsToShow.setHgap(10);
				newDetailsToShow.setVgap(10);
				newDetailsToShow.add(new Text("Address:"), 1, 0);
				view.clearTextFieldOfAddress();
				newDetailsToShow.add(view.getTextFieldOfAddress(), 2, 0);
				newDetailsToShow.add(new Text("Type:"), 1, 1);
				view.resetBallotOptions();
				newDetailsToShow.add(view.getBallotOption1(), 2, 1);
				newDetailsToShow.add(view.getBallotOption2(), 2, 2);
				newDetailsToShow.add(view.getBallotOption3(), 2, 3);
				newDetailsToShow.add(view.getBallotOption4(), 2, 4);
				newDetailsToShow.add(view.getAddBallotButton(), 1, 5);
				newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
				view.showInCenter(newDetailsToShow);
			}
		};
		view.showDetailsToAddBallot(clickOnButtonAddBallotAtMenu);
		
		//Click on add ballot after fill the details
		EventHandler<ActionEvent> clickOnButtonAddBallot = new EventHandler<ActionEvent>() { 
			@Override
			public void handle(ActionEvent event) {
				try {
					if(manager1.addBallot(view.getBallotType(), view.getTextFieldOfAddress().getText())) {
						view.resetMenuSelection();
						view.showInCenter(new Text("Succeed"));
					}
				}
				catch(Exception e){
					view.resetMenuSelection();
					view.showInCenter(new Text(e.getMessage()));
				}
			}
		};
		view.addBallot(clickOnButtonAddBallot);
		
		//Click on add citizen in menu
		EventHandler<ActionEvent> clickOnButtonAddCitizenInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//Make the new buttons and details to show
				GridPane newDetailsToShow = new GridPane();
				newDetailsToShow.setGridLinesVisible(false);
				newDetailsToShow.setHgap(10);
				newDetailsToShow.setVgap(10);
				newDetailsToShow.add(new Text("Name:"), 1, 0);
				view.resetTextFieldOfCitizenName();
				newDetailsToShow.add(view.getTextFieldOfCitizenName(), 2, 0);
				newDetailsToShow.add(new Text("ID Num:"), 1, 1);
				view.resetTextFieldOfIdNum();
				newDetailsToShow.add(view.getTextFieldOfIdNum(), 2, 1);
				newDetailsToShow.add(new Text("Year of birth:"), 1, 2);
				view.resetTextFieldOfBirthYear();
				newDetailsToShow.add(view.getTextFieldOfBirthYear(), 2, 2);
				newDetailsToShow.add(new Text("In insulation:"), 1, 3);
				view.resetCheckBoxOfInInsulation();
				newDetailsToShow.add(view.getCheckBoxOfInInsulation(), 2, 3);
				view.getTextOfDaysInInsulation().setVisible(false);
				newDetailsToShow.add(view.getTextOfDaysInInsulation(), 3, 3);
				view.getTextFieldOfDaysInInsulation().setVisible(false);
				view.setZeroAtDaysInInsulation();
				newDetailsToShow.add(view.getTextFieldOfDaysInInsulation(), 4, 3);
				newDetailsToShow.add(new Text("Wear protection:"), 1, 4);
				view.resetCheckBoxOfWearProtection();
				newDetailsToShow.add(view.getCheckBoxOfWearProtection(), 2, 4);
				newDetailsToShow.add(new Text("Carry weapon:"), 1, 5);
				view.resetCheckBoxOfCarryWeapon();
				newDetailsToShow.add(view.getCheckBoxOfCarryWeapon(), 2, 5);
				newDetailsToShow.add(view.getButtonOfAddCitizen(), 1, 6);
				newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
				view.showInCenter(newDetailsToShow);
			}
		};
		view.addCitizen(clickOnButtonAddCitizenInMenu);
		
		//Click on in insulation
		EventHandler<ActionEvent> clickOnButtonInInsulation = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(view.getCheckBoxOfInInsulation().isSelected()) {//Need to fill days in insulation
					view.setZeroAtDaysInInsulation();
					view.getTextFieldOfDaysInInsulation().setVisible(true);
					view.getTextOfDaysInInsulation().setVisible(true);
				}
				else {//Not need to fill days in insulation
					//Make the new buttons and details to show
					view.setZeroAtDaysInInsulation();
					view.getTextFieldOfDaysInInsulation().setVisible(false);
					view.getTextOfDaysInInsulation().setVisible(false);
				}
			}
		};
		view.clickOnInInsulation(clickOnButtonInInsulation);
		
		//Click on add citizen
		EventHandler<ActionEvent> clickOnButtonAddCitizen = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					view.resetMenuSelection();
					if(view.getTextFieldOfBirthYear().getText().length() == 0) {
						view.showInCenter(new Text("You need to write year of bearth"));
					}
					else {
						if((view.getCheckBoxOfInInsulation().isSelected()) && (view.getTextFieldOfDaysInInsulation().getText().length() == 0)) {
							view.showInCenter(new Text("You need to fill in how many days you in insulation"));
						}
						else {
							view.showInCenter(new Text(manager1.addCitizen(view.getTextFieldOfCitizenName().getText()
									, view.getTextFieldOfIdNum().getText(), Integer.parseInt(view.getTextFieldOfBirthYear().getText())
									, view.getCheckBoxOfInInsulation().isSelected()
									, Integer.parseInt(view.getTextFieldOfDaysInInsulation().getText())
									, view.getCheckBoxOfWearProtection().isSelected(), view.getCheckBoxOfCarryWeapon().isSelected())));
						}
					}
				}
				catch (Exception e) {
					view.resetMenuSelection();
					view.showInCenter(new Text(e.getMessage()));
				}
			}
		};
		view.clickOnButtonAddCitizen(clickOnButtonAddCitizen);
		
		//Click on add party in the menu
		EventHandler<ActionEvent> clickOnButtonAddPartyInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				//Make the new buttons and details to show
				GridPane newDetailsToShow = new GridPane();
				newDetailsToShow.setGridLinesVisible(false);
				newDetailsToShow.setHgap(10);
				newDetailsToShow.setVgap(10);
				newDetailsToShow.add(new Text("Name:"), 1, 0);
				view.resetTextFieldOfPartyName();
				newDetailsToShow.add(view.getTextFieldOfPartyName(), 2, 0);
				newDetailsToShow.add(new Text("Creation date:"), 1, 1);
				newDetailsToShow.add(new Text("Day: "), 1, 2);
				view.resetTextFieldOfPartyCreationDay();
				newDetailsToShow.add(view.getTextFieldOfCreationDay(), 2, 2);
				newDetailsToShow.add(new Text("Month: "), 1, 3);
				view.resetTextFieldOfPartyCreationMonth();
				newDetailsToShow.add(view.getTextFieldOfCreationMonth(), 2, 3);
				newDetailsToShow.add(new Text("Year: "), 1, 4);
				view.resetTextFieldOfPartyCreationYear();
				newDetailsToShow.add(view.getTextFieldOfCreationYear(), 2, 4);
				newDetailsToShow.add(new Text("Tendency:"), 1, 5);
				view.resetPartyTendency();
				newDetailsToShow.add(view.getOptionOfTendency0(), 2, 5);
				newDetailsToShow.add(view.getOptionOfTendency1(), 2, 6);
				newDetailsToShow.add(view.getOptionOfTendency2(), 2, 7);
				newDetailsToShow.add(view.getButtonOfAddParty(), 1, 8);
				newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
				view.showInCenter(newDetailsToShow);
			}
		};
		view.clickOnButtonAddPartyInMenu(clickOnButtonAddPartyInMenu);
		
		//Click on add party
		EventHandler<ActionEvent> clickOnButtonAddParty = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					if((view.getTextFieldOfCreationYear().getText().length() == 0)
							|| (view.getTextFieldOfCreationMonth().getText().length() == 0) 
							|| (view.getTextFieldOfCreationDay().getText().length() == 0)) {
						view.resetMenuSelection();
						view.showInCenter(new Text("You need to fill up the date"));
					}
					else {
						LocalDate createDate = LocalDate.of(Integer.parseInt(view.getTextFieldOfCreationYear().getText())
								, Integer.parseInt(view.getTextFieldOfCreationMonth().getText())
								, Integer.parseInt(view.getTextFieldOfCreationDay().getText()));
						view.resetMenuSelection();
						view.showInCenter(new Text(manager1.addParty(view.getTextFieldOfPartyName().getText(), view.getPartyTendency(), createDate, null)));
					}
				}
				catch (Exception e) {
					view.resetMenuSelection();
					view.showInCenter(new Text(e.getMessage()));
				}
			}
		};
		view.clickOnButtonAddParty(clickOnButtonAddParty);
		
		//Click on add contestant to party in menu
		EventHandler<ActionEvent> clickOnButtonAddContestantToPartyInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//Make the new buttons and details to show
				GridPane newDetailsToShow = new GridPane();
				newDetailsToShow.setGridLinesVisible(false);
				newDetailsToShow.setHgap(10);
				newDetailsToShow.setVgap(10);
				newDetailsToShow.add(new Text("Id num:"), 1, 0);
				view.resetTextFieldOfIdNumOfContestant();
				newDetailsToShow.add(view.getTextFieldOfIdNumOfContestant(), 2, 0);
				newDetailsToShow.add(new Text("Party name: "), 1, 1);
				view.resetTextFieldOfPartyNameToAddContestant();
				newDetailsToShow.add(view.getTextFieldOfPartyNameToAddContestant(), 2, 1);
				newDetailsToShow.add(view.getButtonOfAddContestantToParty(), 1, 2);
				newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
				view.showInCenter(newDetailsToShow);
			}
		};
		view.clickOnButtonAddContestantToPartyInMenu(clickOnButtonAddContestantToPartyInMenu);
		
		//Click on add contestant to party
		EventHandler<ActionEvent> clickOnButtonAddContestantToParty = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					view.resetMenuSelection();
					view.showInCenter(new Text(manager1.addContestantToParty(view.getTextFieldOfIdNumOfContestant().getText()
							, view.getTextFieldOfPartyNameToAddContestant().getText())));
				}
				catch (Exception e) {
					view.resetMenuSelection();
					view.showInCenter(new Text(e.getMessage()));
				}
			}
		};
		view.clickOnButtonAddContestantToParty(clickOnButtonAddContestantToParty);
		
		//Click on show all ballots in the menu
		EventHandler<ActionEvent> clickOnButtonShowAllBallotsInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.resetMenuSelection();
				view.showInCenter(new Text(manager1.allBallotsInString()));
			}
		};
		view.clickOnButtonShowAllBallotsInMenu(clickOnButtonShowAllBallotsInMenu);
		
		//Click on show all citizens in the menu
		EventHandler<ActionEvent> clickOnButtonShowAllCitizensInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.resetMenuSelection();
				view.showInCenter(new Text(manager1.allVotersInString()));
			}
		};
		view.clickOnButtonShowAllCitizensInMenu(clickOnButtonShowAllCitizensInMenu);
		
		//Click on show all parties in the menu
		EventHandler<ActionEvent> clickOnButtonShowAllPartiesInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.resetMenuSelection();
				view.showInCenter(new Text(manager1.allPartiesInString()));
			}
		};
		view.clickOnButtonShowAllPartiesInMenu(clickOnButtonShowAllPartiesInMenu);
		
		//Click on elections day in menu
		EventHandler<ActionEvent> clickOnButtonElectionsDayInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				manager1.resetNumOfVoter();
				manager1.resetOldElections();
				view.resetComboBoxOfAllPartiesNames();
				for(int partyNum = 0; partyNum < manager1.getListOfAllParties().size(); partyNum++)//Add all party names to combo box
					view.addNameToComboBoxOfAllPartiesNames(manager1.getListOfAllParties().get(partyNum).getName());
				GridPane newDetailsToShow;
				newDetailsToShow = new GridPane();
				newDetailsToShow.setGridLinesVisible(false);
				Text txt = new Text("It's elections day!");
				txt.setFill(Color.RED);
				newDetailsToShow.add(txt, 1, 0);
				newDetailsToShow.add(view.getButtonOfFinishVote(), 1, 1);
				view.resetMenuSelection();
				newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
				view.showInCenter(newDetailsToShow);
			}
		};
		view.clickOnButtonElectionsDayInMenu(clickOnButtonElectionsDayInMenu);
		
		//Click on check box want to vote
		EventHandler<ActionEvent> clickOnCheckBoxWantToVote = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(view.getCheckBoxWantToVote().isSelected()) {
					view.getAllPartiesNames().setVisible(true);
				}
				else {
					view.getAllPartiesNames().setVisible(false);
				}
			}
		};
		view.clickOnCheckBoxWantToVote(clickOnCheckBoxWantToVote);
		
		//Click on button next in voting
		EventHandler<ActionEvent> clickOnButtonFinishVote = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.resetMenuSelection();
				GridPane newDetailsToShow = new GridPane();
				newDetailsToShow.setGridLinesVisible(false);
				if((manager1.getNumOfVoter() != -1) && (manager1.getNumOfVoter() < manager1.getListOfAllVoters().size())) {//There are more voters voter and someone vote
					if(view.getCheckBoxWantToVote().isSelected()) { //Want to vote
						manager1.setNameOfPartyToVote(view.getAllPartiesNames().getValue());
						manager1.setIdOfCitizenThatVote(manager1.getListOfAllVoters().get(manager1.getNumOfVoter()).getIdNum());
						manager1.vote(manager1.getIdOfCitizenThatVote(), manager1.getNameOfPartyToVote());
						view.getCheckBoxWantToVote().setSelected(false);
						view.getAllPartiesNames().setVisible(false);
					}
					manager1.numOfVoterPlusPlus();
					if(manager1.getNumOfVoter() < manager1.getListOfAllVoters().size()) {//Show the next citizen
						newDetailsToShow.add(new Text(manager1.askVoterIfWantToVote(manager1.getNumOfVoter())), 1, 0);
						view.resetCheckBoxWantToVote();
						newDetailsToShow.add(view.getCheckBoxWantToVote(), 2, 0);
						view.getAllPartiesNames().setVisible(false);
						newDetailsToShow.add(view.getAllPartiesNames(), 3, 0);
						newDetailsToShow.add(view.getButtonOfFinishVote(), 1, 1);	
					}
					else {
						Text txt = new Text("All the voters voted, and the vote is over");
						txt.setFill(Color.RED);
						newDetailsToShow.add(txt, 1, 0);
						manager1.wereElections();
					}
				}
				else {//Last voter or no voter yet
					if(manager1.getNumOfVoter() == -1) {
						manager1.numOfVoterPlusPlus();
						if(manager1.getNumOfVoter() < manager1.getListOfAllVoters().size()) {//Show the next citizen
							newDetailsToShow.add(new Text(manager1.askVoterIfWantToVote(manager1.getNumOfVoter())), 1, 0);
							newDetailsToShow.add(view.getCheckBoxWantToVote(), 2, 0);
							view.getAllPartiesNames().setVisible(false);
							newDetailsToShow.add(view.getAllPartiesNames(), 3, 0);
							newDetailsToShow.add(view.getButtonOfFinishVote(), 1, 1);	
						}
						else {
							Text txt = new Text("There are no citizens!");
							txt.setFill(Color.RED);
							newDetailsToShow.add(txt, 1, 0);
						}
					}
				}
				newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
				newDetailsToShow.setHgap(10);
				newDetailsToShow.setVgap(10);
				view.showInCenter(newDetailsToShow);
				manager1.saveOldVote();
			}
		};
		view.clickOnFinishVote(clickOnButtonFinishVote);
		
		//Click on button show results in menu
		EventHandler<ActionEvent> clickOnButtonShowResultsInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(manager1.getWereElections()) {//There are results
					view.showInCenter(manager1.showResultsInBox());
				}
				else//There are no results
					view.showInCenter(new Text("You need to complete vote first"));
			}
		};
		view.clickOnShowResultsInMenu(clickOnButtonShowResultsInMenu);
		
		//Click on button save info in the menu
		EventHandler<ActionEvent> clickOnButtonSaveInfoInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Text txt = new Text(manager1.saveData());
				txt.setFill(Color.RED);
				view.showInCenter(txt);
			}
		};
		view.clickOnButtonSaveInfoInMenu(clickOnButtonSaveInfoInMenu);
		//Click on button load old info in the menu
		EventHandler<ActionEvent> clickOnButtonLoadOldInfoInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Text txt = new Text(manager1.loadData());
				txt.setFill(Color.RED);
				view.showInCenter(txt);
			}
		};
		view.clickOnButtonLoadOldInfoInMenu(clickOnButtonLoadOldInfoInMenu);
	}
}
