package com.example.blog.repository;

import com.example.blog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);

}
