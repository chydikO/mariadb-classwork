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



-- Home work database scripts --
CREATE DATABASE todolist;

USE todolist;
CREATE TABLE tasks (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       description VARCHAR(255) NOT NULL,
                       status BOOLEAN NOT NULL DEFAULT 0
);

USE todolist;
DELETE FROM tasks;

USE todolist;
INSERT INTO tasks (description, status) VALUES ('Задача 1', RAND() > 0.5);
INSERT INTO tasks (description, status) VALUES ('Задача 2', RAND() > 0.5);
INSERT INTO tasks (description, status) VALUES ('Задача 3', RAND() > 0.5);
INSERT INTO tasks (description, status) VALUES ('Задача 4', RAND() > 0.5);
INSERT INTO tasks (description, status) VALUES ('Задача 5', RAND() > 0.5);
INSERT INTO tasks (description, status) VALUES ('Задача 6', RAND() > 0.5);
INSERT INTO tasks (description, status) VALUES ('Задача 7', RAND() > 0.5);
INSERT INTO tasks (description, status) VALUES ('Задача 8', RAND() > 0.5);
INSERT INTO tasks (description, status) VALUES ('Задача 9', RAND() > 0.5);
INSERT INTO tasks (description, status) VALUES ('Задача 10', RAND() > 0.5);
