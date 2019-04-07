package kush.hadoop.mapreduce.InvertedIndexProblem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class File1File2Reducer extends Reducer<Text, Text, Text, Text>{

	@Override
	protected void reduce(Text key, Iterable<Text> value, Context context)
			throws IOException, InterruptedException  {
		
		
		  int sum = 0;
		 List<String> FileName = new ArrayList<String>();
		HashMap<String, String> data = new HashMap<String, String>();



for (Text values : value) {
	
	String[] record = values.toString().split(",");  //1,File1
	//context.write( new Text(key),new Text(text));
	
	sum +=  Integer.parseInt(record[0]);
	FileName.add(record[1]);
	

		data.put(key.toString(), Integer.toString(sum)+" "+ FileName.toString());
	
	//data.put(key.toString(), Integer.toString(sum)+" "+ File);
	
	}

for (Map.Entry<String,String> entry : data.entrySet())  
	context.write( new Text(entry.getKey()),new Text(entry.getValue()));

} 

		
				
				
		
		
		
	
	}
	
	
	
