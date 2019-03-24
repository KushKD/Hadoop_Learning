FKLY490998LB,2010-01-29 06:12:17,Mumbai,Ecommerce,39,13,25-35

Add ID  								FKLY490998LB
Add Timpstamp							2010-01-29 06:12:17
Add Location							Mumbai
Add Categories							Ecommerce
No of Clicks on that Add				39
No of Conversion from that add			13
Age Group								25-35

Question? 
We need to find out for each category , the average success rate Location wise

Calculate the success rate individually ie.  13/39 33.3
Then calculate the average

hadoop fs -copyFromLocal /home/hirwuser1249/adds/fb.txt datasets
hadoop jar /home/hirwuser1249/adds/facebookadds.jar kush.hadoop.mapreduce.FacebookAdds.AddsFacebookDriver datasets/fb.txt output/adds
hadoop fs -cat output/adds/part-r-00000

hadoop fs -rm -r output/adds

