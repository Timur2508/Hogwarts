package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

public class StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private long currentId = 1;

    public List<Student> getAllStudents() {
        return students.values().stream().collect(Collectors.toList());
    }

    public Student getStudentById(long id) {
        return students.get(id);
    }

    public Student createStudent(Student student) {
        student.setId(currentId++);
        students.put(student.getId(), student);
        return student;
    }

    public Student updateStudent(Long id, Student student) {
        if (students.containsKey(id)) {
            student.setId(id);
            students.put(id, student);
            return student;
        }
        return null;
    }

    public void deleteStudent(long id) {
        students.remove(id);
    }

    public List<Student> getStudentsByAge(int age) {
        return students.values().stream().
                filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }
}
