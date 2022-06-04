package AmitCohen.model;

import java.io.Serializable;

public class EmployeeMonthlyPlusBonus extends Employee implements Serializable {
	
	//Constructors
	public EmployeeMonthlyPlusBonus(String nameOfEmployee, Preference workPreference, int timeOfStartWorking) throws Exception {
		super(nameOfEmployee, workPreference, timeOfStartWorking);
	}
	public EmployeeMonthlyPlusBonus(String nameOfEmployee, Preference workPreference, int timeOfStartWorking, Role role, boolean canChangeWorkTime) throws Exception {
		super(nameOfEmployee, workPreference, timeOfStartWorking, role, canChangeWorkTime);
	}
	
	//To string
	@Override
	public String toString() {
		return super.toString() + ", Salary type: monthly plus bonus";
	}
}
