create table if not exists Persons (
employee_id integer primary key autoincrement,
first_name varchar(20) not null,
last_name varchar(20) not null
);


insert into Persons (first_name, last_name) values
('Steven', 'King'),
('Neena', 'Kochhar'),
('Lex', 'De Haan'),
('Alexander', 'Hunold'),
('Bruce', 'Ernst'),
('David', 'Austin'),
('Valli', 'Pataballa'),
('Diana', 'Lorentz');


select * from Persons;