package ems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	
	    // change user & pass if needed
	    private static final String URL = "jdbc:mysql://localhost:3306/feb?useSSL=false&serverTimezone=UTC";
	    private static final String USER = "root";
	    private static final String PASS = "root";

	    static {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            // Driver not found — ensure mysql-connector-j jar is on classpath
	            e.printStackTrace();
	        }
	    }

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USER, PASS);
	    }
	}


