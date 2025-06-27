package ru.hogwarts.school.repository;

import org.springframework.data.repository.query.Param;
import ru.hogwarts.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List <Student> findAllByOrderByIdDesc();
    List<Student> findByAgeBetween(int min, int max);
    List<Student> findByFacultyId(Long facultyId);

    @Query("SELECT COUNT(s) FROM Student s")
    int getTotalStudentsCount();

    @Query("SELECT AVG(s.age) FROM Student s")
    double getAverageAge();

    @Query("SELECT s FROM Student s WHERE s.faculty.id = :facultyId")
    List<Student> findStudentsByFacultyId(@Param("facultyId") Long facultyId);

    @Query(value = "SELECT s.* FROM student s ORDER BY s.id DESC LIMIT 5", nativeQuery = true)
    List<Student> getLastFiveStudents();
}