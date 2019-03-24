package kush.hadoop.mapreduce.EvenOddSum;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class EvenOddSumMapper  extends Mapper<LongWritable,Text,Text,IntWritable>{

	public enum Volume{
		EVEN,
		ODD; 
	}
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String line = value.toString();
		String[] values = line.split(",");
		
		for(int i=0; i<values.length;i++) {
			//Convert te String to Integer
			int number = Integer.parseInt(values[i]);
			
			//Check Weather the Number is Even Odd
			
			if(number %2==0) {
				//Number is even
				context.getCounter(Volume.EVEN).increment(1);
				context.write(new Text("EVEN"), new IntWritable(number));
			}else {
				//number is odd
				context.getCounter(Volume.ODD).increment(1);
				context.write(new Text("ODD"), new IntWritable(number));
			}
		}
			
		
		
	}
	
	

}
