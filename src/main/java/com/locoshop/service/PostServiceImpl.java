package com.locoshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locoshop.models.Post;
import com.locoshop.models.User;
import com.locoshop.repository.PostRepositary;
import com.locoshop.repository.UserRepository;
import com.locoshop.request.PostRequest;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostRepositary postRepositary;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Post createPost(PostRequest req,User user) {
		
		Post post2 = new Post();
		post2.setUser(user);
		post2.setName(req.getName());
		post2.setArea(req.getArea());
		post2.setCity(req.getCity());
		post2.setDescription(req.getDescription());
		post2.setMobile(req.getMobile());
		post2.setPrice(req.getPrice());
		post2.setImage(req.getImage());
		
		Post savedPost = postRepositary.save(post2);
		
		
		
		return savedPost;
	}

	@Override
	public List<Post> findAllPosts() {
		
		List<Post> posts = postRepositary.findAll();
		
		return posts;
	}

	@Override
	public List<Post> getPostByUserId(Integer userid) throws Exception {
		
		List<Post> posts = postRepositary.findByUserId(userid);
		
		return posts;
	}

	@Override
	public Post getPostById(Integer postid) throws Exception {
		
		Optional<Post> post = postRepositary.findById(postid);
		
		if(post.isEmpty()) {
			throw new Exception("Post not found");
		}
		
		return post.get();
	}

	@Override
	public void deletePost(Integer postid) throws Exception {
		
		postRepositary.deleteById(postid);
		
	}

	@Override
	public String searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
