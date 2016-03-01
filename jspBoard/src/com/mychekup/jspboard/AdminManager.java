package com.mychekup.jspboard;

import java.sql.*;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.mycheckup.ConnectionPool;


public class AdminManager {

	private static ConnectionPool connectionPool = null;
	private static AdminManager adminManager = null;
	private String id = "atozjames";
	private String password = "ilovepsh10";

	static Logger logger = Logger.getLogger(AdminManager.class);
	
	static{
	try{	
		connectionPool = ConnectionPool.getConnectionPool();
		adminManager = AdminManager.getInstance();
		
		
	}catch(Exception e){
		
		System.out.println("Error01:static block error!");
	}
	

   }
	
	public AdminManager(){}
	

	public static AdminManager getInstance(){
		// TODO Auto-generated method stub
		
		if(adminManager == null){
			adminManager = new AdminManager();
		}
		
		return adminManager;
	}
	
    //객체 테스트
	public String objTest(){
		
		String str="객체가 성공적으로 생성되었습니다.";
		
		return str;
	}
	
	//해당하는 이름의 게시판 이름이 존재하는지 검사하는 메소드
	
	public boolean existBoard(String boardName) {

		String sql = "select count(boardName) from BoardAdmin where boardName='" + boardName + "'";

		logger.info(sql);

		int num = this.adminExecuteQueryNum(sql);

		if (num == 0) {

			return false;
		} else {

			return true;
		}

	}
	
	//새로운 게시판을 만들기 위한 메소드
	
	 public void makeBoard(String boardName,String boardSubject){

		 String insertBoardSql = "insert into BoardAdmin values('"+boardName+"','"+boardSubject+"')";
		 
		 String makeBoardSql="create table "+boardName+"(";
		 makeBoardSql+="num int NOT NULL PRIMARY KEY,";
		 makeBoardSql+="name varchar(20) NOT NULL,";
		 makeBoardSql+="subject varchar(100),";
		 makeBoardSql+="content text,";
		 makeBoardSql+="writeDate datetime,";
		 makeBoardSql+="password varchar(20),";
		 makeBoardSql+="count int NOT NULL,";
		 makeBoardSql+="ref int NOT NULL,";
		 makeBoardSql+="step int NOT NULL,";
		 makeBoardSql+="depth int NOT NULL,";
		 makeBoardSql+="childCount int NOT NULL";
		 makeBoardSql+=")";
		 
		 logger.info(insertBoardSql);
		 logger.info(makeBoardSql);

		 this.adminExecuteUpdate(insertBoardSql);
		 this.adminExecuteUpdate(makeBoardSql);
		 
		 
	 }
	 
	// 관리자로 로그인 했는지의 여부를 파악하는 위한 메소드

	public boolean chkAdmin(String id, String Password){
		
		logger.info("param:"+id+"/"+Password);
		
		if(this.id.equals(id)&& this.password.equals(Password)){
			
			return true;
		}
		
		return false;
	}
	 
	 
	 public boolean isAdmin(String id) {

		if (id.equals(id)) {
			return true;

		} else {

			return false;
		}
	}
	 
	 
	 //게시판 리스트를 해쉬테이블에 반환하는 메서드

	public Hashtable<String,String> getBoardList() throws Exception {

		Hashtable<String,String> ht = new Hashtable<String,String>();
		Connection conn = null;

		try {
			conn = connectionPool.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from BoardAdmin");

			while (rs.next()) {

				String boardName = rs.getString("boardName");
				String boardSubject = rs.getString("boardSubject");

				ht.put(boardName, boardSubject);

			}

			rs.close();
			stmt.close();

		} catch (Exception e) {
			connectionPool.releaseConnection(conn);

		} finally {

		}

		return ht;
	}

	 //게시판 제목을 수정하는 메서드
	 
	 public void updateBoard(String boardName,String boardSubject){
		 
		 String sql="update BoardAdmin set boardSubject='"+boardSubject+"' where boardName='"+boardName+"'";
		 logger.info(sql);
		 
		 this.adminExecuteUpdate(sql);
		 
	 }
	 
	 //게시판 삭제 메서드
     
	 public void deleteBoard(String boardName){
		 
		 String deleteRecordSQL="delete from BoardAdmin where boardName='"+boardName+"'";
		 String dropBoardSQL="drop table "+boardName;
		 
		 logger.info(deleteRecordSQL);
		 logger.info(dropBoardSQL);
		 
		 this.adminExecuteUpdate(deleteRecordSQL);
		 this.adminExecuteUpdate(dropBoardSQL);
		 
	 
	 };
	 
	 //리턴값 없는 sql실행
	 public void adminExecuteUpdate(String sql){
		 
		 Connection conn =connectionPool.getConnection();
		
		 
		 try{
			 Statement stmt = conn.createStatement();
			 stmt.executeUpdate(sql);
			 
			 
		 }catch(Exception e){
			 
			 e.printStackTrace();
			 
		 }finally {
			
		 connectionPool.releaseConnection(conn);
			 
		 }
		 
		 
	 }
	 
	 //게시판 전제 내용을 Vector로 반환하는 메소드
     public Vector<Object> adminExecuteQuery(String Sql){
		
    	 Vector<Object> v = new Vector<Object>();
    	 Connection conn = connectionPool.getConnection();
    	 
    	 try{
    		 Statement stmt = conn.createStatement();
    		 ResultSet rs = stmt.executeQuery(Sql);
    		 
    		 while(rs.next()){
    		 BoardData data = new BoardData();
    		 
    		 data.setNum(rs.getInt(1));
    		 data.setName(rs.getString(2));
    		 data.setSubject(rs.getString(3));
    		 data.setContent(rs.getString(4));
    		 data.setDate(rs.getDate(5));
    		 data.setPassword(rs.getString(6));
    		 data.setCount(rs.getInt(7));
    		 data.setRef(rs.getInt(8));
    		 data.setStep(rs.getInt(9));
    		 data.setDepth(rs.getInt(10));
    		 data.setChildCount(rs.getInt(11));
    		 v.addElement(data);
    		 
    		 }
    		 
    		 rs.close();
    		 stmt.close();
    		 
    		 
    	 }catch(Exception e){
    		 
    		 e.printStackTrace();
    		 
    	 }finally {
			
    		 connectionPool.releaseConnection(conn);
		}
    	 
    	 
    	 
    	 
    	 return v;
     	 
     
     
     }
     
     //질문 결과가 하나의 int형일 경우를 위한 메소드
     public int adminExecuteQueryNum(String sql){
    	 
    	 int num=0;
    	 
    	 Connection conn = connectionPool.getConnection();
    	 try{
    		 Statement stmt=conn.createStatement();
    		 ResultSet rs =stmt.executeQuery(sql);
    		 while(rs.next()){
    			 
    			 num = rs.getInt(1);
    		 }
    		 
    		 rs.close();
    		 
    		 stmt.close();
    		 
    		 
    		 
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }finally{
    		 
    		 connectionPool.releaseConnection(conn);
    	 }
    	 
		return num;
    	
     }
     
     //질문의 결과가 하나의 스티링인 경우
	public String adminExecuteQueryString(String sql) {
		String str=null;

		Connection conn = connectionPool.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {

				str = rs.getString(1);
			}

			rs.close();

			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connectionPool.releaseConnection(conn);
		}

		return str;

	}
     
//	 
//	public static void main(String[] args){
//		
//	AdminManager adminManager=AdminManager.getInstance();
//	
//	String str=adminManager.objTest();
//	System.out.print(str);
//	
//	
//	}
	
}//end of class
