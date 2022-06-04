package AmitCohen.model;

import java.io.Serializable;

public abstract class Employee implements CanChangeWorkingTimeable, Serializable  {
	protected String nameOfEmployee;
	protected Preference workPreference;
	protected int preferTimeOfStartWorking;
	protected boolean employeeCanChangeWorkTime;
	protected Role roleOfEmployee;
	
	//Constructors
	public Employee(String nameOfEmployee, Preference workPreference, int timeOfStartWorking) throws Exception {
		setNameOfEmployee(nameOfEmployee);
		setWorkPreference(workPreference);
		setTimeOfStartWorking(timeOfStartWorking);
		setRoleOfEmployee(null);
		synchAllCanChangeWorkingTime(false);
	}
	public Employee(String nameOfEmployee, Preference workPreference, int timeOfStartWorking, Role role, boolean canChangeWorkTime) throws Exception {
		setNameOfEmployee(nameOfEmployee);
		setWorkPreference(workPreference);
		setTimeOfStartWorking(timeOfStartWorking);
		setRoleOfEmployee(role);
		synchAllCanChangeWorkingTime(canChangeWorkTime);
	}
	//Sets
	private void setNameOfEmployee(String nameOfEmployee) throws Exception {
		if((nameOfEmployee == null) || (nameOfEmployee.length() < 1))
			throw new Exception("Name of employee should contain at least one char");
		this.nameOfEmployee = nameOfEmployee;
	}
	private void setWorkPreference(Preference workPreference) throws Exception{
		this.workPreference = workPreference;
	}
	public void setTimeOfStartWorking(int timeOfStartWorking) throws Exception {
		if((timeOfStartWorking < 0) || (timeOfStartWorking > 23)) {
			if(timeOfStartWorking == -1) {
				if((workPreference.getWorkPreference() == 3) || (workPreference.getWorkPreference() == 4)) {
					this.preferTimeOfStartWorking = -1;
					return;
				}
			}
			throw new Exception("Invalid time of start working");
		}
		if(timeOfStartWorking > 15)
			throw new Exception("Invalid time of start working - its too late to start");
		this.preferTimeOfStartWorking = timeOfStartWorking;
	}
	private void setRoleOfEmployee(Role roleOfEmployee) {
		this.roleOfEmployee = roleOfEmployee;
	}
	
	//Gets
	public String getNameOfEmployee() {
		return nameOfEmployee;
	}
	public Preference getWorkPreference() {
		return workPreference;
	}
	public int getPreferTimeOfStartWorking() {
		return preferTimeOfStartWorking;
	}
	public Role getRoleOfEmployee() {
		return roleOfEmployee;
	}
	
	//Get can change working time
	@Override
	public boolean getCanChangeWorkingTime() {
		return employeeCanChangeWorkTime;
	}
	
	//Synch all can change working time
	@Override
	public void synchAllCanChangeWorkingTime(boolean employeeCanChangeWorkTime) {
		if((roleOfEmployee != null) && (roleOfEmployee.getCanChangeWorkingTime() != employeeCanChangeWorkTime)) // Check if there is role, and can change working time
				this.employeeCanChangeWorkTime = roleOfEmployee.getCanChangeWorkingTime();
		else
			this.employeeCanChangeWorkTime = employeeCanChangeWorkTime;
	}
	
	//Calculate efficiency
	public double calculateEfficiency() {
		int hoursToWork;
		int time;
		double efficiency = 0;
		if(workPreference.getWorkPreference() == 3) //Don't care about work time
			return 0;
		if((workPreference.getWorkPreference() == 4) && (roleOfEmployee.getCanChangeWorkingTime())) //Want to work from home, and can
			return 0.1 * 9;
		if(workPreference.getWorkPreference() == 4) //Want to work from home, but can't
			return 0;
		if(roleOfEmployee.getCanChangeWorkingTime()) //Can work when ever he want
			return 0.2 * 9;
		if(preferTimeOfStartWorking < roleOfEmployee.getDepartmentOfRole().getStartingWorkTime()) { //Want to work early
			time = preferTimeOfStartWorking;
			for(hoursToWork = 9; (time < roleOfEmployee.getDepartmentOfRole().getStartingWorkTime()) && (hoursToWork > 0); hoursToWork--) {
				efficiency -= 0.2;
				time++;
			}
		}
		else { //Want to work lately
			time = roleOfEmployee.getDepartmentOfRole().getStartingWorkTime();
			for(hoursToWork = 9; (time < preferTimeOfStartWorking) && (hoursToWork > 0); hoursToWork--) {
				efficiency -= 0.2;
				time++;
			}
		}
		while(hoursToWork > 0) { //Remain work time is equal to preference
			efficiency += 0.2;
			hoursToWork--;
		}
		return efficiency;
	}
	
	//Calculate efficiency according to change
	public double calculateEfficiencyAccordingToChange(boolean canChangeWorkTime, int startingWorkTime) throws Exception {
		if((startingWorkTime < 0) || (startingWorkTime > 15))
			throw new Exception("Invalid starting work time");
		int hoursToWork;
		int time;
		double efficiency = 0;
		if(workPreference.getWorkPreference() == 3) //Don't care about work time
			return 0;
		if((workPreference.getWorkPreference() == 4) && (canChangeWorkTime)) //Want to work from home, and can
			return 0.1 * 9;
		if(workPreference.getWorkPreference() == 4) //Want to work from home, but can't
			return 0;
		if(canChangeWorkTime) //Can work when ever he want
			return 0.2 * 9;
		if(preferTimeOfStartWorking < startingWorkTime) { //Want to work early
			time = preferTimeOfStartWorking;
			for(hoursToWork = 9; (time < startingWorkTime) && (hoursToWork > 0); hoursToWork--) {
				efficiency -= 0.2;
				time++;
			}
		}
		else { //Want to work lately
			time = startingWorkTime;
			for(hoursToWork = 9; (time < preferTimeOfStartWorking) && (hoursToWork > 0); hoursToWork--) {
				efficiency -= 0.2;
				time++;
			}
		}
		while(hoursToWork > 0) { //Remain work time is equal to preference
			efficiency += 0.2;
			hoursToWork--;
		}
		return efficiency;
	}
	
	//Calculate efficiency according to change employee start working time
	public double calculateEfficiencyAccordingToChangeEmployee(boolean canChangeStartWorkTime,int newPreferStartingWorkTime) throws Exception {
		if((newPreferStartingWorkTime < 0) || (newPreferStartingWorkTime > 15))
			throw new Exception("Invalid starting work time");
		int hoursToWork;
		int time;
		double efficiency = 0;
		if(workPreference.getWorkPreference() == 3) //Don't care about work time
			return 0;
		if((workPreference.getWorkPreference() == 4) && (canChangeStartWorkTime)) //Want to work from home, and can
			return 0.1 * 9;
		if(workPreference.getWorkPreference() == 4) //Want to work from home, but can't
			return 0;
		if(canChangeStartWorkTime) //Can work when ever he want
			return 0.2 * 9;
		if(newPreferStartingWorkTime < roleOfEmployee.getDepartmentOfRole().getStartingWorkTime()) { //Want to work early
			time = newPreferStartingWorkTime;
			for(hoursToWork = 9; (time < roleOfEmployee.getDepartmentOfRole().getStartingWorkTime()) && (hoursToWork > 0); hoursToWork--) {
				efficiency -= 0.2;
				time++;
			}
		}
		else { //Want to work lately
			time = roleOfEmployee.getDepartmentOfRole().getStartingWorkTime();
			for(hoursToWork = 9; (time < newPreferStartingWorkTime) && (hoursToWork > 0); hoursToWork--) {
				efficiency -= 0.2;
				time++;
			}
		}
		while(hoursToWork > 0) { //Remain work time is equal to preference
			efficiency += 0.2;
			hoursToWork--;
		}
		return efficiency;
	}
	
	//To string
	@Override
	public String toString() {
		return "Employee name: " + nameOfEmployee;
	}
}
