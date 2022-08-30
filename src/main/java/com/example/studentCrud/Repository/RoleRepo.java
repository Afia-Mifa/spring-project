package com.example.studentCrud.Repository;

import com.example.studentCrud.model.Role;
import com.example.studentCrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByRoleName(String roleName);

}
