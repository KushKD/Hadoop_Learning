package kush.hadoop.mapreduce.FraudCustomers;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FraudCustomersMapper extends Mapper<LongWritable, Text, Text, CustomerWritable> {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, CustomerWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String line = value.toString();
		//GGYZ333519YS,Allison,08-01-2017,10-01-2017,Delhivery,13-01-2017,yes,15-01-2017,Damaged Item
		CustomerWritable customerWritable = null;
		/*
		 * [0] GGYZ333519YS Customer Id 
		 * [1] Allison Customer Name
		 * [2] 08-01-2017 Date on Which Customer Place the Order
		 * [3] 10-01-2017 Shiping Date
		 * [4] Delhivery Shiping Coriour name
		 * [5] 13-01-2017 Date on which product is received by customer
		 * [6] yes Weather customer returned the product or not Y/N
		 * [7] 15-01-2017 Return Date on which the product is returned back
		 * [8] Damaged Item Reason for Return
		 * 
		 */
		String[] line_data = line.split(",");
		
		 customerWritable = new CustomerWritable
				(Integer.parseInt(line_data[0]), line_data[1], line_data[2], line_data[3], line_data[4], line_data[5], Boolean.valueOf(line_data[6]), line_data[7], line_data[8]);
		 context.write(new Text(line_data[0]), customerWritable);
		
	}
	
	

}
