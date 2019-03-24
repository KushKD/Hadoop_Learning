package kush.hadoop.mapreduce.EvenOddSum;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class EvenOddSumDriver {
	
	public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
		
		if (args.length != 2) {
			System.err.println("Usage: Even Odd SUm <input path> <output path>");
			System.exit(-1);
		}
		
		@SuppressWarnings("deprecation")
		Job job =  new Job();
		job.setJarByClass(EvenOddSumDriver.class);
		job.setJobName("Even ODD Sum");
		
		job.setInputFormatClass(TextInputFormat.class);
		 job.setOutputFormatClass(TextOutputFormat.class);
		 
		//Set input and output locations
			FileInputFormat.addInputPath(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(EvenOddSumMapper.class);
		job.setReducerClass(EvenOddSumReducer.class);
		
		
		//Output types
				job.setOutputKeyClass(Text.class);
				job.setOutputValueClass(IntWritable.class);
				
				//Set number of Reducers
				//job.setNumReduceTasks(5); 
				
				//Combiners
				//No Combiners
				
				
				

				//Submit job
				System.exit(job.waitForCompletion(true) ? 0 : 1);
		
		
		
		
	}

}
