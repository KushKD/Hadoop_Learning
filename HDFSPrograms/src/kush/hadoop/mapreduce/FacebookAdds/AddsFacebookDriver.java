package kush.hadoop.mapreduce.FacebookAdds;

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



public class AddsFacebookDriver {
	
public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
		
		if (args.length != 2) {
			System.err.println("Usage: Even Odd SUm <input path> <output path>");
			System.exit(-1);
		}
		
		@SuppressWarnings("deprecation")
		Job job =  new Job();
		job.setJarByClass(AddsFacebookDriver.class);
		job.setJobName("Face Book Adds");
		
		job.setInputFormatClass(TextInputFormat.class);
		 job.setOutputFormatClass(TextOutputFormat.class);
		 
		//Set input and output locations
			FileInputFormat.addInputPath(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(AddsFacebookMapper.class);
		job.setReducerClass(AddsFacebookReducer.class);
		
		
		//Output types
				job.setOutputKeyClass(Text.class);
				job.setOutputValueClass(Text.class);
				
				//Set number of Reducers
				//job.setNumReduceTasks(5); 
				
				//Combiners
				//No Combiners
				
				
				

				//Submit job
				System.exit(job.waitForCompletion(true) ? 0 : 1);
		
		
		
		
	}

}
