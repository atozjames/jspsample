package com.mycheckup;

import java.util.*;
import java.io.*;

public class ProReader {

	Enumeration propNames=null;
	Vector v = null;
	
	Properties prop=null;
	
	public void ProReader() {

	}

	public void read(){
		
		try{
			
			prop = new Properties();
			
			InputStream is =this.getClass().getClassLoader().getResourceAsStream("oracleDB.properties");
			prop.load(is);
			
			
			propNames= prop.propertyNames();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
		while(propNames.hasMoreElements()){
			
			String key = (String) propNames.nextElement();
			
			System.out.println(key);
			System.out.println(prop.getProperty(key));
			
		}
		
	}
	
	public Hashtable properRead(String fileName){
		
		
		 
			Vector vector = new Vector();
		    Hashtable ht = new Hashtable();
		    Properties prop = new Properties();
		    
		    try{
		    	
		    }catch(Exception e){
		    	
		    	e.printStackTrace();
		    }
		    
		    
		    
		   return ht;
		
	
	
	
	}
	
	
	
	
	
	public static void main(String[] agrs){
		
		ProReader read= new ProReader();
		read.read();
		
	}
}
