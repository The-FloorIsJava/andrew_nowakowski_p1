--enum of the users role
create type role as enum ('Employee', 'Manager', 'Admin');

--enum of the status of a reimbursement ticket
create type status as enum('Pending', 'Approved', 'Denied');

--enum of the reimbursement category
create type reimbursement_type as enum('Travel', 'Lodging', 'Food', 'Other');

--drop tables if they exists(TESTING ONLY)
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
	"id" int primary key,
	"username" varchar(255),
	"amount" numeric not null,
	"description" text not null,
	"status" status default 'Pending',
	"reimbursement_type" reimbursement_type default 'Other',
	foreign key ("username") references user_table("username") on delete set null
);

--inserting into user table
insert into user_table("username", "password", "position") values ('Andrew', 'password', 'Manager'::role);
insert into user_table("username", "password", "position") values ('User', 'test', 'Employee'::role);
insert into user_table("username", "password", "position") values ('Admin', 'Admin', 'Admin'::role);

--inserting into reimbursement ticket table
insert into reimbursement_ticket_table("id", "username", "amount", "description", "status", "reimbursement_type") values (1, 'Andrew', 65.99, 'Seafood buffet', 'Pending'::status, 'Food'::reimbursement_type);
insert into reimbursement_ticket_table("id", "username", "amount", "description", "status", "reimbursement_type") values (2, 'User', 99.99, 'Hotel 2 nights', 'Pending'::status, 'Lodging'::reimbursement_type);
insert into reimbursement_ticket_table("id", "username", "amount", "description", "status", "reimbursement_type") values (3, 'User', 65.99, 'Gas', 'Pending'::status, 'Travel'::reimbursement_type);

--joining the tables to show all the information
select * from user_table join reimbursement_ticket_table on user_table.username = reimbursement_ticket_table.username;

--joining the tables where a specific username
select * from user_table join reimbursement_ticket_table on user_table.username = reimbursement_ticket_table.username where user_table.username = 'User';

--get the max value from the id column
select max(id) from reimbursement_ticket_table;

--getting all pending reimbursement tickets along with the associated users
select * from reimbursement_ticket_table join user_table on user_table.username = reimbursement_ticket_table.username where reimbursement_ticket_table.status = 'Pending' order by reimbursement_ticket_table.id;

--set the status of a specific ticket if the ticket is not already processed
update reimbursement_ticket_table set status = 'Approved'::status where id = 3 and status = 'Pending'::status;

--get the previous tickets for a given user
select * from reimbursement_ticket_table where username = 'Andrew' order by reimbursement_ticket_table.id;
