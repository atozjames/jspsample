<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*,com.mycheckup.*"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>간단한 데이터 베이스 접속 프로그램</h3>

<%
//Class.forName("com.mysql.jdbc.Driver");

/* Class.forName("oracle.jdbc.driver.OracleDriver");

out.print("JDBC driver loading finished<br>");
String url = "jdbc:oracle:thin:@www.mycheckup.co.kr:1521:orcl";
String user="cybercvs";
String password="info9077";
Connection conn = DriverManager.getConnection(url, user, password);
out.print("연결 URL정의 및 DB와 연결이 정상적으로 연결 되었습니다.<br>"); 
*/

ConnectionFactory connectionFactory = ConnectionFactory.getDefaultFactory();

Connection conn = connectionFactory.createConnection("oracle");

out.print("연결 URL정의 및 DB와 연결이 정상적으로 연결 되었습니다.<br>"); 

Statement stmt =conn.createStatement();

String sql="create table NovelJSP(name VARCHAR(10)NOT NULL PRIMARY KEY, age INTEGER)";

stmt.executeUpdate(sql);

out.print("NovelJSP테이블 생성 완료<br>");

stmt=conn.createStatement();

sql="Insert into NovelJSP (name,age) values ('jabook',32)";

stmt.executeUpdate(sql);
out.print("데이터 삽입 완료<br>");

stmt=conn.createStatement();

sql="select * from NovelJSP";

ResultSet rs=stmt.executeQuery(sql);

out.print("데이터 쿼리 완료<br>");

while(rs.next()){
	
	out.print(rs.getString("name")+","+rs.getInt(2)+"<br>");
	
}

rs.close();

stmt=conn.createStatement();

sql="delete from NovelJSP where name='jabook'";

stmt.executeUpdate(sql);

//rs.close();
stmt.close();
conn.close();








%>



</body>
</html>