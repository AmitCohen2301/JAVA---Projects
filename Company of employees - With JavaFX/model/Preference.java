package AmitCohen.model;

import java.io.Serializable;

public class Preference implements Serializable {
	protected int workPreference; // 1-Start early ,2-Start lately ,3-stay like before ,4-work from home
	
	//Constructors
	public Preference(int workPreference) {
		setWorkPreference(workPreference);
	}
	
	//Sets
	private void setWorkPreference(int workPreference) {
		this.workPreference = workPreference;
	}
	
	//Gets
	public int getWorkPreference() {
		return workPreference;
	}
}
