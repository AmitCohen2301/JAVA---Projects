package AmitCohen;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlCommands {
	static final String DB_URL_FOR_MYSQL = "jdbc:mysql://localhost:3306/my_db";
	static final String USER_NAME_FOR_MYSQL = "Your user name to database in mysql";
	static final String PASSWORD_FOR_MYSQL = "Your password to database in mysql";
	
//Gets
	public static String getDbUrlForMySQL() {
		return DB_URL_FOR_MYSQL; 
	}
	
	public static String getUserNameForMySQL() {
		return USER_NAME_FOR_MYSQL; 
	}
	
	public static String getPasswordForMySQL() {
		return PASSWORD_FOR_MYSQL; 
	}
	
//Make connection
	public static Connection makeConnectionToMySQL() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			return DriverManager.getConnection(DB_URL_FOR_MYSQL, USER_NAME_FOR_MYSQL, PASSWORD_FOR_MYSQL);
		}
		catch (Exception e)
		{
			throw new Exception("Data base not found");
		}
	}
}
