package kush.hadoop.mapreduce.BankLoyalCustomers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PersonAccountReducer extends Reducer<Text, Text, Text, Text> {
	
	//OMOI808692OZ	Account,OMOI808692OZ,1245015582,savings,3667,822,no
		//  OMOI808692OZ  PERSON,OMOI808692OZ,Allison,Abbott,21,female,Chicago
		//OMOI808692OZ,1245015582,savings,3667,822,no

	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		
		List<String> personList = new ArrayList<String>();
		List<String> accountList = new ArrayList<String>();
		List<String> accountPerson = new ArrayList<String>();
		List<String> noFraud = new ArrayList<String>();
		HashMap<String, String> innerJoinList = new HashMap<String, String>();
		 int totalDepositAllQuater = 0;
		 int length_No = 4;
		 
		
		
		
		
	for (Text text : values) {
			
			String line = text.toString();
			//Account,OMOI808692OZ,1245015582,savings,3667,822,no
			//PERSON,OMOI808692OZ,Allison,Abbott,21,female,Chicago
			Float withdrawAmountQuater = 0F;
			
			String[] line_data = line.split(","); 
			
			
			if(line_data[0].equalsIgnoreCase("ACCOUNT")) {
				//Calculate the Total Amount
				totalDepositAllQuater += Integer.parseInt(line_data[4]);
				//Calculate Withdraw Amount Quater
				String quaterlyWithdrawAmmountLoyality = calculate_WithdrawnAmmount(line_data[4],line_data[5]);
				
				if(line_data[6].equalsIgnoreCase("no")) {
					noFraud.add(line_data[6]);
				}
				
				
				//if(noFraud.size()>=4) {
					accountList.add(line_data[2]+","+line_data[3]+","+line_data[4]+","+line_data[5]+","+line_data[6]+","+quaterlyWithdrawAmmountLoyality+","+totalDepositAllQuater+","+Integer.toString(noFraud.size()));
				//}
				
				
			}else {
				personList.add(line_data[1]+","+line_data[2]+","+line_data[3]+","+line_data[4]+","+line_data[5]+","+line_data[6]);
			}
				
	}
	
	//Ittreate the List for Inner Join
	for (String person : personList) {
		for (String account : accountList) {
			accountPerson.add(person+","+account);
		}
	}
	
	
	//accountList
	//Map List
	for(int i=0; i<accountPerson.size();i++) {
		
		String Value = accountPerson.get(i).toString();
		innerJoinList.put(key.toString(), Value);  // UNMZ436655MA,Chantal,Alston,30,female,Denver,1108235342,current,1547,1014,yes,L
	
	}
	
	//Check if all the quater is no
	//Total Deposit for all the Quaters Must be greater than 10000
	 for (Map.Entry<String,String> entry : innerJoinList.entrySet()) {
		// System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		 String value = entry.getValue();
		 String[] valueData = value.split(","); 
		 //Q_LOYAL 11,9464 12,2 13
		if(valueData[11].equalsIgnoreCase("Q_LOYAL")&& Integer.parseInt(valueData[12])>10000 && Integer.parseInt(valueData[13])>=4) {
			 context.write(key, new Text(value));
		}
		 
		
			
		 
		 
		
		 
	 }
		 
		 
		 
        
	
	
	
	
	
	
	}

	private String calculate_WithdrawnAmmount(String depositTotal,String withdrawValue) {
		// TODO Auto-generated method stub
		Float QuaterWithdraw = 0F;
		//Convert the String to Float
		Float withdraw_Value = Float.parseFloat(withdrawValue.toString());
		Float Deposit_Value = Float.parseFloat(depositTotal.toString());
		//Calcute the quaterWithdrawValue
		QuaterWithdraw = (Deposit_Value/2);
		
		if(QuaterWithdraw < withdraw_Value) {
			return "Q_LOYAL"; 
		}else {
			return "Q_NLOYAL";
		}
		
		
		
	}
	
	
	
	

}
