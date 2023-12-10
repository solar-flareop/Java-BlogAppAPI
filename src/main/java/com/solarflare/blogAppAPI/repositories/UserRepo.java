package com.solarflare.blogAppAPI.repositories;

import com.solarflare.blogAppAPI.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
