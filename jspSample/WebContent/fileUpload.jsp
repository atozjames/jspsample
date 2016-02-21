<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,com.mycheckup.*,"%>
<%@ page import="com.oreilly.servlet.MultipartRequest, com.oreilly.servlet.multipart.DefaultFileRenamePolicy,java.util.*"%>




<%
	request.setCharacterEncoding("UTF-8");
	String contentType = request.getContentType();

	if ((contentType == null) || (contentType.indexOf("multipart/from-data") == -1))
		throw new Exception("ContentType is Not multipart/from-data");

	
	String uploadDir = "D:\\BomSan\\Workspace\\Java\\java_work\\jspsample\\jspSample\\WebContent\\upload";
	int sizeLimit = 5*1024*1024; //5메가 까지 제안 넘어서면 에러 발생
	
	try{
		MultipartRequest multi = new MultipartRequest(request,uploadDir,sizeLimit,new DefaultFileRenamePolicy());
		Enumeration formNames = multi.getFileNames(); //form에 있는 모든 파일 이름 리턴
		String formName = (String)formNames.nextElement();
		
		
		
	}catch(Exception e){
		
		e.getStackTrace();
	}
	
	
	
	
	ConnectionPool pool =null;
	Connection conn =null;
	
	try{
		pool= ConnectionPool.getConnectionPool();
		
		conn = pool.getConnection();
		Statement stmt=conn.createStatement();
		String sql="Insert Into FileUpload (subject,fileName,fileSize)";
		sql=sql+ "values('" +subject+ "','"+fileName+"'," + fileSize+ ")";
		stmt.executeUpdate(sql);
		stmt.close();
		
	}catch(Exception e){
		
		e.printStackTrace();
	}finally{
		pool.releaseConnection(conn);
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=fileName%>(<%=fileSize %>)을 업로드 하였습니다.<br>
<a href="fileList.jsp">파일 관리 페이지로</a>
</body>
</html>