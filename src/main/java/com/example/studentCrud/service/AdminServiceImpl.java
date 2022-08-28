package com.example.studentCrud.service;

import com.example.studentCrud.Repository.AdminRepo;
import com.example.studentCrud.model.Admin;
import com.example.studentCrud.model.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepo adminRepo;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepo adminRepo, PasswordEncoder passwordEncoder) {
        this.adminRepo = adminRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createAdmin() {
        adminList().forEach(System.out::println);
        if (adminList().size() == 0) {
            Admin admin = new Admin();
            admin.setName("Admin");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setRoles(Arrays.asList(new Role("ADMIN")));
            adminRepo.save(admin);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepo.findAdminByName(username);

        if (admin == null) {
            throw new UsernameNotFoundException("Not a valid admin");
        }
        return new AdminPrincipal(admin);
    }

    @Override
    public List<Admin> adminList() {
        return adminRepo.findAll();
    }

}
