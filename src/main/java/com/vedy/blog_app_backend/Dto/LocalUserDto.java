package com.vedy.blog_app_backend.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LocalUserDto {
    private int id;
    @NotEmpty
    @Size(min = 4,message = "Username must be minimum 4 charecters !!")
    private String name;
    @Email(message = "Email Address not valid")
    private String email;

    @NotEmpty
    @Size(min=4 ,max = 20 ,message = "Password must be minimum 4 charecters and 20 charecters maximum")
    private String password;
    @NotEmpty
    private String about;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public LocalUserDto(int id, String name, String email, String password, String about) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
    }

    public LocalUserDto(String name, String email, String password, String about) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
    }

    public LocalUserDto() {
    }
}
