create database academy default character set utf8;
create table academy.students ( id int, first_name varchar(50), last_name varchar(50));

alter table academy.students add constraint pk_students_id primary key (id);
use academy;
alter table students add column group_id int not null;
alter table students modify column id int auto_increment;


create table groups (id int, name varchar(50));
alter table groups add constraint pk_groups_id primary key (id);

alter table groups modify column id int auto_increment;
alter table groups modify column name varchar(50) not null unique ;

alter table students add constraint fk_students_groups_id foreign key (group_id) references groups (id);

-- DML: INSERT, UPDATE, DELETE
insert into groups(name) values ('Java01');
insert into students (first_name, last_name, group_id) values ('Vasya', 'Pypkin', 1);

update groups set name = 'Java 2023' where id = 1;