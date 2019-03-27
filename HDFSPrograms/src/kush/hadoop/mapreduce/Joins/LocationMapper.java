package kush.hadoop.mapreduce.Joins;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LocationMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		
		String line = value.toString();
		String [] Location = line.split(",");
		
		context.write(new Text(Location[0]), new Text("Location"+Location[1]));
	}
	
	

}
