package com.example.studentCrud.Repository;

import com.example.studentCrud.model.StudentLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRegRepo extends JpaRepository<StudentLogin, String> {
    StudentLogin findByEmail(String email);
}


