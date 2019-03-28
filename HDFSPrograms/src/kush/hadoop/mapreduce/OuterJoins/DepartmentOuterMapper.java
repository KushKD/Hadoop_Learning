package kush.hadoop.mapreduce.OuterJoins;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class DepartmentOuterMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String line = value.toString();
		String[] line_values = line.split(",");
		/*
		 Dept Id  Dept Name  Location
			10		INVENTORY		HYDERABAD
		 * 
		 */
		context.write(new Text(line_values[0]), new Text("Department"+line_values[1]+" "+line_values[2]));
		
	}
}