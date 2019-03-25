package kush.hadoop.mapreduce.FraudCustomers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FraudCustomersReducer extends Reducer<Text, CustomerWritable, Text, IntWritable> {


	 ArrayList<String> customers = new ArrayList<String>();

	@Override
	protected void reduce(Text key, Iterable<CustomerWritable> values, Context context)
			throws IOException, InterruptedException {
		
		int fraudPoints = 0;
		int returnsCount = 0;
		int ordersCount = 0;

		CustomerWritable customer = null;        // initialiszing writable class to null
		Iterator<CustomerWritable> valuesIter = values.iterator();
		
		while(valuesIter.hasNext()) {
			ordersCount++;
			
			customer = valuesIter.next();     // data =  Allison 26-01-2017,yes,16-02-2017
			
			 if (customer.getProductReturned()) {
				 returnsCount++;
				 
				 try
					{
					    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					    Date receiveDate = sdf.parse(customer.getProductReceivedDate());
					    Date returnDate = sdf.parse(customer.getProductReturnedDate());
					    
					    long diffInMillies = Math.abs(returnDate.getTime() - receiveDate.getTime());
					    long diffDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);     
					    
					    /* 1 fraud point to a customer whose (refund_date - receiving_date) > 10 days */
					    if (diffDays > 10)
						fraudPoints++;            // fraudPoints  12
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				 
			 }
		}
		
		/* 10 fraud points to the customer whose return rate is more than 50% */
		double returnRate = (returnsCount/(ordersCount*1.0))*100;
		if (returnRate >= 50)
		    fraudPoints += 10;

		customers.add(key.toString() + "," + customer.getCustomer_Name() + "," + fraudPoints);
	    }
	         //  [{BHEE999914ED,Ana,12} {CCWO777171WT,Arthur,12} {GGYZ333519YS,Allison,12}  {BPLA457837LB,Alex,0}.......]
		
		
		
		
	 @Override
	    protected void cleanup(Context c)throws IOException, java.lang.InterruptedException
	    {
		 
		 
		/* sort customers based on fraudpoints */
		Collections.sort(customers, new Comparator<String>()
				{
			public int compare(String s1, String s2)
			{
			    int fp1 = Integer.parseInt(s1.split(",")[2]);
			    int fp2 = Integer.parseInt(s2.split(",")[2]);
			    
			    return -(fp1-fp2);     /*For desscending order*/
			}});
		for (String f: customers)
		{
		    String[] words = f.split(",");
		    c.write(new Text(words[0] + "," + words[1]), new IntWritable(Integer.parseInt(words[2])));
		}                   // custID     // custname                                    // fraud points
	    }
	
	
	
	
	
	

}
