package com.solarflare.blogAppAPI.services.Impl;

import com.solarflare.blogAppAPI.entities.Post;
import com.solarflare.blogAppAPI.payloads.PostDTO;
import com.solarflare.blogAppAPI.repositories.PostRepo;
import com.solarflare.blogAppAPI.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Post createPost(PostDTO postDTO) {
        return null;
    }

    @Override
    public Post updatePost(PostDTO postDTO, int postId) {
        return null;
    }

    @Override
    public List<Post> getAllPosts() {
        return null;
    }

    @Override
    public List<Post> getPostById(int postId) {
        return null;
    }

    @Override
    public void deletePost(int postId) {

    }

    @Override
    public List<Post> getPostsByUser(int userId) {
        return null;
    }

    @Override
    public List<Post> getPostsByCategory(int categoryId) {
        return null;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}
