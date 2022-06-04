package AmitCohen.model;

import java.io.Serializable;

public class Role implements CanChangeWorkingTimeable, Serializable  {
	protected String nameOfRole;
	protected Employee employeeInRole;
	protected boolean employeeCanChangeWorkTime;
	protected Department departmentOfRole;
	
	//Constructors
	public Role(String nameOfRole, boolean employeeCanChangeWorkTime) throws Exception {
		this(nameOfRole, employeeCanChangeWorkTime, null);
	}
	public Role(String nameOfRole, boolean employeeCanChangeWorkTime, Employee employeeInRole) throws Exception {
		setNameOfRole(nameOfRole);
		synchAllCanChangeWorkingTime(employeeCanChangeWorkTime);
		setEmployeeInRole(employeeInRole);
		setDepartmentOfRole(null);
	}
	public Role(String nameOfRole, boolean employeeCanChangeWorkTime, Employee employeeInRole, Department departmentOfRole) throws Exception {
		if(departmentOfRole != null) {
			if(departmentOfRole.getAllRoles() != null) {
				for(int i = 0; i < departmentOfRole.getAllRoles().size(); i++) {
					if(departmentOfRole.getAllRoles().get(i).getNameOfRole().equals(nameOfRole))
						throw new Exception("There is already role with that name in that department, please select another name");
				}
			}
		}
		setNameOfRole(nameOfRole);
		synchAllCanChangeWorkingTime(employeeCanChangeWorkTime);
		setEmployeeInRole(employeeInRole);
		setDepartmentOfRole(departmentOfRole);
	}
	
	//Sets
	private void setNameOfRole(String nameOfRole) throws Exception {
		if((nameOfRole == null) || (nameOfRole.length() < 1))
			throw new Exception("Name of role should contain at least one char");
		this.nameOfRole = nameOfRole;
	}
	private void setEmployeeInRole(Employee employeeInRole) {
		this.employeeInRole = employeeInRole;
	}
	private void setDepartmentOfRole(Department departmentOfRole) {
		this.departmentOfRole = departmentOfRole;
	}
	
	//Gets
	public String getNameOfRole() {
		return nameOfRole;
	}
	public Employee getEmployeeInRole() {
		return employeeInRole;
	}
	public Department getDepartmentOfRole() {
		return departmentOfRole;
	}
	
	//Get can change working time
	@Override
	public boolean getCanChangeWorkingTime() {
		return employeeCanChangeWorkTime;
	}
	
	//Check if change role work method is worthy
	public boolean checkChangeRoleWorkMethod(boolean canChangeStartWorkTime, int newStartingWorkTime) throws Exception {
		if(employeeInRole != null) {
			if(employeeInRole.calculateEfficiency() <= employeeInRole.calculateEfficiencyAccordingToChangeEmployee(canChangeStartWorkTime, newStartingWorkTime))
				return true;
			else
				return false;
		}
		return true;
	}
	
	//Synch all can change working time
	@Override
	public void synchAllCanChangeWorkingTime(boolean employeeCanChangeWorkTime) {
		if((departmentOfRole != null) && (departmentOfRole.getCanChangeWorkingTime() == false)) // Check if there is department, and can change working time
			this.employeeCanChangeWorkTime = departmentOfRole.getCanChangeWorkingTime();
		else
			this.employeeCanChangeWorkTime = employeeCanChangeWorkTime;
		if(employeeInRole != null)//Need to update employee
			employeeInRole.synchAllCanChangeWorkingTime(this.employeeCanChangeWorkTime);
	}
	
	//Add employee
	public void addEmployee(Employee newEmployee) {
		setEmployeeInRole(newEmployee);
	}
	
	//To string
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("Role name: " + nameOfRole + ", Can change work time: " + employeeCanChangeWorkTime + ", " + (employeeInRole == null ? "empty": employeeInRole.toString()) + "\n");
		return str.toString();
	}
}
