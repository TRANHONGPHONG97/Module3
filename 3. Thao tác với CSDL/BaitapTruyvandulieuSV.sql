USE quanlysinhvien;

-- Hiển thị tất cả sinh viên
select * from student;

-- Hiển thị tất cả sinh viên bắt đầu bằng chữ H 
select * from student 
where StudentName like 'h%';

-- Hiển thị các thông tin lớp học có thời gian bắt đầu vào tháng 12
select * from class 
where StartDate like '12%';

-- Hiển thị tất cả các thông tin môn học có credit trong khoảng từ 3-5
select * from subject 
where Credit between 3 and 5;

-- Thay đổi mã lớp(ClassID) của sinh viên có tên ‘Hung’ là 2
select * from student;
update student set ClassId = 2
where StudentId = 1;

-- Hiển thị các thông tin: StudentName, SubName, Mark. Dữ liệu sắp xếp theo điểm thi (mark) giảm dần, nếu trùng sắp theo tên tăng dần
Select studentName, SubName, Mark
from subject join student join mark
order by mark DESC;