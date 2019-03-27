package kush.hadoop.mapreduce.Joins;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class InnerJoinReducer extends Reducer<Text, Text, Text, Text> {
	
	List<String> EmployeeList  = new ArrayList<>();
	List<String> LocationList  = new ArrayList<>();
	

	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
/*		Second is the Partitioner Phase: 
			Key 	Value
			1		[(A Jack),(B Paris)]
			2		[(A Simon),(B Rome)]
			3		[(A Martin),(B Mumbai)]
			4		[(A Marry),(B Delhi)]
			5		[(A John),(B London)]
		*/
		//Creating Two List
		Iterator<Text> valuedData = values.iterator();
		
		while(valuedData.hasNext()) {
			String[] Record = valuedData.toString().split(",");  // New Record = {employee},{jack}
			
			if(Record[0].equalsIgnoreCase("Employee")) {
				EmployeeList.add(Record[1]);
			}else {
				LocationList.add(Record[1]);
			}
		}
		
		//Now We have Two List
		//For every word in list A combine it with every word in list B
		
		for (String employee : EmployeeList) {
			
			for (String  location : LocationList) {
				context.write(key, new Text(employee+","+location));
			}
			
		}
		
		
		
	
		
		
		
	}
	
	

}
