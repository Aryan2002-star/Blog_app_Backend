package com.vedy.blog_app_backend.Repository;

import com.vedy.blog_app_backend.Model.Category;
import com.vedy.blog_app_backend.Model.LocalUser;
import com.vedy.blog_app_backend.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByLocalUser(LocalUser user);
    List<Post> findByCategory(Category category);
}
