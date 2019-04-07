package kush.hadoop.mapreduce.DepartmentAverageSalary;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AverageSalaryMapper extends Mapper<LongWritable, Text, Text, Text>{
	

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
				
		String line = value.toString();
		String[] line_Values = line.split(",");   //1458,AARON ELVIA J,WATER RATE TAKER,328,73752
		
		
		context.write(new Text(line_Values[3]), new Text("1,"+line)); //3 1458,AARON ELVIA J,WATER RATE TAKER,328,73752
		
			
	}
	
	

}
