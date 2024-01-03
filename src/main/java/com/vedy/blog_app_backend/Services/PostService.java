package com.vedy.blog_app_backend.Services;

import com.vedy.blog_app_backend.Dto.PostDto;
import com.vedy.blog_app_backend.Dto.PostResponse;
import com.vedy.blog_app_backend.Model.Post;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto,Integer usedId,Integer categoryId);
    PostDto updatePost(PostDto postDto,Integer id);
    void deletePost(Integer id);


    //ghet post by id
    PostDto getPostById(Integer id);
    //get all posts
    PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

    //get all posts by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all posts by user
    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);

}
