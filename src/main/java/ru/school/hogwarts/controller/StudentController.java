package ru.school.hogwarts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.school.hogwarts.model.Faculty;
import ru.school.hogwarts.model.Student;
import ru.school.hogwarts.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student addedStudent = studentService.addStudent(student);
        if (addedStudent == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(addedStudent);
    }



    @GetMapping("all")
    public ResponseEntity<List<Student>> findAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getStudentFilteredByAge(@RequestParam(required = false) Integer age,
                                                              @RequestParam(required = false) Integer minAge,
                                                              @RequestParam(required = false) Integer maxAge) {

        if (minAge != null && maxAge != null) {
            return ResponseEntity.ok(studentService.getStudentByAgeBetween(minAge, maxAge));
        }
        if (age != null) {
            return ResponseEntity.ok(studentService.getStudentFilteredByAge(age));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}/faculty")
    public ResponseEntity<Faculty> getStudentFaculty(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudent(id).getFaculty());
    }

    @GetMapping("count-student")
    public ResponseEntity<Integer> countStudentInUniversity() {
        return ResponseEntity.ok(studentService.countStudentInUniversity());
    }

    @GetMapping("avg-age")
    public ResponseEntity<Double> countAverageAge() {
        return ResponseEntity.ok(studentService.countAverageAge());
    }

    @GetMapping("five-last-student")
    public ResponseEntity<List<Student>> findFiveLastStudent() {
        return ResponseEntity.ok(studentService.findFiveLastStudent());
    }

    @GetMapping("student-name-starts-with-A")
    public ResponseEntity<List<String>> findStudentWhoseNameStartsWithA() {
        return ResponseEntity.ok(studentService.findStudentWhoseNameStartsWithA());
    }

    @GetMapping("get-average-age")
    public ResponseEntity<Double> getAverageAge() {
        return ResponseEntity.ok(studentService.getAverageAge());
    }

    @GetMapping("get-six-students-name-with-parallel-threads")
    public void getSixStudentsNameWithParallelThreads() {
        studentService.getSixStudentsNameWithParallelThreads();
    }

    @GetMapping("get-six-students-name-with-synchronized-method")
    public void getSixStudentsNameWithSynchronizedMethod() {
        studentService.getSixStudentsNameWithSynchronizedMethod();
    }

    @PutMapping()
    public Student editStudent(@RequestBody Student student) {

        return studentService.editStudent(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
}
