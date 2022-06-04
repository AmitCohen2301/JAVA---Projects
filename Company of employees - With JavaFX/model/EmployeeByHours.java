package AmitCohen.model;

import java.io.Serializable;

public class EmployeeByHours extends Employee implements Serializable  {
	
	//Constructors
	public EmployeeByHours(String nameOfEmployee, Preference workPreference, int timeOfStartWorking) throws Exception {
		super(nameOfEmployee, workPreference, timeOfStartWorking);
	}
	public EmployeeByHours(String nameOfEmployee, Preference workPreference, int timeOfStartWorking, Role role, boolean canChangeWorkTime) throws Exception {
		super(nameOfEmployee, workPreference, timeOfStartWorking, role, canChangeWorkTime);
	}
	
	//To string
	@Override
	public String toString() {
		return super.toString() + ", Salary type: per hour";
	}
}
