package kush.hadoop.mapreduce.EvenOddSum;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class EvenOddSumReducer extends Reducer<Text, IntWritable, Text, IntWritable>{

	@SuppressWarnings("unlikely-arg-type")
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		
		int sum = 0;
		
//		if(key.equals("ODD")) {
//			for (IntWritable value : values) {
//				sum += value.get();
//			}
//		}else {
//			for (IntWritable value : values) {
//				sum += value.get();
//			}
//		}
		
		for (IntWritable value : values) {
			sum += value.get();
		}
		
		
		
		//Write output
		 context.write(key, new IntWritable(sum));
		 
	}
	
	

}
