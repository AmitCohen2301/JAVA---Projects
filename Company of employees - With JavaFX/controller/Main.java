package AmitCohen.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import AmitCohen.model.Company;
import AmitCohen.model.Department;
import AmitCohen.model.Employee;
import AmitCohen.model.EmployeeByHours;
import AmitCohen.model.EmployeeMonthly;
import AmitCohen.model.EmployeeMonthlyPlusBonus;
import AmitCohen.model.Preference;
import AmitCohen.model.Role;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	Company company;
	//Main
	public static void main(String[] args) throws FileNotFoundException, IOException {
		launch(args);
	}
	
	//Start
	@Override
	public void start(Stage stage) throws Exception {
		AmitCohen.view.view view = new AmitCohen.view.view(stage);
		try {
			company = Company.loadData();
		}
		catch(Exception e) {
			company = null;
		}
		
		if(company == null)
			view.showInTop(new Text("There is no company yet"));
		else
			view.showInTop(new Text("Company: " + company.getNameOfCompany()));
		//Events handler to buttons in menu
		
		//Click on make new company in the menu
		EventHandler<ActionEvent> clickOnButtonAddCompanyInMenu = new EventHandler<ActionEvent>() { 
			@Override
			public void handle(ActionEvent event) {
				//Make the new buttons and details to show
				GridPane newDetailsToShow = new GridPane();
				newDetailsToShow.setHgap(10);
				newDetailsToShow.setVgap(10);
				newDetailsToShow.add(new Text("Name of company:"), 1, 0);
				view.clearTextFieldOfCompanyName();
				newDetailsToShow.add(view.getTextFieldOfCompanyName(), 2, 0);
				newDetailsToShow.add(new Text("(At least one char)"), 3, 0);
				newDetailsToShow.add(view.getCreateCompanyButton(), 1, 1);
				newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
				view.showInCenter(newDetailsToShow);
			}
		};
		view.clickOnAddCompanyInMenu(clickOnButtonAddCompanyInMenu);
		
		//Click on create company
		EventHandler<ActionEvent> clickOnButtonCreateCompany = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try{
					company = new Company(view.getTextFieldOfCompanyName().getText());
					view.showInCenter(new Text("Succeed, Company created successfully"));
					view.showInTop(new Text("Company: " + company.getNameOfCompany()));
				}
				catch (Exception e) {
					view.showInCenter(new Text(e.getMessage()));
				}
				view.resetMenuSelection();
			}
		};
		view.clickOnCreateCompany(clickOnButtonCreateCompany);
		
		//Click on add department in menu
		EventHandler<ActionEvent> clickOnButtonAddDeparmentInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(company == null)
					view.showInCenter(new Text("You need to create company first"));
				else {
					//Make the new buttons and details to show
					GridPane newDetailsToShow = new GridPane();
					newDetailsToShow.setHgap(10);
					newDetailsToShow.setVgap(10);
					newDetailsToShow.add(new Text("Name of department:"), 1, 0);
					view.clearTextFieldOfDepartmentName();
					newDetailsToShow.add(view.getTextFieldOfDepartmentName(), 2, 0);
					newDetailsToShow.add(new Text("(At least one char)"), 3, 0);
					newDetailsToShow.add(new Text("Does roles can change working time? "), 1, 1);
					view.clearDepartmentCheckBoxChangeWorkingTime();
					newDetailsToShow.add(view.getDepartmentCheckBoxChangeWorkingTime(), 2, 1);
					newDetailsToShow.add(view.getAddDepartmentButton(), 1, 2);
					newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
					view.showInCenter(newDetailsToShow);
				}
			}
		};
		view.clickOnAddDepartmentInMenu(clickOnButtonAddDeparmentInMenu);
		
		//Click on add department
		EventHandler<ActionEvent> clickOnButtonAddDepartment = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					view.showInCenter(new Text(company.addDepartment(view.getTextFieldOfDepartmentName().getText()
							, view.getDepartmentCheckBoxChangeWorkingTime().isSelected(), 8)));
				}
				catch(Exception e) {
					view.showInCenter(new Text(e.getMessage()));
				}
				view.resetMenuSelection();
			}
		};
		view.clickOnAddDepartment(clickOnButtonAddDepartment);
		
		//Click on add role in menu
		EventHandler<ActionEvent> clickOnButtonAddRoleInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(company == null)
					view.showInCenter(new Text("You need to create company first"));
				else {
					if((company.getAllDepartments() == null) || (company.getAllDepartments().size() == 0))
						view.showInCenter(new Text("You need to create department first"));
					else {
						//Make the new buttons and details to show
						GridPane newDetailsToShow = new GridPane();
						newDetailsToShow.setHgap(10);
						newDetailsToShow.setVgap(10);
						newDetailsToShow.add(new Text("Name of role:"), 1, 0);
						view.clearTextFieldOfRoleName();
						newDetailsToShow.add(view.getTextFieldOfRoleName(), 2, 0);
						newDetailsToShow.add(new Text("(At least one char)"), 3, 0);
						newDetailsToShow.add(new Text("To which department you want to add role?"), 1, 1);
						view.clearAllDepartmentsNames();
						for(int i = 0; i < company.getAllDepartments().size(); i++)
							view.addNameToAllDepartmentsNamesComboBox(company.getAllDepartments().get(i).getNameOfDepartment());
						newDetailsToShow.add(view.getAllDepartmentsNamesComboBox(), 2, 1);
						newDetailsToShow.add(new Text("(Must choose one from the list)"), 3, 1);
						newDetailsToShow.add(view.getQuest(), 1, 2);
						view.clearRoleCheckBoxChangeWorkingTime();
						newDetailsToShow.add(view.getRoleCheckBoxChangeWorkingTime(), 2, 2);
						newDetailsToShow.add(view.getAddRoleButton(), 1, 3);
						newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
						view.showInCenter(newDetailsToShow);
					}
				}
			}
		};
		view.clickOnAddRoleInMenu(clickOnButtonAddRoleInMenu);
		
		//Click on combo box of all departments names
		EventHandler<ActionEvent> clickOnComboBoxOfAllDepartmentsNames = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String departmentName = view.getAllDepartmentsNamesComboBox().getValue();
				Department selectedDepartment = company.getAllDepartments().get(0);
				for(int i = 0; i < company.getAllDepartments().size(); i++) {
					if(company.getAllDepartments().get(i).getNameOfDepartment().equals(departmentName)) {
						selectedDepartment = company.getAllDepartments().get(i);
						break;
					}
				}
				if(selectedDepartment.getCanChangeWorkingTime() == false) {
					view.getRoleCheckBoxChangeWorkingTime().setSelected(false);
					view.getRoleCheckBoxChangeWorkingTime().setVisible(false);
					view.getQuest().setVisible(false);
				}
				else {
					view.getRoleCheckBoxChangeWorkingTime().setVisible(true);
					view.getQuest().setVisible(true);
				}
			}
		};
		view.clickOnComboBoxOfAllDepartmentsNames(clickOnComboBoxOfAllDepartmentsNames);
		
		//Click on add role
		EventHandler<ActionEvent> clickOnAddRole = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(view.getAllDepartmentsNamesComboBox().getValue() == null)
					view.showInCenter(new Text("You need to choose to which department you want to add role"));
				else {
					try {
						String departmentName = view.getAllDepartmentsNamesComboBox().getValue();
						Department selectedDepartment = company.getAllDepartments().get(0);
						for(int i = 0; i < company.getAllDepartments().size(); i++) { // search for the selected department
							if(departmentName.equals(company.getAllDepartments().get(i).getNameOfDepartment())) {
								selectedDepartment = company.getAllDepartments().get(i);
								break;
							}
						}
						Role newRole = new Role(view.getTextFieldOfRoleName().getText(), view.getRoleCheckBoxChangeWorkingTime().isSelected(), null , selectedDepartment);
						view.showInCenter(new Text(selectedDepartment.addRole(newRole)));
					}
					catch(Exception e) {
						view.showInCenter(new Text(e.getMessage()));
					}
				}
				view.resetMenuSelection();
			}
		};
		view.clickOnAddRole(clickOnAddRole);
		
		//Click on add employee in menu
		EventHandler<ActionEvent> clickOnAddEmplyeeInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(company == null) //There is no company yet
					view.showInCenter(new Text("You need to create company first"));
				else { //There is company
					if((company.getAllDepartments() == null) || (company.getAllDepartments().size() == 0)) //There is no department yet
						view.showInCenter(new Text("You need to create department first"));
					else { //There is department
						ArrayList<Department> departmentsWithFreeRoles = company.getAllDepartmentWithFreeRoles();
						if(departmentsWithFreeRoles.size() == 0) //There are not free roles
							view.showInCenter(new Text("There are no free roles, so you can't add employee. Create new role"));
						else { //There are free roles
							//Make the new buttons and details to show
							GridPane newDetailsToShow = new GridPane();
							newDetailsToShow.setHgap(10);
							newDetailsToShow.setVgap(10);
							newDetailsToShow.add(new Text("Name of employee:"), 1, 0);
							view.clearTextFieldOfEmployeeName();
							newDetailsToShow.add(view.getTextFieldOfEmployeeName(), 2, 0);
							newDetailsToShow.add(new Text("(At least one char)"), 3, 0);
							newDetailsToShow.add(new Text("To which department you want to add the employee?"), 1, 1);
							view.clearAllDepartmentsWithFreeRoles();
							for(int i = 0; i < departmentsWithFreeRoles.size(); i++)
								view.addNameToAllDepartmentsWithFreeRoleComboBox(departmentsWithFreeRoles.get(i).getNameOfDepartment());
							newDetailsToShow.add(view.getAllDepartmentsWithFreeRolesComboBox(), 2, 1);
							newDetailsToShow.add(new Text("(Must choose one from the list)"), 3, 1);
							newDetailsToShow.add(new Text("To which role you want to add the employee?"), 1, 2);
							view.clearAllFreeRolesInDepartment();
							newDetailsToShow.add(view.getAllFreeRolesInDepartmentComboBox(), 2, 2);
							newDetailsToShow.add(new Text("(Must choose one from the list)"), 3, 2);
							newDetailsToShow.add(new Text("Type of salary:"), 1, 3);
							view.resetEmployeeTypes();
							newDetailsToShow.add(view.getEmployeeTypeOneButton(), 2, 3);
							newDetailsToShow.add(new Text("(Must choose one from the list)"), 3, 3);
							newDetailsToShow.add(view.getEmployeeTypeTwoButton(), 2, 4);
							newDetailsToShow.add(view.getEmployeeTypeThreeButton(), 2, 5);
							newDetailsToShow.add(new Text("What is the prefered work time:"), 1, 6);
							view.resetEmployeeWorkingTimePreference();
							newDetailsToShow.add(view.getEmployeeWorkTimePreferenceOneButton(), 2, 6);
							newDetailsToShow.add(new Text("(Must choose one from the list)"), 3, 6);
							newDetailsToShow.add(view.getEmployeeWorkTimePreferenceTwoButton(), 2, 7);
							newDetailsToShow.add(view.getEmployeeWorkTimePreferenceThreeButton(), 2, 8);
							newDetailsToShow.add(view.getEmployeeWorkTimePreferenceFourButton(), 2, 9);
							newDetailsToShow.add(view.getText1(), 1, 10);
							view.clearTextFieldOfPreferStartingWorkTime();
							newDetailsToShow.add(view.getTextFieldOfPreferStartingWorkTime(), 2, 10);
							newDetailsToShow.add(view.getText2(), 3, 10);
							newDetailsToShow.add(view.getAddEmployeeButton(), 1, 11);
							newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
							view.showInCenter(newDetailsToShow);
						}
					}
				}
			}
		};
		view.clickOnAddEmployeeInMenu(clickOnAddEmplyeeInMenu);
		
		//Click on department with free role
		EventHandler<ActionEvent> clickOnDepartmentWithFreeRole =  new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Department department;
				view.clearAllFreeRolesInDepartment();
				if(view.getAllDepartmentsWithFreeRolesComboBox().getValue() != null) {
					department = company.getDepartmentByName(view.getAllDepartmentsWithFreeRolesComboBox().getValue());
					for(int i = 0; i < department.getAllRoles().size(); i++) {
						if(department.getAllRoles().get(i).getEmployeeInRole() == null)
							view.addNameToAllFreeRolesInDepartment(department.getAllRoles().get(i).getNameOfRole());
					}
				}
			}
		};
		view.clickOnDepartmentWithFreeRole(clickOnDepartmentWithFreeRole);
		
		//Click on working time preference
		EventHandler<ActionEvent> clickOnWorkingTimePreference = new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				if((view.getEmployeeWorkTimePreferenceThreeButton().isSelected()) || (view.getEmployeeWorkTimePreferenceFourButton().isSelected())) {
					view.getText1().setVisible(false);
					view.getText2().setVisible(false);
					view.getTextFieldOfPreferStartingWorkTime().setVisible(false);
					view.getTextFieldOfPreferStartingWorkTime().setText("-1");
				}
				else {
					view.clearTextFieldOfPreferStartingWorkTime();
					view.getText1().setVisible(true);
					view.getText2().setVisible(true);	
					view.getTextFieldOfPreferStartingWorkTime().setVisible(true);
				}
			}
		};
		view.clickOnWorkingTimePreference(clickOnWorkingTimePreference);
		
		//Click on add employee
		EventHandler<ActionEvent> clickOnAddEmployee = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(view.getAllDepartmentsWithFreeRolesComboBox().getValue() == null) //Not selected department
					view.showInCenter(new Text("You need to select to which department you want to add the employee"));
				else {
					if(view.getAllFreeRolesInDepartmentComboBox().getValue() == null) //Not selected role
						view.showInCenter(new Text("You need to select to which role you want to add the employee"));
					else {
						if(view.getEmployeeTypes().getSelectedToggle() == null) // Not selected type of salary
							view.showInCenter(new Text("You need to select the salary type of employee"));
						else {
							if(view.getEmployeeWorkingTimePreference().getSelectedToggle() == null)// Not selected work preference
								view.showInCenter(new Text("You need to select work preference"));
							else {
								if((view.getTextFieldOfPreferStartingWorkTime().getText() == null) || (view.getTextFieldOfPreferStartingWorkTime().getText().length() == 0)) //Not fill starting work time
									view.showInCenter(new Text("You need to fill the prefer starting time of work"));
								else {
									Employee newEmployee = null;
									Preference newPreference = new Preference(1);
									if(view.getEmployeeWorkTimePreferenceTwoButton().isSelected())
										newPreference = new Preference(2);
									if(view.getEmployeeWorkTimePreferenceThreeButton().isSelected())
										newPreference = new Preference(3);
									if(view.getEmployeeWorkTimePreferenceFourButton().isSelected())
										newPreference = new Preference(4);
									String nameOfEmployee = view.getTextFieldOfEmployeeName().getText();
									int timeOfStartWorking = Integer.parseInt(view.getTextFieldOfPreferStartingWorkTime().getText());
									Role role = company.getDepartmentByName(view.getAllDepartmentsWithFreeRolesComboBox().getValue()).getRoleByName(view.getAllFreeRolesInDepartmentComboBox().getValue());
									boolean canChangeWorkTime = role.getCanChangeWorkingTime();
									try {
										if(view.getEmployeeTypeOneButton().isSelected()) // Monthly
											newEmployee = new EmployeeMonthly(nameOfEmployee, newPreference, timeOfStartWorking, role, canChangeWorkTime);
										if(view.getEmployeeTypeTwoButton().isSelected()) // Per hour
											newEmployee = new EmployeeByHours(nameOfEmployee, newPreference, timeOfStartWorking, role, canChangeWorkTime);
										if(view.getEmployeeTypeThreeButton().isSelected()) // Monthly plus bonus
											newEmployee = new EmployeeMonthlyPlusBonus(nameOfEmployee, newPreference, timeOfStartWorking, role, canChangeWorkTime);
										role.addEmployee(newEmployee);
										company.setThereIsEmployee(true);
										view.showInCenter(new Text("Succeed, employee added successfully"));
									}
									catch(Exception e) {
										view.showInCenter(new Text(e.getMessage()));
									}
								}
							}
						}						
					}
				}
				view.resetMenuSelection();
			}
		};
		view.clickOnAddEmployee(clickOnAddEmployee);
		
		//Click on show company in menu
		EventHandler<ActionEvent> clickOnShowCompanyInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(company == null)
					view.showInCenter(new Text("There is no company yet"));
				else
					view.showInCenter(new Text(company.toString()));
			}
		};
		view.clickOnShowCompanyInMenu(clickOnShowCompanyInMenu);
		
		//Click on change work method - role in menu
		EventHandler<ActionEvent> clickOnChangeWorkMethodRoleInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(company == null) //Check if there is company
					view.showInCenter(new Text("There is no company yet"));
				else {
					if((company.getAllDepartments() == null) || (company.getAllDepartments().size() == 0)) //Check if there is department
						view.showInCenter(new Text("There is no department yet"));
					else {
						if(company.checkIfThereAreRoles()) { //Check if there is role
							if(company.getThereIsEmployee()) { //Check if there is employee
								//Make the new buttons and details to show
								GridPane newDetailsToShow = new GridPane();
								newDetailsToShow.setHgap(10);
								newDetailsToShow.setVgap(10);
								newDetailsToShow.add(new Text("In which department the role:"), 1, 0);
								view.clearAllDepartmentsNamesToChangeWorkMethodRole();
								for(int i = 0; i < company.getAllDepartments().size(); i++) {
									if((company.getAllDepartments().get(i).getAllRoles() != null) && (company.getAllDepartments().get(i).getAllRoles().size() != 0))
										view.addNameToAllDepartmentsNamesToChangeWorkMethodRole(company.getAllDepartments().get(i).getNameOfDepartment());
								}
								newDetailsToShow.add(view.getAllDepartmentsNamesToChangeWorkMethodRole(), 2, 0);
								newDetailsToShow.add(new Text("(Must choose one from the list)"), 3, 0);
								newDetailsToShow.add(new Text("To which role you want to change method:"), 1, 1);
								view.clearAllRolesNamesToChangeWorkMethodRole();
								newDetailsToShow.add(view.getAllRolesNamesToChangeWorkMethodRole(), 2, 1);
								newDetailsToShow.add(new Text("(Must choose one from the list)"), 3, 1);
								newDetailsToShow.add(new Text("Can change work time:"), 1, 2);
								view.clearCanChangeWorkTimeCheckBox();
								newDetailsToShow.add(view.getCanChangeWorkTimeCheckBox(), 2, 2);
								newDetailsToShow.add(new Text("Start prefer work time:"), 1, 3);
								view.clearNewStartWorkHourTextField();
								newDetailsToShow.add(view.getNewStartWorkHourTextField(), 2, 3);
								newDetailsToShow.add(new Text("(0-15)"), 3, 3);
								newDetailsToShow.add(view.getCheckChangeWorkMethodRoleButton(), 1, 4);
								newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
								view.showInCenter(newDetailsToShow);
							}
							else
								view.showInCenter(new Text("There is no employee yet"));
						}
						else
							view.showInCenter(new Text("There is no role yet"));
					}
				}
			}
		};
		view.clickOnChangeWorkMethodRoleInMenu(clickOnChangeWorkMethodRoleInMenu);
		
		//Click on all departments names to change work method - role
		EventHandler<ActionEvent> clickOnAllDepartmentsNamesToChangeWorkMethodRole = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.clearAllRolesNamesToChangeWorkMethodRole();
				if(view.getAllDepartmentsNamesToChangeWorkMethodRole().getValue() != null) {
					Department selectedDepartment = company.getDepartmentByName(view.getAllDepartmentsNamesToChangeWorkMethodRole().getValue());
					for(int i = 0; i < selectedDepartment.getAllRoles().size(); i++)
						view.addNameToAllRolesNamesToChangeWorkMethodRole(selectedDepartment.getAllRoles().get(i).getNameOfRole());
				}
			}
		};
		view.clickOnAllDepartmentsNamesToChangeWorkMethodRole(clickOnAllDepartmentsNamesToChangeWorkMethodRole);
		
		//Click on check change work method role button
		EventHandler<ActionEvent> clickOnCheckChangeWorkMethodRoleButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					if(view.getAllDepartmentsNamesToChangeWorkMethodRole().getValue() != null) {//Check if department selected
						if(view.getAllRolesNamesToChangeWorkMethodRole().getValue() != null) {//Check if role selected
							Department selectedDepartment = company.getDepartmentByName(view.getAllDepartmentsNamesToChangeWorkMethodRole().getValue());
							Role selectedRole = selectedDepartment.getRoleByName(view.getAllRolesNamesToChangeWorkMethodRole().getValue());
							if(selectedRole.checkChangeRoleWorkMethod(view.getCanChangeWorkTimeCheckBox().isSelected(), Integer.parseInt(view.getNewStartWorkHourTextField().getText()))) {
								selectedRole.synchAllCanChangeWorkingTime(view.getCanChangeWorkTimeCheckBox().isSelected());
								if(selectedRole.getEmployeeInRole() != null)
									selectedRole.getEmployeeInRole().setTimeOfStartWorking(Integer.parseInt(view.getNewStartWorkHourTextField().getText()));
								view.showInCenter(new Text("The change occur, because it's worthy"));
							}
							else
								view.showInCenter(new Text("The change wasn't occur, because it's not worthy"));
						}
						else
							view.showInCenter(new Text("You need to select the role you want to change"));
					}
					else
						view.showInCenter(new Text("You need to select the department that the role in"));
				}
				catch(Exception e) {
					view.showInCenter(new Text("Invalid start work hour"));
				}
				view.resetMenuSelection();
			}
		};
		view.clickOnCheckChangeWorkMethodRoleButton(clickOnCheckChangeWorkMethodRoleButton);
		
		//Click on check change work method department button in menu
		EventHandler<ActionEvent> clickOnCheckChangeWorkMethodDepartmentButtonInMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(company == null) //Check if there is company
					view.showInCenter(new Text("There is no company yet"));
				else {
					if((company.getAllDepartments() == null) || (company.getAllDepartments().size() == 0)) //Check if there is department
						view.showInCenter(new Text("There are no departments yet"));
					else {
						//Make the new buttons and details to show
						GridPane newDetailsToShow = new GridPane();
						newDetailsToShow.setHgap(10);
						newDetailsToShow.setVgap(10);
						newDetailsToShow.add(new Text("To which role you want to change method:"), 1, 0);
						view.clearAllDepartmentsNamesToChangeWorkMethodDepartment();
						for(int i = 0; i < company.getAllDepartments().size(); i++)
							view.addNameToAllDepartmentsNamesToChangeWorkMethodDepartment(company.getAllDepartments().get(i).getNameOfDepartment());
						newDetailsToShow.add(view.getAllDepartmentsNamesToChangeWorkMethodDepartment(), 2, 0);
						newDetailsToShow.add(new Text("(Must choose one from the list)"), 3, 0);
						newDetailsToShow.add(new Text("Can work from home?"), 1, 1);
						view.clearCanWorkFromHomeDepartmentCheckBox();
						newDetailsToShow.add(view.getCanWorkFromHomeDepartmentCheckBox(), 2, 1);
						if(view.getCanWorkFromHomeDepartmentCheckBox().isSelected()) {
							view.getChaneStartWorkTimeDepartmentText().setVisible(false);
							view.getWantToChaneStartWorkTimeDepartmentCheckBox().setVisible(false);
						}
						else {
							view.getChaneStartWorkTimeDepartmentText().setVisible(true);
							view.getWantToChaneStartWorkTimeDepartmentCheckBox().setVisible(true);
						}
						newDetailsToShow.add(view.getChaneStartWorkTimeDepartmentText(), 1, 2);
						view.clearWantToChaneStartWorkTimeDepartmentCheckBox();
						newDetailsToShow.add(view.getWantToChaneStartWorkTimeDepartmentCheckBox(), 2, 2);
						view.clearStartWorkTimeDepartmentTextField();
						if(view.getWantToChaneStartWorkTimeDepartmentCheckBox().isSelected()) {
							view.getStartWorkTimeDepartmentText().setVisible(true);
							view.getStartWorkTimeDepartmentTextField().setVisible(true);
							view.getPossibleStartWorkingTimeDepartmentText().setVisible(true);
						}
						else {
							view.getStartWorkTimeDepartmentText().setVisible(false);
							view.getStartWorkTimeDepartmentTextField().setVisible(false);
							view.getStartWorkTimeDepartmentTextField().setText("8");
							view.getPossibleStartWorkingTimeDepartmentText().setVisible(false);
						}
						newDetailsToShow.add(view.getStartWorkTimeDepartmentText(), 3, 2);
						newDetailsToShow.add(view.getStartWorkTimeDepartmentTextField(), 4, 2);
						newDetailsToShow.add(view.getPossibleStartWorkingTimeDepartmentText(), 5, 2);
						newDetailsToShow.add(view.getCheckChangeWorkMethodDepartmentButton(), 1, 3);
						newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
						view.showInCenter(newDetailsToShow);
					}
				}
			}
		};
		view.clickOnCheckChangeWorkMethodDepartmentButtonInMenu(clickOnCheckChangeWorkMethodDepartmentButtonInMenu);
		
		//Click on can work from home check box in change work method department
		EventHandler<ActionEvent> clickOnCanWorkFromHomeCheckBoxInChangeWorkMethodDepartment = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(view.getCanWorkFromHomeDepartmentCheckBox().isSelected()) {
					view.getChaneStartWorkTimeDepartmentText().setVisible(false);
					view.getWantToChaneStartWorkTimeDepartmentCheckBox().setVisible(false);
					view.clearWantToChaneStartWorkTimeDepartmentCheckBox();
					view.getStartWorkTimeDepartmentText().setVisible(false);
					view.getStartWorkTimeDepartmentTextField().setVisible(false);
					view.getStartWorkTimeDepartmentTextField().setText("8");
					view.getPossibleStartWorkingTimeDepartmentText().setVisible(false);
				}
				else {
					view.getChaneStartWorkTimeDepartmentText().setVisible(true);
					view.getWantToChaneStartWorkTimeDepartmentCheckBox().setVisible(true);
					view.clearWantToChaneStartWorkTimeDepartmentCheckBox();
					view.getStartWorkTimeDepartmentTextField().setText("8");
				}
			}
		};
		view.clickOnCanWorkFromHomeCheckBoxInChangeWorkMethodDepartment(clickOnCanWorkFromHomeCheckBoxInChangeWorkMethodDepartment);
		
		//Click on change start work time check box in change work method department
		EventHandler<ActionEvent> clickOnChangeStartWorkTimeCheckBoxInChangeWorkMethodDepartment = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(view.getWantToChaneStartWorkTimeDepartmentCheckBox().isSelected()) {
					view.getStartWorkTimeDepartmentText().setVisible(true);
					view.clearStartWorkTimeDepartmentTextField();
					view.getStartWorkTimeDepartmentTextField().setVisible(true);
					view.getPossibleStartWorkingTimeDepartmentText().setVisible(true);
				}
				else {
					view.getStartWorkTimeDepartmentText().setVisible(false);
					view.getStartWorkTimeDepartmentTextField().setVisible(false);
					view.getStartWorkTimeDepartmentTextField().setText("8");
					view.getPossibleStartWorkingTimeDepartmentText().setVisible(false);
				}
			}
		};
		view.clickOnChangeStartWorkTimeCheckBoxInChangeWorkMethodDepartment(clickOnChangeStartWorkTimeCheckBoxInChangeWorkMethodDepartment);
		
		//Click on check the change button department
		EventHandler<ActionEvent> clickOnCheckTheChangeButtonDepartment = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(view.getAllDepartmentsNamesToChangeWorkMethodDepartment().getValue() != null) {
					try {
						double efficiencyAfterChange = 0;
						double efficiencyBeforeChange = 0;
						Department selectedDepartment = company.getDepartmentByName(view.getAllDepartmentsNamesToChangeWorkMethodDepartment().getValue());
						if(selectedDepartment.getAllRoles() != null) {//There are rolls
							for(int i = 0; i < selectedDepartment.getAllRoles().size(); i++) { //Move on all roles
								if(selectedDepartment.getAllRoles().get(i).getEmployeeInRole() != null) {//There is employee
									if(view.getWantToChaneStartWorkTimeDepartmentCheckBox().isSelected())
										efficiencyAfterChange += selectedDepartment.getAllRoles().get(i).getEmployeeInRole().calculateEfficiencyAccordingToChange(view.getCanWorkFromHomeDepartmentCheckBox().isSelected(), Integer.parseInt(view.getStartWorkTimeDepartmentTextField().getText()));
									else
										efficiencyAfterChange += selectedDepartment.getAllRoles().get(i).getEmployeeInRole().calculateEfficiencyAccordingToChange(view.getCanWorkFromHomeDepartmentCheckBox().isSelected(), selectedDepartment.getStartingWorkTime());
									efficiencyBeforeChange += selectedDepartment.getAllRoles().get(i).getEmployeeInRole().calculateEfficiency();
								}
							}
						}
						if(efficiencyBeforeChange <= efficiencyAfterChange) { //Change is efficiency
							selectedDepartment.synchAllCanChangeWorkingTime(view.getCanWorkFromHomeDepartmentCheckBox().isSelected());
							if(view.getWantToChaneStartWorkTimeDepartmentCheckBox().isSelected())
								selectedDepartment.setStartingWorkTime(Integer.parseInt(view.getStartWorkTimeDepartmentTextField().getText()));								
							view.showInCenter(new Text("The change was occur because it's worthy, change from " + efficiencyBeforeChange + " to " + efficiencyAfterChange));
						}
						else //Change is not efficiency
							view.showInCenter(new Text("The change wasn't occur because it's not worthy, it will cause to decrese from "  + efficiencyBeforeChange + " to " + efficiencyAfterChange ));
					}
					catch(Exception e) {
						view.showInCenter(new Text("Invalid start working time"));
					}
				}
				else
					view.showInCenter(new Text("You need to select department"));
				view.resetMenuSelection();
			}
		};
		view.clickOnCheckTheChangeButtonDepartment(clickOnCheckTheChangeButtonDepartment);
		
		//Click on show profit or loss in the menu
		EventHandler<ActionEvent> clickOnShowProfitOrLossInTheMenu = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(company != null) {
					if((company.getAllDepartments() != null) && (company.getAllDepartments().size() > 0)) {
						if(company.getThereIsEmployee()) {
							//Make the new buttons and details to show
							GridPane newDetailsToShow = new GridPane();
							newDetailsToShow.setHgap(10);
							newDetailsToShow.setVgap(10);
							newDetailsToShow.add(new Text("To whom you want to show profit / loss:"), 1, 0);
							view.getShowOptionsToProfitOrLossComboBox().setValue(null);
							newDetailsToShow.add(view.getShowOptionsToProfitOrLossComboBox(), 2, 0);
							newDetailsToShow.add(new Text("(Must choose one from the list)"), 3, 0);
							view.clearAllDepartmentsComboBox();
							for(int i = 0; i < company.getAllDepartments().size(); i++)
								view.addNameToAllDepartmentsComboBox(company.getAllDepartments().get(i).getNameOfDepartment());
							view.getToWhichDepartmentText().setVisible(false);
							newDetailsToShow.add(view.getToWhichDepartmentText(), 1, 1);
							view.getAllDepartmentsComboBox().setVisible(false);
							newDetailsToShow.add(view.getAllDepartmentsComboBox(), 2, 1);
							view.clearAllEmployeesComboBox();
							for(int departmentNum = 0; departmentNum < company.getAllDepartments().size(); departmentNum++) {
								for(int roleNum = 0; (company.getAllDepartments().get(departmentNum).getAllRoles() != null) && (roleNum < company.getAllDepartments().get(departmentNum).getAllRoles().size()); roleNum++) {
									if(company.getAllDepartments().get(departmentNum).getAllRoles().get(roleNum).getEmployeeInRole() != null)
										view.addNameToAllEmployeesComboBox(company.getAllDepartments().get(departmentNum).getAllRoles().get(roleNum).getEmployeeInRole().getNameOfEmployee());
								}
							}
							view.getToWhichEmployeeText().setVisible(false);
							newDetailsToShow.add(view.getToWhichEmployeeText(), 1, 2);
							view.getAllEmployeesComboBox().setVisible(false);
							newDetailsToShow.add(view.getAllEmployeesComboBox(), 2, 2);
							newDetailsToShow.add(view.getShowProfitOrLossButton(), 1, 3);
							newDetailsToShow.setAlignment(Pos.CENTER_LEFT);
							view.showInCenter(newDetailsToShow);
						}
						else
							view.showInCenter(new Text("There is no employee"));
					}
					else
						view.showInCenter(new Text("There is no department"));
				}
				else
					view.showInCenter(new Text("There is no company"));
			}
		};
		view.clickOnShowProfitOrLossInTheMenu(clickOnShowProfitOrLossInTheMenu);
		
		//Click on show options to profit or loss combo box
		EventHandler<ActionEvent> clickOnShowOptionsToProfitOrLossComboBox = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.getAllDepartmentsComboBox().setValue(null);
				view.getAllEmployeesComboBox().setValue(null);
				String choiceOption = view.getShowOptionsToProfitOrLossComboBox().getValue();
				if((choiceOption == null) || (choiceOption.equals("Company"))) {
					view.getToWhichDepartmentText().setVisible(false);
					view.getAllDepartmentsComboBox().setVisible(false);
					view.getToWhichEmployeeText().setVisible(false);
					view.getAllEmployeesComboBox().setVisible(false);
				}
				else {
					if(choiceOption.equals("Department")) {
						view.getToWhichDepartmentText().setVisible(true);
						view.getAllDepartmentsComboBox().setVisible(true);
						view.getToWhichEmployeeText().setVisible(false);
						view.getAllEmployeesComboBox().setVisible(false);
					}
					if(choiceOption.equals("Employee")) {
						view.getToWhichDepartmentText().setVisible(false);
						view.getAllDepartmentsComboBox().setVisible(false);
						view.getToWhichEmployeeText().setVisible(true);
						view.getAllEmployeesComboBox().setVisible(true);
					}
				}
			}
		};
		view.clickOnShowOptionsToProfitOrLossComboBox(clickOnShowOptionsToProfitOrLossComboBox);
		
		//Click on button check loss or profit
		EventHandler<ActionEvent> clickOnButtonCheckLossOrProfit = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				double efficiency = 0;
				String selectedComponent = view.getShowOptionsToProfitOrLossComboBox().getValue();
				if(selectedComponent != null) { // Check if select component
					switch(selectedComponent) {
					case "Company":
						for(int departmentNum = 0; departmentNum < company.getAllDepartments().size(); departmentNum++) { //Move on all departments
							for(int roleNum = 0; roleNum < company.getAllDepartments().get(departmentNum).getAllRoles().size(); roleNum++) { //Move on all roles
								if(company.getAllDepartments().get(departmentNum).getAllRoles().get(roleNum).getEmployeeInRole() != null)
									efficiency += company.getAllDepartments().get(departmentNum).getAllRoles().get(roleNum).getEmployeeInRole().calculateEfficiency();
							}
						}
						view.showInCenter(new Text("The efficiency of the company is: " + efficiency));
						break;
					case "Department":
						if(view.getAllDepartmentsComboBox().getValue() != null) {
							String nameOfDepartment = view.getAllDepartmentsComboBox().getValue();
							Department selectedDepartment = company.getDepartmentByName(nameOfDepartment);
							for(int roleNum = 0; roleNum < selectedDepartment.getAllRoles().size(); roleNum++) { //Move on all roles
								if(selectedDepartment.getAllRoles().get(roleNum).getEmployeeInRole() != null)
									efficiency += selectedDepartment.getAllRoles().get(roleNum).getEmployeeInRole().calculateEfficiency();
							}
							view.showInCenter(new Text("The efficiency of the department " + selectedDepartment.getNameOfDepartment() + " is: " + efficiency));
						}
						else
							view.showInCenter(new Text("You need to select department"));
						break;
					case "Employee":
						if(view.getAllEmployeesComboBox().getValue() != null) {
							String emloyeeName = view.getAllEmployeesComboBox().getValue();
							efficiency = company.searchEmployeeByName(emloyeeName).calculateEfficiency();
							view.showInCenter(new Text("The efficiency of the department " + company.searchEmployeeByName(emloyeeName).getNameOfEmployee() + " is: " + efficiency));
						}
						else
							view.showInCenter(new Text("You need to select employee"));
						break;
					}
				}
				else
					view.showInCenter(new Text("You need to select component"));
				view.resetMenuSelection();
			}
		};
		view.clickOnButtonCheckLossOrProfit(clickOnButtonCheckLossOrProfit);
		
		//Click on exit
		EventHandler<WindowEvent> clickOnExit = new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				try {
					company.saveData();
				}
				catch(Exception e) {
					
				}
			}
		};
		stage.setOnCloseRequest(clickOnExit);
	}
}
