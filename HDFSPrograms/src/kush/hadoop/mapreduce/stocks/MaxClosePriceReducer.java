package kush.hadoop.mapreduce.stocks;

/**
 * MaxClosePriceReducer.java
 * www.hadoopinrealworld.com
 * This is a Reduce program to calculate Max Close Price from stock dataset using MapReduce
 */
 import java.io.IOException;

 import org.apache.hadoop.io.FloatWritable;
 import org.apache.hadoop.io.Text;
 import org.apache.hadoop.mapreduce.Reducer;

 public class MaxClosePriceReducer
 extends Reducer<Text, FloatWritable, Text, FloatWritable> {
	 
	 //First and Second Parameters define the input to the reducer in 
	 //Third and Fourth Parameters define the output of the reducer

	 @Override
	 public void reduce(Text key, Iterable<FloatWritable> values, Context context)
			 throws IOException, InterruptedException {

		 float maxClosePrice = Float.MIN_VALUE;
		 
		 //Iterate all temperatures for a year and calculate maximum
		 for (FloatWritable value : values) {
			 maxClosePrice = Math.max(maxClosePrice, value.get());
		 }
		 
		 //Write output
		 context.write(key, new FloatWritable(maxClosePrice));
	 }
 }
