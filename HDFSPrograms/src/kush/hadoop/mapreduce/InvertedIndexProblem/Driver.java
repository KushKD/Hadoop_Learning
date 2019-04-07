package kush.hadoop.mapreduce.InvertedIndexProblem;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import kush.hadoop.mapreduce.Joins.EmployeeLocationDriver;
import kush.hadoop.mapreduce.Joins.EmployeeMapper;
import kush.hadoop.mapreduce.Joins.InnerJoinReducer;
import kush.hadoop.mapreduce.Joins.LocationMapper;

public class Driver {
	
	public static void main(String args[]) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {
		
		if (args.length != 4) {
			System.err.println("Usage: Two Input Files Needed <input path> <output path>");
			System.exit(-1);
		}

		//Define MapReduce job
		@SuppressWarnings("deprecation")
		Job job = new Job();
		job.setJarByClass(Driver.class);
		job.setJobName("Driver");

		//Set input and output locations
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileInputFormat.addInputPath(job, new Path(args[1]));
		FileInputFormat.addInputPath(job, new Path(args[2]));
		FileOutputFormat.setOutputPath(job, new Path(args[3]));
		
		Path Out_Directory = new Path(args[2]);
		
		//name of Mapper1 class 
				job.setMapperClass(File1Mapper.class);  
				// name of Mapper2 class 
				job.setMapperClass(File2Mapper.class);  
				//name of Mapper 3
				job.setMapperClass(File3Mapper.class);
				// name of Reducer class 
				job.setReducerClass(File1File2Reducer.class);              
		  
				
				job.setMapOutputValueClass(Text.class);
				job.setMapOutputKeyClass(Text.class);
				job.setOutputValueClass(Text.class);
				job.setOutputKeyClass(Text.class);
			

				MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class,File1Mapper.class);
				MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class,File2Mapper.class);
				MultipleInputs.addInputPath(job, new Path(args[2]), TextInputFormat.class,File3Mapper.class);

				FileOutputFormat.setOutputPath(job, new Path(args[3]));
				
				Out_Directory.getFileSystem(job.getConfiguration()).delete(new Path(args[3]),	true);
		//Submit job
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
		
	}

}
