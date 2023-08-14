package com.springboot.controller;

import com.springboot.dtos.PostDto;
import com.springboot.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //ceate blogpost RESTAPI
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){//created a method to create Post and providedPostDto as parameter
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);//returning ResponseEntity object

    }
}
