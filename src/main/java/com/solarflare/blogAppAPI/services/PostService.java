package com.solarflare.blogAppAPI.services;

import com.solarflare.blogAppAPI.payloads.PostDTO;
import com.solarflare.blogAppAPI.payloads.PostResponse;

import java.util.List;


public interface PostService {

    PostDTO createPost(PostDTO postDTO,int userId,int categoryId);
    PostDTO updatePost(PostDTO postDTO, int postId);
    PostResponse getAllPosts(int pageNo , int pageSize ,String sortBy,String sortDir);
    PostDTO getPostById(int postId);
    void deletePost(int postId);

    //get all posts of user
    List<PostDTO>getPostsByUser(int userId);

    //get all posts by category
    List<PostDTO>getPostsByCategory(int categoryId);

    //search by keyword
    List<PostDTO>searchPosts(String keyword);

}
