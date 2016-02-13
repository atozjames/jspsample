<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,com.mycheckup.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Factory sample</h3>
<%
ConnectionFactory connectionFactory = ConnectionFactory.getDefaultFactory();
Connection conn =connectionFactory.createConnection("mysql");

Statement stmt = conn.createStatement();
String sql="select * from NovelJSP";
ResultSet rs =stmt.executeQuery(sql);

%>

<table border="1" cellspacing="0" cellpadding="3">
<tr>
<td align="center" bgcolor="#EEEEFF">이름</td>
<td align="center" bgcolor="#EEEEFF">나이</td>
</tr>

<%
while(rs.next()){
	
	out.print("<tr>");
	out.print("<td align=\"center\" bgcolor=\"#EEEEFF\">"+rs.getString(1)+"</td>");
	out.print("<td align=\"center\" bgcolor=\"#EEEEFF\">"+rs.getInt(2)+"</td>");
	out.print("</tr>");
}

%>

</table>

<%
rs.close();
stmt.close();
conn.close();


%>

</body>
</html>