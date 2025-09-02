package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.Optional;

@Service
public abstract class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return null;
    }

    public Student getStudentById(Long id) {
        logger.info("Was invoked method for get student by id");
        return null;
    }

    public Optional<Student> findStudentOptional(Long id) {
        logger.info("Was invoked method for find student optional");
        return Optional.empty();
    }

    public Student updateStudent(Long id, Student student) {
        logger.info("Was invoked method for update student");
        return null;
    }

    public void deleteStudent(Long id) {
        logger.info("Was invoked method for delete student");
    }

    public List<Student> getAllStudents() {
        logger.info("Was invoked method for get all students");
        return null;
    }

    public List<Student> getStudentsByAgeRange(int min, int max) {
        logger.info("Was invoked method for get students by age range");
        return null;
    }

    public Faculty getFacultyByStudentId(Long studentId) {
        logger.info("Was invoked method for get faculty by student id");
        return null;
    }

    public int getTotalStudentsCount() {
        logger.info("Was invoked method for get total students count");
        return 0;
    }

    public double getAverageAge() {
        logger.info("Was invoked method for get average age");
        return 0;
    }

    public List<Object> getLastFiveStudents() {
        logger.info("Was invoked method for get last five students");
        return null;
    }

    public double getAverageAgeUsingStream() {
        logger.info("Was invoked method for get average age using stream");
        return 0;
    }

    public List<String> getStudentsNameStartsWithA() {
        logger.info("Was invoked method for get students whose names start with A");
        return null;
    }

    public Student findStudent(Long id) {
        logger.info("Was invoked method for find student");
        return null;
    }
}