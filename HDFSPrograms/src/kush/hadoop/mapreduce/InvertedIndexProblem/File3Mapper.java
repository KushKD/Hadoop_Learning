package kush.hadoop.mapreduce.InvertedIndexProblem;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class File3Mapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String line = value.toString();  // Hadoop Is Huge In Big Data
		String[] words = line.split(" "); // {Hadoop, Is,Huge, In, Big, Data   }
		
		for (String word : words) {
			context.write(new Text(word),new Text("1,File3")); 
			/*
			 * {Hadoop},{Hadoop,File2}
			 * {Is,File2}
			 * {Huge,File2}
			 * {In,File2}
			 * {Big,File2}
			 * {Data,File2}
			 */
		}
		
	}

}
