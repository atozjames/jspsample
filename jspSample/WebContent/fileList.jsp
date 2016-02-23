
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.*,com.mycheckup.*,java.io.*"%>
<%@ page import="com.oreilly.servlet.MultipartRequest, com.oreilly.servlet.multipart.DefaultFileRenamePolicy,java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>File List 출력</title>
</head>
<body>
<h1>파일 리스트 출력</h1>

<%
ConnectionPool pool=null;
Connection conn = null;

try{
	
pool = ConnectionPool.getConnectionPool();
conn = pool.getConnection();
Statement smtp = conn.createStatement();

String sql="select * from FileUpload";
ResultSet rs=smtp.executeQuery(sql);

while(rs.next()){
	
	String subject = rs.getString("subject");
	String fileName = rs.getString("fileName");
     
%>	
<p>
번호:<%=rs.getInt("num") %><br>
제목:<%=subject %><br>
파일이름:<a href="FileDownload?fileNmae=<%=fileName %>"><%=rs.getString("fileName")%></a><br>
크기:<%=rs.getInt("fileSize") %>
</p>
<% 
}

}catch(Exception e){
	e.printStackTrace();

}finally{
 pool.releaseConnection(conn);
}

%>
<hr>
<h3>피일 업로드</h3>
<form name="fileUpload" method="post" action="fileUpload.jsp" enctype="multipart/form-data">

제목:<input type="text" name="subject" size="40"><br>
파일:<input type="file" name="file" size="30"><br>
<input type="submit" value="submit">

</form>
</body>
</html>