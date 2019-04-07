package kush.hadoop.mapreduce.Counters;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CounterMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	public enum Constants{
		HYDRABAD, 
		BANGLORE,
		CHENNEI,
		TOTAL_RECORDS;
	}

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		//Total no of Records Processed by Mapper  
		context.getCounter(Constants.TOTAL_RECORDS).increment(1);
		String line = value.toString();
		String[] line_data = line.split(",");
		
		
		//Total Record Processed by each Store Location  counter
		if(line_data[4].equalsIgnoreCase("Hyderabad")) {
			context.getCounter(Constants.HYDRABAD).increment(1);
		}else if(line_data[4].equalsIgnoreCase("Bangalore")) {
			context.getCounter(Constants.BANGLORE).increment(1);
		}else if(line_data[4].equalsIgnoreCase("Chennai")) {
			context.getCounter(Constants.CHENNEI).increment(1);
		}
		
		//Records Where no of Sales are less than 10 counter
		if(Integer.parseInt(line_data[3])<10) {
			context.getCounter("SALES","Low_Sales").increment(1);
		}
		
		//no of Records (Where no of sales * Product Price >500) counter
		int salesNo = Integer.parseInt(line_data[3]);
		int priceProduct = Integer.parseInt(line_data[2]);
		int multiply = salesNo*priceProduct;
		
		if(multiply>500) context.getCounter("SALES","HIGH_SALES").increment(1);
		
		
		context.write(new Text(line_data[4]), new Text(line));
		
		
	}
	
	

}
