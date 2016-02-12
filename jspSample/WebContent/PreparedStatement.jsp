<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>간단한 데이터 베이스 접속 프로그램</h3>

<%
Class.forName("com.mysql.jdbc.Driver");

//Class.forName("oracle.jdbc.driver.OracleDriver");

out.print("JDBC driver loading finished<br>");
String url = "jdbc:mysql://www.youscan.co.kr:3306/konavi";
String user="konavi";
String password="konavi";
Connection conn = DriverManager.getConnection(url, user, password);
out.print("연결 URL정의 및 DB와 연결이 정상적으로 연결 되었습니다.<br>");



String sql="Insert into NovelJSP value (?,?)";

PreparedStatement psmt=conn.prepareStatement(sql);

psmt.setString(1, "Grace");
psmt.setInt(2, 24);
psmt.executeUpdate();

psmt.setString(1, "James");
psmt.setInt(2, 28);
psmt.executeUpdate();

out.print("레코드 삽입 완료<br>");


psmt.close();
conn.close();

%>


</body>
</html>