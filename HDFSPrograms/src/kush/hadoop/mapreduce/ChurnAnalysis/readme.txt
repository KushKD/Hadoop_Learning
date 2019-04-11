Churn Customers:- Customers who are going to cease relationship with a company

Churn can be calculated weekly monthly quarterly or yearly

Pridict Churn Customers for a food ordering app and found out the possible reason 
for churn

JXJY167254JK,18-06-2017,654S654,Pizza:Manchurian:Chow Mein:Crispy Onion Rings,197,Wallet,Emperial,08:31:21,2,Late delivery


JXJY167254JK 									Customer ID
18-06-2017 										Order Date
654S654 										Order ID
Pizza:Manchurian:Chow Mein:Crispy Onion Rings   Array of Food ITems
197 											Total Order Bill
Wallet 											Mode of Payment
Emperial 										Resturant where the order was placed
08:31:21 										TimeStamp
2 												Rating Given by Customer out of 5
Late delivery   								Feedback Provided

Q).Customers who will be stop placing orders in coming months also find out the reason behind it.

A Customer is going to churn iff:
  no of bookings for a customer are declining continously for three consecitive months
  Each month order are less than 50% of previous month 
  				june   100 orders
  				july   35   <50% of 35
  				August   10  < 50% of 10
  				September 2  <50% of 10
  				
  				
  Capture all rating of a customer which is less than equal to 3 and whichever feedback is more in number,that is considered as churn person
  
  hadoop jar /home/hirwuser1249/food/food.jar kush.hadoop.mapreduce.ChurnAnalysis.CADriver dataset/food.txt output

