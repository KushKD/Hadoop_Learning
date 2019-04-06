package kush.hadoop.mapreduce.InvertedIndexProblem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class File1File2Reducer extends Reducer<Text, Text, Text, Text>{

	@Override
	protected void reduce(Text KEY, Iterable<Text> VALUE, Context context)
			throws IOException, InterruptedException  {
		
		//	Mapper 1   			Mapper 2
		/*						{Hadoop,File2}
		 * {Big,File1}			{Is,File2}
		 * {Data,File1}			{Huge,File2}
		 * 						{In,File2}
		 * 						{Big,File2}
		 * 						{Data,File2}
		 */
		
		//Shuffle Phase
		/* 
		 {Big,File1}
		 {Data,File1}
		 {Hadoop,File2}
		 {Is,File2}
		 {Huge,File2}
		 {In,File2}
		 {Big,File2}
		 {Data,File2}
		 */
		List<String> file1 = new ArrayList<String>();
		List<String> file2 = new ArrayList<String>();
		
		Iterator<Text> values = VALUE.iterator();
				
				while(values.hasNext()) {
					String[] valuesData = values.toString().trim().split(",");  //{Big,File1}
					context.write( new Text("Testing"),new Text(valuesData.toString()));
//					if(valuesData.length > 0) {
//						System.err.println("Now what to Do"+valuesData[1]);
//						if(valuesData[1].equalsIgnoreCase("File1")) {
//							file1.add(valuesData[0]);
//						}else {
//							//File 2 List
//							file2.add(valuesData[0]);
//						}
					}
				}
				
				//file1 list and file 2 list
//				while(values.hasNext()) {
//					String[] valuesData = values.toString().split(",");  
//					if(!file1.isEmpty() && !file2.isEmpty()) {
//						if(file1.contains(valuesData[0]) && file2.contains(valuesData[0])) {
//							context.write(new Text(valuesData[0]), new Text("File1,File2"));
//						}else if(file1.contains(valuesData[0])&& !file2.contains(valuesData[0])){
//							context.write(new Text(valuesData[0]), new Text("File1"));
//						}else if(!file1.contains(valuesData[0])&& file2.contains(valuesData[0])){
//							context.write(new Text(valuesData[0]), new Text("File2"));
//						}
//					}
//					
//				}
		
		
		
	
	
	
	
	

}
