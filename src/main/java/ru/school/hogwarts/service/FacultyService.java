package ru.school.hogwarts.service;

import ru.school.hogwarts.model.Faculty;

import java.util.List;

public interface FacultyService {
    Faculty addFaculty(Faculty faculty);

    Faculty getFaculty(Long id);

    Faculty editFaculty(Faculty faculty);

    Faculty deleteFaculty(Long id);

    List<Faculty> getFacultyFilteredByAge(String color);
}

