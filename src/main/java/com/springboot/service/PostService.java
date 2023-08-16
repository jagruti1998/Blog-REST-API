package com.springboot.service;

import com.springboot.dtos.PostDto;
import com.springboot.dtos.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);//getAllPosts method

    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);


}