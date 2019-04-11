package kush.hadoop.mapreduce.ChurnAnalysis;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CADriver {
public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
		
		if (args.length != 2) {
			System.err.println("Usage: MaxClosePrice <input path> <output path>");
			System.exit(-1);
		}

		//Define MapReduce job
		@SuppressWarnings("deprecation")
		Job job = new Job();
		job.setJarByClass(CADriver.class);
		job.setJobName("CADriver");

		//Set input and output locations
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		Path Out_Directory = new Path(args[1]);
		
		//Set Input and Output formats
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

	    //Set Mapper and Reduce classes
		job.setMapperClass(CAMapper.class);
		job.setReducerClass(CAReducer.class);
		
		
		

		//Output types
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FloatWritable.class);
		
		//Set number of Reducers
		job.setNumReduceTasks(5);
		
		
		Out_Directory.getFileSystem(job.getConfiguration()).delete(new Path(args[1]),	true);
		//Submit job
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
		
		
	}

}
