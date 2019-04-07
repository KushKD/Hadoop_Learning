package kush.hadoop.mapreduce.DepartmentAverageSalary;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AverageSalaryReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text key, Iterable<Text> value, Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		HashMap<String, String> map = new HashMap<String, String>();
		Float SalaryTotal = 0F;
		Float AverageSalary = 0F;
		int count = 0;
		
		
		//798     3546,GONZALEZ JAVIER,PLUMBER,798,95832
		
		for (Text values : value) {
		//	values = 3546,GONZALEZ JAVIER,PLUMBER,798,95832;
			String[] record = values.toString().split(",");  //[1,3546,GONZALEZ JAVIER,PLUMBER,798,95832]
				
				SalaryTotal += Float.parseFloat(record[5]);
				count  +=  Integer.parseInt(record[0]);
				AverageSalary = SalaryTotal/count;
				map.put(record[4], "Salary Total:- "+Float.toString(SalaryTotal)+
									",  Total No of Employees in the Respective Department" + Integer.toString(count) + 
									",  Average Salary of Department"+Float.toString((AverageSalary)));
			
			}	
		
		for (Map.Entry<String,String> entry : map.entrySet())  
			context.write( new Text(entry.getKey()),new Text(entry.getValue()));

		} 
	}
	

