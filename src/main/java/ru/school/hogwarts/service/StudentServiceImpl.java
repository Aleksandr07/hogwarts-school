package ru.school.hogwarts.service;

import org.springframework.stereotype.Service;
import ru.school.hogwarts.exceptions.EntityNotFoundException;
import ru.school.hogwarts.model.Student;
import ru.school.hogwarts.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        if (!(student.getName() == null || student.getAge() == 0)) {
            return studentRepository.save(student);
        }
        return null;
    }

    @Override
    public Student getStudent(long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return student.get();
    }

    @Override
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudentFilteredByAge(int age) {
        return studentRepository.findByAge(age);
    }

    @Override
    public List<Student> getStudentByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findStudentByAgeBetween(minAge, maxAge);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Integer countStudentInUniversity() {
        return studentRepository.countStudentInUniversity();
    }

    @Override
    public Double countAverageAge() {
        return studentRepository.countAverageAge();
    }

    @Override
    public List<Student> findFiveLastStudent() {
        return studentRepository.findFiveLastStudent();
    }


}
