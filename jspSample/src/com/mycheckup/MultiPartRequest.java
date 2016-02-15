package com.mycheckup;

import java.io.*;
import java.util.Hashtable;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

@SuppressWarnings({ "rawtypes", "serial" })
public class MultiPartRequest extends Hashtable{


private String encoding="UTF-8";
private String uploadDir = "";
private byte[] buf = new byte[64*1024];
private HttpServletRequest request = null;


public MultiPartRequest(HttpServletRequest request,String encoding, String uploadDir)throws IOException{
	
	this.request=request;
	this.encoding=encoding;
	
	if(!uploadDir.endsWith(File.separator)){
		
		this.uploadDir=uploadDir+File.separator;
		
	}else{
		
		this.uploadDir = uploadDir;
		this.checkUploadDir();
		this.parseData();
	}
 }


public String getParameter(String value){
	
	return (String)this.get(value);
}


public FileInfo getFileInfo(String value){
	return (FileInfo)this.get(value);
	
}





private void checkUploadDir() throws IOException{
	
	File f = new File(this.uploadDir);
	
	if(!f.exists()){
		f.mkdirs();
	}
}

private String getCheckedFileName(String filename){
	
	int count =1;
	
	String absPath= this.uploadDir+filename;
	while(new File(absPath).exists()){
		
		filename=getOtherFileName(filename,count++);
        
		absPath=this.uploadDir+filename;
		
	}
	
	return filename;
	
}

private String getOtherFileName(String filename, int count) {

	int pos =0;
	
	if((pos=filename.lastIndexOf("."))!=-1) 
	
	return filename.substring(0, pos)+"("+count+")"+filename.substring(pos);
	
	else

	return filename+"("+count+")";
	
}


private byte[] readLineBytes(ServletInputStream in) throws IOException {

	int count;

	int total =0;

	ByteArrayOutputStream bos = new ByteArrayOutputStream();

	count = in.readLine(buf,0,buf.length);
	if(count!=-1){
		bos.write(buf, 0, count);
		total+=count;
	}

	if(total == 0) return null;
	bos.close();

	return bos.toByteArray();


}



private void parseData() {
	// TODO Auto-generated method stub
	
}

}
