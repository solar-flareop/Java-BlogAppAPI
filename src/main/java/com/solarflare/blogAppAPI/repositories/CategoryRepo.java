package com.solarflare.blogAppAPI.repositories;

import com.solarflare.blogAppAPI.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
