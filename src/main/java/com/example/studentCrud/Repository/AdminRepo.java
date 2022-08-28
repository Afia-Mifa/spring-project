package com.example.studentCrud.Repository;

import com.example.studentCrud.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepo extends JpaRepository<Admin, Long> {
    Admin findAdminByName(String name);
}
