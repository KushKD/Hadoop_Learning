package kush.hadoop.mapreduce.OuterJoins;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EmployeeOuterMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String line = value.toString();
		String[] line_values = line.split(",");
		/*
		 * Emp  IT	  Emp Name 		Position		Salary		MGR ID		Dept Id
			1281,     Shawn, 		Architect,		7890, 		1481,		10
		 * 
		 */
		context.write(new Text(line_values[5]), 
new Text("Employee"+line_values[0]+" "+line_values[1]+" "+line_values[2]+" "+line_values[3]+" "+line_values[4]));
		
	}
	
	

}
