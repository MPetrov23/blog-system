package com.example.blog;

import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class BlogSystemApplicationTests {
	@Autowired
	UserServiceImpl userService;
	@Autowired
	UserRepository userRepository;


	@Test
	void contextLoads() {
	}

	@Test
	@Transactional
	void testCreateUser() {
		String firstName="Mario";
		String lastName="Petrov";
		String username="mario10";
		String email="mario@mail.com";
		String password="password123";

		User user=new User(
				firstName,
				lastName,
				username,
				email,
				password
				);

		userService.createUser(user);



		User savedUser = userRepository.findByUsername(username);
		assertNotNull(savedUser);
		assertNotNull(savedUser.getId());
		assertEquals(firstName, savedUser.getFirstName());
		assertEquals(lastName, savedUser.getLastName());
		assertEquals(username, savedUser.getUsername());
		assertEquals(email, savedUser.getEmail());
		assertNotNull(savedUser.getPassword());
		assertNotNull(savedUser.getRoles());
		assertEquals(1, savedUser.getRoles().size());
	}

}
