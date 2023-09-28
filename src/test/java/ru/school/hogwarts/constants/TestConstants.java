package ru.school.hogwarts.constants;

import ru.school.hogwarts.model.Faculty;
import ru.school.hogwarts.model.Student;

import java.util.ArrayList;
import java.util.List;

public class TestConstants {
    public static final Long MOCK_FACULTY_ID = 1L;
    public static final String MOCK_FACULTY_NAME = "TestFacultyName";
    public static final String MOCK_FACULTY_COLOR = "TestFacultyColor";


    public static final String MOCK_FACULTY_OTHER_NAME = "TestFacultyOtherName";


    public static final Faculty MOCK_FACULTY = new Faculty(
            MOCK_FACULTY_ID,
            MOCK_FACULTY_NAME,
            MOCK_FACULTY_COLOR
    );
    public static final Faculty MOCK_OTHER_FACULTY = new Faculty(
            MOCK_FACULTY_ID,
            MOCK_FACULTY_OTHER_NAME,
            MOCK_FACULTY_COLOR
    );


    public static final List<Faculty> MOCK_FACULTIES = new ArrayList<>(List.of(
            new Faculty(1L, MOCK_FACULTY_NAME, MOCK_FACULTY_COLOR),
            new Faculty(2L, MOCK_FACULTY_OTHER_NAME, MOCK_FACULTY_COLOR)
    ));

    public static final Long MOCK_STUDENT_ID = 1L;
    public static final String MOCK_STUDENT_NAME = "TestStudentName";
    public static final String MOCK_STUDENT_OTHER_NAME = "TestStudentOtherName";
    public static final int MOCK_STUDENT_AGE = 15;

    public static final Student MOCK_STUDENT = new Student(
            MOCK_STUDENT_ID,
            MOCK_STUDENT_NAME,
            MOCK_STUDENT_AGE
    );

    public static final Student MOCK_OTHER_STUDENT = new Student(
            MOCK_STUDENT_ID,
            MOCK_STUDENT_OTHER_NAME,
            MOCK_STUDENT_AGE
    );

    public static final List<Student> MOCK_STUDENTS = new ArrayList<>(List.of(
            new Student(1L, MOCK_STUDENT_NAME, MOCK_STUDENT_AGE),
            new Student(2L, MOCK_STUDENT_OTHER_NAME, MOCK_STUDENT_AGE)
    ));

}
