package com.solarflare.blogAppAPI.services.Impl;

import com.solarflare.blogAppAPI.entities.Category;
import com.solarflare.blogAppAPI.exceptions.ResourceNotFoundException;
import com.solarflare.blogAppAPI.payloads.CategoryDTO;
import com.solarflare.blogAppAPI.repositories.CategoryRepo;
import com.solarflare.blogAppAPI.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category newCategory = this.dtoToCategory(categoryDTO);
        Category addedCategory=this.categoryRepo.save(newCategory);
        return this.categoryToDto(addedCategory);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, int categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());
        Category updatedCategory = this.categoryRepo.save(category);
        return this.categoryToDto(updatedCategory);
    }

    @Override
    public CategoryDTO getCategoryById(int categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        return this.categoryToDto(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category>categories = this.categoryRepo.findAll();
        List<CategoryDTO>categoryDTOS = categories.stream().map(category -> this.categoryToDto(category)).collect(Collectors.toList());
        return categoryDTOS;
    }

    @Override
    public void deleteCategory(int categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        this.categoryRepo.delete(category);
    }

    public Category dtoToCategory (CategoryDTO categoryDTO){
        Category category = this.modelMapper.map(categoryDTO,Category.class);
        return category;
    }

    public CategoryDTO categoryToDto(Category category){
        CategoryDTO categoryDTO = this.modelMapper.map(category,CategoryDTO.class);
        return categoryDTO;
    }
}
