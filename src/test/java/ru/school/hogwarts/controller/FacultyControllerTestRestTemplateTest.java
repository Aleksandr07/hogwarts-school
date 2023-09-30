package ru.school.hogwarts.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import ru.school.hogwarts.model.Faculty;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.school.hogwarts.constants.TestConstants.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application-test.properties")
class FacultyControllerTestRestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getFaculty() {
        ResponseEntity<Faculty> newFacultyRs =
                facultyController.addFaculty(MOCK_FACULTY);
        Faculty newFaculty = newFacultyRs.getBody();
        Faculty faculty =
                testRestTemplate.getForObject("http://localhost:" + port + "/faculty/" + newFaculty.getId(), Faculty.class);


        assertThat(faculty.getId()).isEqualTo(newFaculty.getId());
        assertThat(faculty.getName()).isEqualTo(newFaculty.getName());
        assertThat(faculty.getColor()).isEqualTo(newFaculty.getColor());
    }


    @Test
    public void deleteFaculty() {
        ResponseEntity<Faculty> newFacultyRs =
                facultyController.addFaculty(MOCK_FACULTY);
        Faculty newFaculty = newFacultyRs.getBody();

        testRestTemplate.delete("http://localhost:" + port + "/faculty/" + newFaculty.getId(), Faculty.class);

        ResponseEntity<Faculty> getFacultyRs =
                testRestTemplate.getForEntity("http://localhost:" + port + "/faculty/" + newFaculty.getId(), Faculty.class);

        Faculty faculty = getFacultyRs.getBody();

        assertThat(faculty.getName()).isNull();
    }

    @Test
    public void editFaculty() {
        ResponseEntity<Faculty> newFacultyRs =
                facultyController.addFaculty(MOCK_FACULTY);
        Faculty newFaculty = newFacultyRs.getBody();

        newFaculty.setName("1223");

        testRestTemplate.put("http://localhost:" + port + "/faculty", newFaculty, Faculty.class);

        Faculty faculty =  testRestTemplate.getForObject("http://localhost:" + port + "/faculty/" + newFaculty.getId(), Faculty.class);

        assertThat(faculty.getName()).isEqualTo(newFaculty.getName());
    }

    @Test
    public void addFaculty() {
        ResponseEntity<Faculty> newFacultyRs =
                testRestTemplate.postForEntity("http://localhost:" + port + "/faculty", MOCK_FACULTY, Faculty.class);
        assertThat(newFacultyRs.getStatusCode()).isEqualTo(HttpStatus.OK);
        Faculty newFaculty = newFacultyRs.getBody();
        ResponseEntity<Faculty> getFacultyRs =
                facultyController.getFaculty(newFaculty.getId());
        Faculty faculty = getFacultyRs.getBody();

        assertThat(faculty.getId()).isEqualTo(newFaculty.getId());
        assertThat(faculty.getName()).isEqualTo(newFaculty.getName());
        assertThat(faculty.getColor()).isEqualTo(newFaculty.getColor());
    }

    @Test
    public void getFacultyByNameOrColor() {
        ResponseEntity<Faculty> newFacultyRs =
                facultyController.addFaculty(MOCK_FACULTY);

        assertThat(testRestTemplate.getForObject("http://localhost:" + port + "/faculty?nameOrColor=" + MOCK_FACULTY_COLOR, String.class))
                .isNotNull();
    }


}