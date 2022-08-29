package com.example.studentCrud.service;

import com.example.studentCrud.Repository.StudentLoginRepo;
import com.example.studentCrud.model.StudentLogin;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentLoginServiceImpl implements StudentLoginService{

    private final StudentLoginRepo studentLoginRepo;

    public StudentLoginServiceImpl(StudentLoginRepo studentLoginRepo) {
        this.studentLoginRepo = studentLoginRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StudentLogin studentLogin = studentLoginRepo.findByEmail(username);
        if (studentLogin == null) {
            throw new UsernameNotFoundException("Invalid Student Credentials");
        }
        return new StudentPrincipal(studentLogin);
    }

}
