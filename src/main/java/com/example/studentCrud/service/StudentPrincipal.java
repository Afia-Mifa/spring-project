package com.example.studentCrud.service;

import com.example.studentCrud.model.Role;
import com.example.studentCrud.model.Student;
import com.example.studentCrud.model.StudentLogin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class StudentPrincipal implements UserDetails {

    StudentLogin studentLogin;

    public StudentPrincipal(StudentLogin studentLogin) {
        this.studentLogin = studentLogin;
    }

    public Collection<? extends GrantedAuthority> mapRolesToAuthority(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return mapRolesToAuthority(studentLogin.getRoles());
    }

    @Override
    public String getPassword() {
        return studentLogin.getPassword();
    }

    @Override
    public String getUsername() {
        return studentLogin.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
