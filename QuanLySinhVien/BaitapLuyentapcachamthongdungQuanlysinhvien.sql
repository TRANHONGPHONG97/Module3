use quanlysinhvien;
-- Hiển thị tất cả các thông tin môn học (bảng subject) có credit lớn nhất.
SELECT subid, subname, credit, avg(credit)
from subject
group by subid
having avg(credit) >=
all (Select avg(credit) from `subject`
group by `subject`.credit);

-- Hiển thị các thông tin môn học có điểm thi lớn nhất.
select `subject`.subname, max(credit)
from subject
join mark on `subject`.subid = mark.subid
group by credit;
select * from `mark`;
select * from `subject`;

-- Hiển thị các thông tin sinh viên và điểm trung bình của mỗi sinh viên, xếp hạng theo thứ tự điểm giảm dần
SELECT Student.*, AVG(Mark) as `Điểm trung bình`
FROM student
Join Mark ON Student.studentid = mark.studentid
GROUP BY studentid
order by AVG(mark) DESC;