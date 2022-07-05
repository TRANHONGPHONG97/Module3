use quanlysinhvien;

-- Hiển thị tất cả các thông tin môn học (bảng subject) có credit lớn nhất.
select SubId, SubName, Credit, Status
from subject
group by SubId
having avg(credit) >= all (select avg(credit) 
from subject
group by subject.credit);

-- Hiển thị các thông tin môn học có điểm thi lớn nhất.
SELECT subject.subName, max(credit)
FROM subject
join Mark M on subject.subid = M.subid
GROUP BY credit;

-- Hiển thị các thông tin sinh viên và điểm trung bình của mỗi sinh viên, xếp hạng theo thứ tự điểm giảm dần
SELECT S.StudentId, S.StudentName, AVG(Mark)
FROM Student S join Mark M on S.StudentId = M.StudentId
GROUP BY S.StudentId, S.StudentName
order by AVG(mark) DESC;