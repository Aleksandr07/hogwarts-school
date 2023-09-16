package ru.school.hogwarts.service;

import ru.school.hogwarts.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);

    Student getStudent(long id);

    Student editStudent(Student student);

    void deleteStudent(long id);
    List<Student> getStudentFilteredByAge(int age);

    List<Student> getStudentByAgeBetween(int minAge, int maxAge);

}
