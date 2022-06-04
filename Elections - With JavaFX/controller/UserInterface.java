package controller;

import java.util.Scanner;

public interface UserInterface {
	void showMessage(String msg) throws Exception;
	String getString(Scanner s) throws Exception;
	int getInt(Scanner s) throws Exception;
	boolean getBoolean(Scanner s) throws Exception;
}