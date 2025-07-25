package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class StudentServiceImpl extends StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        logger.info("Creating student: {}", student);
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        logger.info("Fetching student by ID: {}", id);
        return findStudentOptional(id)
                .orElseThrow(() -> {
                    logger.error("Student with ID {} not found", id);
                    return new NoSuchElementException("Студент с ID " + id + " не найден");
                });
    }

    @Override
    public Student findStudent(Long id) {
        logger.info("Finding student by ID: {}", id);
        return studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Student not found with ID: {}", id);
                    return new NoSuchElementException("Студент с ID " + id + " не найден");
                });
    }

    @Override
    public Optional<Student> findStudentOptional(Long id) {
        logger.debug("Fetching optional student by ID: {}", id);
        return studentRepository.findById(id);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        logger.info("Updating student with ID {}: new data {}", id, student);
        Student existingStudent = getStudentById(id);
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        logger.info("Deleting student with ID: {}", id);
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        logger.info("Fetching all students");
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getStudentsByAgeRange(int min, int max) {
        logger.info("Fetching students by age range: {} - {}", min, max);
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Faculty getFacultyByStudentId(Long studentId) {
        logger.info("Fetching faculty for student ID: {}", studentId);
        return getStudentById(studentId).getFaculty();
    }

    @Override
    public int getTotalStudentsCount() {
        logger.info("Fetching total students count");
        return (int) studentRepository.count();
    }

    @Override
    public double getAverageAge() {
        logger.info("Calculating average age of students");
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }

    @Override
    public List<Object> getLastFiveStudents() {
        logger.info("Fetching last 5 students");
        return studentRepository.findAllByOrderByIdDesc().stream()
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public double getAverageAgeUsingStream() {
        logger.info("Calculating average age using stream");
        return getAverageAge();
    }

    @Override
    public List<String> getStudentsNameStartsWithA() {
        logger.info("Fetching students whose names start with 'A' or 'А'");
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .filter(name -> name != null && (name.startsWith("A") || name.startsWith("А")))
                .sorted()
                .toList();
    }
}