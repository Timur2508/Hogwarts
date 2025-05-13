package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

public class FacultyService {
    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long currentId = 1L;

    public List<Faculty> getAllFaculties() {
        return faculties.values().stream().collect(Collectors.toList());
    }
    public Faculty getFacultyById(long id) {
        return faculties.get(id);
    }
    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(currentId++);
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }
    public Faculty updateFaculty(Long id, Faculty faculty) {
        if (faculties.containsKey(id)) {
            faculty.setId(id);
            faculties.put(id, faculty);
            return faculty;
        }
        return null;
    }
    public void deleteFaculty(Long id) {
        faculties.remove(id);
    }
    public List<Faculty> getFacultiesByColor(String color) {
        return faculties.values().stream().
                filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
