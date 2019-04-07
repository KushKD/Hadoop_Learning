package kush.hadoop.mapreduce.Counters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CounterReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text key, Iterable<Text> value, Context context)
			throws IOException, InterruptedException {
		
		
		HashMap<String, String> map = new HashMap<String, String>();
		int sum = 0;
		
		for (Text text : value) {
			
			String line = text.toString();
			String[] line_data = line.split(",");  //1194208577,sofa,39,3,Hyderabad
			
			int salesNo = Integer.parseInt(line_data[3]);
			int priceProduct = Integer.parseInt(line_data[2]);
			int multiply = salesNo*priceProduct;
			sum+=multiply;
			
			map.put(key.toString(),"Total Sales Per Store:- " + Integer.toString(sum));
			
			
		}
		
		//Read the Map
		 for (Map.Entry<String,String> entry : map.entrySet())  
			 context.write(new Text(entry.getKey()), new Text(entry.getValue()));
	         
	    } 
		
	}
	
	

