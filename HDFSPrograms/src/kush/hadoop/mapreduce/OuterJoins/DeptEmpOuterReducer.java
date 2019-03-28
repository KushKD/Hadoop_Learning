package kush.hadoop.mapreduce.OuterJoins;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DeptEmpOuterReducer extends Reducer<Text, Text, Text, Text> {

	List<String> Employee_list = new ArrayList<>();
	List<String> Department_Name = new ArrayList<>();
	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		Iterator<Text> valuesData = values.iterator();
		
		while(valuesData.hasNext()){
			Text data = valuesData.next();  //Employee,1281, Shawn, 	Architect,	7890, 1481
			String[] NewRecord = data.toString().trim().split(",");
			
			if(NewRecord[0].equalsIgnoreCase("Employee")){
				Employee_list.add(NewRecord[1]);
			}else {
				Department_Name.add(NewRecord[1]);
			}
			
			
		}
		
		//Condition for Inner Join
		for (String employee : Employee_list) {
			for (String Department : Department_Name) {
				context.write(key, new Text(employee + " "+ Department));
			}
			
			
		}
		
		//Left Outer Join Condition
		//Left File i.e. Employee File should be non empty and Right File Should be Empty i.e. Dept file
		//
		  if (!Employee_list.isEmpty() && Department_Name.isEmpty())          //Condition for Left Outer Join join
	        {
	            for (String E : Employee_list) 
	            	{
		       	context.write(key, new Text(E + " " + "null_value null_value"));      // output 
	      	}
          	}
		  
		  //Condition for Right Outer Join
		  //Employee at Left Should be Empty and Department at Right Should not be empty
			if (Employee_list.isEmpty() && !Department_Name.isEmpty()) 
          	{
				for (String Department : Department_Name) {
        context.write(key, new Text("null_value null_value null_value null_value null_value" + " " + Department));    //output
				}
				}
		
		
	}
	
	

}
