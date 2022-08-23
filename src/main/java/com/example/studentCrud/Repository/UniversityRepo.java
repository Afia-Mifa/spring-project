package com.example.studentCrud.Repository;

import com.example.studentCrud.model.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepo extends JpaRepository<University, Long> {
}
