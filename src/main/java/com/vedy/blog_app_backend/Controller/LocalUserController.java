package com.vedy.blog_app_backend.Controller;


import com.vedy.blog_app_backend.Dto.LocalUserDto;
import com.vedy.blog_app_backend.Exception.ApiResponse;
import com.vedy.blog_app_backend.Services.LocalUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/localuser/")
public class LocalUserController {



    @Autowired
    private LocalUserService localUserService;


    @PostMapping("/adduser")
    public ResponseEntity<LocalUserDto> createUser(@Valid @RequestBody LocalUserDto localUserDto){
        LocalUserDto localUserDto1 = this.localUserService.createUser(localUserDto);
        return new ResponseEntity<>(localUserDto1, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LocalUserDto>> getAllUsers(){
        return new ResponseEntity<>(this.localUserService.getAllUsers(),HttpStatus.ACCEPTED);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        localUserService.deleteUserById(id);
//        return new ResponseEntity<>(Map.of("message","UserDeleted Successfully"),HttpStatus.OK);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully","true"),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LocalUserDto> updateUser(@Valid @RequestBody LocalUserDto localUserDto,@PathVariable Integer id){
    LocalUserDto localUserDto1 = localUserService.updatUser(localUserDto,id);
    return new ResponseEntity<>(localUserDto1 , HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<LocalUserDto> getUser(@PathVariable Integer id){
        return ResponseEntity.ok(localUserService.getUserById(id));
    }

}
