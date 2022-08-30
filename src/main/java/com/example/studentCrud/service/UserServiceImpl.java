package com.example.studentCrud.service;

import com.example.studentCrud.Repository.RoleRepo;
import com.example.studentCrud.Repository.UserRepo;
import com.example.studentCrud.dto.RegisterDto;
import com.example.studentCrud.dto.UserDto;
import com.example.studentCrud.model.Role;
import com.example.studentCrud.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createAdmin() {
        if (findAdmin("ADMIN") == null) {
            User user = new User();
            user.setEmail("admin@mail.com");
            user.setName("Admin");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setAge(36);
            user.setRoles(Arrays.asList(new Role("ADMIN")));
            user.setActive(-1);
            userRepo.save(user);
        }
    }

    @Override
    public Role findAdmin(String role) {
        return roleRepo.findByRoleName(role);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username);

        if (user==null){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }

    public void createStudent(RegisterDto registerDto, int active, Role role){
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoles(Collections.singletonList(role));
        user.setName(registerDto.getName());
        user.setAge(registerDto.getAge());
        user.setActive(active);
        userRepo.save(user);
    }
    @Override
    public void registerStudent(RegisterDto registerDto, int active) {
             Role role = roleRepo.findByRoleName("STUDENT");

        if(role==null){
            createStudent(registerDto,active,new Role("STUDENT"));

        }else{
            createStudent(registerDto,active,role);
        }
    }

    @Override
    public List<User> getStudentByActive(int active) {
        List<User> activeStudents = userRepo.findAllByActive(active);
        return activeStudents;
    }

    @Override
    public List<User> findAllStudents() {

        List<User> students = userRepo.findAllByRolesAndActive(roleRepo.findByRoleName("STUDENT"),1);
        return students;
    }
    @Override
    public User getStudentById(Long id) {
        Optional<User> studentOptional = userRepo.findById(id);
        User user;
        if (studentOptional.isPresent()) {
            user = studentOptional.get();
        } else {
            throw new RuntimeException("Student not found");
        }
        return user;
    }

    @Override
    public void activateStudentAccount(Long id) {
        User user = getStudentById(id);
        user.setActive(1);
        userRepo.save(user);
    }

    @Override
    public void deleteStudent(User user) {
        userRepo.delete(user);
    }
    @Override
    public void updateStudent(UserDto user) throws Exception {
        User existingUser = userRepo.findById(user.getId()).orElse(null);
        if (Objects.nonNull(existingUser)){
            existingUser.setName(user.getName());
            existingUser.setAge(user.getAge());
            userRepo.save(existingUser);
        } else {
            throw new Exception("User Not Found");
        }
    }

    @Override
    public List<User> searchStudentByName(String name) {
        return userRepo.findByName(name);
    }
}
