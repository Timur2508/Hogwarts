package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    default Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return null;
    }

    default Student getStudentById(Long id) {
        logger.info("Was invoked method for get student by id");
        return null;
    }

    default Optional<Student> findStudentOptional(Long id) {
        logger.info("Was invoked method for find student optional");
        return Optional.empty();
    }

    default Student updateStudent(Long id, Student student) {
        logger.info("Was invoked method for update student");
        return null;
    }

    default void deleteStudent(Long id) {
        logger.info("Was invoked method for delete student");
    }

    default List<Student> getAllStudents() {
        logger.info("Was invoked method for get all students");
        return null;
    }

    default List<Student> getStudentsByAgeRange(int min, int max) {
        logger.info("Was invoked method for get students by age range");
        return null;
    }

    default Faculty getFacultyByStudentId(Long studentId) {
        logger.info("Was invoked method for get faculty by student id");
        return null;
    }

    default int getTotalStudentsCount() {
        logger.info("Was invoked method for get total students count");
        return 0;
    }

    default double getAverageAge() {
        logger.info("Was invoked method for get average age");
        return 0;
    }

    default List<Object> getLastFiveStudents() {
        logger.info("Was invoked method for get last five students");
        return null;
    }

    default double getAverageAgeUsingStream() {
        logger.info("Was invoked method for get average age using stream");
        return 0;
    }

    default List<String> getStudentsNameStartsWithA() {
        logger.info("Was invoked method for get students whose names start with A");
        return null;
    }

    default Student findStudent(Long id) {
        logger.info("Was invoked method for find student");
        return null;
    }
}