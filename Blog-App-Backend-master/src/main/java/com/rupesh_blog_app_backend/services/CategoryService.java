package com.rupesh_blog_app_backend.services;

import com.rupesh_blog_app_backend.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,int categoryId);
    CategoryDto getCategoryById(int categoryId);
    void deleteCategoryById(int categoryId);
    List<CategoryDto> getAllCategory();

}
