package ru.hogwarts.school;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllStudents() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("/students", Student[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testGetStudentById() {
        ResponseEntity<Student> response = restTemplate.getForEntity("/students/1", Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testCreateStudent() {
        Student student = new Student("John Doe", 20);
        ResponseEntity<Student> response = restTemplate.postForEntity("/students", student, Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student("Jane Doe", 22);
        restTemplate.put("/students/1", student);
        ResponseEntity<Student> response = restTemplate.getForEntity("/students/1", Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo("Jane Doe");
    }

    @Test
    public void testDeleteStudent() {
        restTemplate.delete("/students/1");
        ResponseEntity<Student> response = restTemplate.getForEntity("/students/1", Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetStudentsByAgeRange() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("/students/age?min=10&max=20", Student[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testGetFacultyByStudentId() {
        ResponseEntity<Faculty> response = restTemplate.getForEntity("/students/1/faculty", Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }
}