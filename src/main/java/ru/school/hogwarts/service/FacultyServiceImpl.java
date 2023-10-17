package ru.school.hogwarts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.school.hogwarts.exceptions.EntityNotFoundException;
import ru.school.hogwarts.model.Faculty;
import ru.school.hogwarts.repositories.FacultyRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService{

    private final FacultyRepository facultyRepository;

    private final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        if (!(faculty.getName() == null || faculty.getColor() == null)) {
            return facultyRepository.save(faculty);
        }
        logger.warn("Name or color not provided");
        return null;
    }

    @Override
    public Faculty getFaculty(long id) {
        logger.info("Was invoked method for get faculty by id");
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isEmpty()) {
            logger.error("There is not faculty with id = {}", id);
            throw new EntityNotFoundException();
        }
        return faculty.get();
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        logger.info("Was invoked method for edit faculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long id) {
        logger.info("Was invoked method for delete faculty");
        facultyRepository.deleteById(id);
    }


    @Override
    public List<Faculty> getFacultyByNameOrColor(String nameOrColor) {
        logger.info("Was invoked method for get faculty by name or color");
        return facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(nameOrColor, nameOrColor);
    }

    @Override
    public List<Faculty> findAll() {
        logger.info("Was invoked method for get all faculty");
        return facultyRepository.findAll();
    }

    @Override
    public String getLongestFacultyName() {
        return findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparing(String::length))
                .orElse("Наибольшее название факультета не найдено");
    }

}
