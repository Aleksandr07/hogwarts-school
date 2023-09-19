package ru.school.hogwarts.service;

import ru.school.hogwarts.model.Faculty;

import java.util.List;

public interface FacultyService {
    Faculty addFaculty(Faculty faculty);

    Faculty getFaculty(long id);

    Faculty editFaculty(Faculty faculty);

    void deleteFaculty(long id);


    List<Faculty> getFacultyByNameOrColor(String nameOrColor);

}

