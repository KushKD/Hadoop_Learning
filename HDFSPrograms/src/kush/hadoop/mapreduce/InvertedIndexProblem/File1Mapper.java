package kush.hadoop.mapreduce.InvertedIndexProblem;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class File1Mapper extends Mapper<LongWritable, Text, Text , Text> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String line = value.toString();  // Big Data
		String[] words = line.split(" "); // {Big,Data}
		
		for (String word : words) {
			context.write(new Text(word),new Text("1,File1"));  //{Big,{Big,File1}}{Data,{Data,File1}}
		}
		
		
		
		
	}
	
	

}
