package kush.hadoop.mapreduce.FacebookAddsAssignmnets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test {
	
	public static void main(String args[]) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");  
		Date parse = sdf.parse("05-1-2017");  
		Calendar c = Calendar.getInstance();  
		c.setTime(parse);  
		int month = c.get(Calendar.MONTH);
		
		System.out.println(++month); 
	}

}
