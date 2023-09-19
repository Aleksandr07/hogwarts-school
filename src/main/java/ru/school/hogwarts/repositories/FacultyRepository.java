package ru.school.hogwarts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.school.hogwarts.model.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {


    List<Faculty> findFacultyByNameIgnoreCaseOrColorIgnoreCase(String name, String color);

}
