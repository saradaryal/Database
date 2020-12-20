create table department(dept_name varchar(20),
	building varchar(15),
    budget	numeric(12,2),
    primary key(dept_name));
insert into department value ('biology', 'Watson', '90000');
insert into department value ('Comp Sci', 'Taylor', '100000');
insert into department value ('Elec Eng', 'Taylor', '85000');
insert into department value ('Finance', 'Painter', '120000');
insert into department value ('History', 'Painter', '50000');
insert into department value ('Music', 'Packard', '80000');
insert into department value ('Physics', 'Watson', '70000');

select distinct building from department;
select dept_name and building from department;
select dept_mame from department where budget >80000;


 

