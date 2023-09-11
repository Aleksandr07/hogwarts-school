package ru.school.hogwarts.service;

import org.springframework.stereotype.Service;
import ru.school.hogwarts.model.Faculty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService{

    private final Map<Long, Faculty> facultyStorage = new HashMap<>();
    private long countId = 0;

    @Override
    public Faculty addFaculty(Faculty faculty) {
        if (!(faculty.getName() == null || faculty.getColor() == null)) {
            faculty.setId(++countId);
            facultyStorage.put(countId, faculty);
            return faculty;
        }
        return null;
    }

    @Override
    public Faculty getFaculty(long id) {
        return facultyStorage.get(id);
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        if (facultyStorage.containsKey(faculty.getId())) {
            facultyStorage.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    @Override
    public Faculty deleteFaculty(long id) {
        return facultyStorage.remove(id);
    }

    @Override
    public List<Faculty> getFacultyFilteredByAge(String color) {
        return facultyStorage.values().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
