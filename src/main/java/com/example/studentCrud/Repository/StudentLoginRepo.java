package com.example.studentCrud.Repository;

import com.example.studentCrud.model.Student;
import com.example.studentCrud.model.StudentLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentLoginRepo extends JpaRepository<StudentLogin, Long> {
    StudentLogin findByEmail(String email);
    StudentLogin findByStudent(Student student);
}


