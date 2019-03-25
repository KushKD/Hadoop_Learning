package kush.hadoop.mapreduce.FacebookAddsAssignmnets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class AddsReducer extends Reducer<Text, AddsWritable, Text, IntWritable> {

	HashMap<String, HashMap<String, String>> add_Data =  new HashMap<String, HashMap<String, String>>();
	@Override
	protected void reduce(Text key, Iterable<AddsWritable> values, Context context) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		int salesMonthlyCount = 0;
		int month = 0;
		
		AddsWritable addsData = null;        // initialiszing writable class to null
		Iterator<AddsWritable> valuesIter = values.iterator();
		
		while(valuesIter.hasNext()) {
			
			addsData = valuesIter.next();
			
			SimpleDateFormat simple_date_formater = new SimpleDateFormat("dd-MM-yyyy"); 
			try {
				Date date = simple_date_formater.parse(addsData.getTimeStamp());
				Calendar c = Calendar.getInstance();  
				c.setTime(date);
				month = c.get(Calendar.MONTH);  //getting the month from the date
				++month; //Incrementing the month as month starts from 0-11
				
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
			
		}
		
		
		
	}
	

	
	
	
}
