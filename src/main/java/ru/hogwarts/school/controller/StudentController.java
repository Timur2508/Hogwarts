package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return student != null
                ? ResponseEntity.ok(student)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,
                                                 @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/age")
    public ResponseEntity<List<Student>> getStudentsByAgeRange(
            @RequestParam int min,
            @RequestParam int max) {
        List<Student> students = studentService.getStudentsByAgeRange(min, max);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}/faculty")
    public ResponseEntity<Faculty> getFacultyByStudentId(@PathVariable Long id) {
        Faculty faculty = studentService.getFacultyByStudentId(id);
        return faculty != null
                ? ResponseEntity.ok(faculty)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getTotalStudentsCount() {
        int count = studentService.getTotalStudentsCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/average-age")
    public ResponseEntity<Double> getAverageAge() {
        double averageAge = studentService.getAverageAge();
        return ResponseEntity.ok(averageAge);
    }

    @GetMapping("/last-five")
    public ResponseEntity<List<Object>> getLastFiveStudents() {
        List<Object> students = studentService.getLastFiveStudents();
        return ResponseEntity.ok(students);
    }

    @PostMapping("/{id}/avatar")
    public ResponseEntity<String> uploadAvatar(
            @PathVariable Long id,
            @RequestParam MultipartFile avatar) throws IOException {
        avatarService.uploadAvatar(id, avatar);
        return ResponseEntity.ok("Avatar uploaded successfully");
    }

    @GetMapping("/{id}/avatar")
    public ResponseEntity<byte[]> getAvatar(@PathVariable Long id) {
        byte[] avatar = avatarService.getAvatar(id);
        return avatar != null
                ? ResponseEntity.ok(avatar)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/name-starts-with-a")
    public ResponseEntity<List<String>> getStudentsNameStartsWithA() {
        List<String> names = studentService.getStudentsNameStartsWithA();
        return ResponseEntity.ok(names);
    }

    @GetMapping("/average-age-stream")
    public ResponseEntity<Double> getAverageAgeUsingStream() {
        double averageAge = studentService.getAverageAgeUsingStream();
        return ResponseEntity.ok(averageAge);
    }
}