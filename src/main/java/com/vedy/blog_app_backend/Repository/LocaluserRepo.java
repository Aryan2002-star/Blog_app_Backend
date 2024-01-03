package com.vedy.blog_app_backend.Repository;

import com.vedy.blog_app_backend.Model.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocaluserRepo extends JpaRepository<LocalUser,Integer> {
}
