package com.example.studentCrud.Repository;

import com.example.studentCrud.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentRepo extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);
    List<Student> findAllByOrderByIdDesc();
    List<Student> findAllByActive(int active);

}
