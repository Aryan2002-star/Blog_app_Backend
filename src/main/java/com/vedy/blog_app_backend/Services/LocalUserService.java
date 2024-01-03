package com.vedy.blog_app_backend.Services;

import com.vedy.blog_app_backend.Dto.LocalUserDto;

import java.util.List;

public interface LocalUserService {
    LocalUserDto createUser(LocalUserDto localUserDto);

    LocalUserDto updatUser(LocalUserDto localUserDto, Integer id);


    LocalUserDto getUserById(Integer id);

    List<LocalUserDto> getAllUsers();


    void deleteUserById(Integer id);
}
