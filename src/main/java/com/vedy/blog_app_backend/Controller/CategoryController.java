package com.vedy.blog_app_backend.Controller;

import com.vedy.blog_app_backend.Dto.CategoryDto;
import com.vedy.blog_app_backend.Exception.ApiResponse;
import com.vedy.blog_app_backend.Services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @PostMapping("/addcategory")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
    }

    @PutMapping("/update/{catid}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catid){
        CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto,catid);
        return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{delId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer delId){
        this.categoryService.deleteCategory(delId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Successfully","true"),HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer id){
        CategoryDto getCategory = this.categoryService.getCategory(id);
        return new ResponseEntity<CategoryDto>(getCategory,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> allcategories = this.categoryService.getCategories();
        return new ResponseEntity<List<CategoryDto>>(allcategories,HttpStatus.OK);
    }


}
