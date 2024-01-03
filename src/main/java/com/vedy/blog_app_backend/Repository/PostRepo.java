package com.vedy.blog_app_backend.Repository;

import com.vedy.blog_app_backend.Model.Category;
import com.vedy.blog_app_backend.Model.LocalUser;
import com.vedy.blog_app_backend.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByLocalUser(LocalUser user);
    List<Post> findByCategory(Category category);

    List<Post> findPostByTitleContains(String keyword);

//    if we want to write custom queries we can see video 26 of blog Application backend Code with Durgesh
    // Youtube Link : https://youtu.be/bUQnCfK67fw?si=nYqAzBZ_F9_IUb6y

}
