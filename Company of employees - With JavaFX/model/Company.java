package AmitCohen.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Company implements Serializable {
	protected String nameOfCompany;
	protected ArrayList<Department> allDepartments;
	protected boolean thereIsEmployee;
	
	//Constructors
	public Company(String nameOfCompany) throws Exception {
		this(nameOfCompany, null);
		setThereIsEmployee(false);
	}
	public Company(String nameOfCompany, ArrayList<Department> allDepartments) throws Exception {
		setNameOfCompany(nameOfCompany);
		setAllDepartments(allDepartments);
	}
	
	//Sets
	private void setNameOfCompany(String nameOfCompany) throws Exception {
		if((nameOfCompany == null) || (nameOfCompany.length() < 1))
			throw new Exception("Name of company should contain at least one char");
		this.nameOfCompany = nameOfCompany;
	}
	private void setAllDepartments(ArrayList<Department> allDepartments) {
		this.allDepartments = new ArrayList<Department>();
		if(allDepartments != null) {
			for(int i = 0; i < allDepartments.size(); i++)
				this.allDepartments.add(allDepartments.get(i));
		}
	}
	public void setThereIsEmployee(boolean thereIsEmployee) {
		this.thereIsEmployee = thereIsEmployee;
	}
	
	//Gets
	public String getNameOfCompany() {
		return nameOfCompany;
	}
	public ArrayList<Department> getAllDepartments() {
		return allDepartments;
	}
	public boolean getThereIsEmployee() {
		return thereIsEmployee;
	}
	
	//Add department
	public String addDepartment(String departmentName, Boolean canChangeWorkingTime, int startingWorkTime) throws Exception {
		if(allDepartments != null) {
			for(int i = 0; i < allDepartments.size(); i++) {
				if(allDepartments.get(i).getNameOfDepartment().equals(departmentName))
					throw new Exception("There is already department with that name, please select another name");
			}
		}
		Department newDepartment = new Department(departmentName, canChangeWorkingTime, this, startingWorkTime);
		allDepartments.add(newDepartment);
		return "Succeed, Department added successfully";
	}
	
	//Get all departments with at least one free role in ArrayList
	public ArrayList<Department> getAllDepartmentWithFreeRoles() {
		ArrayList<Department> departmentsWithFreeRoles = new ArrayList<Department>();
		for(int i = 0; i < allDepartments.size(); i++) {
			if(allDepartments.get(i).checkIfThereIsFreeRole())
				departmentsWithFreeRoles.add(allDepartments.get(i));
		}
		return departmentsWithFreeRoles;
	}
	
	//Get department by name
	public Department getDepartmentByName(String name) {
		for(int i = 0; (allDepartments != null) && (i < allDepartments.size()); i++) {
			if(name.equals(allDepartments.get(i).getNameOfDepartment()))
					return allDepartments.get(i);
		}
		return null;
	}
	
	//Check if there are roles
	public boolean checkIfThereAreRoles() {
		if((allDepartments == null) || (allDepartments.size() == 0))
			return false;
		for(int i = 0; i < allDepartments.size(); i++) {
			if((allDepartments.get(i).getAllRoles() != null) && (allDepartments.get(i).getAllRoles().size() != 0))
				return true;
		}
		return false;
	}
	
	//Search employee by name
	public Employee searchEmployeeByName(String nameOfEmployee) {
		if(allDepartments == null)
			return null;
		for(int departmentNum = 0; departmentNum < allDepartments.size(); departmentNum++) { // Move on all departments
			for(int roleNum = 0; (allDepartments.get(departmentNum).getAllRoles() != null) && (roleNum < allDepartments.get(departmentNum).getAllRoles().size()); roleNum++) {
				if((allDepartments.get(departmentNum).getAllRoles().get(roleNum).getEmployeeInRole() != null) && (allDepartments.get(departmentNum).getAllRoles().get(roleNum).getEmployeeInRole().getNameOfEmployee().equals(nameOfEmployee)))
					return allDepartments.get(departmentNum).getAllRoles().get(roleNum).getEmployeeInRole();
			}
		}
		return null;
	}
	
	//Load data
	public static Company loadData() throws IOException, ClassNotFoundException {
		File file = new File("OldCompany.dat");
		file.setReadable(true);
		ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(file));
		Company company = (Company)inFile.readObject();
		inFile.close();
		return company;
	}
	
	//Save data
	public void saveData() throws FileNotFoundException, IOException {
		File file = new File("OldCompany.dat");
		file.setWritable(true);
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(file));
		outFile.writeObject(this);
		outFile.close();
	}
	
	//To string
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("Company name: " + nameOfCompany 
				+ ", has " + allDepartments.size() + " department" + (allDepartments.size() == 1 ? "" : "'s") + (allDepartments.size() == 0 ? "\n" : ":\n"));
		for(int i = 0; i < allDepartments.size(); i++)
			str.append("\t" + (i + 1) + ") " + allDepartments.get(i).toString());
		return str.toString();
	}
}
