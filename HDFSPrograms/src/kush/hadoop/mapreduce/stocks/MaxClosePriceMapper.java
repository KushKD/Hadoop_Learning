package kush.hadoop.mapreduce.stocks;

/**
 * MaxClosePriceMapper.java
 * www.hadoopinrealworld.com
 * This is a Mapper program to calculate Max Close Price from stock dataset using MapReduce
 * meaning we need to group the record by symbol and calculate the max close price by symbol
 * input == stocks key as key and its close price as value
 */

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//Make a mapper class just by extending the Mapper class
//There are four parameter 
//The first two parameters dictate the input to the mapper as key and value.  input key is of type LongWritable (Ist PAram) and Input value of type Text (IInd Param)
//The third and Fourth parameters dictate the output of the mapper as key value. Output key is of Type Text (IIIrd PAram) and Output value is of type (FloatWritable)  (IVth PAram)

public class MaxClosePriceMapper extends Mapper<LongWritable, Text, Text, FloatWritable> {

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String line = value.toString();
		String[] items = line.split(",");
		//There ar different colums seperated by Delemeter
		String stock = items[1];
		Float closePrice = Float.parseFloat(items[6]);
		
		context.write(new Text(stock), new FloatWritable(closePrice));
		
	}
}
