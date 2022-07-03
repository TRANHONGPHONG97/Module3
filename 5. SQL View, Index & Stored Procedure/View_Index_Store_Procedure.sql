create database View_Index_Store_Procedure;
use View_Index_Store_Procedure;

CREATE TABLE Products (
id INT AUTO_INCREMENT PRIMARY KEY,
productCode INT UNIQUE,
productName VARCHAR(45),
productPrice INT,
productAmount INT,
productDescription TEXT,
productStatus INT);

INSERT INTO Products
VALUES (1, 1, 'I-Phone', 102, 15, null, 1);
INSERT INTO Products
VALUES (2, 2, 'SamSung', 97, 12, null, 1);
INSERT INTO Products
VALUES (3, 3, 'Xiaomi', 80, 10, null, 1);
INSERT INTO Products
VALUES (4, 4, 'Nokia', 85, 15, null, 0);
INSERT INTO Products
VALUES (5, 5, 'I-pad', 92, 20, null, 1);

SELECT *
FROM PRODUCTs
WHERE productStatus = 1;

-- Tạo Unique Index trên bảng Products (sử dụng cột productCode để tạo chỉ mục)
ALTER TABLE Products ADD UNIQUE INDEX idx_Code(productCode);

-- SỬ DỤNG explain để xem câu lệnh được thực thi như thế nào
EXPLAIN SELECT * 
FROM products 
WHERE productStatus = 1;

-- tạo composite index trên bảng products (sử dụng 2 cột productName và productPrice)
alter table products add index idx_code2(productName, productPrice);

-- sử dụng explain để biết được câu lệnh sql được thực thi như thế nào
EXPLAIN SELECT * 
FROM PRODUCTS 
WHERE PRODUCTSTATUS = 1;

-- tạo view lấy thông tinL code, name, price,status
CREATE VIEW products_view AS
SELECT productCode, productName, productPrice, productStatus
FROM PRODUCTS;
select * from products_view;

ALTER VIEW products_view AS
select id, productCode, productName, productPrice, productStatus
from products;
select *
from products_view;

drop view products_view;

-- tạo store produre lấy tất cẩ thông tin của tất cả
DELIMITER //
CREATE PROCEDURE takeAllProduct()
BEGIN 
SELECT *
FROM Products;
END //
DELIMITER ;
CALL takeAllProduct();

-- tạo store procedure thêm 1sp mới
DELIMITER //
CREATE PROCEDURE addNewProduct(
id INT,
productCode INT,
productName VARCHAR(45),
productPrice INT,
productAmount INT,
productDescription TEXT,
productStatus INT)

BEGIN
  INSERT INTO Products
  VALUES (id, productCode, productName, productPrice, productAmount, productDescription, productStatus);
END //
DELIMITER ;

-- gọi ra
call  addNewProduct(1, 1, 'I-Phone', 102, 15, null, 1);

--  tạo 1 store procedre xóa thông tin sản phẩm
DELIMITER //
CREATE PROCEDURE removeProduct(id_remove INT)
BEGIN
  DELETE FROM Products
  WHERE id = id_remove;
END //
DELIMITER ;
call removeProduct(11);

-- tạo store procedure sửa thông tin sản phẩm theo id
DELIMITER //
CREATE PROCEDURE editProduct(id_edit INT)
begin
update products
set ID = id_edit;
end //
DELIMITER //
CALL editProduct(1);