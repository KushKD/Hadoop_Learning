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
Find Bank Loyal Customers? A Bank considers a customer as loyal if he/she has flag no for every Quater , Withdraw amount for each quater should be less than half of its respective Quater,
Quater1 < Quater1/2
Quater2 < Quater2/2
Quater3 < Quater3/2
Quater4 < Quater4/2

Total Deposit for all the Quaters Must be greater than 10000