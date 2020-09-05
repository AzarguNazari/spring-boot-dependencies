package com.example.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
    @RequestMapping("/user")
    public User getUser() {
    	User user=userRepository.findByUserName("aa");
    	System.out.println("get a user");
        return user;
    }
    
    @RequestMapping("/users")
    public List<User> getUsers() {
    	List<User> users=userRepository.findAll();
    	System.out.println("List of users");
        return users;
    }
}