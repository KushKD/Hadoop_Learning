#Average Bonus as per Department

employee = LOAD 'dataset/employee_pig/employee_chicago' USING PigStorage(',') AS(employeeId:int,employeeName:chararray,employeeDesignation:chararray,employeeDepartment:int,employeeSalary:float);
departments = LOAD 'dataset/employee_pig/department_chicago' USING PigStorage(';') AS(departmentId:int,departmentName:chararray, departmentAddress:map[]);
bonus = LOAD 'dataset/employee_pig/employeeBonus_chicago' USING PigStorage (',') AS(employeeId:int,bonus:float);
innerJoin_employeeDepartment =  JOIN employee BY  (employeeDepartment) , departments BY (departmentId);
innerjoin_bonusEmployee = JOIN employee BY  (employeeId) , bonus BY (employeeId);
innerjoin_employee_department_bonus = JOIN innerJoin_employeeDepartment BY (employee::employeeId), innerjoin_bonusEmployee BY (bonus::employeeId);
innerjoin_employee_department_bonus_result = FOREACH innerjoin_employee_department_bonus GENERATE innerJoin_employeeDepartment::employee::employeeId AS employeeID,innerJoin_employeeDepartment::employee::employeeName AS employeeName,innerJoin_employeeDepartment::employee::employeeDesignation AS employeeDesignation,innerJoin_employeeDepartment::employee::employeeSalary AS employeeSalary,innerJoin_employeeDepartment::departments::departmentId  AS departmentId,innerJoin_employeeDepartment::departments::departmentName AS departmentName,innerjoin_bonusEmployee::bonus::bonus AS Bonus;
group_innerjoin_employee_department_bonus_result  = GROUP innerjoin_employee_department_bonus_result BY departmentId;
averageBonus = FOREACH group_innerjoin_employee_department_bonus_result GENERATE group, AVG(innerjoin_employee_department_bonus_result.Bonus);

#ANSWER
#(43,5508.0) (Department ID, Average Bonus)
#(100,3527.684210526316)
#(210,3278.1)
#(328,3234.1)
#(351,10816.666666666666)
#(743,6872.625)
#(1145,1897.5)


#Number of Employes in Each Departmet
employee = LOAD 'dataset/employee_pig/employee_chicago' USING PigStorage(',') AS(employeeId:int,employeeName:chararray,employeeDesignation:chararray,employeeDepartment:int,employeeSalary:float);
departments = LOAD 'dataset/employee_pig/department_chicago' USING PigStorage(';') AS(departmentId:int,departmentName:chararray, departmentAddress:map[]);
innerJoin_employeeDepartment =  JOIN employee BY  (employeeDepartment) , departments BY (departmentId);
group_innerJoin_employeeDepartment = GROUP innerJoin_employeeDepartment BY departments::departmentId;
totalemployees = FOREACH group_innerJoin_employeeDepartment GENERATE group, COUNT(innerJoin_employeeDepartment.employeeId);

#Answer (Department Id , Number of Employees)
#(20,96) 
#(43,66)
#(56,9)
#(90,75)
#(100,5901)
#(110,365)
#(210,629)
#(328,41)
#(351,826)
#(743,256)
#(798,512)
#(863,68)
#(1145,175)
#(2470,459)
#(3290,788)
#(3588,24)

#Word Count Issues
dataset/wdataset

lines = LOAD 'dataset/wdataset.txt' USING PigStorage('\t') AS(line:chararray);
words = FOREACH lines GENERATE FLATTEN(TOKENIZE(line)) as word;
groupWords = GROUP words BY word;
wordcount = FOREACH groupWords GENERATE group, COUNT(words);
DUMP wordcount;

