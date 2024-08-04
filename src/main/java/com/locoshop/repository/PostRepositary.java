package com.locoshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locoshop.models.Post;

public interface PostRepositary extends JpaRepository<Post, Integer> {
	
	public List<Post> findByUserId(Integer userid);

}
