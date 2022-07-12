-- Viết 1 store thêm 1 customer vào table customers
use classicmodels;
drop procedure if exists classicmodels.addNewCustomer;
delimiter //
create procedure addNewCustomer (
customerNumber int,
customerName varchar (50),
contactLastName varchar (50),
contactFirstName varchar (50),
phone varchar (50),
addressLine1 varchar (50),
city varchar (50),
country varchar (50)
)
begin
INSERT INTO `classicmodels`.`customers` (`customerNumber`, `customerName`, `contactLastName`, `contactFirstName`, `phone`, `addressLine1`, `city`, `country`) 
VALUES ('451', 'Hong', 'An', 'Min', '012345678', 'Hua', 'Ha', 'USA');
end //
delimiter ;

