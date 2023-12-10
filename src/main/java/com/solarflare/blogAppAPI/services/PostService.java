package com.solarflare.blogAppAPI.services;

import com.solarflare.blogAppAPI.entities.Post;
import com.solarflare.blogAppAPI.payloads.PostDTO;

import java.util.List;


public interface PostService {

    Post createPost(PostDTO postDTO);
    Post updatePost(PostDTO postDTO, int postId);
    List<Post>getAllPosts();
    List<Post>getPostById(int postId);
    void deletePost(int postId);

    //get all posts of user
    List<Post>getPostsByUser(int userId);

    //get all posts by category
    List<Post>getPostsByCategory(int categoryId);

    //search by keyword
    List<Post>searchPosts(String keyword);

}
