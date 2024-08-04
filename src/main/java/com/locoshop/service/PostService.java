package com.locoshop.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.locoshop.models.Post;
import com.locoshop.models.User;
import com.locoshop.request.PostRequest;

public interface PostService {
	
	public Post createPost(PostRequest req,User user);
	
	public List<Post> findAllPosts();
	
	public List<Post> getPostByUserId(Integer userid) throws Exception;
	
	public Post getPostById(Integer postid) throws Exception;
	
	public void deletePost(Integer postid) throws Exception;
	
	public String searchPost(String keyword);

}
