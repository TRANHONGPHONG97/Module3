use demo;

-- Tạo bảng product
create table products (
Id int auto_increment primary key,
productCode int unique,
productName varchar(50),
productPrice double,
productAmount int,
productDescription varchar(50),
productStatus varchar(50)
);

-- Thêm dữ liệu sản phẩm
insert into products
values (1,1, 'Chè đậu đỏ', 12000, 12, 'ngon', 'lạnh');
insert into products
values (2,2, 'Chè đậu xanh', 13000, 13, 'ngon', 'nóng');
insert into products
values (3,3, 'Chè bột lọc', 10000, 10, 'ngon', 'nóng');
insert into products
values (4,4, 'Chè thái', 15000, 20, 'ngon', 'lạnh');
insert into products
values (5,5, 'Chè thập cẩm', 15000, 15, 'ngon', 'lạnh');

select * from products
where productCode = 1;

-- Tạo Unique Index trên bảng Products (sử dụng cột productCode để tạo chỉ mục)
CREATE UNIQUE INDEX idx_Code
ON products (productCode);

-- Sử dụng câu lệnh EXPLAIN để biết được câu lệnh SQL của bạn thực thi như nào
explain select * from products
where productCode = 1;

-- Tạo Composite Index trên bảng Products (sử dụng 2 cột productName và productPrice)
CREATE INDEX indexName_Price
ON products (productName, productPrice);

-- Sử dụng câu lệnh EXPLAIN để biết được câu lệnh SQL của bạn thực thi như nào
explain select * from products
where productCode = 1;

-- So sánh câu truy vấn trước và sau khi tạo index (Sau khi có index, thì tốc độ truy vấn dữ liệu nhanh hơn khi không có index)

-- Tạo view lấy về các thông tin: productCode, productName, productPrice, productStatus từ bảng products.
CREATE VIEW products_view AS
SELECT productCode, productName, productPrice, productStatus
FROM products;

-- Tiến hành sửa đổi view
ALTER VIEW products_view AS
SELECT Id, productCode, productName, productPrice, productStatus
FROM products;

-- Tiến hành xoá view
DROP VIEW products_view;

-- Tạo store procedure lấy tất cả thông tin của tất cả các sản phẩm trong bảng product
DELIMITER //
CREATE PROCEDURE allProducts()
BEGIN
SELECT * FROM products;
END // 
DELIMITER ;
call allProducts();

-- Tạo store procedure thêm một sản phẩm mới
DELIMITER //
CREATE PROCEDURE addNewProduct(
Id int,
productCode int,
productName varchar(50),
productPrice double,
productAmount int,
productDescription varchar(50),
productStatus varchar(50)
)
BEGIN
INSERT INTO Products 
VALUES (Id, productCode, productName, productPrice, productAmount, productDescription, productStatus);
END //
DELIMITER ;
CALL addNewProduct(6, 6, 'Chè thạch dừa', 11000, 12, 'ngọt', 'lạnh');
CALL allProducts();

-- tạo store procedure sửa thông tin sản phẩm theo id
DELIMITER //
CREATE PROCEDURE editProduct(id_edit INT, 
productsCode int, 
productsName varchar(50), 
productsPrice double, 
productsAmount int, 
productsDescription varchar(50), 
productsStatus varchar(50))
BEGIN
UPDATE Products
SET
productsCode = productCode, 
productsName = productName, 
productsPrice = productPrice, 
productsAmount = productAmount, 
productsDescription = productDescription, 
productsStatus = productStatus
WHERE Id = id_edit;
END //
DELIMITER ;
CALL editProduct(4, 8, 'chè dừa', 20000, 20, 'dở', 'lạnh');

-- Tạo store procedure xóa thông tin sản phẩm theo id
DELIMITER //
CREATE PROCEDURE deleteProduct(id_delete int)
BEGIN
DELETE FROM Products
WHERE id_delete = Id;
END //
DELIMITER ;
CALL deleteProduct(2);
CALL allProducts();


