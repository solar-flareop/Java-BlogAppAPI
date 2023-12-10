package com.solarflare.blogAppAPI.services;

import com.solarflare.blogAppAPI.payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO,int categoryId);
    CategoryDTO getCategoryById(int categoryId);
    List<CategoryDTO> getAllCategories();
    void deleteCategory(int categoryId);

}
