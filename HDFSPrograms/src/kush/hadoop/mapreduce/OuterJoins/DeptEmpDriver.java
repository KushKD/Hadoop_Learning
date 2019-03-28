package kush.hadoop.mapreduce.OuterJoins;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class DeptEmpDriver  {
	
	public static void main (String args[]) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {
		
		if (args.length != 3) {
			System.err.println("Usage: Two Input Files Needed <input path> <output path>");
			System.exit(-1);
		}

		//Define MapReduce job
		@SuppressWarnings("deprecation")
		Job job = new Job();
		job.setJarByClass(DeptEmpDriver.class);
		job.setJobName("DeptEmpDriver");

		//Set input and output locations
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileInputFormat.addInputPath(job, new Path(args[1]));
		FileOutputFormat.setOutputPath(job, new Path(args[2]));
		
		Path Out_Directory = new Path(args[2]);
		
		//name of Mapper1 class 
				job.setMapperClass(EmployeeOuterMapper.class);  
				// name of Mapper2 class 
				job.setMapperClass(DepartmentOuterMapper.class);  
				// name of Reducer class 
				job.setReducerClass(DeptEmpOuterReducer.class);              
		  
			
				job.setMapOutputValueClass(Text.class);
				job.setMapOutputKeyClass(Text.class);
				job.setOutputValueClass(Text.class);
				job.setOutputKeyClass(Text.class);
			

				MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class,EmployeeOuterMapper.class);
				
				MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class,DepartmentOuterMapper.class);

				FileOutputFormat.setOutputPath(job, new Path(args[2]));
				Out_Directory.getFileSystem(job.getConfiguration()).delete(new Path(args[2]),	true);
		//Submit job
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	
	}

}
