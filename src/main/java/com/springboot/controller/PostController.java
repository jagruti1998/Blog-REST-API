package com.springboot.controller;

import com.springboot.dtos.PostDto;
import com.springboot.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //get all posts rest api
    @GetMapping
    public List<PostDto> getAllPosts(
            @RequestParam(value="pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize
    )
    {
        return postService.getAllPosts(pageNo,pageSize);
    }

    //get post by id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id){
        return ResponseEntity.ok(postService.getPostById(id));

    }

    // update post by id rest api
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id){

        PostDto postResponse = postService.updatePost(postDto, id);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // delete post rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){

        postService.deletePostById(id);

        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }
}
