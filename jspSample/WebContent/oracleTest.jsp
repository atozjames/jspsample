<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>

<%

Statement       stmt    = null;
ResultSet       rs      = null;
String          sql     = "";
PreparedStatement  psmt = null;

Context     init_ctx    = new InitialContext();
Context     env_ctx     = (Context)init_ctx.lookup("java:/comp/env");
DataSource  ds          = (DataSource)env_ctx.lookup("jdbc/CYBER");
Connection  conn        = ds.getConnection();

try{

  	   
	    psmt = conn.prepareStatement("select count(*) from dual");
        rs = psmt.executeQuery();

   
       while(rs.next()){ 
       out.println("While Test");
        }

  }finally {
  
	if (rs != null) try {rs.close(); }catch(SQLException ex) {}
    if (stmt != null) try {stmt.close(); } catch(SQLException ex) {}
    if (conn != null) try {conn.close(); }catch(SQLException ex) {}

}

%>