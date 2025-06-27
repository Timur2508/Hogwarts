package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student createStudent(Student student);
    Student getStudentById(Long id);
    Optional<Student> findStudentOptional(Long id); // Добавленный метод
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);
    List<Student> getAllStudents();
    List<Student> getStudentsByAgeRange(int min, int max);
    Faculty getFacultyByStudentId(Long studentId);
    int getTotalStudentsCount();
    double getAverageAge();
    List<Object> getLastFiveStudents();
    double getAverageAgeUsingStream();
    List<String> getStudentsNameStartsWithA();

    Student findStudent(Long Id);
}