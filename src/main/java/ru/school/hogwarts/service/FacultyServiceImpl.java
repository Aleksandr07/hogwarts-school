package ru.school.hogwarts.service;

import org.springframework.stereotype.Service;
import ru.school.hogwarts.exceptions.EntityNotFoundException;
import ru.school.hogwarts.model.Faculty;
import ru.school.hogwarts.repositories.FacultyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService{

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        if (!(faculty.getName() == null || faculty.getColor() == null)) {
            return facultyRepository.save(faculty);
        }
        return null;
    }

    @Override
    public Faculty getFaculty(long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return faculty.get();
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }


    @Override
    public List<Faculty> getFacultyByNameOrColor(String nameOrColor) {
        return facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(nameOrColor, nameOrColor);
    }

    @Override
    public List<Faculty> findAll() {
        return facultyRepository.findAll();
    }

}
