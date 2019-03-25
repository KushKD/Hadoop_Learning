package kush.hadoop.mapreduce.FacebookAddsAssignmnets;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AddsMapper extends Mapper<LongWritable, Text, Text, AddsWritable> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String line = value.toString();
		String[] valuesArray = line.split(",");
		
		/*
		 * FKQY385181,05-01-2017,Bangalore,Lifestyle,266,23,40-45
		 *  addId [0]
			timeStamp [1]
			location [2]
			category [3]
			addClicks [4]
			addSales [5]
			ageGroup [6]
		 */
		
		AddsWritable addsWritable = new AddsWritable
				(valuesArray[0], valuesArray[1] , valuesArray[2], valuesArray[3], valuesArray[4], valuesArray[5], valuesArray[6]);
		
		context.write(new Text(valuesArray[0]), addsWritable);
		
		
		
	}
	
	

}
