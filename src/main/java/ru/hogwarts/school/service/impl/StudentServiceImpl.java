package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return findStudentOptional(id)
                .orElseThrow(() -> new NoSuchElementException("Студент с ID " + id + " не найден"));
    }

    @Override
    public Student findStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Студент с ID " + id + " не найден"));
    }

    @Override
    public Optional<Student> findStudentOptional(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student existingStudent = getStudentById(id);
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getStudentsByAgeRange(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Faculty getFacultyByStudentId(Long studentId) {
        return getStudentById(studentId).getFaculty();
    }

    @Override
    public int getTotalStudentsCount() {
        return (int) studentRepository.count();
    }

    @Override
    public double getAverageAge() {
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }

    @Override
    public List<Object> getLastFiveStudents() {
        return studentRepository.findAllByOrderByIdDesc().stream()
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public double getAverageAgeUsingStream() {
        return getAverageAge();
    }

    @Override
    public List<String> getStudentsNameStartsWithA() {
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .filter(name -> name != null && (name.startsWith("A") || name.startsWith("А")))
                .sorted()
                .toList();
    }
}