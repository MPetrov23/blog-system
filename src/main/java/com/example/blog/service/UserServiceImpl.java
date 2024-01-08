package com.example.blog.service;

import com.example.blog.model.Role;
import com.example.blog.model.User;
import com.example.blog.repository.RoleRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void createUser(User user) {
        Role role=roleRepository.findByName("ROLE_USER");
        if(role==null){
            role=createRole();
        }

        user.setActive(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(List.of(role));

        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users=userRepository.findAll();
        return users;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public Role createRole(){
        Role role=new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
}
