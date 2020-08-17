package com.justclick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justclick.entity.User;
import com.justclick.repository.UserRepository;

@Service
public class UserService {
	@Autowired 
	private UserRepository repository;
	
	public User findByUsername(String username) {
		return repository.findByUsername(username);
	}
	
}
