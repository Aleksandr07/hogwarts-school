package ru.school.hogwarts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.school.hogwarts.model.Faculty;
import ru.school.hogwarts.model.Student;
import ru.school.hogwarts.service.StudentService;

import java.util.Collection;

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

    @GetMapping("/faculty")
    public ResponseEntity<Faculty> getStudentFaculty(@RequestParam String name) {
        return ResponseEntity.ok(studentService.getStudentFaculty(name));
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
