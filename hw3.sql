create table employee(
employee_name varchar(50),
street varchar(30),
city varchar (30),
primary key (employee_name));

create table works (
employee_name varchar (50),
company_name varchar (50),
salary int (50),
primary key (employee_name),
foreign key (employee_name) references employee(employee_name));

create table company (
company_name varchar (50),
city varchar (30),
primary key (company_name));

create table manages (
employee_name varchar (50),
manager_name varchar (50),
primary key (employee_name));

select employee_name 
from employee natural left outer join manages
where manager_name is null;


select employee_name 
from employee as emp 
where exists (select employee_name from manages as man
			where emp.employee_name = man.employee_name and man.manager_name is null);

select employee_name from employee as emp where not exists (
select employee_name 
from manages as man 
where emp.employee_name = man.employee_name and man.manager_name is not null);

create view tot_credits (year, num_credits) as 
(select year, sum (credits)
from takes antural join courses group by year);

