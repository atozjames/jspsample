<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    
<%@ page import="com.mycheckup.Mcfunction" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>Hello World</h1>
<%

String remoteIP = request.getRemoteAddr();

out.println(remoteIP);

Mcfunction mcFunction = new Mcfunction();

//String tmsg="12.24.56.23";
String delimiter="\\.";

String rmsg=mcFunction.splitStr(remoteIP,delimiter);
String secureMsg=mcFunction.getEncSHA256(rmsg);
out.println(rmsg);


%>

<br>
<h1><%=secureMsg %></h1>
</body>
</html>