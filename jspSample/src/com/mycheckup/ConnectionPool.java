package com.mycheckup;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

class ConnectionInfo{

	public Connection connection=null;
	public long time =0;
	
	public ConnectionInfo(Connection connection,long time){
		this.connection =connection;
		this.time=time;
	}
}

public class ConnectionPool {

	private static int MAX_CONNECTION=5;
	private Vector buffer = new Vector();
	private int wait_count = 0;
	private static ConnectionPool connectionPool =new ConnectionPool();
	

	static{
		
		try{
			initConnectionPool();
			
		}catch(SQLException e){
			
			System.out.println("---Connection Create Error--");
			e.printStackTrace();
			
		}catch(ClassNotFoundException e2){
			System.out.println("--Driver Class Not Founder Error--");
			e2.printStackTrace();
		}
		
	}
	
	private ConnectionPool(){}

    
	@SuppressWarnings("unchecked")
	public synchronized static void initConnectionPool()throws SQLException,ClassNotFoundException {
		ConnectionPool.getConnectionPool();
		// TODO Auto-generated method stub
		ConnectionPool.destoryConnectionPool();
		@SuppressWarnings("rawtypes")
		Vector temp = ConnectionPool.getConnectionPool().getConnectionPoolBuffer();
		ConnectionFactory connectionFactory = ConnectionFactory.getDefaultFactory();
		
		for(int i=0; i <MAX_CONNECTION;i++){
		
			Connection connection = connectionFactory.createConnection("mysql");
			temp.addElement(new ConnectionInfo(connection,System.currentTimeMillis()));
			System.out.println("New Connection Created.."+connection);
			
		}
	}

	

	public synchronized static void destoryConnectionPool(){
		
		Vevtor
	}
	
	public static ConnectionPool getConnectionPool() {
		// TODO Auto-generated method stub
		if(connectionPool==null){
			connectionPool= new ConnectionPool();
		}
		
		return connectionPool;
	}
	private Vector getConnectionPoolBuffer() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
