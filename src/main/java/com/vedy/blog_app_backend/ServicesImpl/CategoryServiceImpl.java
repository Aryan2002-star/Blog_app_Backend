package com.vedy.blog_app_backend.ServicesImpl;

import com.vedy.blog_app_backend.Dto.CategoryDto;
import com.vedy.blog_app_backend.Exception.ResourceNotFoundException;
import com.vedy.blog_app_backend.Model.Category;
import com.vedy.blog_app_backend.Repository.CategoryRepo;
import com.vedy.blog_app_backend.Services.CategoryService;
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
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = this.modelMapper.map(categoryDto, Category.class);
        Category savedcat = this.categoryRepo.save(cat);

        return this.modelMapper.map(savedcat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category cat = this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","ID",id));
        cat.setCategoryname(categoryDto.getCategoryName());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCate = this.categoryRepo.save(cat);

        return this.modelMapper.map(updatedCate,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer id) {
        Category cat = this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","Id",id));
        this.categoryRepo.delete(cat);

    }

    @Override
    public CategoryDto getCategory(Integer id) {
        Category cat = this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","ID",id));
        return this.modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> allcategories = categories.stream().map((category)->this.modelMapper.map(category,CategoryDto.class))
                .collect(Collectors.toList());
        return allcategories;
    }
}
