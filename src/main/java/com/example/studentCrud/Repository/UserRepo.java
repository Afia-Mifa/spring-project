package com.example.studentCrud.Repository;

import com.example.studentCrud.model.Role;
import com.example.studentCrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findAllByActive(int active);
    List<User> findAllByRolesAndActive(Role role,int active);
    Optional<User> findByRolesAndId(Role role, Long id);
    List<User> findByName(String name);

}
