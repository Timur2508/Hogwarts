package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyService(FacultyRepository facultyRepository,
                          StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    // Метод для получения студентов факультета
    public List<Student> getStudents(Long facultyId) {
        return studentRepository.findByFacultyId(facultyId);
    }

    // Другие методы сервиса...
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> getFacultyById(Long id) {
        return facultyRepository.findById(id);
    }

    public Faculty updateFaculty(Long id, Faculty faculty) {
        return null;
    }

    public void deleteFaculty(Long id) {

    }

    public List<Faculty> getFacultiesByNameOrColor(String query) {
        return null;
    }

    public List<Student> getStudentsByFacultyId(Long id) {
        return null;
    }

    public List<Faculty> getAllFaculties() {
        return null;
    }
}