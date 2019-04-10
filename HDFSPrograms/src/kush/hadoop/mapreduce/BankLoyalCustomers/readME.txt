We have two files 

Person File (Personal Details)
OMOI808692OZ,Allison,Abbott,21,female,Chicago

CustomerId   CustomerFirstName   CustomerLastName  CustomerAge   CustomerGender    CustomerCity
OMOI808692OZ  Allison				Abbott			21				female			Chicago



Account File
Contains the transactional Info of the Customer in a Year
OMOI808692OZ,1245015582,savings,3667,822,no

CustomerId    AccountNo   AccountType  DepositQuater  WithdrawlQuater  GoodBadCustomer
OMOI808692OZ  1245015582  savings      3667            822              no

Now the Problem Statement
Find Bank Loyal Customers? 
A Bank considers a customer as loyal if he/she has flag no for every Quater , 
Withdraw amount for each quater should be less than half of its respective Quater,
Quater1 < Quater1/2
Quater2 < Quater2/2
Quater3 < Quater3/2
Quater4 < Quater4/2

Total Deposit for all the Quaters Must be greater than 10000

hadoop jar /home/hirwuser1249/bank/bank.jar kush.hadoop.mapreduce.BankLoyalCustomers.AccountPersonDriver dataset/person dataset/account output

822 < 411 false not loyal

Answer
BFBR875138BZ    BFBR875138BZ,Bonnie,Alford,23,female,Chicago,1188939438,current,1677,1466,no,Q_LOYAL,11020,4
BMHX832394HI    BMHX832394HI,Beryl,Allison,40,male,Los Angeles,1354777658,savings,2775,1745,no,Q_LOYAL,11007,4
BOFX584607FM    BOFX584607FM,Bill,Alexander,22,female,Houston,1104343425,savings,2729,1520,no,Q_LOYAL,13489,4
POOH283211OT    POOH283211OT,Alex,Adams,43,male,New York,1113028282,savings,2373,1240,no,Q_LOYAL,14058,4
