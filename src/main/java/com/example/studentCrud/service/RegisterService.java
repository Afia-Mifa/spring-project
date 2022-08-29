package com.example.studentCrud.service;

import com.example.studentCrud.dto.RegisterDto;
import com.example.studentCrud.model.StudentLogin;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface RegisterService{
    void registerStudent(RegisterDto registerDto,int active);
}
