package com.locoshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.locoshop.models.Post;
import com.locoshop.models.User;
import com.locoshop.request.PostRequest;
import com.locoshop.response.AuthResponse;
import com.locoshop.service.PostService;
import com.locoshop.service.UserService;

@RestController
public class PostController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PostService postService;
	
	@PostMapping("/make-post")
	public ResponseEntity<Post> createPost(@RequestBody PostRequest req,@RequestHeader("Authorization") String jwt){
	
		User user = userService.getUserByToken(jwt);
		
		Post post2 = postService.createPost(req, user);
		
		return new ResponseEntity<>(post2,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/getUser-post")
	public ResponseEntity<List<Post>> getPostByUserId(@RequestHeader("Authorization") String jwt) throws Exception{
		
		  User user = userService.getUserByToken(jwt);
		  
		  List<Post> posts = postService.getPostByUserId(user.getId());
		
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}

	
	
	@GetMapping("/findAllPosts")
	public ResponseEntity<List<Post>> findAllPost(){
		
		List<Post> posts = postService.findAllPosts();
		
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/findpostbyid/{postid}")
	public ResponseEntity<Post> getPostbyId(@PathVariable Integer postid) throws Exception{
		
		Post post = postService.getPostById(postid);
		
		return new ResponseEntity<>(post,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-post/{id}")
	public void deletePost(@PathVariable Integer id) throws Exception {
		
		postService.deletePost(id);
	}
}
