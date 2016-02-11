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
out.print("JDBC driver loading finished");
//String url = "j"

%>



</body>
</html>