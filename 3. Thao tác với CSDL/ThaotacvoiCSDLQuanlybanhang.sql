use quanlybanhang;

INSERT INTO Customer
VALUES  (1, 'Minh Quan', 10),
		(2, 'Ngoc Oanh', 20),
        (3, 'Hong Ha', 50);

INSERT INTO orders(oID, cID, oDate, oToTalPrice)
VALUES 	(1,1,'2006-3-21', null),
		(2,2,'2006-3-23', null),
		(3,1,'2006-3-16', null);
        
INSERT INTO Product
VALUES 	(1,'May Giat', 3),
		(2, 'Tu Lanh', 5),
		(3,'Dieu Hoa', 7),
        (4, 'Quat',1),
        (5, 'Bep Dien',2);
        
INSERT INTO OrderDetail (oID, pID, odQTY)
VALUES	(1,1,3),
		(1,3,7),
        (1,4,2),
        (2,1,1),
        (3,1,8),
        (2,5,4),
        (2,3,3);
        
-- Hiển thị các thông tin  gồm oID, oDate, oPrice của tất cả các hóa đơn trong bảng Order
select oID, oDate, oTotalPrice
from orders;

-- Hiển thị danh sách các khách hàng đã mua hàng, và danh sách sản phẩm được mua bởi các khách (3 join 4 bảng)
select orders.oID, customer.cID, cName, pName
from orders  
join customer on orders.cID =  customer.cID
join orderdetail on orders.oID = orderdetail.oID
join product on orderdetail.pID = product.pID;

-- Hiển thị tên những khách hàng không mua bất kỳ một sản phẩm nào (left join)
select customer.cID, customer.cName
from customer
left join orders on orders.cID = customer.cID
where orders.cID is null
group by cName;

-- Hiển thị mã hóa đơn, ngày bán và giá tiền của từng hóa đơn 
-- (giá một hóa đơn được tính bằng tổng giá bán của từng loại mặt hàng xuất hiện trong hóa đơn. 
-- Giá bán của từng loại được tính = odQTY*pPrice)
select orders.oID, orders.oDate, orderdetail.odQTY * product.pPrice as oTotalPrice 
from orders
join orderdetail on orders.oID = orderdetail.oID
join product on orderdetail.pID = product.pID;
