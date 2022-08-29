package com.example.studentCrud.service;

import com.example.studentCrud.Repository.StudentLoginRepo;
import com.example.studentCrud.Repository.StudentRepo;
import com.example.studentCrud.dto.RegisterDto;
import com.example.studentCrud.model.Role;
import com.example.studentCrud.model.Student;
import com.example.studentCrud.model.StudentLogin;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final PasswordEncoder passwordEncoder;
    private final StudentRepo studentRepo;

    public RegisterServiceImpl(PasswordEncoder passwordEncoder, StudentRepo studentRepo) {
        this.passwordEncoder = passwordEncoder;
        this.studentRepo = studentRepo;
    }

    @Override
    public void registerStudent(RegisterDto registerDto, int active) {

        StudentLogin studentLogin = new StudentLogin();
        Student student = new Student();

        studentLogin.setEmail(registerDto.getEmail());
        studentLogin.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        studentLogin.setRoles(Arrays.asList(new Role("STUDENT")));

        student.setName(registerDto.getName());
        student.setAge(registerDto.getAge());
        student.setActive(active);

        student.setStudentLogin(studentLogin);
        studentLogin.setStudent(student);

        studentRepo.save(student);
    }

}
