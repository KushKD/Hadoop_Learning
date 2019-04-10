package kush.hadoop.mapreduce.BankLoyalCustomers;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AccountMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
	/*	Contains the transactional Info of the Customer in a Year
		OMOI808692OZ,1245015582,savings,3667,822,no

		CustomerId    AccountNo   AccountType  DepositQuater  WithdrawlQuater  GoodBadCustomer
		OMOI808692OZ  1245015582  savings      3667            822              no
		
		*/
		
		String line = value.toString(); //	OMOI808692OZ,1245015582,savings,3667,822,no
		String[] dataLine = line.split(",");  // 	{OMOI808692OZ} {1245015582} {savings} {3667}  {822} {no}
		
		context.write(new Text(dataLine[0]), new Text("ACCOUNT,"+line)); //OMOI808692OZ	Account,OMOI808692OZ,1245015582,savings,3667,822,no
		
		
		
		
		
	}
	
	
	

}
