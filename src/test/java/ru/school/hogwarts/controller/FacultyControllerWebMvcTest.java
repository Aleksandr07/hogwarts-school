package ru.school.hogwarts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.school.hogwarts.model.Faculty;
import ru.school.hogwarts.repositories.FacultyRepository;
import ru.school.hogwarts.service.FacultyServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.school.hogwarts.constants.TestConstants.*;

@WebMvcTest(controllers = FacultyController.class)
class FacultyControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyServiceImpl facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void addFaculty() throws Exception {
        when(facultyRepository.save(any(Faculty.class))).thenReturn(MOCK_FACULTY);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", MOCK_FACULTY_NAME);
        jsonObject.put("color", MOCK_FACULTY_COLOR);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(MOCK_FACULTY_NAME))
                .andExpect(jsonPath("$.color").value(MOCK_FACULTY_COLOR));
    }
    @Test
    public void getFaculty() throws Exception {
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(MOCK_FACULTY));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + MOCK_FACULTY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(MOCK_FACULTY_ID))
                .andExpect(jsonPath("$.name").value(MOCK_FACULTY_NAME))
                .andExpect(jsonPath("$.color").value(MOCK_FACULTY_COLOR));
    }

    @Test
    public void editFaculty() throws Exception {

        when(facultyRepository.save(any(Faculty.class))).thenReturn(MOCK_OTHER_FACULTY);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", MOCK_FACULTY_ID);
        jsonObject.put("name", MOCK_FACULTY_OTHER_NAME);
        jsonObject.put("color", MOCK_FACULTY_COLOR);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(MOCK_FACULTY_ID))
                .andExpect(jsonPath("$.name").value(MOCK_FACULTY_OTHER_NAME))
                .andExpect(jsonPath("$.color").value(MOCK_FACULTY_COLOR));
    }

    @Test
    public void deleteFaculty() throws Exception {
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(MOCK_FACULTY));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + MOCK_FACULTY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getFacultyByNameOrColor() throws Exception {
        when(facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(any(String.class), any(String.class))).thenReturn(MOCK_FACULTIES);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/?nameOrColor=" + MOCK_FACULTY_COLOR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(MOCK_FACULTIES)));
    }


}