package kush.hadoop.mapreduce.DepartmentAverageSalary;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AverageSalaryDriver {
public static void main(String args[]) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {
		
		if (args.length != 2) {
			System.err.println("Usage: Two Input Files Needed <input path> <output path>");
			System.exit(-1);
		}

		//Define MapReduce job
		@SuppressWarnings("deprecation")
		Job job = new Job();
		job.setJarByClass(AverageSalaryDriver.class);
		job.setJobName("AverageSalaryDriver");

		//Set input and output locations
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		Path Out_Directory = new Path(args[1]);
		
		//name of Mapper1 class 
				job.setMapperClass(AverageSalaryMapper.class);  
				job.setReducerClass(AverageSalaryReducer.class);              
		  
				
				job.setMapOutputValueClass(Text.class);
				job.setMapOutputKeyClass(Text.class);
				job.setOutputValueClass(Text.class);
				job.setOutputKeyClass(Text.class);
			

				

				FileOutputFormat.setOutputPath(job, new Path(args[1]));
				
				Out_Directory.getFileSystem(job.getConfiguration()).delete(new Path(args[1]),	true);
		//Submit job
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
		
	}
}
