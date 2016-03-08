package com.example;

import java.util.Calendar;
import java.util.Date;

public class GetTime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//calendar.getTimeZone()
		
		System.out.println("MiliSecondTime:"+date.getTime());
		System.out.println("Representation:"+date.toString());
		System.out.println(calendar.get(Calendar.DATE));
		System.out.println(calendar.get(Calendar.WEEK_OF_MONTH));
		System.out.println(calendar.get(Calendar.MONTH)+1);
		System.out.println(calendar.get(Calendar.YEAR));
		
		System.out.println(calendar.getTimeZone());
		
		
		

	}

}
