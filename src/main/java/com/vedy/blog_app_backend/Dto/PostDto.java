package com.vedy.blog_app_backend.Dto;


import com.vedy.blog_app_backend.Model.Category;
import com.vedy.blog_app_backend.Model.LocalUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDto category;
    private LocalUserDto user;

}
