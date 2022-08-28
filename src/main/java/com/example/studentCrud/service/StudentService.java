package com.example.studentCrud.service;

import com.example.studentCrud.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentService {
    List<Student> getAllStudents();

    List<Student> getInactiveStudents(int active);
    void activateStudentAccount(Long id);
    List<Student> searchStudentByName(String name);

    void saveStudent(Student student);

    Student getStudentById(Long id);

    void updateStudent(Student student);

    void deleteStudent(Student student);

}
