package com.vedy.blog_app_backend.Controller;


import com.vedy.blog_app_backend.Config.AppConstants;
import com.vedy.blog_app_backend.Dto.PostDto;
import com.vedy.blog_app_backend.Dto.PostResponse;
import com.vedy.blog_app_backend.Exception.ApiResponse;

import com.vedy.blog_app_backend.Services.FileService;
import com.vedy.blog_app_backend.Services.PostService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;
    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){
        PostDto post = this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(post, HttpStatus.CREATED);

    }

    @GetMapping("/user/{userid}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userid){

        List<PostDto> postDtos = this.postService.getPostsByUser(userid);

        return new ResponseEntity<>(postDtos,HttpStatus.OK);

    }
    @GetMapping("/category/{catid}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer catid){

        List<PostDto> postDtos = this.postService.getPostsByCategory(catid);

        return new ResponseEntity<>(postDtos,HttpStatus.OK);

    }

    //get all posts
    //@Requestparms used to get value from the url(PageNumber and Size)
    //we created postresponse class to get the required info
    //The default valy=ue in request param is used to set the total value in
    @GetMapping("/all")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam (value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE ,required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY,required = false) String sortBy ,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIRECTION ,required = false) String sortDir)
    {
        //Here we get Default value as constants so we add it to config file and access values from there.
        PostResponse posts = this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("post/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Integer id){
        PostDto postDto = this.postService.getPostById(id);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse deletePost(@PathVariable Integer id){
        this.postService.deletePost(id);
        return new ApiResponse("Post is successfully deleted","True");
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer id){

        PostDto postDto1 = this.postService.updatePost(postDto,id);


        return new ResponseEntity<>(postDto1,HttpStatus.OK);
    }

    //Search By Title
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPosts(@PathVariable String keyword){
        List<PostDto> postDtos = this.postService.searchPosts(keyword);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }




    //POST IMAGE UPLOAD
    @PostMapping("/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage (@RequestParam ("image")MultipartFile image,@PathVariable Integer postId) throws IOException {
        PostDto postDto = this.postService.getPostById(postId);

        String fileName = this.fileService.uploadImage(path,image);
        postDto.setImageName(fileName);
        PostDto updatedPost = this.postService.updatePost(postDto,postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);

    }

    //GET IMAGE ON PAGE
    @GetMapping(value = "/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName , HttpServletResponse response) throws IOException{
        InputStream res = this.fileService.getResources(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(res,response.getOutputStream());
    }


}
