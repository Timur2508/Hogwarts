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
public class FacultyControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllFaculties() {
        ResponseEntity<Faculty[]> response = restTemplate.getForEntity("/faculties", Faculty[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testGetFacultyById() {
        ResponseEntity<Faculty> response = restTemplate.getForEntity("/faculties/1", Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testCreateFaculty() {
        Faculty faculty = new Faculty("Gryffindor", "Red");
        ResponseEntity<Faculty> response = restTemplate.postForEntity("/faculties", faculty, Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testUpdateFaculty() {
        Faculty faculty = new Faculty("Slytherin", "Green");
        restTemplate.put("/faculties/1", faculty);
        ResponseEntity<Faculty> response = restTemplate.getForEntity("/faculties/1", Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo("Slytherin");
    }

    @Test
    public void testDeleteFaculty() {
        restTemplate.delete("/faculties/1");
        ResponseEntity<Faculty> response = restTemplate.getForEntity("/faculties/1", Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetFacultiesByNameOrColor() {
        ResponseEntity<Faculty[]> response = restTemplate.getForEntity("/faculties/search?query=Gryffindor", Faculty[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testGetStudentsByFacultyId() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("/faculties/1/students", Student[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }
}