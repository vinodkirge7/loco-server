package com.locoshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locoshop.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmail( String email); 
}
