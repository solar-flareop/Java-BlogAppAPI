package com.solarflare.blogAppAPI.controllers;

import com.solarflare.blogAppAPI.payloads.APIResponse;
import com.solarflare.blogAppAPI.payloads.CategoryDTO;
import com.solarflare.blogAppAPI.services.CategoryService;
import com.solarflare.blogAppAPI.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO>getCategoryById(@PathVariable("id") int categoryID){
        CategoryDTO category = this.categoryService.getCategoryById(categoryID);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>>getAllCategories(){
        List<CategoryDTO>categories = this.categoryService.getAllCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CategoryDTO>createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO category = this.categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(category,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO>updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable("id") int categoryId){
        CategoryDTO category = this.categoryService.updateCategory(categoryDTO,categoryId);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse>deleteCategory(@PathVariable("id") int categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new APIResponse("Category deleted successfully",true),HttpStatus.OK);
    }
}
