/*
 * 클래스 명은 만드시 대분자로 시작한다(명사).
 * 패키지를 표한하는 이름은 모두 소문자로 표시
 * 변수이름은 소문자로 표시하고 카멜표기
 * 메소드이름은 반드시 소문자로 표시하고 카멜표기(동사)
 * 중요한 변수는 긴이르므로 표시
 * 변수의 이름은 타입의 이름과 동일 
 * private 접근 제한자를 갖는 클래스 변수에 '_' 접미사
 * 상수(final 변수)를 표현하는 이름은 반드시 모두 대문자로 지정하되 '_' 를 사용하여 단어들을 구분
 *
 */


package com.mycheckup;

import java.security.MessageDigest;

/**
 * @author atozjames
 *
 */
public class Mcfunction {
			
			/**
			 * 문자열 분리하는 함수
			 * @param mag 원본 문자열
			 * @param delimiter 구분자
			 * @return String
			 */
	
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
		
		/**
		 * 문자열을 SHA-256 방식으로 암호화
		 * @param txt 암호화 하려하는 문자열
		 * @return String
		 * @throws Exception
		 */
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
		
		
		
}
