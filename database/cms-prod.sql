drop database if exists cms_prod;

create database cms_prod;
use cms_prod;

create table contact (
contact_id int primary key auto_increment,
first_name varchar(250) not null,
last_name varchar(250) not null,
email varchar(250) not null,
phone_number int not null
);

SELECT * FROM contact;