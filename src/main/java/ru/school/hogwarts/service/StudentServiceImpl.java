package ru.school.hogwarts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.school.hogwarts.exceptions.EntityNotFoundException;
import ru.school.hogwarts.model.Student;
import ru.school.hogwarts.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        logger.info("Was invoked method for create student");
        if (!(student.getName() == null || student.getAge() == 0)) {
            return studentRepository.save(student);
        }
        logger.warn("Name or age not provided");
        return null;
    }

    @Override
    public Student getStudent(long id) {
        logger.info("Was invoked method for get student by id");
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            logger.error("There is not student with id = {}", id);
            throw new EntityNotFoundException();
        }
        return student.get();
    }

    @Override
    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudentFilteredByAge(int age) {
        logger.info("Was invoked method for get student(s) filtered by age");
        return studentRepository.findByAge(age);
    }

    @Override
    public List<Student> getStudentByAgeBetween(int minAge, int maxAge) {
        logger.info("Was invoked method for get students whose ages range from min to max");
        return studentRepository.findStudentByAgeBetween(minAge, maxAge);
    }

    @Override
    public List<Student> findAll() {
        logger.info("Was invoked method for get all students");
        return studentRepository.findAll();
    }

    @Override
    public Integer countStudentInUniversity() {
        logger.info("Was invoked method for count students in university");
        return studentRepository.countStudentInUniversity();
    }

    @Override
    public Double countAverageAge() {
        logger.info("Was invoked method for count average age");
        return studentRepository.countAverageAge();
    }

    @Override
    public List<Student> findFiveLastStudent() {
        logger.info("Was invoked method for get five last student");
        return studentRepository.findFiveLastStudent();
    }


}
