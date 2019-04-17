

--Load the dataset
stocks = LOAD '$input' USING PigStorage(',') AS (exchange:chararray, symbol:chararray, date:datetime, open:float, high:float, low:float, close:float,volume:int, adj_close:float);
DESCRIBE stocks

--Filter the Dataset (Only Records for the year 2003)
filterByYear = FILTER stocks by GetYear(date) == 2003;

--Group the record by Symbol to calculate the average 
groupBySymbol = GROUP filterByYear by symbol;

--Now we have to perform the aggregreation
DESCRIBE groupBySymbol;


--Now Calculate the Average Salary on the Group Records
avg_volume = FOREACH groupBySymbol GENERATE group , ROUND(AVG(filterByYear.volume)) as avgVolume;

--Now Order the Result in Decending Order
avg_volume_ordered = ORDER avg_volume BY avgVolume DESC;

--Store Top 10 Records
top10 = LIMIT avg_volume_ordered 10;
STORE top10 INTO '$output' USING PisSTorage(',');


