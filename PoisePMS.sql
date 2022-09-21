create database if not exists PoisePMS;

use PoisePMS;

drop table if exists Finalised;

create table Finalised (
	id int,
	status varchar(50),
	primary key (id)
);

insert into Finalised
values (0, 'Unfinalised'),
       (1, 'Finalised');

drop table if exists Architects;

create table Architects (
	full_name varchar(50),
	telephone_number varchar(50),
	email_address varchar(50),
	physical_address varchar(255),
	primary key (full_name)
);

insert into Architects
values ('Ted Mosby', '0786124539', 'ted.mosby@gmail.com', '9720 16th Avenue, The Bronx'),
       ('Robin Sparkles', '0815783791', 'robin.sparkles@gmail.com', '163 Moose Road, Toronto');

drop table if exists Contractors;

create table Contractors (
	full_name varchar(50),
	telephone_number varchar(50),
	email_address varchar(50),
	physical_address varchar(255),
	primary key (full_name)
);

insert into Contractors
values ('Peter Parker', '0834796528', 'peter.parker@gmail.com', '5630 Rose Avenue, New York'),
       ('Mary Jane Watson', '0624589132', 'mj.watson@gmail.com', '1800 Lily Street, Brooklyn');

drop table if exists Customers;

create table Customers (
	full_name varchar(50),
	telephone_number varchar(50),
	email_address varchar(50),
	physical_address varchar(255),
	primary key (full_name)
);

insert into Customers
values ('Jonah Hill', '0846719834', 'jonah.hill@gmail.com', '21 Jump Street, Hollywood'),
       ('James Franco','0786289723','james.franco@gmail.com','1452 12th Avenue, Beverly Hills');

drop table if exists Projects;

create table Projects (
	project_number int,
	name varchar(50),
	building_type varchar(50),
	physical_address varchar(255),
	erf_number int,
	total_fee_charged decimal,
	total_amount_paid_to_date decimal,
	deadline date,
	finalised_status_id int,
	finalised_date date,
	architect_full_name varchar(50),
	contractor_full_name varchar(50),
	customer_full_name varchar(50),
	primary key (project_number)
);

insert into Projects
values (1, 'House Franco', 'House', '1501 Waterway, Bel-Air', 968, 2500000.00, 1200000.00, '2022/11/30', 0, null, 'Ted Mosby', 'Peter Parker', 'James Franco'),
       (2, 'Hotel Hill', 'Hotel', '1221 Wallaby Street, Beverly Hills', 346, 3100000.00, 1450000.00, '2022/03/31', 0, null, 'Robin Sparkles', 'Mary Jane Watson', 'Jonah Hill');