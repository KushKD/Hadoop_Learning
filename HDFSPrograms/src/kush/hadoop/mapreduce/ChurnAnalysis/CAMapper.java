package kush.hadoop.mapreduce.ChurnAnalysis;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CAMapper extends Mapper<LongWritable, Text, Text, Text>{

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		

		//JXJY167254JK,18-06-2017,654S654,
		//Pizza:Manchurian:Chow Mein:Crispy Onion Rings,197,Wallet,Emperial,08:31:21,2,Late delivery
		
		String line = value.toString();
		String [] lineData = line.split(",");
		
		String UpdatedKey = lineData[0];  // customer_Id JXJY167254JK
		String OrderMonth = lineData[1];  //  18-06-2017
		String CustomerRating = lineData[8]; // user Comments
		String CustomerReview = lineData[9]; // user Comments
		
		context.write(new Text(UpdatedKey), new Text(OrderMonth+","+CustomerRating+"," +CustomerReview+","+"1"));  // JXJY167254JK  18-06-2017,2,Late delivery
		
		
	}
	
	
	

}
