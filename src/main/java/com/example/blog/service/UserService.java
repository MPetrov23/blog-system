package com.example.blog.service;


import com.example.blog.model.User;

import java.util.List;

public interface UserService {
    public void createUser(User user);
    public User findUserByEmail(String email);
    public User findUserByUsername(String username);
    public List<User> findAllUsers();
    public void deleteUser(Long id);
}
