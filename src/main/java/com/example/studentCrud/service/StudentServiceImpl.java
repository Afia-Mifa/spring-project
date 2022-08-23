package com.example.studentCrud.service;

import com.example.studentCrud.Repository.StudentRepo;
import com.example.studentCrud.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements  StudentService{

    private final StudentRepo studentRepo;

    public StudentServiceImpl(StudentRepo studentRepo){
        this.studentRepo =  studentRepo;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public void saveStudent(Student student) {
        studentRepo.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
       Optional<Student> studentOptional = studentRepo.findById(id);
       Student student;

       if (studentOptional.isPresent()){
           student = studentOptional.get();
       }
       else{
           throw new RuntimeException("Student not found");
       }

       return student;
    }

    @Override
    public void updateStudent(Student student) {
        studentRepo.save(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentRepo.delete(student);
    }
}
