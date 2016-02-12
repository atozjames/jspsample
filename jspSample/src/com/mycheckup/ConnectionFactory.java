/**
 * 
 */
package com.mycheckup;

import java.sql.*;

/**
 * @author wBom
 *
 */
public class ConnectionFactory {
	
	
	private static ConnectionFactory connectionFactory = new ConnectionFactory();
	
	private ConnectionFactory(){}; //인스턴스 생성을 막기 위한 조치
	
	public static ConnectionFactory getDefaultFactory(){
		
		if(connectionFactory ==null){
			
			connectionFactory= new ConnectionFactory();
		}
		
		return connectionFactory;
	}
	
	public Connection createConnection() {
		Connection connection = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException cnfe) {

			System.out.println(cnfe);

		}

		String url = "jdbc:mysql://www.youscan.co.kr:3306/konavi";
		String user = "konavi";
		String password = "konavi";

		try {
			connection = DriverManager.getConnection(url, user, password);

		} catch (SQLException sqle) {

			System.out.println(sqle);
		}

		return connection;
	}

	
}
