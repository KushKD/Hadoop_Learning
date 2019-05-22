select ${empid},col2,col3,col4,col5 from emp_tab;
select col1,col6 from ${hiveconf:tablename};
select * from emp_tab where col6 =${deptno};
