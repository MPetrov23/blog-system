package com.example.blog.repository;

import com.example.blog.model.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Password,Long> {
}