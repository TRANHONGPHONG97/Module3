use classicmodels;
drop procedure if exists classicmodels.phoneNumber;
delimiter //
create procedure phoneNumber(
in phones varchar(50),
out message varchar(50)
)
begin
select phone from customers
-- where exists (select phone from customers	
where phone = phones; 
end //
delimiter ;
call phoneNumber(40322555);
