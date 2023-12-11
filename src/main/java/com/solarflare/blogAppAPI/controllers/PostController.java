package com.solarflare.blogAppAPI.controllers;

import com.solarflare.blogAppAPI.config.AppConstants;
import com.solarflare.blogAppAPI.payloads.APIResponse;
import com.solarflare.blogAppAPI.payloads.PostDTO;
import com.solarflare.blogAppAPI.payloads.PostResponse;
import com.solarflare.blogAppAPI.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@Valid  @RequestBody PostDTO postDTO,
                                           @PathVariable("userId") int userId,
                                           @PathVariable("categoryId") int categoryId) {
        PostDTO post = this.postService.createPost(postDTO,userId,categoryId);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>>getPostsByUser(@PathVariable("userId") int userId){
        List<PostDTO>allPosts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<>(allPosts,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>>getPostsByCategory(@PathVariable("categoryId") int categoryId){
        List<PostDTO>allPosts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(allPosts,HttpStatus.OK);
    }

    @GetMapping("/posts")  //api/posts?pageNo=0&pageSize=5&sortBy=postId&sortDir=asc
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false)int pageSize,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
        PostResponse post = this.postService.getAllPosts(pageNo, pageSize,sortBy,sortDir);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO>getPostById(@PathVariable int postId){
        PostDTO post = this.postService.getPostById(postId);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO>updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable int postId){
        PostDTO post = this.postService.updatePost(postDTO,postId);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<APIResponse> deletePost(@PathVariable("id") int postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new APIResponse("Post deleted Successfully!",true),HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable("keyword") String keyword){
        List<PostDTO> posts = this.postService.searchPosts(keyword);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
}
