create table if not exists Users (
id integer primary key autoincrement,
name varchar(20) not null,
phone varchar(20) default null
);


insert into Users (name, phone) values
('Petya', '1234567'),
('Vasya', '6543219'),
('Katya', null);


update Users
set name = 'Ne Petya', phone = '+7961333'
where id = 1
;


SELECT id, name, phone
from Users
WHERE
name not like '%Petya'
AND
phone is not NULL;