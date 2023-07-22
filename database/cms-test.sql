drop database if exists cms_test;

create database cms_test;
use cms_test;

create table contact (
contact_id int primary key auto_increment,
first_name varchar(250) not null,
last_name varchar(250) not null,
email varchar(250) not null,
phone_number int not null
);

delimiter //
create procedure set_known_good_state()
begin

set sql_safe_updates = 0;

delete from contact;
alter table contact auto_increment = 1;

INSERT INTO contact 
	(contact_id, first_name, last_name, email, phone_number)
values
	(1, 'Robert', 'Smith', 'robsmith@test.com', 1234567891);
    
    set sql_safe_updates = 1;
    
end //

delimiter ;

call set_known_good_state();