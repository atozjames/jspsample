<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*,com.mycheckup.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h3>Pooling ���</h3>
<%
ConnectionPool Pool = ConnectionPool.getConnectionPool();
Connection conn = Pool.getConnection();

Statement stmt = conn.createStatement();
String sql="select * from NovelJSP";
ResultSet rs =stmt.executeQuery(sql);
%>

<table border="1" cellspacing="0" cellpadding="3">
<tr>
<td align="center" bgcolor="#EEEEFF">�̸�</td>
<td align="center" bgcolor="#EEEEFF">����</td>
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
Pool.releaseConnection(conn);
%>
</body>
</html>