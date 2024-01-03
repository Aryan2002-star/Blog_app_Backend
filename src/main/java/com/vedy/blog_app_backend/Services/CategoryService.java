package com.vedy.blog_app_backend.Services;

import com.vedy.blog_app_backend.Dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,Integer id);
    void deleteCategory(Integer id);
    CategoryDto getCategory(Integer id);
    List<CategoryDto> getCategories();



}
