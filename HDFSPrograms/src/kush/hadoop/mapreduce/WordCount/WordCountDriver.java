package kush.hadoop.mapreduce.WordCount;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import kush.hadoop.mapreduce.stocks.MaxClosePriceMapper;
import kush.hadoop.mapreduce.stocks.MaxClosePriceReducer;

public class WordCountDriver {
	
	public static void main(String args[]) {
		
		if (args.length != 2) {
			System.err.println("Usage: MaxClosePrice <input path> <output path>");
			System.exit(-1);
		}
		
		//Define Map Redice Job
		try {
			@SuppressWarnings("deprecation")
			Job job = new Job();
			job.setJarByClass(WordCountDriver.class);
			job.setJobName("Word Count Driver");
			
			
			//Set input and output locations
			FileInputFormat.addInputPath(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			
			//Set Input and Output formats
		    job.setInputFormatClass(TextInputFormat.class);
		    job.setOutputFormatClass(TextOutputFormat.class);

		    //Set Mapper and Reduce classes
			job.setMapperClass(WordCountMapper.class);
			job.setReducerClass(WordCountReducer.class);
			
			//Output types
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			
			//Set number of Reducers
			//job.setNumReduceTasks(5);
			
			

			//Submit job
			System.exit(job.waitForCompletion(true) ? 0 : 1);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
