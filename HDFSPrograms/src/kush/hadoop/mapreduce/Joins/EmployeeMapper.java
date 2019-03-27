package kush.hadoop.mapreduce.Joins;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EmployeeMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String line = value.toString();
		String [] Employee = line.split(",");
		
		String Employee_id = Employee[0].toString();
		String Employee_Name = Employee[1].toString();
		
		context.write(new Text(Employee_id.toString()), new Text("Employee,"+Employee_Name.toString()));
		
	}
	
	

}
