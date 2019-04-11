package kush.hadoop.mapreduce.ChurnAnalysis;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CAReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
		//// JXJY167254JK  18-06-2017,Late delivery
		int monthlyTotalOrders = 0;
		int month=0;
		@SuppressWarnings("unused")
		int year;
		HashMap<String, String> data = new HashMap<String, String>();
		List<String> dataList  = new ArrayList<String>();
		
		for(Text value:values) {
			
			String valueData = value.toString();  //18-06-2017,Late delivery,1
			String[] valueDataArray = valueData.split(","); // [18-06-2017, Late delivery,1]
			 context.write(key, new Text(valueDataArray[0]+","+valueDataArray[1]+","+valueDataArray[2]));
			
			//Get the month from the Date
			 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			    try {
					Date receiveDate = sdf.parse(valueDataArray[0]);
					Calendar cal = Calendar.getInstance();
					cal.setTime(receiveDate);
					 month = cal.get(Calendar.MONTH)+1; // 5 
					 year = cal.get(Calendar.YEAR); // 2016
					
			    	} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			    dataList.add(key.toString()+","+Integer.toString(month)+","+"1");
			    //data.put(key.toString(),Integer.toString(month)+","+"1");
			    //context.write(key, new Text(Integer.toString(month)+","+Integer.toString(monthlyTotalOrders)));
			   
		}
		
		for (String string : dataList) {
			 String[] valueData = string.split(","); 
			// monthlyTotalOrders+= Integer.parseInt(valueData[1]);
			 data.put(valueData[0],valueData[1]+","+valueData[2]);
			 context.write(key, new Text(valueData[0]+","+valueData[1]+","+valueData[2]));
			 
		}
		
//		 for (Map.Entry<String,String> entry : data.entrySet()) {
//				// System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//				 String value = entry.getValue();
//				 String[] valueData = value.split(","); 
//				 monthlyTotalOrders+= Integer.parseInt(valueData[1]);
//				 //Q_LOYAL 11,9464 12,2 13
//				//if(valueData[11].equalsIgnoreCase("Q_LOYAL")&& Integer.parseInt(valueData[12])>10000 && Integer.parseInt(valueData[13])>=4) {
//					// context.write(key, new Text(valueData[0]+","+Integer.toString(monthlyTotalOrders)));
//				//}
//				 
//				
//					
//				 
//				 
//				
//				 
//			 }
	}
	
	
	

}
