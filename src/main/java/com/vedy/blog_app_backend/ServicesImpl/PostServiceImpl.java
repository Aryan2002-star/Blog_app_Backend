package com.vedy.blog_app_backend.ServicesImpl;

import com.vedy.blog_app_backend.Dto.PostDto;
import com.vedy.blog_app_backend.Dto.PostResponse;
import com.vedy.blog_app_backend.Exception.ResourceNotFoundException;
import com.vedy.blog_app_backend.Model.Category;
import com.vedy.blog_app_backend.Model.LocalUser;
import com.vedy.blog_app_backend.Model.Post;
import com.vedy.blog_app_backend.Repository.CategoryRepo;
import com.vedy.blog_app_backend.Repository.LocaluserRepo;
import com.vedy.blog_app_backend.Repository.PostRepo;
import com.vedy.blog_app_backend.Services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private LocaluserRepo localuserRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto,Integer usedId,Integer categoryId) {

        LocalUser userr = this.localuserRepo.findById(usedId).orElseThrow(()->new ResourceNotFoundException("User","user Id",usedId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));

        Post post = this.modelMapper.map(postDto,Post.class);
        post.setCategory(category);
        post.setImageName("default.png");
        post.setLocalUser(userr);
        post.setAddedDate(new Date());

        Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost,PostDto.class);

    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer id) {
        Post post = this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("POst","post id",id));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updated = this.postRepo.save(post);
        PostDto postDto1 = this.modelMapper.map(updated,PostDto.class);
        return postDto1;
    }

    @Override
    public void deletePost(Integer id) {
        Post post = this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","Post id",id));
        this.postRepo.delete(post);

    }

    @Override
    public PostDto getPostById(Integer id) {
        Post post = this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",id));
        PostDto postDto = this.modelMapper.map(post,PostDto.class);
        return postDto;
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {

        Sort sort = null;


        //TO get the order of sort is ascending or descending
        if(sortDir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }else{
            sort = Sort.by(sortBy).descending();

        }


        Pageable p = PageRequest.of(pageNumber,pageSize,sort); //Create object for getting content in pages
        Page<Post> postPage = this.postRepo.findAll(p); // This will load the content into matching pages
        List<Post> posts = postPage.getContent(); //To get the content of the paticular page


        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());

        return postResponse;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category cta = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category Id",categoryId));
        List<Post> posts = this.postRepo.findByCategory(cta);

        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        LocalUser user = this.localuserRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
        List<Post> posts = this.postRepo.findByLocalUser(user);
        List <PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }



    //Search By title
    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.findPostByTitleContains(keyword);
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }




}
