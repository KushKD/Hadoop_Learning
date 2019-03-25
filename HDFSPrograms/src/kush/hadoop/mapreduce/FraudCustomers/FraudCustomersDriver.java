package kush.hadoop.mapreduce.FraudCustomers;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import kush.hadoop.mapreduce.stocks.MaxClosePrice;
import kush.hadoop.mapreduce.stocks.MaxClosePriceMapper;
import kush.hadoop.mapreduce.stocks.MaxClosePriceReducer;

public class FraudCustomersDriver {

	
	public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
		
		if (args.length != 2) {
			System.err.println("Usage: MaxClosePrice <input path> <output path>");
			System.exit(-1);
		}

		//Define MapReduce job
		@SuppressWarnings("deprecation")
		Job job = new Job();
		job.setJarByClass(MaxClosePrice.class);
		job.setJobName("MaxClosePrice");

		//Set input and output locations
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//Set Input and Output formats
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(CustomerWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

	    //Set Mapper and Reduce classes
		job.setMapperClass(MaxClosePriceMapper.class);
		job.setReducerClass(MaxClosePriceReducer.class);
		
		
		

		//Output types
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FloatWritable.class);
		
		//Set number of Reducers
		job.setNumReduceTasks(5);
		
		

		//Submit job
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
		
		
	}
	
	
}
