package com.solarflare.blogAppAPI.services.Impl;

import com.solarflare.blogAppAPI.entities.Category;
import com.solarflare.blogAppAPI.entities.Post;
import com.solarflare.blogAppAPI.entities.User;
import com.solarflare.blogAppAPI.exceptions.ResourceNotFoundException;
import com.solarflare.blogAppAPI.payloads.PostDTO;
import com.solarflare.blogAppAPI.payloads.PostResponse;
import com.solarflare.blogAppAPI.repositories.CategoryRepo;
import com.solarflare.blogAppAPI.repositories.PostRepo;
import com.solarflare.blogAppAPI.repositories.UserRepo;
import com.solarflare.blogAppAPI.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDTO createPost(PostDTO postDTO, int userId, int categoryId) {
       User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
       Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
       Post post = this.modelMapper.map(postDTO,Post.class);

       post.setImageName("default.png");
       post.setAddedDate(new Date());
       post.setUser(user);
       post.setCategory(category);

       Post savedPost = this.postRepo.save(post);
       return this.modelMapper.map(savedPost,PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, int postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageName(postDTO.getImageName());

        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDTO.class);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize,String sortBy, String sortDir) {
        Sort sortDirection = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNo,pageSize, sortDirection);
        Page<Post> pagePost = this.postRepo.findAll(p);
        List<Post>allPosts = pagePost.getContent();

        List<PostDTO>postDtoList = allPosts.stream().map((post -> this.modelMapper.map(post,PostDTO.class))).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(postDtoList);
        postResponse.setPageNo(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPosts(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDTO getPostById(int postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        return this.modelMapper.map(post,PostDTO.class);
    }

    @Override
    public void deletePost(int postId){
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDTO> getPostsByUser(int userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        List<Post>posts = this.postRepo.findByUser(user);
        List<PostDTO>allPosts = posts.stream().map((post -> this.modelMapper.map(post,PostDTO.class))).collect(Collectors.toList());
        return allPosts;
    }

    @Override
    public List<PostDTO> getPostsByCategory(int categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        List<Post>posts = this.postRepo.findByCategory(category);
        List<PostDTO>allPosts = posts.stream().map((post)->this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        return allPosts;
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        List<PostDTO> postDtoList = posts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return postDtoList;
    }
}
