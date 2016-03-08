

package com.mycheckup;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author atozjames
 *
 */
public class Mcfunction {
			
			
	
			public String splitStr(String mag,String delimiter){
	
			String splitMsg="";
			
			String[] temp;
			
			temp = mag.split(delimiter);
			 /* print substrings */
			  
			 for(int i =0; i < temp.length ; i++)
			    //System.out.println(temp[i]);
				 splitMsg=splitMsg+temp[i];
			
			return splitMsg;
		
				
		}
		
			public ArrayList<String> Tokenize(String str,String delim){
				
				ArrayList<String> list = new ArrayList<String>();
				
				
				StringTokenizer tokenizer = new StringTokenizer(str, delim);
				
				while(tokenizer.hasMoreTokens()){
					list.add(tokenizer.nextToken());
				}
				
				return list;
			}
			
			
		
		public String getEncSHA256(String txt) throws Exception{
		    StringBuffer sbuf = new StringBuffer();
		     
		    MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
		    mDigest.update(txt.getBytes());
		     
		    byte[] msgStr = mDigest.digest() ;
		     
		    for(int i=0; i < msgStr.length; i++){
		        byte tmpStrByte = msgStr[i];
		        String tmpEncTxt = Integer.toString((tmpStrByte & 0xff) + 0x100, 16).substring(1);
		         
		        sbuf.append(tmpEncTxt) ;
		    }
		     
		    return sbuf.toString();
		}
		
		
	public static void main(String[] agrs) {

		

		Mcfunction fncTest = new Mcfunction();

		
		String scr = "atos.myche.com";

		ArrayList<String> resultList = fncTest.Tokenize(scr, ".");
		
		System.out.println(resultList);

	}

}
