package ru.school.hogwarts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.school.hogwarts.model.CountAverageAge;
import ru.school.hogwarts.model.CountStudentInUniversity;
import ru.school.hogwarts.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByAge(int age);

    List<Student> findStudentByAgeBetween(int minAge, int maxAge);

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    CountStudentInUniversity countStudentInUniversity();

    @Query(value = "SELECT AVG(age) as averageAge FROM student", nativeQuery = true)
    CountAverageAge countAverageAge();

    @Query(value = "SELECT * FROM student OFFSET (SELECT COUNT(id) FROM student) - 5", nativeQuery = true)
    List<Student> findFiveLastStudent();
}
