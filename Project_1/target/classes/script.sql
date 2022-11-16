--creating the user table
create table user_table(
	u_username varChar(255) primary key,
	u_password varchar(255),
	u_position varchar(30)
);

--creating the reimbursement ticket table
create table reimbursement_ticket_table(
	id int primary key,
	username varchar(255),
	amount numeric,
	description text,
	status varchar(30),
	r_type varchar(30),
	foreign key (username) references user_table(u_username)
);

--inserting into user table
insert into user_table(u_username, u_password, u_position) values ('Andrew', 'password', 'Manager');
insert into user_table(u_username, u_password, u_position) values ('User', 'test', 'Employee');

--inserting into reimbursement ticket table
insert into reimbursement_ticket_table(id, username, amount, description, status, r_type) values (1, 'Andrew', 65.99, 'Seafood buffet', 'Pending', 'Food');
insert into reimbursement_ticket_table(id, username, amount, description, status, r_type) values (2, 'User', 99.99, 'Hotel 2 nights', 'Pending', 'Lodging');
insert into reimbursement_ticket_table(id, username, amount, description, status, r_type) values (3, 'User', 65.99, 'Gas', 'Pending', 'Travel');

--joining the tables to show all the information
select * from user_table join reimbursement_ticket_table on user_table.u_username = reimbursement_ticket_table.username;

--joining the tables where a specific username
select * from user_table join reimbursement_ticket_table on user_table.u_username = reimbursement_ticket_table.username where user_table.u_username = 'User';