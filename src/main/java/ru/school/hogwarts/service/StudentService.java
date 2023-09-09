package ru.school.hogwarts.service;

import ru.school.hogwarts.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);

    Student getStudent(Long id);

    Student editStudent(Student student);

    Student deleteStudent(Long id);
    List<Student> getStudentFilteredByAge(int age);
}
