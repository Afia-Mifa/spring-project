package com.example.studentCrud.service;

import com.example.studentCrud.dto.RegisterDto;
import com.example.studentCrud.dto.UserDto;
import com.example.studentCrud.model.Role;
import com.example.studentCrud.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void createAdmin();
    Role findAdmin(String role);
    void registerStudent(RegisterDto registerDto, int active);
    List<User>  getStudentByActive(int active);
    List<User> findAllStudents();
    User getStudentById(Long id);
    void activateStudentAccount(Long id);
    void deleteStudent(User user);
    void updateStudent(UserDto user) throws Exception;
    List<User> searchStudentByName(String name);

}
