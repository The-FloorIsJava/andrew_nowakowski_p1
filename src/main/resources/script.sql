--enum of the users role
create type role as enum ('Employee', 'Manager');

--enum of the status of a reimbursement ticket
create type status as enum('Pending', 'Approved', 'Denied');

--enum of the reimbursement category
create type reimbursement_type as enum('Travel', 'Lodging', 'Food', 'Other');

drop table if exists reimbursement_ticket_table;
drop table if exists user_table;

--creating the user table
create table user_table(
	"username" varChar(255) primary key,
	"password" varchar(255) not null,
	"position" role default 'Employee'
);

--creating the reimbursement ticket table
create table reimbursement_ticket_table(
	"id" serial primary key,
	"username" varchar(255),
	"amount" numeric not null,
	"description" text not null,
	"status" status default 'Pending',
	"reimbursement_type" reimbursement_type default 'Other',
	foreign key ("username") references user_table("username") on delete set null
);

--inserting into user table
insert into user_table("username", "password", "position") values ('Andrew', 'password', 'Manager');
insert into user_table("username", "password", "position") values ('User', 'test', 'Employee');

--inserting into reimbursement ticket table
insert into reimbursement_ticket_table("id", "username", "amount", "description", "status", "reimbursement_type") values (1, 'Andrew', 65.99, 'Seafood buffet', 'Pending', 'Food');
insert into reimbursement_ticket_table("id", "username", "amount", "description", "status", "reimbursement_type") values (2, 'User', 99.99, 'Hotel 2 nights', 'Pending', 'Lodging');
insert into reimbursement_ticket_table("id", "username", "amount", "description", "status", "reimbursement_type") values (3, 'User', 65.99, 'Gas', 'Pending', 'Travel');

--joining the tables to show all the information
select * from user_table join reimbursement_ticket_table on user_table.username = reimbursement_ticket_table.username;

--joining the tables where a specific username
select * from user_table join reimbursement_ticket_table on user_table.username = reimbursement_ticket_table.username where user_table.username = 'User';

