package com.vedy.blog_app_backend.Model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class LocalUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;

    @OneToMany(mappedBy = "localUser", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

//    Cascade means to remove the childs if the parent is removed


}
