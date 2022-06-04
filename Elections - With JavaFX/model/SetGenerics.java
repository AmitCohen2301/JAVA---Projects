package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class SetGenerics<T> implements Serializable{
	private ArrayList<T> infoOfAllTypes;
	
	//Constructor
	public SetGenerics(){
		infoOfAllTypes = new ArrayList<T>();
	}
	
	//Add generic
	public boolean addGenerics(T type) throws Exception {
		if((type.getClass().getSimpleName().equals("Citizen")) 
				|| (type.getClass().getSimpleName().equals("Soldier"))) {//Checks for all types of citizens
			if((((Citizen)type).getIdNum() == null) || (((Citizen)type).getIdNum().length() != 9))// in-valid ID number
				throw new Exception("Invalid ID number");
			if(LocalDate.now().getYear() - ((Citizen)type).getYearOfBirth() < 18)//too young to vote
				throw new Exception("Citizen is too young to vote");
			if(((Citizen)type).getInInsulation()) {//In insulation
				if(!((Citizen)type).getWearProtection())//Check if wear protection
					throw new Exception("Citizen is in insulation and not wear protection so he can't vote!");
			}
		}
		
		if(infoOfAllTypes.size() == 0) {//Empty list - no objects until now
			infoOfAllTypes.add(type);
			return true;
		}
		else {//Not empty list
			for(int i = 0; i < infoOfAllTypes.size(); i++) {//Check if already exist
				if(infoOfAllTypes.get(i).equals((T)type)) {
					throw new Exception(type.getClass().getSimpleName() + " is already exist");//Throw exception (exist)
				}
			}
			infoOfAllTypes.add(type);
			return true;
		}
	}
	
	//Remove last
	public boolean removeLastGenerics() {
		infoOfAllTypes.remove(infoOfAllTypes.size() - 1);
		return true;
	}
}
