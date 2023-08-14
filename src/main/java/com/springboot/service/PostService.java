package com.springboot.service;

import com.springboot.dtos.PostDto;
import com.springboot.entity.Post;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();//getAllPosts method

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto ,long id);
}
