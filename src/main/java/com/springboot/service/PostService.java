package com.springboot.service;

import com.springboot.dtos.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts(int pageNo, int pageSize );//getAllPosts method

    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);

}