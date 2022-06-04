package AmitCohen.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Department implements CanChangeWorkingTimeable, Serializable  {
	protected String nameOfDepartment;
	protected ArrayList<Role> allRoles;
	protected boolean employeeCanChangeWorkTime;
	protected Company companyDepartment;
	protected int startingWorkTime;
	
	//Constructors
	public Department(String nameOfDepartment, boolean employeeCanChangeWorkTime, Company companyDepartment, int startingWorkTime) throws Exception {
		this(nameOfDepartment, null, employeeCanChangeWorkTime, companyDepartment, startingWorkTime);
	}
	public Department(String nameOfDepartment, ArrayList<Role> allRoles, boolean employeeCanChangeWorkTime, Company companyDepartment, int startingWorkTime) throws Exception {
		setNameOfDepartment(nameOfDepartment);
		setAllRoles(allRoles);
		synchAllCanChangeWorkingTime(employeeCanChangeWorkTime);
		setCompanyDepartment(companyDepartment);
		setStartingWorkTime(startingWorkTime);
	}
	
	//Sets
	private void setNameOfDepartment(String nameOfDepartment) throws Exception {
		if((nameOfDepartment == null) || (nameOfDepartment.length() < 1))
			throw new Exception("Name of department should contain at least one char");
		this.nameOfDepartment = nameOfDepartment;
	}
	private void setAllRoles(ArrayList<Role> allRoles) {
		this.allRoles = new ArrayList<Role>();
		if(allRoles != null) {
			for(int i = 0; i < allRoles.size(); i++)
				this.allRoles.add(allRoles.get(i));
		}
	}
	public void setCompanyDepartment(Company companyDepartment) {
		this.companyDepartment = companyDepartment;
	}
	public void setStartingWorkTime(int startingWorkTime) throws Exception {
		if((startingWorkTime < 0) || (startingWorkTime > 15))
			throw new Exception("Invalid starting work time");
		this.startingWorkTime = startingWorkTime;
	}
	
	//Gets
	public String getNameOfDepartment() {
		return nameOfDepartment;
	}
	public ArrayList<Role> getAllRoles() {
		return allRoles;
	}
	public Company getCompanyDepartment() {
		return companyDepartment;
	}
	public int getStartingWorkTime() {
		return startingWorkTime;
	}
	
	//get can change working time
	@Override
	public boolean getCanChangeWorkingTime() {
		return employeeCanChangeWorkTime;
	}
	
	//Synch all can change working time
	@Override
	public void synchAllCanChangeWorkingTime(boolean employeeCanChangeWorkTime) {
		this.employeeCanChangeWorkTime = employeeCanChangeWorkTime;
		for(int i = 0; i < allRoles.size(); i++) {
			allRoles.get(i).synchAllCanChangeWorkingTime(employeeCanChangeWorkTime);
		}
	}
	
	//Add role
	public String addRole(Role roleToAdd) {
		allRoles.add(roleToAdd);
		return "Succeed, role added successfully";
	}
	
	//Check if there is free role
	public boolean checkIfThereIsFreeRole() {
		if(allRoles == null)
			return false;
		for(int i = 0; i < allRoles.size(); i++) {
			if(allRoles.get(i).getEmployeeInRole() == null)
				return true;
		}
		return false;
	}
	
	//Get role by name
	public Role getRoleByName(String roleName) {
		if(allRoles == null)
			return null;
		for(int i = 0; i < allRoles.size(); i++) {
			if(roleName.equals(allRoles.get(i).getNameOfRole()))
				return allRoles.get(i);
		}
		return null;
	}
	
	//To string
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("Department name: " + nameOfDepartment 
				+ ", has " + allRoles.size() + " role" + (allRoles.size() == 1 ? "" : "'s") + ", Can change work time: " + employeeCanChangeWorkTime + (allRoles.size() == 0 ? "\n" : ":\n"));
		for(int i = 0; i < allRoles.size(); i++)
			str.append("\t\t" + (i + 1) + ") " + allRoles.get(i).toString());
		return str.toString();
	}
}
