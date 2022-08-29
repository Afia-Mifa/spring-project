package com.example.studentCrud.service;

import com.example.studentCrud.Repository.StudentLoginRepo;
import com.example.studentCrud.Repository.StudentRepo;
import com.example.studentCrud.model.Student;
import com.example.studentCrud.model.StudentLogin;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;
    private final StudentLoginRepo studentLoginRepo;

    public StudentServiceImpl(StudentRepo studentRepo, StudentLoginRepo studentLoginRepo) {
        this.studentRepo = studentRepo;
        this.studentLoginRepo = studentLoginRepo;
    }

    @Override
    public List<Student> getAllActiveStudents() {
        List<Student> activeStudents = studentRepo.findAllByActive(1);
        return activeStudents;
    }

    @Override
    public List<Student> searchStudentByName(String name) {
        return studentRepo.findByName(name);
    }

    @Override
    public void saveStudent(Student student) {
        studentRepo.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        Optional<Student> studentOptional = studentRepo.findById(id);
        Student student;
        if (studentOptional.isPresent()) {
            student = studentOptional.get();
        } else {
            throw new RuntimeException("Student not found");
        }
        return student;
    }

    @Override
    public void updateStudent(Student student) {
        studentRepo.save(student);
    }


    @Override
    public List<Student> getInactiveStudents(int active) {
        return studentRepo.findAllByActive(active);
    }

    @Override
    public void activateStudentAccount(Long id) {
        Student student = getStudentById(id);
        student.setActive(1);
        studentRepo.save(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentRepo.delete(student);
    }
    @Override
    public void deleteStudentLogin(StudentLogin studentLogin) {
        studentLoginRepo.delete(studentLogin);
    }

    @Override
    public StudentLogin getStudentLogin(Student student) {
        StudentLogin studentLogin = studentLoginRepo.findByStudent(student);
        return studentLogin;
    }


}
