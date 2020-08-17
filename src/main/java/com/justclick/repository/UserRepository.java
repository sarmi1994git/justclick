package com.justclick.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.justclick.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
