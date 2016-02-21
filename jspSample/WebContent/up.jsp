<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,com.mycheckup.*,java.io.*"%>
<%@ page import="com.oreilly.servlet.MultipartRequest, com.oreilly.servlet.multipart.DefaultFileRenamePolicy,java.util.*"%>

<%
	 request.setCharacterEncoding("UTF-8");
	 String contentType = request.getContentType();

	/* if ((contentType == null) || (contentType.indexOf("multipart/form-data") == -1))
		throw new Exception("ContentType is Not multipart/from-data"); */
 
	
	String uploadDir = "D:\\BomSan\\Workspace\\Java\\java_work\\jspsample\\jspSample\\WebContent\\upload";
    out.print("test");
	int sizeLimit = 5*1024*1024; //5메가 까지 제안 넘어서면 에러 발생
	
	 try{
		MultipartRequest multi = new MultipartRequest(request,uploadDir,sizeLimit,"UTF-8",new DefaultFileRenamePolicy());
		Enumeration formNames = multi.getFileNames(); //form에 있는 모든 파일 태그의 이름 리턴
		String formName = (String)formNames.nextElement(); //form에 파일 태그의 이믐
		String fileName = multi.getFilesystemName(formName); //파일 이름 얻기
		
		File file = multi.getFile(formName);
		
		String fname = file.getName();
		String fSize = String.valueOf(file.length()/1024);
		
		if(fileName==null){
			out.print("파일이 업로드 되지 않음");
		}else{
			
			
			out.print("subject:"+multi.getParameter("subject")+"<br>");
			out.print("Form Name:"+formName+"<br>");
			out.print("fileName:"+fileName+"<br>");
			out.print("file Size:"+fSize+"KB <br>");
			out.print("fname:"+fname+"<br>"); 
		}
		
		
	}catch(Exception e){
		
		e.getStackTrace();
	}
	
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


</body>
</html>