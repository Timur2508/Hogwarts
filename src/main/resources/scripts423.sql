SELECT s.name AS student_name, s.age, f.name AS faculty_name
FROM Student s
JOIN Faculty f ON s.faculty_id = f.id;

SELECT s.name AS student_name, s.age
FROM Student s
JOIN Avatar a ON s.id = a.student_id;