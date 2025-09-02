package ru.hogwarts.school;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FacultyController.class)
public class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @Test
    public void testGetAllFaculties() throws Exception {
        when(facultyService.getAllFaculties()).thenReturn(Arrays.asList(new Faculty("Gryffindor", "Red"), new Faculty("Slytherin", "Green")));

        mockMvc.perform(get("/faculties"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetFacultyById() throws Exception {
        when(facultyService.getFacultyById(1L)).thenReturn(Optional.of(new Faculty("Gryffindor", "Red")));

        mockMvc.perform(get("/faculties/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gryffindor"));
    }

    @Test
    public void testCreateFaculty() throws Exception {
        Faculty faculty = new Faculty("Gryffindor", "Red");
        when(facultyService.createFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(post("/faculties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Gryffindor\",\"color\":\"Red\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Gryffindor"));
    }

    @Test
    public void testUpdateFaculty() throws Exception {
        Faculty faculty = new Faculty("Slytherin", "Green");
        when(facultyService.updateFaculty(any(Long.class), any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(put("/faculties/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Slytherin\",\"color\":\"Green\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Slytherin"));
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        mockMvc.perform(delete("/faculties/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetFacultiesByNameOrColor() throws Exception {
        when(facultyService.getFacultiesByNameOrColor("Gryffindor")).thenReturn(Arrays.asList(new Faculty("Gryffindor", "Red")));

        mockMvc.perform(get("/faculties/search?query=Gryffindor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void testGetStudentsByFacultyId() throws Exception {
        when(facultyService.getStudentsByFacultyId(1L)).thenReturn(Arrays.asList(new Student("John Doe", 20)));

        mockMvc.perform(get("/faculties/1/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }
}