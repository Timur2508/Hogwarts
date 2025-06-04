package ru.hogwarts.school;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void testGetAllStudents() throws Exception {
        when(studentService.getAllStudents()).thenReturn(Arrays.asList(new Student("John Doe", 20), new Student("Jane Doe", 22)));

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetStudentById() throws Exception {
        when(studentService.getStudentById(1L)).thenReturn(Optional.of(new Student("John Doe", 20)));

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student("John Doe", 20);
        when(studentService.createStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"age\":20}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student student = new Student("Jane Doe", 22);
        when(studentService.updateStudent(any(Long.class), any(Student.class))).thenReturn(student);

        mockMvc.perform(put("/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Jane Doe\",\"age\":22}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetStudentsByAgeRange() throws Exception {
        when(studentService.getStudentsByAgeRange(10, 20)).thenReturn(Arrays.asList(new Student("John Doe", 20)));

        mockMvc.perform(get("/students/age?min=10&max=20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void testGetFacultyByStudentId() throws Exception {
        when(studentService.getFacultyByStudentId(1L)).thenReturn(Optional.of(new Faculty("Gryffindor", "Red")));

        mockMvc.perform(get("/students/1/faculty"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gryffindor"));
    }
}