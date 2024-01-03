package com.vedy.blog_app_backend.Exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String one,String two,Integer id) {
        super(one+" is Not Found With  "+two +" " +id);
    }
}
