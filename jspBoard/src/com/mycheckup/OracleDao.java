package com.mycheckup;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class OracleDao {

	private static String driverClass = "oracle.jdbc.driver.OracleDriver";
	 
	private Connection con;
 
	private String url;
	private String userName;
	private String password;
 
	/**
	 * Initialize the Dao
	 * 
	 * @param propertiesFile
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void init(String propertiesFile) throws IOException,
			ClassNotFoundException {
		// load the properties
		Properties props = new Properties();
		props.load(new FileInputStream(propertiesFile));
 
		// get the properties
		url = props.getProperty("db.url");
		userName = props.getProperty("db.user");
		password = props.getProperty("db.password");
 
		// load the class
		Class.forName(driverClass);
	}
 
	/**
	 * Open the Dao Connection
	 * 
	 * @param fs
	 * @throws SQLException
	 * @throws IOException
	 */
	public void open() throws SQLException, IOException {
 
		// get the connection to the database
		con = DriverManager.getConnection(url, userName, password);
	}
 
	/**
	 * Close the connection
	 * 
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		if (con != null) {
			// close the connection
			con.close();
		}
	}
 
	/**
	 * Get the Current Time via DB Query
	 * 
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String getCurrentTime() throws SQLException, IOException {
		String dateTime = null;
		ResultSet rst = fetch("select SYSDATE from dual");
		while (rst.next()) {
			dateTime = rst.getString(1);
		}
		// close the resultset
		rst.close();
		return dateTime;
 
	}
 
	/**
	 * Fetch data
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	private ResultSet fetch(String sql) throws SQLException, IOException {
		// create the prepared stmt
		PreparedStatement ps = con.prepareStatement(sql);
 
		// execute the query
		ResultSet rs = ps.executeQuery();
		ps.close(); // close prepared statement
		return rs;
	}
 
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.out
					.println("Usage: java com.mycheckup.OracleDao <Path to your file>");
			System.exit(0);
		}
 
		// store args
		//String propertiesFile = args[0];
		
		String propertiesFile = "oracleDB.properties";
		System.out.println("File : " + propertiesFile);
 
		// execute the test functions
		OracleDao dao = new OracleDao();
		dao.init(propertiesFile);
		dao.open();
		dao.getCurrentTime();
		dao.close();
	}
	
}
