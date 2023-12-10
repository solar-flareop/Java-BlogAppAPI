package com.solarflare.blogAppAPI.repositories;

import com.solarflare.blogAppAPI.entities.Post;
import com.solarflare.blogAppAPI.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(User user);
}
