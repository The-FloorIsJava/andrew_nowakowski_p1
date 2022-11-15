create table customer(customer_id int primary key, customer_name varchar(255), balance numeric);
create table cart_item(cart_item_id int primary key, item_name varchar(255), price numeric, owned_by int, foreign key (owned_by) references customer(customer_id));
insert into customer values (1, 'tim', 4.50);
insert into customer values (2, 'joe', 7.50);
insert into customer values (3, 'kate', 2.50);
select * from customer;

/* inserting into the cart item table and referencing a customer in the customer table */
insert into cart_item values (1, 'tv', 49.99, 1);

select * from customer join cart_item on customer.customer_id = cart_item.owned_by;