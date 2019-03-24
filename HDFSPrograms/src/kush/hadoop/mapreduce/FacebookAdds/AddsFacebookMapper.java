package kush.hadoop.mapreduce.FacebookAdds;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AddsFacebookMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String line = value.toString();
		/* Split csv string */
		String[] words = line.split(",");        // [ {FKLY490998LB} {2009-12-29 06:12:17} {Mumbai} {Ecommerce} {39} {13} {25-35} ]

		
		context.write(new Text(words[3]), new Text (words[2] + "," + words[4] + "," +  words[5]));
		
		
		
	}


	
	
	

}
