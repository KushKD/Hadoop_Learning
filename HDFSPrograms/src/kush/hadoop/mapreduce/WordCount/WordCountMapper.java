package kush.hadoop.mapreduce.WordCount;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	
	private final static IntWritable one = new IntWritable(1);
	 private Text word = new Text();
	 
	

	
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String line = value.toString();
		System.out.println("Line is"+ line);
		StringTokenizer st1 = new StringTokenizer(line," "); 
		
		
		
	        while (st1.hasMoreTokens()) {
	        	 word.set(st1.nextToken());
	        	 context.write(word, one);
	        }
	           
	}

	

	

	
	
}
