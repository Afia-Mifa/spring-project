package com.example.studentCrud.service;

import com.example.studentCrud.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentService {
    List<Student> getAllStudents();
    void saveStudent(Student student);
    Student getStudentById(Long id);
    void updateStudent(Student student);
    void deleteStudent(Student student);
}
