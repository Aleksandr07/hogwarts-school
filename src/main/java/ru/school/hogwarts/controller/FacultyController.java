package ru.school.hogwarts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.school.hogwarts.model.Faculty;
import ru.school.hogwarts.model.Student;
import ru.school.hogwarts.service.FacultyService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty) {
        Faculty addedFaculty = facultyService.addFaculty(faculty);
        if (addedFaculty == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(addedFaculty);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public ResponseEntity<List<Faculty>> getFacultyByNameOrColor(@RequestParam(required = false) String name,
                                                                   @RequestParam(required = false) String color) {
        List<Faculty> faculties = facultyService.getFacultyByNameOrColor(name, color);
        if (faculties.size() == 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(faculties);
    }

    @GetMapping("/students")
    public ResponseEntity<Collection<Student>> getStudentsByFaculty(@RequestParam String name) {
        return ResponseEntity.ok(facultyService.getStudentsByFaculty(name));
    }

    @PutMapping()
    public Faculty editStudent(@RequestBody Faculty faculty) {

        return facultyService.editFaculty(faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }
}
