package com.van.learn_java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class SimpleCalendarSample {

	public static void main (String args[]) throws ParseException {
		
		String input = "Mon May 11 20:20:37 UTC 2020";
		DateTimeFormatter f = DateTimeFormatter.ofPattern( "E MMM d HH:mm:ss z uuuu" );
		ZonedDateTime zdt = ZonedDateTime.parse( input , f );
		System.out.println( "input: " + input );
		System.out.println( "zdt: " + zdt );
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse("2020-05-11 20:20:34");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		System.out.println(cal.getTime().toString());
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.ENGLISH);
		Date date2 = sdf2.parse("Mon May 11 20:20:34 GMT 2020");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		System.out.println(cal2.getTime().toString());
		
		SimpleDateFormat sdf3 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		Date date3 = sdf3.parse("Mon May 11 20:20:34 GMT 2020");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(date3);
		System.out.println(cal3.getTime().toString());
	}
};
