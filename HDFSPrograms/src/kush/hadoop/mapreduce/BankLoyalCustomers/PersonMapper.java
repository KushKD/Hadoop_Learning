package kush.hadoop.mapreduce.BankLoyalCustomers;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PersonMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
	/*	
		Person File (Personal Details)
		OMOI808692OZ,Allison,Abbott,21,female,Chicago

		CustomerId   CustomerFirstName   CustomerLastName  CustomerAge   CustomerGender    CustomerCity
		OMOI808692OZ  Allison				Abbott			21				female			Chicago
		*/
		
		String line = value.toString();  //OMOI808692OZ,Allison,Abbott,21,female,Chicago
		String[] dataLine  = line.split(",");  // {OMOI808692OZ} {Allison} {Abbott} {21} {female} {Chicago}
		
		context.write(new Text(dataLine[0]), new Text(line));   //  OMOI808692OZ  OMOI808692OZ,Allison,Abbott,21,female,Chicago
	}
	
	

}
