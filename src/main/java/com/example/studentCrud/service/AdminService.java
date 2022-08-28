package com.example.studentCrud.service;

import com.example.studentCrud.controller.AdminController;
import com.example.studentCrud.dto.RegisterDto;
import com.example.studentCrud.model.Admin;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AdminService  extends UserDetailsService {
    void createAdmin();
    List<Admin> adminList();
}
