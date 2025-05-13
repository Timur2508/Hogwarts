package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Optional<Faculty> getFacultyById(Long id) {
        return facultyRepository.findById(id);
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty updateFaculty(Long id, Faculty faculty) {
        return facultyRepository.findById(id)
                .map(existingFaculty -> {
                    existingFaculty.setName(faculty.getName());
                    existingFaculty.setColor(faculty.getColor());
                    return facultyRepository.save(existingFaculty);
                })
                .orElseGet(() -> {
                    faculty.setId(id);
                    return facultyRepository.save(faculty);
                });
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }
}