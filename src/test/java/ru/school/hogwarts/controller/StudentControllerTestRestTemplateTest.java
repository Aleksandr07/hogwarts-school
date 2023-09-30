package ru.school.hogwarts.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import ru.school.hogwarts.model.Student;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.school.hogwarts.constants.TestConstants.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application-test.properties")
class StudentControllerTestRestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void getStudent() {
        ResponseEntity<Student> newStudentRs =
                studentController.addStudent(MOCK_STUDENT);
        Student newStudent = newStudentRs.getBody();
        Student student =
                testRestTemplate.getForObject("http://localhost:" + port + "/student/" + newStudent.getId(), Student.class);


        assertThat(student.getId()).isEqualTo(newStudent.getId());
        assertThat(student.getName()).isEqualTo(newStudent.getName());
        assertThat(student.getAge()).isEqualTo(newStudent.getAge());
    }


    @Test
    public void deleteStudent() {
        ResponseEntity<Student> newStudentRs =
                studentController.addStudent(MOCK_STUDENT);
        Student newStudent = newStudentRs.getBody();

        testRestTemplate.delete("http://localhost:" + port + "/student/" + newStudent.getId(), Student.class);

        ResponseEntity<Student> getStudentRs =
                testRestTemplate.getForEntity("http://localhost:" + port + "/student/" + newStudent.getId(), Student.class);

        Student student = getStudentRs.getBody();

        assertThat(student.getName()).isNull();
    }

    @Test
    public void editStudent() {
        ResponseEntity<Student> newStudentRs =
                studentController.addStudent(MOCK_STUDENT);
        Student newStudent = newStudentRs.getBody();

        newStudent.setName("1223");

       testRestTemplate.put("http://localhost:" + port + "/student", newStudent, Student.class);

        Student student =  testRestTemplate.getForObject("http://localhost:" + port + "/student/" + newStudent.getId(), Student.class);

        assertThat(student.getName()).isEqualTo(newStudent.getName());
    }

    @Test
    public void addStudent() {
        ResponseEntity<Student> newStudentRs =
                testRestTemplate.postForEntity("http://localhost:" + port + "/student", MOCK_STUDENT, Student.class);
        assertThat(newStudentRs.getStatusCode()).isEqualTo(HttpStatus.OK);
        Student newStudent = newStudentRs.getBody();
        ResponseEntity<Student> getStudentRs =
                studentController.getStudent(newStudent.getId());
        Student student = getStudentRs.getBody();

        assertThat(student.getId()).isEqualTo(newStudent.getId());
        assertThat(student.getName()).isEqualTo(newStudent.getName());
        assertThat(student.getAge()).isEqualTo(newStudent.getAge());
    }

    @Test
    public void getStudentFilteredByAge() {
        ResponseEntity<Student> newStudentRs =
                studentController.addStudent(MOCK_STUDENT);

        assertThat(testRestTemplate.getForObject("http://localhost:" + port + "/student?age=15", String.class))
                .isNotNull();
    }

}