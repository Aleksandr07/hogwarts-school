package ru.school.hogwarts.service;

import org.springframework.stereotype.Service;
import ru.school.hogwarts.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    private final Map<Long, Student> studentStorage = new HashMap<>();
    private Long countId = 0L;

    @Override
    public Student addStudent(Student student) {
        if (!(student.getName() == null || student.getAge() == 0)) {
            student.setId(++countId);
            studentStorage.put(countId, student);
            return student;
        }
        return null;
    }

    @Override
    public Student getStudent(Long id) {
        return studentStorage.get(id);
    }

    @Override
    public Student editStudent(Student student) {
        if (studentStorage.containsKey(student.getId())) {
            studentStorage.put(student.getId(), student);
            return student;
        }
        return null;
    }

    @Override
    public Student deleteStudent(Long id) {
        return studentStorage.remove(id);
    }

    @Override
    public List<Student> getStudentFilteredByAge(int age) {
        return studentStorage.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }
}
